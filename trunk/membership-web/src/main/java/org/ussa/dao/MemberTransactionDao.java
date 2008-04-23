package org.ussa.dao;

import java.util.List;

import org.ussa.common.dao.GenericDao;
import org.ussa.model.MemberTransaction;

/**
 * User: jminer
 * Date: Apr 15, 2008
 * Time: 7:08:23 AM
 */
public interface MemberTransactionDao extends GenericDao<MemberTransaction, Long>
{
	public List<MemberTransaction> getMemberTransactionsForSeason(Long ussaId, String season);

	public boolean hasHeldIventory(Long ussaId, String invId);
}
