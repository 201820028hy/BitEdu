package bit.baseball.practice;

import java.util.Random;

public class GameHelper {

	//4자리 hiddenNumber 생성
	public static String[] generateGameBall() {
		String[] gameBall = new String[4];
		System.out.println("게임볼을 생성합니다.");
		Random r = new Random();
		while(true) {
			int tempNumber = r.nextInt(9000)+1000;
			System.out.println(tempNumber);
			gameBall = checkGameBall(String.valueOf(tempNumber));
			if(gameBall!=null) {
				break;
			}
		}
		return gameBall;
	}
	
	//4자리수가 올바른 수인지 확인
	public static String[] checkGameBall(String tryGameBall) {
		String[] tryBall = new String[4];
		int[] checker = new int[10];
		for(int i=0;i<tryGameBall.length();i++) {
			int index = Integer.parseInt(String.valueOf(tryGameBall.charAt(i)));
			if(checker[index]!=0) { // 0의 의미는 숫자가 들어오지 않았다.
				tryBall = null;
				break;
			} else {
				checker[index] = index+1; // 0이라는 숫자가 들어오는 것을 방
				tryBall[i] = String.valueOf(index);
			}
		}
		return tryBall;
	}
}
