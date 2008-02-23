package org.ussa.manager.impl;

import org.ussa.dao.MemberDao;
import org.ussa.model.Member;
import org.ussa.manager.MemberManager;
import org.appfuse.service.impl.GenericManagerImpl;
import java.util.List;

public class MemberManagerImpl extends GenericManagerImpl<Member, Long> implements MemberManager {
    MemberDao memberDao;

    public MemberManagerImpl(MemberDao memberDao) {
        super(memberDao);
        this.memberDao = memberDao;
    }
/*
    public List<Member> findByLastName(String lastName) {
        return memberDao.findByLastName(lastName);
    }

    public Member findByUssaId(Integer ussaId) {
        return memberDao.findByUssaId(ussaId);
    }
*/
}









