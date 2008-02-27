package org.ussa.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import org.ussa.model.Inventory;


public interface InventoryDao extends GenericDao<Inventory, String>
{
    public List<Inventory> getAllSports_TypeOrdered();
    public List<Inventory> getAllMemberships_TypeOrdered();

}
