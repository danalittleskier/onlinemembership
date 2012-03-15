package org.ussa.dao;

import org.ussa.common.dao.GenericDao;
import org.ussa.model.MemberSeasonPk;
import org.ussa.model.MemberSeason;

public interface MemberSeasonDao extends GenericDao<MemberSeason, MemberSeasonPk>
{
	public MemberSeason getMostRecentSafeSportCheck(Long ussaId);
	public MemberSeason getMostRecentBackgroundCheck(Long ussaId);
	public MemberSeason hasMemberSeasonForCurrentSeason(MemberSeasonPk memberSeasonPk);
	public MemberSeason getOnlyRenewalNeededBackgroundFlag(Long ussaId,String season);

}
