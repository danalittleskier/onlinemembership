package org.ussa.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.ussa.model.ParameterTable;

import java.io.Serializable;

/**
 * User: jminer
 * Date: Apr 9, 2008
 * Time: 5:41:38 PM
 */
public class UssaIdentifierGenerator implements IdentifierGenerator {
    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        Session s = (Session) session;
        ParameterTable ussaParam = (ParameterTable) s.load(ParameterTable.class, ParameterTable.USSAID, LockMode.UPGRADE);
        Long last = Long.valueOf(ussaParam.getParameterData());
        
        Long newId = last + 1;
        ussaParam.setParameterData(String.valueOf(newId));
        return newId;
    }
}
