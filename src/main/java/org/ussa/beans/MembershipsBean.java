package org.ussa.beans;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import org.ussa.model.Inventory;

public class MembershipsBean implements Serializable
{
	private List<Inventory> unrestrictedMemberships = new ArrayList<Inventory>();
	private List<Inventory> restrictedMemberships = new ArrayList<Inventory>();
	private List<Inventory> shortTermMemberships = new ArrayList<Inventory>();

	public void addUnrestrictedMembership(Inventory inventory)
	{
		unrestrictedMemberships.add(inventory);
	}

	public List<Inventory> getUnrestrictedMemberships()
	{
		return unrestrictedMemberships;
	}

	public void setUnrestrictedMemberships(List<Inventory> unrestrictedMemberships)
	{
		this.unrestrictedMemberships = unrestrictedMemberships;
	}

	public void addRestrictedMembership(Inventory inventory)
	{
		restrictedMemberships.add(inventory);
	}

	public List<Inventory> getRestrictedMemberships()
	{
		return restrictedMemberships;
	}

	public void setRestrictedMemberships(List<Inventory> restrictedMemberships)
	{
		this.restrictedMemberships = restrictedMemberships;
	}

	public List<Inventory> getShortTermMemberships() {
		return shortTermMemberships;
	}

	public void setShortTermMemberships(List<Inventory> shortTerm) {
		this.shortTermMemberships = shortTerm;
	}
	
}
