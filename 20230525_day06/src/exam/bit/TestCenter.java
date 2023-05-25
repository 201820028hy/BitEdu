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
public class TestCenter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TestCenter main = new TestCenter();
		main.testStart();
	}
	
	public void testStart() {
		GisaQuiz quiz = new GisaQuiz();
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
	
}
