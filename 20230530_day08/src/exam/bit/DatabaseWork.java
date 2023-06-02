package exam.bit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;                              

import com.mysql.cj.jdbc.Driver;

public class DatabaseWork {
	
	public void insertData(ArrayList<StudentDTO> students) {
		this.testConnection();
		//DB에 1000개의 DTO를 저장
		//this.insert(students);
		this.insertWithBatchAndTransaction(students);
	}
	private void insertWithBatchAndTransaction(ArrayList<StudentDTO> students) {
		//batch와 transaction을 적용하는 코드 생성
		Connection conn = null;
		try {
			conn = this.getConnection();
			conn.setAutoCommit(false);
			
			//SQL 작성
			StringBuilder sql = new StringBuilder("INSERT INTO students (stdNo, email, kor, eng, math, sci, hist, total, mngCode, accCode, locCode) ")
					.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

			//쿼리 전송 통로 생성
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			//통로를 동해서 쿼리 실행
			int cnt = 0;
			for(StudentDTO dto : students) {
				pstmt.setInt(1, dto.getStdNum());
				pstmt.setString(2, dto.getEmail());
				pstmt.setInt(3, dto.getKor());
				pstmt.setInt(4, dto.getEng());
				pstmt.setInt(5, dto.getMath());
				pstmt.setInt(6, dto.getSci());
				pstmt.setInt(7, dto.getHist());
				pstmt.setInt(8, dto.getTotal());
				pstmt.setString(9, dto.getMgrCode());
				pstmt.setString(10, dto.getAccCode());
				pstmt.setString(11, dto.getLocCode());

				pstmt.addBatch();
				cnt++;
				if(cnt % 100 == 0) { //100건 마다 처리하기 위해
					cnt = 0;
					pstmt.executeBatch();
				}
			}
			
			if(cnt > 0) {
				pstmt.executeBatch();
			}

			conn.commit();
			conn.setAutoCommit(true);
			
			//통로 정리
			pstmt.close();
			
			//연결 정리
			conn.close();
		} catch (SQLException e) {
			try {
				conn.rollback();
				conn.setAutoCommit(true);
				System.out.println(e.getMessage() + " 상의 이유로 작업이 취소되었습니다.");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void insert(ArrayList<StudentDTO> students) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		//SQL 작성
		StringBuilder sql = new StringBuilder("INSERT INTO students (stdNo, email, kor, eng, math, sci, hist, total, mngCode, accCode, locCode) ")
				.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

		//쿼리 전송 통로 생성
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		
		//통로를 동해서 쿼리 실행
		int cnt = 0;
		for(StudentDTO dto : students) {
			pstmt.setInt(1, dto.getStdNum());
			pstmt.setString(2, dto.getEmail());
			pstmt.setInt(3, dto.getKor());
			pstmt.setInt(4, dto.getEng());
			pstmt.setInt(5, dto.getMath());
			pstmt.setInt(6, dto.getSci());
			pstmt.setInt(7, dto.getHist());
			pstmt.setInt(8, dto.getTotal());
			pstmt.setString(9, dto.getMgrCode());
			pstmt.setString(10, dto.getAccCode());
			pstmt.setString(11, dto.getLocCode());
			cnt += pstmt.executeUpdate();
		}
		
		//통로 정리
		pstmt.close();
		
		//연결 정리
		conn.close();
	}
	
	//연결 테스트
	private void testConnection() {
		Connection conn;
		try {
			conn = getConnection(); 
			if(conn != null) {
				System.out.println("연결 성공");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("연결 실패");
			e.printStackTrace();
		}
	}
	
	//DB연결
	private Connection getConnection() throws ClassNotFoundException, SQLException  {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/thisisjava";
		String driverUrl = "com.mysql.cj.jdbc.Driver";
		String user = "root";
		String pwd = "Mdlgpdbs13578";
		
		//Class.forName(driverUrl);
		Driver driver = new Driver();
		Properties properties = new Properties();
		properties.put("user", user);
		properties.put("password", pwd);
		
		conn = driver.connect(url, properties);
		
		/*
		 * DriverManager.getConnection(url, user, pwd);
		 */
		
		return conn;
	}

	//DB의 데이터를 가져와서 List로 변경
	public ArrayList<StudentDTO> getStudentData() throws ClassNotFoundException, SQLException {
		ArrayList<StudentDTO> students = new ArrayList<>();
		
		//커넥션 생성
		Connection conn = getConnection();
		//쿼리 생성
		StringBuilder sql = new StringBuilder("SELECT * FROM students;");
		//쿼리 통로 생성
		Statement stmt = conn.createStatement();
		//쿼리 실행
		ResultSet rs = stmt.executeQuery(sql.toString());
		//쿼리 결과 받고 처리하기 
		while(rs.next()) { //이거 해줘야 시작한다 아니면 before start어쩌구 에러남
			StudentDTO student = new StudentDTO();
			student.setStdNum(rs.getInt("stdNo"));
			student.setEmail(rs.getString("email"));
			student.setKor(rs.getInt("kor"));
			student.setEng(rs.getInt("eng"));
			student.setMath(rs.getInt("math"));
			student.setSci(rs.getInt("sci"));
			student.setHist(rs.getInt("hist"));
			student.setTotal(rs.getInt("total"));
			student.setMgrCode(rs.getString("mngCode"));
			student.setAccCode(rs.getString("accCode"));
			student.setLocCode(rs.getString("locCode"));
			students.add(student);
		}
		//통로 정리
		rs.close();
		stmt.close();
		//커넥션 정리
		conn.close();
		
		return students;
	}
}
