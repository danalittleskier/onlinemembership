package org.ussa.beans;

import java.math.BigDecimal;

public class LineItemBean
{
	String description;

	BigDecimal amount;


	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
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
