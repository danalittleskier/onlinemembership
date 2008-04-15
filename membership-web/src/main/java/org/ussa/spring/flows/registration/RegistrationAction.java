package org.ussa.spring.flows.registration;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.commons.lang.StringUtils;
import org.springframework.webflow.action.MultiAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;
import org.ussa.beans.AccountBean;
import org.ussa.beans.CartBean;
import org.ussa.beans.ExtrasBean;
import org.ussa.bl.DateBL;
import org.ussa.bl.RulesBL;
import org.ussa.dao.AddressDao;
import org.ussa.dao.ClubDao;
import org.ussa.dao.DivisionDao;
import org.ussa.dao.InventoryDao;
import org.ussa.dao.MemberDao;
import org.ussa.dao.MemberLegalDao;
import org.ussa.dao.NationDao;
import org.ussa.dao.RenewRuleInvDao;
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
import org.ussa.common.service.UserManager;
import org.ussa.common.model.User;


public class RegistrationAction extends MultiAction implements Serializable
{
	private MemberDao memberDao;
	private AddressDao addressDao;
	private StateDao stateDao;
	private NationDao nationDao;
	private ClubDao clubDao;
	private DivisionDao divisionDao;
	private InventoryDao inventoryDao;
	private RenewRuleInvDao renewRuleInvDao;
	private MemberLegalDao memberLegalDao;
	private RulesBL rulesBL;
	private DateBL dateBL;
	private UserManager userManager;
	private SecurityContext securityContext;

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
	public void setRenewRuleInvDao(RenewRuleInvDao renewRuleInvDao)
	{
		this.renewRuleInvDao = renewRuleInvDao;
	}
	public void setMemberLegalDao(MemberLegalDao memberLegalDao)
	{
		this.memberLegalDao = memberLegalDao;
	}
	public void setRulesBL(RulesBL rulesBL)
	{
		this.rulesBL = rulesBL;
	}
	public void setDateBL(DateBL dateBL)
	{
		this.dateBL = dateBL;
	}
	public void setMemberDao(MemberDao memberDao)
	{
		this.memberDao = memberDao;
	}
	public void setAddressDao(AddressDao addressDao)
	{
		this.addressDao = addressDao;
	}
	public UserManager getUserManager()
	{
		return userManager;
	}
	public void setUserManager(UserManager userManager)
	{
		this.userManager = userManager;
	}

	public void setSecurityContext(SecurityContext securityContext)
	{
		this.securityContext = securityContext;
	}

	public Event findContactInfo(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
		String idParam = context.getRequestParameters().get("id");

		UserDetails userDetails = (UserDetails) securityContext.getAuthentication().getPrincipal();
		User user = userManager.getUserByUsername(userDetails.getUsername());

		Long id;
		if (idParam != null)
		{
			id = Long.parseLong(idParam);
		}
		else
		{
			id = user.getUssaId();
		}

		String currentSeason = dateBL.getCurrentRenewSeason();

		List<State> usStates = stateDao.getAllStateUS_CodeOrdered();
		accountBean.setUsStates(usStates);

		accountBean.setCartBean(new CartBean());

		// new registration
		if(id == null)
		{
			Member member = new Member();
			accountBean.setMember(member);
			member.setFirstName(user.getFirstName());
			member.setLastName(user.getLastName());
			member.setParentInfo(new ParentInfo());
			if(StringUtils.isNotBlank(user.getBirthDate()))
			{
				member.setBirthDate(formatter.parse(user.getBirthDate()));
				rulesBL.setParentInfoRequired(accountBean);
			}

			Address address = new Address();
			accountBean.setAddress(address);

			MemberLegal memberLegal = new MemberLegal();
			accountBean.setMemberLegal(memberLegal);
			MemberLegalPk memberLegalPk = new MemberLegalPk();
			memberLegalPk.setSeason(currentSeason);
			memberLegal.setMemberLegalPk(memberLegalPk);
		}
		// renewals
		else
		{
			Member member = memberDao.get(id);
			accountBean.setMember(member);

			if(member.getBirthDate() != null)
			{
				accountBean.setBirthDate(formatter.format(member.getBirthDate()));
			}

			rulesBL.setParentInfoRequired(accountBean);
			if(member.getParentInfo() == null)
			{
				member.setParentInfo(new ParentInfo());
			}

			AddressPk addressPk = new AddressPk();
			addressPk.setType(Address.ADDRESS_TYPE_PRIMARY);
			addressPk.setMember(member);
			Address address = addressDao.get(addressPk);
			if (address == null)
			{
				address = new Address();
			}
			else
			{
				// TODO: there has to be a better check that I can than this.
				if(!"USA".equals(address.getCountry()))
				{
					return result("foreign");
				}
			}
			accountBean.setAddress(address);

			MemberLegalPk memberLegalPk = new MemberLegalPk();
			memberLegalPk.setMember(member);
			memberLegalPk.setSeason(currentSeason);
			MemberLegal memberLegal = memberLegalDao.get(memberLegalPk);
			if(memberLegal == null)
			{
				memberLegal = new MemberLegal();
				memberLegal.setMemberLegalPk(memberLegalPk);
				accountBean.setMemberLegal(memberLegal);
			}
			else
			{
				// the person has already processed a registration for this year.
				return result("alreadyRegistered");
			}

			// don't prepopulate the cart for non members
			if(!Member.MEMBER_TYPE_NON_MEMBER.equals(member.getType()))
			{
				// for renewals prepopulate the cart with the recommended membership options
				String lastSeason = dateBL.getLastSeason();
				Integer currentSeasonAge = rulesBL.getAgeForCurrentRenewSeason(member.getBirthDate());
				List<Inventory> recommendedMemberships = renewRuleInvDao.getRecommendedMemberships(member.getId(), currentSeasonAge, lastSeason);
				for (Inventory inventory : recommendedMemberships)
				{
					rulesBL.addMembershipToCart(accountBean, inventory);
				}
			}
		}

		return result("contactInfo");
	}

