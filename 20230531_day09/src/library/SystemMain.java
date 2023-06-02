package library;

import java.sql.SQLException;
import java.util.Scanner;

public class SystemMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SystemMain main = new SystemMain();
		main.start();
	}

	private void start() {
		Scanner scan = 	new Scanner(System.in);
		
		BookService service = new BookService(scan);
		//!-------임시
		boolean flag = true;
		try {
			while(flag) {
				
				System.out.println("도구 선택 - 1. 카테고리별 도서 목록 | 2. 검색별 도서 목록");
				String menu = scan.nextLine();
				
				switch (menu) {
				case "1":
					String category = service.findCategorys();
					service.findBooksByCategory(category);
					break;
				case "2":
					service.searchType();
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//!-------임시
	}
}
