package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table (name="NATION")

public class Nation implements Serializable {

	@Id
	@Column(name = "NATION_CODE", nullable = false, length=3, unique=false)
	private String nationCode;

	@Column(name = "DESCRIPTION", nullable = false, length=30, unique=false)
	private String description;

	public Nation() {
	}

	public String getNationCode()
	{
		return nationCode;
	}

	public void setNationCode(String nationCode)
	{
		this.nationCode = nationCode;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
}
