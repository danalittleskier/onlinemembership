package org.ussa.service.impl;

import java.util.Date;
import java.util.List;

import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.userdetails.UserDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.ussa.beans.AccountBean;
import org.ussa.beans.CartBean;
import org.ussa.beans.LineItemBean;
import org.ussa.bl.DateBL;
import org.ussa.common.dao.UniversalDao;
import org.ussa.common.model.User;
import org.ussa.common.service.UserManager;
import org.ussa.dao.BatchTransactionDao;
import org.ussa.dao.InventoryAddDao;
import org.ussa.dao.InventoryDao;
import org.ussa.dao.MemberDao;
import org.ussa.dao.MemberClubDao;
import org.ussa.model.Address;
import org.ussa.model.AddressPk;
import org.ussa.model.Inventory;
import org.ussa.model.InventoryAdd;
import org.ussa.model.Member;
import org.ussa.model.MemberLegal;
import org.ussa.model.MemberSeason;
import org.ussa.model.MemberSeasonPk;
import org.ussa.model.MemberTransaction;
import org.ussa.model.MemberClub;
import org.ussa.service.CreditCardProcessingService;
import org.ussa.service.MemberRegistrationService;

public class MemberRegistrationServiceImpl implements MemberRegistrationService
{
	private DateBL dateBL;
	private MemberDao memberDao;
	private MemberClubDao memberClubDao;
	private BatchTransactionDao batchTransactionDao;
    private CreditCardProcessingService creditCardProcessingService;
    private UniversalDao universalDao;
    private InventoryAddDao inventoryAddDao;
    private InventoryDao inventoryDao;
	private UserManager userManager;
	private SecurityContext securityContext;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void processRegistration(AccountBean accountBean) throws Exception
	{
		String currentSeason = dateBL.getCurrentRenewSeason();

		// MEMBER
		Member member = accountBean.getMember();
		member.setType(Member.MEMBER_TYPE_INDIVIDUAL);
		member = memberDao.save(member);

		//MEMBERCLUB
		MemberClub memberClub;
		try
		{
			memberClub = memberClubDao.get(member.getId());
		}
		catch (ObjectRetrievalFailureException e)
		{
			memberClub = null;
		}
		if(accountBean.getClubId() != null)
		{
			if(memberClub == null)
			{
				memberClub = new MemberClub();
				memberClub.setIndUssaId(member.getId());
			}
			memberClub.setClubUssaId(accountBean.getClubId());
			memberClubDao.save(memberClub);
		}
		else if(memberClub != null)
		{
			memberClubDao.remove(memberClub);
		}

		// MEMBERADDRESS
		Address address = accountBean.getAddress();
		address.setAddressPk(new AddressPk(member, Address.ADDRESS_TYPE_PRIMARY));
		universalDao.save(address);

		// MEMBERLEGAL
		MemberLegal memberLegal = accountBean.getMemberLegal();
		String season = dateBL.getCurrentRenewSeason();
		memberLegal.setMemberSeasonPk(new MemberSeasonPk(member, season));
		// TODO: What dates should go here?
		memberLegal.setInsuranceWaiverDate(new Date());
		memberLegal.setReleaseWaiverDate(new Date());
		universalDao.save(memberLegal);

		// MEMBERSEASON
		MemberSeason memberSeason = new MemberSeason();
		memberSeason.setMemberSeasonPk(new MemberSeasonPk(member, season));
		memberSeason.setMedicalException(accountBean.getHasInsurance()?"N":"Y");
		// TODO: What dates should go here?
		memberSeason.setAppProcessDate(new Date());
		memberSeason.setAppReceiveDate(new Date());
		universalDao.save(memberSeason);

		// MEMBERTRANSACTION
		CartBean cartBean = accountBean.getCartBean();
		List<LineItemBean> lineItemBeans = cartBean.getLineItems();
		for (LineItemBean lineItem : lineItemBeans)
		{
			saveMemberTransaction(lineItem, member, currentSeason);

			// INVENTORYADD
			String invId = lineItem.getInventory().getId();
			String divisionCode = member.getDivision().getDivisionCode();
			List<InventoryAdd> additionInventory = inventoryAddDao.getInventoryAddByInvId(invId, divisionCode);
			for (InventoryAdd inventoryAdd : additionInventory)
			{
				Inventory inventory = inventoryDao.get(inventoryAdd.getAddInvId());

				saveMemberTransaction(new LineItemBean(inventory), member, currentSeason);
			}
		}

		// then run the card. if the card completes without throwing exception then the transaction completes
		creditCardProcessingService.processCard(accountBean);

		// moving this to the end until we get the transaction manager working with multiple datasources.
		UserDetails userDetails = (UserDetails) securityContext.getAuthentication().getPrincipal();
		User user = userManager.getUserByUsername(userDetails.getUsername());
		user.setUssaId(member.getId());
		userManager.saveUser(user);

		// BATCH TABLES
		batchTransactionDao.insertToBatchTables(accountBean);
	}

	private void saveMemberTransaction(LineItemBean lineItem, Member member, String currentSeason)
	{
		MemberTransaction memberTransaction = new MemberTransaction(member);
		memberTransaction.setSeason(currentSeason);
		memberTransaction.setInventory(lineItem.getInventory());
		memberTransaction.setQty(lineItem.getQty());
		memberTransaction.setAmount(lineItem.getDiscountedAmount());
		// TODO: What dates should go here?
		memberTransaction.setSentDate(new Date());
		memberTransaction.setPurchaseDate(new Date());
		universalDao.save(memberTransaction);
	}


	public void setCreditCardProcessingService(CreditCardProcessingService creditCardProcessingService)
	{
		this.creditCardProcessingService = creditCardProcessingService;
	}

	public void setMemberDao(MemberDao memberDao)
	{
		this.memberDao = memberDao;
	}

	public void setMemberClubDao(MemberClubDao memberClubDao)
	{
		this.memberClubDao = memberClubDao;
	}

	public void setDateBL(DateBL dateBL)
	{
		this.dateBL = dateBL;
	}

	public void setBatchTransactionDao(BatchTransactionDao batchTransactionDao)
	{
		this.batchTransactionDao = batchTransactionDao;
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

	public void setUserManager(UserManager userManager)
	{
		this.userManager = userManager;
	}

	public void setSecurityContext(SecurityContext securityContext)
	{
		this.securityContext = securityContext;
	}
}
