package co.edu.project;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class MyBoardDAO extends DAO {
//	ArrayList<Board> brdList = new ArrayList<Board>();
//	ArrayList<Reply> rpList = new ArrayList<Reply>();
	MyBoard needBrd = null;
	MyReply needRp = null;
	MailApp app = new MailApp();
	
	// 로그인
	public boolean login(String id, String pw) {
		String sql = "select * from theUsers where id = ?";
		conn = getConnect();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			while (rs.next()) {
				if (pw.equals(rs.getString("passwd"))) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;

	}// end login
	
	//회원 가입 신청
	public void applyUser(MyUser newuser) {
		conn = getConnect();
		String sql = "insert into newUsers(id, passwd, user_name, email)\r\n"
				+ " values(?, ?, ?, ?)";
				
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, newuser.getNewId());
			psmt.setString(2, newuser.getNewPw());
			psmt.setString(3, newuser.getNewName());
			psmt.setString(4, newuser.getEmail());
			
			psmt.executeUpdate();
			
			System.out.println("신청 완료 되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			disconnect();
		}
		
	}
	
	//아이디 중복 확인
	public boolean ckOverlap(String id){
		String sql = "select * from theUsers where id = ?";
		conn = getConnect();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			if(rs.next()) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}return false;

	}
	
	
	
	// 글 등록
	public void inputBrd(MyBoard brd) {
		String sql = "insert into theBoard(brd_seq, board_title, board_content, board_writer, creation_date)\r\n"
				+ "values(brd_seq.nextval, ?, ?, ?, sysdate)";

		conn = getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, brd.getbTitle());
			psmt.setString(2, brd.getbContent());
			psmt.setString(3, brd.getbWriter());

			psmt.executeUpdate();

			System.out.println("업로드가 완료되었습니다");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}// end inputBrd

	// 본인 글 찾기 - id로 특정글 찾기
	public List<MyBoard> getBrd(String logId) {
		ArrayList<MyBoard> brdList = new ArrayList<MyBoard>();
		String sql = "select * from theBoard where board_writer = ? order by brd_seq";
		conn = getConnect();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, logId);
			rs = psmt.executeQuery();

			while (rs.next()) {
				brdList.add(new MyBoard(rs.getInt("brd_seq"), rs.getString("board_title"),
						rs.getString("board_content"), null, rs.getString("creation_date")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return brdList;

	}

	// 본인 id로 특정 글에 대한 댓글 찾기
	public List<MyReply> getRp(String logId) {
		ArrayList<MyReply> rpList = new ArrayList<MyReply>();
		String sql = "select * from theReply where rep_writer = ? order by re_seq";
		conn = getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, logId);
			rs = psmt.executeQuery();

			while (rs.next()) {
				rpList.add(new MyReply(rs.getInt("re_seq"), rs.getInt("board_num"), rs.getString("rep_content"),
						rs.getString("rep_writer"), rs.getString("creation_date")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return rpList;

	}

	// 글 수정
	public void updateBrd(MyBoard brd) {
		String sql = "update theBoard " + "set board_content = ? ," + "creation_date = sysdate "
				+ "where brd_seq = ?";

		conn = getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, brd.getbContent());
			psmt.setInt(2, brd.getbNum());

			psmt.executeUpdate();
			System.out.println("수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	// 글 삭제
	public void delBrd(int bNum) {
		String sql = "delete from theBoard where brd_seq = ?";
		conn = getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bNum);

			psmt.executeUpdate();
			System.out.println("삭제 되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	// 댓글 수정
	public void updateRp(MyReply rp) {
		String sql = "update theReply " + "set rep_content =? , creation_date = sysdate where re_seq =?";
		conn = getConnect();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, rp.getReContent());
			psmt.setInt(2, rp.getReNum());

			psmt.executeUpdate();
			System.out.println("수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 댓글 삭제
	public void delRp(int reNum) {
		String sql = "delete from theReply where re_seq = ?";
		conn = getConnect();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, reNum);

			psmt.executeUpdate();
			System.out.println("삭제 되었습니다.");
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// 전체글보기
	public List<MyBoard> viewBrd() {
		ArrayList<MyBoard> brdList = new ArrayList<MyBoard>();
		String sql = "select * from theBoard order by brd_seq";
		conn = getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				brdList.add(new MyBoard(rs.getInt("brd_seq"), rs.getString("board_title"), rs.getString("board_writer"),
						rs.getString("creation_date")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return brdList;
	}

	// 특정 글 보기
	public MyBoard searchBrd(int bNum) {
		String sql = "select * from theBoard where brd_seq = ?";
		conn = getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bNum);
			rs = psmt.executeQuery();

			if (rs.next()) {
				needBrd = new MyBoard(rs.getInt("brd_seq") //
						, rs.getString("board_title")//
						, rs.getString("board_content")//
						, rs.getString("board_writer")//
						, rs.getString("creation_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return needBrd;

	}

	// 특정 글에 대한 댓글보기
	public List<MyReply> searchRp(int bNum) {
		ArrayList<MyReply> rpList = new ArrayList<MyReply>();
		String sql = "select * from theReply where board_num = ? order by re_seq";
		conn = getConnect();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bNum);
			rs = psmt.executeQuery();

			while (rs.next()) {
				rpList.add(new MyReply(rs.getInt("re_seq"), rs.getInt("board_num"), rs.getString("rep_content"),
						rs.getString("rep_writer"), rs.getString("creation_date")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rpList;

	}

	// 댓글쓰기
	public void inputRp(MyReply rp) {
		String sql = "insert into theReply(re_seq, board_num, rep_content, rep_writer, creation_date)"
				+ "values(re_seq.nextval, ?, ?, ?, sysdate)";
		conn = getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, rp.getbNum());
			psmt.setString(2, rp.getReContent());
			psmt.setString(3, rp.getReWriter());

			psmt.executeUpdate();
			System.out.println("댓글이 등록되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	// 권한 확인
	public boolean ckManager(String id) {
		String sql = "select * from themanager where id = ?";
		conn = getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			if (rs.next()) {
				if (id.equals(rs.getString("id"))) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;

	}
	
	//신청 목록 보여주기.
		public List<MyUser> viewApply() {
			ArrayList<MyUser> applyList = new ArrayList<MyUser>();
			String sql = "select * from newUsers";
			conn = getConnect();
			try {
				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();
				while (rs.next()) {
					applyList.add(new MyUser(rs.getString("id"), rs.getString("passwd"), rs.getString("user_name")));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			return applyList;
		}

	// 신청 승인
	public void inputUser(String id) {
		String apply = "select passwd, user_name, email from newUsers where id = ?";
		String sql = "insert into theUsers(id, passwd, user_name) values(?, ?, ?)";
		conn = getConnect();
		try {
			psmt = conn.prepareStatement(apply);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, id);
				psmt.setString(2, rs.getString(1));
				psmt.setString(3, rs.getString(2));
			}
			
			String address = rs.getString(3);
			psmt.executeUpdate();
			
			
			app.sendMail("aeegina@naver.com", address , "떡잎마을 게시판 가입 승인되었습니다", "환영합니다. <br> 떡잎마을 게시판 가입 승인되었습니다.");
			System.out.println("주민 등록 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}
	
	//승인 받았다면 = 신청 테이블에서 신청인 지우기
	public void delApply(String id) {
		String sql = "delete from newUsers where id = ?";
		conn = getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// 회원 탈퇴
	public void delUser(String id, String pw) {
		String sql = "delete from theUsers where passwd = ? and id = ? ";
		conn = getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, pw);
			psmt.setString(2, id);

			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// 기준 - 아이디, 글삭
	public void byeBrd(String id) {
		String sql = "delete from theBoard where board_writer = ?";
		conn = getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// 기준 - 아이디, 댓삭
	public void byeRp(String id) {
		String sql = "delete from theReply where rep_writer = ?";
		conn = getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	//인기글
	public List<MyReply> rankBrd() {
		conn= getConnect();
		List<MyReply> list = new ArrayList<>();
		String sql = "select b.board_num, b.cnt, t.board_title from (select board_num, count(*) cnt from theReply group by board_num) b , theBoard t where b.board_num = t.brd_seq order by b.cnt desc"; 
		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				list.add(new MyReply(rs.getInt("board_num"), rs.getString("board_title"), rs.getInt("cnt")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return list;
	}

}
