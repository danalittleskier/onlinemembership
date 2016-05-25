package org.ussa.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;
import org.ussa.beans.AccountBean;
import org.ussa.beans.CartBean;
import org.ussa.beans.LineItemBean;
import org.ussa.bl.CasLdap;
import org.ussa.bl.DateBL;
import org.ussa.bl.EmailUtility;
import org.ussa.common.dao.UniversalDao;
import org.ussa.dao.InventoryAddDao;
import org.ussa.dao.InventoryDao;
import org.ussa.dao.MemberClubDao;
import org.ussa.dao.MemberDao;
import org.ussa.dao.ClubAffiliationHistoryDao;
import org.ussa.model.Address;
import org.ussa.model.AddressPk;
import org.ussa.model.Inventory;
import org.ussa.model.InventoryAdd;
import org.ussa.model.Member;
import org.ussa.model.MemberClub;
import org.ussa.model.MemberLegal;
import org.ussa.model.MemberSeason;
import org.ussa.model.MemberSeasonPk;
import org.ussa.model.MemberTransaction;
import org.ussa.model.ClubAffiliationHistory;
import org.ussa.service.BatchService;
import org.ussa.service.CreditCardProcessingService;
import org.ussa.service.MemberRegistrationService;
import org.ussa.util.DateTimeUtils;

public class MemberRegistrationServiceImpl implements MemberRegistrationService {
	protected final Log log = LogFactory.getLog(getClass());
	private DateBL dateBL;
	private MemberDao memberDao;
	private MemberClubDao memberClubDao;
	private ClubAffiliationHistoryDao clubAffiliationDao;
	private BatchService batchService;
	private CreditCardProcessingService creditCardProcessingService;
	private UniversalDao universalDao;
	private InventoryAddDao inventoryAddDao;
	private InventoryDao inventoryDao;
	private CasLdap casLdap;
	private EmailUtility emailUtility;

	// private UserManager userManager;
	// private SecurityContext securityContext;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void processRegistration(AccountBean accountBean) throws Exception {

		StopWatch cardSw = new StopWatch();
		cardSw.start();

		// PROCESS THE CARD. if the card completes without throwing exception
		// then the transaction completed
		creditCardProcessingService.processCard(accountBean);
		cardSw.stop();

		StopWatch sw = new StopWatch();
		sw.start();
		String currentSeason = dateBL.getCurrentRenewSeason();
		Member member = accountBean.getMember();
		boolean isNewRegistration = false;

