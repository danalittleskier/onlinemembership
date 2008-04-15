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
	public static final String DIVISION_EASTERN = "E";
	public static final String DIVISION_ROCKY = "R";
	public static final String DIVISION_ALASKA = "A";
	public static final String DIVISION_INTERMOUNTAIN = "I";
	public static final String DIVISION_CENTRAL = "C";
	public static final String DIVISION_NORTHERN = "N";
	public static final String DIVISION_FAR_WEST = "F";
	public static final String DIVISION_PACIFIC_NORTHWEST = "P";
	public static final String DIVISION_FOREIGN = "X";

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
