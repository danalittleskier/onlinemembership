package org.ussa.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.ussa.dao.DivisionDao;
import org.ussa.model.Division;

public class DivisionDaoImpl extends GenericDaoHibernate<Division, Long> implements DivisionDao
{

	public DivisionDaoImpl()
	{
		super(Division.class);
	}

	public Division getDivision(String divisionCode)
	{
		return (Division) getHibernateTemplate().get(Division.class, divisionCode);
	}
}
