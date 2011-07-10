package org.ussa.dao;

import java.util.List;

import org.ussa.common.dao.GenericDao;
import org.ussa.model.InventoryAdd;

public interface InventoryAddDao extends GenericDao<InventoryAdd, Long>
{
	public List<InventoryAdd> getInventoryAddByInvId(String selectedInvId, String divisionCode);
}
