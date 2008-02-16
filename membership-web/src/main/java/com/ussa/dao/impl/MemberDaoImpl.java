package com.ussa.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.ussa.dao.MemberDao;
import com.ussa.model.Member;

public class MemberDaoImpl extends GenericDaoHibernate<Member, Long> implements MemberDao {

    public MemberDaoImpl() {
        super(Member.class);
    }

}
