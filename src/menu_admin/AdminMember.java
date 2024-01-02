package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.MemberDAO;
import util.Util;

public class AdminMember implements MenuCommand {
	private MallController cont;

	@Override
	public void init() {
		System.out.println("============[ 관리자 회원목록 ]============");

		System.out.println("[1] 회원목록\n[2] 회원삭제\n[3] 뒤로가기\n[0] 종료");
		cont = MallController.getInstance();
		System.out.println("=====================");
	}

	@Override
	public boolean update() {
		MemberDAO dao = MemberDAO.getInstance();
		int sel = Util.getValue("메뉴 선택", 0, 3);
		if(sel == 1) {
			System.out.println("==== 전체 회원 목록 === ");
			dao.printAll();
		}else if(sel == 2){
			System.out.println("회원 삭제시 구매 내역이 사라집니다");
			String id = Util.getValue("삭제할 회원 아이디");
			if(dao.deleteAMember(id)) {
				CartDAO.getInstance().deleteAllCartByMember(id);
				System.out.println(" 삭제 완료 ");
			}else {
				System.out.println(" 삭제 실패 ");
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
