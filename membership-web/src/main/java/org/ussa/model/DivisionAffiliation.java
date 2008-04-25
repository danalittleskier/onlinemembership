package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
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

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) // this needs to be EAGER in order for the dwr marshaller not to complain.
	@JoinColumn(name = "DIVISION_CODE", nullable = true, unique = false)
	private Division division;


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

	public Division getDivision()
	{
		return division;
	}

	public void setDivision(Division division)
	{
		this.division = division;
	}
}
