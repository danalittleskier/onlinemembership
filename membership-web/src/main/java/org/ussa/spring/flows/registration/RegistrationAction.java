package org.ussa.spring.flows.registration;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.webflow.action.MultiAction;
import org.springframework.webflow.context.servlet.ServletExternalContext;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;
import org.ussa.beans.AccountBean;
import org.ussa.beans.CartBean;
import org.ussa.dao.AddressDao;
import org.ussa.dao.ClubDao;
import org.ussa.dao.InventoryDao;
import org.ussa.dao.MemberDao;
import org.ussa.dao.NationDao;
import org.ussa.dao.StateDao;
import org.ussa.dao.DivisionDao;
import org.ussa.manager.MemberManager;
import org.ussa.model.Address;
import org.ussa.model.AddressPk;
import org.ussa.model.Club;
import org.ussa.model.Inventory;
import org.ussa.model.Member;
import org.ussa.model.ParentInfo;
import org.ussa.model.State;


public class RegistrationAction extends MultiAction implements Serializable
{
	private MemberManager memberManager;
	private MemberDao memberDao;
	private AddressDao addressDao;
	private StateDao stateDao;
	private NationDao nationDao;
	private ClubDao clubDao;
	private DivisionDao divisionDao;
	private InventoryDao inventoryDao;


	public void setMemberManager(MemberManager memberManager)
	{
		this.memberManager = memberManager;
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
		AccountBean obj = (AccountBean) context.getFlowScope().get("accountBean");
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
		Inventory inventory = new Inventory();

		List<State> usStates = stateDao.getAllStateUS_CodeOrdered();
		obj.setUsStates(usStates);

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
			addressPk.setId(id);//new Long(6050553)); //Micahel Henderson, lucky john
			address = addressDao.get(addressPk);
			if (address == null)
			{
				address = new Address();
			}
			obj.setAddress(address);
			obj.setMember(member);
			obj.setInventory(inventory);
			return result("renew");
		}
		else
		{
			//Member does not exist, do a register
			obj.setAddress(address);
			obj.setMember(member);
			obj.setInventory(inventory);
			return result("register");
		}
	}

	public Event findClubInfo(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
		List<Club> clubs = new ArrayList<Club>();
		String stateAffiliation = new String();
/*
		System.out.println("BirthDate["+accountBean.getMember().getBirthDate()+"]");
		System.out.println("Address's city["+accountBean.getAddress().getCity()+"]");
		System.out.println("Address's state["+accountBean.getAddress().getStateCode()+"]");
		System.out.println("StateAffiliation["+accountBean.getMember().getStateCode()+"]");
*/

		accountBean.setUsCitizen("USA".equals(accountBean.getMember().getNationCode()));
		accountBean.setNations(nationDao.getAllNations());

		if (accountBean != null)
		{
//			System.out.println("Club Info -- Orig state["+accountBean.getMember().getStateCode()+"]");
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
//				System.out.println("state["+stateAffiliation+"]");
				clubs = clubDao.findByStateCode(stateAffiliation);
//				System.out.println("clubs["+clubs.size()+"]");
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

		}
		accountBean.setClubsForState(clubs);
		accountBean.getMember().setStateCode(stateAffiliation);
		return result("form");
	}

	public Event findApplicableSportMemberships(RequestContext context) throws Exception
	{
		HttpServletRequest request = ((ServletExternalContext)context.getExternalContext()).getRequest();
		AccountBean obj = (AccountBean) context.getFlowScope().get("accountBean");
		List<Inventory> memberships = new ArrayList<Inventory>();
		//Here is where logic goes off of birthdate...
		System.out.println("sportId["+obj.getSportId()+"]");

		Integer age = obj.getMember().getAge(); //For now hardcode age but need to compute off of birthdate.
		if (obj != null)
		{
			String sportCode=obj.getSportId();
			//memberships = inventoryDao.getAllMembershipTypes();
			if (sportCode != null)
			{
				memberships = inventoryDao.getAllMembershipsByCriteria(age, sportCode);
			}
		}
		obj.setMemberships(memberships);
		System.out.println("numMemberships["+memberships.size()+"]");
		return result("form");
	}

	public Event addSportMemberships(RequestContext context) throws Exception
	{
		HttpServletRequest request = ((ServletExternalContext)context.getExternalContext()).getRequest();
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
		CartBean cart = accountBean.getCartBean();
		System.out.println("In addSportMembership!!!");
		if (cart == null)
		{
			cart = new CartBean();
		}
		String membershipId = accountBean.getMembershipId();
		Inventory membership = inventoryDao.get(membershipId);
		cart.addMembership(membership);
		if ((accountBean.getMemberships() != null) && (cart != null))
		{
			if (accountBean.getMemberships().size() > 0)
			{

				accountBean.setShoppingCart(cart.getShoppingCart());
//				BigDecimal total = BigDecimal.ZERO;
//				if (accountBean.getCartBean().getTotal() != null)
//				{
//					total = accountBean.getCartBean().getTotal();
//					System.out.println("shoppingCart total["+total+"]");
//				}

				//System.out.println("shoppingCart total["+accountBean.getCartBean().getTotal().toString()+"]");
				//accountBean.setTotal(total);
				//System.out.println("shoppingCart["+accountBean.getCartBean().getShoppingCart().size()+"]");
				//System.out.println("shoppingCart["+accountBean.getCartBean().getTotal().intValue()+"]");

			}
		}
		System.out.println("cart size["+cart.getMemberships().size()+"]");
		accountBean.setCartBean(cart);
		//Clear membership and sport for the next addition
		accountBean.setSportId(null);
		accountBean.setMembershipId(null);
		accountBean.setMemberships(new ArrayList<Inventory>());
		return result("form");
	}


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
	/**
	 * @param inventoryDao the inventoryDao to set
	 */
	public void setInventoryDao(InventoryDao inventoryDao)
	{
		this.inventoryDao = inventoryDao;
	}
}
