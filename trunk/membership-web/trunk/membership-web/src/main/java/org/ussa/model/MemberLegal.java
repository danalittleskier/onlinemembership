package org.ussa.model;

import java.io.*;
import javax.persistence.*;


@Entity
@Table (name="MEMBERLEGAL")

public class MemberLegal implements Serializable {


    // Memberhship Legal

    private int mlUssaId;
    private String season;
    private String insuranceCompany;
    private String insurancePolicy;
    private String insurancePhone;


    public MemberLegal() {
    }


    /**
     * @return the mL_USSA_ID
     */
    @Id
    @Column(name = "USSA_ID", nullable = false, length=5, unique=false)
    public int getML_USSA_ID()
    {
        return mlUssaId;
    }


    /**
     * @param ml_ussa_id the mL_USSA_ID to set
     */
    public void setML_USSA_ID(int ml_ussa_id)
    {
        mlUssaId = ml_ussa_id;
    }


    /**
     * @return the sEASON
     */
    @Column(name = "SEASON", nullable = true, length=4, unique=false)
    public String getSEASON()
    {
        return season;
    }


    /**
     * @param season the sEASON to set
     */
    public void setSEASON(String season)
    {
        season = season;
    }


    /**
     * @return the iNSURANCE_COMPANY
     */
    @Column(name = "INSURANCE_COMPANY", nullable = true, length=50, unique=false)
    public String getINSURANCE_COMPANY()
    {
        return insuranceCompany;
    }


    /**
     * @param insurance_company the iNSURANCE_COMPANY to set
     */
    public void setINSURANCE_COMPANY(String insurance_company)
    {
        insuranceCompany = insurance_company;
    }


    /**
     * @return the iNSURANCE_POLICY
     */
    @Column(name = "INSURANCE_POLICY", nullable = true, length=20, unique=false)
    public String getINSURANCE_POLICY()
    {
        return insurancePolicy;
    }


    /**
     * @param insurance_policy the iNSURANCE_POLICY to set
     */
    public void setINSURANCE_POLICY(String insurance_policy)
    {
        insurancePolicy = insurance_policy;
    }


    /**
     * @return the iNSURANCE_PHONE
     */
    @Column(name = "INSURANCE_PHONE", nullable = true, length=15, unique=false)
    public String getINSURANCE_PHONE()
    {
        return insurancePhone;
    }


    /**
     * @param insurance_phone the iNSURANCE_PHONE to set
     */
    public void setINSURANCE_PHONE(String insurance_phone)
    {
        insurancePhone = insurance_phone;
    }


}
