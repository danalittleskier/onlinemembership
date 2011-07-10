package org.ussa.dao;

import java.util.List;

import org.ussa.common.dao.GenericDao;
import org.ussa.model.DivisionAffiliation;

public interface DivisionAffiliationDao extends GenericDao<DivisionAffiliation, String>
{

	public List<DivisionAffiliation> getDivisionAffiliations(String stateCode);

	public List<DivisionAffiliation> getDivisionAffiliations(String stateCode, String zipCode);
}
