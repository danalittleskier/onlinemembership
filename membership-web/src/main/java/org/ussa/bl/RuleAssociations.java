package org.ussa.bl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.ussa.model.Inventory;
import org.ussa.model.Division;

public class RuleAssociations
{
	public static final Map<String, String> officialsByCoach;
	public static final Map<String, String> coachesByOfficial;
	public static final Map<String, String> competitorByYouth;
	public static final Map<String, String> youthByCompetitor;
	public static final Set<String> twentyFiveDollarDiscountGroup;
	public static final Map<String, String[]> fisByMembership;
	public static final Set<String> disabledFisMemberships;
	public static final Set<String> disabledMemberships;
	public static final Set<String> youthMemberships;
	public static final Set<String> officialMemberships;
	public static final Set<String> onlyOneDivisionDuePerSport;
	public static final Set<String> onlyOneDivisionDue;
	public static final Map<String, String> divisionLateFeesAplineCompetitor;
	static {
		officialsByCoach = new HashMap<String, String>();
		officialsByCoach.put(Inventory.INV_ID_ALPINE_COACH, Inventory.INV_ID_ALPINE_OFFICIAL);
		officialsByCoach.put(Inventory.INV_ID_SNOWBOARD_COACH, Inventory.INV_ID_SNOWBOARD_OFFICIAL);
		officialsByCoach.put(Inventory.INV_ID_FREESTYLE_COACH, Inventory.INV_ID_FREESTYLE_OFFICIAL);
		officialsByCoach.put(Inventory.INV_ID_JUMPING_COACH, Inventory.INV_ID_JUMPING_OFFICIAL);
		officialsByCoach.put(Inventory.INV_ID_CROSS_COUNTRY_COACH, Inventory.INV_ID_CROSS_COUNTRY_OFFICIAL);

		coachesByOfficial = new HashMap<String, String>();
		coachesByOfficial.put(Inventory.INV_ID_ALPINE_OFFICIAL, Inventory.INV_ID_ALPINE_COACH);
		coachesByOfficial.put(Inventory.INV_ID_SNOWBOARD_OFFICIAL, Inventory.INV_ID_SNOWBOARD_COACH);
		coachesByOfficial.put(Inventory.INV_ID_FREESTYLE_OFFICIAL, Inventory.INV_ID_FREESTYLE_COACH);
		coachesByOfficial.put(Inventory.INV_ID_JUMPING_OFFICIAL, Inventory.INV_ID_JUMPING_COACH);
		coachesByOfficial.put(Inventory.INV_ID_CROSS_COUNTRY_OFFICIAL, Inventory.INV_ID_CROSS_COUNTRY_COACH);

		competitorByYouth = new HashMap<String, String>();
		competitorByYouth.put(Inventory.INV_ID_ALPINE_COMPETITOR, Inventory.INV_ID_ALPINE_YOUTH);
		competitorByYouth.put(Inventory.INV_ID_CROSS_COUNTRY_COMPETITOR, Inventory.INV_ID_CROSS_COUNTRY_YOUTH);
		competitorByYouth.put(Inventory.INV_ID_FREESTYLE_COMPETITOR, Inventory.INV_ID_FREESTYLE_YOUTH);
		competitorByYouth.put(Inventory.INV_ID_JUMPING_COMPETITOR, Inventory.INV_ID_JUMPING_YOUTH);

		youthByCompetitor = new HashMap<String, String>();
		youthByCompetitor.put(Inventory.INV_ID_ALPINE_YOUTH, Inventory.INV_ID_ALPINE_COMPETITOR);
		youthByCompetitor.put(Inventory.INV_ID_CROSS_COUNTRY_YOUTH, Inventory.INV_ID_CROSS_COUNTRY_YOUTH);
		youthByCompetitor.put(Inventory.INV_ID_FREESTYLE_YOUTH, Inventory.INV_ID_FREESTYLE_YOUTH);
		youthByCompetitor.put(Inventory.INV_ID_JUMPING_YOUTH, Inventory.INV_ID_JUMPING_COMPETITOR);

		twentyFiveDollarDiscountGroup = new HashSet<String>();
		twentyFiveDollarDiscountGroup.add(Inventory.INV_ID_ALPINE_YOUTH);
		twentyFiveDollarDiscountGroup.add(Inventory.INV_ID_FREESTYLE_YOUTH);
		twentyFiveDollarDiscountGroup.add(Inventory.INV_ID_JUMPING_YOUTH);
		twentyFiveDollarDiscountGroup.add(Inventory.INV_ID_CROSS_COUNTRY_YOUTH);
		twentyFiveDollarDiscountGroup.add(Inventory.INV_ID_FREESTYLE_ROOKIE);
		twentyFiveDollarDiscountGroup.add(Inventory.INV_ID_SNOWBOARD_COMPETITOR_REGIONAL);

		fisByMembership = new HashMap<String, String[]>();
		fisByMembership.put(Inventory.INV_ID_ALPINE_COMPETITOR, new String[]{Inventory.INV_ID_ALPINE_FIS});
		fisByMembership.put(Inventory.INV_ID_DISABLED_ALPINE_COMPETITOR,
				new String[]{Inventory.INV_ID_ALPINE_SKIING_DISABLED_LICENSE_FIS, Inventory.INV_ID_ALPINE_FIS});
		fisByMembership.put(Inventory.INV_ID_ALPINE_MASTER, new String[]{Inventory.INV_ID_ALPINE_MASTER_FIS});
		fisByMembership.put(Inventory.INV_ID_CROSS_COUNTRY_COMPETITOR, new String[]{Inventory.INV_ID_CROSS_COUNTRY_FIS});
		fisByMembership.put(Inventory.INV_ID_FREESTYLE_COMPETITOR, new String[]{Inventory.INV_ID_FREESTYLE_FIS});
		fisByMembership.put(Inventory.INV_ID_SNOWBOARD_COMPETITOR, new String[]{Inventory.INV_ID_SNOWBOARD_FIS});
		fisByMembership.put(Inventory.INV_ID_JUMPING_COMPETITOR, new String[]{Inventory.INV_ID_JUMPING_FIS});
		fisByMembership.put(Inventory.INV_ID_JUMPING_COMPETITOR, new String[]{Inventory.INV_ID_NORDIC_COMBINED_FIS});
		//TODO: is this missing from the db?
//		fisByMembership.put(Inventory.INV_ID_NORDIC_COMBINED_COMPETITOR, new String[]{Inventory.INV_ID_NORDIC_COMBINED_FIS});

		disabledMemberships = new HashSet<String>();
		disabledMemberships.add(Inventory.INV_ID_DISABLED_ALPINE_ASSOCIATE);
		disabledMemberships.add(Inventory.INV_ID_DISABLED_ALPINE_COACH);
		disabledMemberships.add(Inventory.INV_ID_DISABLED_ALPINE_COMPETITOR);
		disabledMemberships.add(Inventory.INV_ID_DISABLED_CROSS_COUNTRY_ASSOCIATE);
		disabledMemberships.add(Inventory.INV_ID_DISABLED_CROSS_COUNTRY_COACH);
		disabledMemberships.add(Inventory.INV_ID_DISABLED_CROSS_COUNTRY_COMPETITOR);

		disabledFisMemberships = new HashSet<String>();
		disabledFisMemberships.add(Inventory.INV_ID_ALPINE_SKIING_DISABLED_LICENSE_FIS);
		disabledFisMemberships.add(Inventory.INV_ID_ALP_PTS_CONF_FIS);

		youthMemberships = new HashSet<String>();
		youthMemberships.add(Inventory.INV_ID_ALPINE_YOUTH);
		youthMemberships.add(Inventory.INV_ID_CROSS_COUNTRY_YOUTH);
		youthMemberships.add(Inventory.INV_ID_FREESTYLE_ROOKIE);
		youthMemberships.add(Inventory.INV_ID_JUMPING_YOUTH);

		officialMemberships = new HashSet<String>();
		officialMemberships.add(Inventory.INV_ID_ALPINE_OFFICIAL);
		officialMemberships.add(Inventory.INV_ID_SNOWBOARD_OFFICIAL);
		officialMemberships.add(Inventory.INV_ID_FREESTYLE_OFFICIAL);
		officialMemberships.add(Inventory.INV_ID_CROSS_COUNTRY_OFFICIAL);

		onlyOneDivisionDuePerSport = new HashSet<String>();
		onlyOneDivisionDuePerSport.add(Division.DIVISION_FAR_WEST);
		onlyOneDivisionDuePerSport.add(Division.DIVISION_INTERMOUNTAIN);
		onlyOneDivisionDuePerSport.add(Division.DIVISION_NORTHERN);

		onlyOneDivisionDue = new HashSet<String>();
		onlyOneDivisionDue.add(Division.DIVISION_PACIFIC_NORTHWEST);
		onlyOneDivisionDue.add(Division.DIVISION_ROCKY);

		divisionLateFeesAplineCompetitor = new HashMap<String, String>();
		divisionLateFeesAplineCompetitor.put(Division.DIVISION_NORTHERN, Inventory.INV_ID_NORTHERN_ALPINE_LATE_FEE);
		divisionLateFeesAplineCompetitor.put(Division.DIVISION_ALASKA, Inventory.INV_ID_ALASKA_ALPINE_LATE_FEE);
		divisionLateFeesAplineCompetitor.put(Division.DIVISION_FAR_WEST, Inventory.INV_ID_FARWEST_LATE_FEE);
		divisionLateFeesAplineCompetitor.put(Division.DIVISION_INTERMOUNTAIN, Inventory.INV_ID_INTERMOUNTAIN_ALPINE_LATE_FEE);
	}
}
