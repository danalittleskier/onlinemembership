package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "BATCHSEQUENCE")

public class BatchSequence implements Serializable
{
	@Id
	@Column(name = "BATCH_ID", nullable = false, length = 7)
	Long batchId;

	@Column(name = "SEQUENCE", nullable = false, length = 4)
	Long sequence;

	@Column(name = "RECEIVE_DATE", nullable = true)
	Date receiveDate;

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

	public Date getReceiveDate()
	{
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate)
	{
		this.receiveDate = receiveDate;
	}

}
