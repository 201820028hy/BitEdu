package lotto.lhy;

//데이터 클래스
public class LottoBall {

	private int number;
	private boolean isSelected;
	
	public LottoBall(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
//	public void setNumber(int number) {
//		this.number = number;
//	}
	
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
}
