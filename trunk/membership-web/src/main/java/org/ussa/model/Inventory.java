package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "INVENTORY")
public class Inventory implements Serializable
{
	public static final String INVENTORY_TYPE_MEMBERSHIP = "MEMBERSHIP";
	public static final String INVENTORY_TYPE_DONATION = "DONATION";
	public static final String INVENTORY_TYPE_BONUS_PACK = "BONUS PACK";

	public static final String SPORT_CODE_ALP = "ALP";
	public static final String SPORT_CODE_BRD = "BRD";
	public static final String SPORT_CODE_FRE = "FRE";
	public static final String SPORT_CODE_JNC = "JNC";
	public static final String SPORT_CODE_XC = "XC";
	public static final String SPORT_CODE_ALL = "ALL";

	@Id
	@Column(name = "INV_ID", length = 8)
	private String id;

	@Column(name = "DESCRIPTION", nullable = true, length = 50, unique = false)
	private String description;

	@Column(name = "RENEW_DESC", nullable = true, length = 50, unique = false)
	private String renewDescription;

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

	@Column(name = "AMOUNT", nullable = true, length = 5, unique = false)
	private BigDecimal amount;


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

	public String getRenewDescription()
	{
		return renewDescription;
	}

	public void setRenewDescription(String renewDescription)
	{
		this.renewDescription = renewDescription;
	}

	public Long getAgeFrom()
	{
		return ageFrom;
	}

	public void setAgeFrom(Long ageFrom)
	{
		this.ageFrom = ageFrom;
	}

	public Long getAgeTo()
	{
		return ageTo;
	}

	public void setAgeTo(Long ageTo)
	{
		this.ageTo = ageTo;
	}

	public String getSportCode()
	{
		return sportCode;
	}

	public void setSportCode(String sportCode)
	{
		this.sportCode = sportCode;
	}

	public String getInventoryType()
	{
		return inventoryType;
	}

	public void setInventoryType(String inventoryType)
	{
		this.inventoryType = inventoryType;
	}

	public String getActive()
	{
		return active;
	}

	public void setActive(String active)
	{
		this.active = active;
	}

	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}
}
