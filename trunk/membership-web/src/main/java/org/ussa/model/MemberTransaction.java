package com.ussa.model;

import java.io.*;

import javax.persistence.*;

import org.appfuse.model.BaseObject;

@Entity
@Table (name="MEMBERTRANSACTION")

public class MemberTransaction extends BaseObject implements Serializable
{

  //Member Transaction table fields
    private String USSA_ID;
    private String SEASON;
    private String INV_ID;
    private String QTY;
    private String AMOUNT;
    private String PURCHASE_DATE;


    public MemberTransaction() {
    }

    /**
     * @return the uSSA_ID
     */
    public String getUSSA_ID()
    {
        return USSA_ID;
    }



    /**
     * @param ussa_id the uSSA_ID to set
     */
    public void setUSSA_ID(String ussa_id)
    {
        USSA_ID = ussa_id;
    }



    /**
     * @return the sEASON
     */
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
     * @return the iNV_ID
     */
    public String getINV_ID()
    {
        return INV_ID;
    }



    /**
     * @param inv_id the iNV_ID to set
     */
    public void setINV_ID(String inv_id)
    {
        INV_ID = inv_id;
    }



    /**
     * @return the qTY
     */
    public String getQTY()
    {
        return QTY;
    }



    /**
     * @param qty the qTY to set
     */
    public void setQTY(String qty)
    {
        QTY = qty;
    }



    /**
     * @return the aMOUNT
     */
    public String getAMOUNT()
    {
        return AMOUNT;
    }



    /**
     * @param amount the aMOUNT to set
     */
    public void setAMOUNT(String amount)
    {
        AMOUNT = amount;
    }



    /**
     * @return the pURCHASE_DATE
     */
    public String getPURCHASE_DATE()
    {
        return PURCHASE_DATE;
    }



    /**
     * @param purchase_date the pURCHASE_DATE to set
     */
    public void setPURCHASE_DATE(String purchase_date)
    {
        PURCHASE_DATE = purchase_date;
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



}
