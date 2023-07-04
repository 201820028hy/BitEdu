package library.vo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.json.simple.JSONObject;

public class BookVO {
	//book_info
	private String bookIsbn;
	private String bookTitle;
	private String bookAuthor;
	private Timestamp bookPublishDate;
	private String bookPublisher;
	
	//book_copy
	private int bookSeq;
	private String bookPosition;
	private String bookStatus;
	
	public BookVO(String bookIsbn, String bookTitle, String bookAuthor, Timestamp bookPublishDate, String bookPublisher,
			int bookSeq, String bookPosition, String bookStatus) {
		super();
		this.bookIsbn = bookIsbn;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookPublishDate = bookPublishDate;
		this.bookPublisher = bookPublisher;
		this.bookSeq = bookSeq;
		this.bookPosition = bookPosition;
		this.bookStatus = bookStatus;
	}
	
	public String getBookIsbn() {
		return bookIsbn;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public Timestamp getBookPublishDate() {
		return bookPublishDate;
	}
	public int getBookSeq() {
		return bookSeq;
	}
	public String getBookPosition() {
		return bookPosition;
	}
	public String getBookStatus() {
		return bookStatus;
	}
	public String getBookPublisher() {
		return bookPublisher;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		JSONObject json = new JSONObject();
		json.put("bookIsbn", bookIsbn);
		json.put("bookIsbn", bookIsbn);
		json.put("bookTitle", bookTitle);
		json.put("bookAuthor", bookAuthor);
		json.put("bookPublishDate", format.format(bookPublishDate));
		json.put("bookPublisher", bookPublisher);
		json.put("bookSeq", bookSeq);
		json.put("bookPosition", bookPosition);
		json.put("bookStatus", bookStatus);
		
		return json.toJSONString();
	}
}
