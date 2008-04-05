package org.ussa.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.ussa.beans.AccountBean;
import org.ussa.beans.CartBean;
import org.ussa.beans.LineItemBean;
import org.ussa.bl.RulesBL;
import org.ussa.bl.DateBL;
import org.ussa.dao.AddressDao;
import org.ussa.dao.BatchTransactionDao;
import org.ussa.dao.MemberDao;
import org.ussa.dao.MemberLegalDao;
import org.ussa.model.Address;
import org.ussa.model.InventoryAdd;
import org.ussa.model.Member;
import org.ussa.model.MemberLegal;
import org.ussa.model.MemberTransaction;
import org.ussa.model.MemberSeason;
import org.ussa.service.CreditCardProcessingService;
import org.ussa.service.MemberRegistrationService;

public class MemberRegistrationServiceImpl implements MemberRegistrationService
{
	private RulesBL rulesBL;
	private DateBL dateBL;
	private MemberDao memberDao;
	private AddressDao addressDao;
	private MemberLegalDao memberLegalDao;
	private BatchTransactionDao batchTransactionDao;
	private CreditCardProcessingService creditCardProcessingService;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void processRegistration(AccountBean accountBean) throws Exception
	{
		String currentSeason = dateBL.getCurrentRenewSeason();

		Member member = accountBean.getMember();
		Address address = accountBean.getAddress();
		MemberLegal memberLegal = accountBean.getMemberLegal();
		CartBean cartBean = accountBean.getCartBean();

		// Generate a new ussaId for new registrations
		Long ussaId = member.getId();
		if(ussaId == null || ussaId == 0)
		{
			ussaId = rulesBL.getNextUssaId();
			member.setId(ussaId);
			address.getAddressPk().setId(ussaId);
			memberLegal.getMemberLegalPk().setUssaId(ussaId);
		}

		// MEMBERLEGAL
		memberLegal.setInsuranceWaiverDate(new Date());
		memberLegal.setReleaseWaiverDate(new Date());
//		memberLegalDao.save(memberLegal);

		// MEMBERADDRESS
//		addressDao.save(address);

		// MEMBER
//		memberDao.save(member);

		// BATCH TABLES
//		batchTransactionDao.insertToBatchTables(accountBean);

		// MEMBERSEASON
		MemberSeason memberSeason = new MemberSeason();

		// INVENTORYADD
		// Get extra inventory that needs to be added, and add it to the list.
		InventoryAdd inventoryAdd = new InventoryAdd();

		// MEMBERTRANSACTION
		List<LineItemBean> lineItemBeans = cartBean.getLineItems();
		for (LineItemBean lineItem : lineItemBeans)
		{
			MemberTransaction memberTransaction = new MemberTransaction();
			memberTransaction.setUssaId(ussaId);
			memberTransaction.setSeason(currentSeason);
			memberTransaction.setInvId(lineItem.getInventory().getId());
			memberTransaction.setQty(lineItem.getQty());
			memberTransaction.setAmount(lineItem.getDiscountedAmount());
			memberTransaction.setSentDate(null);
			memberTransaction.setPurchaseDate(new Date());
			// TODO: save these
		}

		// then run the card. if the card completes without throwing exception then the transaction completes
		creditCardProcessingService.processCard(accountBean);

		//TODO: GET THE TRASANCTION NUMBER FROM AUTHORIZE.NET AND SAVE IT
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

	public DateBL getDateBL()
	{
		return dateBL;
	}

	public void setDateBL(DateBL dateBL)
	{
		this.dateBL = dateBL;
	}

	public BatchTransactionDao getBatchTransactionDao()
	{
		return batchTransactionDao;
	}

	public void setBatchTransactionDao(BatchTransactionDao batchTransactionDao)
	{
		this.batchTransactionDao = batchTransactionDao;
	}
}
