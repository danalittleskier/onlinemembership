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
	@Column(name = "IND_USSA_ID", nullable = false, length = 5, unique = false)
	private String indUssaId;

	@Column(name = "CLUB_USSA_ID", nullable = false, length = 5, unique = false)
	private String clubUssaId;

	public MemberClub()
	{
	}

	public String getIndUssaId()
	{
		return indUssaId;
	}

	public void setIndUssaId(String indUssaId)
	{
		this.indUssaId = indUssaId;
	}

	public String getClubUssaId()
	{
		return clubUssaId;
	}

	public void setClubUssaId(String clubUssaId)
	{
		this.clubUssaId = clubUssaId;
	}

}
