package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "INVENTORY")
@NamedQueries({
        @NamedQuery(name=Inventory.QUERY_BY_TYPE_AND_SPORT_CODE,
        query="select i from Inventory i where i.active = 'Y' and i.inventoryType = :inventoryType and i.sportCode = :sportCode")
        })
@org.hibernate.annotations.Cache(usage =
	org.hibernate.annotations.CacheConcurrencyStrategy.NONSTRICT_READ_WRITE
)
public class Inventory implements Serializable
{
    public static final String QUERY_BY_TYPE_AND_SPORT_CODE = "Inventory.QUERY_BY_TYPE_AND_SPORT_CODE";

	/*
		The following is a useful query for gernerating these constants:

		select 'public static final String INV_ID_'+replace(upper(description), ' ', '_')+' = "'+inv_id+'";' as constant
		from inventory
		where ....;
	 */

	public static final String INVENTORY_TYPE_MEMBERSHIP = "MEMBERSHIP";
	public static final String INVENTORY_TYPE_FIS = "FIS";
	public static final String INVENTORY_TYPE_DONATION = "DONATION";
	public static final String INVENTORY_TYPE_BONUS_PACK = "BONUS PACK";
	public static final String INVENTORY_TYPE_GOLD_PACK = "GOLDPACK";
	public static final String INVENTORY_TYPE_MAGAZINE = "MAG";
	public static final String INVENTORY_TYPE_DIVISION_DUES = "DIV DUES";
	public static final String INVENTORY_TYPE_STATE_DUES = "STATE DUES";
	public static final String INVENTORY_TYPE_SPONSORS = "SPONSORS";

	public static final String SPORT_CODE_ALP = "ALP";
	public static final String SPORT_CODE_DAL = "DAL";
	public static final String SPORT_CODE_BRD = "BRD";
	public static final String SPORT_CODE_FRE = "FRE";
	public static final String SPORT_CODE_JNC = "JNC";
	public static final String SPORT_CODE_XC = "XC";
	public static final String SPORT_CODE_DXC = "DXC";
	public static final String SPORT_CODE_ALL = "ALL";

	// Alpine memberships
	public static final String INV_ID_ALPINE_ASSOCIATE = "AA";
	public static final String INV_ID_ALPINE_COACH = "ACO";
	public static final String INV_ID_ALPINE_COMPETITOR = "AC";
	public static final String INV_ID_ALPINE_MASTER = "AM";
	public static final String INV_ID_ALPINE_STUDENT = "AS";
	public static final String INV_ID_ALPINE_OFFICIAL = "AO";
	public static final String INV_ID_ALPINE_YOUTH = "AY";
	public static final String INV_ID_ALPINE_VOLUNTEER = "AV";
	public static final String INV_ID_ALPINE_NON_COMPETING = "AN";

	// Cross country memberships
	public static final String INV_ID_CROSS_COUNTRY_ASSOCIATE = "XA";
	public static final String INV_ID_CROSS_COUNTRY_COACH = "XCO";
	public static final String INV_ID_CROSS_COUNTRY_COMPETITOR = "XC";
	public static final String INV_ID_CROSS_COUNTRY_YOUTH = "XY";
	public static final String INV_ID_CROSS_COUNTRY_OFFICIAL = "XO";
	public static final String INV_ID_CROSS_COUNTRY_VOLUNTEER = "XV";
	public static final String INV_ID_CROSS_COUNTRY_NON_COMPETING = "XN";

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
	public static final String INV_ID_FREESKIING_COMPETITOR = "FSC";
	public static final String INV_ID_FREESTYLE_VOLUNTEER ="FV";
	public static final String INV_ID_FREESTYLE_NON_COMPETING = "FN";

	// Jumping / Nordic Combined memberships
	public static final String INV_ID_JUMPING_ASSOCIATE = "JA";
	public static final String INV_ID_JUMPING_COACH = "JCO";
	public static final String INV_ID_JUMPING_COMPETITOR = "JC";
	public static final String INV_ID_JUMPING_YOUTH = "JY";
	public static final String INV_ID_JUMPING_OFFICIAL = "JO";
	public static final String INV_ID_JUMPING_VOLUNTEER = "JV";
	public static final String INV_ID_JUMPING_NON_COMPETING ="JN";

