package exam.bit;

/*
 * 로직 클래스
 */
public class GisaQuiz {
	private GisaDAO dao;
	
	public GisaQuiz() {
		dao = new GisaDAO();
	}
	
	//문제 1 처리 로직
	public String solveQuiz1() {
		String answer = null;
		
		//로직 처리(SQL)
		StringBuffer sql = new StringBuffer("SELECT stdNo FROM students ")
				.append("WHERE locCode = 'B' ")
				.append("ORDER BY (kor+eng) DESC, stdNo ASC ")
				.append("LIMIT 4, 1");
		answer = String.valueOf(dao.selectQuiz(sql.toString()));

		return answer;
	}
	
	//문제 2 처리 로직
	public String solveQuiz2() {
		String answer = null;
		
		StringBuffer sql = new StringBuffer("SELECT MAX(kor + eng) FROM students ")
				.append("WHERE locCode = 'B'");
		answer = String.valueOf(dao.selectQuiz(sql.toString()));

		return answer;
	}
	
	//문제 3 처리 로직
	public String solveQuiz3() {
		String answer = null;
		
		StringBuffer sql = new StringBuffer("SELECT SUM(total + ")
				.append("CASE WHEN accCode = 'A' THEN 5 WHEN accCode = 'B' THEN 15 WHEN accCode = 'C' THEN 20 END")
				.append(") FROM students ")
				.append("WHERE (eng + math >= 120)");
		answer = String.valueOf(dao.selectQuiz(sql.toString()));
		
		return answer;
	}
	
	//문제 4 처리 로직
	public String solveQuiz4() {
		String answer = null;

		StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM students ")
				.append("WHERE accCode in ('A', 'B')")
				.append("AND (kor + ")
				.append("CASE WHEN locCode = 'A' THEN 5 WHEN locCode = 'B' THEN 10 WHEN locCode = 'C' THEN 15 END")
				.append(") >= 50");
		answer = String.valueOf(dao.selectQuiz(sql.toString()));
		
		return answer;
	}
	
}
