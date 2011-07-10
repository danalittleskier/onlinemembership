package org.ussa.dao;

import java.util.List;

public interface DisciplineTrackingDao {

	public abstract List<String> getDisciplines(String sportCode);

}