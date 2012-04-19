package org.ussa.dao.impl;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;
import org.ussa.dao.CoachesEducationDao;

public class CoachesEducationDaoImpl implements CoachesEducationDao{

	private DataSource dataSource;

	private String GET_COACHES_CLINICS_SQL = "select * from COACHCLINICHIST where ussa_id=?";
	private String COACH_LEVEL_SQL = "select * from COACHCERT where ussa_id=?";

	public List<String> getCoachesCourses(Long ussaId){
		
		CoachesClinicsQuery sq = new CoachesClinicsQuery(getDataSource());
		Object [] parameters = {ussaId};
		List<String> courses= sq.execute(parameters);		
		//return (courses.isEmpty() ? courses : null);
		return courses;
	    
		
	}
	
	private class CoachesClinicsQuery extends MappingSqlQuery {
		CoachesClinicsQuery(DataSource ds) {
		    super(ds, GET_COACHES_CLINICS_SQL);
		    declareParameter(new SqlParameter(Types.VARCHAR));
		}

		public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		    return resultSet.getString("clinic_type");

		}
	    }
		
public List<String> getCoachLevel(Long ussaId){
		
		CoachLevelQuery sq = new CoachLevelQuery(getDataSource());
		Object [] parameters = {ussaId};
		List<String> level= sq.execute(parameters);		
		//return (courses.isEmpty() ? courses : null);
		return level;
	    
		
	}
	
	private class CoachLevelQuery extends MappingSqlQuery {
		CoachLevelQuery(DataSource ds) {
		    super(ds, COACH_LEVEL_SQL);
		    declareParameter(new SqlParameter(Types.VARCHAR));
		}

		public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		    return resultSet.getString("coach_tier");

		}
	    }

	public void setDataSource(final DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	private DataSource getDataSource()
	{
		return this.dataSource;
	}

}
