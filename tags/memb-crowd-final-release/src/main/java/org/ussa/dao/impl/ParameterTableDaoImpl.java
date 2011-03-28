package org.ussa.dao.impl;

import org.ussa.common.dao.hibernate.GenericDaoHibernate;
import org.ussa.dao.ParameterTableDao;
import org.ussa.model.ParameterTable;

public class ParameterTableDaoImpl  extends GenericDaoHibernate<ParameterTable, String> implements ParameterTableDao
{
	public ParameterTableDaoImpl()
	{
		super(ParameterTable.class);
	}
}
