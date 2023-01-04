package co.edu.board;

import java.io.Serializable;

public class Member implements Serializable {
	private String uId;
	private String uName;
	private int uPoint;
	
	public Member(String uId, String uName, int uPoint) {
		this.uId = uId;
		this.uName = uName;
		this.uPoint = uPoint;
	}
	
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public int getuPoint() {
		return uPoint;
	}
	public void setuPoint(int uPoint) {
		this.uPoint = uPoint;
	}
}
