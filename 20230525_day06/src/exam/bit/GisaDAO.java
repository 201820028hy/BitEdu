package exam.bit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.cj.jdbc.Driver;

//DB관 작업 전용 클래스
public class GisaDAO {
	
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/thisisjava";
		//String driverUrl = "com.mysql.cj.jdbc.Driver";
		String user = "root";
		String pwd = "Mdlgpdbs13578";
		
		//Class.forName(driverUrl);
		Driver driver = new Driver();
		Properties properties = new Properties();
		properties.put("user", user);
		properties.put("password", pwd);
		
		conn = driver.connect(url, properties);
		
		return conn;
	}
	
	//아래 selectQuiz1~4 통합
	public int selectQuiz(String sql) {
		int stdNo = 0;
		
		try {
			Connection conn = this.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				stdNo = rs.getInt(1);
			}
			stmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stdNo;
	}
	
	//1번 문제
	public int selectQuiz1(String sql) {
		int stdNo = 0;
		
		try {
			Connection conn = this.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				stdNo = rs.getInt(1);
			}
			stmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stdNo;
	}
	//2번 문제
	public int selectQuiz2(String sql) {
		int stdNo = 0;
		
		try {
			Connection conn = this.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				stdNo = rs.getInt(1);
			}
			stmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stdNo;
	}
	
	//3번 문제
	public int selectQuiz3(String sql) {
		int stdNo = 0;
		
		try {
			Connection conn = this.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				stdNo = rs.getInt(1);
			}
			stmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stdNo;
	}
	
	//4번 문제
	public int selectQuiz4(String sql) {
		int stdNo = 0;
		
		try {
			Connection conn = this.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				stdNo = rs.getInt(1);
			}
			stmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stdNo;
	}
}
