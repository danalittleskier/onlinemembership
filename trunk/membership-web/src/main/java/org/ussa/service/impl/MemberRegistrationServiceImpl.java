package org.ussa.service.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.ussa.beans.AccountBean;
import org.ussa.beans.CartBean;
import org.ussa.beans.LineItemBean;
import org.ussa.bl.DateBL;
import org.ussa.bl.RulesBL;
import org.ussa.dao.AddressDao;
import org.ussa.dao.BatchTransactionDao;
import org.ussa.dao.MemberDao;
import org.ussa.dao.MemberLegalDao;
import org.ussa.dao.MemberTransactionDao;
import org.ussa.model.Address;
import org.ussa.model.AddressPk;
import org.ussa.model.InventoryAdd;
import org.ussa.model.Member;
import org.ussa.model.MemberLegal;
import org.ussa.model.MemberLegalPk;
import org.ussa.model.MemberSeason;
import org.ussa.model.MemberTransaction;
import org.ussa.service.CreditCardProcessingService;
import org.ussa.service.MemberRegistrationService;

import java.util.Date;
import java.util.List;

public class MemberRegistrationServiceImpl implements MemberRegistrationService
{
	private RulesBL rulesBL;
	private DateBL dateBL;
	private MemberDao memberDao;
	private AddressDao addressDao;
	private MemberLegalDao memberLegalDao;
	private BatchTransactionDao batchTransactionDao;
    private MemberTransactionDao memberTransactionDao;
    private CreditCardProcessingService creditCardProcessingService;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void processRegistration(AccountBean accountBean) throws Exception
	{
		String currentSeason = dateBL.getCurrentRenewSeason();

		Member member = accountBean.getMember();
		Address address = accountBean.getAddress();
		MemberLegal memberLegal = accountBean.getMemberLegal();
		CartBean cartBean = accountBean.getCartBean();

		// MEMBER
		member.setType(Member.MEMBER_TYPE_INDIVIDUAL); // "I" = Individual, "C" = Club, todo: use Enum constants?
		member = memberDao.save(member);

		// MEMBERADDRESS
		address.setAddressPk(new AddressPk(member, Address.ADDRESS_TYPE_PRIMARY)); // "P" = Primary, todo: use Enum constants?
		addressDao.save(address);

		// MEMBERLEGAL
		String season = dateBL.getCurrentRenewSeason();
		memberLegal.setMemberLegalPk(new MemberLegalPk(member, season));
		memberLegal.setInsuranceWaiverDate(new Date());
		memberLegal.setReleaseWaiverDate(new Date());
		memberLegalDao.save(memberLegal);

		// MEMBERSEASON
		MemberSeason memberSeason = new MemberSeason();

		// INVENTORYADD
		// Get extra inventory that needs to be added, and add it to the list.
		InventoryAdd inventoryAdd = new InventoryAdd();

		// MEMBERTRANSACTION
		List<LineItemBean> lineItemBeans = cartBean.getLineItems();
		for (LineItemBean lineItem : lineItemBeans)
		{
			MemberTransaction memberTransaction = new MemberTransaction(member);
			memberTransaction.setSeason(currentSeason);
			memberTransaction.setInvId(lineItem.getInventory().getId());
			memberTransaction.setQty(lineItem.getQty());
			memberTransaction.setAmount(lineItem.getDiscountedAmount());
			memberTransaction.setSentDate(null);
			memberTransaction.setPurchaseDate(new Date());
			memberTransactionDao.save(memberTransaction);
		}

		// then run the card. if the card completes without throwing exception then the transaction completes
		creditCardProcessingService.processCard(accountBean);

		// BATCH TABLES
//		batchTransactionDao.insertToBatchTables(accountBean);
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

    public MemberTransactionDao getMemberTransactionDao() 
    {
        return memberTransactionDao;
    }

    public void setMemberTransactionDao(MemberTransactionDao memberTransactionDao) 
    {
        this.memberTransactionDao = memberTransactionDao;
    }
}
