package co.edu.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BirthExe {
	public static void main(String[] args) {

		String fileName = "src/co/edu/api/Birth.txt";
		File file = new File(fileName);
		
		Scanner scn = null;
		
		try {
			scn = new Scanner(file);
			while (true) {
				String str = scn.nextLine();
				if (str == null)
					break;
				
				//남자입니다. 여자입니다.
				char sex = str.charAt(7);
				if(str.replace("-", "").charAt(6)%2 == 1) {
					System.out.println("남자입니다");
				}else if(str.replace("-", "").charAt(6)%2 == 0) {
					System.out.println("여자입니다");
				}else {//연 월,일 정보가 잘못되면 잘못된 번호로 인식.
					System.out.println("잘못된 번호 입니다.");
				}
				
//				switch(sex) {
//				case '1' :
//				case '3' :
//					System.out.println("남자네용");
//					break;
//				case '2' :
//				case '4' :
//					System.out.println("여자에요용~~뿌이뿌이");
//					break;
//				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {

		}
		System.out.println("end of prog");
	}
}
