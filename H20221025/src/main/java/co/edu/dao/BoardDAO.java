package co.edu.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.board.BoardVO;
import co.edu.common.DAO;

public class BoardDAO extends DAO {
	//입력, 조회, 수정, 삭제..
	
	public BoardVO insertBoard(BoardVO vo) {
		//입력처리 중 에러발생 -> null;
		conn=getConnect();
		String sql = "select board_seq.nextval from dual";
		String sql2 = "insert into tbl_board(board_no, title, content, writer, image) "
				+ "values(?, ?, ?, ?, ?)";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			int nextSeq = 0;
			if(rs.next()) {
				nextSeq = rs.getInt(1);
			}
			
			//insert
			
			psmt = conn.prepareStatement(sql2);
			psmt.setInt(1, nextSeq);
			psmt.setString(2, vo.getTitle());
			psmt.setString(3, vo.getContent());
			psmt.setString(4, vo.getWriter());
			psmt.setString(5, vo.getImage());
			
			int r = psmt.executeUpdate();
			if(r>0) {
				vo.setBoardNo(nextSeq);
				return vo;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return null; //실패할 경우에 null값 반환
	}
	
	public BoardVO searchBoard(int boardNo) {
		BoardVO vo = new BoardVO();
		conn = getConnect();
		String sql = "select * from tbl_board where board_no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setWriteDate(rs.getString("write_date"));
				vo.setClickCnt(rs.getInt("click_count"));
				vo.setImage(rs.getString("image"));
				
			}
			
//			return vo;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return vo;
	}
	
	public List<BoardVO> BoardList(BoardVO vo){
		List<BoardVO> list = new ArrayList<>();
		conn = getConnect();
		String sql = "select * from tbl_board "
		+ " where 1 = 1 "
		+ " and title like '%'||?||'%' "//
		+ " and content like '%'||?||'%' " //
		+ " and writer like '%'||?||'%' ";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			psmt.setString(3, vo.getWriter());
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoardNo(rs.getInt("board_no"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWriter(rs.getString("writer"));
				board.setWriteDate(rs.getString("write_date"));
				board.setClickCnt(rs.getInt("click_count"));
				board.setImage(rs.getString("image"));
				
				list.add(board);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return list;
	}
	
	public boolean updateBoard(BoardVO vo) {
		//처리건수가 0이면 false;
		conn = getConnect();
		String sql = "update tbl_board set content = ? where board_no = ?";
		
		try {
			conn.prepareStatement(sql);
			psmt.setString(1, vo.getContent());
			psmt.setInt(2, vo.getBoardNo());
			
			int r = psmt.executeUpdate();
			
			if(r != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return false;
	}
	
	public boolean deleteBoard(int boardNo) {
		//처리건수가 0이면 false;
		conn = getConnect();
		String sql = "delete * from tbl_board where board_no = ?";
		
		try {
			conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			
			int r = psmt.executeUpdate();
			
			if(r != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return false;
		
	}
	
	//페이징.전체건수 10개씩,,
	public int totalCnt() {
		conn = getConnect();
		String sql = "select count(1) from tbl_board";
		try {
			psmt =conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(rs.next()) {
				int cnt = rs.getInt(1);//1번째 칼럼이란뜻
				return cnt;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return 0;
	}
	
	public List<BoardVO> pageList(int page){
		getConnect();
		List<BoardVO> list = new ArrayList<>();
		String sql = "select b.* "
				+ "from ( select rownum rn, a.* "
				+ "        from (select * "
				+ "                from tbl_board "
				+ "                order by board_no desc) a "
				+ "        where rownum <= ? ) b "
				+ "where b.rn >= ? ";
		
		int from = (page -1) * 10; //1p -> 1
		int to = (page * 10); //1p -> 10
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, to);
			psmt.setInt(2, from);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoardNo(rs.getInt("board_no"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWriter(rs.getString("writer"));
				board.setWriteDate(rs.getString("write_date"));
				board.setClickCnt(rs.getInt("click_count"));
				board.setImage(rs.getString("image"));
				
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return list;
	}
}
