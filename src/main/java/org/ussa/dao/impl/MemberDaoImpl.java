package org.ussa.dao.impl;

import org.ussa.common.dao.hibernate.GenericDaoHibernate;
import org.ussa.dao.MemberDao;
import org.ussa.model.Member;

public class MemberDaoImpl extends GenericDaoHibernate<Member, Long> implements MemberDao {

    public MemberDaoImpl() {
        super(Member.class);
    }

    /**
     * Need to override to help hibernate deal with the OneToOne
     * relationship with ParentInfo.  HibernateTemplate().merge
     * has problems with the ID generation, whereas HibernateTemplate.saveOrUpdate
     * does not.
     */
    @Override
    public Member save(Member member) {
        // must set the back reference to member in order for the 
        // primary key to be set correctly on ParentInfo!
        if (member.getParentInfo() != null) {
            member.getParentInfo().setMember(member);
        }
        getHibernateTemplate().saveOrUpdate(member);
        return member;
    }
}
