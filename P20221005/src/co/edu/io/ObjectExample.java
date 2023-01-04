package co.edu.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

//ObjectOutputStream은 Serializable 을 구현하는 클래스. 
class Emp implements Serializable {
	int id;
	String name;
	String dept;

	public Emp(int id, String name, String dept) {
		this.id = id;
		this.name = name;
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "사원id : " + id + ", 이름 : " + name + ", 부서 : " + dept;
	}
}

public class ObjectExample {
	public static void main(String[] args) {
		
		try (FileInputStream fis = new FileInputStream("C:/Temp/emp.dat");
			ObjectInputStream oos = new ObjectInputStream(fis);
			) {
			ArrayList<Emp> list = (ArrayList<Emp>) oos.readObject();
			
			for(Emp emp:list) {
				System.out.println(emp.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeObj() {
		// 입출력 스트림 -> Class인스턴스 생성 -> 파일 입출력
		// ObjectInputStream, ObjectOutputStream : object 기반.
		ArrayList<Emp> empList = new ArrayList<>();
		empList.add(new Emp(100, "김호두", "인사"));
		empList.add(new Emp(200, "우영경", "개발"));
		empList.add(new Emp(300, "이유정", "총무"));

		try (FileOutputStream fos = new FileOutputStream("C:/Temp/emp.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
//					FileOutputStream fos = new FileOutputStream("C:/Temp/emp.dat"); //try ()에 해주면 굳이 close 안해줘도 됨.
//					ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(empList);

//					oos.close();
//					fos.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("completed~");
	}
}