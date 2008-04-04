package org.ussa.beans;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.ussa.model.Inventory;

public class LineItemBean
{
	Inventory inventory;
	String description;
	BigDecimal amount;
	BigDecimal discount;
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

	public BigDecimal getDiscount()
	{
		return discount;
	}

	public void setDiscount(BigDecimal discount)
	{
		this.discount = discount;
	}

	public Integer getQty()
	{
		return qty;
	}

	public void setQty(Integer qty)
	{
		this.qty = qty;
	}

	public BigDecimal getDiscountedAmount()
	{
		BigDecimal discount = getDiscount();
		if(discount != null)
		{
			return getAmount().subtract(discount);
		}
		return getAmount();
	}

	public BigDecimal getLineItemTotal()
	{
		return getDiscountedAmount().multiply(new BigDecimal(getQty()));
	}

	public String getLineItemTotalFormatted()
	{
		DecimalFormat format = new DecimalFormat("###,###.00");
		return "$"+format.format(getLineItemTotal());
	}
}
