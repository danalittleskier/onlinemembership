package org.ussa.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import java.io.Serializable;

import org.appfuse.model.BaseObject;

@Entity
@Table(name = "DIVISION")

public class Division extends BaseObject implements Serializable
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

	@Override
	public boolean equals(Object o)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
