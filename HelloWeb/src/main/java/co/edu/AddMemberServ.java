package co.edu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.emp.EmpDAO;
import co.edu.emp.EmployeeVO;

@WebServlet({ "/addMemberServlet", "/addMember" }) //
public class AddMemberServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddMemberServ() {
		super();
	}
	
	//doget = 겟방식의 요청이 들어오면 get이라는 메소드 호출.
	//doget - 사용자의 요청정보를 처리해주는,,,
	//request - 파마메터를 읽어들이는 기능가짐
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//content 타입 지정.
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("/addMemberServer 페이지입니다!");
		String empId = request.getParameter("employee_id");
		String lName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String hDate = request.getParameter("hire_date");
		String jobId = request.getParameter("job_id");
		
		System.out.println(empId);
		
		//입력
		EmpDAO dao = new EmpDAO();
		EmployeeVO vo = new EmployeeVO(0, lName, email, hDate, jobId);
		dao.insertEmp(vo);
		
		System.out.println("입력완료");
		 
		
		PrintWriter out = response.getWriter();//출력스트림
		out.print("<h3>Completed</h3>");
		out.print("<p>입력결과 : </p>");
		out.print("<p>사원번호 : " + vo.getEmployeeId() + "</p>");
		out.print("<p>성씨 : " + vo.getLastName() + "</p>");
		out.print("<p>이메일 : " + vo.getEmail() + "</p>");
		out.print("<p>입사일자 : "+ vo.getHireDate() + "</p>");
		out.print("<p>직무 : " + jobId + "</p>");
		out.print("<p>입니다.</p>");
		out.print("<a href = index.html>첫페이지로 이동하기</a>");
		
		
	}
	
	//post 요청으로 방식이 들어오면 doPost 실행
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
//		doGet(request, response);
		//출력스트림
		PrintWriter out = response.getWriter(); //사용자의 브라우저(출력스트림생성)
		out.print("<h3>Post 방식의 요청</h3>");
		
		}

}
