package org.ussa.dao;

import org.ussa.beans.AccountBean;
import org.ussa.model.Batch;

public interface BatchTransactionDao
{
	public void doBatchInsert(Batch batch, Long batchSequence, AccountBean accountBean);

	public Long getNextBatchSequence(Batch batch);
}
