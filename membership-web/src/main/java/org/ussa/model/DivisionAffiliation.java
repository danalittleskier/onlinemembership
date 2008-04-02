package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Table(name = "DIVISIONAFFILIATION")

public class DivisionAffiliation implements Serializable
{

	@Id
	@Column(name = "STATE_CODE", nullable = false, length = 2, unique = false)
	private String stateCode;

	@Column(name = "ZIP_CODE", nullable = true, length = 10, unique = false)
	private String zipCode;

	@Column(name = "DIVISION_CODE", nullable = false, length = 1, unique = false)
	private String divisionCode;


	public String getStateCode()
	{
		return stateCode;
	}

	public void setStateCode(String stateCode)
	{
		this.stateCode = stateCode;
	}

	public String getZipCode()
	{
		return zipCode;
	}

	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}

	public String getDivisionCode()
	{
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode)
	{
		this.divisionCode = divisionCode;
	}
}
