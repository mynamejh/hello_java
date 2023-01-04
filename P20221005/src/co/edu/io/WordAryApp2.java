package co.edu.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class WordAryApp2 {
	public static void main(String[] args) throws Exception {
		// 임의 문장을 10개씩 출력하고 타이핑해서 맞으면 사라지고 ..
		ArrayList<String> words = randomWords(10);
		System.out.println(words.size());
		for (String word : words) {
			System.out.println(word);
		}
		// 하나씩 찾아서 지우개ㅣ.
		Scanner scn = new Scanner(System.in);
		while (true) {
			if (words.size() == 0) {
				System.out.println("끝");
				break;
			}
			for (int i = 0; i < words.size(); i++) {
				System.out.printf("%s \n", words.get(i));
				System.out.print("\n>>");
				String input = scn.nextLine();
				if (words.get(i).equals(input)) {
					words.remove(i);
				}
			}

		}

	}

	public static ArrayList<String> randomWords(int times) throws Exception {
		// ArrayList<String>에 전체 문장을 담고,
		ArrayList<String> words = new ArrayList<String>();
		String path = "src/co/edu/io/wordAry.txt";
		File file = new File(path); // 경로의 파일을..만든다
		Scanner scn = new Scanner(file);
		while (scn.hasNext()) {
			String readStr = scn.nextLine();
			readStr = readStr.replaceAll("\"", "").trim();
//			System.out.println(readStr);
			words.add(readStr.substring(0, readStr.indexOf(","))); // indexof(",") - 쉼표가 들어간 인덱스 값 찾아줌
		}
		// 임의의 갯수 times값만큼 반환하도록.
		ArrayList<String> randomWords = new ArrayList<String>();
		while (randomWords.size() < times) {
			randomWords.add(words.get((int) (Math.random() * 494)));
		}

		scn.close();
		return randomWords;

	}
}