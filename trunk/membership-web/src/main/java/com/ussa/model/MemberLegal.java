package com.ussa.model;

import java.io.*;
import javax.persistence.*;


@Entity
@Table (name="MEMBERLEGAL")

public class MemberLegal implements Serializable {


    // Memberhship Legal

    private int ML_USSA_ID;
    private String SEASON;
    private String INSURANCE_COMPANY;
    private String INSURANCE_POLICY;
    private String INSURANCE_PHONE;


    public MemberLegal() {
    }


    /**
     * @return the mL_USSA_ID
     */
    @Id
    @Column(name = "USSA_ID", nullable = false, length=5, unique=false)
    public int getML_USSA_ID()
    {
        return ML_USSA_ID;
    }


    /**
     * @param ml_ussa_id the mL_USSA_ID to set
     */
    public void setML_USSA_ID(int ml_ussa_id)
    {
        ML_USSA_ID = ml_ussa_id;
    }


    /**
     * @return the sEASON
     */
    @Column(name = "SEASON", nullable = true, length=4, unique=false)
    public String getSEASON()
    {
        return SEASON;
    }


    /**
     * @param season the sEASON to set
     */
    public void setSEASON(String season)
    {
        SEASON = season;
    }


    /**
     * @return the iNSURANCE_COMPANY
     */
    @Column(name = "INSURANCE_COMPANY", nullable = true, length=50, unique=false)
    public String getINSURANCE_COMPANY()
    {
        return INSURANCE_COMPANY;
    }


    /**
     * @param insurance_company the iNSURANCE_COMPANY to set
     */
    public void setINSURANCE_COMPANY(String insurance_company)
    {
        INSURANCE_COMPANY = insurance_company;
    }


    /**
     * @return the iNSURANCE_POLICY
     */
    @Column(name = "INSURANCE_POLICY", nullable = true, length=20, unique=false)
    public String getINSURANCE_POLICY()
    {
        return INSURANCE_POLICY;
    }


    /**
     * @param insurance_policy the iNSURANCE_POLICY to set
     */
    public void setINSURANCE_POLICY(String insurance_policy)
    {
        INSURANCE_POLICY = insurance_policy;
    }


    /**
     * @return the iNSURANCE_PHONE
     */
    @Column(name = "INSURANCE_PHONE", nullable = true, length=15, unique=false)
    public String getINSURANCE_PHONE()
    {
        return INSURANCE_PHONE;
    }


    /**
     * @param insurance_phone the iNSURANCE_PHONE to set
     */
    public void setINSURANCE_PHONE(String insurance_phone)
    {
        INSURANCE_PHONE = insurance_phone;
    }


}
