package com.ussa.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;


import com.ussa.dao.AddressDao;
import com.ussa.model.Address;

public class AddressDaoImpl extends GenericDaoHibernate<Address, Long> implements AddressDao
{

    public AddressDaoImpl() {
        super(Address.class);
    }

}
