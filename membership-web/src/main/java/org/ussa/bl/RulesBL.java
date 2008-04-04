package org.ussa.bl;

import java.util.Date;

public interface RulesBL
{
	public String getCurrentRenewSeason();

	public String getLastSeason();

	public Long getNextUssaId();

	public Integer getAgeForCurrentRenewSeason(Date birthDate);
}
