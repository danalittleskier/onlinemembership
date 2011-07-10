package org.ussa.integration;

import org.ussa.dao.StateDao;
import org.ussa.model.State;

import java.util.List;

/**
 * User: jminer
 * Date: Apr 29, 2008
 * Time: 9:54:02 PM
 */
public class StateDaoTest extends AbstractUssaIntegrationTests {
    private StateDao stateDao;

    public void testGetAllStates() {
        List<State> states = stateDao.getAllStatesOrderedByCode();
        assertNotNull(states);
        assertNotNull("first state is null", states.get(0));
    }

    public void setStateDao(StateDao stateDao) {
        this.stateDao = stateDao;
    }
}
