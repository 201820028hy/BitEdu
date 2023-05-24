
package exam.lhy;

/*
 * 데이터 클래스
 */
public class Student implements Comparable<Student> { //Comparator<StudentDTO>도 가능
	private int stdNum;
	private String email;
	private int kor;
	private int eng;
	private int math;
	private int sci;
	private int hist;
	private int total;
	private String mgrCode;
	private String accCode;
	private String locCode;
	
	
	public int getStdNum() {
		return stdNum;
	}

	public void setStdNum(int stdNum) {
		this.stdNum = stdNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getSci() {
		return sci;
	}

	public void setSci(int sci) {
		this.sci = sci;
	}

	public int getHist() {
		return hist;
	}

	public void setHist(int hist) {
		this.hist = hist;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getMgrCode() {
		return mgrCode;
	}

	public void setMgrCode(String mgrCode) {
		this.mgrCode = mgrCode;
	}

	public String getAccCode() {
		return accCode;
	}

	public void setAccCode(String accCode) {
		this.accCode = accCode;
	}

	public String getLocCode() {
		return locCode;
	}

	public void setLocCode(String locCode) {
		this.locCode = locCode;
	}



	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub
		if((o.getKor()+o.getEng()) > (kor+eng)) {
			return 1;
		} else if((o.getKor()+o.getEng()) == (kor+eng)) {
			if(o.getStdNum() < stdNum) {
				return 1;
			} else {
				return -1;
			}
		} else {
			return -1;
		}
	}
	
	//강사님 버전
	/*
	private void compare(StudentDTO o1, StudentDTO o2) {
		// TODO Auto-generated method stub

		int order = 0;
		order = (o1.getKor()+o1.getEng()) - (o2.getKor()+o2.getEng());
		if(order == 0) {
			//이메일 내림차순
			order = o1.getEmail().compareTo(o2.getEmail());
		}
		return order;
	}*/
}
