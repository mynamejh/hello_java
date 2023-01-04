package co.edu.collect;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapExample {
	public static void main(String[] args) {
		//key : Integer, val : String
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(100, "신짱구");
		map.put(200, "신짱아");
		map.put(300, "신형만");
		map.put(300, "이형만"); 
		//key값 중복이 올 수 없다.-> 이전의 신형만 데이터가 사라지고 이형만으로 대체됨
		
		map.remove(300); //삭제 
		//300 삭제한다고 null값이 될 뿐, 신형만으로 대체되지 않는다.
		
		System.out.println("컬렌션 : " + map.size());
		
		System.out.println("val : " + map.get(300));
		//get() : key값 넣어주면 해당하는 val 찾아줌.
		
		//반복자 생성
		Set<Integer> keySet = map.keySet(); //키만 새로운 set컬렉션에 저장.
		for(Integer key : keySet) {
			System.out.println("키 : " + key + ", 값 : " + map.get(key));
		}
		System.out.println();
		
		Set<Entry<Integer,String>> entSet = map.entrySet();// key + value = entry
		for(Entry<Integer,String> ent: entSet) {
			System.out.println("키: " + ent.getKey() + ", 값 : " + ent.getValue());
		}
		
		
	}
}
