package org.ussa.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.ussa.dao.NationDao;
import org.ussa.model.Nation;

public class NationDaoImpl extends GenericDaoHibernate<Nation, String> implements NationDao
{
	public NationDaoImpl()
	{
		super(Nation.class);
	}

	public List<Nation> getAllNations()
	{
		StringBuilder query = new StringBuilder("select n from Nation n");
		return (List<Nation>) getHibernateTemplate().find(query.toString());
	}

}
