package org.ussa.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.ussa.beans.AccountBean;
import org.ussa.service.CreditCardProcessingService;
import org.ussa.service.MemberRegistrationService;

public class MemberRegistrationServiceImpl implements MemberRegistrationService
{
	private CreditCardProcessingService creditCardProcessingService;

	@Transactional
	public void processRegistration(AccountBean accountBean) throws Exception
	{
		// save all the account stuff first but don't commit transaction

		// then run the card. if the card completes without throwing exception then the transaction completes
		creditCardProcessingService.processCard(accountBean.getPaymentBean());
	}


	public void setCreditCardProcessingService(CreditCardProcessingService creditCardProcessingService)
	{
		this.creditCardProcessingService = creditCardProcessingService;
	}
}
