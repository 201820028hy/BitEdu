package lhy;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * 숫자 야구 게임
 * 1. 시스템은 중복 없는 4자리 숫자 생성(첫번째 숫자는 0이 아니어야 한다)
 * 2. 참가자는 임의의 4자리 숫자 입력
 * 3. 시스템은 받아온 4자리 숫자를 자신의 수와 비교하여 숫자가 같고 자리도 같으면 스트라이크, 숫자는 같고 자리가 틀리면 볼
 * 4. 4스트라이크가 나오기까지 게임 반복, 기권하거나 4스트라이크 나오면 종료
 */
public class BallService {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//시스템 볼 생성
		BallMatcher match = new BallMatcher();
		BallService service = new BallService();

		System.out.println("게임을 시작합니다.");
		service.startGame(match);
	}
	
	public void startGame(BallMatcher match) {
		while(true) {
			System.out.println("-------------------------------------");
			System.out.println("4자리 숫자를 입력해주세요.(기권하시려면 '기권'을 입력하세요.)");
			//입력 받기
			Scanner scan = new Scanner(System.in);
			String scanStr = scan.next();
			
			//기권 의사 묻고 종료
			if("기권".equals(scanStr)) {
				System.out.println("기권하셨습니다. 게임을 종료합니다.");
				break;
			}
			
			String[] scanArr = scanStr.split("");
			if(scanArr.length < 4) {
				System.out.println("4글자를 입력하세요.");
				continue;
			}
			int[] userBalls = new int[scanArr.length];
			for(int i = 0; i < scanArr.length; i++) {
				userBalls[i] = Integer.parseInt(scanArr[i]);
			}
			
			if(!checkValue(userBalls)) {
				System.out.println("중복되지 않은 4자리 숫자를 입력해주세요.");
				continue;
			}

			//기권 의사 묻고 안한다 하거나 4스트라이크면 종료
			if(!match.matcher(userBalls)) {
				System.out.println("4 스트라이크로 게임을 종료합니다.");
				break;
			}
		}
	}
	
	//시스템 볼 중복체크
   public boolean checkValue(int[] balls) {
	   ArrayList<Integer> setBalls = new ArrayList<Integer>();
	  for(int i = 0; i < balls.length; i++) {
		  if(setBalls.contains(balls[i])) {
			  return false;
		  }
		  setBalls.add(balls[i]);
	  }
      return true;
   }
}
