package org.ussa.dao.impl;

import javax.sql.DataSource;
import java.sql.Types;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.ussa.beans.AccountBean;
import org.ussa.beans.CartBean;
import org.ussa.beans.LineItemBean;
import org.ussa.beans.PaymentBean;
import org.ussa.dao.BatchTransactionDao;
import org.ussa.model.Batch;
import org.ussa.model.Inventory;
import org.ussa.model.Member;

public class BatchTransactionDaoJDBC implements BatchTransactionDao
{
	private DataSource dataSource;
	private String INSERT_BATCHPAYMENT_SQL = "insert into BatchPayment (Batch_id,Sequence,Payment_Type, " +
			" Check_Number,cc_number, CC_EXP,Amount,Account_Code, Transaction_Id) " +
			" Values (?, ?, 'CREDIT', NULL, ?, ?, ?, NULL, ?)";
	private String INSERT_BATCHMEMBER_SQL = "Insert Into BatchMember (Batch_Id, Sequence, USSA_Id, Processed)" +
			" Values (?, ?, ?, 'Y')";
	private String INSERT_BATCHDETAIL_SQL = "Insert Into BatchDetail (Batch_id, Sequence, USSA_ID, Inv_Id, Qty, Amount)" +
			" Values (?, ?, ?, ?, ?, ?)";
	private String INSERT_BATCHSEQUENCE_SQL = "Insert Into BatchSequence (Batch_Id, Sequence, Receive_Date)" +
			" Values (?, ?, getDate())";
	private String SELECT_MAX_SQL = "Select max(Sequence) as MaxSeq from BatchSequence with (tablockx) where Batch_Id=? ";
	private String LOCK_BATCH_SQL = "Select * from BatchSequence with (tablockx) where Batch_Id=0 ";

	public void setDataSource(final DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	private DataSource getDataSource()
	{
		return this.dataSource;
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void doBatchInsert(Batch batch, Long batchSequence, AccountBean accountBean, List<LineItemBean> inventoryAddLineItems)
	{
		Member member = accountBean.getMember();
		PaymentBean payment = accountBean.getPaymentBean();
		CartBean cart = accountBean.getCartBean();

		Long batchId = batch.getBatchId();

		InsertBatchPayment bp = new InsertBatchPayment(getDataSource());
		String expireYear = payment.getExpireYear();
		expireYear = expireYear.substring(expireYear.length()-2, expireYear.length()); // only take the last 2 digits.
		Object[] bpParams = {batchId, batchSequence, lastFour(payment.getCardNumber()), payment.getExpireMonth() + "/" + expireYear,
				cart.getTotal(), payment.getCompletedTransactionId()};
		bp.update(bpParams);

		InsertBatchMember bm = new InsertBatchMember(getDataSource());
		Object[] params = {batchId, batchSequence, member.getId()};
		bm.update(params);

		InsertBatchDetail bd = new InsertBatchDetail(getDataSource());
		List<LineItemBean> lineItems = cart.getLineItems();
		if(inventoryAddLineItems != null)
		{
			lineItems.addAll(inventoryAddLineItems);
		}
		for (LineItemBean lineItem : lineItems)
		{
			Inventory inventory = lineItem.getInventory();
			Object[] detailParams = {batchId, batchSequence, member.getId(), inventory.getId(), lineItem.getQty(), lineItem.getDiscountedAmount()};
			bd.update(detailParams);
		}

		InsertBatchSequence bs = new InsertBatchSequence(getDataSource());
		Object[] seqParams = {batchId, batchSequence};
		bs.update(seqParams);

	}

	/**
	 * This give you an exclusive lock on the batch table until the current transaction is complete.
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void aquireLockOnBatchTable()
	{
		LockBatchTableQuery lockQuery = new LockBatchTableQuery(getDataSource());
		lockQuery.execute();
	}

	/**
	 * This gets the max sequence id + 1 and locks the table until the current transaction is complete to ensure that
	 * no one else gets the same sequence id that you got.
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Long getNextBatchSequenceAndLockTable(Batch batch)
	{
		SelectMaxSeqAndLock maxSeq = new SelectMaxSeqAndLock(getDataSource());
		List results = maxSeq.execute(new Object[]{batch.getBatchId()});
		Long seq = (Long) results.get(0);

		if(seq == null)
		{
			seq = 0L;
		}

		return seq+1;
	}

	private String lastFour(String ccNumber)
	{
		if(StringUtils.isNotBlank(ccNumber))
		{
			int length = ccNumber.length();
			if(length >= 4)
			{
				return ccNumber.substring(length-4, length);
			}
		}

		return null;
	}

	private class LockBatchTableQuery extends MappingSqlQuery
	{
		LockBatchTableQuery(DataSource dataSource)
		{
			super(dataSource, LOCK_BATCH_SQL);
		}

		public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException
		{
			return 0;
		}
	}

	private class SelectMaxSeqAndLock extends MappingSqlQuery
	{
		SelectMaxSeqAndLock(DataSource dataSource)
		{
			super(dataSource, SELECT_MAX_SQL);
			declareParameter(new SqlParameter(Types.VARCHAR));
		}

		public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException
		{
			return resultSet.getLong(1);
		}
	}

	private class InsertBatchPayment extends SqlUpdate
	{
		public InsertBatchPayment(DataSource ds)
		{

			super(ds, INSERT_BATCHPAYMENT_SQL);
			declareParameter(new SqlParameter(Types.NUMERIC));
			declareParameter(new SqlParameter(Types.NUMERIC));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));

		}

	}

	private class InsertBatchMember extends SqlUpdate
	{
		public InsertBatchMember(DataSource ds)
		{

			super(ds, INSERT_BATCHMEMBER_SQL);
			declareParameter(new SqlParameter(Types.NUMERIC));
			declareParameter(new SqlParameter(Types.NUMERIC));
			declareParameter(new SqlParameter(Types.VARCHAR));

		}

	}

	private class InsertBatchDetail extends SqlUpdate
	{
		public InsertBatchDetail(DataSource ds)
		{

			super(ds, INSERT_BATCHDETAIL_SQL);
			declareParameter(new SqlParameter(Types.NUMERIC));
			declareParameter(new SqlParameter(Types.NUMERIC));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.NUMERIC));

		}

	}

	private class InsertBatchSequence extends SqlUpdate
	{
		public InsertBatchSequence(DataSource ds)
		{
			super(ds, INSERT_BATCHSEQUENCE_SQL);
			declareParameter(new SqlParameter(Types.NUMERIC));
			declareParameter(new SqlParameter(Types.NUMERIC));
		}
	}
}
