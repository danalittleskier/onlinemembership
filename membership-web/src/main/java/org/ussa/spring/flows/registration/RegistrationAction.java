package org.ussa.spring.flows.registration;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.commons.lang.WordUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.webflow.action.FormAction;
import org.springframework.webflow.context.servlet.ServletExternalContext;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;
import org.ussa.beans.AccountBean;
import org.ussa.beans.CartBean;
import org.ussa.beans.ExtrasBean;
import org.ussa.beans.MessageBean;
import org.ussa.bl.DateBL;
import org.ussa.bl.RulesBL;
import org.ussa.common.dao.UniversalDao;
import org.ussa.common.model.User;
import org.ussa.common.service.UserManager;
import org.ussa.dao.AddressDao;
import org.ussa.dao.ClubDao;
import org.ussa.dao.DivisionDao;
import org.ussa.dao.InventoryDao;
import org.ussa.dao.MemberClubDao;
import org.ussa.dao.MemberDao;
import org.ussa.dao.MemberLegalDao;
import org.ussa.dao.NationDao;
import org.ussa.dao.StateDao;
import org.ussa.model.Address;
import org.ussa.model.AddressPk;
import org.ussa.model.Club;
import org.ussa.model.Division;
import org.ussa.model.Inventory;
import org.ussa.model.Member;
import org.ussa.model.MemberClub;
import org.ussa.model.MemberLegal;
import org.ussa.model.MemberSeasonPk;
import org.ussa.model.ParentInfo;
import org.ussa.util.DateTimeUtils;
import org.ussa.util.StringUtils;


