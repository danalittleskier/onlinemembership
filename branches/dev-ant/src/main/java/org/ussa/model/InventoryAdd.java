package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "INVENTORYADD")

public class InventoryAdd implements Serializable
{
	@Id
	@Column(name = "ID", nullable = false, length = 5)
	Long id;

	@Column(name = "SEL_INV_ID", nullable = true, length = 8)
	String selInvId;

	@Column(name = "DIVISION_CODE", nullable = true, length = 2)
	String divisionCode;

	@Column(name = "ADD_INV_ID", nullable = true, length = 8)
	String addInvId;


	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getSelInvId()
	{
		return selInvId;
	}

	public void setSelInvId(String selInvId)
	{
		this.selInvId = selInvId;
	}

	public String getDivisionCode()
	{
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode)
	{
		this.divisionCode = divisionCode;
	}

	public String getAddInvId()
	{
		return addInvId;
	}

	public void setAddInvId(String addInvId)
	{
		this.addInvId = addInvId;
	}

}
