package org.ussa.bl.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.ussa.beans.AccountBean;
import org.ussa.beans.CartBean;
import org.ussa.beans.LineItemBean;
import org.ussa.bl.DateBL;
import org.ussa.bl.RuleAssociations;
import org.ussa.bl.RulesBL;
import org.ussa.dao.InventoryDao;
import org.ussa.dao.ParameterTableDao;
import org.ussa.dao.RenewRuleInvDao;
import org.ussa.model.Address;
import org.ussa.model.Inventory;
import org.ussa.model.Member;
import org.ussa.model.ParameterTable;
import org.ussa.model.State;

public class RulesBLImpl implements RulesBL
{
	private InventoryDao inventoryDao;
	private ParameterTableDao parameterTableDao;
	private DateBL dateBL;
	private RenewRuleInvDao renewRuleInvDao;

	public void setInventoryDao(InventoryDao inventoryDao)
	{
		this.inventoryDao = inventoryDao;
	}

	public void setParameterTableDao(ParameterTableDao parameterTableDao)
	{
		this.parameterTableDao = parameterTableDao;
	}

	public void setDateBL(DateBL dateBL)
	{
		this.dateBL = dateBL;
	}

	public void setRenewRuleInvDao(RenewRuleInvDao renewRuleInvDao)
	{
		this.renewRuleInvDao = renewRuleInvDao;
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

	public void setParentInfoRequired(AccountBean accountBean)
	{
		Date birthdate = accountBean.getMember().getBirthDate();
		if(birthdate != null)
		{
			Integer currentSeasonAge = getAgeForCurrentRenewSeason(accountBean.getMember().getBirthDate());
			if(currentSeasonAge < 18)
			{
				accountBean.setParentInfoRequired(true);
				return;
			}
		}

		accountBean.setParentInfoRequired(false);
	}

	public List<Inventory> findApplicableSportMemberships(AccountBean accountBean)
	{
		CartBean cart = accountBean.getCartBean();
		List<Inventory> memberships = new ArrayList<Inventory>();
		if (StringUtils.isNotBlank(accountBean.getSportId()))
		{
			int age = getAgeForCurrentRenewSeason(accountBean.getMember().getBirthDate());
			memberships = inventoryDao.getAllMembershipsByCriteria(age, accountBean.getSportId());

			// filter memberships
			for (Iterator<Inventory> iterator = memberships.iterator(); iterator.hasNext();)
			{
				Inventory inventory = iterator.next();
				if(cart.contains(inventory.getId()) || inventoryIsRestricted(accountBean, inventory))
				{
					iterator.remove();
				}
			}
		}

		return memberships;
	}

	private boolean inventoryIsRestricted(AccountBean accountBean, Inventory inventory)
	{
		CartBean cartBean = new CartBean();
		String invId = inventory.getId();

		// If a coach membership is already selected, user may not add an official membership (it’s already included).
		String coachInvId = RuleAssociations.coachesByOfficial.get(invId);
		if(coachInvId != null && cartBean.contains(coachInvId))
		{
			return true;
		}

		// If a competitor membership is already selected, user may not add a youth.
		String competitorInvId = RuleAssociations.competitorByYouth.get(invId);
		if(competitorInvId != null && cartBean.contains(competitorInvId))
		{
			return true;
		}

		// If a youth membership is already selected, user may not add a competitor.
		String youthInvId = RuleAssociations.youthByCompetitor.get(invId);
		if(youthInvId != null && cartBean.contains(youthInvId))
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

	public List<Inventory> findApplicableFisItems(AccountBean accountBean)
	{
		CartBean cart = accountBean.getCartBean();
		List<LineItemBean> lineItems = cart.getLineItems();
		List<String> fisInvIds = new ArrayList<String>();
		for (LineItemBean lineItem : lineItems)
		{
			String[] fises = RuleAssociations.fisByMembership.get(lineItem.getInventory().getId());
			if(fises != null)
			{
				for (String fis : fises)
				{
					if(!fisInvIds.contains(fis))
					{
						fisInvIds.add(fis);
					}
				}
			}
		}

		List<Inventory> fisItems = new ArrayList<Inventory>();
		for (String invId : fisInvIds)
		{
			Inventory fisItem = inventoryDao.get(invId);
			Inventory lateFisItem = getLateFis(fisItem);
			if(lateFisItem != null)
			{
				fisItem = lateFisItem;
			}
			if(!inventoryIsRestricted(accountBean, fisItem))
			{
				fisItems.add(fisItem);
			}
		}

		return fisItems;
	}

	public List<Inventory> findApplicableMagazineItems(AccountBean accountBean)
	{
		Address member = accountBean.getAddress();
		CartBean cart = accountBean.getCartBean();
		List<Inventory> magazineItems = new ArrayList<Inventory>();

		// only give magazines to people with addresses in the US
		// TODO: Do a better USA check than this
		if(member.getCountry().equals("USA"))
		{
			List<String> magazineInvIds = new ArrayList<String>();

			if(cart.getLineItems(Inventory.INVENTORY_TYPE_MEMBERSHIP).size() > 0)
			{
				if(hasOnlyYouthMemberships(accountBean))
				{
					magazineInvIds.add(Inventory.INV_ID_SKI_RACING_MAGAZINE_LIMITED_ISSUES);
				}
				else
				{
					magazineInvIds.add(Inventory.INV_ID_SKI_RACING_MAGAZINE);
					magazineInvIds.add(Inventory.INV_ID_SNOWBOARD_MAGAZINE);
					magazineInvIds.add(Inventory.INV_ID_SKI_TRAX_MAGAZINE);
				}
			}

			for (String magazineInvId : magazineInvIds)
			{
				Inventory magazineInventory = inventoryDao.get(magazineInvId);
				if(!inventoryIsRestricted(accountBean, magazineInventory))
				{
					magazineItems.add(magazineInventory);
				}
			}
		}

		return magazineItems;
	}

	private boolean hasOnlyYouthMemberships(AccountBean accountBean)
	{
		CartBean cart = accountBean.getCartBean();
		List<LineItemBean> membershipItems = cart.getLineItems(Inventory.INVENTORY_TYPE_MEMBERSHIP);
		if(membershipItems.size() > 0)
		{
			for (LineItemBean lineItem : membershipItems)
			{
				if(!RuleAssociations.youthMemberships.contains(lineItem.getInventory().getId()))
				{
					return false;
				}
			}
		}
		else
		{
			return false;
		}

		return true;
	}

	private boolean hasOnlyOfficialMemberships(AccountBean accountBean)
	{
		CartBean cart = accountBean.getCartBean();
		List<LineItemBean> membershipItems = cart.getLineItems(Inventory.INVENTORY_TYPE_MEMBERSHIP);
		if(membershipItems.size() > 0)
		{
			for (LineItemBean lineItem : membershipItems)
			{
				if(!RuleAssociations.officialMemberships.contains(lineItem.getInventory().getId()))
				{
					return false;
				}
			}
		}
		else
		{
			return false;
		}

		return true;
	}

	public void addMembershipToCart(AccountBean accountBean, Inventory inventory)
	{
		CartBean cartBean = accountBean.getCartBean();

		if(!inventoryIsRestricted(accountBean, inventory))
		{
			BigDecimal discount = calculateMembershipDiscount(accountBean, inventory);

			if(discount == null)
			{
				cartBean.addItem(inventory);
			}
			else
			{
				cartBean.addItem(inventory, inventory.getAmount(), discount, 1);
			}

			// If adding a coach and a corresponding official is already in the cart then the coach replaces the official
			String officialInvId = RuleAssociations.officialsByCoach.get(inventory.getId());
			if(officialInvId != null && cartBean.contains(officialInvId))
			{
				cartBean.removeLineItem(officialInvId);
			}

			addRemoveDivisionDuesAndLateFees(accountBean);
		}
	}

	private BigDecimal calculateMembershipDiscount(AccountBean accountBean, Inventory inventory)
	{
		CartBean cart = accountBean.getCartBean();
		BigDecimal discount = null;

		// If you purchase more than one membership then subsequent ones should be discounted $35 or $25
		if(Inventory.INVENTORY_TYPE_MEMBERSHIP.equals(inventory.getInventoryType())
				&& cart.getLineItems(Inventory.INVENTORY_TYPE_MEMBERSHIP).size() > 0)
		{
			// If you are adding or have a membership from the 25 dollar group then the discount is 25.
			if(RuleAssociations.twentyFiveDollarDiscountGroup.contains(inventory.getId())
					|| cart.containsAny(RuleAssociations.twentyFiveDollarDiscountGroup))
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

	private void revokeMembershipDiscountIfNeeded(AccountBean accountBean)
	{
		List<LineItemBean> lineItems = accountBean.getCartBean().getLineItems(Inventory.INVENTORY_TYPE_MEMBERSHIP);
		if(lineItems.size() > 0)
		{
			// if there is more than on membership and they aren't paying full price for any of them.
			int indexOfMinDiscount = -1;
			BigDecimal minDiscount = null;
			for (int i = 0; i < lineItems.size(); i++)
			{
				LineItemBean lineItemBean = lineItems.get(i);
				BigDecimal discount = lineItemBean.getDiscount();
				if(discount == null)
				{
					// the are already paying full price for one of their memberships so do nothing
					return;
				}
				else if(minDiscount == null || discount.floatValue() < minDiscount.floatValue())
				{
					minDiscount = discount;
					indexOfMinDiscount = i;
				}
			}
			// remove the smallest discounted value from one of the memberships
			if(indexOfMinDiscount >= 0)
			{
				lineItems.get(indexOfMinDiscount).setDiscount(null);
			}
		}
	}

	public void removeItemFromCart(AccountBean accountBean, String invId)
	{
		CartBean cart = accountBean.getCartBean();

		LineItemBean lineItem = cart.getLineItem(invId);
		if(lineItem != null)
		{
			if(Inventory.INVENTORY_TYPE_DONATION.equals(lineItem.getInventory().getInventoryType()))
			{
				accountBean.setContributionAmount(null);
			}
			cart.removeLineItem(invId);
		}

		String[] fises = RuleAssociations.fisByMembership.get(invId);
		if(fises != null)
		{
			for (String fis : fises)
			{
				cart.removeLineItem(fis);
			}
		}

		resetFisOptions(accountBean);
		resetMagazineOption(accountBean);

		revokeMembershipDiscountIfNeeded(accountBean);
		addRemoveDivisionDuesAndLateFees(accountBean);
	}

	public void handleFisOptions(AccountBean accountBean)
	{
		// convert String array to Set
		Set<String> fisOptions = new HashSet<String>();
		if(accountBean.getFisOptions() != null)
		{
			for (String fisOption : accountBean.getFisOptions())
			{
				fisOptions.add(fisOption);
			}
		}

		// remove any fis items from the cart that aren't checked
		CartBean cart = accountBean.getCartBean();
		List<LineItemBean> fisItems = cart.getLineItems(Inventory.INVENTORY_TYPE_FIS);
		for (LineItemBean fisItem : fisItems)
		{
			if(!fisOptions.contains(fisItem.getInventory().getId()))
			{
				cart.removeLineItem(fisItem.getInventory().getId());
			}
		}

		// add any fis items that are checked that aren't in the cart
		if(accountBean.getFisOptions() != null)
		{
			for(String fisOption : accountBean.getFisOptions())
			{
				if(!cart.contains(fisOption))
				{
					Inventory fisItem = inventoryDao.get(fisOption);
					addMembershipToCart(accountBean, fisItem);
				}
			}
		}
	}

	private void resetFisOptions(AccountBean accountBean)
	{
		CartBean cart = accountBean.getCartBean();
		List<LineItemBean> lineItems = cart.getLineItems(Inventory.INVENTORY_TYPE_FIS);
		List<String> fisInvIds = new ArrayList<String>();
		for (LineItemBean lineItem : lineItems)
		{
			fisInvIds.add(lineItem.getInventory().getId());
		}

		accountBean.setFisOptions(fisInvIds.toArray(new String[fisInvIds.size()]));
	}

	public void handleMagazineOption(AccountBean accountBean)
	{
		CartBean cart = accountBean.getCartBean();

		String magazineOption = accountBean.getMagazineOption();

		List<LineItemBean> magazines = accountBean.getCartBean().getLineItems(Inventory.INVENTORY_TYPE_MAGAZINE);
		if(magazines.size() > 0 && !magazines.get(0).getInventory().getId().equals(magazineOption))
		{
			cart.removeLineItem(magazines.get(0).getInventory().getId());
			if(StringUtils.isNotBlank(magazineOption))
			{
				cart.addItem(inventoryDao.get(magazineOption));
			}
		}
		else if(magazines.size() == 0)
		{
			if(StringUtils.isNotBlank(magazineOption))
			{
				cart.addItem(inventoryDao.get(magazineOption));
			}
		}
	}

	private void resetMagazineOption(AccountBean accountBean)
	{
		CartBean cart = accountBean.getCartBean();
		List<LineItemBean> lineItems = cart.getLineItems(Inventory.INVENTORY_TYPE_MAGAZINE);
		if(lineItems.size() > 0)
		{
			accountBean.setMagazineOption(lineItems.get(0).getInventory().getId());
		}
		else
		{
			accountBean.setMagazineOption("");
		}
	}

	public void handleContribution(AccountBean accountBean)
	{
		CartBean cart = accountBean.getCartBean();
		List<LineItemBean> donations = cart.getLineItems(Inventory.INVENTORY_TYPE_DONATION);
		LineItemBean donation = null;
		if(donations.size() > 0)
		{
			donation = donations.get(0);
			if(accountBean.getContributionAmount() != null)
			{
				donation.setAmount(new BigDecimal(accountBean.getContributionAmount()));
			}
			else
			{
				cart.removeLineItem(donation.getInventory().getId());
			}
		}
		else
		{
			if(accountBean.getContributionAmount() != null)
			{
				// TODO: find the primary membership and donate to that club
				List<Inventory> donationInventory = inventoryDao.getIventoryByType(Inventory.INVENTORY_TYPE_DONATION);
				cart.addItem(donationInventory.get(0), new BigDecimal(accountBean.getContributionAmount()));
			}
		}
	}

	private Inventory getLateFis(Inventory fisItem)
	{
		String invId = fisItem.getId();
		String lateInvId = null;
		Date now = new Date();
		if(Inventory.INV_ID_ALPINE_FIS.equals(invId) && now.after(dateBL.getAlpineFisLateDate()))
		{
			lateInvId = Inventory.INV_ID_LATE_ALPINE_FIS;
		}
		else if(Inventory.INV_ID_ALPINE_SKIING_DISABLED_LICENSE_FIS.equals(invId) && now.after(dateBL.getAlpineFisLateDate()))
		{
			lateInvId = Inventory.INV_ID_LATE_ALPINE_SKIING_DISABLED_LICENSE_FIS;
		}
		else if(Inventory.INV_ID_CROSS_COUNTRY_FIS.equals(invId) && now.after(dateBL.getCrossCountryFisLateDate()))
		{
			lateInvId = Inventory.INV_ID_LATE_CROSS_COUNTRY_FIS;
		}
		else if(Inventory.INV_ID_FREESTYLE_FIS.equals(invId) && now.after(dateBL.getFreestyleFisLateDate()))
		{
			lateInvId = Inventory.INV_ID_LATE_FREESTYLE_FIS;
		}


		if(lateInvId != null)
		{
			return inventoryDao.get(lateInvId);
		}
		else
		{
			return null;
		}
	}

	private void addRemoveDivisionDuesAndLateFees(AccountBean accountBean)
	{
		CartBean cart = accountBean.getCartBean();
		Member member = accountBean.getMember();

		// REMOVE DIVISION DUES
		cart.removeLineItems(Inventory.INVENTORY_TYPE_DIVISION_DUES);
		cart.removeLineItems(Inventory.INVENTORY_TYPE_STATE_DUES);

		// RE-CALCULATE AND RE-ADD DIVISION DUES
		if(!cart.containsAny(RuleAssociations.disabledMemberships))
		{
			String stateCode = member.getStateCode();
			if(State.STATE_CODE_MAINE.equals(stateCode) || State.STATE_CODE_NEW_JERSEY.equals(stateCode))
			{
				// TODO: do the state dues
			}
			else
			{
				String divisionCode = member.getDivision().getDivisionCode();
				Integer age = getAgeForCurrentRenewSeason(member.getBirthDate());
				List<LineItemBean> membershipLineItems = cart.getLineItems(Inventory.INVENTORY_TYPE_MEMBERSHIP);
				List<String> membershipIds = new ArrayList<String>();
				for (LineItemBean lineItem : membershipLineItems)
				{
					membershipIds.add(lineItem.getInventory().getId());
				}

				List<Inventory> allDues = renewRuleInvDao.getDivisionDues(divisionCode, age, membershipIds);
				List<Inventory> applicableDues = new ArrayList<Inventory>();

				if(RuleAssociations.onlyOneDivisionDuePerSport.contains(member.getDivision().getDivisionCode()))
				{
					Set<String> applicableSportCodes = getApplicableSportCodes(allDues);
					for (String sportCode : applicableSportCodes)
					{
						applicableDues.add(getMostExpensive(allDues, sportCode));
					}
				}
				else if(RuleAssociations.onlyOneDivisionDue.contains(member.getDivision().getDivisionCode()))
				{
					applicableDues.add(getMostExpensive(allDues));
				}
				else
				{
					applicableDues = allDues;
				}

				for (Inventory due : allDues)
				{
					cart.addItem(due);
				}
			}

		}

		// LATE FEES
		Date now = new Date();
		Date lateRenewDate = dateBL.getLateRenewDate();

		if(now.after(lateRenewDate))
		{
			// DIVISION LATE FEES
			if(cart.contains(Inventory.INV_ID_ALPINE_COMPETITOR)) // There are currently only division late fees for alpine competitors
			{
				String divisionCode = member.getDivision().getDivisionCode();
				String invId = RuleAssociations.divisionLateFeesAplineCompetitor.get(divisionCode);
				if(invId != null)
				{
					Inventory divisionLateFee = inventoryDao.get(invId);
					cart.addItem(divisionLateFee);
				}
			}

			// USSA LATE FEES
			if((member.getId() == null || member.getId() == 0)
				|| cart.getLineItems(Inventory.INVENTORY_TYPE_MEMBERSHIP).size() == 0
				|| hasOnlyYouthMemberships(accountBean)
				|| hasOnlyOfficialMemberships(accountBean))
			{
				cart.removeLineItem(Inventory.INV_ID_MEMBER_LATE_FEE);
			}
			else if(!cart.contains(Inventory.INV_ID_MEMBER_LATE_FEE))
			{
				Inventory memberLateFee = inventoryDao.get(Inventory.INV_ID_MEMBER_LATE_FEE);
				cart.addItem(memberLateFee);
			}
		}
	}

	private Inventory getMostExpensive(Collection<Inventory> items)
	{
		Inventory mostExpensive = null;
		for (Inventory inventory : items)
		{
			if (mostExpensive == null || inventory.getAmount().compareTo(mostExpensive.getAmount()) > 1)
			{
				mostExpensive = inventory;
			}
		}

		return mostExpensive;
	}

	private Inventory getMostExpensive(Collection<Inventory> items, String sportCode)
	{
		Inventory mostExpensive = null;
		List<Inventory> result = new ArrayList<Inventory>();
		for (Inventory inventory : items)
		{
			if (Inventory.SPORT_CODE_ALL.equals(inventory.getSportCode())
				|| sportCode.equals(inventory.getSportCode()))
			{
				result.add(inventory);
			}
		}

		return getMostExpensive(result);
	}

	private Set<String> getApplicableSportCodes(List<Inventory> allDues)
	{
		Set<String> sportCodes = new HashSet<String>();
		for (Inventory inventory : allDues)
		{
			sportCodes.add(inventory.getSportCode());
		}
		return sportCodes;
	}

	public boolean hasFis(AccountBean accountBean, boolean disabled)
	{
		CartBean cart = accountBean.getCartBean();
		List<LineItemBean> fis = cart.getLineItems(Inventory.INVENTORY_TYPE_FIS);
		for (LineItemBean lineItem : fis)
		{
			if(disabled && RuleAssociations.disabledFisMemberships.contains(lineItem.getInventory().getId())
				|| !disabled && !RuleAssociations.disabledFisMemberships.contains(lineItem.getInventory().getId()))
			{
				return true;
			}
		}
		return false;
	}

	public void removeFisFromCart(AccountBean accountBean, boolean disabled)
	{
		CartBean cart = accountBean.getCartBean();
		List<LineItemBean> fis = cart.getLineItems(Inventory.INVENTORY_TYPE_FIS);
		for (LineItemBean lineItem : fis)
		{
			if(disabled && RuleAssociations.disabledFisMemberships.contains(lineItem.getInventory().getId())
				|| !disabled && !RuleAssociations.disabledFisMemberships.contains(lineItem.getInventory().getId()))
			{
				cart.removeLineItem(lineItem.getInventory().getId());
			}
		}
	}

}
