package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "RENEWRULEINV")

public class RenewRuleInv implements Serializable
{
	@Id
	@Column(name = "ID", nullable = false, length = 5)
	Long id;

	@Column(name = "INV_ID", nullable = true, length = 8)
	String InvId;

	@Column(name = "AGE_FROM", nullable = true, length = 3)
	Integer ageFrom;

	@Column(name = "AGE_TO", nullable = true, length = 3)
	Integer ageTo;

	@Column(name = "DIVISION_CODE", nullable = true, length = 2)
	String divisionCode;

	@Column(name = "NEW_INV_ID", nullable = true, length = 30)
	String newInvId;

	@Column(name = "MSG_ID", nullable = true, length = 30)
	String msgId;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getInvId()
	{
		return InvId;
	}

	public void setInvId(String invId)
	{
		InvId = invId;
	}

	public Integer getAgeFrom()
	{
		return ageFrom;
	}

	public void setAgeFrom(Integer ageFrom)
	{
		this.ageFrom = ageFrom;
	}

	public Integer getAgeTo()
	{
		return ageTo;
	}

	public void setAgeTo(Integer ageTo)
	{
		this.ageTo = ageTo;
	}

	public String getDivisionCode()
	{
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode)
	{
		this.divisionCode = divisionCode;
	}

	public String getNewInvId()
	{
		return newInvId;
	}

	public void setNewInvId(String newInvId)
	{
		this.newInvId = newInvId;
	}

	public String getMsgId()
	{
		return msgId;
	}

	public void setMsgId(String msgId)
	{
		this.msgId = msgId;
	}

}
