package org.ussa.beans;

import java.util.List;

import org.ussa.model.Address;
import org.ussa.model.Club;
import org.ussa.model.Inventory;
import org.ussa.model.Member;
import org.ussa.model.MemberLegal;
import org.ussa.model.Nation;
import org.ussa.model.State;

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
	private int age;
	private String birthDate;
	private Long clubId;
	private String membershipId;
	private String sportId;
	private Boolean usCitizen;
	private Boolean hasInsurance;

	//Reference Data fields.  Do not save when done:
	private List<State> usStates;
	private List<Nation> nations;
	private List<Club> clubsForState;
	private List<Inventory> memberships;// inventory - memberships
	private List<Integer> years;

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

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
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

	public String getContributionAmount()
	{
		return contributionAmount;
	}

	public void setContributionAmount(String contributionAmount)
	{
		this.contributionAmount = contributionAmount;
	}
}
