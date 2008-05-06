package org.ussa.spring.flows.registration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.validation.BindException;
import org.springframework.webflow.action.FormAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;
import org.ussa.beans.AccountBean;
import org.ussa.beans.PaymentBean;
import org.ussa.exception.CreditCardDeclinedException;
import org.ussa.exception.CreditCardException;
import org.ussa.service.MemberRegistrationService;
import org.ussa.model.Address;
import org.apache.commons.lang.StringUtils;

public class PaymentAction extends FormAction implements Serializable
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
		Address address = accountBean.getAddress();
		PaymentBean paymentBean = accountBean.getPaymentBean();

		try
		{
			if(StringUtils.isBlank(paymentBean.getAddress()))
			{
				paymentBean.setAddress(address.getDeliveryAddress());
			}
			if(StringUtils.isBlank(paymentBean.getZip()))
			{
				paymentBean.setZip(address.getPostalCode());
			}

			memberRegistrationService.processRegistration(accountBean);
		}
		catch (CreditCardDeclinedException e)
		{
			BindException errors = new BindException(accountBean, "accountBean");
			errors.reject("errors.card.declined");
			getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope());
			return error();
		}
		catch (CreditCardException e)
		{
			BindException errors = new BindException(accountBean, "accountBean");
			errors.reject("errors.card.not.approved");
			getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope());
			return error();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			BindException errors = new BindException(accountBean, "accountBean");
			errors.reject("errors.problem.registering.user");
			getFormObjectAccessor(context).putFormErrors(errors, getFormErrorsScope());
			return error();
		}

		return success();
	}

	public void setMemberRegistrationService(MemberRegistrationService memberRegistrationService)
	{
		this.memberRegistrationService = memberRegistrationService;
	}
}
