package co.edu.memo.except;

//Exception 상속받으면 무조건 try = catch해조야함
public class MenuException extends Exception {
	int choice;
	
	public MenuException(int choice) {
		this.choice =choice;
	}
	
	public void showErrMessage() {
		System.out.println(choice + "는 없는 메뉴입니다.");
	}
}
