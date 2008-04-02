package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MemberLegalPk implements Serializable
{
	@Column(name = "SEASON", nullable = false, length = 4)
	private String season;

	@Column(name = "USSA_ID", nullable = false, length = 7)
	private Long ussaId;

	public String getSeason()
	{
		return season;
	}

	public void setSeason(String season)
	{
		this.season = season;
	}

	public Long getUssaId()
	{
		return ussaId;
	}

	public void setUssaId(Long ussaId)
	{
		this.ussaId = ussaId;
	}
}
