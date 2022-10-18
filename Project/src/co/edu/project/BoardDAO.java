package co.edu.project;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO extends DAO {
//	ArrayList<Board> brdList = new ArrayList<Board>();
//	ArrayList<Reply> rpList = new ArrayList<Reply>();
	Board needBrd = null;
	Reply needRp = null;

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

	// 글 등록
	public void inputBrd(Board brd) {
		String sql = "insert into theBoard(board_num, board_title, board_content, board_writer, creation_date)\r\n"
				+ "values( ?, ?, ?, ?, sysdate)";

		conn = getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, brd.getbNum());
			psmt.setString(2, brd.getbTitle());
			psmt.setString(3, brd.getbContent());
			psmt.setString(4, brd.getbWriter());

			psmt.executeUpdate();

			System.out.println("업로드가 완료되었습니다");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}// end inputBrd

	// 본인 글 찾기 - id로 특정글 찾기
	public List<Board> getBrd(String logId) {
		ArrayList<Board> brdList = new ArrayList<Board>();
		String sql = "select * from theBoard where board_writer = ? order by board_num";
		conn = getConnect();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, logId);
			rs = psmt.executeQuery();

			while (rs.next()) {
				brdList.add(new Board(rs.getInt("board_num"), rs.getString("board_title"),
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
	public List<Reply> getRp(String logId) {
		ArrayList<Reply> rpList = new ArrayList<Reply>();
		String sql = "select * from theReply where rep_writer = ? order by re_seq";
		conn = getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, logId);
			rs = psmt.executeQuery();

			while (rs.next()) {
				rpList.add(new Reply(rs.getInt("re_seq"), rs.getInt("board_num"), rs.getString("rep_content"),
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
	public void updateBrd(Board brd) {
		String sql = "update theBoard " + "set board_content = ? ," + "creation_date = sysdate "
				+ "where board_num = ?";

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
		String sql = "delete from theBoard where board_num = ?";
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
	public void updateRp(Reply rp) {
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
	public List<Board> viewBrd() {
		ArrayList<Board> brdList = new ArrayList<Board>();
		String sql = "select * from theBoard ";
		conn = getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				brdList.add(new Board(rs.getInt("board_num"), rs.getString("board_title"), rs.getString("board_writer"),
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
	public Board searchBrd(int bNum) {
		String sql = "select * from theBoard where board_num = ?";
		conn = getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bNum);
			rs = psmt.executeQuery();

			if (rs.next()) {
				needBrd = new Board(rs.getInt("board_num") //
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
	public List<Reply> searchRp(int bNum) {
		ArrayList<Reply> rpList = new ArrayList<Reply>();
		String sql = "select * from theReply where board_num = ? order by re_seq";
		conn = getConnect();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bNum);
			rs = psmt.executeQuery();

			while (rs.next()) {
				rpList.add(new Reply(rs.getInt("re_seq"), rs.getInt("board_num"), rs.getString("rep_content"),
						rs.getString("rep_writer"), rs.getString("creation_date")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rpList;

	}

	// 댓글쓰기
	public void inputRp(Reply rp) {
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

	// 주민등록
	public void inputUser(String userId, String userPw, String userName) {
		String sql = "insert into theUsers(id, passwd, user_name) values(?, ?, ?)";
		conn = getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			psmt.setString(2, userPw);
			psmt.setString(3, userName);

			psmt.executeUpdate();
			System.out.println("주민 등록 완료");
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

}
