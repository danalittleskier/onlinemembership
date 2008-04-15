package org.ussa.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.ussa.common.model.User;
import org.ussa.common.service.UserManager;
import org.ussa.dao.MemberDao;
import org.ussa.model.Member;

public class CertificateController extends AbstractController
{
	private UserManager userManager;
	private MemberDao memberDao;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User user = userManager.getUserByUsername(userDetails.getUsername());
		Member member = memberDao.get(user.getUssaId());

		ModelAndView view = new ModelAndView("certificate");
		view.addObject("member", member);
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
}
