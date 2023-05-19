package lotto.lhy;

import java.util.ArrayList;

//실행 클래스 
public class Studio {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//메소드 실행
		Studio studio = new Studio();
		studio.start();
	}

	//로또공과 공 돌릴 기계 세팅
	public LottoMachine ready() {
		LottoMachine machine = new LottoMachine(); //객체 생성
		
		//기본 45개의 공 세팅
		ArrayList<LottoBall> balls = new ArrayList<>();
		for (int i = 1; i <= 45; i++) {
			balls.add(new LottoBall(i)); //각각의 숫자를 가진 공을 생성하여 배열에 추가
		}
		machine.setBalls(balls);
		
		return machine;
	}
	
	//로또 추첨
	public void start() {
		System.out.println("장비를 세팅합니다.");
		LottoMachine machine = this.ready();
		
		System.out.println("추첨을 시작합니다.");
		LottoBall[] selectedBalls = machine.startMachine();
		
		System.out.println("---------------------");
		int i = 1;
		for(LottoBall ball : selectedBalls) {
			System.out.println(i + "번째 공 : " + (ball.getNumber() < 10 ? " " + ball.getNumber() : ball.getNumber()) + " 번 입니다.");
			i++;
		}
		System.out.println("---------------------");
		System.out.println("추첨이 완료되었습니다.");
	}
}
