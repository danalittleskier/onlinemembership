package org.ussa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.appfuse.model.BaseObject;

@Embeddable
public class AddressPk extends BaseObject implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Column(name = "USSA_ID", length=5)
    private Long id;
    @Column(name = "ADDRESS_TYPE", nullable = true, length=2, unique=false)
    private String type;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
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
