package org.ussa.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import org.ussa.model.Inventory;

public class LineItemBean implements Serializable
{
	Inventory inventory;
	String description;
	BigDecimal amount;
	BigDecimal discount;
	Integer qty;
	Date validFrom;
	Date validTo;

	public LineItemBean()
	{
	}

	public LineItemBean(Inventory inventory)
	{
		this.inventory = inventory;
		this.description = inventory.getRenewDescription();
		this.amount = inventory.getAmount();
		this.qty = 1;
	}

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

	public String getAmountFormatted()
	{
		if(getDiscount() != null)
		{
			DecimalFormat format = new DecimalFormat("###,##0.00");
			return "$"+format.format(getAmount());
		}
		return "";
	}

	public String getDiscountFormatted()
	{
		if(getDiscount() != null)
		{
			DecimalFormat format = new DecimalFormat("###,##0.00");
			return "$"+format.format(getDiscount());
		}
		return "";
	}

	public String getLineItemTotalFormatted()
	{
		DecimalFormat format = new DecimalFormat("###,##0.00");
		return "$"+format.format(getLineItemTotal());
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
	
	
}
