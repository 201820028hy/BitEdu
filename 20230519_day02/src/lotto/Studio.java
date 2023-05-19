package lotto;

import java.util.ArrayList;

/*
 * 
 * 1. 프로그램이란? : 문제 해결을 위한 *작업지시의 순서를 표기한 것
 *  *작업지시 : 작업 대상 + 작업할 내용
 * 객체지향의 3가지 특징
 * 1. 상속성
 * 2. 다형성
 * 3. 은닉성 문법 알기 코드로 구현하기(데이터클래스에 적용)
 */

//실행 클래스
/*
 * 다음의 조건 추가
1. 오름차순 정렬
2. 중복처리
3. 공이 섞임
4. 일정 간격으로 볼 추첨
 * */
public class Studio {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Studio studio = new Studio();//static에선 자신을 생성 후 메서드 호출 ready()X
		//this.onAir() X 이유 :static레벨에서는 non static 사용 불가. 객체가 되었을때 자신을 지칭하는게 this(동작하는 기계 자체)
		studio.onAir();
	}
	
	public LottoMachine ready() {
		//머신과 로또 볼을 준비 초기화
		//프로퍼티로 해도 되지만 쓰레드 변경?될 수 있기 때문에 여기서 초기화
		LottoMachine machine = new LottoMachine();
		ArrayList<LottoBall> balls = new ArrayList<>();
		
		//왜 여기서 for문을 돌릴까? -> 로직클래스에서 돌리면 객체 생성될 때마다 돌려지니까 당연함
		for(int i=1; i<=45; i++) {
			balls.add(new LottoBall(i));
		}
		machine.setBalls(balls);
		
		return machine;
	}
	
	public void onAir() {
		//로또 머신에게 볼을 뽑도록 지시
		System.out.println("방송 시작");
		LottoMachine machine = this.ready();//ready()?
		System.out.println("추첨 시작");
		//기존 : 
		//LottoBall[] selectedBalls = machine.startMachine();
		
		//balls의 내용을 출력
		System.out.println("로또 추첨기 START");
		LottoBall[] selectedBalls = machine.startMachine();
		
		//기존 : 
//		for(LottoBall ball : selectedBalls) {
//			//System.out.println(ball.getNumber() + " 번"); //통상의 방법
//			System.out.println(ball + " 번"); //toString 호출
//		}
		System.out.println("로또 추첨기 END");
	}

}
