package org.ussa.dao.impl;

import java.util.List;

import org.ussa.common.dao.hibernate.GenericDaoHibernate;
import org.ussa.dao.InventoryAddDao;
import org.ussa.model.InventoryAdd;

public class InventoryAddDaoImpl extends GenericDaoHibernate<InventoryAdd, Long> implements InventoryAddDao
{
	public InventoryAddDaoImpl()
	{
		super(InventoryAdd.class);
	}

	public List<InventoryAdd> getInventoryAddByInvId(String selectedInvId, String divisionCode)
	{
		StringBuilder query = new StringBuilder("select i from InventoryAdd i where selInvId = ? and ");
		query.append("(divisionCode is null or divisionCode = ?) ");

		List<InventoryAdd> inventory = getHibernateTemplate().find(query.toString(), new Object[] {selectedInvId, divisionCode});
		return inventory;

	}
}
