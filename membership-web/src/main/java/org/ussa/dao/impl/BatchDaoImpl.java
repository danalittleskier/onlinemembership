package org.ussa.dao.impl;

import javax.sql.DataSource;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.ussa.common.dao.hibernate.GenericDaoHibernate;
import org.ussa.dao.BatchDao;
import org.ussa.dao.BatchIdGeneratorDao;
import org.ussa.model.Batch;

public class BatchDaoImpl extends GenericDaoHibernate<Batch, Long> implements BatchDao
{
	private DataSource dataSource;
	private BatchIdGeneratorDao batchIdGeneratorDao;

	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	public DataSource getDataSource()
	{
		return dataSource;
	}

	public void setBatchIdGeneratorDao(BatchIdGeneratorDao batchIdGeneratorDao)
	{
		this.batchIdGeneratorDao = batchIdGeneratorDao;
	}

	public BatchDaoImpl()
	{
		super(Batch.class);
	}

	@Override
	public Batch save(Batch batch)
	{
		if (batch.getBatchId() == null)
		{
			batch.setBatchId(batchIdGeneratorDao.getNextBatchId());
			getHibernateTemplate().save(batch);
		}
		else
		{
			getHibernateTemplate().update(batch);
		}

		return batch;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Batch getMostRecentBatch()
	{
		List results = getHibernateTemplate().find("from Batch b where b.batchId = (select max(bt.batchId) from Batch bt where bt.batchId >= 10000)");
		if(results.size() > 0)
		{
			return (Batch) results.get(0);
		}

		return null;
	}
}
