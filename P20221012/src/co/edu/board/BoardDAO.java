package co.edu.board;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.jdbc.Employee;
import oracle.net.aso.r;

//Create Read Update Delete
public class BoardDAO extends DAO {
	
	public boolean login(String id, String passwd) {
		String sql = "select * from users where id = ?";
		conn = getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();			
			
			while(rs.next())
				{if(passwd.equals(rs.getString("passwd"))) {
					
				return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}return false;
		
	}
	
	public void insert(Board brd) {
		String sql = "insert into board(board_num, board_title, board_content, board_writer)\r\n"
				+ "values( ?, ?, ?, ?)";
				
		conn = getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1,brd.getbNum());
			psmt.setString(2,brd.getbTitle());
			psmt.setString(3,brd.getbContent());
			psmt.setString(4,brd.getbWriter());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력되었습니다");
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}// end insert;
	
	public void update(Board brd) {
		String sql = "update board "
				+"set board_content = ? ,"
				+ "creation_date = sysdate, "
				+ "where board_num = ?";
		
		conn = getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, brd.getbContent());
			psmt.setInt(2, brd.getbNum());
			
			int r = psmt.executeUpdate(); //실제 업데이트하능거
			System.out.println(r + "건 수정되었습니다");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}// end update
	
	public boolean delete(int bNum) {
		String sql = "delete from board where board_num = ?";
		
		
		conn = getConnect();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bNum);
			
			int r = psmt.executeUpdate();
			if(r>0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}// end delete
	
	public List<Board> search(){
		conn = getConnect();
		String sql = "select * from board order by board_num";
		List<Board> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				list.add(new Board(rs.getInt("board_num")
						, rs.getString("board_title")
						, rs.getString("board_content")
						, rs.getString("board_writer")
						, rs.getString("creation_date")
						, rs.getInt("cnt")));
			}
		}catch(Exception e){
			System.out.println("로그인 실패");
		}finally {
			disconnect();
		}
		return list;
	}//end search
	
	public Board getBrd(int bNum) {
		conn = getConnect();
		Board findbrd = null;
		String sql = "select * from board where board_num = ?";
		String cnt = "update board set cnt = cnt+1 where board_num = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bNum);
			rs =psmt.executeQuery();
			
			psmt = conn.prepareStatement(cnt);
			psmt.setInt(1, bNum);
			int r = psmt.executeUpdate(); //실제 업데이트하능거
//			System.out.println("글" + bNum +"의 조회수가 " + r + "건 올랐습니다");
			
			if (rs.next()) {
				findbrd = new Board(rs.getInt("board_num")//
						, rs.getString("board_title")//
						, rs.getString("board_content")//
						, rs.getString("board_writer")//
						, rs.getString("creation_date")//
						, rs.getInt("cnt"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return findbrd;
	}//end getbrd
	
	//댓글 관련
	//댓글 추가
	public void addReply(Reply rp) {
		String sql = "insert into reply(rep_seq, board_num, rep_content, rep_writer, creation_date)\r\n"
				+ "values(reply_seq.nextval, ? , ? , ? ,sysdate)";
				
		conn = getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, rp.getbNum());
			psmt.setString(2, rp.getReContent());
			psmt.setString(3, rp.getReWriter());
			
			psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}//end addReply
	
	//댓글 조회
	public List<Reply> getReply(int bNum) {
		conn = getConnect();
		List <Reply> list = new ArrayList<>();
		String sql = "select * from reply where board_num  = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bNum);
			rs =psmt.executeQuery();
			
			while(rs.next()) {
				list.add(new Reply(rs.getInt("rep_seq")//
							,rs.getInt("board_num")//
							,rs.getString("rep_content")//
							,rs.getString("rep_writer")//
							,rs.getString("creation_date")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return list;
				
	}//end getReply
	
	public void rankBrd() {
		conn= getConnect();
		List <Reply> list = new ArrayList<>();
		String sql = "select board_num, count(*) \r\n"
				+ "from theReply\r\n"
				+ "group by board_num\r\n"
				+ "order by count(*) desc";
		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
