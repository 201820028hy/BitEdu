package board.lhy;

import java.sql.SQLException;
import java.util.Scanner;

public class BoardMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		
		try {
			BoardService service = new BoardService(scanner);
			service.list();
			service.mainMenu();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
