package org.ussa.dao.impl;

import org.ussa.common.dao.hibernate.GenericDaoHibernate;
import org.ussa.dao.MemberTransactionDao;
import org.ussa.model.MemberTransaction;

/**
 * User: jminer
 * Date: Apr 15, 2008
 * Time: 7:09:46 AM
 */
public class MemberTransactionDaoImpl extends GenericDaoHibernate<MemberTransaction, Long> implements MemberTransactionDao {
    /**
     * Constructor that takes in a class to see which type of entity to persist
     *
     * @param persistentClass the class type you'd like to persist
     */
    public MemberTransactionDaoImpl() {
        super(MemberTransaction.class);
    }
}
