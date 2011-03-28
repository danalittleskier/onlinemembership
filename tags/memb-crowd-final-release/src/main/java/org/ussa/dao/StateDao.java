package org.ussa.dao;

import org.ussa.common.dao.GenericDao;
import org.ussa.model.State;

import java.util.List;

public interface StateDao extends GenericDao<State, String>
{
   public List<State> getAllUsStatesOrderedByCode();
   public List<State> getAllUsStatesOrderedByDescription();
   public List<State> getAllStatesOrderedByCode();
}
