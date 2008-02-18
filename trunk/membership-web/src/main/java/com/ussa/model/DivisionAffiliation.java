package com.ussa.model;

import java.io.*;
import javax.persistence.*;
import org.appfuse.model.BaseObject;

@Entity
@Table (name="DIVISIONAFFILIATION")

public class DivisionAffiliation extends BaseObject implements Serializable
{

    //Division Affiliation table fields
    private String STATE_CODE;
    private String ZIP_CODE;
    private String DIVISION_CODE;

    public DivisionAffiliation() {
    }

    /**
     * @return the sTATE_CODE
     */
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
     * @return the zIP_CODE
     */
    public String getZIP_CODE()
    {
        return ZIP_CODE;
    }
    /**
     * @param zip_code the zIP_CODE to set
     */
    public void setZIP_CODE(String zip_code)
    {
        ZIP_CODE = zip_code;
    }
    /**
     * @return the dIVISION_CODE
     */
    public String getDIVISION_CODE()
    {
        return DIVISION_CODE;
    }
    /**
     * @param division_code the dIVISION_CODE to set
     */
    public void setDIVISION_CODE(String division_code)
    {
        DIVISION_CODE = division_code;
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
