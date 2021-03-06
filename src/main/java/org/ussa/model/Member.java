package org.ussa.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.ussa.util.DateTimeUtils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table (name="MEMBER")
@NamedQueries({
    @NamedQuery(
            name=Member.QUERY_DUPLICATES, 
            query="select m from Member m where lower(m.firstName) = :firstName and lower(m.lastName) = :lastName and m.birthDate = :birthDate"),
    @NamedQuery(
    		name=Member.QUERY_MAX_NENSA_ID,
    		query="select m from Member m where m.nensaId is not null and m.nensaId = (select max(m1.nensaId) from Member m1) ")
})
public class Member implements Serializable
{
	//Setting default values in case nothing gets changed we don't want to enter Null
	public Member(){
		Calendar now = Calendar.getInstance();
		this.privateAddress = "N";
		this.receiveEmail = "Y";
		this.receiveDivisionEmail = "Y";
		this.lifetimeMember = "N";
		this.modifiedDate = now.getTime();;
	}
	
    public static final String QUERY_DUPLICATES = "Member.QUERY_DUPLICATES";
    public static final String QUERY_MAX_NENSA_ID = "Member.QUERY_MAX_NENSA_ID";
    
    public static final String MEMBER_TYPE_INDIVIDUAL = "I";
	public static final String MEMBER_TYPE_CLUB = "C";
	public static final String MEMBER_TYPE_AFFILIATE = "A";
	public static final String MEMBER_TYPE_NON_MEMBER = "N";

	//Member table fields
	@Id
	@Column(name = "USSA_ID", length=7)
	private Long id;

	@Column(name = "MEMBER_TYPE", nullable = false, length=2, unique=false)
	private String type;

	@Column(name = "INACTIVE_STATUS", nullable = true, length=2, unique=false)
	private String inactiveStatus;

	@Column(name = "FIRST_NAME", nullable = true, length=30, unique=false)
	private String firstName;

	@Column(name = "MIDDLE_NAME", nullable = true, length=30, unique=false)
	private String middleName;

	@Column(name = "LAST_NAME", nullable = true, length=30, unique=false)
	private String lastName;

	@Column(name = "SUFFIX_NAME", nullable = true, length=30, unique=false)
	private String suffixName;

	@Column(name = "BIRTHDATE", nullable = true, unique=false)
	private Date birthDate;

	@Column(name = "FIS_ID", nullable = true, length=7, unique=false)
	private String fisId;
	
	@Column(name = "NENSA_ID", nullable = true, length=7, unique=false)
	private Long nensaId;

	@Column(name = "EMAIL", nullable = true, length=60, unique=false)
	private String email;

	@Column(name = "STATE_CODE", nullable = true, length=2, unique=false)
	private String stateCode;

	@Column(name = "CLUB_NAME", nullable = true, length=50, unique=false)
	private String clubName;

	@Column(name = "NATION_CODE", nullable = true, length=3, unique=false)
	private String nationCode;

	@Column(name = "EXPIRE_SEASON", nullable = true, length=4, unique=false)
	private String expireSeason;

	@Column(name = "LIFETIME_MEMBER", nullable = true, length=1, unique=false)
	private String lifetimeMember;

	@Column(name = "GENDER", nullable = true, length=1, unique=false)
	private String gender;

	@Column(name = "ETHNICITY", nullable = true, length=1, unique=false)
	private String ethnicity;

	@Column(name = "PRIVATE_ADDRESS", nullable = true, length=1, unique=false)
	private String privateAddress;

	@Column(name = "RECEIVE_EMAIL", nullable = true, length=1, unique=false)
	private String receiveEmail;
	
	@Column(name = "RECEIVE_DIVISION_EMAIL", nullable = true, length=1, unique=false)
	private String receiveDivisionEmail;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "DIVISION_CODE", nullable = true, unique=false)
	private Division division;

	@Column(name = "SINCE_SEASON", nullable = true, length=4, unique=false)
	private String sinceSeason;

	@Column(name = "CARD_PRINT_FLAG", nullable = true, length=1, unique=false)
	private String cardPrintFlag;
	
	@Column(name = "MODIFIED_DATE", nullable = true, unique = false)
	private Date modifiedDate;

//	@ManyToMany(mappedBy="members", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//	private Set<Club> clubs;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private ParentInfo parentInfo;

//    @OneToOne
//    private Address permanentAddress;

//    @OneToMany(mappedBy = "id", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//    private Set<Address> addresses = null;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getSuffixName() {
		return suffixName;
	}

	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getStateCode()
	{
		return stateCode;
	}

	public void setStateCode(String stateCode)
	{
		this.stateCode = stateCode;
	}

	public String getClubName()
	{
		return clubName;
	}

	public void setClubName(String clubName)
	{
		this.clubName = clubName;
	}

	public String getNationCode()
	{
		return nationCode;
	}

	public void setNationCode(String nationCode)
	{
		this.nationCode = nationCode;
	}

	public String getExpireSeason()
	{
		return expireSeason;
	}

	public void setExpireSeason(String expireSeason)
	{
		this.expireSeason = expireSeason;
	}

	public String getLifetimeMember()
	{
		return lifetimeMember;
	}

	public void setLifetimeMember(String lifetimeMember)
	{
		this.lifetimeMember = lifetimeMember;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public String getEthnicity()
	{
		return ethnicity;
	}

	public void setEthnicity(String ethnicity)
	{
		this.ethnicity = ethnicity;
	}

	public String getPrivateAddress()
	{
		return privateAddress;
	}

	public void setPrivateAddress(String privateAddress)
	{
		this.privateAddress = privateAddress;
	}

	public String getReceiveEmail()
	{
		return receiveEmail;
	}

	public void setReceiveEmail(String receiveEmail)
	{
		this.receiveEmail = receiveEmail;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getInactiveStatus()
	{
		return inactiveStatus;
	}

	public void setInactiveStatus(String inactiveStatus)
	{
		this.inactiveStatus = inactiveStatus;
	}

	public Date getBirthDate()
	{
		return birthDate;
	}

	public void setBirthDate(Date birthDate)
	{
		this.birthDate = birthDate;
	}

	public String getFisId()
	{
		return fisId;
	}

	public void setFisId(String fisId)
	{
		this.fisId = fisId;
	}

	public Long getNensaId() {
		return nensaId;
	}

	public void setNensaId(Long nensaId) {
		this.nensaId = nensaId;
	}

	public Division getDivision()
	{
		return division;
	}

	public void setDivision(Division division)
	{
		this.division = division;
	}

	public String getSinceSeason()
	{
		return sinceSeason;
	}

	public void setSinceSeason(String sinceSeason)
	{
		this.sinceSeason = sinceSeason;
	}

	public String getCardPrintFlag()
	{
		return cardPrintFlag;
	}

	public void setCardPrintFlag(String cardPrintFlag)
	{
		this.cardPrintFlag = cardPrintFlag;
	}

	public ParentInfo getParentInfo()
	{
		return parentInfo;
	}

	public void setParentInfo(ParentInfo parentInfo)
	{
		this.parentInfo = parentInfo;
	}

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (id != null ? !id.equals(member.id) : member.id != null) return false;

        return true;
    }

    public int hashCode() {
        return (id != null ? id.hashCode() : 0);
    }

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	public String getReceiveDivisionEmail() {
		return receiveDivisionEmail;
	}

	public void setReceiveDivisionEmail(String receiveDivisionEmail) {
		this.receiveDivisionEmail = receiveDivisionEmail;
	}
}
