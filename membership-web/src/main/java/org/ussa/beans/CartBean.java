package org.ussa.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.text.DecimalFormat;

import org.ussa.model.Inventory;


public class CartBean
{
	List<LineItemBean> lineItems = new ArrayList<LineItemBean>();

	public void addItem(Inventory inventory)
	{
		addItem(inventory, null, null, null);
	}
	public void addItem(Inventory inventory, BigDecimal cost)
	{
		addItem(inventory, cost, null, null);
	}
	public void addItem(Inventory inventory, Integer qty)
	{
		addItem(inventory, null, null, qty);
	}
	public void addItem(Inventory inventory, BigDecimal cost, BigDecimal discount, Integer qty)
	{
		LineItemBean lineItem = new LineItemBean();
		lineItem.setInventory(inventory);
		lineItem.setDescription(inventory.getRenewDescription());
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

		lineItem.setDiscount(discount);

		if(qty != null)
		{
			lineItem.setQty(qty);
		}
		else
		{
			lineItem.setQty(1);
		}

		LineItemBean existingLineItem = getLineItem(inventory.getId());
		if(existingLineItem != null && existingLineItem.getDiscountedAmount().equals(lineItem.getDiscountedAmount()))
		{
			Integer existingQty = existingLineItem.getQty();
			existingQty += lineItem.getQty();
			existingLineItem.setQty(existingQty);
		}
		else
		{
			lineItems.add(lineItem);
		}
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

	public void removeLineItems(String inventoryType)
	{
		for (Iterator<LineItemBean> iterator = lineItems.iterator(); iterator.hasNext();)
		{
			LineItemBean lineItemBean = iterator.next();
			if(inventoryType.equals(lineItemBean.getInventory().getInventoryType()))
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

	public boolean containsSport(String sportCode)
	{
		for (LineItemBean lineItemBean : lineItems)
		{
			if (sportCode.equals(lineItemBean.getInventory().getSportCode()))
			{
				return true;
			}
		}
		return false;
	}

	public boolean contains(String inventoryId)
	{
		return getLineItem(inventoryId) != null;
	}

	public boolean containsAny(Collection<String> inventoryIds)
	{
		for (String inventoryId : inventoryIds)
		{
			if (getLineItem(inventoryId) != null)
			{
				return true;
			}
		}
		return false;
	}

	public String getTotalFormatted()
	{
		DecimalFormat format = new DecimalFormat("###,##0.00");
		return "$"+format.format(getTotal());
	}
	public BigDecimal getTotal()
	{
		BigDecimal total = BigDecimal.ZERO;
		for (LineItemBean lineItemBean : lineItems)
		{
			total = total.add(lineItemBean.getDiscountedAmount().multiply(new BigDecimal(lineItemBean.getQty())));
		}
		return total;
	}

	public List<LineItemBean> getLineItems()
	{
		return lineItems;
	}

	public void setLineItems(List<LineItemBean> lineItems)
	{
		this.lineItems = lineItems;
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

	public List<LineItemBean> getMemberships()
	{
		return getLineItems(Inventory.INVENTORY_TYPE_MEMBERSHIP);
	}

	public List<LineItemBean> getFis()
	{
		return getLineItems(Inventory.INVENTORY_TYPE_FIS);
	}

	public List<LineItemBean> getDues()
	{
		List<LineItemBean> dues = getLineItems(Inventory.INVENTORY_TYPE_DIVISION_DUES);
		dues.addAll(getLineItems(Inventory.INVENTORY_TYPE_STATE_DUES));
		return dues;
	}

	public List<LineItemBean> getMagazines()
	{
		return getLineItems(Inventory.INVENTORY_TYPE_MAGAZINE);
	}

	public List<LineItemBean> getBonusPacks()
	{
		List<LineItemBean> dues = getLineItems(Inventory.INVENTORY_TYPE_BONUS_PACK);
		dues.addAll(getLineItems(Inventory.INVENTORY_TYPE_GOLD_PACK));
		return dues;
	}

	private static final Set<String> catagories;
	static
	{
		catagories = new HashSet<String>();
		catagories.add(Inventory.INVENTORY_TYPE_MEMBERSHIP);
		catagories.add(Inventory.INVENTORY_TYPE_FIS);
		catagories.add(Inventory.INVENTORY_TYPE_DIVISION_DUES);
		catagories.add(Inventory.INVENTORY_TYPE_STATE_DUES);
		catagories.add(Inventory.INVENTORY_TYPE_MAGAZINE);
		catagories.add(Inventory.INVENTORY_TYPE_BONUS_PACK);
		catagories.add(Inventory.INVENTORY_TYPE_GOLD_PACK);
	}

	public List<LineItemBean> getOther()
	{
		List<LineItemBean> lineItems = new ArrayList<LineItemBean>();
		for (LineItemBean lineItem : getLineItems())
		{
			String type = lineItem.getInventory().getInventoryType();
			if(! catagories.contains(type))
			{
				lineItems.add(lineItem);
			}
		}
		return lineItems;
	}
}
