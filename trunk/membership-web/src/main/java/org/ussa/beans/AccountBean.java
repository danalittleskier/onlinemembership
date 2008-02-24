package org.ussa.beans;

import java.sql.Timestamp;

import org.ussa.model.Address;
import org.ussa.model.Member;

public class AccountBean
{
    private Member member;

    private Address address;

    private Boolean waiverAgree = false;

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


}
