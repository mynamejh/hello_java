package co.edu.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class EmpObjectExam {
	public static void main(String[] args) throws Exception {
		
		//C://Temp/emp.dat 읽어들이는 기능 만들기
		FileInputStream fis = new FileInputStream("C:/Temp/emp.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		ArrayList<Emp> empList = (ArrayList<Emp>) ois.readObject(); //object타입으로 읽어주삼 -> Arraylist 로 캐스팅
		Scanner scn = new Scanner(System.in);
		while(true) {
			System.out.println("1. 사원등록 2. 목록출력 3. 삭제 4. 종료");
			System.out.println("메뉴 선택 >>");
			int menu = scn.nextInt();
			scn.nextLine();
			String[] empAry; //선생님이 하실 때 
			if(menu == 1) {
//				System.out.println("사원id >> ");
//				int id = scn.nextInt();
//				scn.nextLine();
//				System.out.println("사원 이름 >> ");
//				String name = scn.nextLine();
//				System.out.println("부서 이름 >> ");
//				String dept = scn.nextLine();
//				
//				Emp employee = new Emp(id, name, dept);
//				empList.add(employee);
				
//쌤이 한거				
				System.out.println(">>");
				String empVal = scn.nextLine();
				//split을 이용할경우
				empAry = empVal.split(" ");
				Emp emp = new Emp(Integer.parseInt(empAry[0]), empAry[1], empAry[2]);
				empList.add(emp);
				System.out.println("입력 완료");
				
			} else if(menu ==2) {
				for(Emp emp:empList) {
					System.out.println(emp.toString());
				}
			} else if(menu == 3) {
				//사원번호를 기준으로 삭제
				System.out.println("사원 번호를 입력 하세요 >>");
				int empId = scn.nextInt();
				scn.nextLine();
				for(int i = 0; i<empList.size(); i++) {
					if(empId == empList.get(i).id)
					empList.remove(i);
				}
				System.out.println("삭제되었습니다.");
				
			} else if (menu ==4) {
				//컬렉션에 있던 ObjectOutStream을 활용해서 C:/Temp/emp.dat 저장.
				try(FileOutputStream fos = new FileOutputStream("C:/Temp/emp.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);){
					oos.writeObject(empList);
				} catch(Exception e) {
					e.printStackTrace();
				}
				System.out.println("프로그램을 종료합니다.");
				break;
			} else {
				System.out.println("잘못된 메뉴를 선택했습니다.");
			}
		}
	}//end main.
}