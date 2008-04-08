package org.ussa.bl;

import java.util.Date;

public interface DateBL
{
	public String getCurrentRenewSeason();

	public String getLastSeason();

	public Date getAlpineFisLateDate();

	public Date getCrossCountryFisLateDate();

	public Date getFreestyleFisLateDate();

	public Date getJumpingFisLateDate();

	public Date getIpcAsLateDate();

	public Date getLateRenewDate();

	public Integer calculateCurrentRenewSeason();
}
