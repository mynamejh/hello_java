package co.edu.memo;

import java.io.Serializable;

public class Memo implements Serializable { 
	//객체를 파일로 저장 or 네트워크로 전송할 목적일때 Serializable 선언 필요

	private int no;
	private String date;
	private String content;
	
	public Memo(int no, String date, String content) {
		this.no = no;
		this.date = date;
		this.content = content;
	}
	
	//getter/setter
	public void setNo(int no) {
		this.no = no;
	}
	public int getNo() {
		return no;
	}
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "번호 : " + no + ", 날짜 : " + date + ", 내용 : " + content;
	}
}
