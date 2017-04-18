package org.ussa.dao.impl;

import org.ussa.common.dao.hibernate.GenericDaoHibernate;
import org.ussa.dao.InventoryDao;
import org.ussa.model.Inventory;

import java.util.List;


public class InventoryDaoImpl extends GenericDaoHibernate<Inventory, String> implements InventoryDao
{

	public InventoryDaoImpl()
	{
		super(Inventory.class);
	}

	public List<Inventory> getAllMembershipTypes()
	{
		StringBuilder query = new StringBuilder("select i from Inventory i where inventoryType='MEMBERSHIP' and i.active='Y' order by i.description asc");
		List<Inventory> objs = getHibernateTemplate().find(query.toString());
		return objs;
	}

	public List<Inventory> getAllMembershipsByAge(Integer age)
	{
		StringBuilder query = new StringBuilder("select distinct i from Inventory i where inventoryType='MEMBERSHIP' and i.active='Y' ");
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
		StringBuilder query = new StringBuilder("select distinct i from Inventory i where inventoryType='MEMBERSHIP' and i.active='Y' and i.id<>'SX' ");
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

	public List<Inventory> getIventoryByType(String inventoryType)
	{
		StringBuilder query = new StringBuilder("select i from Inventory i where inventoryType='")
				.append(inventoryType).append("' and i.active='Y' ");

		List<Inventory> list = getHibernateTemplate().find(query.toString());
		return list;
	}

    @SuppressWarnings("unchecked")
    public List<Inventory> getIventoryByTypeAndSportCode(String inventoryType, String sportCode)
	{
		return getHibernateTemplate().findByNamedQueryAndNamedParam(Inventory.QUERY_BY_TYPE_AND_SPORT_CODE, 
                new String[]{"inventoryType", "sportCode"}, new Object[]{inventoryType, sportCode});
	}

}
