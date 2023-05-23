package bit;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Main main = new Main();
		main.test();
	}

	public void test() {
		//다형성
		ArrayList list = new ArrayList();
		list.add("문자열");
		list.add(new Integer(2));
		list.add(new Data());
		list.add(5);
		
		for(int i = 0; i < 3 ; i++) {
			String temp = (String) list.get(i);
		}
		
		//아래처럼
		ArrayList<Integer> listInt = new ArrayList<>();
		listInt.add(new Integer(2));//드물게 잘못 동작할 때가 있다 사용을 자제하라고 한다  new라고 만들어봤자 변하는게 없다 왜냐먄 숫자는 안바뀌니까
		listInt.add(Integer.valueOf(3));//개념상으론 이걸 써야한다.왜 이걸 ㅆㅓ야하나??
		listInt.add(5);
		for(int i = 0; i < 3 ; i++) {
			String temp = (String) list.get(i);
		}
	}
}
