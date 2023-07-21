package bitedu.bipa.book.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitedu.bipa.book.vo.BookCopy;

@Repository("blmDAO3")
public class BlmDAO3 implements IBlmDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public boolean insertBook(BookCopy copy){
		boolean flag = false;
		//String sql1 = "insert into book_info values (?,?,?,?)";
		//String sql2 = "insert into book_copy(book_isbn) values (?)";
		
		int affectedCount1 = sqlSession.insert("mapper.book.insertBook",copy);
		int affectedCount2 = sqlSession.insert("mapper.book.insertCopy",copy.getIsbn());
		if(affectedCount1>0&&affectedCount2>0) {
			flag = true;
		}

		return flag;
	}

	@Override
	public ArrayList<BookCopy> selectBookAll(){
		ArrayList<BookCopy> list = null;
		list = (ArrayList)sqlSession.selectList("mapper.book.selectAllBook");
		System.out.println(list.size());
		return list;
	}
	
	@Override
	public ArrayList<BookCopy> selectPagingBook(HashMap<String, Integer> paramMap) {
		// TODO Auto-generated method stub
		ArrayList<BookCopy> list = null;
		list = (ArrayList)sqlSession.selectList("mapper.book.selectPagingBook", paramMap);
		System.out.println(list.size());
		return list;
	}
	
	@Override
	public int selectTotalCount() {
		// TODO Auto-generated method stub
		int total = 0;
		
		total = sqlSession.selectOne("mapper.book.selectTotalCount");
		
		return total;
	}
	
	@Override
	public boolean deleteBook(int parseInt) {
		// TODO Auto-generated method stub
		boolean flag = false;
		//String sql = "delete from book_copy where book_seq = ?";
		int affectedCount = sqlSession.delete("mapper.book.deleteBook",parseInt);
		if(affectedCount>0) {
			flag = true;
		}
		return flag;
	}
	
	@Override
	public BookCopy selectBook(int parseInt) {
		// TODO Auto-generated method stub
		BookCopy copy = null;
//		StringBuilder sb = new StringBuilder("select a.*, b.* from book_info a ");
//		sb.append("inner join book_copy b on a.book_isbn=b.book_isbn ");
//		sb.append("where b.book_seq = ?");
//		String sql = sb.toString();
		copy = sqlSession.selectOne("mapper.book.selectBookBySeq",parseInt);
		return copy;
	}
	
	@Override
	public boolean updateBook(BookCopy copy) {
		// TODO Auto-generated method stub
		boolean flag = false;
		//String sql = "update book_info set book_title = ?, book_author=?, book_published_date = ? where book_isbn = ?";
		int affectedCount = sqlSession.update("mapper.book.updateBook",copy);
		if(affectedCount>0) {
			flag = true;
		}
		return flag;
	}
}







