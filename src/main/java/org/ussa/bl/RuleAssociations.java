package org.ussa.bl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.ussa.model.Inventory;

public class RuleAssociations
{
	public static final Map<String, String> officialsByCoach;
	public static final Map<String, String> coachesByOfficial;
	public static final Set<String> twentyFiveDollarDiscountGroup;
	public static final Map<String, String[]> fisByMembership;
	public static final Set<String> disabledFisMemberships;
	public static final Set<String> youthMemberships;
	public static final Set<String> officialMemberships;
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
				new String[]{Inventory.INV_ID_ALPINE_SKIING_DISABLED_LICENSE_FIS, Inventory.INV_ID_ALPINE_FIS, Inventory.INV_ID_ALP_PTS_CONF_FIS});
		fisByMembership.put(Inventory.INV_ID_ALPINE_MASTER, new String[]{Inventory.INV_ID_ALPINE_MASTER_FIS});
		fisByMembership.put(Inventory.INV_ID_CROSS_COUNTRY_COMPETITOR, new String[]{Inventory.INV_ID_CROSS_COUNTRY_FIS});
		fisByMembership.put(Inventory.INV_ID_FREESTYLE_COMPETITOR, new String[]{Inventory.INV_ID_FREESTYLE_FIS});
		fisByMembership.put(Inventory.INV_ID_SNOWBOARD_COMPETITOR, new String[]{Inventory.INV_ID_SNOWBOARD_FIS});
		fisByMembership.put(Inventory.INV_ID_JUMPING_COMPETITOR, new String[]{Inventory.INV_ID_JUMPING_FIS});
		fisByMembership.put(Inventory.INV_ID_NORDIC_COMBINED_COMPETITOR, new String[]{Inventory.INV_ID_NORDIC_COMBINED_FIS});

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

	}
}
