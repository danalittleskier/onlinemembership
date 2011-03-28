package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "MEMBERCLUB")

public class MemberClub implements Serializable
{

	@Id
	@Column(name = "IND_USSA_ID", nullable = false, length = 7, unique = false)
	private Long indUssaId;

	@Column(name = "CLUB_USSA_ID", nullable = false, length = 7, unique = false)
	private Long clubUssaId;

	public MemberClub()
	{
	}

	public Long getIndUssaId()
	{
		return indUssaId;
	}

	public void setIndUssaId(Long indUssaId)
	{
		this.indUssaId = indUssaId;
	}

	public Long getClubUssaId()
	{
		return clubUssaId;
	}

	public void setClubUssaId(Long clubUssaId)
	{
		this.clubUssaId = clubUssaId;
	}

}
