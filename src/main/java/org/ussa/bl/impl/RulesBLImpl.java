package org.ussa.bl.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ussa.beans.AccountBean;
import org.ussa.beans.CartBean;
import org.ussa.beans.LineItemBean;
import org.ussa.beans.MessageBean;
import org.ussa.beans.MembershipsBean;
import org.ussa.bl.DateBL;
import org.ussa.bl.RuleAssociations;
import org.ussa.bl.RulesBL;
import org.ussa.dao.ClubDao;
import org.ussa.dao.DisciplineTrackingDao;
import org.ussa.dao.DivDuesRulesDao;
import org.ussa.dao.DivisionAffiliationDao;
import org.ussa.dao.DivisionDao;
import org.ussa.dao.InventoryDao;
import org.ussa.dao.MemberSeasonDao;
import org.ussa.dao.MemberTransactionDao;
import org.ussa.dao.StateDuesRulesDao;
import org.ussa.dao.RenewRuleInvDao;
import org.ussa.model.Address;
import org.ussa.model.Club;
import org.ussa.model.Division;
import org.ussa.model.DivisionAffiliation;
import org.ussa.model.Inventory;
import org.ussa.model.Member;
import org.ussa.model.MemberSeason;
import org.ussa.model.MemberTransaction;
import org.ussa.util.DateTimeUtils;
import org.ussa.dao.CoachesEducationDao;

public class RulesBLImpl implements RulesBL {
    private static Log log = LogFactory.getLog(RulesBLImpl.class);

    private InventoryDao inventoryDao;
    private RenewRuleInvDao renewRuleInvDao;
    private DateBL dateBL;
    private DivDuesRulesDao divDuesRulesDao;
    private StateDuesRulesDao stateDuesRulesDao;
    private MemberTransactionDao memberTransactionDao;
    private MemberSeasonDao memberSeasonDao;
    private ClubDao clubDao;
    private DivisionDao divisionDao;
    private DivisionAffiliationDao divisionAffiliationDao;
    private DisciplineTrackingDao disciplineTrackingDao;
    private CoachesEducationDao coachesEducationDao;
    
    private static String DATE_FORMAT = "MM/dd/yyyy";
    private static SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

    public void setInventoryDao(InventoryDao inventoryDao) {
	this.inventoryDao = inventoryDao;
    }

    public void setRenewRuleInvDao(RenewRuleInvDao renewRuleInvDao) {
	this.renewRuleInvDao = renewRuleInvDao;
    }

    public void setDateBL(DateBL dateBL) {
	this.dateBL = dateBL;
    }

    public void setDivDuesRulesDao(DivDuesRulesDao divDuesRulesDao) {
	this.divDuesRulesDao = divDuesRulesDao;
    }

    public void setStateDuesRulesDao(StateDuesRulesDao stateDuesRulesDao) {
	this.stateDuesRulesDao = stateDuesRulesDao;
    }

    public void setMemberTransactionDao(MemberTransactionDao memberTransactionDao) {
	this.memberTransactionDao = memberTransactionDao;
    }

    public void setMemberSeasonDao(MemberSeasonDao memberSeasonDao) {
	this.memberSeasonDao = memberSeasonDao;
    }

    public void setClubDao(ClubDao clubDao) {
	this.clubDao = clubDao;
    }

    public void setDivisionDao(DivisionDao divisionDao) {
	this.divisionDao = divisionDao;
    }
    
    public void setcoachesEducationDao(CoachesEducationDao coachesEducationDao){
    	this.coachesEducationDao = coachesEducationDao;
    }

    public void setDisciplineTrackingDao(DisciplineTrackingDao disciplineTrackingDao) {

	this.disciplineTrackingDao = disciplineTrackingDao;
    }

    public void setDivisionAffiliationDao(DivisionAffiliationDao divisionAffiliationDao) {
	this.divisionAffiliationDao = divisionAffiliationDao;
    }

    public Integer getAgeForCurrentRenewSeason(Date birthDate) {
	if (birthDate != null) {
	    Calendar bDate = Calendar.getInstance();
	    bDate.setTime(birthDate);
	    int birthDateYear = bDate.get(Calendar.YEAR);
	    int currentRenewSeason = dateBL.calculateCurrentRenewSeason();

	    return currentRenewSeason - (birthDateYear + 1);
	}

	return 0;
    }

    public void setParentInfoRequired(AccountBean accountBean) {
	Date birthdate = accountBean.getMember().getBirthDate();
	if (birthdate != null) {
	    Integer currentSeasonAge = getAgeForCurrentRenewSeason(accountBean.getMember().getBirthDate());
	    if (currentSeasonAge < 18) {
		accountBean.setParentInfoRequired(true);
		return;
	    }
	}

	accountBean.setParentInfoRequired(false);
    }

    public void prepopulateCart(AccountBean accountBean) {
	Member member = accountBean.getMember();

	// don't prepopulate the cart for non members
	if (!Member.MEMBER_TYPE_NON_MEMBER.equals(member.getType())) {
	    // for renewals prepopulate the cart with the recommended membership options
		String hasAbilityMembership = "N";
	    String lastSeason = dateBL.getLastSeason();
	    Integer currentSeasonAge = getAgeForCurrentRenewSeason(member.getBirthDate());
	    List<Inventory> recommendedMemberships = renewRuleInvDao.getRecommendedMemberships(member.getId(), currentSeasonAge, lastSeason);
	    for (Inventory inventory : recommendedMemberships) {
	    	//Outdated memberships
	    	if(Inventory.SPORT_CODE_DAL.equals(inventory.getSportCode()) || Inventory.SPORT_CODE_DXC.equals(inventory.getSportCode())){
	    		hasAbilityMembership = "Y";
	    	}
	    }
	    for (Inventory inventory : recommendedMemberships) {	
	    	//if(!Inventory.INV_ID_ALPINE_STUDENT.equals(inventory.getId()) && !Inventory.SPORT_CODE_DAL.equals(inventory.getSportCode()) && !Inventory.SPORT_CODE_DXC.equals(inventory.getSportCode())){
	    	if(!Inventory.INV_ID_ALPINE_STUDENT.equals(inventory.getId()) && hasAbilityMembership.equals("N")){
	    		addMembershipToCart(accountBean, inventory);
	    	}
	    }
	}
    }

