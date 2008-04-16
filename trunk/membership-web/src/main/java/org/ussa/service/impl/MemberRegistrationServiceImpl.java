package org.ussa.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.ussa.beans.AccountBean;
import org.ussa.beans.CartBean;
import org.ussa.beans.LineItemBean;
import org.ussa.bl.DateBL;
import org.ussa.common.dao.UniversalDao;
import org.ussa.dao.AddressDao;
import org.ussa.dao.BatchTransactionDao;
import org.ussa.dao.InventoryAddDao;
import org.ussa.dao.InventoryDao;
import org.ussa.dao.MemberDao;
import org.ussa.dao.MemberLegalDao;
import org.ussa.dao.MemberTransactionDao;
import org.ussa.model.Address;
import org.ussa.model.AddressPk;
import org.ussa.model.Member;
import org.ussa.model.MemberLegal;
import org.ussa.model.MemberSeason;
import org.ussa.model.MemberSeasonPk;
import org.ussa.model.MemberTransaction;
import org.ussa.service.CreditCardProcessingService;
import org.ussa.service.MemberRegistrationService;

public class MemberRegistrationServiceImpl implements MemberRegistrationService
{
	private DateBL dateBL;
	private MemberDao memberDao;
	private AddressDao addressDao;
	private MemberLegalDao memberLegalDao;
	private BatchTransactionDao batchTransactionDao;
    private MemberTransactionDao memberTransactionDao;
    private CreditCardProcessingService creditCardProcessingService;
    private UniversalDao universalDao;
    private InventoryAddDao inventoryAddDao;
    private InventoryDao inventoryDao;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void processRegistration(AccountBean accountBean) throws Exception
	{
		String currentSeason = dateBL.getCurrentRenewSeason();

		Member member = accountBean.getMember();
		Address address = accountBean.getAddress();
		MemberLegal memberLegal = accountBean.getMemberLegal();
		CartBean cartBean = accountBean.getCartBean();

		// MEMBER
		member.setType(Member.MEMBER_TYPE_INDIVIDUAL);
		member = memberDao.save(member);

		// MEMBERADDRESS
		address.setAddressPk(new AddressPk(member, Address.ADDRESS_TYPE_PRIMARY));
		addressDao.save(address);

		// MEMBERLEGAL
		String season = dateBL.getCurrentRenewSeason();
		memberLegal.setMemberSeasonPk(new MemberSeasonPk(member, season));
		memberLegal.setInsuranceWaiverDate(new Date());
		memberLegal.setReleaseWaiverDate(new Date());
		memberLegalDao.save(memberLegal);

		// MEMBERSEASON
		MemberSeason memberSeason = new MemberSeason();
		memberSeason.setMemberSeasonPk(new MemberSeasonPk(member, season));
		// TODO: What date should go here?
		memberSeason.setAppProcessDate(new Date());
		memberSeason.setAppReceiveDate(new Date());
		universalDao.save(memberSeason);

		// MEMBERTRANSACTION
		List<LineItemBean> lineItemBeans = cartBean.getLineItems();
		for (LineItemBean lineItem : lineItemBeans)
		{
			saveMemberTransaction(lineItem, member, currentSeason);

			// INVENTORYADD
//			String invId = lineItem.getInventory().getId();
//			String divisionCode = member.getDivision().getDivisionCode();
//			List<InventoryAdd> additionInventory = inventoryAddDao.getInventoryAddByInvId(invId, divisionCode);
//			for (InventoryAdd inventoryAdd : additionInventory)
//			{
//				Inventory inventory = inventoryDao.get(inventoryAdd.getAddInvId());
//
//				saveMemberTransaction(new LineItemBean(inventory), member, currentSeason);
//			}
		}

		// TODO update account user's ussaid

		// then run the card. if the card completes without throwing exception then the transaction completes
		creditCardProcessingService.processCard(accountBean);

		// BATCH TABLES
//		batchTransactionDao.insertToBatchTables(accountBean);
	}

	private void saveMemberTransaction(LineItemBean lineItem, Member member, String currentSeason)
	{
		MemberTransaction memberTransaction = new MemberTransaction(member);
		memberTransaction.setSeason(currentSeason);
		memberTransaction.setInvId(lineItem.getInventory().getId());
		memberTransaction.setQty(lineItem.getQty());
		memberTransaction.setAmount(lineItem.getDiscountedAmount());
		// TODO: What date should go here?
		memberTransaction.setSentDate(new Date());
		memberTransaction.setPurchaseDate(new Date());
		memberTransactionDao.save(memberTransaction);
	}


	public void setCreditCardProcessingService(CreditCardProcessingService creditCardProcessingService)
	{
		this.creditCardProcessingService = creditCardProcessingService;
	}


	public void setMemberDao(MemberDao memberDao)
	{
		this.memberDao = memberDao;
	}

	public void setAddressDao(AddressDao addressDao)
	{
		this.addressDao = addressDao;
	}

	public void setMemberLegalDao(MemberLegalDao memberLegalDao)
	{
		this.memberLegalDao = memberLegalDao;
	}

	public void setDateBL(DateBL dateBL)
	{
		this.dateBL = dateBL;
	}

	public void setBatchTransactionDao(BatchTransactionDao batchTransactionDao)
	{
		this.batchTransactionDao = batchTransactionDao;
	}

    public void setMemberTransactionDao(MemberTransactionDao memberTransactionDao)
    {
        this.memberTransactionDao = memberTransactionDao;
    }

	public void setUniversalDao(UniversalDao universalDao)
	{
		this.universalDao = universalDao;
	}

	public void setInventoryAddDao(InventoryAddDao inventoryAddDao)
	{
		this.inventoryAddDao = inventoryAddDao;
	}

	public void setInventoryDao(InventoryDao inventoryDao)
	{
		this.inventoryDao = inventoryDao;
	}
}
