package lotto.bit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
로직 클래스(Service)
데이터를 집합으로 다루는 문법적 내용을 알고 코드로 구현할 수 있어야 한다.
컬렉션을 다룰 수 있어야 한다. - ArrayList, HashMap, Iterator
제너릭에 대해서도 알고 있어야 한다.	-> ArrayList<LottoBall>에서 LottoBall이 제너릭
배열의 사용법에 대해서 알고 있어야 한다.
메소드의 시그니처(선언)
리턴타입, 파라메터 타입
**문법알기**
*/
public class LottoMachine {
	private ArrayList<LottoBall> balls;
	
	//6개의 로또볼 꺼내겠다
	public LottoBall[] startMachine() {
		LottoBall[] selectedBalls = new LottoBall[6];

		for (int i = 0; i < selectedBalls.length; i++) {
			selectedBalls[i] = this.getBall();
		}
		
		return selectedBalls;
	}
	
	//1개의 로또볼을 꺼내겠다
	private LottoBall getBall() {
		LottoBall ball = null;
		Random ran = new Random();
		
		//중복처리
		while (true) {
			//저장소에서 랜덤으로 꺼낼 공 선택 
			ball = balls.get(ran.nextInt(balls.size()));
			
			//꺼낸 공 중복체크
			if(!ball.isSelected()) {
				//중복처리 후 빠져나감
				ball.setSelected(true);
				break;
			}
		}
		
		return ball;
	}
	
	public void setBalls(ArrayList<LottoBall> balls) {
		this.balls = balls;
	}
}
