package work.dto;

import org.json.simple.JSONObject;

public class BookDTO {
	private int bookNo;
	private String title;
	private String content;
	private String author;
	private String rentalCheck;
	private String displayCheck;
	private String categoryName;
	
	public BookDTO(int bookNo, String title, String content, String author, String rentalCheck, String displayCheck,
			String categoryName) {
		super();
		this.bookNo = bookNo;
		this.title = title;
		this.content = content;
		this.author = author;
		this.rentalCheck = rentalCheck;
		this.displayCheck = displayCheck;
		this.categoryName = categoryName;
	}
	
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getRentalCheck() {
		return rentalCheck;
	}
	public void setRentalCheck(String rentalCheck) {
		this.rentalCheck = rentalCheck;
	}
	public String getDisplayCheck() {
		return displayCheck;
	}
	public void setDisplayCheck(String displayCheck) {
		this.displayCheck = displayCheck;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("bookNo", getBookNo());
		jsonObject.put("title", getTitle());
		jsonObject.put("content", getContent());
		jsonObject.put("author", getAuthor());
		jsonObject.put("rentalCheck", getRentalCheck());
		jsonObject.put("displayCheck", getDisplayCheck());
		jsonObject.put("categoryName", getCategoryName());
		
		return jsonObject.toJSONString();
	}
}
