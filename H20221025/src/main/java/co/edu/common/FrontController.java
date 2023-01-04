package co.edu.common;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.control.BulletinControl;
import co.edu.control.MainControl;
import co.edu.control.MemberList;
import co.edu.control.SearchBoard;
import co.edu.control.SignIn;
import co.edu.control.SignInForm;
import co.edu.control.SignOut;
import co.edu.control.SignUp;
import co.edu.control.SignUpForm;
import co.edu.control.WriteBoard;
import co.edu.control.WriteForm;


//HttpServlet을 상속받는 서블릿임!
public class FrontController extends HttpServlet {

	HashMap<String, Control> controlList ;
	String charset;
	
	//init() - 서블릿 실행되면 처음만 작동
//	@Override
//	public void init() throws ServletException {
//		ServletContext sc = this.getServletContext();
//		
//	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		charset = config.getInitParameter("charset");
		controlList = new HashMap<String, Control>();
		
		controlList.put("/main.do", new MainControl()); //main.do라는 요청이들어오면 Maincontrol 클래스로..고고!
		controlList.put("/bulletin.do", new BulletinControl());
		controlList.put("/searchBoard.do", new SearchBoard());
		controlList.put("/writeBoardForm.do", new WriteForm());
		controlList.put("/writeBoard.do", new WriteBoard());
		//회원가입
		controlList.put("/signUpForm.do", new SignUpForm()); //회원가입화면
		controlList.put("/signUp.do", new SignUp());//회원가입 처리 => 회원가입되었습니당
		controlList.put("/memberList.do", new MemberList());
		//로그인
		controlList.put("/signInForm.do", new SignInForm());
		controlList.put("/signIn.do", new SignIn());
		controlList.put("/signOut.do", new SignOut());
	}
	
	//service() - 실행될때마다 작동
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding(charset); //한글처리
		resp.setCharacterEncoding(charset);
		
		String url = req.getRequestURI(); 
		//url - http://localhost:8081/H20221025/main.do에서 H20221025/main.do만 가져온다 
		String context = req.getContextPath();
		//url에서 /H20221025 해당
		String path = url.substring(context.length());
		// url에서 main.do가져오는거 -> /H20221025 이후. context끝까지 자르겠다.
		
		System.out.println(path);
		Control subControl = controlList.get(path);
		subControl.exec(req, resp); 
		//main.do를 호출해 여기에 매핑되어잇는 control실행하겠다.
	}
	
}
