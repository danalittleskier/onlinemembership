package org.ussa.dao.impl;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Iterator;
import java.util.List;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.ussa.dao.InventoryDao;
import org.ussa.dao.RenewRuleInvDao;
import org.ussa.model.Inventory;

/**
 * @author John Jenson
 */
public class RenewRuleInvDaoJDBC implements RenewRuleInvDao
{
	private DataSource dataSource;
	/* private String RECOMMDENDED_MEMBERSHIPS_SQL = "select r.inv_id " +
			"from membertransaction mt " +
			"inner join member m on mt.ussa_id = m.ussa_id " +
			"inner join renewruleinv r on mt.inv_id = r.inv_id " +
			"where mt.ussa_id = ? " +
			"and mt.season = ? " +
			"and ? between r.age_from and r.age_to " +
			"and (r.division_code = m.division_code or r.division_code is null) " +
			"group by r.inv_id " +
			"order by r.inv_id"; */

	private String RECOMMDENDED_MEMBERSHIPS_SQL = "select r.new_inv_id as inv_id " +
		"from membertransaction mt, member m, renewruleinv r " +
		"where mt.ussa_id = m.ussa_id " +
		"and mt.inv_id = r.inv_id " +
		"and mt.ussa_id = ? " +
		"and mt.season = ? " +
		"and ? between r.age_from and r.age_to " +
		"and (r.division_code = m.division_code or r.division_code is null) " +
		"group by r.new_inv_id " +
		"order by r.new_inv_id"; 
	
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
}
