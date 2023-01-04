package co.edu.project;

public class MyReply {
	private int reNum;
	private int bNum;
	private String reContent;
	private String reWriter;
	private String reDate;
	private int popular;
	private String brdTitle;
	
	

	public MyReply() {	
	}
	
	public MyReply(int bNum, String brdTitle, int popular) {
		this.bNum = bNum;
		this.brdTitle =  brdTitle;
		this.popular = popular;
	}
	public MyReply(int reNum, String reContent) {
		this.reNum = reNum;
		this.reContent = reContent;
	}
	public MyReply(int bNum, String reContent, String reWriter) {
		this.bNum = bNum;
		this.reContent = reContent;
		this.reWriter = reWriter;
	}
	
	public MyReply(int reNum, int bNum, String reContent, String reWriter, String reDate) {
		this.reNum  = reNum;
		this.bNum = bNum;
		this.reContent = reContent;
		this.reWriter = reWriter;
		this.reDate = reDate;
		
	}
	
	public int getReNum() {
		return reNum;
	}
	public void setReNum(int reNum) {
		this.reNum = reNum;
	}
	public int getbNum() {
		return bNum;
	}
	public void setbNum(int bNum) {
		this.bNum = bNum;
	}
	public String getReContent() {
		return reContent;
	}
	public void setReContent(String reContent) {
		this.reContent = reContent;
	}
	public String getReWriter() {
		return reWriter;
	}
	public void setReWriter(String reWriter) {
		this.reWriter = reWriter;
	}
	public String getReDate() {
		return reDate;
	}
	public void setReDate(String reDate) {
		this.reDate = reDate;
	}
	public int getPopular() {
		return popular;
	}
	public void setPopular(int popular) {
		this.popular = popular;
	}
	public String getBrdTitle() {
		return brdTitle;
	}
	public void setBrdTitle(String brdTitle) {
		this.brdTitle = brdTitle;
	}

	@Override
	public String toString() {
		return "댓글 번호 : " + reNum + ", 댓글 내용 : " + reContent + ", 댓쓴이 : " + reWriter + ", 작성 시간 :" + reDate;
	}
	
	public String rank() {
		return bNum + "  " + brdTitle + "(" + popular + ")";
	}
}
