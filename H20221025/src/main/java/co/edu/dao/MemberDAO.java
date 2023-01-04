package co.edu.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.board.MemberVO;
import co.edu.common.DAO;

public class MemberDAO extends DAO {
	
	public MemberVO insertMember(MemberVO vo) {
		conn = getConnect();
		String sql = "insert into members(id, passwd, name, email, responsibility) values(?, ?, ?, ?, 'user')";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPasswd());
			psmt.setString(3, vo.getName());
			psmt.setString(4, vo.getEmail());
			
			int r = psmt.executeUpdate();
			if(r>0) {
				return vo;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return null;
	}
	
	
//	public List<MemberVO> pageList(){
//		
//		
//	}
	
	
	//회원목록 출력하기 for member/memberList.jsp에서 jstl이용
	public List<MemberVO> memberList(){
		List<MemberVO> list = new ArrayList<>();
		conn = getConnect();
		String sql = "select * from members";
			
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			
			while(rs.next()){
				MemberVO vo = new MemberVO();
				
				vo.setId(rs.getString("id"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				
				list.add(vo);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return list;
	}
	
	
	//로그인
	public MemberVO login(String id, String passwd) {
		conn = getConnect();
		String sql = "select * from members where id=? and passwd =?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, passwd);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setAuth(rs.getString("responsibility"));
				
				return vo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
