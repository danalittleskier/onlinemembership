package org.ussa.beans;

import java.sql.Timestamp;
import java.util.List;

import org.ussa.model.Address;
import org.ussa.model.Club;
import org.ussa.model.Member;
import org.ussa.model.State;

public class AccountBean
{
    //Fields to save when done
    private Member member;

    private Address address;

    private Club club;

    //Helper data, not necessarailly persisited...
    private Boolean waiverAgree = false; //This one will go away
    private Long clubId = new Long(0);
    private String stateAffiliation;


    //Reference Data fields.  Do not save when done:
    List<State> usStates;
    List<Club>  clubsForState;


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


}
