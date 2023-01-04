package co.edu.nested;

import java.io.IOException;
import java.util.Scanner;

public class EscExample {
	static Scanner scn = new Scanner(System.in);

	public static void main(String[] args) {
		inputValidate("입력하세요");

	}

	public static boolean inputValidate(String prompt) {
		System.out.println(prompt);
		try {
			while (true) {
				int inRead = System.in.read();

				if (inRead == -1) {
					break;
				}
				System.out.println((char) inRead);
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
		return true;
	}
}
