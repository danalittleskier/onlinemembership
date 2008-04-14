package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "MEMBERSEASON")

public class MemberSeason implements Serializable
{
	@Id
	@Column(name = "USSA_ID", nullable = false, length = 7)
	private Long ussaId;

	@Column(name = "SEASON", nullable = false, length = 4, unique = false)
	private String season;

	@Column(name = "APP_PROCESS_DATE", nullable = true, unique = false)
	private Date appProcessDate;

	@Column(name = "APP_RECEIVE_DATE", nullable = true, unique = false)
	private Date appReceiveDate;

	public Long getUssaId()
	{
		return ussaId;
	}

	public void setUssaId(Long ussaId)
	{
		this.ussaId = ussaId;
	}

	public String getSeason()
	{
		return season;
	}

	public void setSeason(String season)
	{
		this.season = season;
	}

	public Date getAppProcessDate()
	{
		return appProcessDate;
	}

	public void setAppProcessDate(Date appProcessDate)
	{
		this.appProcessDate = appProcessDate;
	}

	public Date getAppReceiveDate()
	{
		return appReceiveDate;
	}

	public void setAppReceiveDate(Date appReceiveDate)
	{
		this.appReceiveDate = appReceiveDate;
	}
}
