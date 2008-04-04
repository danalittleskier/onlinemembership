package org.ussa.spring.flows.registration;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.commons.lang.StringUtils;
import org.springframework.webflow.action.MultiAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;
import org.ussa.beans.AccountBean;
import org.ussa.beans.CartBean;
import org.ussa.beans.ExtrasBean;
import org.ussa.beans.LineItemBean;
import org.ussa.bl.RulesBL;
import org.ussa.dao.AddressDao;
import org.ussa.dao.ClubDao;
import org.ussa.dao.DivisionDao;
import org.ussa.dao.InventoryDao;
import org.ussa.dao.MemberDao;
import org.ussa.dao.MemberLegalDao;
import org.ussa.dao.NationDao;
import org.ussa.dao.RecommendedMembershipsDao;
import org.ussa.dao.StateDao;
import org.ussa.model.Address;
import org.ussa.model.AddressPk;
import org.ussa.model.Club;
import org.ussa.model.Inventory;
import org.ussa.model.Member;
import org.ussa.model.MemberLegal;
import org.ussa.model.MemberLegalPk;
import org.ussa.model.ParentInfo;
import org.ussa.model.State;


public class RegistrationAction extends MultiAction implements Serializable
{
	private MemberDao memberDao;
	private AddressDao addressDao;
	private StateDao stateDao;
	private NationDao nationDao;
	private ClubDao clubDao;
	private DivisionDao divisionDao;
	private InventoryDao inventoryDao;
	private RecommendedMembershipsDao recommendedMembershipsDao;
	private MemberLegalDao memberLegalDao;
	private RulesBL rulesBL;

