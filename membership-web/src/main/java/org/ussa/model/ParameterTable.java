package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Table(name = "PARAMETERTABLE")

public class ParameterTable implements Serializable
{
	public static final String LATE_RENEW_DATE = "LATE_RENEW_DATE";
	public static final String LAST_DAY_RENEW_SEASON = "LAST_DY_RENEWSEASN";

	@Id
	@Column(name = "PARAMETER_CODE", nullable = false, length = 20, unique = false)
	private String parameterCode;

	@Column(name = "PARAMETER_DATA", nullable = true, length = 20, unique = false)
	private String parameterData;

	@Column(name = "PARAMETER_DESCRIPTION", nullable = true, length = 80, unique = false)
	private String parameterDescription;

	public String getParameterCode()
	{
		return parameterCode;
	}

	public void setParameterCode(String parameterCode)
	{
		this.parameterCode = parameterCode;
	}

	public String getParameterData()
	{
		return parameterData;
	}

	public void setParameterData(String parameterData)
	{
		this.parameterData = parameterData;
	}

	public String getParameterDescription()
	{
		return parameterDescription;
	}

	public void setParameterDescription(String parameterDescription)
	{
		this.parameterDescription = parameterDescription;
	}
}
