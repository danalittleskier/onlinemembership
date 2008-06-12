package org.ussa.service.impl;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.mail.SimpleMailMessage;
import org.ussa.beans.AccountBean;
import org.ussa.beans.LineItemBean;
import org.ussa.dao.BatchDao;
import org.ussa.dao.BatchTransactionDao;
import org.ussa.model.Batch;
import org.ussa.service.BatchService;
import org.ussa.util.UssaJavaMailer;

public class BatchServiceImpl implements BatchService
{
	private BatchDao batchDao;
	private BatchTransactionDao batchTransactionDao;
	private UssaJavaMailer ussaJavaMailer;


	public void setBatchDao(BatchDao batchDao)
	{
		this.batchDao = batchDao;
	}

	public void setBatchTransactionDao(BatchTransactionDao batchTransactionDao)
	{
		this.batchTransactionDao = batchTransactionDao;
	}

	public void setUssaJavaMailer(UssaJavaMailer ussaJavaMailer)
	{
		this.ussaJavaMailer = ussaJavaMailer;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Batch doBatchTableInsert(AccountBean accountBean, List<LineItemBean> inventoryAddLineItems, String currentSeason)
	{
		Batch batch = getOrOpenBatch(currentSeason);

		Long batchSequence;
		batchSequence = batchTransactionDao.getNextBatchSequenceAndLockTable(batch);
		// we don't want a batch to ever get bigger than 100. if so then close out the current batch and create a new one.
		//if(batchSequence >= 100)
		//{
		//	closeBatch(batch);
		//	batch = createNewBatch(currentSeason);
		//	batchSequence = batchTransactionDao.getNextBatchSequenceAndLockTable(batch);
		//}

		batchTransactionDao.doBatchInsert(batch, batchSequence, accountBean, inventoryAddLineItems);

		return batch;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Batch getOrOpenBatch(String currentSeason)
	{
		// start by aquiring a lock on the batch table so that we can make sure that we don't create double batches
		batchTransactionDao.aquireLockOnBatchTable();

		Batch batch = batchDao.getMostRecentBatch();

		if(batch == null || batch.getCloseDate() != null)
		{
			batch = createNewBatch(currentSeason);
		}
		return batch;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Batch createNewBatch(String currentSeason)
	{
		Batch batch = new Batch();
		batch.setOpenDate(new Date());
		batch.setOpenUserId("MembershipWeb");
		batch.setSeason(currentSeason);
		batchDao.save(batch);
		return batch;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void closeBatch(Batch batch)
	{
		batch.setCloseDate(new Date());
		batch.setCloseUserId("MembershipWeb");
		batchDao.save(batch);

		sendBatchCloseEmail(batch);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void closeCurrentlyOpenBatch()
	{
		// start by aquiring a lock on the batch table so that we can make sure that we are actually closing the most recent batch
		batchTransactionDao.aquireLockOnBatchTable();

		Batch batch = batchDao.getMostRecentBatch();
		if(batch != null)
		{
			closeBatch(batch);
		}
	}

	private static SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");

	private void sendBatchCloseEmail(Batch batch)
	{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("support@ussa.org");
		message.setTo("membership@ussa.org");
		message.setCc(new String[] {"lbenevento@ussa.org"});
		message.setSubject("Membership Web - Batch Closed");

		StringBuffer body = new StringBuffer();
		body.append("Web Batch Completed.\n");
		body.append("Batch ID: "+batch.getBatchId()+"\n");
		body.append("Opened: "+format.format(batch.getOpenDate())+"\n");
		body.append("Closed: "+format.format(batch.getCloseDate())+"\n");
		message.setText(body.toString());
		ussaJavaMailer.safeSend(message);
	}
}
