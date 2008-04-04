package org.ussa.beans;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.ussa.model.Inventory;

public class LineItemBean
{
	Inventory inventory;
	String description;
	BigDecimal amount;
	Integer qty;

	public Inventory getInventory()
	{
		return inventory;
	}

	public void setInventory(Inventory inventory)
	{
		this.inventory = inventory;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}

	public Integer getQty()
	{
		return qty;
	}

	public void setQty(Integer qty)
	{
		this.qty = qty;
	}

	public BigDecimal getLineItemTotal()
	{
		return getAmount().multiply(new BigDecimal(getQty()));
	}

	public String getLineItemTotalFormatted()
	{
		DecimalFormat format = new DecimalFormat("###,###.00");
		return "$"+format.format(getLineItemTotal());
	}
}
