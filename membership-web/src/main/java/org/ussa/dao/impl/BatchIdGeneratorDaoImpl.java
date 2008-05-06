package org.ussa.dao.impl;

import org.hibernate.LockMode;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.ussa.dao.BatchIdGeneratorDao;
import org.ussa.model.ParameterTable;

public class BatchIdGeneratorDaoImpl extends HibernateDaoSupport implements BatchIdGeneratorDao
{
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Long getNextBatchId()
	{
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		ParameterTable param = (ParameterTable) hibernateTemplate.load(ParameterTable.class, ParameterTable.WEB_BATCH, LockMode.UPGRADE);
		Long batchId = Long.valueOf(param.getParameterData());
		param.setParameterData(String.valueOf(batchId + 1));
		return batchId;
	}
}
