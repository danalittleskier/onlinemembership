package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "BATCHPAYMENT")

public class BatchPayment implements Serializable
{
	@Id
	@Column(name = "ID", nullable = false, length = 7)
	Long id;

	@Column(name = "BATCH_ID", nullable = false, length = 7)
	Long batchId;

	@Column(name = "SEQUENCE", nullable = false, length = 4)
	Long sequence;

	@Column(name = "PAYMENT_TYPE", nullable = false, length = 10)
	String paymentType;

	@Column(name = "CHECK_NUMBER", nullable = true, length = 10)
	String checkNumber;

	@Column(name = "CC_NUMBER", nullable = false, length = 16)
	String ccNumber;

	@Column(name = "CC_EXP", nullable = true, length = 6)
	String ccExp;

	@Column(name = "AMOUNT", nullable = true, length = 10)
	BigDecimal amount;

	@Column(name = "AMOUNT_CODE", nullable = true, length = 20)
	String amountCode;


	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

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

	public String getPaymentType()
	{
		return paymentType;
	}

	public void setPaymentType(String paymentType)
	{
		this.paymentType = paymentType;
	}

	public String getCheckNumber()
	{
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber)
	{
		this.checkNumber = checkNumber;
	}

	public String getCcNumber()
	{
		return ccNumber;
	}

	public void setCcNumber(String ccNumber)
	{
		this.ccNumber = ccNumber;
	}

	public String getCcExp()
	{
		return ccExp;
	}

	public void setCcExp(String ccExp)
	{
		this.ccExp = ccExp;
	}

	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}

	public String getAmountCode()
	{
		return amountCode;
	}

	public void setAmountCode(String amountCode)
	{
		this.amountCode = amountCode;
	}

}
