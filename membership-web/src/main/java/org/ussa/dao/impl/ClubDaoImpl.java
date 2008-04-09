package org.ussa.dao.impl;

import java.util.List;

import org.ussa.app.dao.hibernate.GenericDaoHibernate;
import org.ussa.dao.ClubDao;
import org.ussa.model.Club;

public class ClubDaoImpl extends GenericDaoHibernate<Club, Long> implements ClubDao
{

	public ClubDaoImpl()
	{
		super(Club.class);
	}

	public List<Club> findByStateCode(String stateCode)
	{
		//Delphi: select club_name from member where member_type = 'c' and state_code = ? order by club_name
		StringBuilder query = new StringBuilder("select c from Club c where lower(c.type)='c' and ");
		query.append(" lower(c.stateCode)=lower('").append(stateCode).append("') order by c.name");
		//System.out.println("club query["+query.toString()+"]");
		List<Club> clubs = getHibernateTemplate().find(query.toString());
		return clubs;
	}

	public Club getClub(Long id)
	{
		return (Club) getHibernateTemplate().get(Club.class, id);
	}
}
