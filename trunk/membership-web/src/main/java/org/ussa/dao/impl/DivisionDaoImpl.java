package org.ussa.dao.impl;

import org.ussa.common.dao.hibernate.GenericDaoHibernate;
import org.ussa.dao.DivisionDao;
import org.ussa.model.Division;

public class DivisionDaoImpl extends GenericDaoHibernate<Division, String> implements DivisionDao
{

	public DivisionDaoImpl()
	{
		super(Division.class);
	}
}
