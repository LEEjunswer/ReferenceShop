package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import dto.Board;
import util.Util;

public class MemberBoard implements MenuCommand{
	private MallController cont;
	@Override
	public void init() {

		System.out.println("============[ 게시판 ]============");
		System.out.println(" [1] 게시글보기\n [2] 게시글추가\n [3] 내개시글(삭제)\n [4] 뒤로가기\n [0] 종료");
		cont = MallController.getInstance();
		System.out.println("=====================");
		
	}

	@Override
	public boolean update() {
		BoardDAO dao = BoardDAO.getInstance();
		//dao.printAll();
		
		int sel = Util.getValue("메뉴 입력", 0, 4);
		if (sel == 0) {
			System.out.println("[ 프로그램 종료 ]");
			cont.setNext(null);
			
		}
		else if (sel == 1) {
			dao.printAll();
		} else if (sel == 2) {
			dao.addBoardByMember(cont.getLoginId());
		} else if (sel == 3) {
			dao.printMyBoardList(cont.getLoginId());
			System.out.println("[1]삭제");
			System.out.println("[0]돌아가기");
			sel = Util.getValue("선택", 0, 1);
			if(sel == 1) {
				int num = Util.getValue("삭제할 게시글 번호 입력", 1 , dao.getSize());
				if( dao.deleteABoardByNum(num, cont.getLoginId()) == null) {
					System.out.println("본인 게시글만 삭제하실 수 있습니다");
				}else {
					System.out.println("게시글 삭제 완료");
				}
			}
			
		} else if (sel == 4) {
			cont.setNext("MemberMain");
		}

		
		return false;
	}

}
