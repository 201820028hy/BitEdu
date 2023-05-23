package exam.lhy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.FileSystem;
import java.util.ArrayList;
import java.util.List;

/*
 * 실행 클래스
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Main m = new Main();
		m.start();
	}
	
	public void start() {
		System.out.println("시험지를 배부합니다.");
		ArrayList<Student> students = readData();
		System.out.println("시험지을 시작합니다.");
		//로직클래스 생성
		DataService service = new DataService(students);
		String answer = null;
		
		answer = service.solveQuiz1();
		this.submitAnswer(1, answer);
		answer = service.solveQuiz2();
		this.submitAnswer(2, answer);
		answer = service.solveQuiz3();
		this.submitAnswer(3, answer);
		answer = service.solveQuiz4();
		this.submitAnswer(4, answer);
	}
	
	//문제 결과
	private void submitAnswer(int num, String answer) {
		System.out.println(num + " 번째 문제 답 : " + answer);
		
		try {
			String path = "C:/C_it/data/Ans"+num+".txt";
			FileWriter writer = new FileWriter(new File(path));
			writer.write(answer);
			writer.flush();
			writer.close();
			
//			fileOutputStream = new FileOutputStream(new File(path));
//			fileOutputStream.write(Integer.parseInt(answer));
//			fileOutputStream.flush();
//			fileOutputStream.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//파일 읽기
	private ArrayList<Student> readData() {
		ArrayList<Student> students = new ArrayList<>();
		
		String path = "./data/Abc1115.txt";//파일 경로
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
			String str;
			int i = 0;
			while((str = reader.readLine()) != null) { //파일의 끝은 null
				str = str.replaceAll(" ", "0");
				Student student = new Student();
				
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
				i++;
			}
			reader.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
		
		return students;
	}
}
