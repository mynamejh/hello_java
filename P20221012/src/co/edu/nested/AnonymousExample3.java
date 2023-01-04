package co.edu.nested;

//익명구현 객체
// Fuctional 인터페이스 : 추상메소드 하나만 존재하는 인터페이스
interface MyInterface{
	void run();
}

interface Myinterface2 {
	void run(String kind);
}

interface MyInterface3 {
	int sum(int num1, int num2);
}

public class AnonymousExample3 {
	public static void main(String[] args) {
		
		//인터페이스 원래 인스턴스로 구현 못하는데, 저렇게 선언해서 익명의 구현개체로 쓰겠다는 의미
		MyInterface intf1 = new MyInterface() {
			@Override
			public void run() {
				System.out.println("MyInterface Run.");
			}
		};
		intf1.run();
		
		//람다표현식 (intf1 랑 intf2 같은 거임..)
		//람다표현식 목적 : 간단하게 코드를 구현하고 싶음..
		MyInterface intf2 = () -> System.out.println("My interface Run Run.");
		
		intf2.run();
		
		Myinterface2 intf22 = kind -> System.out.println(kind + "Run.");
		//매게변수 kind 하나 넣어서 저 메소드 할게..ㅎㅎ 
		// 메게변수 1개일때는 괄호 생략 가넝. 실행할 메소드 한개면 중괄호 생략 가넝.ㅋㅋ
		intf22.run("농구종목");
		
		MyInterface3 intf3 = (num1, num2) -> num1 * 2 + num2;

		int result = intf3.sum(10,20);
		
		intf3 = (num1, num2) -> num1 *2 + num2 * 3;
		result = intf3.sum(11, 22);
	}
}
