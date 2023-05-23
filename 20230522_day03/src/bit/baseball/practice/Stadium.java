package bit.baseball.practice;

import java.util.Arrays;
import java.util.Scanner;


public class Stadium {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stadium stadium = new Stadium();
		//stadium.playBall();
	}
	
	public void playBall() {
		boolean flag = false;
		String[] hiddenBall = GameHelper.generateGameBall();
		System.out.println(Arrays.toString(hiddenBall));
		Game game = new Game(hiddenBall);
		int[] result = null;
		Scanner scan = new Scanner(System.in);
		String[] tryBall = null;
		while(!flag) {
			while(true) {
				System.out.print("중복되지 않은 4자리 숫자를 입력하세요 >>> ");
				String tryNumber = scan.nextLine();
				tryBall = GameHelper.checkGameBall(tryNumber);
				if(tryBall==null) {
					System.out.println("중복된 숫자가 있습니다.");
					continue;
				} else {
					break;
				}
			}
			System.out.println(Arrays.toString(tryBall));
			result = game.judge(tryBall);
			String temp = null;
			if(result[0]==4) {
				System.out.println(result[0]+" Strike(s). 정답입니다.");
				System.out.print("한게임 더 하시겠습니까? [예:yes / 아니오:no] >>> ");
				temp = scan.nextLine();
				if(temp.equals("no")) {
					flag = true;
					System.out.println("게임을 종료합니다.");
				}
			} else {
				System.out.println(result[0]+" Strike(s) "+result[1]+" Ball(s)");
				System.out.print("게임을 그만 하시겠습니까? [예:yes / 아니오:no] >>> ");
				temp = scan.nextLine();
				if(temp.equals("yes")) {
					flag = true;
					System.out.println("게임을 종료합니다.");
				}
			}
		}
		scan.close();
	}

}
