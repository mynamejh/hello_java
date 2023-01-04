package co.edu.api;

import java.util.ArrayList;
import java.util.List;

class Box<T> { // T : 값이 입력된 시점, 클래스가 사용된 시점에 출력 값의 데이터 타입 정할게.
	// 어떤 내용물을 담기 위한 클래스..
	T obj;

	void set(T obj) {
		this.obj = obj;
	}

	T get() {
		return obj;
	}
}

class Member {
	private String name;
	private String id;
	private int point;

	public Member(String name, String id, int point) {
		super();
		this.name = name;
		this.id = id;
		this.point = point;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

}// end class

class Employee {
	private String empName;
	private String empId;
	private int salary;

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

}// end class

public class StringExe3 {
	public static void main(String[] args) {
		Box<String> box = new Box<String>();
		box.set("Hello");
//		box.set(200); error
//		String result = (String) box.get(); //리턴 타입이 오브젝트 -> 문자열로 바꾸는 캐스팅 한것
//		Integer result = (Integer) box.get(); //200 int라 출력할 때 오류나서 이렇게 바꿔줌. 이러면 헬로가 오류뜸..
		String result = box.get();
		System.out.println(result);// Hello는 가능 200은 int라 불가능

		String[] stAry;
		List<String> list = new ArrayList<String>(); // List = 컬렉션
		list.add("Hello");
//		list.add(200);

		List<Member> members = new ArrayList<Member>();
		members.add(new Member("폰호두", "pon", 10000));
		members.add(new Member("박이현", "park", 20000));
		members.add(new Member("우영경", "woo", 30000));

		List<Employee> employees = changeType(members);
		// member => Employee 로 바꾸는거
		for (Employee emp : employees) {
			System.out.println("name : "+ emp.getEmpName() + " id : " + emp.getEmpId() );
		}
		
		
		
	}

	public static List<Employee> changeType(List<Member> collect) {
		// members 값 3개를 employee3개로 바꾸기.
		List<Employee> empls = new ArrayList<Employee>();
		for (Member member : collect) {
				Employee emp = new Employee();
				emp.setEmpId(member.getId());
				emp.setEmpName(member.getName());
				emp.setSalary(member.getPoint());
				
				empls.add(emp);
		}
		return empls;
	}	
}
