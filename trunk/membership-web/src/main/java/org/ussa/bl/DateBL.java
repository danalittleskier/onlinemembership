package org.ussa.bl;

import java.util.Date;

public interface DateBL
{
	public String getCurrentRenewSeason();

	public String getLastSeason();

	public Date getAlpineFisLateDate();

	public Date getCrossCountryFisLateDate();

	public Date getFisLateDate();

	public Date getLateRenewDate();

	public Integer calculateCurrentRenewSeason();
}
