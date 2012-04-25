package org.ussa.spring.flows.registration;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.WordUtils;
import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.validation.Assertion;
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
import org.ussa.beans.GlobalRescueBean;
import org.ussa.beans.MembershipsBean;
import org.ussa.beans.MessageBean;
import org.ussa.beans.PaymentBean;
import org.ussa.beans.UserBean;
import org.ussa.bl.CasLdap;
import org.ussa.bl.DateBL;
import org.ussa.bl.RulesBL;
import org.ussa.bl.impl.RulesBLImpl;
import org.ussa.common.dao.UniversalDao;
import org.ussa.dao.AddressDao;
import org.ussa.dao.ClubDao;
import org.ussa.dao.DivisionDao;
import org.ussa.dao.InventoryDao;
import org.ussa.dao.MemberClubDao;
import org.ussa.dao.MemberDao;
import org.ussa.dao.MemberLegalDao;
import org.ussa.dao.MemberSeasonDao;
import org.ussa.dao.NationDao;
import org.ussa.dao.StateDao;
import org.ussa.dao.impl.InventoryAddDaoImpl;
import org.ussa.dao.impl.InventoryDaoImpl;
import org.ussa.exception.CreditCardDeclinedException;
import org.ussa.exception.CreditCardException;
import org.ussa.exception.GlobalRescueException;
import org.ussa.model.Address;
import org.ussa.model.AddressPk;
import org.ussa.model.Club;
import org.ussa.model.Division;
import org.ussa.model.Inventory;
import org.ussa.model.Member;
import org.ussa.model.MemberClub;
import org.ussa.model.MemberLegal;
import org.ussa.model.MemberSeason;
import org.ussa.model.MemberSeasonPk;
import org.ussa.model.ParentInfo;
import org.ussa.service.GlobalRescueService;
import org.ussa.service.MemberRegistrationService;
import org.ussa.util.DateTimeUtils;
import org.ussa.util.StringUtils;

public class RegistrationAction extends FormAction implements Serializable {
 
    private static Log log = LogFactory.getLog(RulesBLImpl.class);
    private static final long serialVersionUID = 1L;
    
    private MemberDao memberDao;
    private AddressDao addressDao;
    private StateDao stateDao;
    private NationDao nationDao;
    private ClubDao clubDao;
    private MemberClubDao memberClubDao;
    private DivisionDao divisionDao;
    private InventoryDao inventoryDao;
    private MemberLegalDao memberLegalDao;
    private MemberSeasonDao memberSeasonDao;
    private RulesBL rulesBL;
    private DateBL dateBL;
    // private UserManager userManager;
    // private SecurityContext securityContext;
    private UniversalDao universalDao;
    private MemberRegistrationService memberRegistrationService;
    private CasLdap casLdap;
    
    private static String DATE_FORMAT = "MM/dd/yyyy";
    private static SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

    public void setNationDao(NationDao nationDao) {
	this.nationDao = nationDao;
    }

    public void setStateDao(StateDao stateDao) {
	this.stateDao = stateDao;
    }

    public void setClubDao(ClubDao clubDao) {
	this.clubDao = clubDao;
    }

    public void setMemberClubDao(MemberClubDao memberClubDao) {
	this.memberClubDao = memberClubDao;
    }

    public void setDivisionDao(DivisionDao divisionDao) {
	this.divisionDao = divisionDao;
    }

    public void setInventoryDao(InventoryDao inventoryDao) {
	this.inventoryDao = inventoryDao;
    }

    public void setMemberLegalDao(MemberLegalDao memberLegalDao) {
	this.memberLegalDao = memberLegalDao;
    }

    public void setMemberSeasonDao(MemberSeasonDao memberSeasonDao) {
	this.memberSeasonDao = memberSeasonDao;
    }

    public void setRulesBL(RulesBL rulesBL) {
	this.rulesBL = rulesBL;
    }

    public void setDateBL(DateBL dateBL) {
	this.dateBL = dateBL;
    }

    public void setMemberDao(MemberDao memberDao) {
	this.memberDao = memberDao;
    }

    public void setAddressDao(AddressDao addressDao) {
	this.addressDao = addressDao;
    }

    /*
     * public void setUserManager(UserManager userManager) { this.userManager = userManager; } public void setSecurityContext(SecurityContext securityContext) { this.securityContext = securityContext; }
     */public void setUniversalDao(UniversalDao universalDao) {
	this.universalDao = universalDao;
    }

    public void setMemberRegistrationService(MemberRegistrationService memberRegistrationService) {
	this.memberRegistrationService = memberRegistrationService;
    }

