package org.ussa.service;

import java.util.List;

import org.ussa.model.Batch;
import org.ussa.beans.AccountBean;
import org.ussa.beans.LineItemBean;

public interface BatchService
{
	public Batch doBatchTableInsert(AccountBean accountBean, List<LineItemBean> inventoryAddLineItems, String currentSeason);

	public Batch getOrOpenBatch(String currentSeason);

	public Batch createNewBatch(String currentSeason);

	public void closeBatch(Batch batch);

	public void closeCurrentlyOpenBatch();
}
