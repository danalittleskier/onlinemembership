package org.ussa.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.ussa.beans.AccountBean;
import org.ussa.bl.RulesBL;
import org.ussa.dao.AddressDao;
import org.ussa.dao.MemberDao;
import org.ussa.dao.MemberLegalDao;
import org.ussa.service.CreditCardProcessingService;
import org.ussa.service.MemberRegistrationService;

public class MemberRegistrationServiceImpl implements MemberRegistrationService
{
	private MemberDao memberDao;
	private AddressDao addressDao;
	private MemberLegalDao memberLegalDao;
	private RulesBL rulesBL;

	private CreditCardProcessingService creditCardProcessingService;

	@Transactional
	public void processRegistration(AccountBean accountBean) throws Exception
	{
		// save all the account stuff first but don't commit transaction
//		MemberLegal memberLegal = accountBean.getMemberLegal();
//		memberLegal.setInsuranceWaiverDate(new Date());
//		memberLegal.setReleaseWaiverDate(new Date());
//		memberLegalDao.save(memberLegal);

		// then run the card. if the card completes without throwing exception then the transaction completes
		creditCardProcessingService.processCard(accountBean.getPaymentBean());
	}


	public void setCreditCardProcessingService(CreditCardProcessingService creditCardProcessingService)
	{
		this.creditCardProcessingService = creditCardProcessingService;
	}


	public MemberDao getMemberDao()
	{
		return memberDao;
	}

	public void setMemberDao(MemberDao memberDao)
	{
		this.memberDao = memberDao;
	}

	public AddressDao getAddressDao()
	{
		return addressDao;
	}

	public void setAddressDao(AddressDao addressDao)
	{
		this.addressDao = addressDao;
	}

	public MemberLegalDao getMemberLegalDao()
	{
		return memberLegalDao;
	}

	public void setMemberLegalDao(MemberLegalDao memberLegalDao)
	{
		this.memberLegalDao = memberLegalDao;
	}

	public RulesBL getRulesBL()
	{
		return rulesBL;
	}

	public void setRulesBL(RulesBL rulesBL)
	{
		this.rulesBL = rulesBL;
	}
}
