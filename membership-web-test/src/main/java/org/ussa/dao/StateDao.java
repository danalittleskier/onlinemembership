package org.ussa.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import org.ussa.model.State;

public interface StateDao extends GenericDao<State, String>
{
   public List<State> getAllStateUS_CodeOrdered();
   public List<State> getAllStateUS_DescriptionOrdered();

}
