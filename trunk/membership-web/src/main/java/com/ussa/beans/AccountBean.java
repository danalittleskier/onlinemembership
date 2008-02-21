package com.ussa.beans;

import java.sql.Timestamp;

import com.ussa.model.Address;
import com.ussa.model.Member;
import com.ussa.model.MemberAddress;

public class AccountBean
{
    // Option 1: use a string per line
/*    private String ussaId;

    private String lastName;

    private String firstName;

    private String company;*/

    // Option 2: use objects

    private Member member;

    private Address address;

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


}
