package bitedu.bipa.member.vo;

public class TestVo {
	private String id;
	private String pass;
	private String pass_check;
	private String user_name;
	private String post_code;
	private String addr1;
	private String addr2;
	private String birth_year;
	private String birth_month;
	private String birth_date;
	private String gender;
	private String[] favr;
	private String intro;
	
	public TestVo(String id, String pass, String pass_check, String user_name, String post_code, String addr1,
			String addr2, String birth_year, String birth_month, String birth_date, String gender, String[] favr,
			String intro) {
		super();
		this.id = id;
		this.pass = pass;
		this.pass_check = pass_check;
		this.user_name = user_name;
		this.post_code = post_code;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.birth_year = birth_year;
		this.birth_month = birth_month;
		this.birth_date = birth_date;
		this.gender = gender;
		this.favr = favr;
		this.intro = intro;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPass_check() {
		return pass_check;
	}
	public void setPass_check(String pass_check) {
		this.pass_check = pass_check;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPost_code() {
		return post_code;
	}
	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getBirth_year() {
		return birth_year;
	}
	public void setBirth_year(String birth_year) {
		this.birth_year = birth_year;
	}
	public String getBirth_month() {
		return birth_month;
	}
	public void setBirth_month(String birth_month) {
		this.birth_month = birth_month;
	}
	public String getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String[] getFavr() {
		return favr;
	}
	public void setFavr(String[] favr) {
		this.favr = favr;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
}
