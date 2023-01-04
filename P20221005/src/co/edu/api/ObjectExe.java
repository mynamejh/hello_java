package co.edu.api;

import java.util.HashSet;
import java.util.Set;

class Student {
	String name;
	int age;

	@Override
	public String toString() {
		return "학생의 이름은 " + name + "이고, 나이는 " + age + "입니당.";
	}

	// set 컬렉션 : 동일한 인스턴스는 담아주지 않는 컬렉션.
	@Override
	// 인스턴스의 위치의 주소값이라던지, 객체가 갖고있는 독특한 값을 16지수로 보여줌
	public int hashCode() {
		return age;
	}

	@Override
	// equals라는 메소드에 object들어오고 값은 참/거짓으로만 나온다
	// Studnet의 name 값끼리 비교해서 true/false 값 낼것.
	public boolean equals(Object obj) {
		if (!(obj instanceof Student)) {
			return false;
		}
		Student str = (Student) obj;
//		return super.equals(obj);
		if (this.name.equals(str.name) && (this.age == str.age))
			return true;

		return false;
	}
}

//모든 클래스의 최상위 Object.
public class ObjectExe {
	public static void main(String[] args) {
		Object obj = new Object();
		Object obj2 = new Object();

		System.out.println(obj.equals(obj2));// 주소값 비교

		Student s1 = new Student();
		s1.name = "Hong";
		s1.age = 10;
		Student s2 = new Student();
		s2.name = "Hong";
		s2.age = 10;
		String s3 = "Hong";
		
		System.out.println(s1.equals(s1));
		System.out.println(s1.toString());
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		
		//set 컬렉션 : 인터페이스 -> 구현 클래스를 통해서 인스턴스 생성 
		Set<Integer>set/*인터페이스*/ = new HashSet<Integer>(); /*구현 클래스*/
		set.add(new Integer(100));
		set.add(new Integer(110));
		set.add(new Integer(100));
		
		System.out.println("컬렉션의 크기 : " +set.size()); 
		//동일한 값은 담지 않기 때문에 100은 중복되서  2개만 담김.
		//set 컬렉션은 논리값?의 중복을 피한다. 
		
		//Set 컬렉션에 Student 인스턴스 저장시 논리적으로 동일한 인스턴스 판단.
		//hashCode(), equals() -> 기준. 
		Set<Student> students = new HashSet<Student>();
		students.add(s1);
		students.add(s2);
		System.out.println("컬렉션의 크기 : " + students.size()); 
	}

}
