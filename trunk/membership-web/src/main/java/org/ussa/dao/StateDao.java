package org.ussa.dao;

import java.util.List;

import org.ussa.common.dao.GenericDao;
import org.ussa.model.State;

public interface StateDao extends GenericDao<State, String>
{
   public List<State> getAllUsStatesOrderedByCode();
   public List<State> getAllUsStatesOrderedByDescription();

}
