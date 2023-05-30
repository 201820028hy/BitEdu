package exam.bit.lesson4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionManager {
	
	public static void closeConnection(ResultSet rs, Statement stmt, Connection conn) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static Connection getConnection() {
		Connection conn = null;
		
		String url = "";
		String driver = "";
		String user = "";
		String pwd = "";
		
		try {
			/*
			// 방법 1 : properties 파일 읽은 후 값 설정
			FileReader reader = new FileReader(new File("./data/db.properties"));
			BufferedReader br = new BufferedReader(reader);
			url = br.readLine().split("=")[1].trim();
			driver = br.readLine().split("=")[1].trim();
			user = br.readLine().split("=")[1].trim();
			pwd = br.readLine().split("=")[1].trim();
			// ! 방법 1
			 */
			
			// 방법 2 : Properties 클레스 사용
			Properties properties = new Properties();
			properties.load(new FileReader(new File("./data/db.properties")));
			
			url = properties.getProperty("db.url");
			driver = properties.getProperty("db.driver");
			user = properties.getProperty("db.id");
			pwd = properties.getProperty("db.pwd");
			// ! 방법 2
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pwd);
			
			if(conn != null) {
				System.out.println("DB success");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
}
