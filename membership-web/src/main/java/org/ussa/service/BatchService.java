package org.ussa.service;

import org.ussa.model.Batch;
import org.ussa.beans.AccountBean;

public interface BatchService
{
	public Batch doBatchTableInsert(AccountBean accountBean, String currentSeason);

	public Batch getOrOpenBatch(String currentSeason);

	public Batch createNewBatch(String currentSeason);

	public void closeBatch(Batch batch);

	public void closeCurrentlyOpenBatch();
}
