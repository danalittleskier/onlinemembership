package org.ussa.dao;

import java.util.List;

import org.ussa.app.dao.GenericDao;
import org.ussa.model.Club;

public interface ClubDao extends GenericDao<Club, Long>
{
	public List<Club> findByStateCode(String stateCode);

	public Club getClub(Long id);
}
