package co.edu.api;

public class MorningExe {
	public static void main(String[] args) {
		
		int num = 1;
		for(int i = 1; i<=4; i++) {
			for(int j =0; j<i; j++) {
				System.out.printf("%3d", num++);
			}
			System.out.println();
		}
		System.out.println();
		
		String blank = " ";
		int num2 = 1;
		for(int i = 1; i<=4; i++) {
			for(int j = 0; j<4-i; j++) {
				System.out.printf("%3s",blank);
			}
			for(int s = 0; s<i; s++) {
				System.out.printf("%3d",num2++);
			}
			System.out.println();
		}
	}//end main;
}//end class
