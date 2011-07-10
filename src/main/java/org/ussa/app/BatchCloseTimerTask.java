package org.ussa.app;

import java.util.TimerTask;

import org.ussa.service.BatchService;

public class BatchCloseTimerTask extends TimerTask
{
	BatchService batchService;


	public void setBatchService(BatchService batchService)
	{
		this.batchService = batchService;
	}

	public void run()
	{
		batchService.closeCurrentlyOpenBatch();
	}
}
