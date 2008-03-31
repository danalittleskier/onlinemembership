package org.ussa.beans;

import java.math.BigDecimal;

import org.ussa.model.Inventory;

public class LineItemBean
{
	Inventory inventory;
	String description;
	BigDecimal amount;

	public Inventory getInventory()
	{
		return inventory;
	}

	public void setInventory(Inventory inventory)
	{
		this.inventory = inventory;
	}

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
