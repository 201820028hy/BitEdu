package exam.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class TestPlace {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TestPlace testPlace = new TestPlace();
		testPlace.startTest();
	}
	
	//문제 풀기 시작
	public void startTest( ) {
		System.out.println("시험을 시작합니다.");
		
		ArrayList<StudentDTO> studentList = readFile();
		ExamService service = new ExamService(studentList);
		
		String answer = null;
		answer = service.examQuiz1();
		System.out.println("문제 1번 정답 : " + answer);
		endQuiz(1, answer);
		answer = service.examQuiz2();
		System.out.println("문제 2번 정답 : " + answer);
		endQuiz(2, answer);
		answer = service.examQuiz3();
		System.out.println("문제 3번 정답 : " + answer);
		endQuiz(3, answer);
		answer = service.examQuiz4();
		System.out.println("문제 4번 정답 : " + answer);
		endQuiz(4, answer);

		System.out.println("시험을 종료합니다.");
	}
	
	//결과 파일 생성
	private void endQuiz(int quizNum, String answer) {
		String path = "./data/Ans" + quizNum + ".txt";
		try {
			FileWriter write = new FileWriter(new File(path));
			BufferedWriter e = new BufferedWriter(write);
			e.write(answer);
			e.flush();
			write.flush();
			e.close();
			write.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	//파일 데이터 읽기
	private ArrayList<StudentDTO> readFile() {
		ArrayList<StudentDTO> studentList = new ArrayList<StudentDTO>();
		
		try {
			FileReader fileReader = new FileReader(new File("./data/Abc1115.txt"));
			BufferedReader bufferReader = new BufferedReader(fileReader);
			
			String line = "";
			while((line = bufferReader.readLine()) != null) {
				//아래 부분 나중에 롬복 빌드 이용해서 한줄로 줄여보기
				StudentDTO dto = new StudentDTO();
				dto.setStdNum(Integer.parseInt(line.substring(0, 6)));
				dto.setEmail(line.substring(6, 10));
				dto.setKor(Integer.parseInt(line.substring(10, 13).trim()));
				dto.setEng(Integer.parseInt(line.substring(13, 16).trim()));
				dto.setMath(Integer.parseInt(line.substring(16, 19).trim()));
				dto.setSci(Integer.parseInt(line.substring(19, 22).trim()));
				dto.setHist(Integer.parseInt(line.substring(22, 25).trim()));
				dto.setTotal(Integer.parseInt(line.substring(25, 28).trim()));
				dto.setMng(line.substring(28, 29));
				dto.setAcc(line.substring(29, 30));
				dto.setLoc(line.substring(30, 31));
				
				studentList.add(dto);
			}
			
			bufferReader.close();
			fileReader.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return studentList;
	}

}
