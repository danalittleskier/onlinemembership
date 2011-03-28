package org.ussa.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.ussa.common.dao.hibernate.GenericDaoHibernate;
import org.ussa.dao.DivisionAffiliationDao;
import org.ussa.model.DivisionAffiliation;

public class DivisionAffiliationDaoImpl extends GenericDaoHibernate<DivisionAffiliation, String> implements DivisionAffiliationDao
{
    public DivisionAffiliationDaoImpl()
	{
		super(DivisionAffiliation.class);
	}

	public List<DivisionAffiliation> getDivisionAffiliations(String stateCode)
	{
		return (List<DivisionAffiliation>) getHibernateTemplate()
				.find("from DivisionAffiliation a where a.stateCode = ?", new Object[]{stateCode});
	}

	public List<DivisionAffiliation> getDivisionAffiliations(String stateCode, String zipCode)
	{
		if(StringUtils.isNotBlank(zipCode))
		{
			Object[] params = new Object[]{stateCode, zipCode};
			return (List<DivisionAffiliation>) getHibernateTemplate()
					.find("from DivisionAffiliation a where a.stateCode = ? and a.zipCode = ?", params);
		}
		return getDivisionAffiliations(stateCode);
	}
}
