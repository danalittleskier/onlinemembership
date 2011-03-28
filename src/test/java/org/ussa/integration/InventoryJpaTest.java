package org.ussa.integration;

import org.ussa.dao.InventoryDao;
import org.ussa.model.Inventory;

/**
 * User: jminer
 * Date: Apr 28, 2008
 * Time: 9:24:59 AM
 */
public class InventoryJpaTest extends AbstractUssaIntegrationTests {
    InventoryDao inventoryDao;

    public void testGetInventoryByTypeAndSportCode() {
        Object result = inventoryDao.getIventoryByTypeAndSportCode(
                Inventory.INVENTORY_TYPE_DONATION, Inventory.SPORT_CODE_ALP);
        assertNotNull(result);
    }

    public void setInventoryDao(InventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }
}
