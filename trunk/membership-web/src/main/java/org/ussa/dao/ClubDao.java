package org.ussa.dao;

import java.util.List;

import org.ussa.common.dao.GenericDao;
import org.ussa.model.Club;
import org.ussa.model.Member;

public interface ClubDao extends GenericDao<Club, Long>
{
	public List<Club> findByStateCode(String stateCode);

	public Club getClub(Long id);
	
	public List<Club> findByMember(Member member);
}
