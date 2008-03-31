package org.ussa.spring.flows.registration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.springframework.webflow.action.MultiAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;
import org.ussa.beans.AccountBean;
import org.ussa.beans.CartBean;
import org.ussa.beans.LineItemBean;
import org.ussa.dao.AddressDao;
import org.ussa.dao.ClubDao;
import org.ussa.dao.DivisionDao;
import org.ussa.dao.InventoryDao;
import org.ussa.dao.MemberDao;
import org.ussa.dao.NationDao;
import org.ussa.dao.StateDao;
import org.ussa.model.Address;
import org.ussa.model.AddressPk;
import org.ussa.model.Club;
import org.ussa.model.Inventory;
import org.ussa.model.Member;
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
		String zId = context.getRequestParameters().get("id");

		Long id = null;
		if (zId != null)
		{
			id = Long.parseLong(zId);
		}

		Member member = new Member();
		ParentInfo parentInfo = new ParentInfo();
		member.setParentInfo(parentInfo);
		Address address = new Address();

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
			return result("renew");
		}
		else
		{
			//Member does not exist, do a register
			accountBean.setAddress(address);
			accountBean.setMember(member);
			return result("register");
		}
	}

	public Event handleBirthDate(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
		String birthDate = accountBean.getBirthDate();
		if(StringUtils.isNotBlank(birthDate))
		{
			accountBean.getMember().setBirthDate(new java.sql.Date(formatter.parse(birthDate).getTime()));
		}
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
			memberships = inventoryDao.getAllMembershipsByCriteria(accountBean.getMember().getAge(), accountBean.getSportId());
			filterMembershipsThatAlreadyAreInCart(memberships, accountBean.getCartBean());
		}

		accountBean.setMemberships(memberships);

		return success();
	}

	private void filterMembershipsThatAlreadyAreInCart(List<Inventory> memberships, CartBean cart)
	{
		for (Iterator<Inventory> iterator = memberships.iterator(); iterator.hasNext();)
		{
			Inventory inventory = iterator.next();
			if(cart.contains(inventory.getId()))
			{
				iterator.remove();
			}
		}
	}

	public Event addSportMemberships(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");

		CartBean cart = accountBean.getCartBean();
		Inventory membership = inventoryDao.get(accountBean.getMembershipId());
		cart.addItem(membership);

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

}
