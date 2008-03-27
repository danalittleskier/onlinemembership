package org.ussa.dao;

import org.appfuse.dao.GenericDao;
import org.ussa.model.Division;

public interface DivisionDao extends GenericDao<Division, Long>
{
	public Division getDivision(String divisionCode);
}
