package org.ussa.dao.impl;

import org.ussa.common.dao.hibernate.GenericDaoHibernate;
import org.ussa.dao.ClubAffiliationHistoryDao;
import org.ussa.model.ClubAffiliationHistory;


public class ClubAffiliationHistoryDaoImpl extends GenericDaoHibernate<ClubAffiliationHistory, Long> implements ClubAffiliationHistoryDao
{

	public ClubAffiliationHistoryDaoImpl()
	{
		super(ClubAffiliationHistory.class);
	}

}
