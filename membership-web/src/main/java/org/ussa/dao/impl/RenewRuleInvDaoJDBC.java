package org.ussa.dao.impl;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.ussa.dao.InventoryDao;
import org.ussa.dao.RenewRuleInvDao;
import org.ussa.model.Inventory;
import org.apache.commons.lang.StringUtils;

/**
 * @author John Jenson
 */
public class RenewRuleInvDaoJDBC implements RenewRuleInvDao
{
	private DataSource dataSource;
	private String RECOMMDENDED_MEMBERSHIPS_SQL = "select r.inv_id " +
			"from membertransaction mt " +
			"inner join member m on mt.ussa_id = m.ussa_id " +
			"inner join renewruleinv r on mt.inv_id = r.inv_id " +
			"where mt.ussa_id = ? " +
			"and mt.season = ? " +
			"and ? between r.age_from and r.age_to " +
			"and (r.division_code = m.division_code or r.division_code is null) " +
			"group by r.inv_id, r.division_code " +
			"order by r.inv_id, r.division_code";

	private String DUES_SQL = "select r.inv_id, r.new_inv_id " +
			"from renewruleinv r" +
			"where " +
			"r.division_code = ? " +
			"and ? between r.age_from and r.age_to " +
			"and inv_id in ($INVENTORY_LIST$) " +
			"group by r.inv_id, r.new_inv_id";

	private InventoryDao inventoryDao;

	public void setInventoryDao(InventoryDao inventoryDao)
	{
		this.inventoryDao = inventoryDao;
	}

	public List<Inventory> getRecommendedMemberships(Long ussaID, Integer currentSeasonAge, String lastSeason)
	{
		Object[] parameters = {ussaID, lastSeason, currentSeasonAge};
		RecommendedMembershipsQuery rmQuery = new RecommendedMembershipsQuery(getDataSource());
		List<Inventory> memberships = (List<Inventory>) rmQuery.execute(parameters);
		removeFIS(memberships);
		return memberships;
	}

	private void removeFIS(List<Inventory> lineItems)
	{
		// Don't bring FIS over from previous years
		for (Iterator<Inventory> iterator = lineItems.iterator(); iterator.hasNext();)
		{
			Inventory inventory = iterator.next();
			String invID = inventory.getId();
			if (invID.startsWith("FIS"))
			{
				iterator.remove();
			}
		}
	}

	public List<Inventory> getDivisionDues(String divisionCode, Integer currentSeasonAge, List<String> membershipIds)
	{
		String query = DUES_SQL;
		StringBuffer inventoryWhere = new StringBuffer();
		for (int i = 0; i < membershipIds.size(); i++)
		{
			if(i > 0)
			{
				inventoryWhere.append(",");
			}
			inventoryWhere.append("?");
		}
		query = StringUtils.replace(query, "$INVENTORY_LIST$", inventoryWhere.toString());
		DuesQuery duesQuery = new DuesQuery(getDataSource(), query, membershipIds.size());

		List<Object> parameters = new ArrayList<Object>();
		parameters.add(divisionCode);
		parameters.add(currentSeasonAge);
		parameters.addAll(membershipIds);

		List<Inventory> dues = new ArrayList<Inventory>();
		Set<String> dueIds = new HashSet<String>();
		List<Map<String, String>> newOldInvIdMappings = (List<Map<String, String>>) duesQuery.execute(parameters.toArray());
		for (Map<String, String> newOldInvIdMapping : newOldInvIdMappings)
		{
			String invId = newOldInvIdMapping.get("inv_id");
			String[] newInvIds = newOldInvIdMapping.get("new_inv_id").split(",");
			for (String newInvId : newInvIds)
			{
				String newInvIdUpper = newInvId.toUpperCase();
				if(!dueIds.contains(invId) && (newInvIdUpper.startsWith("DD") || newInvIdUpper.startsWith("SD")))
				{
					dueIds.add(invId);
					Inventory due = inventoryDao.get(newInvId);
					if("Y".equals(due.getActive().toUpperCase()))
					{
						dues.add(due);
					}
				}
			}
		}

		return dues;
	}

	public void setDataSource(final DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	private DataSource getDataSource()
	{
		return this.dataSource;
	}

	private class RecommendedMembershipsQuery extends MappingSqlQuery
	{
		RecommendedMembershipsQuery(DataSource dataSource)
		{
			super(dataSource, RECOMMDENDED_MEMBERSHIPS_SQL);
			declareParameter(new SqlParameter(Types.NUMERIC));
			declareParameter(new SqlParameter(Types.NUMERIC));
			declareParameter(new SqlParameter(Types.VARCHAR));
		}

		public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException
		{
			return inventoryDao.get(resultSet.getString("inv_id"));
		}
	}

	private class DuesQuery extends MappingSqlQuery
	{
		DuesQuery(DataSource dataSource, String query, int invIdsSize)
		{
			super(dataSource, query);
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.NUMERIC));

			for (int i = 0; i < invIdsSize; i++)
			{
				declareParameter(new SqlParameter(Types.VARCHAR));
			}
		}

		public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException
		{
			Map<String, String> map = new HashMap<String, String>();
			map.put("inv_id", resultSet.getString("inv_id"));
			map.put("new_inv_id", resultSet.getString("new_inv_id"));
			return map;
		}
	}

}
