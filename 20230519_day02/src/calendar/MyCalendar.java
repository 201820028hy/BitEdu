package calendar;

import java.util.Calendar;
import java.util.List;

public class MyCalendar {
	
	public void viewMonth(int year, int month) {
		System.out.println(year + "년 " + month + "월");
		
		month = month-1;
		
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, 1);
		
		int lastWeek = cal.getActualMaximum(Calendar.WEEK_OF_MONTH); //마지막 주차
		
		System.out.println("일\t월\t화\t수\t목\t금\t토");
		for(int i = 0; i < lastWeek; i++) {
			String str = "";
			for(int j = 0; j<7; j++) {
				if(cal.get(Calendar.DAY_OF_WEEK) != j+1) {
					str += "\t";
					continue;
				}
				str += cal.get(Calendar.DATE) + "\t";
				cal.add(Calendar.DATE, 1);
				
				if(cal.get(Calendar.MONTH) != month) break;
			}
			
			System.out.println(str);
			
		}
		
		System.out.println();
	}
	
}
