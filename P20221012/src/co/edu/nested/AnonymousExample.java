package co.edu.nested;

class People{
	int height;
	int weight;
	
	void showInfo() {
		System.out.println("showInfo");
	}
}

class Student extends People {
	int studNo;
	
}
public class AnonymousExample {
	public static void main(String[] args) {
		// 익명 상속 클래스.(people이라는 클래스를 상속받는 익명의 어쩌구..)
		People people = new People() {
			
			String color;
			@Override
			void showInfo() {
				System.out.println("익명 showInfo");
				showColor();
			}
			
			void showColor() {
				System.out.println(this.color);
			}
		};
	}
}
