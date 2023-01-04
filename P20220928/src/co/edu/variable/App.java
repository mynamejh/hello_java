package co.edu.variable;

//자바는 class 시작
//클래스의 main 메소드로 시작

public class App {
	public static void main(String[] args) {
		System.out.println("매개값을 출력."); //(매개값을 출력)
		
		//변수의 타입 지정 -> 정수(int, long), 실수(double, float)
		//참-거짓(boolean), 문자열 : String
		
		int myAge = 29; //왼쪽 = 오른쪽의 타입 항상 동일.
		//myAge = "스물다섯"; //컴파일 에러.
		String 내나이 = "스물아홉";
		내나이 = "29"; //문자열 29.
		
		int result = myAge + 30;
		System.out.println(myAge + "값과 30을 더하면 " + result + "입니다.");
		
		//배열
		//let numAry = []; 자바 스크립트 방식
		int[] numAry = {20, 29, 13, 44, 88, 33}; //배열 선언.
		int sum = 0;
//		1) 배열의 합계
		int sum2 = 0;
		for(int i = 0; i < 6; i++) {
			//짝수 조건일 경우에만 합계
			if(numAry[i] % 2 == 0){
			sum = sum + numAry[i];
			}
			if(i % 2 == 0) { // 홀수 자리 인덱스들의 합. (인덱스가 0으로 시작하기 때문) 
			sum2 = sum2 + numAry[i];
			}
		}
		System.out.println("짝수의 합계=> " + sum);
		System.out.println("홀수 인덱스의 합계 " +sum2);

//		2) 배열조건
		sum = 0; //0값으로 초기화. 
//		문제 : sum의 값이 100을 넘어서는 시점 break; 그때의 sum 출력
		for (int i =0; i < 6; i++) {
			sum = sum + numAry[i];
			if(sum > 100) {
				System.out.println("100이 넘어나는 시점의 합 " + sum);
				break;
			}
		}
		System.out.println("정상커밋완료.");
	}
}