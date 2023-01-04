package co.edu.nested;

class Person {
	// 필드
	String name;
	int age;

	// 생성자
	Person() {
	};

	// 메소드
	void showInfo() {
		//로컬 클래스
		class Local{
			void show() {
				System.out.println("show");
			}
		}
		Local local = new Local();
		local.show();
	}

	//inner클래스(인스턴스)
	class Car {
		String carName;
		
		void run() {
			System.out.println("run ..");
		}
	}
	
	//inner 클래스(정적)
	static class Calculator{
		void add() {
			System.out.println("add");
		}
	}
	class Run implements Runnable{
		@Override
		public void rund() {
			System.out.println("run 구현.");
			
		}
	}
	
	//Inner 인터페이스
	interface Runnable {
		void rund();
	}
}// end of class Person

public class NestedExample {
	public static void main(String[] args) {
		Person p = new Person();
		
		//인스턴스 멤버 클래스 - person 인스턴스 만들어야 호출?선언?가능
		Person.Car car = p.new Car();
		car.run();
		
		//정적 멤버 클래스 - 따로 person 인스턴스 안만들어줘도 된다네요..
		Person.Calculator cal = new Person.Calculator();
		cal.add();
		
		p.showInfo(); //로컬 클래스 호출하겠다는것
	}

}
