package board.bit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class BoardDAO {
	
	//CR[all]R[condition]UD

	public boolean insert(BoardDTO item) throws SQLException {
		boolean flag = false;
		
		Connection conn = ConnectionManager.getConnection();
		String sql = "insert into boards (btitle, bcontent, bwriter, bdate) values(?, ?, ?, now())";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, item.getBtitle());
		pstmt.setString(2, item.getBcontent());
		pstmt.setString(3, item.getBwriter());
		
		int affectedCount = pstmt.executeUpdate();
		if(affectedCount > 0) {
			flag = true;
		}
		
		ConnectionManager.closeConnection(null, null, pstmt, conn);
		
		return flag;
	}
	
	public BoardDTO select(int bno) throws SQLException {
		BoardDTO item = null;
		
		Connection conn = ConnectionManager.getConnection();
		String sql = "select * from boards where bno = ?;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bno);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) { // or if(rs.next())
			//로직이 들어오지 않는다. 처리하는 동안 커넥션이 계속 유지되기 때문
			//DB의 내용을 로컬데이터셋(Data Bean)으로 저장하는 것이 주 목적
			//item = new BoardDTO(bno, rs.getString("btitle"), rs.getString("bcontent"), rs.getString("bwriter"), rs.getDate("bdate"));
			item = new BoardDTO(bno, rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
		}
		
		ConnectionManager.closeConnection(rs, null, pstmt, conn);
		
		return item;
	}
	
	public boolean delete(String sql) throws SQLException {
		boolean flag = false;
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int affectedCount = pstmt.executeUpdate(sql);
		if(affectedCount > 0) {
			flag = true;
		}
		
		ConnectionManager.closeConnection(null, null, pstmt, conn);
		
		return flag;
	}
	
	public ArrayList<BoardDTO> selectAll() throws SQLException {
		ArrayList<BoardDTO> list = new ArrayList<>();
		
		Connection conn = ConnectionManager.getConnection();
		String sql = "select * from boards";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		BoardDTO item = null;
		while(rs.next()) { // or if(rs.next())
			//로직이 들어오지 않는다. 처리하는 동안 커넥션이 계속 유지되기 때문
			//DB의 내용을 로컬데이터셋(Data Bean)으로 저장하는 것이 주 목적
			//item = new BoardDTO(bno, rs.getString("btitle"), rs.getString("bcontent"), rs.getString("bwriter"), rs.getDate("bdate"));
			item = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
			list.add(item);
		}
		
		ConnectionManager.closeConnection(rs, null, pstmt, conn);
		
		return list;
	}
	
	public boolean update(BoardDTO item) throws SQLException {
		boolean flag = false;
		
		Connection conn = ConnectionManager.getConnection();
		String sql = "update boards set btitle = ?, bcontent = ?, bwriter = ? where bno = ?;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, item.getBtitle());
		pstmt.setString(2, item.getBcontent());
		pstmt.setString(3, item.getBwriter());
		pstmt.setInt(4, item.getBno());
		
		int affectedCount = pstmt.executeUpdate();
		if(affectedCount > 0) {
			flag = true;
		}
		
		ConnectionManager.closeConnection(null, null, pstmt, conn);
		
		return flag;
	}
	
}
