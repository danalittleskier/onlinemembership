package org.ussa.dao;

import java.util.List;

import org.ussa.common.dao.GenericDao;
import org.ussa.model.Inventory;


public interface InventoryDao extends GenericDao<Inventory, String>
{
	public List<Inventory> getAllMembershipTypes();

	public List<Inventory> getAllMembershipsByAge(Integer age);

	public List<Inventory> getAllMembershipsByCriteria(Integer age, String sportCode);

	public List<Inventory> getIventoryByType(String inventoryType);

	public List<Inventory> getIventoryByTypeAndSportCode(String inventoryType, String sportCode);
}