public class RegistrationAction extends FormAction implements Serializable
{
	private MemberDao memberDao;
	private AddressDao addressDao;
	private StateDao stateDao;
	private NationDao nationDao;
	private ClubDao clubDao;
	private MemberClubDao memberClubDao;
	private DivisionDao divisionDao;
	private InventoryDao inventoryDao;
	private MemberLegalDao memberLegalDao;
	private RulesBL rulesBL;
	private DateBL dateBL;
	private UserManager userManager;
	private SecurityContext securityContext;
	private UniversalDao universalDao;

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
	public void setMemberClubDao(MemberClubDao memberClubDao)
	{
		this.memberClubDao = memberClubDao;
	}
	public void setDivisionDao(DivisionDao divisionDao)
	{
		this.divisionDao = divisionDao;
	}
	public void setInventoryDao(InventoryDao inventoryDao)
	{
		this.inventoryDao = inventoryDao;
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
	public void setUserManager(UserManager userManager)
	{
		this.userManager = userManager;
	}
	public void setSecurityContext(SecurityContext securityContext)
	{
		this.securityContext = securityContext;
	}
	public void setUniversalDao(UniversalDao universalDao) {
		this.universalDao = universalDao;
	}
	private void initExistingAccountBean(AccountBean accountBean, User user, Long memberId) {
		Member member = memberDao.get(memberId);
		member.setEmail(user.getEmail());
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
		Address address = null;
		try
		{
			address = addressDao.get(addressPk);
		}
		catch (ObjectRetrievalFailureException e)
		{
			address = new Address();
		}
		accountBean.setAddress(address);
		
		// MemberLegal
		
		MemberSeasonPk memberSeasonPk = new MemberSeasonPk();
		memberSeasonPk.setMember(member);
		memberSeasonPk.setSeason(dateBL.getCurrentRenewSeason());

		MemberLegal memberLegal = null;
		try
		{
			memberLegal = memberLegalDao.get(memberSeasonPk);
			accountBean.setMemberLegal(memberLegal);
		}
		catch (ObjectRetrievalFailureException e)
		{
			memberLegal = new MemberLegal();
			memberLegal.setMemberSeasonPk(memberSeasonPk);
			accountBean.setMemberLegal(memberLegal);
		}
	}
	
	public Event loadContactInfoForUpdate(RequestContext context) throws Exception {
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");

		UserDetails userDetails = (UserDetails) securityContext.getAuthentication().getPrincipal();
		User user = userManager.getUserByUsername(userDetails.getUsername());

		Long id;
		if (StringUtils.isNotBlank(context.getRequestParameters().get("id")))
		{
			id = Long.parseLong(context.getRequestParameters().get("id"));
		}
		else
		{
			id = user.getUssaId();
		}
		
		accountBean.setUsStates(stateDao.getAllUsStatesOrderedByCode());
		accountBean.setAllStates(stateDao.getAllStatesOrderedByCode());

		initExistingAccountBean(accountBean, user, id);
		
		return result("updateContactInfo");
	}
	
	public Event saveMemberInfo(RequestContext context) throws Exception { 
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
		
		memberDao.save(accountBean.getMember());
		universalDao.save(accountBean.getAddress());
		return result("complete");
	}
	
	public Event loadMedicalInfoForUpdate(RequestContext context) throws Exception {
		loadContactInfoForUpdate(context);
		return result("updateMedicalInfo");
	}

	public Event loadContactInfo(RequestContext context) throws Exception
	{
		// FIXME: Need to figure out the elegant way to do this via configuration in SWF
		String subflow = context.getRequestParameters().get("subflow");
		if (StringUtils.isNotBlank(subflow)) {
			if ("updateContactInfo".equals(subflow)) {
				return result("loadContactInfoForUpdate");
			}
			
			if ("updateMedicalInfo".equals(subflow)) {
				return result("loadMedicalInfoForUpdate");
			}
		}
		
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");

		UserDetails userDetails = (UserDetails) securityContext.getAuthentication().getPrincipal();
		User user = userManager.getUserByUsername(userDetails.getUsername());

		Long id;
		if (StringUtils.isNotBlank(context.getRequestParameters().get("id")))
		{
			id = Long.parseLong(context.getRequestParameters().get("id"));
		}
		else
		{
			id = user.getUssaId();
		}

		String currentSeason = dateBL.getCurrentRenewSeason();

		accountBean.setUsStates(stateDao.getAllUsStatesOrderedByCode());
		accountBean.setAllStates(stateDao.getAllStatesOrderedByCode());

		accountBean.setCartBean(new CartBean());

		// new registration
		if(id == null)
		{
			Member member = new Member();
			accountBean.setMember(member);
			member.setFirstName(user.getFirstName());
			member.setLastName(user.getLastName());
			member.setEmail(user.getEmail());
			member.setParentInfo(new ParentInfo());
			if(StringUtils.isNotBlank(user.getBirthDate()))
			{
				member.setBirthDate(formatter.parse(user.getBirthDate()));
				rulesBL.setParentInfoRequired(accountBean);
			}

			Address address = new Address();
			// default the country to US
			address.setCountry("USA");
			accountBean.setAddress(address);

			MemberLegal memberLegal = new MemberLegal();
			accountBean.setMemberLegal(memberLegal);
			MemberSeasonPk memberSeasonPk = new MemberSeasonPk();
			memberSeasonPk.setSeason(currentSeason);
			memberLegal.setMemberSeasonPk(memberSeasonPk);
		}
		// renewals
		else
		{
			initExistingAccountBean(accountBean, user, id);

			if(!rulesBL.isCountryUs(accountBean.getAddress().getCountry()))
			{
				BindException errors = new BindException(accountBean, "accountBean");
				errors.reject("errors.foreign");
				getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope());
				return result("registrationError");
			}

			if(accountBean.getMemberLegal() != null)
			{
				// the person has already processed a registration for this year.
				BindException errors = new BindException(accountBean, "accountBean");
				errors.reject("errors.already.registered");
				getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope());
				return result("registrationError");
			}

			// prepopulate cart
			rulesBL.prepopulateCart(accountBean);
		}

