package lotto.bit;

//데이터 클래스(DTO, VO)
public class LottoBall {
	private int number;
	private boolean isSelected; //중복여부
	
	//생성자
	public LottoBall(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
	
	//수정할 수 있기 때문에 private
	private void setNumber(int number) {
		this.number = number;
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	//객체를 찍을 때 기본적으로 return
	@Override
	public String toString() {
		return String.valueOf(this.number);
	}
}
