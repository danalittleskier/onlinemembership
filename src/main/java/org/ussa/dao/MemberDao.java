package org.ussa.dao;

import org.ussa.common.dao.GenericDao;
import org.ussa.model.Member;

import java.util.Date;
import java.util.List;


public interface MemberDao extends GenericDao<Member, Long>
{
    List<Member> getDuplicateCandidates(String firstName, String lastName, Date birthDate);
    public Member getMaxNensaId();
}