    public List<Inventory> getApplicableSportMemberships(AccountBean accountBean) {
	CartBean cart = accountBean.getCartBean();
	List<Inventory> memberships = new ArrayList<Inventory>();
	if (StringUtils.isNotBlank(accountBean.getSportId())) {
	    int age = getAgeForCurrentRenewSeason(accountBean.getMember().getBirthDate());
	    memberships = inventoryDao.getAllMembershipsByCriteria(age, accountBean.getSportId());

	    // filter memberships
	    for (Iterator<Inventory> iterator = memberships.iterator(); iterator.hasNext();) {
		Inventory inventory = iterator.next();
		if (cart.contains(inventory.getId()) || inventoryIsRestricted(accountBean, inventory, age)) {
		    iterator.remove();
		}
	    }
	}

	return memberships;
    }

    private boolean inventoryIsRestricted(AccountBean accountBean, Inventory inventory, Integer age) {
	CartBean cartBean = accountBean.getCartBean();
	String invIdAdding = inventory.getId();

	// For memberships that you want hidden from the users, add them to RuleAssociations.restrictedMemberships.
	if (RuleAssociations.restrictedMemberships.contains(invIdAdding)) {
	    return true;
	}

	if (age != null && ((inventory.getAgeFrom() != null && age < inventory.getAgeFrom()) || (inventory.getAgeTo() != null && age > inventory.getAgeTo()))) {
	    return true;
	}

	// If a coach membership is already selected, user may not add an official membership (it�s already included).
	Set<String> coachInvIds = RuleAssociations.coachesByOfficial.get(invIdAdding);
	if (coachInvIds != null && cartBean.containsAny(coachInvIds)) {
	    return true;
	}

	// If a member holds an Alpine Competitor or Disabled Alpine Competior membership and is 18 or over, they may not add an Alpine Master membership.
	//if (invIdAdding.equals(Inventory.INV_ID_ALPINE_MASTER) && (cartBean.contains(Inventory.INV_ID_ALPINE_COMPETITOR_U16) || cartBean.contains(Inventory.INV_ID_DISABLED_ALPINE_COMPETITOR)) && (age != null && age >= 18)) {
	//    return true;
	//}

	// If a member holds and Alpine Non-Scored Student memberhip and is between 18-22 years old they may not add an Alpine Masters membership.
	if (invIdAdding.equals(Inventory.INV_ID_ALPINE_MASTER) && cartBean.contains(Inventory.INV_ID_ALPINE_STUDENT) && (age != null && age >= 18 && age <= 22)) {
	    return true;
	}
	
	// Can't be a freestyle rookie if you have ever been a freestyle competitor
	if (invIdAdding.equals(Inventory.INV_ID_FREESTYLE_ROOKIE) && memberTransactionDao.hasEverHeldIventoryInSport(accountBean.getMember().getId(), Inventory.SPORT_CODE_FRE)) {
	    return true;
	}

	return false;
    }

    public List<Inventory> getApplicableFisItems(AccountBean accountBean) {
	CartBean cart = accountBean.getCartBean();
	List<LineItemBean> lineItems = cart.getLineItems();
	List<String> fisInvIds = new ArrayList<String>();
	for (LineItemBean lineItem : lineItems) {
	    String[] fises = RuleAssociations.fisByMembership.get(lineItem.getInventory().getId());
	    if (fises != null && (accountBean.getUsCitizen()!= null && accountBean.getUsCitizen())) {
		for (String fis : fises) {
		    if (!fisInvIds.contains(fis)) {
			fisInvIds.add(fis);
		    }
		}
	    }
	}

	Integer age = getAgeForCurrentRenewSeason(accountBean.getMember().getBirthDate());

	List<Inventory> fisItems = new ArrayList<Inventory>();
	for (String fisInvId : fisInvIds) {
	    String lateFisInvId = getLateFisInvId(fisInvId);
	    if (lateFisInvId != null) {
		fisInvId = lateFisInvId;
	    }
	    Inventory fisItem = inventoryDao.get(fisInvId);
	    if (!inventoryIsRestricted(accountBean, fisItem, age)) {
		fisItems.add(fisItem);
	    }
	}

	return fisItems;
    }

    private String getLateFisInvId(String fisInvId) {
	String lateInvId = null;
	Date now = new Date();
	if (Inventory.INV_ID_ALPINE_FIS.equals(fisInvId) && now.after(dateBL.getAlpineFisLateDate())) {
	    lateInvId = Inventory.INV_ID_LATE_ALPINE_FIS;
	//} else if (Inventory.INV_ID_ALPINE_SKIING_DISABLED_LICENSE_FIS.equals(fisInvId) && now.after(dateBL.getIpcAsLateDate())) {
	//    lateInvId = Inventory.INV_ID_LATE_ALPINE_SKIING_DISABLED_LICENSE_FIS;
	} else if (Inventory.INV_ID_CROSS_COUNTRY_FIS.equals(fisInvId) && now.after(dateBL.getCrossCountryFisLateDate())) {
	    lateInvId = Inventory.INV_ID_LATE_CROSS_COUNTRY_FIS;
	} else if (Inventory.INV_ID_FREESTYLE_FIS.equals(fisInvId) && now.after(dateBL.getFreestyleFisLateDate())) {
	    lateInvId = Inventory.INV_ID_LATE_FREESTYLE_FIS;
	}

	if (lateInvId != null) {
	    return lateInvId;
	} else {
	    return null;
	}
    }

    public List<String> getValidDisciplines(String sportCode) {

	if(StringUtils.isBlank(sportCode))
	    return null;
	
	List<String> validDisciplines = new ArrayList<String>();
	validDisciplines = disciplineTrackingDao.getDisciplines(sportCode);

	return validDisciplines;
    }

