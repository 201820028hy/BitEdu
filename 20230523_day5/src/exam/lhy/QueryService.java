package exam.lhy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryService {
	
	public void startConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/thisisjava", "root", "Mdlgpdbs13578");
			System.out.println("연결 성공");
			
			PreparedStatement pstmt = null;
			ResultSet set = null;
			
			System.out.println("1번 문제 답 : " + query1(conn, pstmt, set));
			System.out.println("2번 문제 답 : " + query2(conn, pstmt, set));
			System.out.println("3번 문제 답 : " + query3(conn, pstmt, set));
			System.out.println("4번 문제 답 : " + query4(conn, pstmt, set));
			if(set != null) set.close();
			if(pstmt != null) pstmt.close();
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
	}
	
	public int query1(Connection conn, PreparedStatement pstmt, ResultSet set) throws SQLException {
		int result = 0;
		String sql = new StringBuilder()
				.append("SELECT stdNo FROM students ")
				.append("WHERE locCode = 'B' ")
				.append("ORDER BY kor+eng DESC, stdNo ASC ")
				.append("limit 4,1")
				.toString();
		pstmt = conn.prepareStatement(sql);
		set = pstmt.executeQuery();
		
		if(set.next()) {
			result = set.getInt("stdNo");
		}
		
		return result;
	}
	
	public int query2(Connection conn, PreparedStatement pstmt, ResultSet set) throws SQLException {
		int result = 0;
		String sql = new StringBuilder()
				.append("SELECT MAX(kor+eng) AS score FROM students ")
				.append("WHERE locCode = 'B'")
				.toString();
		pstmt = conn.prepareStatement(sql);
		set = pstmt.executeQuery();
		
		if(set.next()) {
			result = set.getInt("score");
		}
		
		return result;
		
	}
	
	public int query3(Connection conn, PreparedStatement pstmt, ResultSet set) throws SQLException {
		int result = 0;
		String sql = new StringBuilder()
				.append("SELECT ")
				.append("sum(total + (case when accCode = 'A' then 5 when accCode = 'B' then 15 when accCode = 'C' then 20 end)) AS score ")
				.append("FROM students ")
				.append("WHERE eng+math >= 120 ")
				.toString();
		pstmt = conn.prepareStatement(sql);
		set = pstmt.executeQuery();
		
		if(set.next()) {
			result = set.getInt("score");
		}
		
		return result;
	}
	
	public int query4(Connection conn, PreparedStatement pstmt, ResultSet set) throws SQLException {
		int result = 0;
		String sql = new StringBuilder()
				.append("SELECT count(*) AS score FROM students ")
				.append("WHERE accCode IN ('A', 'B')")
				.append("AND kor + (case when locCode = 'A' then 5 when locCode = 'B' then 10 when locCode = 'C' then 15 end) >= 50")
				.toString();
		pstmt = conn.prepareStatement(sql);
		set = pstmt.executeQuery();
		
		if(set.next()) {
			result = set.getInt("score");
		}
		
		return result;
	}
}
