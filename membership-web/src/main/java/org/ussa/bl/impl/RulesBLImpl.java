package org.ussa.bl.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ussa.beans.AccountBean;
import org.ussa.beans.CartBean;
import org.ussa.bl.DateBL;
import org.ussa.bl.RulesBL;
import org.ussa.dao.ParameterTableDao;
import org.ussa.model.Inventory;
import org.ussa.model.Member;
import org.ussa.model.ParameterTable;

public class RulesBLImpl implements RulesBL
{
	private static Map<String, String> officialsByCoach;
	private static Map<String, String> coachesByOfficial;
	private static Map<String, String> twentyFiveDollarDiscountGroup;
	static {
		officialsByCoach = new HashMap<String, String>();
		officialsByCoach.put(Inventory.INV_ID_ALPINE_COACH, Inventory.INV_ID_ALPINE_OFFICIAL);
		officialsByCoach.put(Inventory.INV_ID_SNOWBOARD_COACH, Inventory.INV_ID_SNOWBOARD_OFFICIAL);
		officialsByCoach.put(Inventory.INV_ID_FREESTYLE_COACH, Inventory.INV_ID_FREESTYLE_OFFICIAL);
		officialsByCoach.put(Inventory.INV_ID_JUMPING_COACH, Inventory.INV_ID_JUMPING_OFFICIAL);
		officialsByCoach.put(Inventory.INV_ID_CROSS_COUNTRY_COACH, Inventory.INV_ID_CROSS_COUNTRY_OFFICIAL);

		coachesByOfficial = new HashMap<String, String>();
		coachesByOfficial.put(Inventory.INV_ID_ALPINE_OFFICIAL, Inventory.INV_ID_ALPINE_COACH);
		coachesByOfficial.put(Inventory.INV_ID_SNOWBOARD_OFFICIAL, Inventory.INV_ID_SNOWBOARD_COACH);
		coachesByOfficial.put(Inventory.INV_ID_FREESTYLE_OFFICIAL, Inventory.INV_ID_FREESTYLE_COACH);
		coachesByOfficial.put(Inventory.INV_ID_JUMPING_OFFICIAL, Inventory.INV_ID_JUMPING_COACH);
		coachesByOfficial.put(Inventory.INV_ID_CROSS_COUNTRY_OFFICIAL, Inventory.INV_ID_CROSS_COUNTRY_COACH);

		twentyFiveDollarDiscountGroup = new HashMap<String, String>();
		twentyFiveDollarDiscountGroup.put(Inventory.INV_ID_ALPINE_YOUTH, "Y");
		twentyFiveDollarDiscountGroup.put(Inventory.INV_ID_FREESTYLE_YOUTH, "Y");
		twentyFiveDollarDiscountGroup.put(Inventory.INV_ID_JUMPING_YOUTH, "Y");
		twentyFiveDollarDiscountGroup.put(Inventory.INV_ID_CROSS_COUNTRY_YOUTH, "Y");
		twentyFiveDollarDiscountGroup.put(Inventory.INV_ID_FREESTYLE_ROOKIE, "Y");
		twentyFiveDollarDiscountGroup.put(Inventory.INV_ID_SNOWBOARD_COMPETITOR_REGIONAL, "Y");
	}

	private ParameterTableDao parameterTableDao;
	private DateBL dateBL;

	public void setParameterTableDao(ParameterTableDao parameterTableDao)
	{
		this.parameterTableDao = parameterTableDao;
	}

	public void setDateBL(DateBL dateBL)
	{
		this.dateBL = dateBL;
	}

	public Long getNextUssaId()
	{
		// change this to a select for update, and then increment it and commit.
		ParameterTable parameter = parameterTableDao.get(ParameterTable.USSAID);
		String ussaId = parameter.getParameterData();

		return new Long(ussaId);
	}

