package org.ussa.dao.impl;

import org.ussa.common.dao.hibernate.GenericDaoHibernate;
import org.ussa.dao.MemberClubDao;
import org.ussa.model.MemberClub;

public class MemberClubDaoImpl extends GenericDaoHibernate<MemberClub, Long> implements MemberClubDao
{
	public MemberClubDaoImpl()
	{
		super(MemberClub.class);
	}
}
