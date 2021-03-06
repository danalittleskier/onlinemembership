package org.ussa.bl;

import java.util.Date;
import java.util.List;

import org.ussa.beans.AccountBean;
import org.ussa.beans.MessageBean;
import org.ussa.beans.MembershipsBean;
import org.ussa.model.Division;
import org.ussa.model.Inventory;
import org.ussa.model.Member;
import org.ussa.model.MemberTransaction;

public interface RulesBL
{
	public Integer getAgeForCurrentRenewSeason(Date birthDate);

	public void setParentInfoRequired(AccountBean accountBean);

	public void prepopulateCart(AccountBean accountBean);

	public List<Inventory> getApplicableSportMemberships(AccountBean accountBean);

	public List<Inventory> getApplicableFisItems(AccountBean accountBean);

	public List<Inventory> getValidMagazineOptions(AccountBean accountBean);

	public List<MessageBean> addMembershipToCart(AccountBean accountBean, Inventory inventory);

	public void removeItemFromCart(AccountBean accountBean, String invId);

	public void handleFisOptions(AccountBean accountBean);

	public void handleMagazineOption(AccountBean accountBean);

	public void handleContribution(AccountBean accountBean);

	public void addRemoveDivisionDuesAndLateFees(AccountBean accountBean);

	public boolean hasFis(AccountBean accountBean, boolean disabled);

	public void removeFisFromCart(AccountBean accountBean, boolean disabled);

	public boolean needsBackgroundCheck(Long ussaId);

	public boolean needsBackgroundCheck(AccountBean accountBean);
	
	public boolean needsFastStartCourse(AccountBean accountBean);

	public MembershipsBean getPurchasedMemberships(Long ussaId);

	public MembershipsBean populateMembershipsBean(Long ussaId, List<MemberTransaction> memberTransactions);

	public boolean isCountryUs(String country);

	public boolean isCountryUsOrCanada(String country);

	public Division determineDivision(String nationCode, String stateCode, Long clubId, String zipCode);

	public boolean isMemberInactive(Member member);
	
	public List<String> getValidDisciplines(String sportCode);
}
