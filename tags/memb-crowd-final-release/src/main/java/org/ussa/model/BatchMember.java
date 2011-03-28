package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "BATCHMEMBER")

public class BatchMember implements Serializable
{
	@Id
	@Column(name = "BATCH_ID", nullable = false, length = 7)
	Long batchId;

	@Column(name = "SEQUENCE", nullable = false, length = 4)
	Long sequence;

	@Column(name = "USSA_ID", nullable = false, length = 7)
	Long ussaId;

	@Column(name = "PROCESSED", nullable = true, length = 1)
	String processed;

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

	public String getProcessed()
	{
		return processed;
	}

	public void setProcessed(String processed)
	{
		this.processed = processed;
	}

}
