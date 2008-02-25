package com.ussa.model;

import java.io.*;
import javax.persistence.*;

import org.appfuse.model.BaseObject;

@Entity
@Table (name="MEMBERCLUB")

public class MemberClub extends BaseObject implements Serializable
{

  //Member Club table fields
    private String indUssaId;
    private String clubUssaId;

    public MemberClub() {
    }

    /**
     * @return the iND_USSA_ID
     */
    @Column(name = "IND_USSA_ID", nullable = false, length=5, unique=false)
    public String getIND_USSA_ID()
    {
        return indUssaId;
    }
    /**
     * @param ind_ussa_id the iND_USSA_ID to set
     */
    public void setIND_USSA_ID(String ind_ussa_id)
    {
        indUssaId = ind_ussa_id;
    }
    /**
     * @return the cLUB_USSA_ID
     */
    @Column(name = "CLUB_USSA_ID", nullable = false, length=5, unique=false)
    public String getCLUB_USSA_ID()
    {
        return clubUssaId;
    }
    /**
     * @param club_ussa_id the cLUB_USSA_ID to set
     */
    public void setCLUB_USSA_ID(String club_ussa_id)
    {
        clubUssaId = club_ussa_id;
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
