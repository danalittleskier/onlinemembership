package org.ussa.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Calendar;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.ussa.bl.DateBL;
import org.ussa.common.model.User;
import org.ussa.common.service.UserManager;
import org.ussa.dao.MemberDao;
import org.ussa.dao.MemberLegalDao;
import org.ussa.model.Member;
import org.ussa.model.MemberLegal;
import org.ussa.model.MemberSeasonPk;
import org.ussa.util.DateTimeUtils;

public class CertificateController extends AbstractController
{
	private UserManager userManager;
	private MemberDao memberDao;
	private DateBL dateBL;
	private MemberLegalDao memberLegalDao;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User user = userManager.getUserByUsername(userDetails.getUsername());

		Member member = null;
		if(user.getUssaId() != null)
		{
			member = memberDao.get(user.getUssaId());
			String lastSeason = dateBL.getLastSeason();
			String currentSeason = dateBL.getCurrentRenewSeason();
			MemberSeasonPk memberSeasonPk = new MemberSeasonPk();
			memberSeasonPk.setMember(member);
			memberSeasonPk.setSeason(currentSeason);
			MemberLegal memberLegal = memberLegalDao.get(memberSeasonPk);
			if(memberLegal == null)
			{
				return new ModelAndView("redirect:errorNotRegistered.html");
			}

			request.setAttribute("member", member);
			Calendar birthday = DateTimeUtils.getCalendar(member.getBirthDate());
			request.setAttribute("yearOfBirth",  birthday.get(Calendar.YEAR));
			request.setAttribute("lastSeason", lastSeason);
			request.setAttribute("lastSeason", lastSeason);
		}
		else
		{
			return new ModelAndView("redirect:errorNotRegistered.html");
		}

		ModelAndView view = new ModelAndView("certificate");
		return view;
	}

	public void setUserManager(UserManager userManager)
	{
		this.userManager = userManager;
	}

	public void setMemberDao(MemberDao memberDao)
	{
		this.memberDao = memberDao;
	}

	public void setDateBL(DateBL dateBL)
	{
		this.dateBL = dateBL;
	}

	public void setMemberLegalDao(MemberLegalDao memberLegalDao)
	{
		this.memberLegalDao = memberLegalDao;
	}
}
