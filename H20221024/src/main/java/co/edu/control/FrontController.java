package co.edu.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.common.Command;

//모든 호출의 시작과 끝 - 모든 요청을 frontConroller통해서 오게끔, web.xml에서 그걸 설정한다.

public class FrontController extends HttpServlet { 
	
	Map<String, Command> control = new HashMap<>();
	@Override
	public void init() throws ServletException {
		control.put("/main.do", new Maincontrol());
		//정보등록
		control.put("/memberAddForm.do", new MemberAddForm());
		control.put("/memberAdd.do", new MemberAddControl()); //처리
		//수정
		control.put("/memberModifyForm.do", new MemberModifyForm());
		control.put("/memberModify.do", new MemberModify());
		//삭제
		control.put("/memberRemoveForm.do", new MemberRemoveForm());
		control.put("/memberRemove.do", new MemberRemove());
		//한건조회
		control.put("/memberSearchForm.do", new MemberSearchForm());
		control.put("/memberSearch.do", new memberSearch());
		//목록보기
		control.put("/memberList.do", new MemberList());
		//로그인
		control.put("/loginForm.do", new LoginForm());
		control.put("/login.do", new Login());
		control.put("/logOut.do", new logOut());
	}
;	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		String contextPath = req.getContextPath();
		String path = url.substring(contextPath.length());
		
		Command command = control.get(path);
		command.exec(req, resp);
	}
	
	
	
}
