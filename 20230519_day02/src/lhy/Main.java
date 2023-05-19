package lhy;
import java.util.*;
/*
다음을 코딩하시오
- 만년달력을 작성하시오
- 년도와 월을 입력하면
- 해당 월을 출력합니다
- 형식)
	2023년 5월
일 월 화 수 목 금 토
    1. 2. 3. 4. 5. 6
*/

public class Main {
	
	Calendar cal;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
		int year = scan.nextInt();
		int month = scan.nextInt();
		
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, 1);
		
		//int last
//		CalendarSet cal = new CalendarSet(2023, 5);
//		Main main = new Main();
//		main.print(cal);
	}
	
//	public void print(CalendarSet cal) {
//		CalendarService service = new CalendarService();
//		int[][] dates = service.writeCalendar(cal);
//		System.out.println("년 월");
//		System.out.println("일\t월\t화\t수\t목\t금\t토");
//		for(int i=0; i<dates.length; i++) {
//			for(int j=0; j<dates[i].length; j++) {
//				System.out.println(dates[i][j]);
//			}
//		}
//	}	

}
