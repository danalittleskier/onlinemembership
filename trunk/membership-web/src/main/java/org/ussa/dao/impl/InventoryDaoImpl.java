package org.ussa.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.ussa.dao.InventoryDao;
import org.ussa.model.Inventory;

public class InventoryDaoImpl extends GenericDaoHibernate<Inventory, String> implements InventoryDao {

    public InventoryDaoImpl() {
        super(Inventory.class);
    }
}
