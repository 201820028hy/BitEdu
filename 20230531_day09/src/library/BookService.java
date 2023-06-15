package library;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class BookService {
	private Scanner scan;
	private BookDAO dao;
	
	public BookService(Scanner scan) {
		this.scan = scan;
		this.dao = new BookDAO();
	}

	public String findCategorys() throws SQLException {
		String categoryName = null;
		
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("카테고리: ");
		System.out.println("1.전체");
		ArrayList<String> categorys = dao.selectCategorys();
		for(int i = 2; i <= categorys.size()-1; i++) {
			System.out.println(i + "." + categorys.get(i-2));
		}
		System.out.print("카테고리 선택: ");
		int categoryNo = Integer.parseInt(scan.nextLine());
		
		if(categoryNo != 1) {
			categoryName = categorys.get(categoryNo-2);
		}
		
		return categoryName;
	}
	
	public void findBooksByCategory(String category) throws SQLException {
		ArrayList<BookDTO> list = dao.selectSearchList(0, category);
		System.out.println();
		System.out.println("[도서 목록]");
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.printf("%-6s%-30s%-20s%-20s%-6s\n", "no", "제목", "저자", "카테고리", "대출 여부");
		System.out.println("-----------------------------------------------------------------------------------------------");
		if(list.size() > 0) {
			for(int i = 1; i <= list.size(); i++) {
				BookDTO book = list.get(i-1);
				System.out.printf("%-6s%-30s%-20s%-20s%-6s\n",
						i,
						(book.getTitle().length() >= 20 ? book.getTitle().substring(0, 15) + "..." : book.getTitle()),
						(book.getAuthor().length() >= 10 ? book.getAuthor().substring(0, 7) + "..." : book.getAuthor()),
						book.getCategoryName(),
						(book.getRentalCheck().equals("F") ? "대출 가능" : "대출 불가")
					);
			}
			System.out.println();
		} else {
			System.out.println("조회된 도서가 없습니다.");
		}
	}
	
	public void searchType() throws SQLException {
		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("검색 조건: 1.제목 | 2.내용 | 3.저자 | 4.대출 가능 도서 ");
		System.out.print("검색 조건 선택: ");
		int menuNo = Integer.parseInt(scan.nextLine());
		String word = null;
		
		if(menuNo != 4) {
			System.out.println();
			System.out.print("검색어: ");
			word = scan.nextLine();
		} else {
			word = "F";
		}
		
		ArrayList<BookDTO> list = dao.selectSearchList(menuNo, word);
		
		System.out.println();
		System.out.println("[도서 목록]");
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.printf("%-6s%-30s%-20s%-20s%-6s\n", "no", "제목", "저자", "카테고리", "대출 여부");
		System.out.println("-----------------------------------------------------------------------------------------------");
		if(list.size() > 0) {
			for(int i = 1; i <= list.size(); i++) {
				BookDTO book = list.get(i-1);
				System.out.printf("%-6s%-30s%-20s%-20s%-6s\n",
						i,
						(book.getTitle().length() >= 20 ? book.getTitle().substring(0, 15) + "..." : book.getTitle()),
						(book.getAuthor().length() >= 10 ? book.getAuthor().substring(0, 7) + "..." : book.getAuthor()),
						book.getCategoryName(),
						(book.getRentalCheck().equals("F") ? "대출 가능" : "대출 불가")
					);
			}
			System.out.println();
		} else {
			System.out.println("조회된 도서가 없습니다.");
		}
		
	}
	
	public void findBook() {
		
	}
}
