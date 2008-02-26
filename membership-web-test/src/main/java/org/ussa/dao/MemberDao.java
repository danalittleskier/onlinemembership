package org.ussa.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import org.ussa.model.Member;

public interface MemberDao extends GenericDao<Member, Long>
{
    //public List<Member> findByLastName(String lastName);

   // public Member findByUssaId(Integer ussaId);

}
