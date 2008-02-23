package org.ussa.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;


import org.ussa.dao.AddressDao;
import org.ussa.model.Address;

public class AddressDaoImpl extends GenericDaoHibernate<Address, Long> implements AddressDao
{

    public AddressDaoImpl() {
        super(Address.class);
    }

}