	// Snowboarding memberships
	public static final String INV_ID_SNOWBOARD_ASSOCIATE = "BA";
	public static final String INV_ID_SNOWBOARD_COACH = "BCO";
	public static final String INV_ID_SNOWBOARD_COMPETITOR = "BC";
	public static final String INV_ID_SNOWBOARD_COMPETITOR_REGIONAL = "BCR";
	public static final String INV_ID_SNOWBOARD_OFFICIAL = "BO";
	public static final String INV_ID_SNOWBOARD_VOLUNTEER = "BV";
	public static final String INV_ID_SNOWBOARD_NON_COMPETING = "BN";

	// FIS
	public static final String INV_ID_ALPINE_FIS = "FISA";
	public static final String INV_ID_ALPINE_MASTER_FIS = "FISAM";
	public static final String INV_ID_SPEED_SKIING_ALPINE_FIS = "FISAPSS";
	public static final String INV_ID_ALP_PTS_CONF_FIS = "FISAPTC";
	//public static final String INV_ID_ALPINE_SKIING_DISABLED_LICENSE_FIS = "FISASD";
	public static final String INV_ID_SNOWBOARD_FIS = "FISB";
	public static final String INV_ID_SNOWBOARD_POINTS_CONFIRMATION_FIS = "FISBPTC";
	public static final String INV_ID_FREESTYLE_FIS = "FISF";
	public static final String INV_ID_CROSS_COUNTRY_FIS = "FISX";
	public static final String INV_ID_JUMPING_FIS = "FISJ";
	public static final String INV_ID_NORDIC_COMBINED_FIS = "FISN";

	// FIS Late
	public static final String INV_ID_LATE_ALPINE_FIS = "FISAL";
	//public static final String INV_ID_LATE_ALPINE_SKIING_DISABLED_LICENSE_FIS = "FISASDL";
	public static final String INV_ID_LATE_CROSS_COUNTRY_FIS = "FISXL";
	public static final String INV_ID_LATE_FREESTYLE_FIS = "FISFL";

	// Magazines
	public static final String INV_ID_SNOWBOARD_MAGAZINE = "MAGSB";
	public static final String INV_ID_FREESKIING_MAGAZINE = "MAGFS";
	public static final String INV_ID_SKI_RACING_MAGAZINE = "MRCE";
	public static final String INV_ID_SKI_RACING_MAGAZINE_LIMITED_ISSUES = "MRLTD";
	public static final String INV_ID_SKI_TRAX_MAGAZINE = "MTRX";

	// Division Late Fees
	public static final String INV_ID_NORTHERN_ALPINE_LATE_FEE = "DDNAL";
	public static final String INV_ID_ALASKA_ALPINE_LATE_FEE = "DDAA1L";
	public static final String INV_ID_FARWEST_LATE_FEE = "DDFAL";
	public static final String INV_ID_INTERMOUNTAIN_ALPINE_LATE_FEE = "DDIAL";

	// State Dues
	public static final String INV_ID_MARA_LATE_FEE = "SDMEAL";
	public static final String INV_ID_MARA_DUES_AY_10_UNDER = "SDMEA1";
	public static final String INV_ID_MARA_DUES_AY_11_12_AS_AC_ACO = "SDMEA2";
	public static final String INV_ID_NJSRA_LATE_FEE = "SDNJAL";
	public static final String INV_ID_NJSRA_DUES_AY_AC = "SDNJA1";
	public static final String INV_ID_NJSRA_DUES_ACO_AO = "SDNJA2";

	// USSA Late Fee
	public static final String INV_ID_MEMBER_LATE_FEE = "LATE";

	// Misc - not used at this point
	public static final String INV_ID_MEMBER_CLUB = "CLUB";
	public static final String INV_ID_STAFF_MEMBERSHIP = "ZS";
	public static final String INV_ID_TRUSTEE_MEMBERSHIP = "TM";

