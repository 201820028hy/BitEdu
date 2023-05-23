package exam.lhy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 로직 클래스
 */
public class DataService {
	private ArrayList<Student> studentList;
	
	public DataService(ArrayList<Student> list) {
		this.studentList = list;
	}
	
	//문제 1 처리 로직
	public String solveQuiz1() {
		String answer = null;

		Collections.sort(studentList);
		int i = 0;
		for(Student student : studentList) {
			if(!"B".equals(student.getLocCode())) {
				continue;
			}
			
			
			
			if(i == 4) {
				answer = String.valueOf(student.getStdNum());
				break;
			}
			i++;
		}
		
		return answer;
	}
	
	//문제 2 처리 로직
	public String solveQuiz2() {
		String answer = null;
		int maxScore = 0;
		
		Collections.sort(studentList);
		int i = 0;
		for(Student student : studentList) {
			if(!"B".equals(student.getLocCode())) {
				continue;
			}
			
//			if(student.getKor() + student.getEng() > maxScore) {
//				maxScore = student.getKor() + student.getEng();
//			}
			maxScore = student.getKor() + student.getEng();
			break;
		}
		
		answer = String.valueOf(maxScore);
		
		return answer;
	}
	
	//문제 3 처리 로직
	public String solveQuiz3() {
		String answer = null;

		int answerInt = 0;
		for(Student student : studentList) {
			if(student.getEng() + student.getMath() < 120) {
				continue;
			}
			
			int point = 0;
			switch (student.getAccCode()) {
			case "A":
				point = 5;
				break;
			case "B":
				point = 15;
				break;
			case "C":
				point = 20;
				break;
			default:
				break;
			}
			answerInt += student.getTotal() + point;
		}
		
		answer = String.valueOf(answerInt);
		
		return answer;
	}
	
	//문제 4 처리 로직
	public String solveQuiz4() {
		String answer = null;
		
		int answerInt = 0;
		for(Student student : studentList) {
			if(!("A".equals(student.getAccCode()) || "B".equals(student.getAccCode()))) {
				continue;
			}
			
			int point = 0;
			switch (student.getLocCode()) {
			case "A":
				point = 5;
				break;
			case "B":
				point = 10;
				break;
			case "C":
				point = 15;
				break;
			default:
				break;
			}
			
			if(student.getKor() + point >= 50) {
				answerInt++;
			}
		}
		
		answer = String.valueOf(answerInt);
		
		return answer;
	}
	
}
