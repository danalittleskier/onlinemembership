package org.ussa.bl.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.ussa.bl.DateBL;
import org.ussa.dao.ParameterTableDao;
import org.ussa.model.ParameterTable;
import org.ussa.util.DateTimeUtils;

public class DateBLImpl implements DateBL
{
	private static String LATE_DATE_PATTERN = "MM-dd";
	private static SimpleDateFormat lateDateFormat = new SimpleDateFormat(LATE_DATE_PATTERN);

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
		return String.valueOf(calculateCurrentRenewSeason() - 1);
	}

	public Date getAlpineFisLateDate()
	{
		return getDateFromParameterTable(ParameterTable.ALPINE_FIS_LATE_DATE);
	}

	public Date getCrossCountryFisLateDate()
	{
		return getDateFromParameterTable(ParameterTable.CC_FIS_LATE_DATE);
	}

	public Date getFreestyleFisLateDate()
	{
		return getDateFromParameterTable(ParameterTable.FREESTYLE_FIS_LATE_DATE);
	}

	public Date getJumpingFisLateDate()
	{
		return getDateFromParameterTable(ParameterTable.ALPINE_FIS_LATE_DATE);
//		return getDateFromParameterTable(ParameterTable.JUMPING_FIS_LATE_DATE);
	}

	public Date getIpcAsLateDate()
	{
		return getDateFromParameterTable(ParameterTable.IPC_LATE_DATE);
	}

	public Date getLateRenewDate()
	{
		return getDateFromParameterTable(ParameterTable.LATE_RENEW_DATE);
	}

	private Date getDateFromParameterTable(String parameterCode)
	{
		try
		{
			Integer renewStartYear = calculateCurrentRenewSeason()-1;

			ParameterTable parameter = parameterTableDao.get(parameterCode);
			Calendar date = DateTimeUtils.getCalendar(lateDateFormat.parse(parameter.getParameterData()));
			date.set(Calendar.MILLISECOND, 0);
			date.set(Calendar.SECOND, 0);
			date.set(Calendar.HOUR_OF_DAY, 0);
			date.set(Calendar.YEAR, renewStartYear);
			date.add(Calendar.DATE, 1);
			return date.getTime();
		}
		catch (ParseException e)
		{
			throw new Error("There was an error parsing "+parameterCode+" from the parametertable. The expected format is "+ LATE_DATE_PATTERN);
		}
	}

	public Integer calculateCurrentRenewSeason()
	{
		ParameterTable parameter = parameterTableDao.get(ParameterTable.LAST_DAY_RENEW_SEASON);

		Calendar currentTime = Calendar.getInstance();

		int season;
		try
		{
			Calendar lastDayRenewSeason = DateTimeUtils.getCalendar(lateDateFormat.parse(parameter.getParameterData()));
			lastDayRenewSeason.set(Calendar.MILLISECOND, 0);
			lastDayRenewSeason.set(Calendar.SECOND, 0);
			lastDayRenewSeason.set(Calendar.HOUR_OF_DAY, 0);
			lastDayRenewSeason.set(Calendar.YEAR, currentTime.get(Calendar.YEAR));
			lastDayRenewSeason.add(Calendar.DATE, 1);
			if (currentTime.before(lastDayRenewSeason))
			{
				season = currentTime.get(Calendar.YEAR);
			}
			else
			{
				season = currentTime.get(Calendar.YEAR) + 1;
			}
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			season = currentTime.get(Calendar.YEAR) + 1;
		}

		return season;
	}

}
