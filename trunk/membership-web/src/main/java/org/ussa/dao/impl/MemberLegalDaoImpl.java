package org.ussa.dao.impl;

import org.ussa.app.dao.hibernate.GenericDaoHibernate;
import org.ussa.dao.MemberLegalDao;
import org.ussa.model.MemberLegal;
import org.ussa.model.MemberLegalPk;

public class MemberLegalDaoImpl extends GenericDaoHibernate<MemberLegal, MemberLegalPk> implements MemberLegalDao
{
	public MemberLegalDaoImpl()
	{
		super(MemberLegal.class);
	}


	public MemberLegal get(MemberLegalPk memberLegalPk)
	{
		return (MemberLegal) getHibernateTemplate().get(MemberLegal.class, memberLegalPk);
	}
}
