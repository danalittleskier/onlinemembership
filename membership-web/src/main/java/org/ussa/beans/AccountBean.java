package org.ussa.beans;

import java.sql.Timestamp;
import java.util.List;

import org.ussa.model.Address;
import org.ussa.model.Club;
import org.ussa.model.Member;
import org.ussa.model.State;
import org.ussa.model.Inventory;

public class AccountBean
{
    //Fields to save when done
    private Member member; //Only save the member at the end...

    //These objects will be added to the member and saved via "member.save();".  May need a transactionManager
    private Address address;
    private Club club;
    private Inventory inventory;

    //Helper fields for binding, not persisited...
    private Boolean waiverAgree = false; //This one will go away
    private Long clubId;
    private String membershipId;
    private String stateAffiliation;

    //Reference Data fields.  Do not save when done:
    List<State> usStates;
    List<Club>  clubsForState;
    List<Inventory> memberships;// inventory - memberships

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


}
