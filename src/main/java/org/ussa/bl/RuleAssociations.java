package org.ussa.bl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

import org.ussa.model.Inventory;
import org.ussa.model.Division;

public class RuleAssociations
{
	public static final Map<String, Set<String>> officialsByCoach;
	public static final Map<String, Set<String>> coachesByOfficial;
	public static final Set<String> restrictedMemberships;
	public static final Set<String> volunteerMemberships;
	public static final Set<String> nonCompetingMemberships;
	public static final Set<String> twentyFiveDollarDiscountGroup;
	public static final Map<String, String[]> fisByMembership;
	public static final Set<String> disabledFisMemberships;
	public static final Set<String> membershipsExemptFromDues;
	public static final Set<String> youthMemberships;
	public static final Map<String, Set<String>> mutuallyExclusiveMemberships;
	public static final Set<String> officialMemberships;
	public static final Set<String> coachMemberships;
	public static final Set<String> onlyOneDivisionDuePerSport;
	public static final Set<String> onlyOneDivisionDue;
	public static final Map<String, String> divisionLateFeesAplineCompetitor;
	static {
		List<String[]> coachesAndOfficials = new ArrayList<String[]>();
		coachesAndOfficials.add(new String[] {Inventory.INV_ID_ALPINE_COACH, Inventory.INV_ID_ALPINE_OFFICIAL});
		//coachesAndOfficials.add(new String[] {Inventory.INV_ID_DISABLED_ALPINE_COACH, Inventory.INV_ID_ALPINE_OFFICIAL});
		coachesAndOfficials.add(new String[] {Inventory.INV_ID_SNOWBOARD_COACH, Inventory.INV_ID_SNOWBOARD_OFFICIAL});
		coachesAndOfficials.add(new String[] {Inventory.INV_ID_FREESTYLE_COACH, Inventory.INV_ID_FREESTYLE_OFFICIAL});
		coachesAndOfficials.add(new String[] {Inventory.INV_ID_FREESKIING_COACH, Inventory.INV_ID_FREESKIING_OFFICIAL});
		coachesAndOfficials.add(new String[] {Inventory.INV_ID_JUMPING_COACH, Inventory.INV_ID_JUMPING_OFFICIAL});
		coachesAndOfficials.add(new String[] {Inventory.INV_ID_CROSS_COUNTRY_COACH, Inventory.INV_ID_CROSS_COUNTRY_OFFICIAL});
		//coachesAndOfficials.add(new String[] {Inventory.INV_ID_DISABLED_CROSS_COUNTRY_COACH, Inventory.INV_ID_CROSS_COUNTRY_OFFICIAL});
		

		officialsByCoach = new HashMap<String, Set<String>>();
		coachesByOfficial = new HashMap<String, Set<String>>();
		for (String[] invIds : coachesAndOfficials)
		{
			addMembership(officialsByCoach, invIds[0], invIds[1]);
			addMembership(coachesByOfficial, invIds[1], invIds[0]);
		}

		List<String[]> mutuallyExclusiveList = new ArrayList<String[]>();
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_ALPINE_COMPETITOR, Inventory.INV_ID_ALPINE_YOUTH});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_CROSS_COUNTRY_COMPETITOR, Inventory.INV_ID_CROSS_COUNTRY_YOUTH});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_FREESTYLE_COMPETITOR, Inventory.INV_ID_FREESTYLE_YOUTH});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_FREESKIING_COMPETITOR, Inventory.INV_ID_FREESTYLE_YOUTH});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_JUMPING_COMPETITOR, Inventory.INV_ID_JUMPING_YOUTH});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_SNOWBOARD_COMPETITOR, Inventory.INV_ID_SNOWBOARD_COMPETITOR_REGIONAL});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_CROSS_COUNTRY_COMPETITOR, Inventory.INV_ID_DISABLED_CROSS_COUNTRY_COMPETITOR});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_ALPINE_COMPETITOR, Inventory.INV_ID_DISABLED_ALPINE_COMPETITOR});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_ALPINE_COACH, Inventory.INV_ID_DISABLED_ALPINE_COACH});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_CROSS_COUNTRY_COACH, Inventory.INV_ID_DISABLED_CROSS_COUNTRY_COACH});
		
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_ALPINE_COMPETITOR, Inventory.INV_ID_ALPINE_STUDENT});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_ALPINE_COMPETITOR_U10, Inventory.INV_ID_ALPINE_STUDENT});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_ALPINE_COMPETITOR_U12, Inventory.INV_ID_ALPINE_STUDENT});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_ALPINE_COMPETITOR_U14, Inventory.INV_ID_ALPINE_STUDENT});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_ALPINE_COMPETITOR_U16, Inventory.INV_ID_ALPINE_STUDENT});
		
		
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_FREESTYLE_COMPETITOR, Inventory.INV_ID_FREESKIING_COMPETITOR});
		
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_FREESTYLE_COMPETITOR_U09, Inventory.INV_ID_FREESTYLE_ROOKIE});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_FREESTYLE_COMPETITOR_U11, Inventory.INV_ID_FREESTYLE_ROOKIE});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_FREESTYLE_COMPETITOR_U13, Inventory.INV_ID_FREESTYLE_ROOKIE});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_FREESTYLE_COMPETITOR_U15, Inventory.INV_ID_FREESTYLE_ROOKIE});
		
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_FREESKIING_COMPETITOR_U09, Inventory.INV_ID_FREESTYLE_ROOKIE});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_FREESKIING_COMPETITOR_U11, Inventory.INV_ID_FREESTYLE_ROOKIE});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_FREESKIING_COMPETITOR_U13, Inventory.INV_ID_FREESTYLE_ROOKIE});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_FREESKIING_COMPETITOR_U15, Inventory.INV_ID_FREESTYLE_ROOKIE});
		
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_ALPINE_COMPETITOR, Inventory.INV_ID_ALPINE_NON_COMPETING});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_ALPINE_COMPETITOR_U10, Inventory.INV_ID_ALPINE_NON_COMPETING});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_ALPINE_COMPETITOR_U12, Inventory.INV_ID_ALPINE_NON_COMPETING});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_ALPINE_COMPETITOR_U14, Inventory.INV_ID_ALPINE_NON_COMPETING});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_ALPINE_COMPETITOR_U16, Inventory.INV_ID_ALPINE_NON_COMPETING});
		
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_FREESTYLE_COMPETITOR_U09, Inventory.INV_ID_FREESTYLE_NON_COMPETING});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_FREESTYLE_COMPETITOR_U11, Inventory.INV_ID_FREESTYLE_NON_COMPETING});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_FREESTYLE_COMPETITOR_U13, Inventory.INV_ID_FREESTYLE_NON_COMPETING});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_FREESTYLE_COMPETITOR_U15, Inventory.INV_ID_FREESTYLE_NON_COMPETING}); 
		
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_SNOWBOARD_COMPETITOR, Inventory.INV_ID_SNOWBOARD_NON_COMPETING});
		
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_CROSS_COUNTRY_COMPETITOR_U10, Inventory.INV_ID_CROSS_COUNTRY_NON_COMPETING});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_CROSS_COUNTRY_COMPETITOR_U12, Inventory.INV_ID_CROSS_COUNTRY_NON_COMPETING});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_CROSS_COUNTRY_COMPETITOR_U14, Inventory.INV_ID_CROSS_COUNTRY_NON_COMPETING});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_CROSS_COUNTRY_COMPETITOR_U16, Inventory.INV_ID_CROSS_COUNTRY_NON_COMPETING});
		
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_JUMPING_COMPETITOR_U10, Inventory.INV_ID_JUMPING_NON_COMPETING});		
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_JUMPING_COMPETITOR_U12, Inventory.INV_ID_JUMPING_NON_COMPETING});	
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_JUMPING_COMPETITOR_U14, Inventory.INV_ID_JUMPING_NON_COMPETING});	
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_JUMPING_COMPETITOR_U16, Inventory.INV_ID_JUMPING_NON_COMPETING});	
		
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_ALPINE_MASTER, Inventory.INV_ID_ALPINE_NON_COMPETING});
		
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_ALPINE_COACH, Inventory.INV_ID_ALPINE_NON_COMPETING});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_FREESTYLE_COACH, Inventory.INV_ID_FREESTYLE_NON_COMPETING});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_SNOWBOARD_COACH, Inventory.INV_ID_SNOWBOARD_NON_COMPETING});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_CROSS_COUNTRY_COACH, Inventory.INV_ID_CROSS_COUNTRY_NON_COMPETING});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_JUMPING_COACH, Inventory.INV_ID_JUMPING_NON_COMPETING});		
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_ALPINE_COACH, Inventory.INV_ID_ALPINE_NON_COMPETING});
		
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_ALPINE_VOLUNTEER, Inventory.INV_ID_ALPINE_NON_COMPETING});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_FREESTYLE_VOLUNTEER, Inventory.INV_ID_FREESTYLE_NON_COMPETING});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_SNOWBOARD_VOLUNTEER, Inventory.INV_ID_SNOWBOARD_NON_COMPETING});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_CROSS_COUNTRY_VOLUNTEER, Inventory.INV_ID_CROSS_COUNTRY_NON_COMPETING});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_JUMPING_VOLUNTEER, Inventory.INV_ID_JUMPING_NON_COMPETING});
		
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_ALPINE_OFFICIAL, Inventory.INV_ID_ALPINE_VOLUNTEER});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_FREESTYLE_OFFICIAL, Inventory.INV_ID_FREESTYLE_VOLUNTEER});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_FREESKIING_OFFICIAL, Inventory.INV_ID_FREESKIING_VOLUNTEER});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_SNOWBOARD_OFFICIAL, Inventory.INV_ID_SNOWBOARD_VOLUNTEER});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_CROSS_COUNTRY_OFFICIAL, Inventory.INV_ID_CROSS_COUNTRY_VOLUNTEER});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_JUMPING_OFFICIAL, Inventory.INV_ID_JUMPING_VOLUNTEER});

		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_ALPINE_COACH, Inventory.INV_ID_ALPINE_VOLUNTEER});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_FREESTYLE_COACH, Inventory.INV_ID_FREESTYLE_VOLUNTEER});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_SNOWBOARD_COACH, Inventory.INV_ID_SNOWBOARD_VOLUNTEER});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_CROSS_COUNTRY_COACH, Inventory.INV_ID_CROSS_COUNTRY_VOLUNTEER});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_JUMPING_COACH, Inventory.INV_ID_JUMPING_VOLUNTEER});
		
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_FREESTYLE_NON_COMPETING, Inventory.INV_ID_FREESTYLE_ROOKIE});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_FREESTYLE_NON_COMPETING, Inventory.INV_ID_FREESKIING_COMPETITOR});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_SNOWBOARD_NON_COMPETING, Inventory.INV_ID_SNOWBOARD_COMPETITOR_REGIONAL});
		
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_ALPINE_NON_COMPETING, Inventory.INV_ID_ALPINE_YOUTH});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_FREESTYLE_NON_COMPETING, Inventory.INV_ID_FREESTYLE_YOUTH});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_JUMPING_NON_COMPETING, Inventory.INV_ID_JUMPING_YOUTH});
		mutuallyExclusiveList.add(new String[] {Inventory.INV_ID_CROSS_COUNTRY_NON_COMPETING, Inventory.INV_ID_CROSS_COUNTRY_YOUTH});
		
		

		
		
		mutuallyExclusiveMemberships = new HashMap<String, Set<String>>();
		for (String[] invIds : mutuallyExclusiveList)
		{
			addMembership(mutuallyExclusiveMemberships, invIds[0], invIds[1]);
			addMembership(mutuallyExclusiveMemberships, invIds[1], invIds[0]);
		}
		
		volunteerMemberships = new HashSet<String>();
		volunteerMemberships.add(Inventory.INV_ID_ALPINE_VOLUNTEER);
		volunteerMemberships.add(Inventory.INV_ID_FREESTYLE_VOLUNTEER);
		volunteerMemberships.add(Inventory.INV_ID_SNOWBOARD_VOLUNTEER);
		volunteerMemberships.add(Inventory.INV_ID_CROSS_COUNTRY_VOLUNTEER);
		volunteerMemberships.add(Inventory.INV_ID_JUMPING_VOLUNTEER);
		
		nonCompetingMemberships = new HashSet<String>();
		nonCompetingMemberships.add(Inventory.INV_ID_ALPINE_NON_COMPETING);
		nonCompetingMemberships.add(Inventory.INV_ID_FREESTYLE_NON_COMPETING);
		nonCompetingMemberships.add(Inventory.INV_ID_SNOWBOARD_NON_COMPETING);
		nonCompetingMemberships.add(Inventory.INV_ID_CROSS_COUNTRY_NON_COMPETING);
		nonCompetingMemberships.add(Inventory.INV_ID_JUMPING_NON_COMPETING);

		restrictedMemberships = new HashSet<String>();
		restrictedMemberships.add(Inventory.INV_ID_ALPINE_ASSOCIATE);
		restrictedMemberships.add(Inventory.INV_ID_DISABLED_ALPINE_ASSOCIATE);
		restrictedMemberships.add(Inventory.INV_ID_CROSS_COUNTRY_ASSOCIATE);
		restrictedMemberships.add(Inventory.INV_ID_DISABLED_CROSS_COUNTRY_ASSOCIATE);
		restrictedMemberships.add(Inventory.INV_ID_FREESTYLE_ASSOCIATE);
		restrictedMemberships.add(Inventory.INV_ID_JUMPING_ASSOCIATE);
		restrictedMemberships.add(Inventory.INV_ID_SNOWBOARD_ASSOCIATE);

		twentyFiveDollarDiscountGroup = new HashSet<String>();
		twentyFiveDollarDiscountGroup.add(Inventory.INV_ID_ALPINE_YOUTH);
		twentyFiveDollarDiscountGroup.add(Inventory.INV_ID_FREESTYLE_YOUTH);
		twentyFiveDollarDiscountGroup.add(Inventory.INV_ID_JUMPING_YOUTH);
		twentyFiveDollarDiscountGroup.add(Inventory.INV_ID_CROSS_COUNTRY_YOUTH);
		twentyFiveDollarDiscountGroup.add(Inventory.INV_ID_FREESTYLE_ROOKIE);
		twentyFiveDollarDiscountGroup.add(Inventory.INV_ID_SNOWBOARD_COMPETITOR_REGIONAL);

		fisByMembership = new HashMap<String, String[]>();
		fisByMembership.put(Inventory.INV_ID_ALPINE_COMPETITOR_U16, new String[]{Inventory.INV_ID_ALPINE_FIS});
		fisByMembership.put(Inventory.INV_ID_DISABLED_ALPINE_COMPETITOR,
				new String[]{Inventory.INV_ID_ALPINE_FIS});
		fisByMembership.put(Inventory.INV_ID_ALPINE_MASTER, new String[]{Inventory.INV_ID_ALPINE_MASTER_FIS});
		fisByMembership.put(Inventory.INV_ID_CROSS_COUNTRY_COMPETITOR_U16, new String[]{Inventory.INV_ID_CROSS_COUNTRY_FIS});
		fisByMembership.put(Inventory.INV_ID_FREESTYLE_COMPETITOR_U15, new String[]{Inventory.INV_ID_FREESTYLE_FIS});
		fisByMembership.put(Inventory.INV_ID_FREESKIING_COMPETITOR_U15, new String[]{Inventory.INV_ID_FREESTYLE_FIS});
		fisByMembership.put(Inventory.INV_ID_SNOWBOARD_COMPETITOR, new String[]{Inventory.INV_ID_SNOWBOARD_FIS});

		//TODO: Is there a problem here?
		fisByMembership.put(Inventory.INV_ID_JUMPING_COMPETITOR_U16, new String[]{Inventory.INV_ID_JUMPING_FIS, Inventory.INV_ID_NORDIC_COMBINED_FIS});
		//TODO: is this missing from the db?
