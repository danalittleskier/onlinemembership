package org.ussa.bl.impl;

import org.apache.commons.lang.StringUtils;
import org.ussa.beans.AccountBean;
import org.ussa.beans.CartBean;
import org.ussa.beans.LineItemBean;
import org.ussa.bl.DateBL;
import org.ussa.bl.RuleAssociations;
import org.ussa.bl.RulesBL;
import org.ussa.dao.ClubDao;
import org.ussa.dao.DivisionAffiliationDao;
import org.ussa.dao.DivisionDao;
import org.ussa.dao.InventoryDao;
import org.ussa.dao.MemberSeasonDao;
import org.ussa.dao.MemberTransactionDao;
import org.ussa.dao.RenewRuleInvDao;
import org.ussa.model.Address;
import org.ussa.model.Club;
import org.ussa.model.Division;
import org.ussa.model.DivisionAffiliation;
import org.ussa.model.Inventory;
import org.ussa.model.Member;
import org.ussa.model.MemberSeason;
import org.ussa.model.MemberTransaction;
import org.ussa.model.State;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RulesBLImpl implements RulesBL
{
	private InventoryDao inventoryDao;
	private DateBL dateBL;
	private RenewRuleInvDao renewRuleInvDao;
	private MemberTransactionDao memberTransactionDao;
	private MemberSeasonDao memberSeasonDao;
	private ClubDao clubDao;
	private DivisionDao divisionDao;
	private DivisionAffiliationDao divisionAffiliationDao;

	public void setInventoryDao(InventoryDao inventoryDao)
	{
		this.inventoryDao = inventoryDao;
	}

	public void setDateBL(DateBL dateBL)
	{
		this.dateBL = dateBL;
	}

	public void setRenewRuleInvDao(RenewRuleInvDao renewRuleInvDao)
	{
		this.renewRuleInvDao = renewRuleInvDao;
	}

	public void setMemberTransactionDao(MemberTransactionDao memberTransactionDao)
	{
		this.memberTransactionDao = memberTransactionDao;
	}

	public void setMemberSeasonDao(MemberSeasonDao memberSeasonDao)
	{
		this.memberSeasonDao = memberSeasonDao;
	}

	public void setClubDao(ClubDao clubDao)
	{
		this.clubDao = clubDao;
	}

	public void setDivisionDao(DivisionDao divisionDao)
	{
		this.divisionDao = divisionDao;
	}

	public void setDivisionAffiliationDao(DivisionAffiliationDao divisionAffiliationDao)
	{
		this.divisionAffiliationDao = divisionAffiliationDao;
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
				if(cart.contains(inventory.getId()) || inventoryIsRestricted(accountBean, inventory, age))
				{
					iterator.remove();
				}
			}
		}

		return memberships;
	}

	private boolean inventoryIsRestricted(AccountBean accountBean, Inventory inventory, Integer age)
	{
		CartBean cartBean = accountBean.getCartBean();
		String invId = inventory.getId();

		// For memberships that you want hidden from the users, add them to RuleAssociations.restrictedMemberships.
		if(RuleAssociations.restrictedMemberships.contains(invId))
		{
			return true;
		}

		if(age != null 
				&& ((inventory.getAgeFrom() != null && age < inventory.getAgeFrom())
						|| (inventory.getAgeTo() != null && age > inventory.getAgeTo())))
		{
			return true;
		}

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
		if(invId.equals(Inventory.INV_ID_FREESTYLE_ROOKIE)
				&& memberTransactionDao.hasHeldIventory(accountBean.getMember().getId(), Inventory.INV_ID_FREESTYLE_COMPETITOR))
		{
			return true;
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

		Integer age = getAgeForCurrentRenewSeason(accountBean.getMember().getBirthDate());

		List<Inventory> fisItems = new ArrayList<Inventory>();
		for (String invId : fisInvIds)
		{
			Inventory fisItem = inventoryDao.get(invId);
			Inventory lateFisItem = getLateFis(fisItem);
			if(lateFisItem != null)
			{
				fisItem = lateFisItem;
			}
			if(!inventoryIsRestricted(accountBean, fisItem, age))
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
		if(isCountryUs(member.getCountry()))
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

			Integer age = getAgeForCurrentRenewSeason(accountBean.getMember().getBirthDate());

			for (String magazineInvId : magazineInvIds)
			{
				Inventory magazineInventory = inventoryDao.get(magazineInvId);
				if(!inventoryIsRestricted(accountBean, magazineInventory, age))
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

		if(!inventoryIsRestricted(accountBean, inventory, null))
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

	//TODO: magazines need to be removed from cart if you remove your memberships.
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
        cart.removeLineItems(Inventory.INVENTORY_TYPE_DONATION);
        if(accountBean.getContributionAmount() != null && StringUtils.isNotEmpty(accountBean.getContributionSportId()))
        {
            List<Inventory> donationInventory = inventoryDao.getIventoryByTypeAndSportCode(Inventory.INVENTORY_TYPE_DONATION, accountBean.getContributionSportId());
            cart.addItem(donationInventory.get(0), new BigDecimal(accountBean.getContributionAmount()));
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

		// RE-CALCULATE AND RE-ADD DIVISION AND STATE DUES
		if(!cart.containsAny(RuleAssociations.disabledMemberships))
		{
			Integer age = getAgeForCurrentRenewSeason(member.getBirthDate());
			String stateCode = member.getStateCode();
			if(State.STATE_CODE_MAINE.equals(stateCode))
			{
				List<Inventory> items = new ArrayList<Inventory>();
				if(age <= 10 && cart.contains(Inventory.INV_ID_ALPINE_YOUTH))
				{
					items.add(inventoryDao.get(Inventory.INV_ID_MARA_DUES_AY_10_UNDER));
				}
				if(age >= 11 && age <= 12 && cart.contains(Inventory.INV_ID_ALPINE_YOUTH)
						|| cart.contains(Inventory.INV_ID_ALPINE_STUDENT)
						|| cart.contains(Inventory.INV_ID_ALPINE_COMPETITOR)
						|| cart.contains(Inventory.INV_ID_ALPINE_COACH))
				{
					items.add(inventoryDao.get(Inventory.INV_ID_MARA_DUES_AY_11_12_AS_AC_ACO));
				}
				cart.addItem(getMostExpensive(items));
			}
			else if(State.STATE_CODE_NEW_JERSEY.equals(stateCode))
			{
				List<Inventory> items = new ArrayList<Inventory>();
				if(cart.contains(Inventory.INV_ID_ALPINE_YOUTH)
						|| cart.contains(Inventory.INV_ID_ALPINE_COMPETITOR))
				{
					items.add(inventoryDao.get(Inventory.INV_ID_NJSRA_DUES_AY_AC));
				}
				if(cart.contains(Inventory.INV_ID_ALPINE_COACH)
						|| cart.contains(Inventory.INV_ID_ALPINE_OFFICIAL))
				{
					items.add(inventoryDao.get(Inventory.INV_ID_NJSRA_DUES_ACO_AO));
				}
				cart.addItem(getMostExpensive(items));
			}
			else
			{
				String divisionCode = member.getDivision().getDivisionCode();
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

				for (Inventory due : applicableDues)
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
			// DIVISION AND STATE LATE FEES
			String stateCode = member.getStateCode();
			if(State.STATE_CODE_MAINE.equals(stateCode))
			{
				cart.addItem(inventoryDao.get(Inventory.INV_ID_MARA_LATE_FEE));
			}
			else if(State.STATE_CODE_NEW_JERSEY.equals(stateCode))
			{
				cart.addItem(inventoryDao.get(Inventory.INV_ID_NJSRA_LATE_FEE));
			}
			else if(cart.contains(Inventory.INV_ID_ALPINE_COMPETITOR)) // There are currently only division late fees for alpine competitors
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

	public boolean needsBackgroundCheck(Long ussaId)
	{
		String currentSeason = dateBL.getCurrentRenewSeason();
		List<MemberTransaction> memberTransactions = memberTransactionDao.getMemberTransactionsForSeason(ussaId, currentSeason);
		Set<String> items = new HashSet<String>();
		for (MemberTransaction memberTransaction : memberTransactions)
		{
			items.add(memberTransaction.getInventory().getId());
		}
		return needsBackgroundCheck(ussaId, items);
	}

	private boolean needsBackgroundCheck(Long ussaId, Set<String> items)
	{
		if(containsAny(items, RuleAssociations.coachMemberships) || containsAny(items, RuleAssociations.officialMemberships))
		{
			MemberSeason memberSeason = memberSeasonDao.getMostRecentBackgroundCheck(ussaId);
			int bacgroundCheckSeason = Integer.parseInt(memberSeason.getMemberSeasonPk().getSeason());
			if(dateBL.calculateCurrentRenewSeason() <= (bacgroundCheckSeason + 3))
			{
				return false;
			}
		}
		return true;
	}

	private boolean containsAny(Set<String> set, Collection<String> items)
	{
		for (String s : items)
		{
			if (set.contains(s))
			{
				return true;
			}
		}
		return false;
	}

	public boolean certificateIsRestricted(Long ussaId)
	{
		String currentSeason = dateBL.getCurrentRenewSeason();
		List<MemberTransaction> memberTransactions = memberTransactionDao.getMemberTransactionsForSeason(ussaId, currentSeason);
		Set<String> items = new HashSet<String>();
		for (MemberTransaction memberTransaction : memberTransactions)
		{
			items.add(memberTransaction.getInventory().getId());
		}

		if(needsBackgroundCheck(ussaId, items))
		{
			Set<String> coachesAndOfficials = new HashSet<String>();
			coachesAndOfficials.addAll(RuleAssociations.coachMemberships);
			coachesAndOfficials.addAll(RuleAssociations.officialMemberships);
			if(containsOnly(items, coachesAndOfficials))
			{
				return true;
			}
		}
		return false;
	}

	private boolean containsOnly(Set<String> set, Collection<String> items)
	{
		for (String s : items)
		{
			if (!set.contains(s))
			{
				return false;
			}
		}
		return true;
	}

	public boolean isCountryUs(String country)
	{
		if(StringUtils.isNotBlank(country))
		{
			country = country.toUpperCase();
			if(
					"US".equals(country)
					|| "USA".equals(country)
					|| "UNITED STATES".equals(country)
					|| "UNITED STATES OF AMERICA".equals(country))
			{
				return true;
			}
		}
		return false;
	}

	public Division determineDivision(String nationCode, String stateCode, Long clubId, String zipCode)
	{
		if(!isCountryUs(nationCode))
		{
			return divisionDao.get(Division.DIVISION_FOREIGN);
		}

		if(clubId != null && clubId != 0)
		{
			Club club = clubDao.get(clubId);
			return club.getDivision();
		}

		if(StringUtils.isNotBlank(stateCode))
		{
			List<DivisionAffiliation> affiliations = divisionAffiliationDao.getDivisionAffiliations(stateCode);
			if(affiliations.size() == 1)
			{
				return affiliations.get(0).getDivision();
			}
		}

		if(StringUtils.isNotBlank(stateCode) && StringUtils.isNotBlank(zipCode))
		{
			if(zipCode.length() > 3)
			{
				zipCode = zipCode.substring(0,3);
			}
			List<DivisionAffiliation> affiliations = divisionAffiliationDao.getDivisionAffiliations(stateCode, zipCode);
			if(affiliations.size() == 1)
			{
				return affiliations.get(0).getDivision();
			}
		}

		return null;
	}
}
