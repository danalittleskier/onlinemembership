package com.ussa.model;

import java.io.*;
import javax.persistence.*;


@Entity
@Table (name="MEMBERADDRESS")

public class MemberAddress implements Serializable {


    //MemberAddress table fields
    private int maUssaId;
    private String company;
    private String deliveryAddress;
    private String city;
    private String stateCode;
    private String postalCode;
    private String country;
    private String phoneHome;
    private String phoneWork;
    private String phoneFax;
    private String phoneOther;
    /**
     * @return the mA_USSA_ID
     */
    @Id
    @Column(name = "USSA_ID", nullable = false, length=5, unique=false)
    public int getMA_USSA_ID()
    {
        return maUssaId;
    }
    /**
     * @param ma_ussa_id the mA_USSA_ID to set
     */
    public void setMA_USSA_ID(int ma_ussa_id)
    {
        maUssaId = ma_ussa_id;
    }
    /**
     * @return the cOMPANY
     */
    @Column(name = "COMPANY", nullable = true, length=40, unique=false)
    public String getCOMPANY()
    {
        return company;
    }
    /**
     * @param company the cOMPANY to set
     */
    public void setCOMPANY(String company)
    {
        company = company;
    }
    /**
     * @return the dELIVERY_ADDRESS
     */
    @Column(name = "DELIVERY_ADDRESS", nullable = true, length=40, unique=false)
    public String getDELIVERY_ADDRESS()
    {
        return deliveryAddress;
    }
    /**
     * @param delivery_address the dELIVERY_ADDRESS to set
     */
    public void setDELIVERY_ADDRESS(String delivery_address)
    {
        deliveryAddress = delivery_address;
    }
    /**
     * @return the cITY
     */
    @Column(name = "CITY", nullable = true, length=30, unique=false)
    public String getCITY()
    {
        return city;
    }
    /**
     * @param city the cITY to set
     */
    public void setCITY(String city)
    {
        city = city;
    }
    /**
     * @return the sTATE_CODE
     */
    @Column(name = "STATE_CODE", nullable = true, length=2, unique=false)
    public String getSTATE_CODE()
    {
        return stateCode;
    }
    /**
     * @param state_code the sTATE_CODE to set
     */
    public void setSTATE_CODE(String state_code)
    {
        stateCode = state_code;
    }
    /**
     * @return the pOSTAL_CODE
     */
    @Column(name = "POSTAL_CODE", nullable = true, length=10, unique=false)
    public String getPOSTAL_CODE()
    {
        return postalCode;
    }
    /**
     * @param postal_code the pOSTAL_CODE to set
     */
    public void setPOSTAL_CODE(String postal_code)
    {
        postalCode = postal_code;
    }
    /**
     * @return the cOUNTRY
     */
    @Column(name = "COUNTRY", nullable = true, length=30, unique=false)
    public String getCOUNTRY()
    {
        return country;
    }
    /**
     * @param country the cOUNTRY to set
     */
    public void setCOUNTRY(String country)
    {
        country = country;
    }
    /**
     * @return the pHONE_HOME
     */
    @Column(name = "PHONE_HOME", nullable = true, length=16, unique=false)
    public String getPHONE_HOME()
    {
        return phoneHome;
    }
    /**
     * @param phone_home the pHONE_HOME to set
     */
    public void setPHONE_HOME(String phone_home)
    {
        phoneHome = phone_home;
    }
    /**
     * @return the pHONE_WORK
     */
    @Column(name = "PHONE_WORK", nullable = true, length=16, unique=false)
    public String getPHONE_WORK()
    {
        return phoneWork;
    }
    /**
     * @param phone_work the pHONE_WORK to set
     */
    public void setPHONE_WORK(String phone_work)
    {
        phoneWork = phone_work;
    }
    /**
     * @return the pHONE_FAX
     */
    @Column(name = "PHONE_FAX", nullable = true, length=16, unique=false)
    public String getPHONE_FAX()
    {
        return phoneFax;
    }
    /**
     * @param phone_fax the pHONE_FAX to set
     */
    public void setPHONE_FAX(String phone_fax)
    {
        phoneFax = phone_fax;
    }
    /**
     * @return the pHONE_OTHER
     */
    @Column(name = "PHONE_OTHER", nullable = true, length=16, unique=false)
    public String getPHONE_OTHER()
    {
        return phoneOther;
    }
    /**
     * @param phone_other the pHONE_OTHER to set
     */
    public void setPHONE_OTHER(String phone_other)
    {
        phoneOther = phone_other;
    }


}
