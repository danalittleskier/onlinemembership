package org.ussa.dao;

import java.util.List;

public interface CoachesEducationDao {

	public abstract List<String> getCoachesCourses(Long ussaId);
	public abstract List<String> getCoachLevel(Long ussaId);
}
