package co.edu.service;

import java.util.List;

import co.edu.vo.MemberVO;

//crud 기능.
public interface MemberService {
	public void addMember(MemberVO vo); //추가
	public void modifyMember(MemberVO vo); //수정
	public void removeMember(String id); //삭제
	public MemberVO findMember(String id); //한건 조회
	public List<MemberVO> memberList(); //전체목록
	
	public MemberVO login(String id, String passwd);//로그인처리
}
