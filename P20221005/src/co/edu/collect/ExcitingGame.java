package co.edu.collect;

import java.util.ArrayList;
import java.util.Scanner;

public class ExcitingGame {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		
		long gameStart = System.currentTimeMillis();
		
		String target = "Sour sweet grapes";
		String[] targetAry = target.split(" ");
		ArrayList list = new ArrayList();
		for (int i = 0; i < targetAry.length; i++) {
			list.add(targetAry[i]);
		}
		System.out.println(target);

		
		while (true) {
			System.out.println("\n치세용 >> ");
			String input = scn.nextLine();
			if (list.size() == 0) {
				break;
			}
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals(input)) {
					list.remove(i);
				}

			}
			for (int i = 0; i < list.size(); i++) {
				System.out.printf("%s ", list.get(i));

			}
		}
		long gameEnd = System.currentTimeMillis();
		
		long during = (gameEnd - gameStart); // ms으로 나옴
		// 1*60*1000 + 30*1000 = 63000/60000 분, 63000$60000 = 초
		long min = during/60000;
		long sec = (during%60000)/1000;
		if(min==0 && sec<5) {
		System.out.println("소요시간 : " + min + "분 " + sec + "초");
		} else {
			System.out.println("패배자");
		}
		

	}
}
