package com.ussa.model;

import java.io.*;
import javax.persistence.*;
import org.appfuse.model.BaseObject;

@Entity
@Table (name="MEMBERCLUB")

public class MemberClub extends BaseObject implements Serializable
{

  //Member Club table fields
    private String IND_USSA_ID;
    private String CLUB_USSA_ID;

    public MemberClub() {
    }

    /**
     * @return the iND_USSA_ID
     */
    public String getIND_USSA_ID()
    {
        return IND_USSA_ID;
    }
    /**
     * @param ind_ussa_id the iND_USSA_ID to set
     */
    public void setIND_USSA_ID(String ind_ussa_id)
    {
        IND_USSA_ID = ind_ussa_id;
    }
    /**
     * @return the cLUB_USSA_ID
     */
    public String getCLUB_USSA_ID()
    {
        return CLUB_USSA_ID;
    }
    /**
     * @param club_ussa_id the cLUB_USSA_ID to set
     */
    public void setCLUB_USSA_ID(String club_ussa_id)
    {
        CLUB_USSA_ID = club_ussa_id;
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
