package org.ussa.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.ussa.beans.AccountBean;
import org.ussa.beans.CartBean;
import org.ussa.beans.LineItemBean;
import org.ussa.bl.DateBL;
import org.ussa.common.dao.UniversalDao;
import org.ussa.common.model.User;
import org.ussa.common.service.UserManager;
import org.ussa.dao.InventoryAddDao;
import org.ussa.dao.InventoryDao;
import org.ussa.dao.MemberClubDao;
import org.ussa.dao.MemberDao;
import org.ussa.model.Address;
import org.ussa.model.AddressPk;
import org.ussa.model.InventoryAdd;
import org.ussa.model.Member;
import org.ussa.model.MemberClub;
import org.ussa.model.MemberLegal;
import org.ussa.model.MemberSeason;
import org.ussa.model.MemberSeasonPk;
import org.ussa.model.MemberTransaction;
import org.ussa.service.BatchService;
import org.ussa.service.CreditCardProcessingService;
import org.ussa.service.MemberRegistrationService;
import org.ussa.util.DateTimeUtils;

public class MemberRegistrationServiceImpl implements MemberRegistrationService
{
	private DateBL dateBL;
	private MemberDao memberDao;
	private MemberClubDao memberClubDao;
	private BatchService batchService;
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
		Member member = accountBean.getMember();
		boolean isNewRegistration = false;

		try
		{
			// MEMBER
			member.setType(Member.MEMBER_TYPE_INDIVIDUAL);
			if(member.getId() == null)
			{
				isNewRegistration = true;
				member.setSinceSeason(currentSeason);
			}
			member.setExpireSeason(currentSeason);
			member.setCardPrintFlag("Y");
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
			if(accountBean.getClubId() != null && accountBean.getClubId() > 0)
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
			if(StringUtils.isNotBlank(memberLegal.getFisReleaseForm()))
			{
				memberLegal.setFisReleaseFormDate(new Date());
			}
			if(StringUtils.isNotBlank(memberLegal.getIpcReleaseForm()))
			{
				memberLegal.setIpcReleaseFormDate(new Date());
			}
			if(StringUtils.isNotBlank(memberLegal.getInsuranceWaiver()))
			{
				memberLegal.setInsuranceWaiverDate(new Date());
			}
			memberLegal.setReleaseWaiver("Y");
			memberLegal.setReleaseWaiverDate(new Date());
			universalDao.save(memberLegal);

			// MEMBERSEASON
			Calendar now = Calendar.getInstance();
			Date appProcessDate = DateTimeUtils.moveToStartOfDay(now).getTime();
			MemberSeason memberSeason = new MemberSeason();
			memberSeason.setMemberSeasonPk(new MemberSeasonPk(member, season));
			memberSeason.setMedicalException(accountBean.getHasInsurance()?"N":"Y");
			memberSeason.setAppProcessDate(appProcessDate);
			memberSeason.setAppReceiveDate(appProcessDate);
			universalDao.save(memberSeason);

			// MEMBERTRANSACTION
			CartBean cartBean = accountBean.getCartBean();
			List<LineItemBean> lineItemBeans = cartBean.getLineItems();
			Set<String> invIdsAdded = new HashSet<String>();
			for (LineItemBean lineItem : lineItemBeans)
			{
				saveMemberTransaction(lineItem, member, currentSeason, invIdsAdded);

				// add additional inventory
				addAdditionalInventory(lineItem.getInventory().getId(), member, currentSeason, invIdsAdded);
			}

			// PROCESS THE CARD. if the card completes without throwing exception then the transaction completed
			creditCardProcessingService.processCard(accountBean);

			// BATCH TABLES
			batchService.doBatchInsert(accountBean, currentSeason);

			// moving this to the end until we get the transaction manager working with multiple datasources.
			// USER ACCOUNT
			UserDetails userDetails = (UserDetails) securityContext.getAuthentication().getPrincipal();
			User user = userManager.getUserByUsername(userDetails.getUsername());
			user.setUssaId(member.getId());
			userManager.saveUser(user);
		}
		catch (Exception e)
		{
			if(isNewRegistration)
			{
				member.setId(null);

				UserDetails userDetails = (UserDetails) securityContext.getAuthentication().getPrincipal();
				User user = userManager.getUserByUsername(userDetails.getUsername());
				user.setUssaId(null);
				userManager.saveUser(user);
			}
			throw e;
		}
	}

	private void saveMemberTransaction(LineItemBean lineItem, Member member, String currentSeason, Set<String> invIdsAdded)
	{
		MemberTransaction memberTransaction = new MemberTransaction(member);
		memberTransaction.setSeason(currentSeason);
		memberTransaction.setInventory(lineItem.getInventory());
		memberTransaction.setQty(lineItem.getQty());
		memberTransaction.setAmount(lineItem.getDiscountedAmount());
		memberTransaction.setPurchaseDate(new Date());
		universalDao.save(memberTransaction);
		invIdsAdded.add(lineItem.getInventory().getId());
	}

	private void addAdditionalInventory(String invId, Member member, String currentSeason, Set<String> invIdsAdded)
	{
		String divisionCode = member.getDivision().getDivisionCode();
		List<InventoryAdd> additionInventory = inventoryAddDao.getInventoryAddByInvId(invId, divisionCode);
		for (InventoryAdd inventoryAdd : additionInventory)
		{
			if(! invIdsAdded.contains(inventoryAdd.getAddInvId()))
			{
				LineItemBean lineItem = new LineItemBean(inventoryDao.get(inventoryAdd.getAddInvId()));
				lineItem.setAmount(BigDecimal.ZERO);

				saveMemberTransaction(lineItem, member, currentSeason, invIdsAdded);

				// add additional inventory
				addAdditionalInventory(lineItem.getInventory().getId(), member, currentSeason, invIdsAdded);
			}
		}

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

	public void setBatchService(BatchService batchService)
	{
		this.batchService = batchService;
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
