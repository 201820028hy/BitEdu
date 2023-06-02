package library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDAO {
	
	public ArrayList<String> selectCategorys() throws SQLException {
		ArrayList<String> categorys = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder("select category_name from book group by category_name order by category_name asc;");
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			categorys.add(rs.getString(1));
		}
		
		ConnectionManager.closeConnection(rs, pstmt, conn);
		
		return categorys;
	}

	public ArrayList<BookDTO> selectSearchList(int menuNo, String word) throws SQLException {
		ArrayList<BookDTO> books = new ArrayList<>();

		
		Connection conn = ConnectionManager.getConnection();
		StringBuilder sql = new StringBuilder("select * from book where 1=1 and display_check = 'T' ");
		
		if(word == null) {
			menuNo = -1;
		}
		
		switch (menuNo) {
		case 0:
			sql.append("and category_name like concat('%', ?, '%')");
			break;
		case 1:
			sql.append("and title like concat('%', ?, '%') ");
			break;
		case 2:
			sql.append("and content like concat('%', ?, '%') ");
			break;
		case 3:
			sql.append("and author like concat('%', ?, '%') ");
			break;
		case 4:
			sql.append("and rental_check = ?");
			break;
		default:
			break;
		}
		
		sql.append(" order by field(rental_check, 'F', 'T'), title asc, author asc;");
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		if(word != null) {
			pstmt.setString(1, word);
		}
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			BookDTO book = new BookDTO(rs.getInt("book_no"), rs.getString("title"), rs.getString("content"), rs.getString("author"), 
					rs.getString("rental_check"), rs.getString("display_check"), rs.getString("category_name"));
			books.add(book);
		}
		
		ConnectionManager.closeConnection(rs, pstmt, conn);
		
		return books;
	}
	
	public ArrayList<BookDTO> selectBookList() {
		ArrayList<BookDTO> books = null;
		
		return books;
	}
	
	public BookDTO selectBook(int book_no) {
		BookDTO book = null;
		
		return book;
	}
}
