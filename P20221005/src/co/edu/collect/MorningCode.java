package co.edu.collect;

import java.util.ArrayList;
import java.util.Scanner;

class Employ {
	int empId;
	String empName;
	int salary;

	public Employ(int empId, String empName, int salary) {
		this.empId = empId;
		this.empName = empName;
		this.salary = salary;
	}

	public Employ() {
	}

	@Override
	public String toString() {
		return "Employ [empId=" + empId + ", empName=" + empName + ", salary=" + salary + "]";
	}

}

public class MorningCode {
	public static void main(String[] args) {
		String tok = "Hello World Good";
		Scanner scn = new Scanner(tok);
		Scanner scanner = new Scanner(System.in);// 키보드 입력으로 들어온 애 받아들이는역할

//		while (scn.hasNext()) //읽어올 요소가 있는지 체크 
//			System.out.println(scn.next()); // 공백을 구분자로 사용, 가져옴

		String[] toks = tok.split(" "); // 공백을 기준으로, 문장을 나눌게,
		for (String str : toks) {
			System.out.println(str);
		}

		// 첫번째 값 => empId, 두번째 값 -> empName, 세번째 -> salary
		// Employee 클래스의 인스턴스를 생성.
		// ArrayList에 저장하기.
		// 종료를 하고 싶으면 quit 종료.
		// 종료 후 for(반복문) 출력.

		ArrayList<Employ> list = new ArrayList<Employ>();
		Employ employ = new Employ();
		while (true) {
			System.out.println("입력 >> ex)100 홍길동 2500");
			System.out.println("종료하고 싶다면 quit을 입력하세요");
			String inputVal = scanner.nextLine();
			String[] input = inputVal.split(" ");


			if (input[0].equals("quit")) {
				System.out.println("프로그램을 종료합니다");
				break;
			}
			if (input.length != 3) {
				System.out.println("다시 입력하세요");
				continue;
			}
			list.add(new Employ(Integer.parseInt(input[0]), input[1], Integer.parseInt(input[2])));
		} // end of while
		for (Employ emp : list) {
			System.out.println(emp);
		}
	}
}
