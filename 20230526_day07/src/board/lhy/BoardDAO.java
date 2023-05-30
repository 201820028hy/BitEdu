package board.lhy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDAO {
	
	public ArrayList<BoardDTO> selectList() throws SQLException {
		ArrayList<BoardDTO> list = new ArrayList<>();
		
		Connection conn = ConnectionManager.getConnection();
		
		//boads 테이블에서 게시물 정보를 가져와서 출력하기
		String sql = "" +
			"SELECT bno, btitle, bcontent, bwriter, bdate " +
			"FROM boards " + 
			"ORDER BY bno DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {		
			BoardDTO board = new BoardDTO();
			board.setBno(rs.getInt("bno"));
			board.setBtitle(rs.getString("btitle"));
			board.setBcontent(rs.getString("bcontent"));
			board.setBwriter(rs.getString("bwriter"));
			board.setBdate(rs.getDate("bdate"));
			
			list.add(board);
		}
		
		ConnectionManager.closeConnection(rs, null, pstmt, conn);
		
		return list;
	}
	
	public BoardDTO selectOne(int bno) throws SQLException {
		BoardDTO board = new BoardDTO();

		Connection conn = ConnectionManager.getConnection();
		
		//boards 테이블에서 해당 게시물을 가져와 출력
		String sql = "" +
			"SELECT bno, btitle, bcontent, bwriter, bdate " +
			"FROM boards " +
			"WHERE bno=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bno);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			board.setBno(rs.getInt("bno"));
			board.setBtitle(rs.getString("btitle"));
			board.setBcontent(rs.getString("bcontent"));
			board.setBwriter(rs.getString("bwriter"));
			board.setBdate(rs.getDate("bdate"));
		}
		ConnectionManager.closeConnection(null, null, pstmt, conn);
		
		return board;
	}
	
	public void insert(BoardDTO dto) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		
		//boards 테이블에 게시물 정보 저장
		String sql = "" +
			"INSERT INTO boards (btitle, bcontent, bwriter, bdate) " +
			"VALUES (?, ?, ?, now())";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, dto.getBtitle());
		pstmt.setString(2, dto.getBcontent());
		pstmt.setString(3, dto.getBwriter());
		pstmt.executeUpdate();

		ConnectionManager.closeConnection(null, null, pstmt, conn);
	}
	
	public void update(BoardDTO board) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		
		//boards 테이블에서 게시물 정보 수정
		String sql = "" +
			"UPDATE boards SET btitle=?, bcontent=?, bwriter=? " +
			"WHERE bno=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, board.getBtitle());
		pstmt.setString(2, board.getBcontent());
		pstmt.setString(3, board.getBwriter());
		pstmt.setInt(4, board.getBno());
		pstmt.executeUpdate();
		
		ConnectionManager.closeConnection(null, null, pstmt, conn);
	}
	
	public void delete(BoardDTO board) throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		
		//boards 테이블에 게시물 정보 삭제
		String sql = "DELETE FROM boards WHERE bno=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, board.getBno());
		pstmt.executeUpdate();
		
		ConnectionManager.closeConnection(null, null, pstmt, conn);
	}
	
	public void clear() throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		
		//boards 테이블에 게시물 정보 전체 삭제
		String sql = "TRUNCATE TABLE boards";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
		
		ConnectionManager.closeConnection(null, null, pstmt, conn);
			
	}
}
