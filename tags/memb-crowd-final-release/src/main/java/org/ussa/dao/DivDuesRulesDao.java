package org.ussa.dao;

import java.util.List;

import org.ussa.model.Inventory;

public interface DivDuesRulesDao
{
	public List<Inventory> getDivisionDues(String divisionCode, Integer currentSeasonAge, List<String> membershipIds, boolean late);
}
