package com.ussa.model;

import java.io.*;

import javax.persistence.*;

import org.appfuse.model.BaseObject;

@Entity
@Table (name="MEMBERSEASON")

public class MemberSeason extends BaseObject implements Serializable
{
  //Member Season table fields
    private String USSA_ID;
    private String SEASON;
    private String APP_PROCESS_DATE;
    private String APP_RECEIVE_DATE;

    public MemberSeason() {
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
     * @return the aPP_PROCESS_DATE
     */
    public String getAPP_PROCESS_DATE()
    {
        return APP_PROCESS_DATE;
    }



    /**
     * @param app_process_date the aPP_PROCESS_DATE to set
     */
    public void setAPP_PROCESS_DATE(String app_process_date)
    {
        APP_PROCESS_DATE = app_process_date;
    }



    /**
     * @return the aPP_RECEIVE_DATE
     */
    public String getAPP_RECEIVE_DATE()
    {
        return APP_RECEIVE_DATE;
    }



    /**
     * @param app_receive_date the aPP_RECEIVE_DATE to set
     */
    public void setAPP_RECEIVE_DATE(String app_receive_date)
    {
        APP_RECEIVE_DATE = app_receive_date;
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
