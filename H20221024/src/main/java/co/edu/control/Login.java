package co.edu.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.edu.common.Command;
import co.edu.common.HttpUtil;
import co.edu.service.MemberService;
import co.edu.service.MemberServiceImpl;
import co.edu.vo.MemberVO;

public class Login implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String pass = req.getParameter("passwd");
		
		MemberService service = new MemberServiceImpl();
		MemberVO result = service.login(id, pass);
		
		//회원아이디랑 비밀번호 맞으면 => main.jsp 이동
		if(result != null) {
			req.setAttribute("logInfo", result);
		
			HttpSession session = req.getSession();
			session.setAttribute("id", result.getId());
			session.setAttribute("auth", result.getResponsibility()); //admin/user권한 
		
			HttpUtil.forward(req, resp, "memberView/main.jsp");
		//login이 되면 main.jsp로 간다
		} else {
			HttpUtil.forward(req, resp, "memberLog/loginForm.jsp");
		}
	}

}
