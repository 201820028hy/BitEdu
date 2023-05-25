package exam.bit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mysql.cj.xdevapi.PreparableStatement;

/*
 * 로직 클래스
 */
public class GisaQuiz {
	private ArrayList<StudentDTO> studentList;
	
	public GisaQuiz(ArrayList<StudentDTO> list) {
		this.studentList = list;
	}
	
	//문제 1 처리 로직
	public String solveQuiz1() {
		String answer = null;

		Collections.sort(studentList);
		int i = 0;
		for(StudentDTO student : studentList) {
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
		for(StudentDTO student : studentList) {
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
		for(StudentDTO student : studentList) {
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
		for(StudentDTO student : studentList) {
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
	
	public int insertData() {
		Connection conn = null;
		int cnt = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/thisisjava", "root", "Mdlgpdbs13578");
			System.out.println("연결 성공");
			
			String sql = new StringBuilder()
					.append("INSERT INTO students (stdNo, email, kor, eng, math, sci, hist, total, mngCode, accCode, locCode)")
					.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
					.toString();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			for(StudentDTO student : studentList) {
				pstmt.setInt(1, student.getStdNum());
				pstmt.setString(2, student.getEmail());
				pstmt.setInt(3, student.getKor());
				pstmt.setInt(4, student.getEng());
				pstmt.setInt(5, student.getMath());
				pstmt.setInt(6, student.getSci());
				pstmt.setInt(7, student.getHist());
				pstmt.setInt(8, student.getTotal());
				pstmt.setString(9, student.getMgrCode());
				pstmt.setString(10, student.getAccCode());
				pstmt.setString(11, student.getLocCode());
				cnt += pstmt.executeUpdate();
			}
			
			System.out.println(cnt + "행 저장");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
					System.out.println("연결 끊기");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return cnt;
	}
	
}
