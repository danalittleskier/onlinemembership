package org.ussa.dao;

import java.util.List;

import org.ussa.model.Inventory;

public interface RenewRuleInvDao
{
	public List<Inventory> getRecommendedMemberships(Long ussaID, Integer currentSeasonAge, String lastSeason);
}
