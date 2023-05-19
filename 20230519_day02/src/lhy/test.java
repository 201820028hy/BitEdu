package lhy;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        int month = scanner.nextInt();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, 1);
        int i = calendar.get(Calendar.DAY_OF_WEEK); // 시작요일
        int i1 = calendar.getActualMinimum(Calendar.WEEK_OF_MONTH);//마지막 주
        int actualMaximum = calendar.getActualMaximum(Calendar.DATE);//마지막 날
        int arr[][] = new int[i1][7];

        String value = "";
        String[] days = {"일", "월", "화", "수", "목", "금", "토"};
        for (String day : days) {
            value += day + "\t";
        }
        
        value += "\n";
        
        boolean flag = true;
        int cnt = 1;
        int day = 1;
        while(flag) {
        	if(day != i) {
        		value += "\t";
        		day++;
        		continue;
        	}
        	
        	if(day > 7) {
        		day = 1;
        		value += "\n";
        	}
        	
        	value += cnt + "\t";
        	
        	if(cnt == actualMaximum) {
        		flag = false;
        	}
        	cnt++;
        }
//        for (int j = 0; j < actualMaximum; j++) {
//        	if(j+1 == i) {
//        		continue;
//        	}
//            value += j+1;
//            if(cnt > days.length) {
//            	cnt = 0;
//            	value += "\n";
//            }
//            cnt++;
//        }
        
        System.out.println(value);
    }
}
