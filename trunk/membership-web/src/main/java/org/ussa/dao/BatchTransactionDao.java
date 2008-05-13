package org.ussa.dao;

import java.util.List;

import org.ussa.beans.AccountBean;
import org.ussa.beans.LineItemBean;
import org.ussa.model.Batch;

public interface BatchTransactionDao
{
	public void doBatchInsert(Batch batch, Long batchSequence, AccountBean accountBean, List<LineItemBean> inventoryAddLineItems);

	public void aquireLockOnBatchTable();

	public Long getNextBatchSequenceAndLockTable(Batch batch);
}
