package org.ussa.dao;

import org.ussa.common.dao.GenericDao;
import org.ussa.model.Division;

public interface DivisionDao extends GenericDao<Division, String>
{

    /**
     * Get the foreign division ("X")
     */
    public Division getForeignDivision();
}
