package com.ussa.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.ussa.dao.MemberDao;
import com.ussa.model.Member;

public class MemberDaoImpl extends GenericDaoHibernate<Member, Long> implements MemberDao {

    public MemberDaoImpl() {
        super(Member.class);
    }
    /*
    public List<Member> findByLastName(String lastName) {
        return getHibernateTemplate().find("from Member where FIRST_NAME=?", lastName);

    }

    public Member findByUssaId(Integer ussaId)
    {
        Member member = null;
        //List<Member> members = getHibernateTemplate().findByNamedQuery("Member.findByUssaId");
        //List<Member> members = getHibernateTemplate().findByNamedQueryAndNamedParam("Member.findByUssaId", "ussaId", ussaId );
        //List<Member> members = getHibernateTemplate().findByNamedQueryAndNamedParam("Member.findByLastName", "lastName", "chadwick" );
        String lastName = "Chadwick";
        StringBuilder query = new StringBuilder("select m from Member m where lower(m.lastName) like lower(").append(lastName).append(")");
        List<Member> members = getHibernateTemplate().find(query.toString());

        if (!members.isEmpty())
        {
            if (members.size() > 1)
            {
                //TODO: log error, throw exception
            }
            member = members.get(0);
        }
        return member;
        return new Member();
    }
    */
}
