package org.ussa.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.ussa.bl.DateBL;
import org.ussa.bl.RulesBL;
import org.ussa.common.model.User;
import org.ussa.common.service.UserManager;
import org.ussa.dao.MemberDao;
import org.ussa.dao.MemberSeasonDao;
import org.ussa.dao.MemberTransactionDao;
import org.ussa.model.Member;
import org.ussa.model.MemberSeason;
import org.ussa.model.MemberSeasonPk;
import org.ussa.model.MemberTransaction;
import org.ussa.model.Inventory;
import org.ussa.util.DateTimeUtils;

public class CertificateController extends AbstractController
{
	private UserManager userManager;
	private MemberDao memberDao;
	private DateBL dateBL;
	private RulesBL rulesBL;
	private MemberSeasonDao memberSeasonDao;
	private MemberTransactionDao memberTransactionDao;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id;
		if (StringUtils.isNotBlank(request.getParameter("id")))
		{
			id = Long.parseLong(request.getParameter("id"));
		}
		else
		{
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			User user = userManager.getUserByUsername(userDetails.getUsername());
			id = user.getUssaId();
		}

		Member member = null;
		if(id != null)
		{
			if(rulesBL.certificateIsRestricted(id))
			{
				request.setAttribute("restricted", Boolean.TRUE);
			}
			else
			{
				member = memberDao.get(id);
				String lastSeason = dateBL.getLastSeason();
				String currentSeason = dateBL.getCurrentRenewSeason();
				MemberSeasonPk memberSeasonPk = new MemberSeasonPk();
				memberSeasonPk.setMember(member);
				memberSeasonPk.setSeason(currentSeason);
				MemberSeason memberSeason = memberSeasonDao.get(memberSeasonPk);
				if(memberSeason == null)
				{
					return new ModelAndView("redirect:errorNotRegistered.html");
				}

				request.setAttribute("member", member);
				request.setAttribute("memberSeason", memberSeason);
				Calendar birthday = DateTimeUtils.getCalendar(member.getBirthDate());
				request.setAttribute("yearOfBirth",  birthday.get(Calendar.YEAR));
				request.setAttribute("lastSeason", lastSeason);
				request.setAttribute("currentSeason", currentSeason);

				List<MemberTransaction> items = memberTransactionDao.getMemberTransactionsForSeason(id, currentSeason);
				request.setAttribute("membershipTransactions", filterByInventoryType(items, Inventory.INVENTORY_TYPE_MEMBERSHIP));

				List<MemberTransaction> divisionDues = filterByInventoryType(items, Inventory.INVENTORY_TYPE_DIVISION_DUES);
				BigDecimal divisionDueTotal = new BigDecimal(0);
				for (MemberTransaction memberTransaction : divisionDues)
				{
					divisionDueTotal = divisionDueTotal.add(memberTransaction.getAmount());
				}
				request.setAttribute("divisionDues", divisionDueTotal.toString());
			}
		}
		else
		{
			return new ModelAndView("redirect:errorNotRegistered.html");
		}

		ModelAndView view = new ModelAndView("certificate");
		return view;
	}

	private List<MemberTransaction> filterByInventoryType(List<MemberTransaction> items, String inventoryType)
	{
		List<MemberTransaction> result = new ArrayList<MemberTransaction>();
		for (MemberTransaction memberTransaction : items)
		{
			if (inventoryType.equals(memberTransaction.getInventory().getInventoryType()))
			{
				result.add(memberTransaction);
			}
		}
		return result;
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

	public void setRulesBL(RulesBL rulesBL)
	{
		this.rulesBL = rulesBL;
	}

	public void setMemberSeasonDao(MemberSeasonDao memberSeasonDao)
	{
		this.memberSeasonDao = memberSeasonDao;
	}

	public void setMemberTransactionDao(MemberTransactionDao memberTransactionDao)
	{
		this.memberTransactionDao = memberTransactionDao;
	}
}
