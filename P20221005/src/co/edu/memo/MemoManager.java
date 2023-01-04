package co.edu.memo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemoManager {
	List<Memo> memoStorage = new ArrayList<>();//정보 담을 컬렉션
	File file = new File("C:/Temp/memobook.dat");
	Scanner scn = new Scanner(System.in);
	
	//싱글톤
	private static MemoManager instance = new MemoManager();
	private MemoManager() {
		readFromFile();
	}
	public static MemoManager getInstance() {
		return instance;
	}
	
	// 추가.
	public void inputData() {
		System.out.print("번호 > ");
		int no = Integer.parseInt(scn.nextLine());
		System.out.print("날짜 > ");
		String date = scn.nextLine();
		System.out.println("내용 > ");
		String content = scn.nextLine();
		
		memoStorage.add(new Memo(no, date, content));
		System.out.println("저장이 완료 되었습니다.");
	}
	
	//조회. 기준 - 날짜
	public void searchData() {
		System.out.println("날짜 >");
		String sDate = scn.nextLine();
		boolean exists = false;
		for(int i =0; i<memoStorage.size(); i++) {
			if(sDate.equals(memoStorage.get(i).getDate())) {
				System.out.println(memoStorage.get(i).toString());
				exists = true;
			}
		}
		if(!exists) {
			System.out.println("해당 날짜의 메모가 없습니다.");
		}
	}//end searchData
	
	//번호를 입력하면 한건 삭제.
	public void deleteData() {
		System.out.println("번호 >> ");
		int sNo = Integer.parseInt(scn.nextLine());
		boolean exists = false;
		for(int i =0; i<memoStorage.size(); i++) {
			if(sNo == memoStorage.get(i).getNo()) {
				memoStorage.remove(i);
				exists = true;
				System.out.println("삭제가 완료 되었습니다.");
			}
		}
		if(!exists) {
			System.out.println("해당 번호의 메모가 없습니다.");
		}
	}
	
	//프로그램 실행 시  파일을 읽어서 데이터 memoStorage 기능.
	public void readFromFile() {
		//try with resource.
		try(FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			) {
			
			memoStorage = (List<Memo>) ois.readObject(); //파일 리턴(바이트 -> 객체)
		} catch(Exception e) {
//			e.printStackTrace(); 
			return; //없으면 어쩔수 없지 모 ㅋㅋ
		}
	}
	
	//종료하면 파일에 저장갈겨.
	public void storeToFile() {
		try(FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos); 
		) {
			oos.writeObject(memoStorage); //파일저장(객체 -> 바이트배열)
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
			
}// end class
