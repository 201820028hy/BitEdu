package ballGame;

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
        BallMatcher ballMatcher = new BallMatcher();
        Client client = new Client();
		boolean bool = true;
	      
	      while(bool) {
	         int[] userBalls = client.play();
	         if(!client.isFlag()) {
	        	 System.out.print("기권입니다");
	        	 break;
	         }
	         if(userBalls == null) {
	        	 continue;
	         }
	         bool = ballMatcher.matcher(userBalls);
	      }
		
		//입력 받기
//		Scanner scan = new Scanner(System.in);
//		int scanInt = scan.nextInt();
//		
//		String[] userBalls = String.valueOf(scanInt).split("");
		//랜덤한 숫자 4자리 생성
		
		//받은 수와 랜덤한 수 2중for문 돌며 체크
		
		//스트라이크 데이터 입력, 볼 데이터 입력
		
		//기권 의사 묻고 안한다 하거나 4스트라이크면 종료
	}
	

}