    public void setCasLdap(CasLdap casLdap) {
	this.casLdap = casLdap;
    }

    private void initExistingAccountBean(AccountBean accountBean, UserBean userBean, Long memberId, boolean isForUpdate) {
	Member member = memberDao.get(memberId);

	// We don't want to set email to registered account just in case
	// it is a different user who is registering them
	// member.setEmail(userBean.getEmail());
	accountBean.setMember(member);

	if (member.getBirthDate() != null) {
	    accountBean.setBirthDate(formatter.format(member.getBirthDate()));
	}

	rulesBL.setParentInfoRequired(accountBean);
	if (member.getParentInfo() == null) {
	    member.setParentInfo(new ParentInfo());
	}

	AddressPk addressPk = new AddressPk();
	addressPk.setType(Address.ADDRESS_TYPE_PRIMARY);
	addressPk.setMember(member);
	Address address = null;
	try {
	    address = addressDao.get(addressPk);
	} catch (ObjectRetrievalFailureException e) {
	    address = new Address();
	}
	accountBean.setAddress(address);

	// MemberLegal

	MemberSeasonPk memberSeasonPk = new MemberSeasonPk();
	memberSeasonPk.setMember(member);
	memberSeasonPk.setSeason(dateBL.getCurrentRenewSeason());

	MemberLegal memberLegal = null;
	try {
	    memberLegal = memberLegalDao.get(memberSeasonPk);
	    accountBean.setHasInsurance(memberLegal.hasInsurance());
	    accountBean.setMemberLegal(memberLegal);
	} catch (ObjectRetrievalFailureException e) {
	    if (isForUpdate) {
		memberLegal = new MemberLegal();
		memberLegal.setMemberSeasonPk(memberSeasonPk);
		accountBean.setMemberLegal(memberLegal);
	    } else {// null out member legal so this person can now renew
		accountBean.setMemberLegal(null);
	    }
	}

	// MemberSeason
	MemberSeason memberSeason = memberSeasonDao.hasMemberSeasonForCurrentSeason(memberSeasonPk);
	accountBean.setMemberSeason(memberSeason);
	
	//TODO ZTS next 2 lines should go away.  LoadContactInfo also initializes GlobalRescueBean
	GlobalRescueBean grb = new GlobalRescueBean(accountBean,rulesBL);
	accountBean.setGlobalRescueBean(grb);
    }

