package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import util.Util;

public class AdminBoard implements MenuCommand {
	private MallController cont;
	@Override
	public void init() {
		System.out.println("============[ 관리자 게시판 ]============");
		System.out.println(" [1] 게시글목록\n [2] 게시글삭제\n [3]뒤로가기\n [0] 종료");
		cont = MallController.getInstance();
		System.out.println("=====================");
	}

	@Override
	public boolean update() {
		BoardDAO dao = BoardDAO.getInstance();
		int sel = Util.getValue("메뉴 선택", 0, 3);
		if(sel == 1) {
			System.out.println(" ===== 전체 게시글 목록 ======= ");
			dao.printAll();
		}else if(sel == 2){
			int num = Util.getValue("삭제할 게시글 번호 입력", 1 , dao.getSize());
			if( dao.deleteABoardByNum(num) == null) {
				System.out.println("게시글 번호가 존재하지 않습니다");
			}else {
				System.out.println("게시글 삭제 완료");
			}
	
		}else if(sel == 3){
			cont.setNext("AdminMain");
		}else if (sel == 0) {
			System.out.println("[ 프로그램 종료 ]");
			cont.setNext(null);
		}
		return false;
	}

}
