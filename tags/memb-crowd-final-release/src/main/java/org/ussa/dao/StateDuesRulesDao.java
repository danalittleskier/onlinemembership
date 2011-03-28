package org.ussa.dao;

import java.util.List;

import org.ussa.model.Inventory;

public interface StateDuesRulesDao
{
	public List<Inventory> getStateDues(String stateCode, Integer currentSeasonAge, List<String> membershipIds, boolean late);
}
