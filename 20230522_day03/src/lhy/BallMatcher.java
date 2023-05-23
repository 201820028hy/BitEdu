package lhy;

import java.util.Arrays;

public class BallMatcher {
	private int[] systemBalls;
	
	public BallMatcher() {
		BallProvider provider = new BallProvider();
		this.systemBalls = provider.makeBall();
	}
   
   public boolean matcher(int[] userBalls) {
      boolean bool = true;

      System.out.println("유저 볼 : " + Arrays.toString(userBalls));
      //System.out.println("시스템 볼 : " + Arrays.toString(systemBalls));
      
      int strike = 0;
      int ball = 0;
      for(int i = 0; i < userBalls.length; i++) {
		  if(userBalls[i] == systemBalls[i]) {
			  strike++;
			  continue;
		  }
    	  for(int j = 0; j < systemBalls.length; j++) {
        	  if(userBalls[i] == systemBalls[j]) {
        		  ball++;
        	  }
    	  }
      }
      
      System.out.println(strike + " 스트라이크 " + ball + " 볼");
      if(strike == 4) {
    	  bool = false;
      }
      
      return bool;
   }
   
}