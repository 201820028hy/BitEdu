package library.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import library.service.LibraryService;
import library.vo.BookVO;

public class LibraryController extends HttpServlet {

	/**
	 * 도서 목록/조회/기능관리
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//resp.setContentType("application/json; charset=UTF-8"); tq 이러게 쓰니까 상태가 200인데도 error로 담김 html로 변경
		resp.setContentType("text/html; charset=UTF-8");
		LibraryService service = new LibraryService();
		String url = "./library/book_list.jsp";
		
		String bookSeq = req.getParameter("book_seq");
		String cmd = req.getParameter("cmd");
		String userId = req.getParameter("user_id");
		
		if(cmd == null) {
			if(bookSeq == null) {
				ArrayList<BookVO> bookList = new ArrayList<BookVO>();
				try {
					bookList = service.bookList();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				req.setAttribute("list", bookList);
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, resp);
			} else {
				if(userId != null) {
					this.doPost(req, resp);
				} else {
					BookVO book = service.bookInfo(Integer.parseInt(bookSeq));
					req.setAttribute("info", book);
					url = "./library/book_update.jsp";
					RequestDispatcher rd = req.getRequestDispatcher(url);
					rd.forward(req, resp);
				}
			}
		} else {
			switch (cmd) {
				case "delete": {
					this.doDelete(req, resp);
				}
				case "regist": {
					url = "./library/book_regist.jsp";
					RequestDispatcher rd = req.getRequestDispatcher(url);
					rd.forward(req, resp);
				}
				case "detail": {
					BookVO book = service.bookInfo(Integer.parseInt(bookSeq));
					req.setAttribute("info", book);
					url = "./library/book_detail.jsp";
					RequestDispatcher rd = req.getRequestDispatcher(url);
					rd.forward(req, resp);
				}
			}
		}
	}
	
	/**
	 * 도서 수정/예약
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		//PrintWriter writer = resp.getWriter();
		
		String bookSeq = req.getParameter("book_seq");
		String userId = req.getParameter("user_id");
		if(bookSeq != null) {
			if(userId != null) {
				LibraryService service = new LibraryService();
				int cnt = service.bookRental(Integer.parseInt(bookSeq), userId);
				
//				req.setAttribute("rental_book", cnt);
//				RequestDispatcher rp = req.getRequestDispatcher("./libraryService");
//				rp.forward(req, resp);
				resp.sendRedirect("./libraryService?rental_cnt="+cnt);
			} else {
				this.doPut(req, resp);
			}
		} else {
			String bookIsbn = req.getParameter("isbn");
			String bookTitle = req.getParameter("book_title");
			String bookAuthor = req.getParameter("author");
			String bookPublisher = req.getParameter("publisher");
			int year = Integer.parseInt(req.getParameter("year"));
			int month = Integer.parseInt(req.getParameter("month"));
			int date = Integer.parseInt(req.getParameter("date"));
			BookVO book = new BookVO(bookIsbn, bookTitle, bookAuthor, 
					Timestamp.valueOf(year + "-" + month + "-" + date + " 00:00:00.0"),
					bookPublisher, 0, null, null);

			LibraryService service = new LibraryService();
			int cnt = service.setBookInfo(book);
			
//			writer.write("{\"result\":" + cnt + "}");
//			writer.flush();
//			writer.close();
			
			resp.sendRedirect("./libraryService");
		}
	}
	
	/**
	 * 도서 수정
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int bookSeq = Integer.parseInt(req.getParameter("book_seq"));
		String bookIsbn = req.getParameter("isbn");
		String bookTitle = req.getParameter("book_title");
		String bookAuthor = req.getParameter("author");
		String bookPublisher = req.getParameter("publisher");
		int year = Integer.parseInt(req.getParameter("year"));
		int month = Integer.parseInt(req.getParameter("month"));
		int date = Integer.parseInt(req.getParameter("date"));
		String bookPosition = req.getParameter("book_position");
		String bookStatus = req.getParameter("book_status");
		BookVO book = new BookVO(bookIsbn, bookTitle, bookAuthor, 
				Timestamp.valueOf(year + "-" + month + "-" + date + " 00:00:00.0"),
				bookPublisher, bookSeq, bookPosition, bookStatus);

		LibraryService service = new LibraryService();
		int cnt = service.updateBook(book);
		
		resp.sendRedirect("./libraryService");
	}
	
	/**
	 * 도서 삭제
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int bookSeq = Integer.parseInt(req.getParameter("book_seq"));
		String bookIsbn = req.getParameter("book_isbn");
		
		LibraryService service = new LibraryService();
		int cnt = service.removeBook(bookSeq, bookIsbn);
		
		resp.sendRedirect("./libraryService");
	}
}
