package co.edu.api;

import java.io.IOException;

public class StringExe2 {
	public static void main(String[] args) {
		System.out.println("abcDEF".toUpperCase());
		System.out.println("abcDEF".toLowerCase());
//		while(true) {
//			System.out.println("알파벳 >> ");
//			try {
//				int bytes = System.in.read();
//				if(bytes == 122)
//					break;
//				
//				System.out.println("입력값 : "+bytes);
//			} catch(IOException e) {
//				e.printStackTrace();
//			}
//		}
//		System.out.println("End of prog");
//	}
}
	public static void changeCapital(String msg) {
		//대문자 => 소문자, 소문자 -> 대문자
		//65~90까지 대문자, 97~122 소문자, 97:65; 소문자-대문자 : 32
		//aBcD = AbCd
		while(true){
		System.out.println("영어를 입력하세요 >> ");
			try {
				int bytes = System.in.read();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
