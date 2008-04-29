package org.ussa.bl;

import java.util.Date;
import java.util.List;

import org.ussa.beans.AccountBean;
import org.ussa.model.Inventory;
import org.ussa.model.Division;

public interface RulesBL
{
	public Integer getAgeForCurrentRenewSeason(Date birthDate);

	public void setParentInfoRequired(AccountBean accountBean);

	public List<Inventory> getApplicableSportMemberships(AccountBean accountBean);

	public List<Inventory> getApplicableFisItems(AccountBean accountBean);

	public List<Inventory> getValidMagazineOptions(AccountBean accountBean);

	public void addMembershipToCart(AccountBean accountBean, Inventory inventory);

	public void removeItemFromCart(AccountBean accountBean, String invId);

	public void handleFisOptions(AccountBean accountBean);

	public void handleMagazineOption(AccountBean accountBean);

	public void handleContribution(AccountBean accountBean);

	public void addRemoveDivisionDuesAndLateFees(AccountBean accountBean);

	public boolean hasFis(AccountBean accountBean, boolean disabled);

	public void removeFisFromCart(AccountBean accountBean, boolean disabled);

	public boolean needsBackgroundCheck(Long ussaId);

	public boolean certificateIsRestricted(Long ussaId);

	public boolean isCountryUs(String country);

	public Division determineDivision(String nationCode, String stateCode, Long clubId, String zipCode);
}
