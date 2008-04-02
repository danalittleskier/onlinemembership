package org.ussa.dao.impl;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.ussa.beans.LineItemBean;
import org.ussa.dao.InventoryDao;
import org.ussa.dao.RecommendedMembershipsDao;

/**
 * @author Dan McMillan
 * Adapted by John Jenson
 */
public class RecommendedMembershipsDaoJDBC implements RecommendedMembershipsDao
{
	private static final int ALPINE = 0, SNOWBOARD = 1, FREESTYLE = 2, JUMPING = 3, CROSS_COUNTRY = 4;
	private static final String ALPINE_CODE = "ACO", SNOWBOARD_CODE = "BCO",
			FREESTYLE_CODE = "FCO", JUMPING_CODE = "JCO", CROSS_COUNTRY_CODE = "XCO";
	private DataSource dataSource;
	private String RECOMMDENDED_MEMBERSHIPS_SQL = "select distinct mt.inv_id, "
			+ "r.age_from, r.age_to, r.msg_id, m.user_code1, m.state_code, " +
			" i.description, i.renew_desc, i.amount, " +
			"(? - year(m.birthdate)) AS AGE,r.new_inv_id,r.division_code  " +
			"from membertransaction mt, renewruleinv r,member m, inventory i " +
			"where mt.ussa_id = ?   " +
			" and mt.season = ?   " +
			" and mt.inv_id = r.inv_id " +
			" and i.inv_id=mt.inv_id and m.ussa_id = mt.ussa_id " +
			" and (? - year(m.birthdate)) between  r.age_from and r.age_to " +
			" and (r.division_code = m.division_code or r.division_code is Null) " +
			" order by mt.inv_id,r.division_code ";

	private InventoryDao inventoryDao;

	public void setInventoryDao(InventoryDao inventoryDao)
	{
		this.inventoryDao = inventoryDao;
	}

	public List<LineItemBean> getRecommendedMemberships(String currentYear, Long ussaID, String lastSeason)
	{
		Object[] parameters = {currentYear, ussaID, lastSeason, lastSeason};
		RecommendedMembershipsQuery rmQuery = new RecommendedMembershipsQuery(getDataSource());
		List<LineItemBean> memberships = (List<LineItemBean>) rmQuery.execute(parameters);
		cleanseList(memberships);
		return memberships;
	}

	private void cleanseList(List<LineItemBean> list)
	{
		boolean[] hasCoach = new boolean[5];
		for (int ii = 0; ii < 5; ii++)
		{
			hasCoach[ii] = false;
		}
		ArrayList<Integer> fisIndexes = new ArrayList<Integer>();
		int thisIndex = 0;
		//check for coaches...and fis
		for (LineItemBean bean : list)
		{
			String invID = bean.getInventory().getId();
			if (invID == null)
			{
				continue;
			}
			else if (invID.startsWith("FIS"))
			{
				fisIndexes.add(thisIndex);
			}
			else if (invID.equals(ALPINE_CODE))
			{
				hasCoach[ALPINE] = true;
			}
			else if (invID.equals(SNOWBOARD_CODE))
			{
				hasCoach[SNOWBOARD] = true;
			}
			else if (invID.equals(FREESTYLE_CODE))
			{
				hasCoach[FREESTYLE] = true;
			}
			else if (invID.equals(JUMPING_CODE))
			{
				hasCoach[JUMPING] = true;
			}
			else if (invID.equals(CROSS_COUNTRY_CODE))
			{
				hasCoach[CROSS_COUNTRY] = true;
			}
			thisIndex++;
		}
		//now remove officials
		for (int ii = 0; ii < 5; ii++)
		{
			if (hasCoach[ii])
			{
				removeOfficial(list, ii);
			}
		}

		for (int index : fisIndexes)
		{
			list.remove(index);
		}
	}

	private void removeOfficial(List<LineItemBean> list, int coach)
	{

		String invToRemove = null;
		switch (coach)
		{
			case ALPINE:
				invToRemove = "AO";
				break;
			case SNOWBOARD:
				invToRemove = "BO";
				break;
			case FREESTYLE:
				invToRemove = "FO";
				break;
			case JUMPING:
				invToRemove = "JO";
				break;
			case CROSS_COUNTRY:
				invToRemove = "XO";
				break;
		}
		if (invToRemove == null)
		{
			return;
		}
		int j = 0;
		int removeIndex = -1;

		for (LineItemBean bean : list)
		{
			String invID = bean.getInventory().getId();
			if (invToRemove.equalsIgnoreCase(invID))
			{
				removeIndex = j;
				break;
			}
			j++;
		}
		if (removeIndex != -1)
		{
			list.remove(removeIndex);
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
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
		}

		public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException
		{
			LineItemBean iBean = new LineItemBean();
			iBean.setDescription(resultSet.getString("description"));
			iBean.setAmount(resultSet.getBigDecimal("amount"));
			iBean.setInventory(inventoryDao.get(resultSet.getString("inv_id")));

			return iBean;
		}
	}

}
