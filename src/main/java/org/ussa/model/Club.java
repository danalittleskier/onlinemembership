package org.ussa.model;

import java.io.*;
import java.util.Set;

import javax.persistence.*;

import org.appfuse.model.BaseObject;

@NamedQueries ({
})

@Entity
@Table (name="MEMBER")

public class Club extends BaseObject implements Serializable
{
    //Member table fields for a club
    @Id
    @Column(name = "USSA_ID")
    private Long id;

    @Column(name = "MEMBER_TYPE", nullable = true, length=2, unique=false)
    private String type;

    @Column(name = "CLUB_NAME", nullable = true, length=50, unique=false)
    private String name;

    @Column(name = "CLUB_PRESIDENT", nullable = true, length=50, unique=false)
    private String president;

    @Column(name = "CLUB_SECRETARY", nullable = true, length=50, unique=false)
    private String secretary;

    @Column(name = "CLUB_HEADCOACH", nullable = true, length=50, unique=false)
    private String headcoach;

    @Column(name = "EMAIL", nullable = true, length=60, unique=false)
    private String email;

    @Column(name = "STATE_CODE", nullable = true, length=2, unique=false)
    private String stateCode;

//    @Column(name = "EXPIRE_SEASON", nullable = true, length=4, unique=false)
//    private String expireSeason;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="MEMBERCLUB",
            inverseJoinColumns = { @JoinColumn( name="IND_USSA_ID") },
            joinColumns = @JoinColumn( name="CLUB_USSA_ID")
    )
    private Set<Member> members;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPresident()
    {
        return president;
    }

    public void setPresident(String president)
    {
        this.president = president;
    }

    public String getSecretary()
    {
        return secretary;
    }

    public void setSecretary(String secretary)
    {
        this.secretary = secretary;
    }

    public String getHeadcoach()
    {
        return headcoach;
    }

    public void setHeadcoach(String headcoach)
    {
        this.headcoach = headcoach;
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

    public Set<Member> getMembers()
    {
        return members;
    }

    public void setMembers(Set<Member> members)
    {
        this.members = members;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }


}
