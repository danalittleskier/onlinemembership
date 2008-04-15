package org.ussa.beans;

import org.ussa.model.Address;
import org.ussa.model.Club;
import org.ussa.model.Inventory;
import org.ussa.model.Member;
import org.ussa.model.MemberLegal;
import org.ussa.model.Nation;
import org.ussa.model.State;

import java.util.List;

public class AccountBean
{
	//Fields to save when done
	private Member member;
	private Address address;
	private MemberLegal memberLegal;

	private CartBean cartBean = new CartBean();
	private PaymentBean paymentBean = new PaymentBean();
	private ExtrasBean extrasBean = new ExtrasBean();

	//Helper fields for binding, not persisited...
	private Boolean parentInfoRequired;
	private Boolean usCitizen;
	private String birthDate;
	private Long clubId;
	private String sportId;
	private String membershipId;
	private String[] fisOptions;
	private String magazineOption;
	private Boolean hasInsurance;
	private Boolean hasFisWaiver;
	private Boolean hasFisWaiverDisabled;

	//Reference Data fields.  Do not save when done:
	private List<State> usStates;
	private List<Nation> nations;
	private List<Club> clubsForState;
	private List<Inventory> memberships;
	private List<Inventory> fisItems;
	private List<Inventory> magazineItems;
	private List<Integer> years;
    private List<Member> duplicateMembers;

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

	public Boolean getHasFisWaiver()
	{
		return hasFisWaiver;
	}

	public void setHasFisWaiver(Boolean hasFisWaiver)
	{
		this.hasFisWaiver = hasFisWaiver;
	}

	public Boolean getHasFisWaiverDisabled()
	{
		return hasFisWaiverDisabled;
	}

	public void setHasFisWaiverDisabled(Boolean hasFisWaiverDisabled)
	{
		this.hasFisWaiverDisabled = hasFisWaiverDisabled;
	}

	public void setUsStates(List<State> usStates)
	{
		this.usStates = usStates;
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
}
