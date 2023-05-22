package lhy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
	private static boolean flag = true;
   
   public int[] play () {
      Scanner sc = new Scanner(System.in);
      
      String input = sc.next();
      if(input.contains("기권")) {
    	  flag = false;
    	  return null;
    	  
      }
      int[] userBalls = new int[4];
      List<Integer> intgers = new ArrayList<>();
      char[] charArr = input.toCharArray();
      
      if(charArr.length < 4) {
    	  System.out.println("4자리 숫자를 입력하세요.");
    	  return null;
      }
      
      for(int i = 0; i < charArr.length; i++) {
 		 int charNum = Integer.parseInt(String.valueOf(charArr[i]));
 		 if(intgers.contains(charNum)) {
	    	 System.out.println("중복되지 않은 숫자를 입력하세요.");
			 return null;
 		 }
		 intgers.add(charNum);
      }
      
      for(int i = 0; i < intgers.size();i++) {
    	  userBalls[i] = (int) intgers.toArray()[i];
      }

      return userBalls;
      
   }
   public boolean isFlag() {
	   return this.flag;
   }
}