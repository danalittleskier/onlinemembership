package org.ussa.dao;

import org.ussa.common.dao.GenericDao;
import org.ussa.model.Batch;

public interface BatchDao extends GenericDao<Batch, Long>
{
	public Batch getMostRecentBatch();
}
