package org.ussa.bl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.ussa.beans.AccountBean;
import org.ussa.model.Inventory;

public interface RulesBL
{
	public Long getNextUssaId();

	public Integer getAgeForCurrentRenewSeason(Date birthDate);

	public BigDecimal calculateDiscount(AccountBean accountBean, Inventory inventory);

	public void filterMemberships(AccountBean accountBean, List<Inventory> memberships);

	public boolean inventoryIsRestricted(AccountBean accountBean, Inventory inventory);

	public void addMembershipToCart(AccountBean accountBean, Inventory inventory);
}
