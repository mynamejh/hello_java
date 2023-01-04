package co.edu.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.board.MemberVO;
import co.edu.common.Control;
import co.edu.common.HttpUtil;
import co.edu.service.MemberService;
import co.edu.service.MemberServiceImpl;

public class MemberList implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//회원 전체 조회
		MemberService service = new MemberServiceImpl();
		List<MemberVO> list = service.memberList();
		req.setAttribute("list", list);
		
		
		HttpUtil.forward(req, resp, "member/memberList.tiles");
		
	}

}