    public List<Inventory> getValidMagazineOptions(AccountBean accountBean) {
    Boolean freMembership = false;
	Address member = accountBean.getAddress();
	CartBean cart = accountBean.getCartBean();
	List<Inventory> magazineItems = new ArrayList<Inventory>();

	// only give magazines to people with addresses in the US
	if (isCountryUs(member.getCountry())) {
	    List<String> magazineInvIds = new ArrayList<String>();
	    List<LineItemBean> items = cart.getLineItems(Inventory.INVENTORY_TYPE_MEMBERSHIP);
	    if (items.size() > 0) {
		 if(hasOnlyNonCompetingMemberships(accountBean))
		 {
		 //magazineInvIds.add(Inventory.INV_ID_SKI_RACING_MAGAZINE);
		 }
		 else
		 {
		
		//if(cart.contains(Inventory.INV_ID_FREESKIING_COMPETITOR)){
	    	for (LineItemBean lineItem : items) {
	    		if(lineItem.getInventory().getSportCode().equalsIgnoreCase("FRE")){
	    			freMembership = true;
	    		}
	    	}
	    	//List<LineItemBean> freestyleMembership = cart.getLineItems(Inventory.SPORT_CODE_FRE);
			if(freMembership) {
			
			//magazineInvIds.add(Inventory.INV_ID_SKI_RACING_MAGAZINE);
			magazineInvIds.add(Inventory.INV_ID_SNOWBOARD_MAGAZINE);
			magazineInvIds.add(Inventory.INV_ID_SKI_TRAX_MAGAZINE);
			magazineInvIds.add(Inventory.INV_ID_FREESKIING_MAGAZINE);
		}
		else{
			//magazineInvIds.add(Inventory.INV_ID_SKI_RACING_MAGAZINE);
			magazineInvIds.add(Inventory.INV_ID_SNOWBOARD_MAGAZINE);
			magazineInvIds.add(Inventory.INV_ID_SKI_TRAX_MAGAZINE);
		}
		
		 }
	    }

	    Integer age = getAgeForCurrentRenewSeason(accountBean.getMember().getBirthDate());

	    for (String magazineInvId : magazineInvIds) {
		Inventory magazineInventory = inventoryDao.get(magazineInvId);
		if (!inventoryIsRestricted(accountBean, magazineInventory, age)) {
		    magazineItems.add(magazineInventory);
		}
	    }
	}

	return magazineItems;
    }
    
    private boolean hasOnlyNonCompetingMemberships(AccountBean accountBean) {
    	CartBean cart = accountBean.getCartBean();
    	List<LineItemBean> membershipItems = cart.getLineItems(Inventory.INVENTORY_TYPE_MEMBERSHIP);
    	if (membershipItems.size() > 0) {
    	    for (LineItemBean lineItem : membershipItems) {
    		if (!RuleAssociations.nonCompetingMemberships.contains(lineItem.getInventory().getId()) && !RuleAssociations.volunteerMemberships.contains(lineItem.getInventory().getId())) {
    		    return false;
    		}
    	    }
    	} else {
    	    return false;
    	}
    	
    	return true;
    }
    private boolean hasOnlyShortTermMemberships(AccountBean accountBean) {
    	CartBean cart = accountBean.getCartBean();
    	List<LineItemBean> membershipItems = cart.getLineItems(Inventory.INVENTORY_TYPE_MEMBERSHIP);
    	if (membershipItems.size() > 0) {
    	    for (LineItemBean lineItem : membershipItems) {
    		if (!RuleAssociations.shortTermMemberships.contains(lineItem.getInventory().getId())) {
    		    return false;
    		}
    	    }
    	} else {
    	    return false;
    	}
    	
    	return true;
    }
    
    private boolean hasOnlyYouthMemberships(AccountBean accountBean) {
	CartBean cart = accountBean.getCartBean();
	List<LineItemBean> membershipItems = cart.getLineItems(Inventory.INVENTORY_TYPE_MEMBERSHIP);
	if (membershipItems.size() > 0) {
	    for (LineItemBean lineItem : membershipItems) {
		if (!RuleAssociations.youthMemberships.contains(lineItem.getInventory().getId())) {
		    return false;
		}
	    }
	} else {
	    return false;
	}

	return true;
    }

    private boolean hasOnlyOfficialMemberships(AccountBean accountBean) {
	CartBean cart = accountBean.getCartBean();
	List<LineItemBean> membershipItems = cart.getLineItems(Inventory.INVENTORY_TYPE_MEMBERSHIP);
	if (membershipItems.size() > 0) {
	    for (LineItemBean lineItem : membershipItems) {
		if (!RuleAssociations.officialMemberships.contains(lineItem.getInventory().getId())) {
		    return false;
		}
	    }
	} else {
	    return false;
	}

	return true;
    }