//		fisByMembership.put(Inventory.INV_ID_NORDIC_COMBINED_COMPETITOR, new String[]{Inventory.INV_ID_NORDIC_COMBINED_FIS});

		membershipsExemptFromDues = new HashSet<String>();
		membershipsExemptFromDues.add(Inventory.INV_ID_DISABLED_ALPINE_ASSOCIATE);
		membershipsExemptFromDues.add(Inventory.INV_ID_DISABLED_ALPINE_COACH);
		membershipsExemptFromDues.add(Inventory.INV_ID_DISABLED_ALPINE_COMPETITOR);
		membershipsExemptFromDues.add(Inventory.INV_ID_DISABLED_CROSS_COUNTRY_ASSOCIATE);
		membershipsExemptFromDues.add(Inventory.INV_ID_DISABLED_CROSS_COUNTRY_COACH);
		membershipsExemptFromDues.add(Inventory.INV_ID_DISABLED_CROSS_COUNTRY_COMPETITOR);

		disabledFisMemberships = new HashSet<String>();
		//disabledFisMemberships.add(Inventory.INV_ID_ALPINE_SKIING_DISABLED_LICENSE_FIS);
		disabledFisMemberships.add(Inventory.INV_ID_ALP_PTS_CONF_FIS);
		//disabledFisMemberships.add(Inventory.INV_ID_LATE_ALPINE_SKIING_DISABLED_LICENSE_FIS);

		youthMemberships = new HashSet<String>();
		youthMemberships.add(Inventory.INV_ID_ALPINE_YOUTH);
		youthMemberships.add(Inventory.INV_ID_CROSS_COUNTRY_YOUTH);
		youthMemberships.add(Inventory.INV_ID_FREESTYLE_ROOKIE);
		youthMemberships.add(Inventory.INV_ID_JUMPING_YOUTH);
		youthMemberships.add(Inventory.INV_ID_FREESTYLE_YOUTH);

		officialMemberships = RuleAssociations.coachesByOfficial.keySet();
		coachMemberships = RuleAssociations.officialsByCoach.keySet();


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

	private static void addMembership(Map<String, Set<String>> memberships, String invId, String childInvId)
	{
		Set<String> excludedMemberships = memberships.get(invId);
		if(excludedMemberships == null)
		{
			excludedMemberships = new HashSet<String>();
			memberships.put(invId, excludedMemberships);
		}
		excludedMemberships.add(childInvId);
	}
}
