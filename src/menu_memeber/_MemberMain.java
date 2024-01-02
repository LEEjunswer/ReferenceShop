package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import util.Util;

public class _MemberMain implements MenuCommand {
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
		System.out.println("============[ 회원 "+ cont.getLoginId() +"님 ]============");
		System.out.println(" [1] 상품구매\n [2] 구매내역\n [3] 게시판\n [4] 나의 정보\n [5] 회원 탈퇴\n [6] 로그아웃\n [0] 종료");
		System.out.println("=====================");
	}

	@Override
	public boolean update() {
		int sel = Util.getValue("메뉴 입력", 0, 6);
		if (sel == 0) {
			cont.setNext("MallMain");
		} else if (sel == 1) {
			cont.setNext("MemberShopping");
		} else if (sel == 2) {
			cont.setNext("MemberCart");
		} else if (sel == 3) {
			cont.setNext("MemberBoard");
		} else if (sel == 4) {
			cont.setNext("MemberInfo");
		} else if (sel == 5) {
			cont.setNext("MemberQuit");
		}else if (sel == 6) {
			System.out.println("[ 로그아웃 완료 ]");
			cont.setLoginId(null);
			cont.setNext("MallMain");
		}else if (sel == 0) {
			System.out.println("[ 프로그램 종료 ]");
			cont.setNext(null);
		}
		return false;
	}

}
