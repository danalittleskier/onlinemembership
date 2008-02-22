package com.ussa.model;

import java.io.*;
import javax.persistence.*;


@Entity
@Table (name="MEMBERADDRESS")

public class MemberAddress implements Serializable {


    //MemberAddress table fields
    private int MA_USSA_ID;
    private String COMPANY;
    private String DELIVERY_ADDRESS;
    private String CITY;
    private String STATE_CODE;
    private String POSTAL_CODE;
    private String COUNTRY;
    private String PHONE_HOME;
    private String PHONE_WORK;
    private String PHONE_FAX;
    private String PHONE_OTHER;
    /**
     * @return the mA_USSA_ID
     */
    @Id
    @Column(name = "USSA_ID", nullable = false, length=5, unique=false)
    public int getMA_USSA_ID()
    {
        return MA_USSA_ID;
    }
    /**
     * @param ma_ussa_id the mA_USSA_ID to set
     */
    public void setMA_USSA_ID(int ma_ussa_id)
    {
        MA_USSA_ID = ma_ussa_id;
    }
    /**
     * @return the cOMPANY
     */
    @Column(name = "COMPANY", nullable = true, length=40, unique=false)
    public String getCOMPANY()
    {
        return COMPANY;
    }
    /**
     * @param company the cOMPANY to set
     */
    public void setCOMPANY(String company)
    {
        COMPANY = company;
    }
    /**
     * @return the dELIVERY_ADDRESS
     */
    @Column(name = "DELIVERY_ADDRESS", nullable = true, length=40, unique=false)
    public String getDELIVERY_ADDRESS()
    {
        return DELIVERY_ADDRESS;
    }
    /**
     * @param delivery_address the dELIVERY_ADDRESS to set
     */
    public void setDELIVERY_ADDRESS(String delivery_address)
    {
        DELIVERY_ADDRESS = delivery_address;
    }
    /**
     * @return the cITY
     */
    @Column(name = "CITY", nullable = true, length=30, unique=false)
    public String getCITY()
    {
        return CITY;
    }
    /**
     * @param city the cITY to set
     */
    public void setCITY(String city)
    {
        CITY = city;
    }
    /**
     * @return the sTATE_CODE
     */
    @Column(name = "STATE_CODE", nullable = true, length=2, unique=false)
    public String getSTATE_CODE()
    {
        return STATE_CODE;
    }
    /**
     * @param state_code the sTATE_CODE to set
     */
    public void setSTATE_CODE(String state_code)
    {
        STATE_CODE = state_code;
    }
    /**
     * @return the pOSTAL_CODE
     */
    @Column(name = "POSTAL_CODE", nullable = true, length=10, unique=false)
    public String getPOSTAL_CODE()
    {
        return POSTAL_CODE;
    }
    /**
     * @param postal_code the pOSTAL_CODE to set
     */
    public void setPOSTAL_CODE(String postal_code)
    {
        POSTAL_CODE = postal_code;
    }
    /**
     * @return the cOUNTRY
     */
    @Column(name = "COUNTRY", nullable = true, length=30, unique=false)
    public String getCOUNTRY()
    {
        return COUNTRY;
    }
    /**
     * @param country the cOUNTRY to set
     */
    public void setCOUNTRY(String country)
    {
        COUNTRY = country;
    }
    /**
     * @return the pHONE_HOME
     */
    @Column(name = "PHONE_HOME", nullable = true, length=16, unique=false)
    public String getPHONE_HOME()
    {
        return PHONE_HOME;
    }
    /**
     * @param phone_home the pHONE_HOME to set
     */
    public void setPHONE_HOME(String phone_home)
    {
        PHONE_HOME = phone_home;
    }
    /**
     * @return the pHONE_WORK
     */
    @Column(name = "PHONE_WORK", nullable = true, length=16, unique=false)
    public String getPHONE_WORK()
    {
        return PHONE_WORK;
    }
    /**
     * @param phone_work the pHONE_WORK to set
     */
    public void setPHONE_WORK(String phone_work)
    {
        PHONE_WORK = phone_work;
    }
    /**
     * @return the pHONE_FAX
     */
    @Column(name = "PHONE_FAX", nullable = true, length=16, unique=false)
    public String getPHONE_FAX()
    {
        return PHONE_FAX;
    }
    /**
     * @param phone_fax the pHONE_FAX to set
     */
    public void setPHONE_FAX(String phone_fax)
    {
        PHONE_FAX = phone_fax;
    }
    /**
     * @return the pHONE_OTHER
     */
    @Column(name = "PHONE_OTHER", nullable = true, length=16, unique=false)
    public String getPHONE_OTHER()
    {
        return PHONE_OTHER;
    }
    /**
     * @param phone_other the pHONE_OTHER to set
     */
    public void setPHONE_OTHER(String phone_other)
    {
        PHONE_OTHER = phone_other;
    }


}
