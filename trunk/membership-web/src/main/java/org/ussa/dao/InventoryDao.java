package org.ussa.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import org.ussa.model.Inventory;


public interface InventoryDao extends GenericDao<Inventory, String>
{
    public List<Inventory> getAllMembershipTypes();
    public List<Inventory> getAllSportsCodes();
    public List<Inventory> getAllSportsCodesByAge(Integer age);
    public List<Inventory> getAllMembershipsByAge(Integer age);
}
