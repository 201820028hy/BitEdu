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

public class PageController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//resp.setContentType("application/json; charset=UTF-8"); tq 이러게 쓰니까 상태가 200인데도 error로 담김 html로 변경
		resp.setContentType("text/html; charset=UTF-8");
		LibraryService service = new LibraryService();
		String url = "./library/book_list.jsp";
		
		String bookSeq = req.getParameter("book_seq");
		if(bookSeq == null) {
//			PrintWriter writer = resp.getWriter();
			ArrayList<BookVO> bookList = new ArrayList<BookVO>();
			try {
				bookList = service.bookList();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			String jsonString = JSONArray.toJSONString(bookList);
//			writer.write("{\"list\":" + jsonString + "}");
//
//			writer.flush();
//			writer.close();
			req.setAttribute("list", bookList);
		} else {
			BookVO book = service.bookInfo(Integer.parseInt(bookSeq));
			req.setAttribute("info", book);
			url = "./library/book_update.jsp";
		}
		
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		//PrintWriter writer = resp.getWriter();
		
		String book_seq = req.getParameter("book_seq");
		if(book_seq != null) {
			this.doPut(req, resp);
		} else {
			String bookIsbn = req.getParameter("isbn");
			String bookTitle = req.getParameter("book_title");
			String bookAuthor = req.getParameter("author");
			String bookPublisher = req.getParameter("publisher");
			String[] bookPublishedDate = req.getParameter("publish_date").split("-");
			BookVO book = new BookVO(bookIsbn, bookTitle, bookAuthor, 
					new Timestamp(Integer.parseInt(bookPublishedDate[0]), Integer.parseInt(bookPublishedDate[1]), Integer.parseInt(bookPublishedDate[2]), 0, 0, 0, 0),
					bookPublisher, 0, null, null);

			LibraryService service = new LibraryService();
			int cnt = service.setBookInfo(book);
			
//			writer.write("{\"result\":" + cnt + "}");
//			writer.flush();
//			writer.close();
			
			resp.sendRedirect("./library/book_list.jsp");
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int bookSeq = Integer.parseInt(req.getParameter("book_seq"));
		String bookIsbn = req.getParameter("isbn");
		String bookTitle = req.getParameter("book_title");
		String bookAuthor = req.getParameter("author");
		String bookPublisher = req.getParameter("publisher");
		String[] bookPublishedDate = req.getParameter("publish_date").split("-");
		String bookPosition = req.getParameter("book_position");
		String bookStatus = req.getParameter("book_status");
		BookVO book = new BookVO(bookIsbn, bookTitle, bookAuthor, 
				new Timestamp(Integer.parseInt(bookPublishedDate[0]), Integer.parseInt(bookPublishedDate[1]), Integer.parseInt(bookPublishedDate[2]), 0, 0, 0, 0),
				bookPublisher, bookSeq, bookPosition, bookStatus);

		LibraryService service = new LibraryService();
		int cnt = service.updateBook(book);
		
		resp.sendRedirect("./library/book_list.jsp");
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		
		int bookSeq = Integer.parseInt(req.getParameter("book_seq"));
		String bookIsbn = req.getParameter("book_isbn");
		
		LibraryService service = new LibraryService();
		int cnt = service.removeBook(bookSeq, bookIsbn);

		writer.write("{\"result\":" + cnt + "}");
		writer.flush();
		writer.close();
	}
}
