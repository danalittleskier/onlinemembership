package org.ussa.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.ussa.dao.InventoryDao;
import org.ussa.model.Inventory;


public class InventoryDaoImpl extends GenericDaoHibernate<Inventory, String> implements InventoryDao {

    public InventoryDaoImpl() {
        super(Inventory.class);
    }

    public List<Inventory> getAllMembershipTypes()
    {
        StringBuilder query = new StringBuilder("select i from Inventory i where lower(inventoryType)='membership' and lower(i.active)='y' order by i.description asc");
        List<Inventory> objs = getHibernateTemplate().find(query.toString());
        return objs;
    }
    public List<String> getAllSportCodes()
    {
        //List<String> objs = new ArrayList<String>();
        //objs.add("All");
        StringBuilder query = new StringBuilder("select distinct i.sportCode from Inventory i where lower(i.active)='y' and i.sportCode is not null order by i.sportCode asc");
        List<String> objs = getHibernateTemplate().find(query.toString());
        return objs;
    }

    public List<Inventory> getAllMembershipsByAge(Integer age)
    {
        StringBuilder query = new StringBuilder("select distinct i from Inventory i where lower(inventoryType)='membership' and lower(i.active)='y' ");
        if (age != null)
        {
            query.append(" and (i.ageFrom <= '").append(age).append("' or i.ageFrom is null)");
            query.append(" and (i.ageTo   >= '").append(age).append("' or i.ageTo is null)");
        }
        List<Inventory> objs = getHibernateTemplate().find(query.toString());
        return objs;
    }
    public List<Inventory> getAllMembershipsByCriteria(Integer age, String sportCode)
    {
        StringBuilder query = new StringBuilder("select distinct i from Inventory i where lower(inventoryType)='membership' and lower(i.active)='y' ");
        if (age != null)
        {
            query.append(" and (i.ageFrom <= '").append(age).append("' or i.ageFrom is null)");
            query.append(" and (i.ageTo   >= '").append(age).append("' or i.ageTo is null)");
        }
        if (sportCode != null)
        {
            query.append(" and lower(i.sportCode)=lower('").append(sportCode).append("')");
        }

        List<Inventory> objs = getHibernateTemplate().find(query.toString());
        return objs;
    }

}
