package co.edu.project;

public class MyUser {
	private String newId;
	private String newPw;
	private String newName;
	private String email;
	
	public MyUser(String newId, String newPw, String newName) {
		this.newId = newId;
		this.newPw = newPw;
		this.newName = newName;
	}
	
	public MyUser(String newId, String newPw, String newName, String email) {
		this.newId = newId;
		this.newPw = newPw;
		this.newName = newName;
		this.email = email;
	}
	
	

	public String getNewId() {
		return newId;
	}
	public void setNewId(String newId) {
		this.newId = newId;
	}
	public String getNewPw() {
		return newPw;
	}
	public void setNewPw(String newPw) {
		this.newPw = newPw;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "신청 아이디 : " + newId + " 신청 비번 : " + newPw + " 신청인 성함 : " + newName;
	}
}
