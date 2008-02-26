package org.ussa.model;

import java.io.*;
import javax.persistence.*;

import org.appfuse.model.BaseObject;

@Entity
@Table(name = "INVENTORY")
public class Inventory extends BaseObject implements Serializable
{
    @Id
    @Column(name = "INV_ID", length = 8)
    private String id;

    @Column(name = "RENEW_DESC", nullable = true, length = 50, unique = false)
    private String renewDesc;

    @Column(name = "DESCRIPTION", nullable = true, length = 50, unique = false)
    private String description;

    @Column(name = "AGE_FROM", nullable = true, length = 5, unique = false)
    private Long ageFrom;

    @Column(name = "AGE_TO", nullable = true, length = 5, unique = false)
    private Long ageTo;

    @Column(name = "SPORT_CODE", nullable = true, length = 3, unique = false)
    private String sportCode;

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

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getRenewDesc()
    {
        return renewDesc;
    }

    public void setRenewDesc(String renewDesc)
    {
        this.renewDesc = renewDesc;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Long getAgeFrom()
    {
        return ageFrom;
    }

    public void setAgeFrom(Long ageFrom)
    {
        this.ageFrom = ageFrom;
    }

    public Long getAgeTo()
    {
        return ageTo;
    }

    public void setAgeTo(Long ageTo)
    {
        this.ageTo = ageTo;
    }

    public String getSportCode()
    {
        return sportCode;
    }

    public void setSportCode(String sportCode)
    {
        this.sportCode = sportCode;
    }

}
