package library;

import java.sql.Date;

public class RentalHistoryDTO {

	private int history_no;
	private int book_no;
	private int user_no;
	private Date rental_date;
	private Date return_date;
	private Date expect_return_date;
	
	public RentalHistoryDTO(int history_no, int book_no, int user_no, Date rental_date, Date return_date,
			Date expect_return_date) {
		super();
		this.history_no = history_no;
		this.book_no = book_no;
		this.user_no = user_no;
		this.rental_date = rental_date;
		this.return_date = return_date;
		this.expect_return_date = expect_return_date;
	}
	
	public int getHistory_no() {
		return history_no;
	}
	public void setHistory_no(int history_no) {
		this.history_no = history_no;
	}
	public int getBook_no() {
		return book_no;
	}
	public void setBook_no(int book_no) {
		this.book_no = book_no;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public Date getRental_date() {
		return rental_date;
	}
	public void setRental_date(Date rental_date) {
		this.rental_date = rental_date;
	}
	public Date getReturn_date() {
		return return_date;
	}
	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}
	public Date getExpect_return_date() {
		return expect_return_date;
	}
	public void setExpect_return_date(Date expect_return_date) {
		this.expect_return_date = expect_return_date;
	}
}
