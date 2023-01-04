package co.edu.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.edu.board.MemberVO;
import co.edu.common.Control;
import co.edu.common.HttpUtil;
import co.edu.service.MemberService;
import co.edu.service.MemberServiceImpl;

public class SignIn implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//아이디 패스워드 => 검증 맞으면 session객체에  id값 자저장
		//로그인성공했습니다. signIn.jsp
		String id = req.getParameter("id");
		String passwd = req.getParameter("passwd");
		
		MemberService service = new MemberServiceImpl();
		MemberVO result = service.login(id, passwd);
		
		if(result!= null) {
			//setAttribute - 속성정하기, 속성이름, 속성값
			req.setAttribute("logInfo", result);
			HttpSession session = req.getSession();
			session.setAttribute("id", result.getId());
			
			HttpUtil.forward(req, resp, "member/signIn.tiles");
		}else {
			HttpUtil.forward(req, resp, "bulletin/bulletin.tiles");
		}
		
	}

}
