package bit.baseball.practice;

public class Game {
	private String[] hiddenNumber;
	
	public Game(String[] hiddenNumber) {
		this.hiddenNumber = hiddenNumber;
	}
	
	public int[] judge(String[] tryNumber) {
		int[] result = new int[2];
		for(int i=0;i<hiddenNumber.length;i++) {
			for(int j=0;j<tryNumber.length;j++) {
				if(hiddenNumber[i].equals(tryNumber[j])) {
					if(i==j) {	
						result[0] = result[0] + 1;
					} else {
						result[1] = result[1] + 1;
					}
				}
			}
		}
		return result;
	}
}
