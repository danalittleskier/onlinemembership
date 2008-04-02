package org.ussa.bl.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.ussa.bl.RulesBL;
import org.ussa.dao.ParameterTableDao;
import org.ussa.model.ParameterTable;

public class RulesBLImpl implements RulesBL
{
	private ParameterTableDao parameterTableDao;

	public void setParameterTableDao(ParameterTableDao parameterTableDao)
	{
		this.parameterTableDao = parameterTableDao;
	}

	public String getCurrentRenewSeason()
	{
		return String.valueOf(calculateCurrentRenewSeason());
	}

	public String getLastSeason()
	{
		return String.valueOf(calculateCurrentRenewSeason()-1);
	}

	public int calculateCurrentRenewSeason()
	{
		ParameterTable parameter = parameterTableDao.get(ParameterTable.LAST_DAY_RENEW_SEASON);

		SimpleDateFormat format = new SimpleDateFormat("MM-dd");
		Calendar currentTime = Calendar.getInstance();

		int season;
		try
		{
			Calendar lastDayRenewSeason = (Calendar) currentTime.clone();
			lastDayRenewSeason.setTime(format.parse(parameter.getParameterData()));
			lastDayRenewSeason.set(Calendar.MILLISECOND, 0);
			lastDayRenewSeason.set(Calendar.SECOND, 0);
			lastDayRenewSeason.set(Calendar.HOUR_OF_DAY, 0);
			lastDayRenewSeason.set(Calendar.YEAR, currentTime.get(Calendar.YEAR));
			lastDayRenewSeason.add(Calendar.DATE, 1);
			if(currentTime.before(lastDayRenewSeason))
			{
				season = currentTime.get(Calendar.YEAR);
			}
			else
			{
				season = currentTime.get(Calendar.YEAR)+1;
			}
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			season = currentTime.get(Calendar.YEAR)+1;
		}

		return season;
	}

	public Long getNextUssaId()
	{
		// change this to a select for update, and then increment it and commit.
		ParameterTable parameter = parameterTableDao.get(ParameterTable.USSAID);
		String ussaId = parameter.getParameterData();

		return new Long(ussaId);
	}
}