	private static String DATE_FORMAT = "MM/dd/yyyy";
	private static SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);


	public void setNationDao(NationDao nationDao)
	{
		this.nationDao = nationDao;
	}
	public void setStateDao(StateDao stateDao)
	{
		this.stateDao = stateDao;
	}
	public void setClubDao(ClubDao clubDao)
	{
		this.clubDao = clubDao;
	}
	public void setDivisionDao(DivisionDao divisionDao)
	{
		this.divisionDao = divisionDao;
	}
	public void setInventoryDao(InventoryDao inventoryDao)
	{
		this.inventoryDao = inventoryDao;
	}
	public void setRecommendedMembershipsDao(RecommendedMembershipsDao recommendedMembershipsDao)
	{
		this.recommendedMembershipsDao = recommendedMembershipsDao;
	}
	public void setMemberLegalDao(MemberLegalDao memberLegalDao)
	{
		this.memberLegalDao = memberLegalDao;
	}
	public void setRulesBL(RulesBL rulesBL)
	{
		this.rulesBL = rulesBL;
	}
	public void setMemberDao(MemberDao memberDao)
	{
		this.memberDao = memberDao;
	}
	public void setAddressDao(AddressDao addressDao)
	{
		this.addressDao = addressDao;
	}
	public Event findContactInfo(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
		String idParam = context.getRequestParameters().get("id");

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(" "+userDetails.getUsername());

		Long id = null;
		if (idParam != null)
		{
			id = Long.parseLong(idParam);
		}

		String currentSeason = rulesBL.getCurrentRenewSeason();
		String lastSeason = rulesBL.getLastSeason();

		Member member = new Member();
		ParentInfo parentInfo = new ParentInfo();
		member.setParentInfo(parentInfo);
		Address address = new Address();

		MemberLegal memberLegal = new MemberLegal();
		MemberLegalPk memberLegalPk = new MemberLegalPk();
		memberLegalPk.setSeason(currentSeason);
		memberLegal.setMemberLegalPk(memberLegalPk);

		List<State> usStates = stateDao.getAllStateUS_CodeOrdered();
		accountBean.setUsStates(usStates);

		if (id != null)
		{
			//Member exists, do a renew and pre-populate info
			member = memberDao.get(id);

			if (member == null)
			{
				member = new Member();
			}

			if(member.getParentInfo() == null)
			{
				member.setParentInfo(parentInfo);
			}

			AddressPk addressPk = new AddressPk();
			addressPk.setType("P");
			addressPk.setId(id);
			address = addressDao.get(addressPk);
			if (address == null)
			{
				address = new Address();
			}
			accountBean.setAddress(address);
			accountBean.setMember(member);
			if(member.getBirthDate() != null)
			{
				accountBean.setBirthDate(formatter.format(member.getBirthDate()));
			}
			accountBean.setAge(rulesBL.getAgeForCurrentRenewSeason(member.getBirthDate()));

			// prepopulate the cart with the recommended membership options
			accountBean.setCartBean(new CartBean());
			List<LineItemBean> recommendedMemberships = recommendedMembershipsDao.getRecommendedMemberships(currentSeason, member.getId(), lastSeason);
			accountBean.getCartBean().setLineItems(recommendedMemberships);

			memberLegalPk.setUssaId(member.getId());
			MemberLegal tempMemberLegal = memberLegalDao.get(memberLegalPk);
			if(tempMemberLegal != null)
			{
				accountBean.setMemberLegal(tempMemberLegal);
			}
			else
			{
				accountBean.setMemberLegal(memberLegal);
			}

			return result("renew");
		}
		else
		{
			//Member does not exist, do a register
			accountBean.setAddress(address);
			accountBean.setMember(member);
			accountBean.setMemberLegal(memberLegal);
			return result("register");
		}
	}

	public Event handleBirthDate(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
		String birthDate = accountBean.getBirthDate();
		if(StringUtils.isNotBlank(birthDate))
		{
			accountBean.getMember().setBirthDate(formatter.parse(birthDate));
		}
		accountBean.setAge(rulesBL.getAgeForCurrentRenewSeason(accountBean.getMember().getBirthDate()));
		return success();
	}

	public Event findClubInfo(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
		List<Club> clubs = new ArrayList<Club>();
		String stateAffiliation = new String();

		accountBean.setUsCitizen("USA".equals(accountBean.getMember().getNationCode()));
		accountBean.setNations(nationDao.getAllNations());

		if (accountBean.getMember().getStateCode() != null)
		{ //If I change the state affiliation (in stateClub) set it here
			stateAffiliation = accountBean.getMember().getStateCode();
		}
		else if (accountBean.getAddress() != null)
		{ //Coming through first pass regular flow
			if (accountBean.getAddress().getStateCode() != null)
			{ //This should always be hit as the user is required to put in an address on the prior screen
				stateAffiliation = accountBean.getAddress().getStateCode();
			}
		}
		if (stateAffiliation.length()!=0)
		{
			clubs = clubDao.findByStateCode(stateAffiliation);
		}

		if(!"USA".equals(accountBean.getMember().getNationCode()))
		{
			accountBean.getMember().setDivision(divisionDao.getDivision("X"));
		}
		else if(accountBean.getClubId() != null)
		{
			Club club = clubDao.get(accountBean.getClubId());
			accountBean.getMember().setDivision(club.getDivision());
		}

		accountBean.setClubsForState(clubs);
		accountBean.getMember().setStateCode(stateAffiliation);
		return success();
	}


	public Event findApplicableSportMemberships(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");

		List<Inventory> memberships = new ArrayList<Inventory>();
		if (StringUtils.isNotBlank(accountBean.getSportId()))
		{
			int age = rulesBL.getAgeForCurrentRenewSeason(accountBean.getMember().getBirthDate());
			memberships = inventoryDao.getAllMembershipsByCriteria(age, accountBean.getSportId());
			rulesBL.filterMemberships(accountBean, memberships);
		}

		accountBean.setMemberships(memberships);

		return success();
	}

	public Event addSportMemberships(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");

		CartBean cart = accountBean.getCartBean();
		Inventory membership = inventoryDao.get(accountBean.getMembershipId());
		if(!rulesBL.inventoryIsRestricted(accountBean, membership))
		{
			cart.addItem(membership);
		}

		return success();
	}

	public Event handleContribution(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");

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
				List<Inventory> donationInventory = inventoryDao.getIventoryByType(Inventory.INVENTORY_TYPE_DONATION);
				cart.addItem(donationInventory.get(0), new BigDecimal(accountBean.getContributionAmount()));
			}
		}

		return success();
	}

	public Event removeItemFromCart(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");

		CartBean cart = accountBean.getCartBean();
		String id = context.getRequestParameters().get("id");

		LineItemBean lineItem = cart.getLineItem(id);
		if(lineItem != null)
		{
			if(Inventory.INVENTORY_TYPE_DONATION.equals(lineItem.getInventory().getInventoryType()))
			{
				accountBean.setContributionAmount(null);
			}
			cart.removeLineItem(id);
		}

		return result("back");
	}

	public Event addExtras(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
		ExtrasBean extrasBean = accountBean.getExtrasBean();
		CartBean cartBean = accountBean.getCartBean();

		handleOption(cartBean, extrasBean.getAlpineOption(), extrasBean.getAlpineQty());
		handleOption(cartBean, extrasBean.getFreestyleOption(), extrasBean.getFreestyleQty());
		handleOption(cartBean, extrasBean.getCrossCountryOption(), extrasBean.getCrossCountryQty());
		handleOption(cartBean, extrasBean.getJumpingOption(), extrasBean.getJumpingQty());
		handleOption(cartBean, extrasBean.getBoardingOption(), extrasBean.getBoardingQty());
		handleOption(cartBean, extrasBean.getGeneralOption(), extrasBean.getGeneralQty());
		handleOption(cartBean, extrasBean.getDecalSkiOption(), extrasBean.getDecalSkiQty());
		handleOption(cartBean, extrasBean.getDecalBoardOption(), extrasBean.getDecalBoardQty());

		// clear the extras bean the quick and dirty way
		accountBean.setExtrasBean(new ExtrasBean());

		return success();
	}

	private void handleOption(CartBean cartBean, String invId, String qty)
	{
		if(StringUtils.isNotBlank(invId))
		{
			Inventory inventory = inventoryDao.get(invId);
			if(StringUtils.isNotBlank(qty))
			{
				cartBean.addItem(inventory, new Integer(qty));
			}
		}
	}

}
