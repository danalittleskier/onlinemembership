package org.ussa.dao.impl;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.ussa.common.dao.hibernate.GenericDaoHibernate;
import org.ussa.dao.MemberTransactionDao;
import org.ussa.model.MemberTransaction;

/**
 * User: jminer
 * Date: Apr 15, 2008
 * Time: 7:09:46 AM
 */
public class MemberTransactionDaoImpl extends GenericDaoHibernate<MemberTransaction, Long> implements MemberTransactionDao
{

	private DataSource dataSource;

	public MemberTransactionDaoImpl()
	{
		super(MemberTransaction.class);
	}

	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	public List<MemberTransaction> getMemberTransactionsForSeason(Long ussaId, String season)
	{
		return (List<MemberTransaction>) getHibernateTemplate()
				.find("from MemberTransaction t where t.member.id = ? and t.season = ?",
						new Object[]{ussaId, season});
	}

	public boolean hasHeldIventory(Long ussaId, String invId)
	{
		CountPurchasedInventoryQuery query = new CountPurchasedInventoryQuery(dataSource);
		List<Boolean> results = (List<Boolean>) query.execute(new Object[]{ussaId, invId});
		return results.get(0);
	}

	private class CountPurchasedInventoryQuery extends MappingSqlQuery
	{
		CountPurchasedInventoryQuery(DataSource dataSource)
		{
			super(dataSource, "select count(1) num from membertransaction where ussa_id = ? and inv_id = ?");
			declareParameter(new SqlParameter(Types.NUMERIC));
			declareParameter(new SqlParameter(Types.VARCHAR));
		}

		public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException
		{
			return resultSet.getInt(1) > 0;
		}
	}
}
