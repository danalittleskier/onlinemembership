package org.ussa.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

import org.ussa.model.Address;
import org.ussa.model.Club;
import org.ussa.model.Inventory;
import org.ussa.model.Member;
import org.ussa.model.MemberLegal;
import org.ussa.model.MemberSeason;
import org.ussa.model.Nation;
import org.ussa.model.State;
import org.ussa.spring.flows.registration.RegistrationAction;
import com.sun.media.jai.codec.SeekableOutputStream;

public class AccountBean implements Serializable
{
	/**
     * 
     */
    private static final long serialVersionUID = -6421993006262894040L;
	//Fields to save when done
	private Member member;
	private Address address;
	private MemberLegal memberLegal;
	private MemberSeason memberSeason;

	private CartBean cartBean = new CartBean();
	private PaymentBean paymentBean = new PaymentBean();
	private ExtrasBean extrasBean = new ExtrasBean();
	private MembershipsBean membershipsBean = new MembershipsBean();
    private UserBean userBean = new UserBean();
    private GlobalRescueBean globalRescueBean = null;

	public GlobalRescueBean getGlobalRescueBean() {
		return globalRescueBean;
	}
	
	public void setGlobalRescueBean(GlobalRescueBean globalRescueBean) {
		this.globalRescueBean = globalRescueBean;
	}

	//Helper fields for binding, not persisited...
	private Boolean alreadyAMember;
	private Boolean parentInfoRequired;
	private Boolean usCitizen;
	private String birthDate;
	private Long clubId;
	private String clubZipCode;
	private String divisionCode;
	private String divisionDescription;
	private String sportId;
	private String contributionSportId;
	private String membershipId;
	private String[] fisOptions;
	private String magazineOption;
	private Boolean hasInsurance;
	private boolean wasBgScreeningInfoAlreadyShown;
	private boolean fastStartCourseInfoAlreadyShown;
	private boolean needsBackground;
	private String fisWaiverParentalConsent;
	private String fisWaiverDisabledParentalConsent;
	private String medicalWaiverParentalConsent;
	private String releaseWaiverParentalConsent;
	private String confirmationEmail;
	private boolean needsFastStartCourse;
	private boolean needsFastRefresherCourse;
	//private Date membershipFrom;
	//private Date membershipTo;
	private String membershipFrom;
	private String membershipTo;


	//Reference Data fields.
	private List<State> usStates;
	private List<State> allStates;
	private List<Nation> nations;
	private List<Club> clubsForState;
	private List<Inventory> memberships;
	private List<Inventory> fisItems;
	private List<Inventory> magazineItems;
	private List<Integer> years;
    private List<Member> duplicateMembers;
    private List<String> validDisciplines;

    public List<String> getValidDisciplines() {
		return validDisciplines;
	}

	public void setValidDisciplines(List<String> validDisciplines) {
		this.validDisciplines = validDisciplines;
	}

	private String contributionAmount;

	public Member getMember()
	{
		return member;
	}

	public void setMember(Member member)
	{
		this.member = member;
	}

	public Address getAddress()
	{
		return address;
	}

	public void setAddress(Address address)
	{
		this.address = address;
	}

	public MemberLegal getMemberLegal()
	{
		return memberLegal;
	}

	public void setMemberLegal(MemberLegal memberLegal)
	{
		this.memberLegal = memberLegal;
	}

	public Boolean getAlreadyAMember()
	{
		return alreadyAMember;
	}

	public void setAlreadyAMember(Boolean alreadyAMember)
	{
		this.alreadyAMember = alreadyAMember;
	}

	public Boolean getParentInfoRequired()
	{
		return parentInfoRequired;
	}

	public void setParentInfoRequired(Boolean parentInfoRequired)
	{
		this.parentInfoRequired = parentInfoRequired;
	}

	public String getBirthDate()
	{
		return birthDate;
	}

