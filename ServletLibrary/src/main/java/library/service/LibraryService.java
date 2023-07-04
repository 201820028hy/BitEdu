package library.service;

import java.sql.SQLException;
import java.util.ArrayList;

import library.dao.LibraryDAO;
import library.vo.BookVO;

public class LibraryService {
	private LibraryDAO dao;
	
	public LibraryService() {
		this.dao = new LibraryDAO();
	}
	
	public ArrayList<BookVO> bookList() throws SQLException {
		ArrayList<BookVO> bookList = dao.selectAllBookList();
		
		return bookList;
	}
	
	public BookVO bookInfo(int bookSeq) {
		BookVO book = dao.selectBookByBookSeq(bookSeq);
		
		return book;
	}
	
	public int setBookInfo(BookVO book) {
		int info = dao.insertBook(book);
		
		return info;
	}
	
	public int updateBook(BookVO book) {
		int info = dao.updateBook(book);
		
		return info;
	}
	
	public int removeBook(int bookSeq, String bookIsbn) {
		int info = dao.deleteBook(bookSeq, bookIsbn);
		
		return info;
	}
	
	public int bookRental(int bookSeq, String userId) {
		int cnt = dao.insertRental(bookSeq, userId);
		
		return cnt;
	}
}
