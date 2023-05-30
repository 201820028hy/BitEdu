package exam.bit.lesson4;

import java.sql.SQLException;

/*
 * 로직 클래스
 */
public class GisaQuiz {
	private GisaDAO dao;
	
	public GisaQuiz() {
		dao = new GisaDAO();
	}
	
	//문제 1 처리 로직
	public String solveQuiz1() throws SQLException {
		String answer = null;
		
		//로직 처리(SQL)
		StringBuilder sql = new StringBuilder("SELECT stdNo FROM students ")
				.append("WHERE locCode = 'B' ")
				.append("ORDER BY (kor+eng) DESC, stdNo ASC ")
				.append("LIMIT 4, 1");
		answer = String.valueOf(dao.selectQuiz(sql.toString()));

		return answer;
	}
	
	//문제 2 처리 로직
	public String solveQuiz2() throws SQLException {
		String answer = null;
		
		StringBuilder sql = new StringBuilder("SELECT MAX(kor + eng) FROM students ")
				.append("WHERE locCode = 'B'");
		answer = String.valueOf(dao.selectQuiz(sql.toString()));

		return answer;
	}
	
	//문제 3 처리 로직
	public String solveQuiz3() throws SQLException {
		String answer = null;
		
		StringBuilder sql = new StringBuilder("SELECT SUM(total + ")
				.append("CASE WHEN accCode = 'A' THEN 5 WHEN accCode = 'B' THEN 15 WHEN accCode = 'C' THEN 20 END")
				.append(") FROM students ")
				.append("WHERE (eng + math >= 120)");
		answer = String.valueOf(dao.selectQuiz(sql.toString()));
		
		return answer;
	}
	
	//문제 4 처리 로직
	public String solveQuiz4() throws SQLException {
		String answer = null;

		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM students ")
				.append("WHERE accCode in ('A', 'B')")
				.append("AND (kor + ")
				.append("CASE WHEN locCode = 'A' THEN 5 WHEN locCode = 'B' THEN 10 WHEN locCode = 'C' THEN 15 END")
				.append(") >= 50");
		answer = String.valueOf(dao.selectQuiz(sql.toString()));
		
		return answer;
	}
	
}