	/**
	 * Normally set from {@link Member#getBirthDate()}
	 * see also {@link RegistrationAction#initexistingAccountBean}
	 * 
	 * @param birthDate
	 */
	public void setBirthDate(String birthDate)
	{
		this.birthDate = birthDate;
	}
	
	public Boolean getUsCitizen()
	{
		return usCitizen;
	}

	public void setUsCitizen(Boolean usCitizen)
	{
		this.usCitizen = usCitizen;
	}

	public List<State> getUsStates()
	{
		return usStates;
	}

	public Boolean getHasInsurance()
	{
		return hasInsurance;
	}

	public void setHasInsurance(Boolean hasInsurance)
	{
		this.hasInsurance = hasInsurance;
	}

	public void setUsStates(List<State> usStates)
	{
		this.usStates = usStates;
	}

	public boolean getWasBgScreeningInfoAlreadyShown()
	{
		return wasBgScreeningInfoAlreadyShown;
	}

	public void setWasBgScreeningInfoAlreadyShown(boolean wasBgScreeningInfoAlreadyShown)
	{
		this.wasBgScreeningInfoAlreadyShown = wasBgScreeningInfoAlreadyShown;
	}
	
	public boolean isFastStartCourseInfoAlreadyShown() {
		return fastStartCourseInfoAlreadyShown;
	}

	public void setFastStartCourseInfoAlreadyShown(boolean fastStartCourseInfoAlreadyShown) {
		this.fastStartCourseInfoAlreadyShown = fastStartCourseInfoAlreadyShown;
	}

	public String getFisWaiverParentalConsent()
	{
		return fisWaiverParentalConsent;
	}

	public void setFisWaiverParentalConsent(String fisWaiverParentalConsent)
	{
		this.fisWaiverParentalConsent = fisWaiverParentalConsent;
	}

	public String getFisWaiverDisabledParentalConsent()
	{
		return fisWaiverDisabledParentalConsent;
	}

	public void setFisWaiverDisabledParentalConsent(String fisWaiverDisabledParentalConsent)
	{
		this.fisWaiverDisabledParentalConsent = fisWaiverDisabledParentalConsent;
	}

	public String getMedicalWaiverParentalConsent()
	{
		return medicalWaiverParentalConsent;
	}

	public void setMedicalWaiverParentalConsent(String medicalWaiverParentalConsent)
	{
		this.medicalWaiverParentalConsent = medicalWaiverParentalConsent;
	}

	public String getReleaseWaiverParentalConsent()
	{
		return releaseWaiverParentalConsent;
	}

	public void setReleaseWaiverParentalConsent(String releaseWaiverParentalConsent)
	{
		this.releaseWaiverParentalConsent = releaseWaiverParentalConsent;
	}

	public List<State> getAllStates()
	{
		return allStates;
	}

	public void setAllStates(List<State> allStates)
	{
		this.allStates = allStates;
	}

	public List<Nation> getNations()
	{
		return nations;
	}

	public void setNations(List<Nation> nations)
	{
		this.nations = nations;
	}

	public List<Club> getClubsForState()
	{
		return clubsForState;
	}

	public void setClubsForState(List<Club> clubsForState)
	{
		this.clubsForState = clubsForState;
	}

	public List<Inventory> getMemberships()
	{
		return memberships;
	}

	public void setMemberships(List<Inventory> memberships)
	{
		this.memberships = memberships;
	}

	public List<Inventory> getFisItems()
	{
		return fisItems;
	}

	public void setFisItems(List<Inventory> fisItems)
	{
		this.fisItems = fisItems;
	}

	public List<Inventory> getMagazineItems()
	{
		return magazineItems;
	}

	public void setMagazineItems(List<Inventory> magazineItems)
	{
		this.magazineItems = magazineItems;
	}

	public List<Integer> getYears()
	{
		return years;
	}

	public void setYears(List<Integer> years)
	{
		this.years = years;
	}

	public Long getClubId()
	{
		return clubId;
	}

	public void setClubId(Long clubId)
	{
		this.clubId = clubId;
	}

	public String getClubZipCode()
	{
		return clubZipCode;
	}