    public Event loadContactInfoForUpdate(RequestContext context) throws Exception {
	
	// This doesn't seem to ever be called; Maybe remove?  Need to verify
	
	
	AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");

	String username = getUsernameFromSession(context);
	
	UserBean userBean = casLdap.getUserInfo(username);

	Long id;
	if (StringUtils.isNotBlank(context.getRequestParameters().get("id"))) {

	    String parameterId = context.getRequestParameters().get("id");

	    if (!casLdap.doesIdBelongToAccount(parameterId, userBean)) {
		BindException errors = new BindException(accountBean, "accountBean");
		errors.reject("errors.bad.ussaid");
		getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope());
		return result("registrationError");
	    }

	    id = Long.parseLong(parameterId);
	} else { // parameter of add=new has to be passed or error message thrown
	    if (StringUtils.isNotBlank(context.getRequestParameters().get("add"))) {
		if ("new".equals(context.getRequestParameters().get("add"))) {
		    id = null;
		} else {
		    BindException errors = new BindException(accountBean, "accountBean");
		    errors.reject("errors.bad.noaccount");
		    getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope());
		    return result("registrationError");
		}
	    } else {
		if (StringUtils.isNotBlank(context.getRequestParameters().get("dupId"))) {
		    id = Long.parseLong(context.getRequestParameters().get("dupId"));
		} else {
		    BindException errors = new BindException(accountBean, "accountBean");
		    errors.reject("errors.bad.noaccount");
		    getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope());
		    return result("registrationError");
		}
	    }
	}

	accountBean.setUsStates(stateDao.getAllUsStatesOrderedByCode());
	accountBean.setAllStates(stateDao.getAllStatesOrderedByCode());

	initExistingAccountBean(accountBean, userBean, id, true);

	return result("updateContactInfo");
    }

    public Event saveMemberInfo(RequestContext context) throws Exception {
	AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
	Calendar now = Calendar.getInstance();
	accountBean.getMember().setModifiedDate(now.getTime());
	accountBean.getAddress().setChangeDate(now.getTime());
	memberDao.save(accountBean.getMember());
	universalDao.save(accountBean.getAddress());
	return result("complete");
    }

    public Event saveMedicalInfo(RequestContext context) throws Exception {
	AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
	MemberLegal memberLegal = accountBean.getMemberLegal();
	MemberSeason memberSeason = new MemberSeason();
	memberSeason.setMemberSeasonPk(new MemberSeasonPk(accountBean.getMember(), dateBL.getCurrentRenewSeason()));

	if (!memberLegal.hasInsurance()) {
	    memberLegal.setInsuranceWaiverDate(new Date());
	    memberSeason.setMedicalException("Y");
	} else {
	    memberSeason.setMedicalException("N");
	}

	memberLegalDao.save(memberLegal);
	// universalDao.save(memberSeason);
	return result("complete");
    }

    public Event loadMedicalInfoForUpdate(RequestContext context) throws Exception {
	loadContactInfoForUpdate(context);
	return result("updateMedicalInfo");
    }

    public Event loadContactInfo(RequestContext context) throws Exception {
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
	
	String username = getUsernameFromSession(context);
	
	UserBean userBean = casLdap.getUserInfo(username);
	accountBean.setUserBean(userBean);

	// USSA Id
	Long id;

	// An id has to be passed if existing member
	if (StringUtils.isNotBlank(context.getRequestParameters().get("id"))) {

	    String parameterId = context.getRequestParameters().get("id");

	    if (!casLdap.doesIdBelongToAccount(parameterId, userBean)) {
		BindException errors = new BindException(accountBean, "accountBean");
		errors.reject("errors.bad.ussaid");
		getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope());
		return result("registrationError");
	    }

	    id = Long.parseLong(parameterId);
	} else { // parameter of add=new has to be passed or error message thrown
	    if (StringUtils.isNotBlank(context.getRequestParameters().get("add"))) {
		if ("new".equals(context.getRequestParameters().get("add"))) {
		    id = null;
		} else {
		    BindException errors = new BindException(accountBean, "accountBean");
		    errors.reject("errors.bad.noaccount");
		    getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope());
		    return result("registrationError");
		}
	    } else {
		if (StringUtils.isNotBlank(context.getRequestParameters().get("dupId"))) {
		    id = Long.parseLong(context.getRequestParameters().get("dupId"));
		} else {
		    BindException errors = new BindException(accountBean, "accountBean");
		    errors.reject("errors.bad.noaccount");
		    getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope());
		    return result("registrationError");
		}
	    }
	}

	String currentSeason = dateBL.getCurrentRenewSeason();

	accountBean.setUsStates(stateDao.getAllUsStatesOrderedByCode());
	accountBean.setAllStates(stateDao.getAllStatesOrderedByCode());

	// Set confirmation email to the account email for authorize.net receipt
	accountBean.setConfirmationEmail(userBean.getEmail());

	accountBean.setCartBean(new CartBean());
	
	GlobalRescueBean grb = new GlobalRescueBean(accountBean,rulesBL);
	accountBean.setGlobalRescueBean(grb);
	

	// new registration
	if (id == null) {
	    // TODO: Check what happens when new is passed but when they fill out info
	    // the system recognizes it...need to add ussa id to account automatically
	    Member member = new Member();
	    accountBean.setMember(member);
	    
	    // If USSA Id is not in primary ussa id field in ldap, pre-populate with ldap account info
	    if (userBean.getUssaId() == null) {
		member.setFirstName(userBean.getFirstName());
		member.setLastName(userBean.getLastName());
		member.setEmail(userBean.getEmail());
		
		if (StringUtils.isNotBlank(userBean.getBirthDate())) {
		    member.setBirthDate(formatter.parse(userBean.getBirthDate()));
		    rulesBL.setParentInfoRequired(accountBean);
		}
	    }
	    
	    member.setParentInfo(new ParentInfo());
	    
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
	else {
	    initExistingAccountBean(accountBean, userBean, id, false);

	    if (rulesBL.isMemberInactive(accountBean.getMember())) {
		BindException errors = new BindException(accountBean, "accountBean");
		errors.reject("errors.inactive");
		getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope());
		return result("registrationError");
	    }

	    if (!rulesBL.isCountryUs(accountBean.getAddress().getCountry())) {
		BindException errors = new BindException(accountBean, "accountBean");
		errors.reject("errors.foreign");
		getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope());
		return result("registrationError");
	    }

	    if (accountBean.getMemberLegal() == null) {
		MemberLegal memberLegal = new MemberLegal();
		memberLegal.setMemberSeasonPk(memberLegal.getMemberSeasonPk());
		accountBean.setMemberLegal(memberLegal);
	    }

	    if (accountBean.getMemberSeason() != null) {
		// the person has already processed a registration for this year.
		BindException errors = new BindException(accountBean, "accountBean");
		errors.reject("errors.already.registered");
		getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope());
		return result("registrationError");
	    }

	    /*
	     * if(accountBean.getMemberLegal() != null) { BindException errors = new BindException(accountBean, "accountBean"); errors.reject("errors.already.registered"); getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope()); return result("registrationError"); } else { MemberLegal memberLegal = new MemberLegal(); memberLegal.setMemberSeasonPk(memberLegal.getMemberSeasonPk()); accountBean.setMemberLegal(memberLegal); }
	     */

	    // prepopulate cart
	    rulesBL.prepopulateCart(accountBean);
	}

	return result("contactInfo");
    }

    public Event handleContactInfo(RequestContext context) throws Exception {
	AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
	Address address = accountBean.getAddress();
	Member member = accountBean.getMember();

	// force all phone numbers to the specified format
	/*
	 * address.setPhoneHome(StringUtils.formatPhone(address.getPhoneHome())); address.setPhoneWork(StringUtils.formatPhone(address.getPhoneWork())); address.setPhoneOther(StringUtils.formatPhone(address.getPhoneOther())); address.setPhoneFax(StringUtils.formatPhone(address.getPhoneFax()));
	 */
	// We use only digits for phone nrs in the db, not like the above format
	address.setPhoneHome(address.getPhoneHome());
	address.setPhoneWork(address.getPhoneWork());
	address.setPhoneOther(address.getPhoneOther());
	address.setPhoneFax(address.getPhoneFax());

	// force postal code to the spcified format
	address.setPostalCode(StringUtils.formatPostalCode(address.getPostalCode()));

	// force title case for the following
	member.setFirstName(WordUtils.capitalizeFully(member.getFirstName()));
	member.setMiddleName(WordUtils.capitalizeFully(member.getMiddleName()));
	member.setLastName(WordUtils.capitalizeFully(member.getLastName()));
	address.setDeliveryAddress(WordUtils.capitalizeFully(address.getDeliveryAddress()));
	address.setCity(WordUtils.capitalizeFully(address.getCity()));

	// force upper case for the country
	address.setCountry(address.getCountry().toUpperCase());

	// They want to force the US the be entered same way for everyone
	if (rulesBL.isCountryUs(address.getCountry())) {
	    address.setCountry("USA");
	}

	// if their address is in the US then their state is required
	if (rulesBL.isCountryUsOrCanada(address.getCountry())) {
	    if (StringUtils.isBlank(address.getStateCode())) {
		BindException errors = new BindException(accountBean, "accountBean");
		errors.reject("errors.state.required.for.us");
		getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope());
		return error();
	    }
	}

	// bind the birthdate and do some additional validation
	String birthDateStr = accountBean.getBirthDate();
	if (StringUtils.isNotBlank(birthDateStr)) {
	    Calendar birthDate = DateTimeUtils.getCalendar(formatter.parse(birthDateStr));
	    Calendar now = Calendar.getInstance();
	    Calendar hundredsBack = DateTimeUtils.getCalendar(now.getTime());
	    hundredsBack.add(Calendar.YEAR, -150);
	    if (birthDate.after(now) || birthDate.before(hundredsBack)) {
		/*
		 * The birthday field is read only if there is any value in the field, the only exception to this is if the field didn't pass validation, so it is important that the birthdate error gets set in the correct way so that the user can edit the incorrect value.
		 */
		BindException errors = new BindException(accountBean, "accountBean");
		ObjectError error = new FieldError("accountBean", "birthDate", accountBean.getBirthDate(), false, new String[] { "errors.invalid.birthdate" }, new Object[] { new DefaultMessageSourceResolvable("label.birth.date") }, "errors.invalid.birthdate");
		errors.addError(error);
		getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope());
		return error();
	    }
	    member.setBirthDate(birthDate.getTime());
	}

	// for new registrations check for dups
	if (member.getId() == null) {
	    List<Member> dups = memberDao.getDuplicateCandidates(member.getLastName(), member.getBirthDate());
	    if (dups != null && !dups.isEmpty()) {
		accountBean.setDuplicateUsers(dups);
		return result("duplicateAccount");
	    } else if (accountBean.getAlreadyAMember() != null && accountBean.getAlreadyAMember()) {
		BindException errors = new BindException(accountBean, "accountBean");
		errors.reject("errors.membership.not.found");
		getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope());
		return result("registrationError");
	    }
	}

	// for new registrations that are under 18 send them to the parent info screen
	rulesBL.setParentInfoRequired(accountBean);
	if (member.getId() == null && accountBean.getParentInfoRequired()) {
	    return result("parentInfo");
	}

	return result("findClubInfo");
    }

    public Event loadClubInfo(RequestContext context) throws Exception {
	AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
	Member member = accountBean.getMember();

	// default US citizenship to 'true' if their nationCode is already USA
	String nationCode = member.getNationCode();
	if (StringUtils.isBlank(nationCode)) {
	    nationCode = rulesBL.isCountryUs(accountBean.getAddress().getCountry()) ? "USA" : accountBean.getAddress().getCountry();
	}
	if (rulesBL.isCountryUs(nationCode)) {
	    accountBean.setUsCitizen(true);
	    member.setNationCode("USA");
	}

	// load nation options
	accountBean.setNations(nationDao.getAllNations());

	// determine the user's state affiliation
	String stateAffiliation = getStateAffiliation(accountBean);
	member.setStateCode(stateAffiliation);

	// determine the user's club
	try {
	    if (member.getId() != null) {
		MemberClub memberClub = memberClubDao.get(member.getId());
		accountBean.setClubId(memberClub.getClubUssaId());
	    }
	} catch (ObjectRetrievalFailureException e) {
	    accountBean.setClubId(0L);
	}

	// determine which clubs to display in the select list based on the state affiliation
	loadClubOptions(accountBean);

	// Division division = rulesBL.determineDivision(nationCode, member.getStateCode(), null, null);
	Division division = rulesBL.determineDivision(nationCode, member.getStateCode(), accountBean.getClubId(), accountBean.getClubZipCode());
	if (division != null) {
	    member.setDivision(division);
	    accountBean.setDivisionCode(division.getDivisionCode());
	    accountBean.setDivisionDescription(division.getDescription());
	} else {
	    accountBean.setClubId(Long.parseLong("-1"));
	}

	return success();
    }

    private void loadClubOptions(AccountBean accountBean) {
	List<Club> clubs = new ArrayList<Club>();
	String stateCode = accountBean.getMember().getStateCode();
	if (StringUtils.isNotEmpty(stateCode)) {
	    clubs = clubDao.findByStateCode(stateCode);
	}
	accountBean.setClubsForState(clubs);
    }

    /**
     * The user's state affiliation is retrieved first from their state code (if available) or from the state code of their address.
     */
    private String getStateAffiliation(AccountBean accountBean) {
	String result = null;
	if (StringUtils.isNotEmpty(accountBean.getMember().getStateCode())) {
	    result = accountBean.getMember().getStateCode();
	} else if (accountBean.getAddress() != null && StringUtils.isNotEmpty(accountBean.getAddress().getStateCode())) {
	    result = accountBean.getAddress().getStateCode();
	}
	return result;
    }

    public Event processClubInfo(RequestContext context) throws Exception {
	AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");

	// if they are foreign then they can't do foreign at this time
	if (!rulesBL.isCountryUs(accountBean.getMember().getNationCode())) {
	    BindException errors = new BindException(accountBean, "accountBean");
	    errors.reject("errors.foreign");
	    getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope());
	    return result("registrationError");
	}

	if (StringUtils.isNotBlank(accountBean.getDivisionCode())) {
	    accountBean.getMember().setDivision(divisionDao.get(accountBean.getDivisionCode()));
	}

	// Incase they just changed their division we need to reasses their div and state dues;
	rulesBL.addRemoveDivisionDuesAndLateFees(accountBean);

	return success();
    }

    public Event loadSportMemberships(RequestContext context) throws Exception {
	AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");

	accountBean.setMemberships(rulesBL.getApplicableSportMemberships(accountBean));
	accountBean.setFisItems(rulesBL.getApplicableFisItems(accountBean));
	accountBean.setMagazineItems(rulesBL.getValidMagazineOptions(accountBean));
		
	//Adding to pop up disciplines for tracking
	accountBean.setValidDisciplines(rulesBL.getValidDisciplines(accountBean.getSportId())); 

	// this is to set the background screening flag to no if they need to do a background check
	if (rulesBL.needsBackgroundCheck(accountBean)) {
	    accountBean.setNeedsBackground(true);
	} else {
	    accountBean.setNeedsBackground(false);
	}

	if (rulesBL.needsFastStartCourse(accountBean)){
		accountBean.setNeedsFastStartCourse(true);
	} else {
		accountBean.setNeedsFastStartCourse(false);
	}
	// this is needed to determine whether or not to show the backgroundScreeningPopup
	HttpServletRequest request = ((ServletExternalContext) context.getExternalContext()).getRequest();
	
	request.getSession().removeAttribute("showBackgroundScreening");
	request.getSession().removeAttribute("showFastStartCourse");
	request.getSession().removeAttribute("showFastStartCourseLink");
	
	
	if (!accountBean.getWasBgScreeningInfoAlreadyShown() && rulesBL.needsBackgroundCheck(accountBean)) {
	    request.getSession().setAttribute("showBackgroundScreening", true);

	    accountBean.setWasBgScreeningInfoAlreadyShown(true);
	}
	
	if (!accountBean.isFastStartCourseInfoAlreadyShown() && rulesBL.needsFastStartCourse(accountBean)){
		log.warn("needs the popup");
		request.getSession().setAttribute("showFastStartCourse", true);
		request.getSession().setAttribute("showFastStartCourseLink", true);
		
		accountBean.setFastStartCourseInfoAlreadyShown(true);
	}

	if(request.getSession().getAttribute("showFastStartCourse") != null && request.getSession().getAttribute("showBackgroundScreening") != null && request.getSession().getAttribute("showFastStartCourse").equals(true) && request.getSession().getAttribute("showBackgroundScreening").equals(true) ){
		request.getSession().setAttribute("showBothWarnings", true);
	}
	
	return success();
    }

    public Event addSportMemberships(RequestContext context) throws Exception {
	AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");

	Inventory membership = inventoryDao.get(accountBean.getMembershipId());
	List<MessageBean> messages = rulesBL.addMembershipToCart(accountBean, membership);

	// Yes, this is a bit of a hack. need to learn more about Spring MVC messages. Hopefully there is a better way to do this.
	HttpServletRequest request = ((ServletExternalContext) context.getExternalContext()).getRequest();
	request.getSession().setAttribute("messages", messages);

	rulesBL.handleFisOptions(accountBean);
	rulesBL.handleMagazineOption(accountBean);
	rulesBL.handleContribution(accountBean);

	return success();
    }

    public Event handleContribution(RequestContext context) throws Exception {
	AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");

	rulesBL.handleFisOptions(accountBean);
	rulesBL.handleMagazineOption(accountBean);
	rulesBL.handleContribution(accountBean);

	return success();
    }

    public Event addGlobalRescue(RequestContext context) throws Exception {
    	AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
    	String addModeParameter = context.getRequestParameters().get("_eventId_add");
    	boolean addMode = (addModeParameter != null);
    	
    	String radiobutton = context.getRequestParameters().get("globalrescueradiobutton");
    	
    	List<Inventory> sponsors = inventoryDao.getIventoryByType(Inventory.INVENTORY_TYPE_SPONSORS);
    	Inventory thisSponsor = null;
    	for(Inventory sponsor : sponsors){
    		if(sponsor.getId().equals(radiobutton)){
    			thisSponsor = sponsor;
    			break;
    		}
    	}
    	
    	GlobalRescueBean grb = accountBean.getGlobalRescueBean();
    	if(thisSponsor != null){
    		//String iAgree = context.getRequestParameters().get("agreetoglobalrescue");
    		
    		if(!grb.getiagree()){
	    		List<MessageBean> messages = new ArrayList<MessageBean>();
	    		MessageBean mb = new MessageBean("error.globalrescue.iagree",null);
	    		messages.add(mb);
				HttpServletRequest request = ((ServletExternalContext) context.getExternalContext()).getRequest();
				request.getSession().setAttribute("messages", messages);
	    		
		    	return error();
	    	}
    		
    		/*
	    	grb.setParent1(grb.new Person(context.getRequestParameters().get("firstname1"),
	    			context.getRequestParameters().get("lastname1"),
	    			context.getRequestParameters().get("month1"),
	    			context.getRequestParameters().get("day1"),
	    			context.getRequestParameters().get("year1"),
	    			"parent1"));
	    			*/
    		grb.getParent1().setBirthMonth(context.getRequestParameters().get("month1"));
    		grb.getParent1().setBirthDay(context.getRequestParameters().get("day1"));
    		grb.getParent1().setBirthYear(context.getRequestParameters().get("year1"));
	    	grb.setParent2(grb.new Person(context.getRequestParameters().get("firstname2"),
	    			context.getRequestParameters().get("lastname2"),
	    			context.getRequestParameters().get("month2"),
	    			context.getRequestParameters().get("day2"),
	    			context.getRequestParameters().get("year2"),
	    			"parent2"));
	    	grb.setPerson(grb.new Person(context.getRequestParameters().get("firstname3"),
	    			context.getRequestParameters().get("lastname3"),
	    			context.getRequestParameters().get("month3"),
	    			context.getRequestParameters().get("day3"),
	    			context.getRequestParameters().get("year3"),
	    			"dependent1"));
	    	grb.setPerson(grb.new Person(context.getRequestParameters().get("firstname4"),
	    			context.getRequestParameters().get("lastname4"),
	    			context.getRequestParameters().get("month4"),
	    			context.getRequestParameters().get("day4"),
	    			context.getRequestParameters().get("year4"),
	    			"dependent2"));
	    	grb.setPerson(grb.new Person(context.getRequestParameters().get("firstname5"),
	    			context.getRequestParameters().get("lastname5"),
	    			context.getRequestParameters().get("month5"),
	    			context.getRequestParameters().get("day5"),
	    			context.getRequestParameters().get("year5"),
	    			"dependent3"));
	    	grb.setPerson(grb.new Person(context.getRequestParameters().get("firstname6"),
	    			context.getRequestParameters().get("lastname6"),
	    			context.getRequestParameters().get("month6"),
	    			context.getRequestParameters().get("day6"),
	    			context.getRequestParameters().get("year6"),
	    			"dependent4"));
	    	
	    	if(Inventory.INV_ID_SPONSORS_FAMILY.equals(thisSponsor.getId()) && !grb.getAre2People()){
	    		List<MessageBean> messages = new ArrayList<MessageBean>();
	    		MessageBean mb = new MessageBean("error.globalrescue.parentform",null);
	    		messages.add(mb);
				HttpServletRequest request = ((ServletExternalContext) context.getExternalContext()).getRequest();
				request.getSession().setAttribute("messages", messages);
	    		
		    	return error();
	    	}
	    	
	    	if(addMode){
		    	accountBean.getCartBean().addItem(thisSponsor);
	    	}
    	
    	}
    	
    	
    	return success();
    }
    
    public Event removeItemFromCart(RequestContext context) throws Exception {
	AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");

	String id = context.getRequestParameters().get("id");

	rulesBL.removeItemFromCart(accountBean, id);

	return result("back");
    }

    public Event needsFisWaiver(RequestContext context) throws Exception {
	AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
	if (rulesBL.hasFis(accountBean, false) && !"Y".equals(accountBean.getMemberLegal().getFisReleaseForm())) {
	    return result("true");
	} else {
	    return result("false");
	}
    }

    public Event handleFisWaiverResponse(RequestContext context) throws Exception {
	AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
	if ("N".equals(accountBean.getMemberLegal().getFisReleaseForm())) {
	    rulesBL.removeFisFromCart(accountBean, false);
	}

	return success();
    }

    public Event needsFisWaiverDisabled(RequestContext context) throws Exception {
	AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
	if (rulesBL.hasFis(accountBean, true) && !"Y".equals(accountBean.getMemberLegal().getIpcReleaseForm())) {
	    return result("true");
	} else {
	    return result("false");
	}
    }

    public Event handleFisWaiverDisabledResponse(RequestContext context) throws Exception {
	AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
	if ("N".equals(accountBean.getMemberLegal().getIpcReleaseForm())) {
	    rulesBL.removeFisFromCart(accountBean, true);
	}

	return success();
    }

    public Event addExtras(RequestContext context) throws Exception {
	AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
	ExtrasBean extrasBean = accountBean.getExtrasBean();

	// Bonus Packs available to U.S. residents only
	if (rulesBL.isCountryUs(accountBean.getAddress().getCountry())) {
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

	} else {// Foreign Address
	    // Only display error message if they have actually tried to pick a bonus pack
	    if (StringUtils.isNotBlank(extrasBean.getAlpineQty()) || StringUtils.isNotBlank(extrasBean.getFreestyleQty()) || StringUtils.isNotBlank(extrasBean.getCrossCountryQty()) || StringUtils.isNotBlank(extrasBean.getJumpingQty()) || StringUtils.isNotBlank(extrasBean.getBoardingQty()) || StringUtils.isNotBlank(extrasBean.getGeneralQty()) || StringUtils.isNotBlank(extrasBean.getDecalSkiQty()) || StringUtils.isNotBlank(extrasBean.getDecalBoardQty())) {
		BindException errors = new BindException(accountBean, "accountBean");
		errors.reject("error.foreign.bonuspack");
		getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope());
	    }

	    // return success instead of error so that it moves to the next page automatically
	    return success();
	}

    }

    private void handleOption(CartBean cartBean, String invId, String qty) {
	if (StringUtils.isNotBlank(invId)) {
	    Inventory inventory = inventoryDao.get(invId);
	    if (StringUtils.isNotBlank(qty)) {
		cartBean.addItem(inventory, new Integer(qty));
	    }
	}
    }

    public Event handleMedical(RequestContext context) throws Exception {
	AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
	MemberLegal memberLegal = accountBean.getMemberLegal();

	// format phone for insurance company
	memberLegal.setInsurancePhone(memberLegal.getInsurancePhone());

	return success();
    }

    public Event handleReleaseWaiver(RequestContext context) throws Exception {
	AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
	MemberLegal memberLegal = accountBean.getMemberLegal();

	// force title case for guardian name
	memberLegal.setGuardianName(WordUtils.capitalizeFully(memberLegal.getGuardianName()));

	return success();
    }
    
    //Adding new concussion waiver - April 2011
    public Event handleConcussionWaiver(RequestContext context) throws Exception {
    	AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
    	MemberLegal memberLegal = accountBean.getMemberLegal();

    	// force title case for guardian name
    	memberLegal.setConcussionGuardianName(WordUtils.capitalizeFully(memberLegal.getConcussionGuardianName()));

    	return success();
        }

    //Adding new safe sport waiver - April 2012
    public Event handleSafeSportWaiver(RequestContext context) throws Exception {
    	AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
    	MemberLegal memberLegal = accountBean.getMemberLegal();

    	// force title case for guardian name
    	memberLegal.setSafeSportGuardianName(WordUtils.capitalizeFully(memberLegal.getSafeSportGuardianName()));

    	return success();
        }

    public Event loadPayment(RequestContext context) throws Exception {
	AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");

	List<Integer> years = new ArrayList<Integer>();
	Calendar cal = Calendar.getInstance();
	for (int i = 0; i < 10; i++) {
	    years.add(cal.get(Calendar.YEAR));
	    cal.add(Calendar.YEAR, 1);
	}

	accountBean.setYears(years);

	return success();
    }

    public Event processOrder(RequestContext context) throws Exception {
	AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");
	Address address = accountBean.getAddress();
	PaymentBean paymentBean = accountBean.getPaymentBean();

	try {
	    if (org.apache.commons.lang.StringUtils.isBlank(paymentBean.getAddress())) {
		paymentBean.setAddress(address.getDeliveryAddress());
	    }
	    if (org.apache.commons.lang.StringUtils.isBlank(paymentBean.getZip())) {
		paymentBean.setZip(address.getPostalCode());
	    }
	    
	    // Make sure it isn't an AMEX or Discover Card
	    String firstNumber = paymentBean.getCardNumber().substring(0, 1);
	    if("3".equals(firstNumber) || "6".equals(firstNumber)){
	    	BindException errors = new BindException(accountBean, "accountBean");
	 	    errors.reject("errors.card.invalid.type");
	 	    getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope());
	 	    return error();
	    }

	    memberRegistrationService.processRegistration(accountBean);
	    if(accountBean.getGlobalRescueBean().getIsInCart()){
		    GlobalRescueService grs = new GlobalRescueService();
		    grs.createPrepaidAccount(accountBean);
	    }
	} catch (CreditCardDeclinedException e) {
	    BindException errors = new BindException(accountBean, "accountBean");
	    errors.reject("errors.card.declined");
	    getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope());
	    return error();
	} catch (CreditCardException e) {
	    BindException errors = new BindException(accountBean, "accountBean");
	    errors.reject("errors.card.not.approved");
	    getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope());
	    return error();
	} catch (GlobalRescueException gre){
		List<String> details = new ArrayList<String>();
		details.add(gre.getMessage());
		accountBean.getGlobalRescueBean().setMessages(details);
		/*
	    BindException errors = new BindException(accountBean, "accountBean");
	    errors.reject("errors.globalrescue.createaccount");
	    getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope());
	    return error();
	    */
		
	}
	catch (Exception e) {
	    e.printStackTrace();
	    BindException errors = new BindException(accountBean, "accountBean");
	    errors.reject("errors.problem.registering.user");
	    getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope());
	    return error();
	}

	return success();
    }

    public Event loadRegistrationComplete(RequestContext context) throws Exception {
	AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");

	MembershipsBean membershipsBean = rulesBL.getPurchasedMemberships(accountBean.getMember().getId());
	accountBean.setMembershipsBean(membershipsBean);

	// Add groups to account
	List<String> memberGroups = new ArrayList<String>();
	// Add current member to account - Added already in CasLdapImpl

	// Add club group to account
	// memberGroups.add(String.valueOf(accountBean.getClubId()));
	// casLdap.addGroupsToUser(accountBean.getUserBean().getUsername(), memberGroups);
	return success();
    }
    
    private String getUsernameFromSession(RequestContext context) throws Exception{
	ServletExternalContext externalContext = (ServletExternalContext)context.getExternalContext();
	
	// Either of these will work
	//return externalContext.getRequest().getRemoteUser();
	return externalContext.getRequest().getUserPrincipal().getName();
    }

}
