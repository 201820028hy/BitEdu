package bitedu.bipa.book.service;

import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitedu.bipa.book.dao.BlmDAO3;
import bitedu.bipa.book.utils.PageInfo;
import bitedu.bipa.book.utils.PagingbarGenerator;
import bitedu.bipa.book.vo.BookCopy;

@Service("blmService4")
public class BlmService4 {

	@Autowired
	private BlmDAO3 dao;
	
	public boolean registBook(BookCopy copy) {
		boolean flag = false;
		flag = dao.insertBook(copy);
		return flag;
	}
	
	public ArrayList<BookCopy> searchBookAll(){
		ArrayList<BookCopy> list = null;
		list = dao.selectBookAll();
		return list;
	}
	public ArrayList<BookCopy> searchBookPage(String page, PageInfo info){
		ArrayList<BookCopy> list = null;
		
		HashMap<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("firstIndex", info.calcuOrderOfPage(page));
		paramMap.put("lastIndex", info.getItemsPerPage());
		
		list = dao.selectPagingBook(paramMap);
		
		return list;
	}
	public String makeNavBar(String page, String group, PageInfo info) {
		String nav = PagingbarGenerator.generatePagingInfo(Integer.parseInt(group), Integer.parseInt(page), info);
		
		return nav;
	}
	public int totalCount() {
		int totalCount = 0;
		
		totalCount = dao.selectTotalCount();
		
		return totalCount;
	}
	
	public boolean removeBook(String bookSeq) {
		// TODO Auto-generated method stub
		boolean flag = false;
		flag = dao.deleteBook(Integer.parseInt(bookSeq));
		return flag;
	}
	public BookCopy findBook(String bookSeq) {
		BookCopy copy = null;
		copy = dao.selectBook(Integer.parseInt(bookSeq));
		System.out.println(copy);
		return copy;
	}
	public boolean modifyBook(BookCopy copy) {
		// TODO Auto-generated method stub
		boolean flag = false;
		flag = dao.updateBook(copy);
		return flag;
	}
	
	public BookCopy upload(List<FileItem> items) throws Exception {
		// TODO Auto-generated method stub
		BookCopy copy = null;
		copy = new BookCopy();
		for(FileItem item : items) {
			if(item!=null&item.isFormField()) {
				//일반적인 Form값 추출
				String fieldName = item.getFieldName();
				if(fieldName.equals("isbn")) {
					copy.setIsbn(item.getString("UTF-8"));
				} else if(fieldName.equals("book_title")) {
					copy.setTitle(item.getString("UTF-8"));
				} else if(fieldName.equals("author")) {
					copy.setAuthor(item.getString("UTF-8"));
				} else if(fieldName.equals("publish_date")) {
					String date = item.getString("UTF-8");
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					try {
						Date now = df.parse(date);
						copy.setPublishDate(new Timestamp(now.getTime()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				// uplode할 데이터에 대한 정보 추출
				String fieldName = item.getFieldName();
				if(fieldName.equals("book_image")) {
					String temp = item.getName();
					System.out.println("book_image "+temp);
					int index = temp.lastIndexOf("\\");
					String fileName = temp.substring(index+1);
					copy.setBookImage(fileName);
					File uploadFile = new File("d:\\dev\\upload_files\\images\\"+fileName);
					item.write(uploadFile);//바이너리
				}
			}
		}
		return copy;
	}
	
	
	
}







