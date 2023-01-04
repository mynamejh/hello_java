package co.edu.board;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

//main - 등록하는 메세지 ,메뉴 출력 등등 
public class BoardApp {
	public static void main(String[] args) {
		BoardDAO dao = new BoardDAO();
		Scanner scn = new Scanner(System.in);
		Board brd = new Board();
		Reply reply = new Reply();
		String logid = null;

		while (true) {
			System.out.println("로그인 페이지");
			System.out.println("아이디 >>");
			String id = scn.nextLine();
			System.out.println("비밀번호 >> ");
			String passwd = scn.nextLine();

			if (dao.login(id, passwd)) {
				System.out.println("로그인 성공");
				logid = id;
				break;
			} else {
				System.out.println("로그인 실패");
				continue;
			}
		}

		// 1. 글동록 2. 글 수정 3. 글 삭제 4. 글목록 5. 상세조회 6. 종료
		while (true) {
			System.out.println("1.글 등록 2.글 수정 3.글 삭제 4.글 목록 5. 상세 조회 6. 떡잎마을 인기글 7. 종료");
			System.out.print("메뉴 선택 >> ");
			int menu = Integer.parseInt(scn.nextLine());

			if (menu == 1) {
				System.out.println("글 등록");
				System.out.println("글 번호 >>");
				int bNum = Integer.parseInt(scn.nextLine());
				System.out.println("글 제목 >>");
				String bTitle = scn.nextLine();
				System.out.println("글 내용 >>");
				String bContent = scn.nextLine();
//			System.out.println("작성자 >>");
				String bWriter = logid;

				brd = new Board(bNum, bTitle, bContent, bWriter);
				dao.insert(brd);

			} else if (menu == 2) {

				System.out.println("글 수정");
				System.out.print("수정할 글 번호 >>");
				int bNum = Integer.parseInt(scn.nextLine());
				System.out.println("글 내용 >>");
				String bContent = scn.nextLine();

				brd = new Board(bNum, bContent);

				if (dao.getBrd(bNum).getbWriter().equals(logid)) {
					dao.update(brd);
					System.out.println("수정 완료");
				} else {
					System.out.println("수정할 권한이 없습니다");

				}
			} else if (menu == 3) {
				System.out.println("글 삭제");
				System.out.print("삭제할 글 번호 >>");
				int bNum = Integer.parseInt(scn.nextLine());

				if (dao.getBrd(bNum).getbWriter().equals(logid)) {
					dao.delete(bNum);
					System.out.println("삭제 완료");
				} else {
					System.out.println("삭제할 권한이 없습니다");
				}
			} else if (menu == 4) {
				System.out.println("글 목록");
				List<Board> boards = dao.search();
				for (Board i : boards) {
					System.out.println(i.getString());
				}

			} else if (menu == 5) {
				System.out.println("상세 조회");
				System.out.println("글 번호 >> ");

				int bNum = Integer.parseInt(scn.nextLine());

				// 글내용 상세보기
				Board x = dao.getBrd(bNum);
				

				// 댓글내용 상세보기
				if (x != null) {
					System.out.println(x);
					
					List<Reply> replies = dao.getReply(bNum);
					for (Reply i : replies) {
						System.out.println(i.toString());
					}
					
					System.out.println("댓글을 다시겠어요..? 1.네 2.아뇨");
					System.out.println("메뉴 선택 >>");
					int submenu = Integer.parseInt(scn.nextLine());
					if (submenu == 1) {
						int bNumber = bNum;
						System.out.println("댓글 내용 >> ");
						String reContent = scn.nextLine();
						String reWriter = logid;

						reply = new Reply(bNumber, reContent, reWriter);
						dao.addReply(reply);
					} else if (submenu == 2) {
						// 메뉴로 나가기.
					}
				}else {
					System.out.println("글이 없습니다.");
				}
	 
			} else if (menu == 6) {

				System.out.println("프로그램을 종료합니다");
				break;
			} else {
				System.out.println("메뉴를 잘못 입력하셨습니다. ");
			}
		}
	}

}
