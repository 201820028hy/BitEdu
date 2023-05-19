package lhy;

import java.util.Calendar;

public class CalendarSet {
	private Calendar calendar;
	private int week;
	private int date;
	
	public CalendarSet(int year, int month) {
		this.calendar = Calendar.getInstance();
		this.calendar.set(year, month, date);
		
		this.week = this.calendar.get(Calendar.WEEK_OF_MONTH);
	}

	public Calendar getCalendar() {
		return calendar;
	}
	
	public int getWeek() {
		return week;
	}
	
	public int getDate() {
		return date;
	}
	
	public int getMaxDate() {
		int maxDate = this.calendar.getMaximum(Calendar.DATE);
		return maxDate;
	}
	
	public int addDate(int i) {
		this.calendar.set(Calendar.DATE, i);
		this.date = this.calendar.get(Calendar.DATE);
		
		return this.date;
	}

//	public void setCalendar(Calendar calendar) {
//		this.calendar = calendar;
//	}
}
