package javastudy.book.dao;

import javastudy.book.dto.UserDTO;
import javastudy.book.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	public UserDTO login(int userNo) {
        try (Connection conn = ConnectionManager.getConnection();
                PreparedStatement pstmt = createPreparedStatement(conn, userNo);
                ResultSet rs = pstmt.executeQuery()) {

           
           // DONE : 로그인
              if(rs.next() && rs.getInt("user_no") == userNo) {
                 
                 return createUserDTO(rs);   
             }else {
               return null;      
             }
           


           } catch (SQLException e) {
               throw new RuntimeException("Failed to execute login query.", e);
           }
    }

    private PreparedStatement createPreparedStatement(Connection conn, int userNo) throws SQLException {
        String sql = "SELECT user_no, grade_code, overdue_date, (SELECT grade_name FROM grade WHERE grade_code = user.grade_code) AS grade_name FROM user WHERE user_no = ?";
        //수정 : , (SELECT grade_name FROM grade WHERE grade_code = user.grade_code) AS grade_name
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, userNo);
        return pstmt;
    }

    private UserDTO createUserDTO(ResultSet rs) throws SQLException {
        int userNo = rs.getInt("user_no");
        int gradeCode = rs.getInt("grade_code");
        java.util.Date overdueDate = rs.getDate("overdue_date");
        String gradeName = rs.getString("grade_name");//수정(추가)
        return new UserDTO(userNo, gradeCode, overdueDate, gradeName);//수정 : , gradeName 추가
    }
}
