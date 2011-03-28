package org.ussa.dao.impl;

import java.util.Date;
import java.util.List;

import org.ussa.common.dao.hibernate.GenericDaoHibernate;
import org.ussa.dao.MemberDao;
import org.ussa.dao.UssaIdGeneratorDao;
import org.ussa.model.Member;

public class MemberDaoImpl extends GenericDaoHibernate<Member, Long> implements MemberDao
{
	private UssaIdGeneratorDao ussaIdGeneratorDao;

	public void setUssaIdGeneratorDao(UssaIdGeneratorDao ussaIdGeneratorDao)
	{
		this.ussaIdGeneratorDao = ussaIdGeneratorDao;
	}

	public MemberDaoImpl()
	{
		super(Member.class);
	}

	/**
	 * Need to override to help hibernate deal with the OneToOne
	 * relationship with ParentInfo.  HibernateTemplate().merge
	 * has problems with the ID generation, whereas HibernateTemplate.saveOrUpdate
	 * does not.
	 */
	@Override
	public Member save(Member member)
	{
		// must set the back reference to member in order for the
		// primary key to be set correctly on ParentInfo!
		if (member.getParentInfo() != null)
		{
			member.getParentInfo().setMember(member);
		}

		if (member.getId() == null)
		{
			member.setId(ussaIdGeneratorDao.getNextUssaId());
			getHibernateTemplate().save(member);
		}
		else
		{
			getHibernateTemplate().update(member);
		}

		return member;
	}

	@SuppressWarnings("unchecked")
	public List<Member> getDuplicateCandidates(String lastName, Date birthDate)
	{
		return getHibernateTemplate().findByNamedQueryAndNamedParam(Member.QUERY_DUPLICATES,
				new String[]{"lastName", "birthDate"}, new Object[]{lastName, birthDate});
	}
}
