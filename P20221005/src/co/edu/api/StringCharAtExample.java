package co.edu.api;

public class StringCharAtExample {
	public static void main(String[] args) {
		String ssn = "010624-1230123"; //주민등록번호
		char sex = ssn.charAt(7);
		switch(sex) {
		case '1' :
		case '3' :
			System.out.println("남자입니다");
			break;
		case '2' :
		case '4' : 
			System.out.println("여자입니다.");
			break;
		}
	}
}
