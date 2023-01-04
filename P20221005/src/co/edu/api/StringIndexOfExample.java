package co.edu.api;

public class StringIndexOfExample {
	public static void main(String[] args) {
		String subject = "자바 프로그래밍";
		
		int location = subject.indexOf("프로그래밍");
		System.out.println(location);
		
		//indexOf 메소드 - 매개값으로 주어진 문자열이 시작되는 인덱스를 리턴, 
		//인덱스는 0부터 시작, 존재 하지 않는다면 -1을 산출한다.
		if(subject.indexOf("자바") != -1) {
			System.out.println("자바와 관련된 책이군용");
		} else {
			System.out.println("자바와 관련없는 책이군요");
		}
	}
}
