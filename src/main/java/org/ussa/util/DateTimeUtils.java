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
}
