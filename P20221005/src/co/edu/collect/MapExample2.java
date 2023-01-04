package co.edu.collect;

import java.util.HashMap;
import java.util.Map;

class Book{
	String bookCode;
	String author;
	String publish;
	
	public Book(String bookCode, String author, String publish) {
		this.bookCode = bookCode;
		this.author = author;
		this.publish = publish;
	}//생성자 함수
	
	//hashCode, equals 메소드 동일한 값이면 어쩌구...
	@Override
	public int hashCode() {
		return bookCode.hashCode(); //똑같은 문자면 똑같은 값을 리턴해준다.
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Book) {
			Book target = (Book) obj;
			return this.bookCode.equals(target.bookCode);
		}else {
			return false;
		}
	}
}
public class MapExample2 {
	public static void main(String[] args) {
		Map<Book, Integer> map = new HashMap<>();
		map.put(new Book("123456", "폰호두", "멍멍출판사"),2000);//키값, value
		map.put(new Book("bagopa", "박이현", "돼지출판사"),3000);
		map.put(new Book("javage", "남궁성", "자바출판사"),2000);
		
		Integer val = map.get(new Book("123456", "폰호두", "멍멍출판사"));
		System.out.println("값 : " + val);
	}
}
