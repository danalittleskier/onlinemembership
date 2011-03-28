package org.ussa.app;

import java.util.Calendar;
import java.util.Date;

import org.springframework.scheduling.timer.ScheduledTimerTask;

public class TimerTaskScheduler extends ScheduledTimerTask
{
	public void setStartTime(String startTime) {
		String[] elements = startTime.split(":");
		int hour = Integer.valueOf(elements[0]);
		int minute = Integer.valueOf(elements[1]);
		Date startDate = getNotificationDate(hour, minute);
		Date now = new Date();

		this.setDelay(startDate.getTime() - now.getTime());
	}

	private Date getNotificationDate(int hourOfDay, int minute) {
		Calendar notificationDate = Calendar.getInstance();
		Calendar now = Calendar.getInstance();

		notificationDate.set(Calendar.MILLISECOND, 0);
		notificationDate.set(Calendar.MINUTE, minute);
		notificationDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
		notificationDate.set(Calendar.SECOND, 0);

		// Roll the notification clock forward to the next notification hour / minute if the hour
		// and minute combination is before now.
		if (notificationDate.before(now)) {
			notificationDate.add(Calendar.DATE, 1);
		}

		return notificationDate.getTime();
	}
}
