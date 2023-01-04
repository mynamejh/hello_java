package co.edu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {
	//모든 servlet관리.
	//url주소와 실행할 서블릿을 map타입으로 관리
	Map<String, Command> map = new HashMap<String, Command>();
	
	@Override
	public void init() throws ServletException {
		map.put("/first.do", new FirstImpl()); //first라는 요청이 들어온다면
		map.put("/second.do", new SecondImpl());
		map.put("/third.do", new ThirdImpl());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI(); //http://localhost:8081/HellowWeb/first.do - url
		//host정보랑 프로젝트정보를 url에서 빼내줌
		String contextPath = req.getContextPath();
		String path = url.substring(contextPath.length());
		
//		System.out.println("url : " + url);
//		System.out.println("contextPath : " + contextPath);
//		System.out.println("path : " + path);
		//요청 url에서 프로젝트의 정보를 반환해줌.
		
		Command command = map.get(path);
		command.exec(req, resp);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}
	
}
