package calendar;

import java.util.Calendar;
import java.util.Scanner;

public class CalendarService {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CalendarService service = new CalendarService();
		service.startService();
	}
	
	public void startService() {
		System.out.println("서비스를 시작합니다.");
		//달력을 만들어내는 객체를 이용하여 서비스하는 내용
		//원하는 만큼 달력을 만들어 낼 수 있다
		//원하는 달, 또는 원하는 년도의 모든 월을 볼 수 있다.
		Scanner scan = new Scanner(System.in); //키보드
		MyCalendar calendar = new MyCalendar();
		
		boolean bool = false;
		
		while(!bool) {
			//년도를 입력받고
			System.out.print("년도를 입력하세요(4자리) >>> ");
			int year = Integer.parseInt(scan.nextLine());
			
			//월을 입력받고(월 전체인지, 1개월인지 확인)
			System.out.print("월을 입력하세요(해당년도의 전체 월 출력(13입력)) >>> ");
			int month = Integer.parseInt(scan.nextLine());
			
			//객체에 요청
			if(month == 13) {
				for(int i = 0; i < 12; i++) {
					calendar.viewMonth(year, i+1);
				}
			} else {
				calendar.viewMonth(year, month);
			}
			
			//계속 서비스 이용할지 물어보고
			System.out.print("계속 이용하시겠습니까? ( y / n ) >>> ");
			String cmd = scan.nextLine();
			if(cmd.equals("n")) {
				System.out.println("END");
				bool = true;
			}
		}
		
		calendar = null;
		scan.close();
	}
}
