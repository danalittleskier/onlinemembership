package com.ussa.model;

import java.io.*;
import javax.persistence.*;

import org.appfuse.model.BaseObject;


@Entity
@Table (name="MEMBER")

public class Member extends BaseObject implements Serializable {


    //Member table fields
    private int M_USSA_ID;
    private String FIRST_NAME;
    private String LAST_NAME;
    private String BIRTHDATE;
    private String EMAIL;
    private String STATE_CODE;
    private String CLUB_NAME;
    private String NATION_CODE;
    private String EXPIRE_SEASON;
    private String LIFETIME_MEMBER;
    private String GENDER;



    public Member() {
    }

    /**
     * @return the m_USSA_ID
     */
    @Id
    @Column(name = "USSA_ID", nullable = false, length=5, unique=false)
    public int getM_USSA_ID()
    {
        return M_USSA_ID;
    }


    /**
     * @param m_ussa_id the m_USSA_ID to set
     */
    public void setM_USSA_ID(int m_ussa_id)
    {
        M_USSA_ID = m_ussa_id;
    }


    /**
     * @return the FIRST_NAME
     */
    @Column(name = "FIRST_NAME", nullable = true, length=30, unique=false)
    public String getFIRST_NAME()
    {
        return FIRST_NAME;
    }


    /**
     * @param first_name the fIRST_NAME to set
     */
    public void setFIRST_NAME(String first_name)
    {
        FIRST_NAME = first_name;
    }


    /**
     * @return the lAST_NAME
     */
    @Column(name = "LAST_NAME", nullable = true, length=30, unique=false)
    public String getLAST_NAME()
    {
        return LAST_NAME;
    }


    /**
     * @param last_name the lAST_NAME to set
     */
    public void setLAST_NAME(String last_name)
    {
        LAST_NAME = last_name;
    }


    /**
     * @return the bIRTHDATE
     */
    @Column(name = "BIRTHDATE", nullable = true, length=8, unique=false)
    public String getBIRTHDATE()
    {
        return BIRTHDATE;
    }


    /**
     * @param birthdate the bIRTHDATE to set
     */
    public void setBIRTHDATE(String birthdate)
    {
        BIRTHDATE = birthdate;
    }


    /**
     * @return the eMAIL
     */
    @Column(name = "EMAIL", nullable = true, length=60, unique=false)
    public String getEMAIL()
    {
        return EMAIL;
    }


    /**
     * @param email the eMAIL to set
     */
    public void setEMAIL(String email)
    {
        EMAIL = email;
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
     * @return the cLUB_NAME
     */
    @Column(name = "CLUB_NAME", nullable = true, length=50, unique=false)
    public String getCLUB_NAME()
    {
        return CLUB_NAME;
    }


    /**
     * @param club_name the cLUB_NAME to set
     */
    public void setCLUB_NAME(String club_name)
    {
        CLUB_NAME = club_name;
    }


    /**
     * @return the NATION_CODE
     */
    @Column(name = "NATION_CODE", nullable = true, length=3, unique=false)
    public String getNATION_CODE()
    {
        return NATION_CODE;
    }


    /**
     * @param nation_code the nATION_CODE to set
     */
    public void setNATION_CODE(String nation_code)
    {
        NATION_CODE = nation_code;
    }


    /**
     * @return the eXPIRE_SEASON
     */
    @Column(name = "EXPIRE_SEASON", nullable = true, length=4, unique=false)
    public String getEXPIRE_SEASON()
    {
        return EXPIRE_SEASON;
    }


    /**
     * @param expire_season the eXPIRE_SEASON to set
     */
    public void setEXPIRE_SEASON(String expire_season)
    {
        EXPIRE_SEASON = expire_season;
    }


    /**
     * @return the lIFETIME_MEMBER
     */
    @Column(name = "LIFETIME_MEMBER", nullable = true, length=1, unique=false)
    public String getLIFETIME_MEMBER()
    {
        return LIFETIME_MEMBER;
    }


    /**
     * @param lifetime_member the lIFETIME_MEMBER to set
     */
    public void setLIFETIME_MEMBER(String lifetime_member)
    {
        LIFETIME_MEMBER = lifetime_member;
    }


    /**
     * @return the gENDER
     */
    @Column(name = "GENDER", nullable = true, length=1, unique=false)
    public String getGENDER()
    {
        return GENDER;
    }


    /**
     * @param gender the gENDER to set
     */
    public void setGENDER(String gender)
    {
        GENDER = gender;
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
