package co.edu.board;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Morning20221014 {
	public static void main(String[] args) {
		File file = new File("C:/Temp/memberList.txt");
		List <Member> list = new ArrayList<>();
		String x = null;
		Scanner scn = new Scanner(System.in);
			
	while(true) {
		System.out.println("회원 목록 입력 해버령 - 종료하고 싶으면 quit 입력해");
		System.out.println("회원아이디 >> ");
		String uId = scn.nextLine();
		
		if(uId.equals("quit")) {
			System.out.println("입력 종료");
			break;
		}
		
		System.out.println("회원이름 >> ");
		String uName = scn.nextLine();
		System.out.println("회원포인트 >> ");
		int uPoint = Integer.parseInt(scn.nextLine());
		
		list.add(new Member(uId, uName, uPoint));
		
		
	}
	
		try(FileWriter fw = new FileWriter(file);
			){
			for(int i =0; i<list.size(); i++) {
				x = list.get(i).getuId() + " " + list.get(i).getuName()
						+ " " + list.get(i).getuPoint();
				
				fw.write(x + " ");
			}
			System.out.println("저장 성공");
		}catch(Exception e) {
			e.printStackTrace();
		}scn.close();
		
		
	}
}
