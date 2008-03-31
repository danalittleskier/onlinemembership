package org.ussa.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.ussa.model.Inventory;


public class CartBean
{
	List<LineItemBean> lineItems = new ArrayList<LineItemBean>();

	public void addItem(Inventory inventory)
	{
		addItem(inventory, null);
	}
	public void addItem(Inventory inventory, BigDecimal cost)
	{
		LineItemBean lineItem = new LineItemBean();
		lineItem.setInventory(inventory);
		lineItem.setDescription(inventory.getDescription());
		if(cost != null)
		{
			lineItem.setAmount(cost);
		}
		else
		{
			if(inventory.getAmount() != null)
			{
				lineItem.setAmount(inventory.getAmount());
			}
			else
			{
				lineItem.setAmount(BigDecimal.ZERO);
			}
		}
		lineItems.add(lineItem);
	}

	public void removeLineItem(String inventoryId)
	{
		for (Iterator<LineItemBean> iterator = lineItems.iterator(); iterator.hasNext();)
		{
			LineItemBean lineItemBean = iterator.next();
			if(inventoryId.equals(lineItemBean.getInventory().getId()))
			{
				iterator.remove();
			}
		}
	}

	public LineItemBean getLineItem(String inventoryId)
	{
		for (LineItemBean lineItemBean : lineItems)
		{
			if (inventoryId.equals(lineItemBean.getInventory().getId()))
			{
				return lineItemBean;
			}
		}
		return null;
	}

	public boolean contains(String inventoryId)
	{
		return getLineItem(inventoryId) != null;
	}

	public String getTotalFormatted()
	{
		return getTotal().toString();
	}
	public BigDecimal getTotal()
	{
		BigDecimal total = BigDecimal.ZERO;
		for (LineItemBean lineItemBean : lineItems)
		{
			total = total.add(lineItemBean.getAmount());
		}
		return total;
	}

	public List<LineItemBean> getLineItems()
	{
		return lineItems;
	}

	public List<LineItemBean> getLineItems(String inventoryType)
	{
		List<LineItemBean> results = new ArrayList<LineItemBean>();
		for (LineItemBean lineItemBean : lineItems)
		{
			if (inventoryType.equals(lineItemBean.getInventory().getInventoryType()))
			{
				results.add(lineItemBean);
			}
		}
		return results;
	}
}
