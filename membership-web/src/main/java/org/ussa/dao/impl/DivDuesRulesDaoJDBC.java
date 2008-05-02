package org.ussa.dao.impl;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.ussa.dao.DivDuesRulesDao;
import org.ussa.dao.InventoryDao;
import org.ussa.model.Inventory;

/**
 * @author John Jenson
 */
public class DivDuesRulesDaoJDBC implements DivDuesRulesDao
{
	private DataSource dataSource;

	private String DUES_SQL = "select r.divdue_inv_id " +
			"from divduesrules r " +
			"where " +
			"r.division_code = ? " +
			"and ? between r.age_from and r.age_to " +
			"and r.inv_id in ($INVENTORY_LIST$) " +
			"and late_fee = ? " +
			"group by r.divdue_inv_id";

	private InventoryDao inventoryDao;

	public void setInventoryDao(InventoryDao inventoryDao)
	{
		this.inventoryDao = inventoryDao;
	}

	public List<Inventory> getDivisionDues(String divisionCode, Integer currentSeasonAge, List<String> membershipIds, boolean late)
	{
		List<Inventory> dues = new ArrayList<Inventory>();

		if(membershipIds != null && membershipIds.size() > 0)
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
			parameters.add(late ? "Y" : "N");

			dues = (List<Inventory>) duesQuery.execute(parameters.toArray());
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
			declareParameter(new SqlParameter(Types.VARCHAR));
		}

		public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException
		{
			return inventoryDao.get(resultSet.getString("divdue_inv_id"));
		}
	}

}
