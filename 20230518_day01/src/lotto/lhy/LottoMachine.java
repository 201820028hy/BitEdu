package lotto.lhy;

import java.util.ArrayList;
import java.util.Random;

//로직 클래스
public class LottoMachine {
	private ArrayList<LottoBall> balls;
	
	//6개의 볼 조회
	public LottoBall[] startMachine() {
		LottoBall[] selectedBalls = new LottoBall[6]; //돌려줄 6개의 공
		
		for(int i = 0; i < selectedBalls.length; i++) {
			selectedBalls[i] = this.getBall();
		}
		
		return selectedBalls;
	}
	
	//1개의 볼 조회
	public LottoBall getBall() {
		LottoBall ball = null;
		Random random = new Random();
		
		//중복되지 않은 공이 나올때까지 반복
		while (true) {
			ball = balls.get(random.nextInt(balls.size())); //45개의 공 중에서 random번째 공 가져온다
			if(!ball.isSelected()) { //중복되지 않았다면
				ball.setSelected(true); //뽑았으니까 중복 처리
				break; //이 공을 return하기 위해서 끊기
			}
		}
		
		return ball;
	}

	public void setBalls(ArrayList<LottoBall> balls) {
		this.balls = balls;
	}
	
}
