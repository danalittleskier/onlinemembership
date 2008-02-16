package com.ussa.manager.impl;

import com.ussa.dao.MemberDao;
import com.ussa.model.Member;
import com.ussa.manager.MemberManager;
import org.appfuse.service.impl.GenericManagerImpl;

import java.util.List;

public class MemberManagerImpl extends GenericManagerImpl<Member, Long> implements MemberManager {
    MemberDao memberDao;

    public MemberManagerImpl(MemberDao memberDao) {
        super(memberDao);
        this.memberDao = memberDao;
    }

}









