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
import org.ussa.dao.DisciplineTrackingDao;


public class DisciplineTrackingDaoImpl implements DisciplineTrackingDao {
	
	private DataSource dataSource;

	private String DISCIPLINES_SQL = "select * from discipline where active='Y' and sport_code=?";
	private String INSERT_DISCIPLINES_SQL="insert into disciplinetracking (ussa_id, discipline_code,sport_code) values (?,?,?)";
	
	/* (non-Javadoc)
	 * @see org.ussa.dao.impl.DisciplineTrackingDao#getDisciplines(java.lang.String)
	 */
	public List<String> getDisciplines(String sportCode){
		try {
		    setDataSource((DataSource) new InitialContext().lookup("java:comp/env/jdbc/APSDS"));
		} catch (Exception ex) {
		}
		
		DisciplinesQuery sq = new DisciplinesQuery(this.dataSource);
		Object [] parameters = {sportCode};
		List<String> disciplines= sq.execute(parameters);
		return disciplines;
	    
		
	}
	
	private class DisciplinesQuery extends MappingSqlQuery {
		DisciplinesQuery(DataSource ds) {
		    super(ds, DISCIPLINES_SQL);
		    declareParameter(new SqlParameter(Types.VARCHAR));
		}

		public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		    return resultSet.getString("discipline_code");

		}
	    }
	
	public void insertDisciplines(String ussaId, String discipline, String sport){
		InsertDisciplines bm = new InsertDisciplines(getDataSource());
		Object[] params = {ussaId, discipline,sport};
		bm.update(params);
	}
	
	private class InsertDisciplines extends SqlUpdate
	{
		public InsertDisciplines(DataSource ds)
		{

			super(ds, INSERT_DISCIPLINES_SQL);
			declareParameter(new SqlParameter(Types.NUMERIC));
			declareParameter(new SqlParameter(Types.NUMERIC));
			declareParameter(new SqlParameter(Types.VARCHAR));

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
