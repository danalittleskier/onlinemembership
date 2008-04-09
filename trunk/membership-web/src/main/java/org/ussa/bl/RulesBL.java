package org.ussa.bl;

import java.util.Date;
import java.util.List;

import org.ussa.beans.AccountBean;
import org.ussa.model.Inventory;

public interface RulesBL
{
	public Long getNextUssaId();

	public Integer getAgeForCurrentRenewSeason(Date birthDate);

	public List<Inventory> findApplicableSportMemberships(AccountBean accountBean);

	public List<Inventory> findApplicableFisItems(AccountBean accountBean);

	public List<Inventory> findApplicableMagazineItems(AccountBean accountBean);

	public void addMembershipToCart(AccountBean accountBean, Inventory inventory);

	public void removeItemFromCart(AccountBean accountBean, String invId);

	public void handleFisOptions(AccountBean accountBean);

	public void handleMagazineOption(AccountBean accountBean);

	public void handleContribution(AccountBean accountBean);

	public void addRemoveUssaLateFee(AccountBean accountBean);
}