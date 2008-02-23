package org.ussa.model;

import java.io.*;
import javax.persistence.*;

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
    @Column(name = "USSA_ID")//, nullable = false, length=5, unique=false)
    private Long id;

    @Column(name = "FIRST_NAME", nullable = true, length=30, unique=false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = true, length=30, unique=false)
    private String lastName;

    @Column(name = "BIRTHDATE", nullable = true, length=8, unique=false)
    private String birthDate;

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
    private String etnicity;

//    @OneToMany(mappedBy = "id", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//    private Set<Address> addresses = null;

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

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(String birthDate)
    {
        this.birthDate = birthDate;
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

    public String getEtnicity()
    {
        return etnicity;
    }

    public void setEtnicity(String etnicity)
    {
        this.etnicity = etnicity;
    }


}
