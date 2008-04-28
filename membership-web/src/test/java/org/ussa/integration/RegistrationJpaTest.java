package org.ussa.integration;

import java.util.Date;

import org.ussa.dao.AddressDao;
import org.ussa.dao.InventoryDao;
import org.ussa.dao.MemberDao;
import org.ussa.dao.MemberLegalDao;
import org.ussa.dao.MemberTransactionDao;
import org.ussa.model.Member;

/**
 * User: jminer
 * Date: Apr 8, 2008
 * Time: 5:03:41 PM
 */
public class RegistrationJpaTest extends AbstractUssaIntegrationTests {
    private MemberDao memberDao;
    private AddressDao addressDao;
    private MemberLegalDao memberLegalDao;
    private InventoryDao inventoryDao;
    private MemberTransactionDao memberTransactionDao;

    public void testDuplicates() {
//        Calendar c = Calendar.getInstance();
//        c.set(2008, 0, 31, 0, 0, 0); // int year, int month, int date, int hourOfDay, int minute, int second
//        Date birthDate = c.getTime();
//        Member member1 = createMember("joe", "nobody", "joe@nobody.com", birthDate);
//        Member member2 = createMember("joe", "nobody", "joe2@nobody.com", birthDate);
//        Member member3 = createMember("joe", "nobody", "joe3@nobody.com", birthDate);
//        List<Member> list = memberDao.getDuplicateCandidates("nobody", birthDate);
//        assertNotNull(list);
//        assertTrue(list.size() >= 3);
    }

    private Member createMember(String firstName, String lastName, String email, Date birthDate) {
        Member member = new Member();
        member.setFirstName(firstName);
        member.setLastName(lastName);
        member.setEmail(email);
        member.setBirthDate(birthDate);

        member.setType("hi");
        member.setLifetimeMember("N");
        member.setMiddleName("m");
        member.setNationCode("USA");
        member.setPrivateAddress("Y");
        member.setReceiveEmail("Y");
        member.setStateCode("UT");
        member.setSuffixName("suffix");
        return memberDao.save(member);
    }

    /**
     * persist Member, Address, and MemberLegal
     */
    public void testSave() {

//        Member member = new Member();
//        member.setType("hi");
//        member.setBirthDate(new Date());
//        member.setClubName("club name");
//        member.setEmail("my@email.com");
//        member.setEthnicity("c");
//        member.setExpireSeason("expi");
//        member.setFirstName("firstname");
//        member.setGender("M");
//        member.setLastName("lastname");
//        member.setLifetimeMember("N");
//        member.setMiddleName("m");
//        member.setNationCode("USA");
//        member.setPrivateAddress("Y");
//        member.setReceiveEmail("Y");
//        member.setStateCode("UT");
//        member.setSuffixName("suffix");
//
//        ParentInfo parentInfo = new ParentInfo();
//        parentInfo.setParent1Email("parent1@email.com");
//        parentInfo.setParent1First("first1");
//        parentInfo.setParent1Last("last1");
//        parentInfo.setParent2Email("parent2@email.com");
//        parentInfo.setParent2First("first2");
//        parentInfo.setParent2Last("last2");
//        parentInfo.setParent2Relation("relation2");
//        member.setParentInfo(parentInfo);
//
//        Member persistentMember = memberDao.save(member);
//
//        Address address = new Address(persistentMember, "M");
//        address.setCity("sale lake city");
//        address.setCountry("USA");
//        address.setCompany("ussa");
//        address.setDeliveryAddress("delivery address");
//        addressDao.save(address);
//
//        MemberLegal memberLegal = new MemberLegal(persistentMember, "fbar");
//        memberLegal.setInsuranceCompany("insurance company");
//        memberLegal.setInsurancePhone("888-555-1212");
//        memberLegal.setInsuranceWaiverDate(new Date());
//        memberLegalDao.save(memberLegal);
//
//        MemberTransaction memberTransaction = new MemberTransaction(persistentMember);
//        memberTransaction.setAmount(BigDecimal.TEN);
//        memberTransaction.setInventory(inventoryDao.get("AC"));
//        memberTransaction.setPurchaseDate(new Date());
//        memberTransaction.setQty(3);
//        memberTransaction.setSeason("2008");
//        memberTransaction = memberTransactionDao.save(memberTransaction);
//        assertNotNull(memberTransaction.getId());

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

	public void setInventoryDao(InventoryDao inventoryDao)
	{
		this.inventoryDao = inventoryDao;
	}

	public void setMemberTransactionDao(MemberTransactionDao memberTransactionDao) {
        this.memberTransactionDao = memberTransactionDao;
    }
}
