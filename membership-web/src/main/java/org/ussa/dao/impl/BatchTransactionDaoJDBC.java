package org.ussa.dao.impl;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;
import org.ussa.beans.AccountBean;
import org.ussa.beans.CartBean;
import org.ussa.beans.LineItemBean;
import org.ussa.beans.PaymentBean;
import org.ussa.dao.BatchTransactionDao;
import org.ussa.model.Inventory;
import org.ussa.model.Member;

public class BatchTransactionDaoJDBC implements BatchTransactionDao
{
	private DataSource dataSource;
	private String INSERT_BATCHPAYMENT_SQL = "insert into BatchPayment (Batch_id,Sequence,Payment_Type, " +
			" Check_Number,cc_number, CC_EXP,Amount,Account_Code) " +
			" Values (?, ?, 'CREDIT', NULL, ?, ?, ?, NULL)";
	private String INSERT_BATCHMEMBER_SQL = "Insert Into BatchMember (Batch_Id, Sequence, USSA_Id, Processed)" +
			" Values (?, ?, ?, 'Y')";
	private String INSERT_BATCHDETAIL_SQL = "Insert Into BatchDetail (Batch_id, Sequence, USSA_ID, Inv_Id, Qty, Amount)" +
			" Values (?, ?, ?, ?, ?, ?)";
	private String INSERT_BATCHSEQUENCE_SQL = "Insert Into BatchSequence (Batch_Id, Sequence, Receive_Date)" +
			" Values (?, ?, getDate())";
	private String SELECT_MAX_SQL = "Select Max(Sequence)+1 as MaxSeq from BatchMember where Batch_Id=? ";

	public void setDataSource(final DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	private DataSource getDataSource()
	{
		return this.dataSource;
	}

	public void insertToBatchTables(AccountBean accountBean)
	{
		Member member = accountBean.getMember();
		PaymentBean payment = accountBean.getPaymentBean();
		CartBean cart = accountBean.getCartBean();

		Object[] parameters = {123123};
		SelectMaxSeq maxQuery = new SelectMaxSeq(getDataSource());
		List top = maxQuery.execute(parameters);


		String maxS = (String) top.get(0);
		InsertBatchPayment bp = new InsertBatchPayment(getDataSource());
		Object[] bpParams = {123123, maxS, payment.getCardNumber(), payment.getExpireMonth() + "/" + payment.getExpireYear(), cart.getTotal()};
		bp.update(bpParams);

		InsertBatchMember bm = new InsertBatchMember(getDataSource());
		Object[] params = {123123, maxS, member.getId()};
		bm.update(params);

		InsertBatchDetail bd = new InsertBatchDetail(getDataSource());
		List<LineItemBean> lineItems = cart.getLineItems();
		for (LineItemBean lineItem : lineItems)
		{
			Inventory inventory = lineItem.getInventory();
			Object[] detailParams = {123123, maxS, member.getId(), inventory.getId(), lineItem.getQty(), lineItem.getDiscountedAmount()};
			bd.update(detailParams);
		}

		InsertBatchSequence bs = new InsertBatchSequence(getDataSource());
		Object[] seqParams = {123123, maxS};
		bs.update(seqParams);

	}

	private class SelectMaxSeq extends MappingSqlQuery
	{
		SelectMaxSeq(DataSource dataSource)
		{
			super(dataSource, SELECT_MAX_SQL);
			declareParameter(new SqlParameter(Types.VARCHAR));
		}

		public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException
		{
			return resultSet.getString(1);
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