    public List<MessageBean> addMembershipToCart(AccountBean accountBean, Inventory inventory){
	CartBean cartBean = accountBean.getCartBean();
	List<MessageBean> messages = new ArrayList<MessageBean>();

	if (!inventoryIsRestricted(accountBean, inventory, null) && !cartBean.contains(inventory.getId())) {
	    int age = getAgeForCurrentRenewSeason(accountBean.getMember().getBirthDate());

	    /*
	     * FIRST DO DATA SCRUBBING
	     */

	    // remove any mutually exclusive memberships
	    Set<String> excludedInvIds = RuleAssociations.mutuallyExclusiveMemberships.get(inventory.getId());
	    if (excludedInvIds != null) {
		for (String excludedInvId : excludedInvIds) {
		    if (cartBean.contains(excludedInvId)) {
			LineItemBean lineItem = cartBean.getLineItem(excludedInvId);
			messages.add(new MessageBean("messages.mutually.exclusive", lineItem.getInventory().getRenewDescription(), inventory.getRenewDescription()));
			cartBean.removeLineItem(excludedInvId);
			//added to remove fast start coaching course if coach membership removed
			if(RuleAssociations.coachMemberships.contains(excludedInvId)){
				if(cartBean.contains(Inventory.INV_ID_CLINIC_FAST_START_COACHING)){
					cartBean.removeLineItem(Inventory.INV_ID_CLINIC_FAST_START_COACHING);
				}
				if(cartBean.contains(Inventory.INV_ID_CLINIC_FAST_START_COACHING_REF)){
					cartBean.removeLineItem(Inventory.INV_ID_CLINIC_FAST_START_COACHING_REF);
				}
			}
		    }
		}
	    }

	    // If adding a coach and a corresponding official is already in the cart then the coach replaces the official
	    Set<String> officialInvIds = RuleAssociations.officialsByCoach.get(inventory.getId());
	    if (officialInvIds != null && cartBean.containsAny(officialInvIds)) {
		for (String officialInvId : officialInvIds) {
		    LineItemBean lineItem = cartBean.getLineItem(officialInvId);
		    messages.add(new MessageBean("messages.membership.includes.another", lineItem.getInventory().getRenewDescription(), inventory.getRenewDescription()));
		    cartBean.removeLineItem(officialInvId);
		}
	    }
	  //If a member is trying to add Coaches Membership to the cart then check if they need to add Fast Start Coaching Course
	  //  Set<String> coachInvIds = RuleAssociations.coachMemberships;
	 //   if(!cartBean.contains(Inventory.INV_ID_CLINIC_FAST_START_COACHING) && accountBean.isNeedsFastStartCourse()){
	 //   	cartBean.addItem(inventoryDao.get(Inventory.INV_ID_CLINIC_FAST_START_COACHING));
	//	    messages.add(new MessageBean("messages.membership.mandatoryCoachesCourse", "Fast Start Coaching Course "));

	 //   }


	    // If a member has an alpine master in their cart and is 18 or over, they must still have the option of adding the alpine competitor or disabled alpine competitor. If one of these are chosen, the alpine master must be removed.
	    //if (cartBean.contains(Inventory.INV_ID_ALPINE_MASTER) && age >= 18 && (Inventory.INV_ID_ALPINE_COMPETITOR_U16.equals(inventory.getId()) || Inventory.INV_ID_DISABLED_ALPINE_COMPETITOR.equals(inventory.getId()))) {
		//LineItemBean lineItem = cartBean.getLineItem(Inventory.INV_ID_ALPINE_MASTER);
		//messages.add(new MessageBean("messages.mutually.exclusive", lineItem.getInventory().getRenewDescription(), inventory.getRenewDescription()));
		//cartBean.removeLineItem(Inventory.INV_ID_ALPINE_MASTER);
	    //}

	    // If a member has an alpine master in their cart and is between 18 and 22 yrs old, they must still have the option of adding the alpine non-scored student. If the non-scored student membership is chosen, the alpine master must be removed.
	    if (cartBean.contains(Inventory.INV_ID_ALPINE_MASTER) && age >= 18 && age <= 22 && (Inventory.INV_ID_ALPINE_STUDENT.equals(inventory.getId()))) {
		LineItemBean lineItem = cartBean.getLineItem(Inventory.INV_ID_ALPINE_MASTER);
		messages.add(new MessageBean("messages.mutually.exclusive", lineItem.getInventory().getRenewDescription(), inventory.getRenewDescription()));
		cartBean.removeLineItem(Inventory.INV_ID_ALPINE_MASTER);
	    }

	 // If a member is trying to add a Alpine Competitor membership and is between 12 and 13 yrs old, should be advised that they only need it if doing scored races
	    if ((Inventory.INV_ID_ALPINE_COMPETITOR.equals(inventory.getId())) && age >= 12 && age <= 13 ) {
		messages.add(new MessageBean("messages.membership.competitor.youth", inventory.getRenewDescription()));
	    }
	    
	 // If a member is trying to add a Alpine Youth membership and is between 12 and 13 yrs old, should be advised that they need Alpine Competitor if doing scored races
	 //   if ((Inventory.INV_ID_ALPINE_YOUTH.equals(inventory.getId())) && age >= 12 && age <= 13 ) {
	//	messages.add(new MessageBean("messages.membership.youth.competitor", inventory.getRenewDescription()));
	 //   }
	    
	
	    /*
	     * THEN ADD ITEM TO CART
	     */
	    if(inventory.getId().startsWith("ST")){
	    	Calendar startDate = Calendar.getInstance();
	    	Calendar endDate = Calendar.getInstance();
			//startDate.setTime(accountBean.getMembershipFrom());
			//endDate.setTime(accountBean.getMembershipTo());
	    	Date begDate = new Date();
	    	Date finishDate = new Date();
	    	if(accountBean.getMembershipFrom() == null || accountBean.getMembershipTo() == null){
	    		messages.add(new MessageBean("messages.membership.competitor.shortterm", inventory.getRenewDescription()));
	    	}
	    	else{
	    		try {
					begDate = formatter.parse(accountBean.getMembershipFrom());
					finishDate = formatter.parse(accountBean.getMembershipTo());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	
	    	
	    	startDate.setTime(begDate);
	    	endDate.setTime(finishDate);
	    	
	    	long daysBetween = 0; 
	    	
	    	while (startDate.before(endDate)) {  
	    	    startDate.add(Calendar.DAY_OF_MONTH, 1);  
	    	    daysBetween++;  
	    	  }  
	    	Integer qty = (int)daysBetween +1;

	    	//cartBean.addItem(inventory, qty,  accountBean.getMembershipFrom(), accountBean.getMembershipTo());
	    	cartBean.addItem(inventory, qty,  begDate, finishDate);
	    	}
	    }
	    else{
	    	cartBean.addItem(inventory);
	    }
	    

	    
	    /*
	     * THEN APPLY DISCOUNTS AND ADD FEES
	     */
	    addRemoveSecondMembershipDiscounts(accountBean);
	    addRemoveDivisionDuesAndLateFees(accountBean);

	    /* MAKE SURE FIS IS BEING TAKEN OFF IF NECESSARY */

	    resetFisOptions(accountBean);
	}

	return messages;
    }

    private void addRemoveSecondMembershipDiscounts(AccountBean accountBean) {
	CartBean cart = accountBean.getCartBean();

	List<LineItemBean> memberships = cart.getLineItems(Inventory.INVENTORY_TYPE_MEMBERSHIP);

	LineItemBean fullPriceMembership = chooseTheFullPriceMembership(memberships);

	// If you purchase more than one membership then subsequent ones should be discounted $35 or $25
	for (LineItemBean membership : memberships) {
	    String invId = membership.getInventory().getId();
	    
	    //if (RuleAssociations.nonCompetingMemberships.contains(invId) || RuleAssociations.shortTermMemberships.contains(invId) || fullPriceMembership.getInventory().getId().equals(invId)){	    	
	    if (RuleAssociations.shortTermMemberships.contains(invId) || fullPriceMembership.getInventory().getId().equals(invId)){	    		    		
	    	// This is your full price membership. The rest are discounted.
	    	membership.setDiscount(null);
	    } else {	    
		// If you are adding or have a membership from the 25 dollar group then the discount is 25 otherwise 35.
	   // if (RuleAssociations.twentyFiveDollarDiscountGroup.contains(invId)) {
		    //membership.setDiscount(new BigDecimal(25));
		    membership.setDiscount(membership.getAmount());
		//} else {
		//    membership.setDiscount(new BigDecimal(35));
		//}
	    }
	    
	}
    }

    /**
     * this tries to pick the first membership from the 35 dollar discount group if there is one, otherwise it returns the first match in the 25 dollar discount group
     * 
     * @param memberships
     *            all of the memberships in the cart.
     * @return the membership that will be full price.
     */
    private LineItemBean chooseTheFullPriceMembership(List<LineItemBean> memberships) {
	LineItemBean firstFrom25DollarDiscoutGroup = null;
	Double fullAmount = 0.0;
	for (LineItemBean membership : memberships) {
	    String invId = membership.getInventory().getId();
	    Double membershipAmount = membership.getAmount().doubleValue();
	    
	    // as soon as we find a membership from the 35 dollar discount group, return it and we are done.
	    //if (!RuleAssociations.nonCompetingMemberships.contains(invId) && !RuleAssociations.twentyFiveDollarDiscountGroup.contains(invId) ) {
	    //	return membership;
	    //} else if (firstFrom25DollarDiscoutGroup == null) {
	    //	firstFrom25DollarDiscoutGroup = membership;
	    //}
	    if(membershipAmount > fullAmount){
	    	fullAmount = membershipAmount;
	    	firstFrom25DollarDiscoutGroup = membership;
	    }
	    
	}
	return firstFrom25DollarDiscoutGroup;
    }

    public void removeItemFromCart(AccountBean accountBean, String invId) {
	CartBean cart = accountBean.getCartBean();

	LineItemBean lineItem = cart.getLineItem(invId);
	if (lineItem != null) {
	    if (Inventory.INVENTORY_TYPE_DONATION.equals(lineItem.getInventory().getInventoryType())) {
		accountBean.setContributionAmount(null);
	    }
	    if(lineItem.getInventory().getId().startsWith("ST")){
	    accountBean.setMembershipFrom(null);
	    accountBean.setMembershipTo(null);
	    }
	    cart.removeLineItem(invId);
	}

	String[] fises = RuleAssociations.fisByMembership.get(invId);
	if (fises != null) {
	    for (String fis : fises) {
		cart.removeLineItem(fis);
	    }
	}

	if(RuleAssociations.coachMemberships.contains(invId)){
		if(cart.contains(Inventory.INV_ID_CLINIC_FAST_START_COACHING)){
			cart.removeLineItem(Inventory.INV_ID_CLINIC_FAST_START_COACHING);
		}
		if(cart.contains(Inventory.INV_ID_CLINIC_FAST_START_COACHING_REF)){
			cart.removeLineItem(Inventory.INV_ID_CLINIC_FAST_START_COACHING_REF);
		}
	}
	
	resetFisOptions(accountBean);
	resetMagazineOption(accountBean);

	addRemoveSecondMembershipDiscounts(accountBean);
	addRemoveDivisionDuesAndLateFees(accountBean);
    }

    public void handleFisOptions(AccountBean accountBean) {
	// convert String array to Set
	Set<String> fisOptions = new HashSet<String>();
	if (accountBean.getFisOptions() != null) {
	    for (String fisOption : accountBean.getFisOptions()) {
		fisOptions.add(fisOption);
	    }
	}

	// remove any fis items from the cart that aren't checked
	CartBean cart = accountBean.getCartBean();
	List<LineItemBean> fisItems = cart.getLineItems(Inventory.INVENTORY_TYPE_FIS);
	for (LineItemBean fisItem : fisItems) {
	    if (!fisOptions.contains(fisItem.getInventory().getId())) {
		cart.removeLineItem(fisItem.getInventory().getId());
	    }
	}

	// add any fis items that are checked that aren't in the cart
	if (accountBean.getFisOptions() != null) {
	    for (String fisOption : accountBean.getFisOptions()) {
		if (!cart.contains(fisOption)) {
		    Inventory fisItem = inventoryDao.get(fisOption);
		    addMembershipToCart(accountBean, fisItem);
		}
	    }
	}
    }

    private void resetFisOptions(AccountBean accountBean) {
	List<Inventory> applicableFisItems = getApplicableFisItems(accountBean);

	CartBean cart = accountBean.getCartBean();
	List<LineItemBean> lineItems = cart.getLineItems(Inventory.INVENTORY_TYPE_FIS);
	List<String> fisInvIds = new ArrayList<String>();
	for (LineItemBean lineItem : lineItems) {
	    String invId = lineItem.getInventory().getId();
	    // if the user is no longer eligible for the selected fis option, then remove it from the cart
	    if (!contains(applicableFisItems, invId)) {
		cart.removeLineItem(invId);
	    } else {
		fisInvIds.add(invId);
	    }
	}

	accountBean.setFisOptions(fisInvIds.toArray(new String[fisInvIds.size()]));
    }

    public void handleMagazineOption(AccountBean accountBean) {
	CartBean cart = accountBean.getCartBean();

	String magazineOption = accountBean.getMagazineOption();

	List<LineItemBean> magazines = accountBean.getCartBean().getLineItems(Inventory.INVENTORY_TYPE_MAGAZINE);
	if (magazines.size() > 0 && !magazines.get(0).getInventory().getId().equals(magazineOption)) {
	    cart.removeLineItem(magazines.get(0).getInventory().getId());
	    if (StringUtils.isNotBlank(magazineOption)) {
		cart.addItem(inventoryDao.get(magazineOption));
	    }
	} else if (magazines.size() == 0) {
	    if (StringUtils.isNotBlank(magazineOption)) {
		cart.addItem(inventoryDao.get(magazineOption));
	    }
	}
    }

    private void resetMagazineOption(AccountBean accountBean) {
	CartBean cart = accountBean.getCartBean();
	List<LineItemBean> lineItems = cart.getLineItems(Inventory.INVENTORY_TYPE_MAGAZINE);
	if (lineItems.size() > 0) {
	    String magazineOption = lineItems.get(0).getInventory().getId();
	    List<Inventory> validMagazineOptions = getValidMagazineOptions(accountBean);
	    // if the user is no longer eligible for the selected magazine option, then remove it from the cart
	    if (!contains(validMagazineOptions, magazineOption)) {
		cart.removeLineItem(magazineOption);
		accountBean.setMagazineOption("");
	    } else {
		accountBean.setMagazineOption(magazineOption);
	    }
	} else {
	    accountBean.setMagazineOption("");
	}
    }

    public void handleContribution(AccountBean accountBean) {
	CartBean cart = accountBean.getCartBean();
	cart.removeLineItems(Inventory.INVENTORY_TYPE_DONATION);
	if (accountBean.getContributionAmount() != null && StringUtils.isNotEmpty(accountBean.getContributionSportId())) {
	    List<Inventory> donationInventory = inventoryDao.getIventoryByTypeAndSportCode(Inventory.INVENTORY_TYPE_DONATION, accountBean.getContributionSportId());
	    String amountStr = accountBean.getContributionAmount();
	    amountStr = StringUtils.replace(amountStr, "$", "");
	    DecimalFormat format = new DecimalFormat("###,##0.##");
	    try {
		cart.addItem(donationInventory.get(0), new BigDecimal(format.parse(amountStr).floatValue()));
	    } catch (ParseException e) {
		log.debug(e.getMessage());
	    }
	}
    }

    public void addRemoveDivisionDuesAndLateFees(AccountBean accountBean) {
	CartBean cart = accountBean.getCartBean();
	Member member = accountBean.getMember();

	// REMOVE DIVISION DUES
	cart.removeLineItems(Inventory.INVENTORY_TYPE_DIVISION_DUES);
	cart.removeLineItems(Inventory.INVENTORY_TYPE_STATE_DUES);

	Date now = new Date();
	Integer qty = 0;
	Date lateRenewDate = dateBL.getLateRenewDate();
	String stateCode = member.getStateCode();
	String divisionCode = member.getDivision().getDivisionCode();
	
	if(divisionCode.equalsIgnoreCase("X")){
		divisionCode = divisionAffiliationDao.getDivisionAffiliations(stateCode).get(0).getDivision().getDivisionCode();
	}
	
	// RE-CALCULATE AND RE-ADD DIVISION AND STATE DUES
	if (!cart.containsAny(RuleAssociations.membershipsExemptFromDues)) {
	    
	    Integer age = getAgeForCurrentRenewSeason(member.getBirthDate());
	    List<LineItemBean> membershipLineItems = cart.getLineItems(Inventory.INVENTORY_TYPE_MEMBERSHIP);
	    List<String> membershipIds = new ArrayList<String>();
	    for (LineItemBean lineItem : membershipLineItems) {
		membershipIds.add(lineItem.getInventory().getId());
			if(lineItem.getQty() != null){
				qty = lineItem.getQty();
			}
	    }
	    List<Inventory> stateDues = stateDuesRulesDao.getStateDues(stateCode, age, membershipIds, false);
	    List<Inventory> divDues = divDuesRulesDao.getDivisionDues(divisionCode, age, membershipIds, false);

	    if (stateDues.size() > 0) {
		Inventory mostExpensive = getMostExpensive(stateDues);
		cart.addItem(mostExpensive);

		// state late fees if any
		if (now.after(lateRenewDate) && (member.getId() != null && memberTransactionDao.getMemberTransactionsForSeason(member.getId(), dateBL.getLastSeason()).size() != 0)) {
		    List<Inventory> stateLateFees = stateDuesRulesDao.getStateDues(stateCode, age, membershipIds, true);
		    for (Inventory lateFee : stateLateFees) {
			cart.addItem(lateFee);
		    }
		}
	    } 
	    if(divDues.size() > 0){		

		List<Inventory> allDues = divDuesRulesDao.getDivisionDues(divisionCode, age, membershipIds, false);
		List<Inventory> applicableDues = new ArrayList<Inventory>();

		if (RuleAssociations.onlyOneDivisionDuePerSport.contains(divisionCode)) {
		    Set<String> applicableSportCodes = getApplicableSportCodes(allDues);
		    for (String sportCode : applicableSportCodes) {
			Inventory mostExpensive = getMostExpensive(allDues, sportCode);
			if (mostExpensive != null) {
			    applicableDues.add(mostExpensive);
			}
		    }
		} else if (RuleAssociations.onlyOneDivisionDue.contains(divisionCode)) {
		    Inventory mostExpensive = getMostExpensive(allDues);
		    if (mostExpensive != null) {
			applicableDues.add(mostExpensive);
		    }
		} else {
		    applicableDues = allDues;
		}

		for (Inventory due : applicableDues) {
			if(qty != null){
				cart.addItem(due, qty);
			}else{
		    	cart.addItem(due);
			}
		}

		// division late fees if any
		if (now.after(lateRenewDate) && (member.getId() != null && memberTransactionDao.getMemberTransactionsForSeason(member.getId(), dateBL.getLastSeason()).size() != 0)) {
		    List<Inventory> divisionLateFees = divDuesRulesDao.getDivisionDues(divisionCode, age, membershipIds, true);
		    for (Inventory lateFee : divisionLateFees) {
			cart.addItem(lateFee);
		    }
		}
	    }
	}

	// USSA LATE FEE
	if (now.after(lateRenewDate)) {
	    // new registrations are exempt from late fee and if they have only youth/official/non-competing membership
	    if ((member.getId() == null || member.getId() == 0) || cart.getLineItems(Inventory.INVENTORY_TYPE_MEMBERSHIP).size() == 0 || hasOnlyYouthMemberships(accountBean) || hasOnlyOfficialMemberships(accountBean)|| hasOnlyNonCompetingMemberships(accountBean) || hasOnlyShortTermMemberships(accountBean) || (member.getId() != null && memberTransactionDao.getMemberTransactionsForSeason(member.getId(), dateBL.getLastSeason()).size() == 0)) {
		cart.removeLineItem(Inventory.INV_ID_MEMBER_LATE_FEE);
	    } else if (!cart.contains(Inventory.INV_ID_MEMBER_LATE_FEE)) {
		Inventory memberLateFee = inventoryDao.get(Inventory.INV_ID_MEMBER_LATE_FEE);
		cart.addItem(memberLateFee);
	    }
	}
    }

    private Inventory getMostExpensive(Collection<Inventory> items) {
	Inventory mostExpensive = null;
	for (Inventory inventory : items) {
	    if (mostExpensive == null || inventory.getAmount().compareTo(mostExpensive.getAmount()) > 0) {
		mostExpensive = inventory;
	    }
	}

	return mostExpensive;
    }

    private Inventory getMostExpensive(Collection<Inventory> items, String sportCode) {
	List<Inventory> result = new ArrayList<Inventory>();
	for (Inventory inventory : items) {
	    if (Inventory.SPORT_CODE_ALL.equals(inventory.getSportCode()) || sportCode.equals(inventory.getSportCode())) {
		result.add(inventory);
	    }
	}

	return getMostExpensive(result);
    }

    private Set<String> getApplicableSportCodes(List<Inventory> allDues) {
	Set<String> sportCodes = new HashSet<String>();
	for (Inventory inventory : allDues) {
	    sportCodes.add(inventory.getSportCode());
	}
	return sportCodes;
    }

    public boolean hasFis(AccountBean accountBean, boolean disabled) {
	CartBean cart = accountBean.getCartBean();
	List<LineItemBean> fis = cart.getLineItems(Inventory.INVENTORY_TYPE_FIS);
	for (LineItemBean lineItem : fis) {
	    if (disabled && RuleAssociations.disabledFisMemberships.contains(lineItem.getInventory().getId()) || !disabled && !RuleAssociations.disabledFisMemberships.contains(lineItem.getInventory().getId())) {
		return true;
	    }
	}
	return false;
    }

    public void removeFisFromCart(AccountBean accountBean, boolean disabled) {
	CartBean cart = accountBean.getCartBean();
	List<LineItemBean> fis = cart.getLineItems(Inventory.INVENTORY_TYPE_FIS);
	for (LineItemBean lineItem : fis) {
	    if (disabled && RuleAssociations.disabledFisMemberships.contains(lineItem.getInventory().getId()) || !disabled && !RuleAssociations.disabledFisMemberships.contains(lineItem.getInventory().getId())) {
		cart.removeLineItem(lineItem.getInventory().getId());
	    }
	}
    }

    public boolean needsBackgroundCheck(Long ussaId) {
	String currentSeason = dateBL.getCurrentRenewSeason();
	List<MemberTransaction> memberTransactions = memberTransactionDao.getMemberTransactionsForSeason(ussaId, currentSeason);
	Set<String> items = new HashSet<String>();
	for (MemberTransaction memberTransaction : memberTransactions) {
	    items.add(memberTransaction.getInventory().getId());
	}

	if (containsAny(items, RuleAssociations.coachMemberships) || containsAny(items, RuleAssociations.officialMemberships)) {
	    if (isBackgroundCheckCurrent(ussaId)) {
		return false;
	    }
	}
	return true;
    }

    public boolean needsBackgroundCheck(AccountBean accountBean) {
	CartBean cartBean = accountBean.getCartBean();
	return (cartBean.containsAny(RuleAssociations.coachMemberships) || cartBean.containsAny(RuleAssociations.officialMemberships) || cartBean.containsAny(RuleAssociations.volunteerMemberships)) && !isBackgroundCheckCurrent(accountBean.getMember().getId());
    }

    private boolean isBackgroundCheckCurrent(Long ussaId) {
	if (ussaId != null) {
	    MemberSeason memberSeason = memberSeasonDao.getMostRecentBackgroundCheck(ussaId);

	    if (memberSeason != null) {
		int backgroundCheckSeason = Integer.parseInt(memberSeason.getMemberSeasonPk().getSeason());
		if (dateBL.calculateCurrentRenewSeason() < (backgroundCheckSeason + 3))// If check was less than 3 yrs ago than background check is current
		{
		    return true;
		} else {// due for background check-

			/*
		    // Check the renewal flag set by membership to see if we send or ignore this year
		    MemberSeason memberBackground = memberSeasonDao.getOnlyRenewalNeededBackgroundFlag(ussaId, dateBL.getCurrentRenewSeason());

		    // memberBackground is not empty if need backgrnd check
		    if (memberBackground != null) {
			return false;
		    } else {
			return true;
		    }
		    */
			//Just removed the tag check so it catches anyone who has not had a check in more than 3 years.
			return false;
		}
	    }
	}
	return false;
    }
    
    //New rule to check for Safe Sport Course
    public boolean needsFastStartCourse(AccountBean accountBean){
    	CartBean cartBean= accountBean.getCartBean();
    	    	
    	if(cartBean.containsAny(RuleAssociations.coachMemberships)  && !isFastStartCourseCurrent(accountBean.getMember().getId()) ){     
    		String needsFastStart = "Y";
        	//Check if most recent fast start course is less than 3 years
        	MemberSeason memberSeason = memberSeasonDao.getMostRecentFastStartCheck(accountBean.getMember().getId());
        	if(memberSeason != null){
            	int lastFastStartCheck = Integer.parseInt(memberSeason.getMemberSeasonPk().getSeason());
        		if(dateBL.calculateCurrentRenewSeason() < lastFastStartCheck + 3){
        			needsFastStart="N";
        			return false;
        		}
        		
        	}       	    		
    		if(!cartBean.contains(Inventory.INV_ID_CLINIC_FAST_START_COACHING) && !cartBean.contains(Inventory.INV_ID_CLINIC_FAST_START_COACHING_REF)  && needsFastStart.equals("Y")){
    			if(memberSeason != null){
    				cartBean.addItem(inventoryDao.get(Inventory.INV_ID_CLINIC_FAST_START_COACHING_REF));
    				accountBean.setNeedsFastRefresherCourse(true);
    			}
    			else{
    				cartBean.addItem(inventoryDao.get(Inventory.INV_ID_CLINIC_FAST_START_COACHING));
    			}
    			
    		}   		
    		return true;
    	}    	
    	else{
    		return false;
    	}
    	//return ((cartBean.containsAny(RuleAssociations.coachMemberships)  && !isFastStartCourseCurrent(accountBean.getMember().getId())) || ((cartBean.containsAny(RuleAssociations.officialMemberships)) && !cartBean.containsAny(RuleAssociations.coachMemberships)));
    	    }

    private boolean isFastStartCourseCurrent(Long ussaId){
    if(ussaId != null){
    	List<String> level = coachesEducationDao.getCoachLevel(ussaId);
      if(!level.isEmpty()){
    	  for(int i = 0; i < level.size(); i++){
    		  String levelName = (String) level.get(i);
    	  log.warn("Level is not empty "+levelName);
    	  }
    	  return true;
      }
      else{
    	  log.warn("Level is empty");
    	  return false;
      }
    }
    else{
    	return false;
    }
    }
    
    
    private boolean containsAny(Set<String> set, Collection<String> items) {
	for (String s : items) {
	    if (set.contains(s)) {
		return true;
	    }
	}
	return false;
    }

    private boolean contains(Collection<Inventory> items, String invId) {
	for (Inventory item : items) {
	    if (invId.equals(item.getId())) {
		return true;
	    }
	}
	return false;
    }

    public MembershipsBean getPurchasedMemberships(Long ussaId) {
	String currentSeason = dateBL.getCurrentRenewSeason();
	List<MemberTransaction> memberTransactions = memberTransactionDao.getMemberTransactionsForSeason(ussaId, currentSeason);
	return populateMembershipsBean(ussaId, memberTransactions);
    }

    public MembershipsBean populateMembershipsBean(Long ussaId, List<MemberTransaction> memberTransactions) {
	boolean isBackgroundCheckCurrent = isBackgroundCheckCurrent(ussaId);

	MembershipsBean membershipsBean = new MembershipsBean();
	Set<String> coachesAndOfficials = new HashSet<String>();
	coachesAndOfficials.addAll(RuleAssociations.coachMemberships);
	coachesAndOfficials.addAll(RuleAssociations.officialMemberships);
	coachesAndOfficials.addAll(RuleAssociations.volunteerMemberships);
	for (MemberTransaction memberTransaction : memberTransactions) {
	    Inventory inventory = memberTransaction.getInventory();
	    if (Inventory.INVENTORY_TYPE_MEMBERSHIP.equals(inventory.getInventoryType())) {
		boolean isCoachOrOfficial = coachesAndOfficials.contains(inventory.getId());
		if (!isCoachOrOfficial || isCoachOrOfficial && isBackgroundCheckCurrent) {
		    membershipsBean.addUnrestrictedMembership(inventory);
		} else {
		    membershipsBean.addRestrictedMembership(inventory);
		}
	    }
	}
	return membershipsBean;
    }

    public boolean isCountryUs(String country) {
	if (StringUtils.isNotBlank(country)) {
	    country = country.toUpperCase().trim();
	    if ("US".equals(country) || "U.S.".equals(country) || "U. S.".equals(country) || "USA".equals(country) || "U S.A.".equals(country) || "U. S. A.".equals(country) || "UNITED STATES".equals(country) || "UNITED STATES OF AMERICA".equals(country)) {
		return true;
	    }
	}
	return false;
    }

    public boolean isCountryUsOrCanada(String country) {
	if (StringUtils.isNotBlank(country)) {
	    country = country.toUpperCase().trim();
	    if ("CAN".equals(country) || "CANADA".equals(country) || isCountryUs(country)) {
		return true;
	    }
	}
	return false;
    }

    public Division determineDivision(String nationCode, String stateCode, Long clubId, String zipCode) {
	if (!isCountryUs(nationCode)) {
	    return divisionDao.get(Division.DIVISION_FOREIGN);
	}

	if (clubId != null && clubId > 0) {
	    Club club = clubDao.get(clubId);
	    return club.getDivision();
	}

	if (StringUtils.isNotBlank(stateCode)) {
	    List<DivisionAffiliation> affiliations = divisionAffiliationDao.getDivisionAffiliations(stateCode);
	    if (affiliations.size() == 1) {
		return affiliations.get(0).getDivision();
	    }
	}

	if (StringUtils.isNotBlank(stateCode) && StringUtils.isNotBlank(zipCode)) {
	    if (zipCode.length() > 3) {
		zipCode = zipCode.substring(0, 3);
	    }
	    List<DivisionAffiliation> affiliations = divisionAffiliationDao.getDivisionAffiliations(stateCode, zipCode);
	    if (affiliations.size() == 1) {
		return affiliations.get(0).getDivision();
	    }
	}

	return null;
    }

    public boolean isMemberInactive(Member member) {
	return member.getInactiveStatus() != null && !member.getInactiveStatus().trim().equals("");
    }
}
