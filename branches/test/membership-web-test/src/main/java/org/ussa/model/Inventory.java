package org.ussa.model;

import java.io.*;
import javax.persistence.*;

import org.appfuse.model.BaseObject;

@Entity
@Table(name = "INVENTORY")
public class Inventory extends BaseObject implements Serializable
{
    @Id
    @Column(name = "INV_ID", length = 8)
    private String id;

    @Column(name = "AMOUNT", nullable = true, length = 5, unique = false)
    private String amount;

    @Column(name = "DESCRIPTION", nullable = true, length = 50, unique = false)
    private String description;

    @Column(name = "AGE_FROM", nullable = true, length = 5, unique = false)
    private Long ageFrom;

    @Column(name = "AGE_TO", nullable = true, length = 5, unique = false)
    private Long ageTo;

    @Column(name = "SPORT_CODE", nullable = true, length = 3, unique = false)
    private String sportCode;

    @Column(name = "INVENTORY_TYPE", nullable = true, length = 20, unique = false)
    private String inventoryType;

    @Column(name = "ACTIVE", nullable = true, length = 1, unique = false)
    private String active;

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

    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * @return the amount
     */
    public String getAmount()
    {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return the ageFrom
     */
    public Long getAgeFrom()
    {
        return ageFrom;
    }

    /**
     * @param ageFrom the ageFrom to set
     */
    public void setAgeFrom(Long ageFrom)
    {
        this.ageFrom = ageFrom;
    }

    /**
     * @return the ageTo
     */
    public Long getAgeTo()
    {
        return ageTo;
    }

    /**
     * @param ageTo the ageTo to set
     */
    public void setAgeTo(Long ageTo)
    {
        this.ageTo = ageTo;
    }

    /**
     * @return the sportCode
     */
    public String getSportCode()
    {
        return sportCode;
    }

    /**
     * @param sportCode the sportCode to set
     */
    public void setSportCode(String sportCode)
    {
        this.sportCode = sportCode;
    }

    /**
     * @return the inventoryType
     */
    public String getInventoryType()
    {
        return inventoryType;
    }

    /**
     * @param inventoryType the inventoryType to set
     */
    public void setInventoryType(String inventoryType)
    {
        this.inventoryType = inventoryType;
    }

    /**
     * @return the active
     */
    public String getActive()
    {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(String active)
    {
        this.active = active;
    }



}
