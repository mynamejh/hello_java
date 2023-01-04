package co.test;

import java.util.Scanner;

public class StudentApp {
	public static void main(String[] args) {

		StudentManage app = StudentManage.getInstance();
		Scanner scn = new Scanner(System.in);

		while (true) {
			System.out.println("1.등록 2.삭제 3.목록(총점) 4.순위 9.종료");
			int menu = scn.nextInt();
			scn.nextLine();
			if (menu == 1) { // 등록.
				System.out.println("1. (등록일 경우)");
				System.out.println("-----------------------");
				System.out.println("학생번호>> ");
				int studNo = Integer.parseInt(scn.nextLine());
				System.out.println("학생이름>> ");
				String studName = scn.nextLine();
				System.out.println("학생나이>> ");
				int studAge = Integer.parseInt(scn.nextLine());
				System.out.println("영어점수>>");
				int engScore = Integer.parseInt(scn.nextLine());
				System.out.println("수학점수>>");
				int mathScore = Integer.parseInt(scn.nextLine());
				
				Student student = new Student(studNo, studName, studAge, engScore, mathScore);
				
				app.add(student);
				
			} else if (menu == 2) { // 삭제.
				System.out.println("2. 삭제할 경우");
				System.out.println("삭제할 학생번호>> ");
				int studNo = Integer.parseInt(scn.nextLine());
				
				app.del(studNo);
				
			} else if (menu == 3) { // 목록.
				System.out.println("3. 목록일 경우");
				System.out.println("학번 이름 나이 영어 국어 총점");
				
				app.list();
				
			} else if (menu == 4) { // 순위.
				System.out.println("4. 순위일 경우");
				
				app.ord();
			} else if (menu == 9) { // 종료.
				System.out.println("종료일 경우");
				app.storeToFile();
				break;
			}

		}

		scn.close();
		System.out.println("프로그램 종료.");
	}
}
