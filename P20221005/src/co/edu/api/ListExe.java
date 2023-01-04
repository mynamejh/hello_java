package co.edu.api;

import java.util.ArrayList;

public class ListExe {
	public static void main(String[] args) {
		//컬렉션
		int[] intAry = new int[5];
		ArrayList<Integer> list = new ArrayList<Integer>();
//		list.add(new Integer(100));
		list.add(100);
		
		int a =5;
		Integer b = 10; //int => Integer.
		//박싱 해주면 여러 메소드를 사용할 수 있게 된다.
		a = b; //integer -> int : unboxing
		
	}
}
