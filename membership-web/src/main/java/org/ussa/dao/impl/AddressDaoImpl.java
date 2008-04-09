package org.ussa.dao.impl;

import org.ussa.app.dao.hibernate.GenericDaoHibernate;
import org.ussa.dao.AddressDao;
import org.ussa.model.Address;
import org.ussa.model.AddressPk;

public class AddressDaoImpl extends GenericDaoHibernate<Address, AddressPk> implements AddressDao
{

    public AddressDaoImpl() {
        super(Address.class);
    }

}