	public Integer getAgeForCurrentRenewSeason(Date birthDate)
	{
		if (birthDate != null)
		{
			Calendar bDate = Calendar.getInstance();
			bDate.setTime(birthDate);
			int birthDateYear = bDate.get(Calendar.YEAR);
			int currentRenewSeason = dateBL.calculateCurrentRenewSeason();

			return currentRenewSeason - (birthDateYear + 1);
		}

		return 0;
	}

	public void addMembershipToCart(AccountBean accountBean, Inventory inventory)
	{
		CartBean cartBean = new CartBean();

		cartBean.addItem(inventory);

		// If adding a coach and a corresponding official is already in the cart then the coach replaces the official
		String officialInvId = officialsByCoach.get(inventory.getId());
		if(officialInvId != null && cartBean.contains(officialInvId))
		{
			cartBean.removeLineItem(officialInvId);
		}
	}

	public void filterMemberships(AccountBean accountBean, List<Inventory> memberships)
	{
		CartBean cart = accountBean.getCartBean();
		for (Iterator<Inventory> iterator = memberships.iterator(); iterator.hasNext();)
		{
			Inventory inventory = iterator.next();
			if(cart.contains(inventory.getId()) || inventoryIsRestricted(accountBean, inventory))
			{
				iterator.remove();
			}
		}
	}

	public boolean inventoryIsRestricted(AccountBean accountBean, Inventory inventory)
	{
		CartBean cartBean = new CartBean();
		String invId = inventory.getId();

		// If a coach membership is selected, they may not add an official membership (it’s already included).
		String coachInvId = coachesByOfficial.get(invId);
		if(coachInvId != null && cartBean.contains(coachInvId))
		{
			return true;
		}

		// Alpine student cannot hold a competitor membership and vis versa
		if(invId.equals(Inventory.INV_ID_ALPINE_COMPETITOR) && cartBean.contains(Inventory.INV_ID_ALPINE_STUDENT)
				|| invId.equals(Inventory.INV_ID_ALPINE_STUDENT) && cartBean.contains(Inventory.INV_ID_ALPINE_COMPETITOR))
		{
			return true;
		}

		// Freestyle rookie cannot hold a competitor membership and vis versa
		if(invId.equals(Inventory.INV_ID_FREESTYLE_COMPETITOR) && cartBean.contains(Inventory.INV_ID_FREESTYLE_ROOKIE)
				|| invId.equals(Inventory.INV_ID_FREESTYLE_ROOKIE) && cartBean.contains(Inventory.INV_ID_FREESTYLE_COMPETITOR))
		{
			return true;
		}

		// Can't be a freestyle rookie if you have ever been a freestyle competitor
		if(invId.equals(Inventory.INV_ID_FREESTYLE_ROOKIE))
		{
			// TODO: check to see if the member has ever been a freestyle competitor
		}

		return false;
	}

	public BigDecimal calculateDiscount(AccountBean accountBean, Inventory inventory)
	{
		Member member = accountBean.getMember();
		CartBean cart = accountBean.getCartBean();
		BigDecimal discount = null;

		if("Y".equals(member.getLifetimeMember()))
		{
			// TODO: give $35 discount to lifetime members
		}

		// If you purchase more than one membership then subsequent ones should be discounted $35 or $25
		if(Inventory.INVENTORY_TYPE_MEMBERSHIP.equals(inventory.getInventoryType())
				&& cart.getLineItems(Inventory.INVENTORY_TYPE_MEMBERSHIP).size() > 0)
		{
			// If you are adding or have a membership from the 25 dollar group then the discount is 25.
			if(twentyFiveDollarDiscountGroup.containsKey(inventory.getId())
					|| cart.containsAny(twentyFiveDollarDiscountGroup.keySet()))
			{
				discount = new BigDecimal(25);
			}
			else
			{
				discount = new BigDecimal(35);
			}
		}
		return discount;
	}

	public BigDecimal calculateDivisionDuesLateFees()
	{
		return null;
	}

	public BigDecimal calculateUssaLateFees()
	{
		Calendar currentTime = Calendar.getInstance();
		if(currentTime.after(dateBL.getLateRenewDate()))
		{
			return new BigDecimal(25);
		}

		return null;
	}
}
