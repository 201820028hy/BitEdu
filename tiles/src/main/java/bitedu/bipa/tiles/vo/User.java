package bitedu.bipa.tiles.vo;

public class User {
	private String id;
	private String pwd;
	
	public User(String id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", pwd=" + pwd + "]";
	}
	
}
