package library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import library.vo.BookVO;
import utils.ConnectionManager;

public class LibraryDAO {
	
	public ArrayList<BookVO> selectAllBookList() {
		ArrayList<BookVO> bookList = new ArrayList<BookVO>();
		
		StringBuilder sb = new StringBuilder("select * from book_copy copy join book_info info ")
				.append("on info.book_isbn = copy.book_isbn ");
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionManager.getInstance().getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sb.toString());
			while(rs.next()) {
				BookVO book = new BookVO(rs.getString("book_isbn"), rs.getString("book_title"), rs.getString("book_author"),
						rs.getTimestamp("book_published_date"), rs.getString("book_publisher"), rs.getInt("book_seq"), rs.getString("book_position"), rs.getString("book_status"));
				bookList.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionManager.getInstance().closeConnection(rs, null, stmt, con);
		}
		
		return bookList;
	}
	
	public BookVO selectBookByBookSeq(int bookSeq) {
		BookVO book = null;
		StringBuilder sb = new StringBuilder("select * from book_copy copy join book_info info ")
				.append("on info.book_isbn = copy.book_isbn ")
				.append("where copy.book_seq = ?");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionManager.getInstance().getConnection();
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, bookSeq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				book = new BookVO(rs.getString("book_isbn"), 
						rs.getString("book_title"), 
						rs.getString("book_author"), 
						rs.getTimestamp("book_published_date"), 
						rs.getString("book_publisher"), 
						rs.getInt("book_seq"), 
						rs.getString("book_position"), 
						rs.getString("book_status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionManager.getInstance().closeConnection(null, pstmt, null, con);
		}
		
		return book;
	}
	
	public int insertBook(BookVO paramBook) {
		int cnt = 0;
		StringBuilder sb1 = new StringBuilder("insert into book_info(book_isbn, book_title, book_author, book_published_date, book_publisher) value(?, ?, ?, ?, ?);");
		StringBuilder sb2 = new StringBuilder("insert into book_copy(book_isbn) value(?);");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionManager.getInstance().getConnection();
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(sb1.toString());
			pstmt.setString(1, paramBook.getBookIsbn());
			pstmt.setString(2, paramBook.getBookTitle());
			pstmt.setString(3, paramBook.getBookAuthor());
			pstmt.setTimestamp(4, paramBook.getBookPublishDate());
			pstmt.setString(5, paramBook.getBookPublisher());
			cnt += pstmt.executeUpdate();
			if(cnt > 0) {
				pstmt = con.prepareStatement(sb2.toString());
				pstmt.setString(1, paramBook.getBookIsbn());
				cnt += pstmt.executeUpdate();
				if(cnt > 1) {
					con.commit();
				} else {
					con.rollback();
				}
			} else {
				con.rollback();
			}
			con.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionManager.getInstance().closeConnection(null, pstmt, null, con);
		}
		
		return cnt;
	}
	
	public int updateBook(BookVO paramBook) {
		int cnt = 0;
		StringBuilder sb1 = new StringBuilder("update book_info set book_title = ?, book_author = ?, book_published_date = ?, book_publisher = ? where book_isbn = ?");
		StringBuilder sb2 = new StringBuilder("update book_copy set book_position = ?, book_status = ? where book_seq = ?");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionManager.getInstance().getConnection();
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(sb1.toString());
			pstmt.setString(1, paramBook.getBookTitle());
			pstmt.setString(2, paramBook.getBookAuthor());
			pstmt.setTimestamp(3, paramBook.getBookPublishDate());
			pstmt.setString(4, paramBook.getBookPublisher());
			pstmt.setString(5, paramBook.getBookIsbn());
			cnt += pstmt.executeUpdate();
			if(cnt > 0) {
				pstmt = con.prepareStatement(sb2.toString());
				pstmt.setString(1, paramBook.getBookPosition());
				pstmt.setString(2, paramBook.getBookStatus());
				pstmt.setInt(3, paramBook.getBookSeq());
				cnt += pstmt.executeUpdate();
				if(cnt > 1) {
					con.commit();
				} else {
					con.rollback();
				}
			} else {
				con.rollback();
			}
			con.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionManager.getInstance().closeConnection(null, pstmt, null, con);
		}
		
		return cnt;
	}
	
	public int deleteBook(int bookSeq, String bookIsbn) {
		int cnt = 0;
		StringBuilder sb1 = new StringBuilder("delete from book_copy where book_seq = ? ;");
		StringBuilder sb2 = new StringBuilder("delete from book_info where book_isbn = ? ;");

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionManager.getInstance().getConnection();
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(sb1.toString());
			pstmt.setInt(1, bookSeq);
			cnt += pstmt.executeUpdate();
			if(cnt > 0) {
				pstmt = con.prepareStatement(sb2.toString());
				pstmt.setString(1, bookIsbn);
				cnt += pstmt.executeUpdate();
				if(cnt > 1) {
					con.commit();
				} else {
					con.rollback();
				}
			} else {
				con.rollback();
			}
			
			con.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionManager.getInstance().closeConnection(null, pstmt, null, con);
		}
		
		return cnt;
	}
	
	public int insertRental(int bookSeq, String userId) {
		int cnt = 0;
		
		StringBuilder sql1 = new StringBuilder("insert book_rental(book_seq, user_id, rental_date) ")
				.append("value(?, ?, now())");
		StringBuilder sql2 = new StringBuilder("update book_copy set book_position = 'BS-0002' where book_seq = ?");
		
		Connection con = ConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(sql1.toString());
			pstmt.setInt(1, bookSeq);
			pstmt.setString(2, userId);
			cnt += pstmt.executeUpdate();
			if(cnt > 0) {
				pstmt = con.prepareStatement(sql2.toString());
				pstmt.setInt(1, bookSeq);
				cnt += pstmt.executeUpdate();
				if(cnt > 1) {
					con.commit();
					cnt = 1;
				} else {
					con.rollback();
				}
			} else {
				con.rollback();
			}
			
			con.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionManager.getInstance().closeConnection(null, pstmt, null, con);
		}
		
		return cnt;
	}
}
