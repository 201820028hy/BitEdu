package exam.test;

import java.util.ArrayList;
import java.util.Collections;

public class ExamService {
	private ArrayList<StudentDTO> studentList;
	
	public ExamService(ArrayList<StudentDTO> students) {
		this.studentList = students;
	}
	
	public String examQuiz1() {
		String answer = null;
		
		Collections.sort(studentList);
		
		int i = 0;
		for(StudentDTO student : studentList) {
			if(!"B".equals(student.getLoc())) {
				continue;
			}
			
			if(i == 4) {
				answer = String.valueOf(student.getStdNum());
			}
			
			i++;
		}
		
		return answer;
	}
	
	public String examQuiz2() {
		String answer = null;
		
		Collections.sort(studentList);
		
		for(StudentDTO student : studentList) {
			if(!"B".equals(student.getLoc())) {
				continue;
			}
			
			answer = String.valueOf(student.getKor() + student.getEng());
			break;
		}
		
		return answer;
	}
	
	public String examQuiz3() {
		String answer = null;
		
		int total = 0;
		for(StudentDTO student : studentList) {
			if(student.getEng()+student.getMath() < 120) {
				continue;
			}
			
			int point;
			switch (student.getAcc()) {
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
					point = 0;
					break;
			}
			total += student.getTotal() + point;
		}
		
		answer = String.valueOf(total);
		
		return answer;
	}
	
	public String examQuiz4() {
		String answer = null;
		
		int total = 0;
		for(StudentDTO student : studentList) {
			if(!("A".equals(student.getAcc()) || "B".equals(student.getAcc()))) {
				continue;
			}
			
			int point;
			switch (student.getLoc()) {
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
					point = 0;
					break;
			}
			
			if(student.getKor()+point >= 50) {
				total++;
			}
		}
		
		answer = String.valueOf(total);
		
		return answer;
	}
}