		try {

			// MEMBER
			member.setType(Member.MEMBER_TYPE_INDIVIDUAL);
			if (member.getId() == null) {
				isNewRegistration = true;
				member.setSinceSeason(currentSeason);
			}

			Calendar today = Calendar.getInstance();
			member.setModifiedDate(today.getTime());
			member.setExpireSeason(currentSeason);
			member.setCardPrintFlag("Y");
			
			log.warn("Beginning Member");
			log.warn("USSA Id: " + member.getId());
			log.warn("First Name: " + member.getFirstName());
			log.warn("Middle Initial: " + member.getMiddleName());
			log.warn("Last Name: " + member.getLastName());
			log.warn("Suffix: " + member.getSuffixName());
			log.warn("Birth Date: " + member.getBirthDate());
			log.warn("Email: " + member.getEmail());
			log.warn("End Member");
			
			member = memberDao.save(member);

			// MEMBERCLUB
			MemberClub memberClub;
			
			try {
				memberClub = memberClubDao.get(member.getId());
			} catch (ObjectRetrievalFailureException e) {
				memberClub = null;
			}
			if (accountBean.getClubId() != null && accountBean.getClubId() > 0) {
				if (memberClub == null) {
					memberClub = new MemberClub();
					memberClub.setIndUssaId(member.getId());
				}
				memberClub.setClubUssaId(accountBean.getClubId());
				memberClubDao.save(memberClub);
			} else if (memberClub != null) {
				memberClubDao.remove(memberClub);
			}

			//CLUBAFFILIATIONHISTORY
			ClubAffiliationHistory clubAffiliationHistory = new ClubAffiliationHistory();
			if (accountBean.getClubId() != null && accountBean.getClubId() > 0) {
				clubAffiliationHistory.setIndUssaId(member.getId());
				clubAffiliationHistory.setClubUssaId(accountBean.getClubId());
				clubAffiliationHistory.setProcessDate(today.getTime());
				clubAffiliationHistory.setChangedBy("OnlineMembership");
				//universalDao.save(clubAffiliationHistory);
				clubAffiliationDao.save(clubAffiliationHistory);
			}
			
			// MEMBERADDRESS
			Address address = accountBean.getAddress();
			address.setBadAddress("N");
			address.setUserId("MembershipWeb");
			address.setChangeDate(today.getTime());
			address.setAddressPk(new AddressPk(member, Address.ADDRESS_TYPE_PRIMARY));
			universalDao.save(address);

			// MEMBERLEGAL
			MemberLegal memberLegal = accountBean.getMemberLegal();
			String season = dateBL.getCurrentRenewSeason();
			memberLegal.setMemberSeasonPk(new MemberSeasonPk(member, season));
			if (StringUtils.isNotBlank(memberLegal.getFisReleaseForm())) {
				memberLegal.setFisReleaseFormDate(new Date());
			}
			if (StringUtils.isNotBlank(memberLegal.getIpcReleaseForm())) {
				memberLegal.setIpcReleaseFormDate(new Date());
			}
			if (StringUtils.isNotBlank(memberLegal.getInsuranceWaiver())) {
				memberLegal.setInsuranceWaiverDate(new Date());
			}
			memberLegal.setReleaseWaiver("Y");
			memberLegal.setReleaseWaiverDate(new Date());

			// New concussion waiver
			memberLegal.setConcussionWaiver("Y");
			memberLegal.setConcussionWaiverDate(new Date());

			//New safe sport waiver
	        memberLegal.setSafeSportWaiver("Y");
	        memberLegal.setSafeSportWaiverDate(new Date());
	        
			universalDao.save(memberLegal);

			// MEMBERSEASON
			Calendar now = Calendar.getInstance();
			Date appProcessDate = DateTimeUtils.moveToStartOfDay(now).getTime();
			MemberSeason memberSeason = new MemberSeason();
			memberSeason.setMemberSeasonPk(new MemberSeasonPk(member, season));
			memberSeason.setMedicalException(accountBean.getHasInsurance() ? "N" : "Y");

			// Checks to see if a background check is needed. This field is set
			// in the RegistrationAction.java -> loadSportMemberships()
			if (accountBean.isNeedsBackground()) {
				memberSeason.setBackgroundCheckFlag("N");
			}
			
			//Check to see if they need to complete the safe sport course This field is set
			// in the RegistrationAction.java -> loadSportMemberships()
			if(accountBean.isNeedsFastStartCourse()){
				memberSeason.setFastStartCourseCheckFlag("N");
			}


			// These are now being set in MemberSeason.java
			// memberSeason.setMedicalException("N");
			// memberSeason.setCurrentFlag("Y");
			// memberSeason.setWaiverSigned("Y");
			// memberSeason.setProvisionalFis("N");
			memberSeason.setAppProcessDate(appProcessDate);
			memberSeason.setAppReceiveDate(appProcessDate);
			// memberSeason.setTeamAgreement("N");
			// memberSeason.setDivMedicalRelease("N");
			// memberSeason.setPrep("N");
			universalDao.save(memberSeason);
			accountBean.setMemberSeason(memberSeason);

			// MEMBERTRANSACTION
			//List<Inventory> xcMemberships = inventoryDao.getIventoryByTypeAndSportCode("Membership", "XC");
			int addOne = 0;
			CartBean cartBean = accountBean.getCartBean();
			List<LineItemBean> lineItemBeans = cartBean.getLineItems();
			Set<String> invIdsAlreadyAdded = new HashSet<String>();
			Set<String> inventoryAddInvIds = new HashSet<String>();
			for (LineItemBean lineItem : lineItemBeans) {
				//Nensa stuff				
				log.warn("nensa id is "+member.getNensaId());				
				log.warn("state code is "+accountBean.getMember().getStateCode());
				log.warn("sport code is "+lineItem.getInventory().getSportCode());
				if(member.getNensaId() == null && lineItem.getInventory().getSportCode().contains("XC") && (accountBean.getMember().getStateCode().equalsIgnoreCase("VT")) || accountBean.getMember().getStateCode().equalsIgnoreCase("NH") || accountBean.getMember().getStateCode().equalsIgnoreCase("MA") || accountBean.getMember().getStateCode().equalsIgnoreCase("MD") || accountBean.getMember().getStateCode().equalsIgnoreCase("ME") || accountBean.getMember().getStateCode().equalsIgnoreCase("CT") || accountBean.getMember().getStateCode().equalsIgnoreCase("NJ") || accountBean.getMember().getStateCode().equalsIgnoreCase("NY") || accountBean.getMember().getStateCode().equalsIgnoreCase("PA") || accountBean.getMember().getStateCode().equalsIgnoreCase("RI") || accountBean.getMember().getStateCode().equalsIgnoreCase("DE") || accountBean.getMember().getStateCode().equalsIgnoreCase("WV")){
					addOne = 1;
					log.warn("in the nensa if loop ");
				}
				if(addOne == 1){
					member.setNensaId(memberDao.getMaxNensaId().getNensaId() +addOne);
				}
				log.warn("nensa id after max add is "+member.getNensaId());
				/////////
				
				saveMemberTransaction(lineItem, member, currentSeason);
				invIdsAlreadyAdded.add(lineItem.getInventory().getId());

				// add additional inventory
				inventoryAddInvIds.addAll(getAdditionalInvIds(lineItem.getInventory().getId(), member.getDivision().getDivisionCode(), invIdsAlreadyAdded));
			}

			// make sure that the competition guide isn't added twice since the
			// guide/dir includes both
			for (Iterator<String> iterator = inventoryAddInvIds.iterator(); iterator.hasNext();) {
				String invId = iterator.next();
				if (inventoryAddInvIds.contains(invId + "/DIR")) {
					iterator.remove();
				}
			}

			// insert member transactions for all the inventory add items
			List<LineItemBean> inventoryAddLineItems = new ArrayList<LineItemBean>();
			for (String invId : inventoryAddInvIds) {
				Inventory inventory = inventoryDao.get(invId);
				LineItemBean lineItem = new LineItemBean(inventory);
				lineItem.setAmount(BigDecimal.ZERO);
				inventoryAddLineItems.add(lineItem);
				saveMemberTransaction(lineItem, member, currentSeason);
			}

			// BATCH TABLES
			batchService.doBatchTableInsert(accountBean, inventoryAddLineItems, currentSeason);

			// UserDetails userDetails = (UserDetails)
			// securityContext.getAuthentication().getPrincipal();
			// User user =
			// userManager.getUserByUsername(userDetails.getUsername());

			// userManager.saveUser(user);
			// TODO:
			// Get Username
			// Determine if ussa id goes in ussa id field or alternates field
			casLdap.addUssaIdToAccount(accountBean.getUserBean(), accountBean, String.valueOf(member.getId()));

			sw.stop();
			log.trace("Member registration completed in (" + sw.getTotalTimeMillis() + ") milliseconds");
			log.trace("Processing the credit card took (" + cardSw.getTotalTimeMillis() + ") milliseconds");

		} catch (Exception e) {

			// An error has occurred while saving to database!!!!
			// Refund their money
			creditCardProcessingService.voidTransaction(accountBean.getPaymentBean().getCompletedTransactionId());

			// Send an refund email
			emailUtility.refundEmail(accountBean);

			// Lets try and save their info to the account if account is new
			casLdap.addUssaIdToAccount(accountBean.getUserBean(), accountBean, String.valueOf(member.getId()));

			throw e;
		}
	}

	private void saveMemberTransaction(LineItemBean lineItem, Member member, String currentSeason) {
		MemberTransaction memberTransaction = new MemberTransaction(member);
		memberTransaction.setSeason(currentSeason);
		memberTransaction.setInventory(lineItem.getInventory());
		memberTransaction.setQty(lineItem.getQty());
		memberTransaction.setAmount(lineItem.getDiscountedAmount());
		memberTransaction.setPurchaseDate(new Date());
		memberTransaction.setValidFrom(lineItem.getValidFrom());
		memberTransaction.setValidTo(lineItem.getValidTo());
		universalDao.save(memberTransaction);
	}

	private Set<String> getAdditionalInvIds(String invId, String divisionCode, Set<String> invIdsAlreadyAdded) {
		Set<String> additionalInvIds = new HashSet<String>();
		List<InventoryAdd> additionInventory = inventoryAddDao.getInventoryAddByInvId(invId, divisionCode);
		for (InventoryAdd inventoryAdd : additionInventory) {
			if (!invIdsAlreadyAdded.contains(inventoryAdd.getAddInvId())) {
				invIdsAlreadyAdded.add(inventoryAdd.getAddInvId());

				additionalInvIds.add(inventoryAdd.getAddInvId());

				// get additional child inventory
				additionalInvIds.addAll(getAdditionalInvIds(inventoryAdd.getAddInvId(), divisionCode, invIdsAlreadyAdded));
			}
		}

		return additionalInvIds;
	}

	public void setCreditCardProcessingService(CreditCardProcessingService creditCardProcessingService) {
		this.creditCardProcessingService = creditCardProcessingService;
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public void setMemberClubDao(MemberClubDao memberClubDao) {
		this.memberClubDao = memberClubDao;
	}
	
	public void setClubAffiliationHistoryDao(ClubAffiliationHistoryDao clubAffiliationDao){
		this.clubAffiliationDao = clubAffiliationDao;
	}

	public void setDateBL(DateBL dateBL) {
		this.dateBL = dateBL;
	}

	public void setBatchService(BatchService batchService) {
		this.batchService = batchService;
	}

	public void setUniversalDao(UniversalDao universalDao) {
		this.universalDao = universalDao;
	}

	public void setInventoryAddDao(InventoryAddDao inventoryAddDao) {
		this.inventoryAddDao = inventoryAddDao;
	}

	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}

	public void setCasLdap(CasLdap casLdap) {
		this.casLdap = casLdap;
	}

	public void setEmailUtility(EmailUtility emailUtility) {
		this.emailUtility = emailUtility;
	}

}
