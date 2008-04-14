package org.ussa.spring.flows.registration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.webflow.action.MultiAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;
import org.ussa.beans.AccountBean;
import org.ussa.service.MemberRegistrationService;

public class PaymentAction extends MultiAction implements Serializable
{

	private MemberRegistrationService memberRegistrationService;

	String responseCode;

	public Event loadPayment(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");

		List<Integer> years = new ArrayList<Integer>();
		Calendar cal = Calendar.getInstance();
		for(int i=0; i<10; i++)
		{
			years.add(cal.get(Calendar.YEAR));
			cal.add(Calendar.YEAR, 1);
		}

		accountBean.setYears(years);

		return success();
	}

	public Event processOrder(RequestContext context) throws Exception
	{
		AccountBean accountBean = (AccountBean) context.getFlowScope().get("accountBean");

		memberRegistrationService.processRegistration(accountBean);

		return success();
	}

	public void setMemberRegistrationService(MemberRegistrationService memberRegistrationService)
	{
		this.memberRegistrationService = memberRegistrationService;
	}
}
