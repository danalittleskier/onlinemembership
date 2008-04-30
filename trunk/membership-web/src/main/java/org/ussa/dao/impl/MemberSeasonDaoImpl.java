package org.ussa.dao.impl;

import java.util.List;

import org.ussa.common.dao.hibernate.GenericDaoHibernate;
import org.ussa.dao.MemberSeasonDao;
import org.ussa.model.MemberSeason;
import org.ussa.model.MemberSeasonPk;

public class MemberSeasonDaoImpl extends GenericDaoHibernate<MemberSeason, MemberSeasonPk> implements MemberSeasonDao
{
	public MemberSeasonDaoImpl()
	{
		super(MemberSeason.class);
	}

	public MemberSeason getMostRecentBackgroundCheck(Long ussaId)
	{
		List<MemberSeason> seasons = (List<MemberSeason>) getHibernateTemplate()
				.find("from MemberSeason s where s.memberSeasonPk.member.id = ? and s.memberSeasonPk.season = " +
						"(select max(ms.memberSeasonPk.season) from MemberSeason ms where ms.memberSeasonPk.member.id = ? and ms.backgroundCheckFlag = 'Y')",
						new Object[]{ussaId, ussaId});

		if(seasons != null && seasons.size() > 0)
		{
			return seasons.get(0);
		}
		else
		{
			return null;
		}
	}
}
