package org.ussa.beans;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import org.ussa.model.Address;
import org.ussa.model.Club;
import org.ussa.model.Inventory;
import org.ussa.model.Member;
import org.ussa.model.State;

public class AccountBean
{
    //Fields to save when done
    private Member member; //Only save the member at the end...

    //These objects will be added to the member and saved via "member.save();".  May need a transactionManager
    private Address address;
    private Club club;
    private Inventory inventory;
    private CartBean cartBean;

    //Helper fields for binding, not persisited...
    private Boolean waiverAgree = false; //This one will go away
    private Long clubId;
    private String membershipId;
    private String sportId;
    private String stateAffiliation;

    //Reference Data fields.  Do not save when done:
    List<State> usStates;
    List<Club>  clubsForState;
    List<Inventory> memberships;// inventory - memberships
    List<String> sports;// inventory - sports
    Set<LineItemBean> shoppingCart = new HashSet<LineItemBean>();
    BigDecimal total = BigDecimal.ZERO;
    String totalCost = "0";
    String paymentTransactionCode;

    public String getPaymentTransactionCode() {
		return paymentTransactionCode;
	}

	public void setPaymentTransactionCode(String paymentTransactionCode) {
		this.paymentTransactionCode = paymentTransactionCode;
	}

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

    public Boolean getWaiverAgree()
    {
        return waiverAgree;
    }

    public void setWaiverAgree(Boolean waiverAgree)
    {
        this.waiverAgree = waiverAgree;
    }

    public List<State> getUsStates()
    {
        return usStates;
    }

    public void setUsStates(List<State> usStates)
    {
        this.usStates = usStates;
    }

    public Club getClub()
    {
        return club;
    }

    public void setClub(Club club)
    {
        this.club = club;
    }

    public String getStateAffiliation()
    {
        return stateAffiliation;
    }

    public void setStateAffiliation(String stateAffiliation)
    {
        this.stateAffiliation = stateAffiliation;
    }

    public List<Club> getClubsForState()
    {
        return clubsForState;
    }

    public void setClubsForState(List<Club> clubsForState)
    {
        this.clubsForState = clubsForState;
    }

    public Long getClubId()
    {
        return clubId;
    }

    public void setClubId(Long clubId)
    {
        this.clubId = clubId;
    }

    /**
     * @return the inventory
     */
    public Inventory getInventory()
    {
        return inventory;
    }

    /**
     * @param inventory the inventory to set
     */
    public void setInventory(Inventory inventory)
    {
        this.inventory = inventory;
    }

    public List<Inventory> getMemberships()
    {
        return memberships;
    }

    public void setMemberships(List<Inventory> memberships)
    {
        this.memberships = memberships;
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

    public List<String> getSports()
    {
        return sports;
    }

    public void setSports(List<String> sports)
    {
        this.sports = sports;
    }

    public CartBean getCartBean()
    {
        return cartBean;
    }

    public void setCartBean(CartBean cartBean)
    {
        this.cartBean = cartBean;
    }

    public Set<LineItemBean> getShoppingCart()
    {
        return shoppingCart;
    }

    public void setShoppingCart(Set<LineItemBean> shoppingCart)
    {
        this.shoppingCart = shoppingCart;
    }

    public BigDecimal getTotal()
    {
        return total;
    }

    public void setTotal(BigDecimal total)
    {
        this.total = total;
    }


}
