package board.lhy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardService {
	private BoardDAO dao;
	private Scanner scanner;
	
	public BoardService(Scanner scanner) {
		this.dao = new BoardDAO();
		this.scanner = scanner;
	}
	
	public void mainMenu() throws SQLException {
		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("메인메뉴: 1.Create | 2.Read | 3.Clear | 4.Exit");
		System.out.print("메뉴선택: ");
		String menuNo = scanner.nextLine();
		System.out.println();
		
		switch(menuNo) {
			case "1" :
				create();
			case "2" : 
				read();
			case "3" :
				clear();
			case "4" :
				exit();
		}
	}
	
	public void list() throws SQLException {
		//타이틀 및 컬럼명 출력
		System.out.println();
		System.out.println("[게시물 목록]");
		System.out.println("-----------------------------------------------------------------------");
		System.out.printf("%-6s%-12s%-16s%-40s\n", "no", "writer", "date", "title");
		System.out.println("-----------------------------------------------------------------------");
		
		ArrayList<BoardDTO> boardList = dao.selectList();
		for(BoardDTO board : boardList) {
			System.out.printf("%-6s%-12s%-16s%-40s \n", 
					board.getBno(), 
					board.getBwriter(),
					board.getBdate(),
					board.getBtitle());
		}
		
		mainMenu();
	}
	
	private void read() throws SQLException {
		//입력 받기
		System.out.println("[게시물 읽기]");
		System.out.print("bno: "); 	
		int bno = Integer.parseInt(scanner.nextLine());
		
		//boards 테이블에서 해당 게시물을 가져와 출력
		BoardDTO board = dao.selectOne(bno);
		System.out.println("#############");
		System.out.println("번호: " + board.getBno());
		System.out.println("제목: " + board.getBtitle());
		System.out.println("내용: " + board.getBcontent());
		System.out.println("쓴이: " + board.getBwriter());
		System.out.println("날짜: " + board.getBdate());
		//보조메뉴 출력
		String menuNo = getSubMenu("read");
		System.out.println();

		if(menuNo.equals("1")) {
			update(board);
		} else if(menuNo.equals("2")) {
			delete(board);
		}
		list();
	}
	
	private void create() throws SQLException {
		//입력 받기
		BoardDTO board = new BoardDTO();
		System.out.println("[새 게시물 입력]");
		System.out.print("제목: "); 	
		board.setBtitle(scanner.nextLine());
		System.out.print("내용: "); 	
		board.setBcontent(scanner.nextLine());
		System.out.print("글쓴이: "); 	
		board.setBwriter(scanner.nextLine());
		
		//보조메뉴 출력
		String menuNo = getSubMenu("create");
		if(menuNo.equals("1")) {
			//boards 테이블에 게시물 정보 저장
			dao.insert(board);
		}
		list();
	}
	
	private void update(BoardDTO board) throws SQLException {
		//수정 내용 입력 받기
		System.out.println("[수정 내용 입력]");
		System.out.print("제목: "); 	
		board.setBtitle(scanner.nextLine());
		System.out.print("내용: "); 	
		board.setBcontent(scanner.nextLine());
		System.out.print("글쓴이: "); 	
		board.setBwriter(scanner.nextLine());
		
		//보조메뉴 출력
		String menuNo = getSubMenu("update");
		if(menuNo.equals("1")) {
			//boards 테이블에서 게시물 정보 수정
			dao.update(board);
		}
		list();
	}
	
	private void delete(BoardDTO board) throws SQLException {
		//boards 테이블에 게시물 정보 삭제
		dao.delete(board);
		list();
	}
	
	private void clear() throws SQLException {
		System.out.println("[게시물 전체 삭제]");
		String menuNo = getSubMenu("clear");
		if(menuNo.equals("1")) {
			//boards 테이블에 게시물 정보 전체 삭제
			dao.clear();
		}
		list();
	}
	
	private void exit() {
		System.out.println("** 게시판 종료 **");
		System.exit(0);
	}
	
	private String getSubMenu(String methodName) {
		String menuNo = "";
		
		switch (methodName) {
		case "read":
			System.out.println("-------------------------------------------------------------------");
			System.out.println("보조메뉴: 1.Update | 2.Delete | 3.List");
			System.out.print("메뉴선택: ");
			menuNo = scanner.nextLine();
			break;
		default:
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("보조메뉴: 1.Ok | 2.Cancel");
			System.out.print("메뉴선택: ");
			menuNo = scanner.nextLine();
			break;
		}
		
		return menuNo;
	}
}
