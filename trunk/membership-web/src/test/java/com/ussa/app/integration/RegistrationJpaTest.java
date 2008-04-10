package com.ussa.app.integration;

import org.ussa.dao.AddressDao;
import org.ussa.dao.MemberDao;
import org.ussa.dao.MemberLegalDao;
import org.ussa.model.Address;
import org.ussa.model.AddressPk;
import org.ussa.model.Member;
import org.ussa.model.MemberLegal;

import java.util.Date;

/**
 * User: jminer
 * Date: Apr 8, 2008
 * Time: 5:03:41 PM
 */
public class RegistrationJpaTest extends AbstractUssaIntegrationTests {
    private MemberDao memberDao;
    private AddressDao addressDao;
    private MemberLegalDao memberLegalDao;

    /**
     * persist Member, Address, and MemberLegal
     */
    public void testSave() {
        
        Member member = new Member();
        member.setType("hi");
        member.setBirthDate(new Date());
        member.setClubName("club name");
        member.setEmail("my@email.com");
        member.setEthnicity("c");
        member.setExpireSeason("expi");
        member.setFirstName("first name");
        member.setGender("M");
        member.setLastName("last name");
        member.setLifetimeMember("N");
        member.setMiddleName("m");
        member.setNationCode("USA");
        member.setPrivateAddress("Y");
        member.setReceiveEmail("Y");
        member.setStateCode("UT");
        member.setSuffixName("suffix");
        Member persistentMember = memberDao.save(member);
        
        Address address = new Address(persistentMember, "M");
        address.setAddressPk(new AddressPk(persistentMember, "M"));
        address.setCity("sale lake city");
        address.setCountry("USA");
        address.setCompany("ussa");
        address.setDeliveryAddress("delivery address");
        addressDao.save(address);

        MemberLegal memberLegal = new MemberLegal(persistentMember, "fbar");
        memberLegal.setInsuranceCompany("insurance company");
        memberLegal.setInsurancePhone("888-555-1212");
        memberLegal.setInsuranceWaiverDate(new Date());
        memberLegalDao.save(memberLegal);
        
        // setComplete(); // tells spring to commit the transaction instead of rolling back (default)
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    public void setMemberLegalDao(MemberLegalDao memberLegalDao) {
        this.memberLegalDao = memberLegalDao;
    }
}
