package org.ussa.dao.impl;

import org.ussa.common.dao.hibernate.GenericDaoHibernate;
import org.ussa.dao.MemberLegalDao;
import org.ussa.model.MemberLegal;
import org.ussa.model.MemberSeasonPk;

public class MemberLegalDaoImpl extends GenericDaoHibernate<MemberLegal, MemberSeasonPk> implements MemberLegalDao
{
	public MemberLegalDaoImpl()
	{
		super(MemberLegal.class);
	}
}
