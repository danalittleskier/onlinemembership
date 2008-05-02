package org.ussa.util;

import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils
{
	private static Calendar staticCalendar = Calendar.getInstance();

	public static Calendar getCalendar(Date date)
	{
		Calendar cal = (Calendar) staticCalendar.clone();
		cal.setTime(date);
		return cal;
	}

	public static Calendar moveToStartOfDay(Calendar cal)
	{
		Calendar newCal = getCalendar(cal.getTime());
		newCal.set(Calendar.MILLISECOND, 0);
		newCal.set(Calendar.SECOND, 0);
		newCal.set(Calendar.MINUTE, 0);
		newCal.set(Calendar.HOUR_OF_DAY, 0);
		return newCal;
	}
}
