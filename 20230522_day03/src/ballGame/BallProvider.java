package ballGame;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class BallProvider {

	   //시스템 볼 중복체크
	   private void checkValue() {
	      
	   }
	   
	   //볼 생성
	   public int[] makeBall() {
	      int[] systemBalls = new int[4];
	      
	      ArrayList<Integer> balls = new ArrayList<>();
	      for(int i = 0; i < 10; i++) {
	         balls.add(i);
	      }
	      
	      for(int j = 0; j < systemBalls.length; j++) {
	         Random random = new Random();
	         int ranNum = random.nextInt(balls.size());
	         
	         if(balls.get(ranNum) == 0 && j == 0) {
	           j--; 
	            continue;
	         }
	         systemBalls[j] = balls.remove(ranNum);
	      }
	      return systemBalls;
	   }

	}