	public void setClubZipCode(String clubZipCode)
	{
		this.clubZipCode = clubZipCode;
	}

	public String getDivisionCode()
	{
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode)
	{
		this.divisionCode = divisionCode;
	}

	public String getDivisionDescription()
	{
		return divisionDescription;
	}

	public void setDivisionDescription(String divisionDescription)
	{
		this.divisionDescription = divisionDescription;
	}

	public String getMembershipId()
	{
		return membershipId;
	}

	public void setMembershipId(String membershipId)
	{
		this.membershipId = membershipId;
	}

	public String[] getFisOptions()
	{
		return fisOptions;
	}

	public void setFisOptions(String[] fisOptions)
	{
		this.fisOptions = fisOptions;
	}

	public String getMagazineOption()
	{
		return magazineOption;
	}

	public void setMagazineOption(String magazineOption)
	{
		this.magazineOption = magazineOption;
	}

	public String getSportId()
	{
		return sportId;
	}

	public void setSportId(String sportId)
	{
		this.sportId = sportId;
	}

    public String getContributionSportId() 
    {
        return contributionSportId;
    }

    public void setContributionSportId(String contributionSportId) 
    {
        this.contributionSportId = contributionSportId;
    }

    public CartBean getCartBean()
	{
		return cartBean;
	}

	public void setCartBean(CartBean cartBean)
	{
		this.cartBean = cartBean;
	}

	public PaymentBean getPaymentBean()
	{
		return paymentBean;
	}

	public void setPaymentBean(PaymentBean paymentBean)
	{
		this.paymentBean = paymentBean;
	}

	public ExtrasBean getExtrasBean()
	{
		return extrasBean;
	}

	public void setExtrasBean(ExtrasBean extrasBean)
	{
		this.extrasBean = extrasBean;
	}

	public MembershipsBean getMembershipsBean()
	{
		return membershipsBean;
	}

	public void setMembershipsBean(MembershipsBean membershipsBean)
	{
		this.membershipsBean = membershipsBean;
	}

	public UserBean getUserBean() {
	    return userBean;
	}

	public void setUserBean(UserBean userBean) {
	    this.userBean = userBean;
	}

	public List<Member> getDuplicateMembers()
	{
		return duplicateMembers;
	}

	public void setDuplicateUsers(List<Member> duplicateMembers)
	{
		this.duplicateMembers = duplicateMembers;
	}

	public String getContributionAmount()
	{
		return contributionAmount;
	}

	public void setContributionAmount(String contributionAmount)
	{
		this.contributionAmount = contributionAmount;
	}

	public boolean isNeedsBackground() {
		return needsBackground;
	}

	public void setNeedsBackground(boolean needsBackground) {
		this.needsBackground = needsBackground;
	}

	public MemberSeason getMemberSeason() {
		return memberSeason;
	}

	public void setMemberSeason(MemberSeason memberSeason) {
		this.memberSeason = memberSeason;
	}

	public String getConfirmationEmail() {
		return confirmationEmail;
	}

	public void setConfirmationEmail(String confirmationEmail) {
		this.confirmationEmail = confirmationEmail;
	}

	public boolean isNeedsFastStartCourse() {
		return needsFastStartCourse;
	}

	public void setNeedsFastStartCourse(boolean needsFastStartCourse) {
		this.needsFastStartCourse = needsFastStartCourse;
	}

	public boolean isNeedsFastRefresherCourse() {
		return needsFastRefresherCourse;
	}

	public void setNeedsFastRefresherCourse(boolean needsFastRefresherCourse) {
		this.needsFastRefresherCourse = needsFastRefresherCourse;
	}

	public String getMembershipFrom() {
		return membershipFrom;
	}

	public void setMembershipFrom(String membershipFrom) {
		this.membershipFrom = membershipFrom;
	}

	public String getMembershipTo() {
		return membershipTo;
	}

	public void setMembershipTo(String membershipTo) {
		this.membershipTo = membershipTo;
	}
	
	
}
