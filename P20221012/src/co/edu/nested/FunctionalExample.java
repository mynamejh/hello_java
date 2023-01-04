package co.edu.nested;

import java.util.List;
import java.util.function.Consumer;

import co.edu.jdbc.Employee;
import co.edu.jdbc.EmployeeDAO;

//람다식 - 많이씀 ㅋㅋ
public class FunctionalExample {
	public static void main(String[] args) {
		
//		Consumer<String> consumer = new Consumer<String>() {			
//			@Override
//			public void accept(String t) {
//				System.out.println(t + "는 짜증나");
//			}
//		};
//		consumer.accept("자바");
		
		Consumer<String> consumer = (String t) -> {
				System.out.println(t + "는 짜증나");
		};
		consumer.accept("자바");
		
		//함수적 프로그래밍 -> 데이터 처리 시 어케어케 기능부여할때 많이 쓰임.
		EmployeeDAO dao = new EmployeeDAO();
		List<Employee> list = dao.search();
		
		//stream : 데이터를 시냇물이 흘러가듯이 하나하나 흘러보낸다..데이터가 흘러가는 통로?
		//stream에서 반복문 : forEach
		//기능을 매게값으로 넣음
//		list.stream().forEach(new Consumer<Employee>() {
//			@Override
//			public void accept(Employee t) {
//				System.out.println("사번 " + t.getEmployeeId() //
//						+ ", 이름 " + t.getLastName());
//				
//			}
//		});
		
		//우ㅣ를 람다식으로 바꾼것
		list.stream().forEach((Employee t) -> System.out.println(t.toString()));
		
	}
}
