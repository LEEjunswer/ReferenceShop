package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import dao.MemberDAO;
import util.Util;

public class MemberCart implements MenuCommand {
	private MallController cont;
	@Override
	public void init() {
		
		System.out.println("============[ 구매내역 ]============");
		System.out.println("[1] 쇼핑하기\n[2] 뒤로가기\n[0] 종료\n");
		System.out.println("=====================");
		cont = MallController.getInstance();
	}

	@Override
	public boolean update() {

		CartDAO cdao = CartDAO.getInstance();
		if(!cdao.printCartByMember(cont.getLoginId())) {
			cont.setNext("MemberMain");
			return false;
		}
		
		int sel = Util.getValue("메뉴 선택", 0, 2);
		if(sel == 1) {
			cont.setNext("MemberShopping");
		}else if(sel == 2){
			cont.setNext("MemberMain");
		}else if (sel == 0) {
			System.out.println("[ 프로그램 종료 ]");
			cont.setNext(null);
		}
		

		return false;
	}

}
