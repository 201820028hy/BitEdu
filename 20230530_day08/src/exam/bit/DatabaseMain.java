package exam.bit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * 실행 클래스
 */
public class DatabaseMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DatabaseMain main = new DatabaseMain();
		main.makeTable();
		//main.testStart();
	}
	
	public void testStart() {
		ArrayList<StudentDTO> students = this.readyData();
		
		GisaQuiz quiz = new GisaQuiz(students);
		String answer = "";
		answer = quiz.solveQuiz1();
		System.out.println("1 번째 문제 답 : " + answer);
		answer = quiz.solveQuiz2();
		System.out.println("2 번째 문제 답 : " + answer);
		answer = quiz.solveQuiz3();
		System.out.println("3 번째 문제 답 : " + answer);
		answer = quiz.solveQuiz4();
		System.out.println("4 번째 문제 답 : " + answer);
	}
	
	//문제 결과
	private void submitAnswer(int num, String answer) {
		
		try {
			String path = "./data/Ans"+num+".txt";
			FileWriter writer = new FileWriter(new File(path));
			writer.write(answer);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private ArrayList<StudentDTO> readyData() {

		DatabaseWork work = new DatabaseWork();
		ArrayList<StudentDTO> students = null;
		try {
			students = work.getStudentData();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return students;
	}

	public void makeTable() {
		try {
			this.makeData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//파일 읽기 후 데이터 입력
	private void makeData() throws IOException {
		ArrayList<StudentDTO> students = new ArrayList<>();
		
		String path = "./data/Abc1115.txt";//파일 경로
		
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String str;
		while((str = reader.readLine()) != null) { //파일의 끝은 null
			str = str.replaceAll(" ", "0");
			StudentDTO student = new StudentDTO();
			
			student.setStdNum(Integer.parseInt(str.substring(0, 6)));
			student.setEmail(str.substring(6, 10));
			student.setKor(Integer.parseInt(str.substring(10, 13)));
			student.setEng(Integer.parseInt(str.substring(13, 16)));
			student.setMath(Integer.parseInt(str.substring(16, 19)));
			student.setSci(Integer.parseInt(str.substring(19, 22)));
			student.setHist(Integer.parseInt(str.substring(22, 25)));
			student.setTotal(Integer.parseInt(str.substring(25, 28)));
			student.setMgrCode(str.substring(28, 29));
			student.setAccCode(str.substring(29, 30));
			student.setLocCode(str.substring(30, 31));
			
			students.add(student);
		}
		reader.close();
		
		DatabaseWork work = new DatabaseWork();
		work.insertData(students);
		
	}
}
