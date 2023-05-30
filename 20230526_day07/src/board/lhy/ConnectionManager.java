package board.lhy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionManager {

	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Properties prop = new Properties();
			prop.load(new FileReader("./data/board.properties"));
			
			String url = prop.getProperty("url");
			String driver = prop.getProperty("driver");
			String user = prop.getProperty("user");
			String pwd = prop.getProperty("pwd");
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void closeConnection(ResultSet rs, Statement stmt, PreparedStatement pstmt, Connection conn) {
		try {
			if(rs != null) {
				rs.close();
			}
			
			if(stmt != null) {
				stmt.close();
			}
			
			if(pstmt != null) {
				pstmt.close();
			}
			
			if(conn != null) {
				conn.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