		return result("contactInfo");
	}

	public Event handleContactInfo(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
		Address address = accountBean.getAddress();
		Member member = accountBean.getMember();

		// force all phone numbers to the spcified format
		address.setPhoneHome(StringUtils.formatPhone(address.getPhoneHome()));
		address.setPhoneWork(StringUtils.formatPhone(address.getPhoneWork()));
		address.setPhoneOther(StringUtils.formatPhone(address.getPhoneOther()));
		address.setPhoneFax(StringUtils.formatPhone(address.getPhoneFax()));

		// force title case for the following
		member.setFirstName(WordUtils.capitalizeFully(member.getFirstName()));
		member.setMiddleName(WordUtils.capitalizeFully(member.getMiddleName()));
		member.setLastName(WordUtils.capitalizeFully(member.getLastName()));
		address.setCity(WordUtils.capitalizeFully(address.getCity()));

		// force upper case for the country
		address.setCountry(address.getCountry().toUpperCase());

		// They want to force the US the be entered same way for everyone
		if(rulesBL.isCountryUs(address.getCountry()))
		{
			address.setCountry("USA");
		}

		// if their address is in the US then their state is required
		if(rulesBL.isCountryUsOrCanada(address.getCountry()))
		{
			if(StringUtils.isBlank(address.getStateCode()))
			{
				BindException errors = new BindException(accountBean, "accountBean");
				errors.reject("errors.state.required.for.us");
				getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope());
				return error();
			}
		}

		// bind the birthdate and do some additional validation
		String birthDateStr = accountBean.getBirthDate();
		if(StringUtils.isNotBlank(birthDateStr))
		{
			Calendar birthDate = DateTimeUtils.getCalendar(formatter.parse(birthDateStr));
			Calendar now = Calendar.getInstance();
			Calendar hundredsBack = DateTimeUtils.getCalendar(now.getTime());
			hundredsBack.add(Calendar.YEAR, -150);
			if(birthDate.after(now) || birthDate.before(hundredsBack))
			{
				BindException errors = new BindException(accountBean, "accountBean");
				ObjectError error = new FieldError("accountBean", "member.birthDate", accountBean.getBirthDate(), 
						false, new String[]{"errors.invalid.birthdate"},
						new Object[]{new DefaultMessageSourceResolvable("label.birth.date")}, "errors.invalid.birthdate");
				errors.addError(error);
				getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope());
				return error();
			}
			member.setBirthDate(birthDate.getTime());
		}

		// for new registrations check for dups
		if (member.getId() == null)
		{
			List<Member> dups = memberDao.getDuplicateCandidates(member.getLastName(), member.getBirthDate());
			if (dups != null && !dups.isEmpty()) {
				accountBean.setDuplicateUsers(dups);
				return result("duplicateAccount");
			}
		}

		// for new registrations that are under 18 send them to the parent info screen
		rulesBL.setParentInfoRequired(accountBean);
		if(member.getId() == null && accountBean.getParentInfoRequired())
		{
			return result("parentInfo");
		}

		return result("findClubInfo");
	}

	public Event loadClubInfo(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
		Member member = accountBean.getMember();

		// default US citizenship to 'true' if their nationCode is already USA
		String nationCode = member.getNationCode();
		if(StringUtils.isBlank(nationCode))
		{
			nationCode = rulesBL.isCountryUs(accountBean.getAddress().getCountry()) ? "USA" : accountBean.getAddress().getCountry();
		}
		if(rulesBL.isCountryUs(nationCode))
		{
			accountBean.setUsCitizen(true);
			member.setNationCode("USA");
		}

		// load nation options
		accountBean.setNations(nationDao.getAllNations());

		// determine the user's state affiliation
		String stateAffiliation = getStateAffiliation(accountBean);
		member.setStateCode(stateAffiliation);

		// determine the user's club
		try
		{
			if(member.getId() != null)
			{
				MemberClub memberClub = memberClubDao.get(member.getId());
				accountBean.setClubId(memberClub.getClubUssaId());
			}
		}
		catch (ObjectRetrievalFailureException e)
		{
			accountBean.setClubId(0L);
		}

		// determine which clubs to display in the select list based on the state affiliation
		loadClubOptions(accountBean);

		Division division = rulesBL.determineDivision(nationCode, member.getStateCode(), null, null);
		if(division != null)
		{
			member.setDivision(division);
			accountBean.setDivisionCode(division.getDivisionCode());
			accountBean.setDivisionDescription(division.getDescription());
		}

		return success();
	}

	private void loadClubOptions(AccountBean accountBean) {
		List<Club> clubs = new ArrayList<Club>();
		String stateCode = accountBean.getMember().getStateCode();
		if (StringUtils.isNotEmpty(stateCode))
		{
			clubs = clubDao.findByStateCode(stateCode);
		}
		accountBean.setClubsForState(clubs);
	}

	/**
	 * The user's state affiliation is retrieved first
	 * from their state code (if available) or from the state code of their address.
	 */
	private String getStateAffiliation(AccountBean accountBean) {
		String result = null;
		if (StringUtils.isNotEmpty(accountBean.getMember().getStateCode()))
		{
			result = accountBean.getMember().getStateCode();
		}
		else if (accountBean.getAddress() != null && StringUtils.isNotEmpty(accountBean.getAddress().getStateCode()))
		{
			result = accountBean.getAddress().getStateCode();
		}
		return result;
	}

	public Event processClubInfo(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");

		// if they are foreign then they can't do foreign at this time
		if(!rulesBL.isCountryUs(accountBean.getMember().getNationCode()))
		{
			return result("foreign");
		}

		if(StringUtils.isNotBlank(accountBean.getDivisionCode()))
		{
			accountBean.getMember().setDivision(divisionDao.get(accountBean.getDivisionCode()));
		}

		// Incase they just changed their division we need to reasses their div and state dues;
		rulesBL.addRemoveDivisionDuesAndLateFees(accountBean);

		return success();
	}

	public Event loadSportMemberships(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");

		accountBean.setMemberships(rulesBL.getApplicableSportMemberships(accountBean));
		accountBean.setFisItems(rulesBL.getApplicableFisItems(accountBean));
		accountBean.setMagazineItems(rulesBL.getValidMagazineOptions(accountBean));

		// this is needed to determine whether or not to show the backgroundScreeningPopup
		HttpServletRequest request = ((ServletExternalContext)context.getExternalContext()).getRequest();
		request.getSession().removeAttribute("showBackgroundScreening");
		if(! accountBean.getWasBgScreeningInfoAlreadyShown() && rulesBL.needsBackgroundCheck(accountBean))
		{
			request.getSession().setAttribute("showBackgroundScreening", true);

			accountBean.setWasBgScreeningInfoAlreadyShown(true);
		}

		return success();
	}

	public Event addSportMemberships(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");

		Inventory membership = inventoryDao.get(accountBean.getMembershipId());
		List<MessageBean> messages = rulesBL.addMembershipToCart(accountBean, membership);

		// Yes, this is a bit of a hack. need to learn more about Spring MVC messages. Hopefully there is a better way to do this.
		HttpServletRequest request = ((ServletExternalContext)context.getExternalContext()).getRequest();
		request.getSession().setAttribute("messages", messages);

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
		if(rulesBL.hasFis(accountBean, false) && !"Y".equals(accountBean.getMemberLegal().getFisReleaseForm()))
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
		if("N".equals(accountBean.getMemberLegal().getFisReleaseForm()))
		{
			rulesBL.removeFisFromCart(accountBean, false);
		}

		return success();
	}

	public Event needsFisWaiverDisabled(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
		if(rulesBL.hasFis(accountBean, true) && !"Y".equals(accountBean.getMemberLegal().getIpcReleaseForm()))
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
		if("N".equals(accountBean.getMemberLegal().getIpcReleaseForm()))
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

	public Event handleMedical(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
		MemberLegal memberLegal = accountBean.getMemberLegal();

		// format phone for insurance company
		memberLegal.setInsurancePhone(StringUtils.formatPhone(memberLegal.getInsurancePhone()));

		return success();
	}

	public Event handleReleaseWaiver(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
		MemberLegal memberLegal = accountBean.getMemberLegal();

		// force title case for guardian name
		memberLegal.setGuardianName(WordUtils.capitalizeFully(memberLegal.getGuardianName()));

		return success();
	}

}
