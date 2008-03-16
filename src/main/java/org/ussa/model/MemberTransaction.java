package org.ussa.model;

import java.io.*;

import javax.persistence.*;

import org.appfuse.model.BaseObject;

@Entity
@Table (name="MEMBERTRANSACTION")

public class MemberTransaction extends BaseObject implements Serializable
{

  //Member Transaction table fields
    private String ussaId;
    private String season;
    private String invId;
    private String qty;
    private String amount;
    private String purchaseDate;


    public MemberTransaction() {
    }

    /**
     * @return the uSSA_ID
     */
    public String getUSSA_ID()
    {
        return ussaId;
    }



    /**
     * @param ussa_id the uSSA_ID to set
     */
    public void setUSSA_ID(String ussa_id)
    {
        ussaId = ussa_id;
    }



    /**
     * @return the sEASON
     */
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
     * @return the iNV_ID
     */
    public String getINV_ID()
    {
        return invId;
    }



    /**
     * @param inv_id the iNV_ID to set
     */
    public void setINV_ID(String inv_id)
    {
        invId = inv_id;
    }



    /**
     * @return the qTY
     */
    public String getQTY()
    {
        return qty;
    }



    /**
     * @param qty the qTY to set
     */
    public void setQTY(String qty)
    {
        qty = qty;
    }



    /**
     * @return the aMOUNT
     */
    public String getAMOUNT()
    {
        return amount;
    }



    /**
     * @param amount the aMOUNT to set
     */
    public void setAMOUNT(String amount)
    {
        amount = amount;
    }



    /**
     * @return the pURCHASE_DATE
     */
    public String getPURCHASE_DATE()
    {
        return purchaseDate;
    }



    /**
     * @param purchase_date the pURCHASE_DATE to set
     */
    public void setPURCHASE_DATE(String purchase_date)
    {
        purchaseDate = purchase_date;
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
