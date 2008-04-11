package org.ussa.dao.impl;

import java.util.List;

import org.ussa.app.dao.hibernate.GenericDaoHibernate;
import org.ussa.dao.InventoryDao;
import org.ussa.model.Inventory;


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
		StringBuilder query = new StringBuilder("select distinct i from Inventory i where inventoryType='MEMBERSHIP' and i.active='Y' ");
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

	public List<Inventory> getIventoryByTypeAndSportCode(String inventoryType, String sportCode)
	{
		StringBuilder query = new StringBuilder("select i from Inventory i where inventoryType='")
				.append(inventoryType).append("' and sportCode='").append(sportCode).append("' and i.active='Y' ");

		List<Inventory> list = getHibernateTemplate().find(query.toString());
		return list;
	}

}