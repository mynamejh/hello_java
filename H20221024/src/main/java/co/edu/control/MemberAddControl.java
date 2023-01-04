package co.edu.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.common.Command;
import co.edu.common.HttpUtil;
import co.edu.service.MemberService;
import co.edu.service.MemberServiceImpl;
import co.edu.service.MemberServiceMysql;
import co.edu.vo.MemberVO;

public class MemberAddControl implements Command {
//사용자의 요청이 들어오면 데이터 베이스에 회원정보 하나 추가하는 기능 
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//parameter 값에 따라서 ..
		//MemberServiceImpl , MemberServiceMysql 둘다 Memberservice를 구현객체임
		//호출하는 객체만 바꿔주면 crud 기능을 따로 수정할필요가 없다.
		String id = req.getParameter("id"); //id라는 파라메터의 값 가져와서 id에 담겠다
		String pw = req.getParameter("passwd");
		String nm = req.getParameter("name");
		String ml = req.getParameter("mail");
		
		MemberVO vo = new MemberVO(id, pw, nm, ml);
		
		//db입력처리
		MemberService service = new MemberServiceImpl();
		service.addMember(vo);
		
		//처리된 결과를 페이지.
		HttpUtil.forward(req, resp, "memberResult/memberInsertOutput.jsp");
		//memberResult/memberInsertOutput.jsp를 재호출할게
	}

}
