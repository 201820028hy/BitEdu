package utils;

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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionManager {
	private static ConnectionManager manager;
	
	private ConnectionManager() {
		
	}
	
	public static ConnectionManager getInstance() {
		if(manager == null) {
			manager = new ConnectionManager();
		}
		
		return manager;
	}
	
	public Connection getConnection() {
		//커넥션 풀링까지 제공
		Connection con = null;
		
		try {
			Context ctx = new InitialContext();//환경정보
			Context env = (Context) ctx.lookup("java:/comp/env");//자바에 컴포넌트 있을건데
			DataSource ds = (DataSource) env.lookup("jdbc/book");//book이라는 데이터 소스 정보 줄래
			con = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	public Connection getConnectionOld() {
		Connection con = null;
		Properties prop = new Properties();
		try {
			prop.load(new FileReader("D:\\lhy\\bipa_workspace\\ServletLibrary\\src\\main\\java\\utils\\db.properties"));
			Class.forName(prop.getProperty("driver"));
			con = DriverManager.getConnection(prop.getProperty("jdbcURL"), prop.getProperty("user"), prop.getProperty("pwd"));
			System.out.println("연결 성공");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return con;
	}
	
	public void closeConnection(ResultSet rs, PreparedStatement pstmt, Statement stmt, Connection con) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(pstmt != null) {
			try {
				pstmt.close();
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
		
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			con = null;
		}
	}
}
