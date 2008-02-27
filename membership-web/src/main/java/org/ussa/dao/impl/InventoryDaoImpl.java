package org.ussa.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.ussa.dao.InventoryDao;
import org.ussa.model.Inventory;


public class InventoryDaoImpl extends GenericDaoHibernate<Inventory, String> implements InventoryDao {

    public InventoryDaoImpl() {
        super(Inventory.class);
    }

    public List<Inventory> getAllMemberships_TypeOrdered()
    {
        StringBuilder query = new StringBuilder("select i from Inventory i where lower(inventoryType)='membership' and lower(i.active) = 'y' order by i.description asc");
        List<Inventory> objs = getHibernateTemplate().find(query.toString());
        return objs;
    }

    public List<Inventory> getAllSports_TypeOrdered()
    {
        StringBuilder query = new StringBuilder("select i from Inventory i where lower(inventoryType)='membership' and lower(i.active) = 'y' order by i.description asc");
        List<Inventory> objs = getHibernateTemplate().find(query.toString());
        return objs;
    }
}
