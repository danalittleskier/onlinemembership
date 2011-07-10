package org.ussa.dao.impl;

import org.ussa.common.dao.hibernate.GenericDaoHibernate;
import org.ussa.dao.StateDao;
import org.ussa.model.State;

import java.util.List;

public class StateDaoImpl extends GenericDaoHibernate<State, String> implements StateDao
{

	public StateDaoImpl()
	{
		super(State.class);
	}

	public List<State> getAllUsStatesOrderedByCode()
	{
		StringBuilder query = new StringBuilder("select s from State s where lower(s.us)='y' order by s.id asc ");
		List<State> objs = getHibernateTemplate().find(query.toString());
		return objs;
	}

	public List<State> getAllUsStatesOrderedByDescription()
	{
		StringBuilder query = new StringBuilder("select s from State s where lower(s.us)='y' order by s.description asc ");
		List<State> objs = getHibernateTemplate().find(query.toString());
		return objs;
	}

    public List<State> getAllStatesOrderedByCode() {
        // "where s.id is not null" because there was/is a row in the database
        // that has null values in every column (yes, really)
        StringBuilder query = new StringBuilder("select s from State s where s.id is not null order by s.id asc ");
        List<State> objs = getHibernateTemplate().find(query.toString());
        return objs;
    }

}
