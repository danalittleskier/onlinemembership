package com.ussa.model;

import java.io.*;
import javax.persistence.*;


@Entity
@Table (name="INVENTORY")

public class Inventory implements Serializable {


    //Member table fields
    private int INV_ID;
    private String RENEW_DESC;
    private String DESCRIPTION;
    private String AGE_FROM;
    private String AGE_TO;
    private String SPORT_CODE;


    public Inventory() {
    }


    /**
     * @return the iNV_ID
     */
    @Id
    @Column(name = "INV_ID", nullable = false, length=8, unique=false)
    public int getINV_ID()
    {
        return INV_ID;
    }


    /**
     * @param inv_id the iNV_ID to set
     */
    public void setINV_ID(int inv_id)
    {
        INV_ID = inv_id;
    }


    /**
     * @return the rENEW_DESC
     */
    @Column(name = "RENEW_DESC", nullable = true, length=50, unique=false)
    public String getRENEW_DESC()
    {
        return RENEW_DESC;
    }


    /**
     * @param renew_desc the rENEW_DESC to set
     */
    public void setRENEW_DESC(String renew_desc)
    {
        RENEW_DESC = renew_desc;
    }


    /**
     * @return the dESCRIPTION
     */
    @Column(name = "DESCRIPTION", nullable = true, length=50, unique=false)
    public String getDESCRIPTION()
    {
        return DESCRIPTION;
    }


    /**
     * @param description the dESCRIPTION to set
     */
    public void setDESCRIPTION(String description)
    {
        DESCRIPTION = description;
    }


    /**
     * @return the aGE_FROM
     */
    @Column(name = "AGE_FROM", nullable = true, length=5, unique=false)
    public String getAGE_FROM()
    {
        return AGE_FROM;
    }


    /**
     * @param age_from the aGE_FROM to set
     */
    public void setAGE_FROM(String age_from)
    {
        AGE_FROM = age_from;
    }


    /**
     * @return the aGE_TO
     */
    @Column(name = "AGE_TO", nullable = true, length=5, unique=false)
    public String getAGE_TO()
    {
        return AGE_TO;
    }


    /**
     * @param age_to the aGE_TO to set
     */
    public void setAGE_TO(String age_to)
    {
        AGE_TO = age_to;
    }


    /**
     * @return the sPORT_CODE
     */
    @Column(name = "SPORT_CODE", nullable = true, length=3, unique=false)
    public String getSPORT_CODE()
    {
        return SPORT_CODE;
    }


    /**
     * @param sport_code the sPORT_CODE to set
     */
    public void setSPORT_CODE(String sport_code)
    {
        SPORT_CODE = sport_code;
    }


}
