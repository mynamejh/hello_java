package co.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

public class StudentManage {

	File file = new File("c:/Temp/studentList.txt");
	ArrayList<Student> studentList = new ArrayList<>();
	ArrayList<Student> rankList = new ArrayList<>();
	String x = null;

	// 싱글톤 인스턴스 생성.
	private static StudentManage instance = new StudentManage();

	private StudentManage() {
		readFromFile();
	}

	public static StudentManage getInstance() {
		return instance;
	}

	// 등록.
	public void add(Student student) {

		studentList.add(student);
		System.out.println("<<입력되었습니다!>>");

	}

	// 삭제.
	public void del(int studNo) {
		boolean check = false;
		for (int i = 0; i < studentList.size(); i++) {
			if (studentList.get(i).getStudNo() == studNo) {
				studentList.remove(i);
				System.out.println("<<삭제되었습니다.>>");
				check = true;
			}
		}
		if (!check) {
			System.out.println("<<해당정보가 없습니다>>");
		}
	}

	// 목록(입력순서).
	public void list() {

		for (int i = 0; i < studentList.size(); i++) {
			System.out.println(studentList.get(i).toString());
		}
	}

	// 순위(총점순서).
	public void ord() {
		for (int i = 0; i < studentList.size(); i++) {
			int last = rankList.size();
			for (int j = 0; j <= last; j++) {
				// 비교룰 허더거 머자막 순번이 됐을 경우에는 제일 뒤에 추가하기.
				if (j == last) {
					rankList.add(studentList.get(i));
					break;
				}
				// 마지막 순번이 아니면 현재 비교하는 위치에 추가하기.
				if (studentList.get(i).getSumScore() > rankList.get(j).getSumScore()) { // 점수정렬
					if (studentList.get(i).getStudNo() < rankList.get(j).getStudNo()) {
						rankList.add(j, studentList.get(i));
						break;
					}
				}
			}
		} //end of for
		
		int ord = 1;
		for(Student i : rankList) {
			System.out.println(rankList);
		}

//		for(int i=0; i<studentList.size(); i++) {
//			for(int j =0; j<studentList.size()-1; j++) {
//				if(studentList.get(i).getSumScore()<studentList.get(i+1).getSumScore()){
//					rankList.add(i, studentList.get(i));
//					rankList.remove(i+2);
//				}			
//			}
//			System.out.println(rankList.get(i));
//			
//		}

	}

	// 저장된 파일을 읽어오기.
	public void readFromFile() {

		try {
			FileReader fr = new FileReader(file); //
			BufferedReader br = new BufferedReader(fr);//

			String studentInfo = null;
			String[] itemAry = null;

			while (true) {
				studentInfo = br.readLine();
				if (studentInfo == null) { // 파일의 끝부분이면 종료.
					break;
				}
				itemAry = studentInfo.split(" ");
				int no = Integer.parseInt(itemAry[0]);
				String name = itemAry[1];
				int age = Integer.parseInt(itemAry[2]);
				int eng = Integer.parseInt(itemAry[3]);
				int math = Integer.parseInt(itemAry[4]);
				Student student = new Student(no, name, age, eng, math);

				studentList.add(student);

			}
			br.close();
			fr.close();

		} catch (Exception e) {
			System.out.println("읽을 파일이 없습니다.");
		}
	} // 입력스트림

	// 입력된 내용을 파일에 저장하기.
	public void storeToFile() {
		try {
			FileWriter fw = new FileWriter(file);

			for (int i = 0; i < studentList.size(); i++) {
				x = studentList.get(i).toString();

				fw.write(x + "\n");
			}
			System.out.println("<<저장되었습니다!!>>");
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // 출력스트림

}
