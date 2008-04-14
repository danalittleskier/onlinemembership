package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "BATCHDETAIL")

public class BatchDetail implements Serializable
{

	@Id
	@Column(name = "BATCH_ID", nullable = false, length = 7)
	Long batchId;

	@Column(name = "SEQUENCE", nullable = false, length = 4)
	Long sequence;

	@Column(name = "USSA_ID", nullable = false, length = 7)
	Long ussaId;

	@Column(name = "INV_ID", nullable = false, length = 8)
	String invId;

	@Column(name = "QTY", nullable = true, length = 4)
	Integer qty;

	@Column(name = "AMOUNT", nullable = true, length = 11)
	BigDecimal amount;

	@Column(name = "SENT_DATE", nullable = true)
	Date sentDate;

	@Column(name = "PROCESS_DATE", nullable = true)
	Date processDate;

	public Long getBatchId()
	{
		return batchId;
	}

	public void setBatchId(Long batchId)
	{
		this.batchId = batchId;
	}

	public Long getSequence()
	{
		return sequence;
	}

	public void setSequence(Long sequence)
	{
		this.sequence = sequence;
	}

	public Long getUssaId()
	{
		return ussaId;
	}

	public void setUssaId(Long ussaId)
	{
		this.ussaId = ussaId;
	}

	public String getInvId()
	{
		return invId;
	}

	public void setInvId(String invId)
	{
		this.invId = invId;
	}

	public Integer getQty()
	{
		return qty;
	}

	public void setQty(Integer qty)
	{
		this.qty = qty;
	}

	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}

	public Date getSentDate()
	{
		return sentDate;
	}

	public void setSentDate(Date sentDate)
	{
		this.sentDate = sentDate;
	}

	public Date getProcessDate()
	{
		return processDate;
	}

	public void setProcessDate(Date processDate)
	{
		this.processDate = processDate;
	}

}
