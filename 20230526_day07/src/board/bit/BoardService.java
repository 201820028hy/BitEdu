package board.bit;

import java.sql.SQLException;
import java.util.ArrayList;

/*
 * 비즈니스에 관련된 용어만 쓴다
 * insert, delete 등 적으면 안된다
 */
public class BoardService {
	private BoardDAO dao;
	
	public BoardService() {
		this.dao = new BoardDAO();
	}

	public void registItem(BoardDTO item) {
		// 글쓰기
		try {
			dao.insert(item);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BoardDTO readItem(int bno) {
		BoardDTO item = null;
		// 게시글 조건 조회
		try {
			item = dao.select(bno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return item;
	}
	
	public boolean removeAll() {
		boolean flag = false;
		// 게시글 전체 삭제
		String sql = "delete from boards;";
		try {
			flag = dao.delete(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public ArrayList<BoardDTO> readAll() {
		ArrayList<BoardDTO> list = null;
		// 게시글 전체 조회
		try {
			list = dao.selectAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public boolean modify(BoardDTO item) {
		boolean flag = false;
		// 게시글 수정
		try {
			flag = dao.update(item);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public boolean remove(int bno) {
		boolean flag = false;
		// 게시글 삭제
		String sql = "delete from boards where bno = " + bno;
		try { 
			flag = dao.delete(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
}
