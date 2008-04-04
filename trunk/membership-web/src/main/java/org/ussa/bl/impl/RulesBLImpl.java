package org.ussa.bl.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Iterator;
import java.math.BigDecimal;

import org.ussa.bl.RulesBL;
import org.ussa.dao.ParameterTableDao;
import org.ussa.model.ParameterTable;
import org.ussa.model.Inventory;
import org.ussa.beans.CartBean;
import org.ussa.beans.AccountBean;

public class RulesBLImpl implements RulesBL
{
	private ParameterTableDao parameterTableDao;

	public void setParameterTableDao(ParameterTableDao parameterTableDao)
	{
		this.parameterTableDao = parameterTableDao;
	}

	public String getCurrentRenewSeason()
	{
		return String.valueOf(calculateCurrentRenewSeason());
	}

	public String getLastSeason()
	{
		return String.valueOf(calculateCurrentRenewSeason() - 1);
	}

	public Integer calculateCurrentRenewSeason()
	{
		ParameterTable parameter = parameterTableDao.get(ParameterTable.LAST_DAY_RENEW_SEASON);

		SimpleDateFormat format = new SimpleDateFormat("MM-dd");
		Calendar currentTime = Calendar.getInstance();

		int season;
		try
		{
			Calendar lastDayRenewSeason = (Calendar) currentTime.clone();
			lastDayRenewSeason.setTime(format.parse(parameter.getParameterData()));
			lastDayRenewSeason.set(Calendar.MILLISECOND, 0);
			lastDayRenewSeason.set(Calendar.SECOND, 0);
			lastDayRenewSeason.set(Calendar.HOUR_OF_DAY, 0);
			lastDayRenewSeason.set(Calendar.YEAR, currentTime.get(Calendar.YEAR));
			lastDayRenewSeason.add(Calendar.DATE, 1);
			if (currentTime.before(lastDayRenewSeason))
			{
				season = currentTime.get(Calendar.YEAR);
			}
			else
			{
				season = currentTime.get(Calendar.YEAR) + 1;
			}
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			season = currentTime.get(Calendar.YEAR) + 1;
		}

		return season;
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
			int currentRenewSeason = calculateCurrentRenewSeason();

			return currentRenewSeason - (birthDateYear + 1);
		}

		return 0;
	}

	public BigDecimal calculateDiscount(AccountBean accountBean, Inventory inventory)
	{
		CartBean cart = accountBean.getCartBean();
		BigDecimal discount = null;

		// If you purchase more than one membership then subsequent ones should be discounted $25
		if(Inventory.INVENTORY_TYPE_MEMBERSHIP.equals(inventory.getInventoryType()))
		{
			if(cart.getLineItems(Inventory.INVENTORY_TYPE_MEMBERSHIP).size() > 0)
			{
				discount = new BigDecimal(25);
			}
		}
		return discount;
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
		return false;
	}
}
