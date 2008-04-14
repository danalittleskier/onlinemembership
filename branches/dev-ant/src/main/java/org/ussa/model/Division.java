package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "DIVISION")

public class Division implements Serializable
{
	@Id
	@Column(name = "DIVISION_CODE", nullable = false, length = 21)
	private String divisionCode;

	@Column(name = "DESCRIPTION", nullable = false, length = 20)
	private String description;

	public Division()
	{
	}

	public String getDivisionCode()
	{
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode)
	{
		this.divisionCode = divisionCode;
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
