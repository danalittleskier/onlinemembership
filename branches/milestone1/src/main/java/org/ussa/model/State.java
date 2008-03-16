package org.ussa.model;

import java.io.*;
import javax.persistence.*;

import org.appfuse.model.BaseObject;

@NamedQueries ({
})

@Entity
@Table (name="STATE")

public class State extends BaseObject implements Serializable
{
    //Member table fields for a club
    @Id
    @Column(name = "CODE", length=2)
    private String id;

    @Column(name = "DESCRIPTION", nullable = true, length=30)
    private String description;

    @Column(name = "USstate", nullable = true, length=2, unique=false)
    private String us;

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

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getUs()
    {
        return us;
    }

    public void setUs(String us)
    {
        this.us = us;
    }


}
