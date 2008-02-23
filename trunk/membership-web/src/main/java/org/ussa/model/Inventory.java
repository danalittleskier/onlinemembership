package org.ussa.model;

import java.io.*;
import javax.persistence.*;


@Entity
@Table (name="INVENTORY")

public class Inventory implements Serializable {


    //Member table fields
    private int invId;
    private String renewDesc;
    private String description;
    private String ageFrom;
    private String ageTo;
    private String sportCode;


    public Inventory() {
    }


    /**
     * @return the iNV_ID
     */
    @Id
    @Column(name = "INV_ID", nullable = false, length=8, unique=false)
    public int getINV_ID()
    {
        return invId;
    }


    /**
     * @param inv_id the iNV_ID to set
     */
    public void setINV_ID(int inv_id)
    {
        invId = inv_id;
    }


    /**
     * @return the rENEW_DESC
     */
    @Column(name = "RENEW_DESC", nullable = true, length=50, unique=false)
    public String getRENEW_DESC()
    {
        return renewDesc;
    }


    /**
     * @param renew_desc the rENEW_DESC to set
     */
    public void setRENEW_DESC(String renew_desc)
    {
        renewDesc = renew_desc;
    }


    /**
     * @return the dESCRIPTION
     */
    @Column(name = "DESCRIPTION", nullable = true, length=50, unique=false)
    public String getDESCRIPTION()
    {
        return description;
    }


    /**
     * @param description the dESCRIPTION to set
     */
    public void setDESCRIPTION(String description)
    {
        description = description;
    }


    /**
     * @return the aGE_FROM
     */
    @Column(name = "AGE_FROM", nullable = true, length=5, unique=false)
    public String getAGE_FROM()
    {
        return ageFrom;
    }


    /**
     * @param age_from the aGE_FROM to set
     */
    public void setAGE_FROM(String age_from)
    {
        ageFrom = age_from;
    }


    /**
     * @return the aGE_TO
     */
    @Column(name = "AGE_TO", nullable = true, length=5, unique=false)
    public String getAGE_TO()
    {
        return ageTo;
    }


    /**
     * @param age_to the aGE_TO to set
     */
    public void setAGE_TO(String age_to)
    {
        ageTo = age_to;
    }


    /**
     * @return the sPORT_CODE
     */
    @Column(name = "SPORT_CODE", nullable = true, length=3, unique=false)
    public String getSPORT_CODE()
    {
        return sportCode;
    }


    /**
     * @param sport_code the sPORT_CODE to set
     */
    public void setSPORT_CODE(String sport_code)
    {
        sportCode = sport_code;
    }


}
