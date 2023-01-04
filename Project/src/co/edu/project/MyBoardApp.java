package co.edu.project;

import java.util.List;
import java.util.Scanner;

import co.edu.project.MyBoard;


public class MyBoardApp {
	public static void main(String[] args) {
		MyBoardDAO dao = new MyBoardDAO();
		MyBoard brd = new MyBoard();
		MyReply rp = new MyReply();
		String logId = null;
		Scanner scn = new Scanner(System.in);

		while (true) {
			System.out.println("♣ 떡잎마을 주민 게시판 ♣");
			System.out.println("1. 로그인 2. 게시판 회원 가입");
			int doorMenu = Integer.parseInt(scn.nextLine());

			if (doorMenu == 1) {
				System.out.println("<< 로그인 >>");
				System.out.println("아이디 >> ");
				String id = scn.nextLine();
				System.out.println("비밀번호 >> ");
				String pw = scn.nextLine();

				if (dao.login(id, pw)) {
					System.out.println("로그인 성공");
					logId = id;
					break;
				} else {
					System.out.println("로그인 실패");
					continue;
				}
			} else if (doorMenu == 2) {
				System.out.println("<< 게시판 회원 가입 >>");
				System.out.println("사용 아이디 >> ");
				String id = scn.nextLine();
				System.out.println("사용 비밀번호 >> ");
				String passwd = scn.nextLine();
				System.out.println("이름 >> ");
				String name = scn.nextLine();
				System.out.println("메일 주소 >>");
				String email = scn.nextLine();

				MyUser newuser = new MyUser(id, passwd, name, email);
				if (dao.ckOverlap(id)) {
					dao.applyUser(newuser);
					System.out.println("가입 신청이 완료되었습니다.");
					System.out.println("관리자의 승인을 기다려 주세요.");
				} else {
					System.out.println("이미 존재하는 아이디입니다. ");
					continue;
				}
			}

		}

		while (true) {
			System.out.println("♣ 떡잎마을 주민 게시판 ♣");
			System.out.println("1.글 등록 2.나의 글/댓글 3.전체 글 보기 4.상세 글 보기 5.떡잎마을 인기글 6.탈퇴 7.가입 신청 승인 8.종료");
			System.out.print(">>");
			int menu = Integer.parseInt(scn.nextLine());

			if (menu == 1) {
				System.out.println("<< 글 등록 페이지 >>");
//				System.out.println("글 번호 >> ");
//				int bNum = Integer.parseInt(scn.nextLine());
				System.out.println("글 제목 >> ");
				String bTitle = scn.nextLine();
				System.out.println("글 내용 >> ");
				String bContent = scn.nextLine();
				String bWriter = logId;

				brd = new MyBoard(bTitle, bContent, bWriter);

				dao.inputBrd(brd);

			} else if (menu == 2) {
				System.out.println("<< 나의 글/댓글 >>");
				System.out.println("< 나의 글 목록>");
				List<MyBoard> myBrd = dao.getBrd(logId);
				for (MyBoard i : myBrd) {
					System.out.println(i.myString());
				}

				System.out.println("<나의 댓글 목록>");
				List<MyReply> myRp = dao.getRp(logId);
				for (MyReply i : myRp) {
					System.out.println(i.toString());
				}

				System.out.println("정정을 원하는 항목이 있나요? 1.네 2.아니오");
				int modify = Integer.parseInt(scn.nextLine());
				if (modify == 1) {
					System.out.println("1.글 수정 2. 글 삭제 3.댓글 수정 4.댓글 삭제");
					int subMenu = Integer.parseInt(scn.nextLine());
					if (subMenu == 1) {
						System.out.println("수정할 글 번호 >> ");
						int bNum = Integer.parseInt(scn.nextLine());
						System.out.println("수정할 내용 >> ");
						String bContent = scn.nextLine();
						brd = new MyBoard(bNum, bContent);

						if (dao.searchBrd(bNum).getbWriter().equals(logId)) {
							dao.updateBrd(brd);
							System.out.println("수정 완료");
						} else {
							System.out.println("수정할 권한이 없습니다.");
						}

					} else if (subMenu == 2) {
						System.out.println("삭제할 글 번호 >> ");
						int bNum = Integer.parseInt(scn.nextLine());

						if (dao.searchBrd(bNum).getbWriter().equals(logId)) {
							dao.delBrd(bNum);
							System.out.println("삭제 완료");
						} else {
							System.out.println("삭제 권한이 없습니다.");
						}

					} else if (subMenu == 3) {
						System.out.println("수정할 댓글 번호 >> ");
						int reNum = Integer.parseInt(scn.nextLine());
						System.out.println("수정할 내용 >> ");
						String reContent = scn.nextLine();
						rp = new MyReply(reNum, reContent);
						dao.updateRp(rp);
					} else if (subMenu == 4) {
						System.out.println("삭제할 댓글 번호 >> ");
						int reNum = Integer.parseInt(scn.nextLine());
						dao.delRp(reNum);
					}
				}
			} else if (menu == 3) {
				System.out.println("<< 전체 글 목록 >>");
				List<MyBoard> everyBrd = dao.viewBrd();
				int totalCnt = everyBrd.size();
				int totalPage = (int) Math.ceil(totalCnt / 5.0);
				boolean firstpage = true;
				
				if(firstpage) {
					for(int i = 0; i<5; i++) {
						System.out.println(everyBrd.get(i));
					}
				}
				
				while (true) {
					// 메뉴 보여주기, 매뉴가 더 필요해지면 자동으로 생성이 되게끔!
					System.out.print("페이지 목록 : ");
					for (int i = 1; i <= totalPage; i++) {
						System.out.print(i + ", ");
					}
					System.out.println("이동할 페이지 >> ");
					System.out.println("메인 메뉴로 나가고 싶다면 0을 입력하세요");
					int pageNum = Integer.parseInt(scn.nextLine());
					firstpage = false;
					if (pageNum > 0) {
						int page = pageNum;
						int from, to;
						from = (page - 1) * 5;
						to = (5 * page) - 1;
						if (to > totalCnt)
							to = totalCnt - 1;
						System.out.println("<< " + page + "페이지 >>");
						System.out.println("글번호 제목 글쓴이 날짜");
						for (int i = from; i <= to; i++) {
							System.out.println(everyBrd.get(i));
						}
						System.out.println("----------------------");

					} else if (pageNum == 0) {
						break;
					}

				} // end of while

			} else if (menu == 4) {
				System.out.println("<< 글 상세 검색 >>");
				System.out.println("글 번호 >> ");
				int bNum = Integer.parseInt(scn.nextLine());

				MyBoard x = dao.searchBrd(bNum);
				List<MyReply> y = dao.searchRp(bNum);

				if (x != null) {
					System.out.println(x.showString());
					for (MyReply i : y) {
						System.out.println(i.toString());
					}
					System.out.println("댓글 다실건지? 1. 댓글 등록 2.아니오");
					System.out.print(">> ");
					int subMenu = Integer.parseInt(scn.nextLine());
					if (subMenu == 1) {
						int boardNum = bNum;
						System.out.println("댓글 입력 >> ");
						String reContent = scn.nextLine();
						String reWriter = logId;

						rp = new MyReply(boardNum, reContent, reWriter);
						dao.inputRp(rp);
					}
				} else {
					System.out.println("글이 없습니다.");
				}

			} else if (menu == 5) {
				System.out.println("<<떡잎 마을 인기글>>");
				System.out.println("댓글수 순으로 5개 보여드립니다!");
				List<MyReply> popularBrd = dao.rankBrd();
				System.out.println("     글번호    제목            ");
				System.out.println("----------------------------");
				for (int i = 0; i < 5; i++) {
					System.out.println((i + 1) + "등 : " + popularBrd.get(i).rank());
				}
			} else if (menu == 6) {
				System.out.println("<< 회원 탈퇴 >>");
				System.out.println(logId + " 님 탈퇴하시려면 비밀 번호를 입력해주세요");
				System.out.println("탈퇴 시 작성하신 모든 글/댓글도 사라집니다.");
				System.out.println(">>");
				String id = logId;
				String pw = scn.nextLine();
				dao.delUser(id, pw);
				dao.byeBrd(id);
				dao.byeRp(id);
				System.out.println("탈퇴 완료 되었습니다.");
			} else if (menu == 7) {
				System.out.println("<< 가입 신청 승인 페이지 >>");
				String id = logId;
				if (dao.ckManager(id)) {
					List<MyUser> apply = dao.viewApply();
					for (int i = 0; i < apply.size(); i++) {
						System.out.println(apply.get(i).toString());
					}

					System.out.println("승인할 아이디 입력> ");
					String applyId = scn.nextLine();

					dao.inputUser(applyId);
					dao.delApply(applyId);

				} else {
					System.out.println("관리자 권한이 없습니다.");
				}
			} else if (menu == 8) {
				System.out.println("종료합니다.");
				break;
			}
		}
	}

}// end of class
