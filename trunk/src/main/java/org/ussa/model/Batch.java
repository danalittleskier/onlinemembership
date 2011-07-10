package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="BATCH")
public class Batch implements Serializable
{
	@Id
	@Column(name = "BATCH_ID", length=7)
	private Long batchId;

	@Column(name = "SEASON", nullable = true, length=4, unique=false)
	private String season;

	@Column(name = "OPEN_DATE", nullable = true, unique=false)
	private Date openDate;

	@Column(name = "CLOSE_DATE", nullable = true, unique=false)
	private Date closeDate;

	@Column(name = "OPEN_USERID", nullable = true, length=15, unique=false)
	private String openUserId;

	@Column(name = "CLOSE_USERID", nullable = true, length=15, unique=false)
	private String closeUserId;

	public Long getBatchId()
	{
		return batchId;
	}

	public void setBatchId(Long batchId)
	{
		this.batchId = batchId;
	}

	public String getSeason()
	{
		return season;
	}

	public void setSeason(String season)
	{
		this.season = season;
	}

	public Date getOpenDate()
	{
		return openDate;
	}

	public void setOpenDate(Date openDate)
	{
		this.openDate = openDate;
	}

	public Date getCloseDate()
	{
		return closeDate;
	}

	public void setCloseDate(Date closeDate)
	{
		this.closeDate = closeDate;
	}

	public String getOpenUserId()
	{
		return openUserId;
	}

	public void setOpenUserId(String openUserId)
	{
		this.openUserId = openUserId;
	}

	public String getCloseUserId()
	{
		return closeUserId;
	}

	public void setCloseUserId(String closeUserId)
	{
		this.closeUserId = closeUserId;
	}

}
