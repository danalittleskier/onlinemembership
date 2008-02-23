package org.ussa.model;

import java.io.*;
import javax.persistence.*;

import org.appfuse.model.BaseObject;


@Entity
@Table (name="MEMBERADDRESS")

public class Address extends BaseObject implements Serializable {


    //MemberAddress table fields
    @Id
    @Column(name = "USSA_ID")//, nullable = false, length=5, unique=false)
    private Long id;

    @Column(name = "COMPANY", nullable = true, length=40, unique=false)
    private String company;

    @Column(name = "DELIVERY_ADDRESS", nullable = true, length=40, unique=false)
    private String deliveryAddress;

    @Column(name = "CITY", nullable = true, length=30, unique=false)
    private String city;

    @Column(name = "STATE_CODE", nullable = true, length=2, unique=false)
    private String stateCode;

    @Column(name = "POSTAL_CODE", nullable = true, length=10, unique=false)
    private String postalCode;

    @Column(name = "COUNTRY", nullable = true, length=30, unique=false)
    private String country;

    @Column(name = "PHONE_HOME", nullable = true, length=16, unique=false)
    private String phoneHome;

    @Column(name = "PHONE_WORK", nullable = true, length=16, unique=false)
    private String phoneWork;

    @Column(name = "PHONE_FAX", nullable = true, length=16, unique=false)
    private String phoneFax;

    @Column(name = "PHONE_OTHER", nullable = true, length=16, unique=false)
    private String phoneOther;

    public String getCompany()
    {
        return company;
    }

    public void setCompany(String company)
    {
        this.company = company;
    }

    public String getDeliveryAddress()
    {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress)
    {
        this.deliveryAddress = deliveryAddress;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getStateCode()
    {
        return stateCode;
    }

    public void setStateCode(String stateCode)
    {
        this.stateCode = stateCode;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getPhoneHome()
    {
        return phoneHome;
    }

    public void setPhoneHome(String phoneHome)
    {
        this.phoneHome = phoneHome;
    }

    public String getPhoneWork()
    {
        return phoneWork;
    }

    public void setPhoneWork(String phoneWork)
    {
        this.phoneWork = phoneWork;
    }

    public String getPhoneFax()
    {
        return phoneFax;
    }

    public void setPhoneFax(String phoneFax)
    {
        this.phoneFax = phoneFax;
    }

    public String getPhoneOther()
    {
        return phoneOther;
    }

    public void setPhoneOther(String phoneOther)
    {
        this.phoneOther = phoneOther;
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


}
