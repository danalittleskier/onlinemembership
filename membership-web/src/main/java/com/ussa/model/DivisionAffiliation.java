package com.ussa.model;

import java.io.*;
import javax.persistence.*;

import org.appfuse.model.BaseObject;

@Entity
@Table (name="DIVISIONAFFILIATION")

public class DivisionAffiliation extends BaseObject implements Serializable
{

    //Division Affiliation table fields
    private String stateCode;
    private String zipCode;
    private String divisionCode;

    public DivisionAffiliation() {
    }

    /**
     * @return the sTATE_CODE
     */
    @Id
    @Column(name = "STATE_CODE", nullable = false, length=2, unique=false)
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
     * @return the zIP_CODE
     */
    @Column(name = "ZIP_CODE", nullable = true, length=10, unique=false)
    public String getZIP_CODE()
    {
        return zipCode;
    }
    /**
     * @param zip_code the zIP_CODE to set
     */
    public void setZIP_CODE(String zip_code)
    {
        zipCode = zip_code;
    }
    /**
     * @return the dIVISION_CODE
     */
    @Column(name = "DIVISION_CODE", nullable = false, length=1, unique=false)
    public String getDIVISION_CODE()
    {
        return divisionCode;
    }
    /**
     * @param division_code the dIVISION_CODE to set
     */
    public void setDIVISION_CODE(String division_code)
    {
        divisionCode = division_code;
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
