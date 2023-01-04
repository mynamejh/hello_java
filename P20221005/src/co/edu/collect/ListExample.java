package co.edu.collect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class ListExample {
	public static void main(String[] args) {
		ArrayList<String>strs = new ArrayList<String>();
		//추가.
		strs.add("폰호두");
		strs.add("박이현");
		strs.add("신짱구");
		
		strs.remove(0);//index 0번 삭제
		strs.remove("신짱구");
		
		strs.add(0,"신짱아"); //0번 자리에 입력, 원래 0번 자리 값은 1번으로 뒤로감.
		
		System.out.println("collect 크기 : " + strs.size());
		
		for(String str : strs) {
			System.out.println(strs);
		}
		
		for(int i =0; i<strs.size();i++) {
			System.out.printf("%d번째의 값은 %s\n", i, strs.get(i));
		}
		//반복자를 생성. 
		//iterator : ArrayList, HashSet과 같은 컬렉션을 반복하는 데 사용할 수 있는 객체
		Iterator<String> itr = strs.iterator();
		while(itr.hasNext()) { //hasNext : 가져 올거 있는지 확인하는거
			String result = itr.next(); //next : 가져오는거.>?ㄴ
			System.out.println(result);
		}
		
		//100만건
		ArrayList<Integer> inAry = new ArrayList<Integer>();
		long start = System.nanoTime(); //
		for(int i =0; i<1000000; i++) {
			inAry.add(i);
		}
		long end = System.nanoTime(); //
		System.out.println("ary: " + (end - start));
		
		LinkedList<Integer> lnAry = new LinkedList<Integer>();
		start = System.nanoTime(); //
		for(int i =0; i<1000000; i++) {
			lnAry.add(i);
		}
		end = System.nanoTime(); //
		System.out.println("lna : " + (end - start));
	}//end main
}//end class
