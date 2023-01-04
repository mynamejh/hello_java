package co.edu.service;

import java.util.List;

import co.edu.dao.MemberDAO;
import co.edu.vo.MemberVO;

//서비스 로직 = 실제 데이터베이스 등록/수정/삭제/조회 여기서 조합해서 사용함
// MemberDAO에서 구현해놓은 기능을 조합해서 사용.
//인터페이스 구현 객체
public class MemberServiceImpl implements MemberService{
	
	MemberDAO dao = new MemberDAO();
	
	@Override
	public void addMember(MemberVO vo) {
		dao.memberInsert(vo);
	}

	@Override
	public void modifyMember(MemberVO vo) {
		dao.memberUpdate(vo);
	}

	@Override
	public void removeMember(String id) {
		dao.memberdelete(id);
	}

	@Override
	public List<MemberVO> memberList() {
		return dao.memberList();
	}

	@Override
	public MemberVO findMember(String id) {
		return dao.memberSearch(id);
	}

	@Override
	public MemberVO login(String id, String passwd) {
		return dao.login(id, passwd);
		
	}

}