	// Bonus Packs
	public static final String INV_ID_BONUS_ALPINE_LARGE = "BPAL";
	public static final String INV_ID_BONUS_ALPINE_MEDIUM = "BPAM";
	public static final String INV_ID_BONUS_ALPINE_SMALL = "BPAS";
	public static final String INV_ID_BONUS_ALPINE_EXTRA_LARGE = "BPAX";
	public static final String INV_ID_BONUS_SNOWBOARD_LARGE = "BPBL";
	public static final String INV_ID_BONUS_SNOWBOARD_MEDIUM = "BPBM";
	public static final String INV_ID_BONUS_SNOWBOARD_SMALL = "BPBS";
	public static final String INV_ID_BONUS_SNOWBOARD_EXTRA_LARGE = "BPBX";
	public static final String INV_ID_BONUS_FREESTYLE_LARGE = "BPFL";
	public static final String INV_ID_BONUS_FREESTYLE_MEDIUM = "BPFM";
	public static final String INV_ID_BONUS_FREESTYLE_SMALL = "BPFS";
	public static final String INV_ID_BONUS_FREESTYLE_EXTRA_LARGE = "BPFX";
	public static final String INV_ID_BONUS_JUMPING_LARGE = "BPJL";
	public static final String INV_ID_BONUS_JUMPING_MEDIUM = "BPJM";
	public static final String INV_ID_BONUS_JUMPING_SMALL = "BPJS";
	public static final String INV_ID_BONUS_JUMPING_EXTRA_LARGE = "BPJX";
	public static final String INV_ID_BONUS_CROSS_COUNTRY_LARGE = "BPXL";
	public static final String INV_ID_BONUS_CROSS_COUNTRY_MEDIUM = "BPXM";
	public static final String INV_ID_BONUS_CROSS_COUNTRY_SMALL = "BPXS";
	public static final String INV_ID_BONUS_CROSS_COUNTRY_EXTRA_LARGE = "BPXX";
	public static final String INV_ID_BONUS_SKI_TEAM_LOGO_LARGE = "BPYL";
	public static final String INV_ID_BONUS_SKI_TEAM_LOGO_MEDIUM = "BPYM";
	public static final String INV_ID_BONUS_SKI_TEAM_LOGO_SMALL = "BPYS";
	public static final String INV_ID_BONUS_SKI_TEAM_LOGO_EXTRA_LARGE = "BPYX";
	public static final String INV_ID_BONUS_DECALS_SNOWBOARD = "DECSB";
	public static final String INV_ID_BONUS_DECALS_SKI_TEAM = "DECSKI";
	public static final String INV_ID_BONUS_PACK_NOMAG = "BPNM";
	
	// Global Rescueo
	public static final String INV_ID_SPONSORS_FAMILY="GRF";
	public static final String INV_ID_SPONSORS_INDIVIDUAL="GRI";
	public static final String INV_ID_SPONSORS_STUDENT="GRS";
	
	//COACHES CLINICS COURSES
	
	public static final String INV_ID_CLINIC_FAST_START_COACHING = "FSCC";



	@Id
	@Column(name = "INV_ID", length = 8)
	private String id;

	@Column(name = "DESCRIPTION", nullable = true, length = 50, unique = false)
	private String description;

	@Column(name = "RENEW_DESC", nullable = true, length = 50, unique = false)
	private String renewDescription;

	@Column(name = "AGE_FROM", nullable = true, length = 5, unique = false)
	private Integer ageFrom;

	@Column(name = "AGE_TO", nullable = true, length = 5, unique = false)
	private Integer ageTo;

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

	public Integer getAgeFrom()
	{
		return ageFrom;
	}

	public void setAgeFrom(Integer ageFrom)
	{
		this.ageFrom = ageFrom;
	}

	public Integer getAgeTo()
	{
		return ageTo;
	}

	public void setAgeTo(Integer ageTo)
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
	
	@Override
	public int hashCode()
	{
		return new HashCodeBuilder(11,31)
		.append(id)
		.toHashCode();
	}

	@Override
	public boolean equals(Object other)
	{
		if (!(other instanceof Inventory)) {
			return false;
		}
		Inventory otherObj = (Inventory) other;
		return new EqualsBuilder().append(this.id, otherObj.id).isEquals();
	}
}
