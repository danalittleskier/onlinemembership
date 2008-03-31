package org.ussa.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

import org.appfuse.model.BaseObject;

@NamedQueries ({
		// @NamedQuery(name="Member.findByLastName", query="select m from Member m where lower(m.lastName) like lower(:lastName)"),
		// @NamedQuery(name="Member.findByUssaId", query="Select m from Member m ")//where m.mUssaId=:ussaId" )
		})

@Entity
@Table (name="MEMBER")

public class Member extends BaseObject implements Serializable
{
	//Member table fields
	@Id
	@Column(name = "USSA_ID", length=7)
	private Long id;

	@Column(name = "MEMBER_TYPE", nullable = true, length=2, unique=false)
	private String type;

	@Column(name = "FIRST_NAME", nullable = true, length=30, unique=false)
	private String firstName;

	@Column(name = "MIDDLE_NAME", nullable = true, length=30, unique=false)
	private String middleName;

	@Column(name = "LAST_NAME", nullable = true, length=30, unique=false)
	private String lastName;

	@Column(name = "SUFFIX_NAME", nullable = true, length=30, unique=false)
	private String suffixName;

	@Column(name = "BIRTHDATE", nullable = true, length=8, unique=false)
	private Date birthDate;

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

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "DIVISION_CODE", nullable = true, unique=false)
	private Division division;

//	@ManyToMany(mappedBy="members", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//	private Set<Club> clubs;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private ParentInfo parentInfo;

//    @OneToOne
//    private Address permanentAddress;

//    @OneToMany(mappedBy = "id", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//    private Set<Address> addresses = null;

	public Integer getAge()
	{
		if(birthDate != null)
		{
			Integer age;
			int acctYr=0;
			Calendar today=Calendar.getInstance();
			Calendar bDate=Calendar.getInstance();
			Calendar memYear=Calendar.getInstance();
			bDate.setTime(birthDate);
			memYear.setTime(birthDate);
			memYear.set(Calendar.YEAR, today.get(Calendar.YEAR));
			if(memYear.after(today)){
				acctYr--;
			}
			age = today.get(Calendar.YEAR)-bDate.get(Calendar.YEAR)+acctYr;
			return age;
		}

		return new Integer(0);

	}
	@Override
	public boolean equals(Object o)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return null;
	}

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

	public Date getBirthDate()
	{
		return birthDate;
	}

	public void setBirthDate(Date birthDate)
	{
		this.birthDate = birthDate;
	}

	public Division getDivision()
	{
		return division;
	}

	public void setDivision(Division division)
	{
		this.division = division;
	}

	public ParentInfo getParentInfo()
	{
		return parentInfo;
	}

	public void setParentInfo(ParentInfo parentInfo)
	{
		this.parentInfo = parentInfo;
	}
}
