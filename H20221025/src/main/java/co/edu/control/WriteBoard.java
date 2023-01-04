package co.edu.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import co.edu.board.BoardVO;
import co.edu.common.Control;
import co.edu.common.HttpUtil;
import co.edu.service.BoardService;
import co.edu.service.BoardServiceImpl;

public class WriteBoard implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String saveDir = req.getServletContext().getRealPath("upload");
		System.out.println(saveDir);
		String encod = "UTF-8";
		int maxSize = 5 * 1024 * 1024; //5mb로 제한
		
		MultipartRequest mr = new MultipartRequest(
				req, //요청정보
				saveDir, //저장위치
				maxSize, //파일크기
				encod, //인코딩방식
				new DefaultFileRenamePolicy()//리네임 정책.
				
			);
		
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String writer = mr.getParameter("writer");
		String image = mr.getFilesystemName("image");
		
		//디비 입력.
		BoardService service = new BoardServiceImpl();
		service.insertBoard(new BoardVO(0, title, content, writer, null, 0, image));
		
//		HttpUtil.forward(req, resp, "bulletin/bulletin.tiles");
		resp.sendRedirect("bulletin.do");
		//입력하고 목록페이지로 간다.
	}

}
