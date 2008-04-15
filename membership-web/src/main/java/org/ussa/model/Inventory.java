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
	public static final String INVENTORY_TYPE_FIS = "FIS";
	public static final String INVENTORY_TYPE_DONATION = "DONATION";
	public static final String INVENTORY_TYPE_BONUS_PACK = "BONUS PACK";
	public static final String INVENTORY_TYPE_MAGAZINE = "MAG";
	public static final String INVENTORY_TYPE_DIVISION_DUES = "DIV DUES";
	public static final String INVENTORY_TYPE_STATE_DUES = "STATE DUES";

	public static final String SPORT_CODE_ALP = "ALP";
	public static final String SPORT_CODE_BRD = "BRD";
	public static final String SPORT_CODE_FRE = "FRE";
	public static final String SPORT_CODE_JNC = "JNC";
	public static final String SPORT_CODE_XC = "XC";
	public static final String SPORT_CODE_ALL = "ALL";

	// Alpine memberships
	public static final String INV_ID_ALPINE_ASSOCIATE = "AA";
	public static final String INV_ID_ALPINE_COACH = "ACO";
	public static final String INV_ID_ALPINE_COMPETITOR = "AC";
	public static final String INV_ID_ALPINE_MASTER = "AM";
	public static final String INV_ID_ALPINE_STUDENT = "AS";
	public static final String INV_ID_ALPINE_OFFICIAL = "AO";
	public static final String INV_ID_ALPINE_YOUTH = "AY";

	// Cross country memberships
	public static final String INV_ID_CROSS_COUNTRY_ASSOCIATE = "XA";
	public static final String INV_ID_CROSS_COUNTRY_COACH = "XCO";
	public static final String INV_ID_CROSS_COUNTRY_COMPETITOR = "XC";
	public static final String INV_ID_CROSS_COUNTRY_YOUTH = "XY";
	public static final String INV_ID_CROSS_COUNTRY_OFFICIAL = "XO";

	// Disabled Alpine memberships
	public static final String INV_ID_DISABLED_ALPINE_ASSOCIATE = "DAA";
	public static final String INV_ID_DISABLED_ALPINE_COACH = "DACO";
	public static final String INV_ID_DISABLED_ALPINE_COMPETITOR = "DAC";
	public static final String INV_ID_DISABLED_CROSS_COUNTRY_ASSOCIATE = "DXA";
	public static final String INV_ID_DISABLED_CROSS_COUNTRY_COACH = "DXCO";
	public static final String INV_ID_DISABLED_CROSS_COUNTRY_COMPETITOR = "DXC";

	// Freestyle memberships
	public static final String INV_ID_FREESTYLE_ASSOCIATE = "FA";
	public static final String INV_ID_FREESTYLE_COACH = "FCO";
	public static final String INV_ID_FREESTYLE_COMPETITOR = "FC";
	public static final String INV_ID_FREESTYLE_OFFICIAL = "FO";
	public static final String INV_ID_FREESTYLE_ROOKIE = "FR";
	public static final String INV_ID_FREESTYLE_YOUTH = "FY";

	// Jumping / Nordic Combined memberships
	public static final String INV_ID_JUMPING_ASSOCIATE = "JA";
	public static final String INV_ID_JUMPING_COACH = "JCO";
	public static final String INV_ID_JUMPING_COMPETITOR = "JC";
	public static final String INV_ID_JUMPING_YOUTH = "JY";
	public static final String INV_ID_JUMPING_OFFICIAL = "JO";

	// Snowboarding memberships
	public static final String INV_ID_SNOWBOARD_ASSOCIATE = "BA";
	public static final String INV_ID_SNOWBOARD_COACH = "BCO";
	public static final String INV_ID_SNOWBOARD_COMPETITOR = "BC";
	public static final String INV_ID_SNOWBOARD_COMPETITOR_REGIONAL = "BCR";
	public static final String INV_ID_SNOWBOARD_OFFICIAL = "BO";

	// FIS
	public static final String INV_ID_ALPINE_FIS = "FISA";
	public static final String INV_ID_ALPINE_MASTER_FIS = "FISAM";
	public static final String INV_ID_SPEED_SKIING_ALPINE_FIS = "FISAPSS";
	public static final String INV_ID_ALP_PTS_CONF_FIS = "FISAPTC";
	public static final String INV_ID_ALPINE_SKIING_DISABLED_LICENSE_FIS = "FISASD";
	public static final String INV_ID_SNOWBOARD_FIS = "FISB";
	public static final String INV_ID_SNOWBOARD_POINTS_CONFIRMATION_FIS = "FISBPTC";
	public static final String INV_ID_FREESTYLE_FIS = "FISF";
	public static final String INV_ID_CROSS_COUNTRY_FIS = "FISX";
	public static final String INV_ID_JUMPING_FIS = "FISJ";
	public static final String INV_ID_NORDIC_COMBINED_FIS = "FISN";

	// FIS Late
	public static final String INV_ID_LATE_ALPINE_FIS = "FISAL";
	public static final String INV_ID_LATE_ALPINE_SKIING_DISABLED_LICENSE_FIS = "FISASDL";
	public static final String INV_ID_LATE_CROSS_COUNTRY_FIS = "FISXL";
	public static final String INV_ID_LATE_FREESTYLE_FIS = "FISFL";

	// Magazines
	public static final String INV_ID_SNOWBOARD_MAGAZINE = "MAGSB";
	public static final String INV_ID_SKI_RACING_MAGAZINE = "MRCE";
	public static final String INV_ID_SKI_RACING_MAGAZINE_LIMITED_ISSUES = "MRLTD";
	public static final String INV_ID_SKI_TRAX_MAGAZINE = "MTRX";

	// Division Late Fees
	public static final String INV_ID_NORTHERN_ALPINE_LATE_FEE = "DDNAL";
	public static final String INV_ID_ALASKA_ALPINE_LATE_FEE = "DDAA1L";
	public static final String INV_ID_FARWEST_LATE_FEE = "DDFAL";
	public static final String INV_ID_INTERMOUNTAIN_ALPINE_LATE_FEE = "DDIAL";

	// USSA Late Fee
	public static final String INV_ID_MEMBER_LATE_FEE = "LATE";

	// Misc - not used at this point
	public static final String INV_ID_MEMBER_CLUB = "CLUB";
	public static final String INV_ID_STAFF_MEMBERSHIP = "ZS";
	public static final String INV_ID_TRUSTEE_MEMBERSHIP = "TM";



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
