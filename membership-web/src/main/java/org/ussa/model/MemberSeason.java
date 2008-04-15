package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "MEMBERSEASON")

public class MemberSeason implements Serializable
{
	@EmbeddedId
	MemberSeasonPk memberSeasonPk;

	@Column(name = "APP_PROCESS_DATE", nullable = true, unique = false)
	private Date appProcessDate;

	@Column(name = "APP_RECEIVE_DATE", nullable = true, unique = false)
	private Date appReceiveDate;

	public MemberSeasonPk getMemberSeasonPk()
	{
		return memberSeasonPk;
	}

	public void setMemberSeasonPk(MemberSeasonPk memberSeasonPk)
	{
		this.memberSeasonPk = memberSeasonPk;
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