	public Event handleBirthDate(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");

		rulesBL.setParentInfoRequired(accountBean);
		boolean wasParentInfoRequired = accountBean.getParentInfoRequired();

		String birthDate = accountBean.getBirthDate();
		if(StringUtils.isNotBlank(birthDate))
		{
			accountBean.getMember().setBirthDate(formatter.parse(birthDate));
		}

		rulesBL.setParentInfoRequired(accountBean);
		if(!wasParentInfoRequired && accountBean.getParentInfoRequired())
		{
			return result("parentInfo");
		}

		return result("findClubInfo");
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


	public Event loadSportMemberships(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");

		accountBean.setMemberships(rulesBL.findApplicableSportMemberships(accountBean));
		accountBean.setFisItems(rulesBL.findApplicableFisItems(accountBean));
		accountBean.setMagazineItems(rulesBL.findApplicableMagazineItems(accountBean));

		return success();
	}

	public Event addSportMemberships(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");

		Inventory membership = inventoryDao.get(accountBean.getMembershipId());
		rulesBL.addMembershipToCart(accountBean, membership);

		rulesBL.handleFisOptions(accountBean);
		rulesBL.handleMagazineOption(accountBean);
		rulesBL.handleContribution(accountBean);

		return success();
	}

	public Event updateMembershipOptions(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");

		rulesBL.handleFisOptions(accountBean);
		rulesBL.handleMagazineOption(accountBean);
		rulesBL.handleContribution(accountBean);

		return success();
	}

	public Event handleContribution(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");

		rulesBL.handleFisOptions(accountBean);
		rulesBL.handleMagazineOption(accountBean);
		rulesBL.handleContribution(accountBean);

		return success();
	}

	public Event removeItemFromCart(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");

		String id = context.getRequestParameters().get("id");

		rulesBL.removeItemFromCart(accountBean, id);

		return result("back");
	}

	public Event needsFisWaiver(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
		if(rulesBL.hasFis(accountBean, false) && !Boolean.TRUE.equals(accountBean.getHasFisWaiver()))
		{
			return result("true");
		}
		else
		{
			return result("false");
		}
	}

	public Event handleFisWaiverResponse(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
		if(Boolean.FALSE.equals(accountBean.getHasFisWaiver()))
		{
			rulesBL.removeFisFromCart(accountBean, false);
		}

		return success();
	}

	public Event needsFisWaiverDisabled(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
		if(rulesBL.hasFis(accountBean, true) && !Boolean.TRUE.equals(accountBean.getHasFisWaiverDisabled()))
		{
			return result("true");
		}
		else
		{
			return result("false");
		}
	}

	public Event handleFisWaiverDisabledResponse(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
		if(Boolean.FALSE.equals(accountBean.getHasFisWaiverDisabled()))
		{
			rulesBL.removeFisFromCart(accountBean, true);
		}

		return success();
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
