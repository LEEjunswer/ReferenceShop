package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.MemberDAO;
import util.Util;

public class MemberQuit implements MenuCommand{
	private MallController cont;
	@Override
	public void init() {
		cont = MallController.getInstance();
		System.out.println("============[ "+cont.getLoginId()+"회원탈퇴 ]============");		
		System.out.println("회원 탈퇴시 구매 내역이 사라집니다");
		System.out.println("정말 탈퇴하시겠습니까? ");
		System.out.println("[1] 예\n[2] 뒤로가기\n[0] 종료");
		System.out.println("=====================");
	}

	@Override
	public boolean update() {
		CartDAO cdao = CartDAO.getInstance(); 
		MemberDAO mdao = MemberDAO.getInstance();
		int sel = Util.getValue("메뉴 선택", 0, 1);
		if(sel == 1) {
			
			cdao.deleteAllCartByMember(cont.getLoginId());
			if(mdao.deleteAMember(cont.getLoginId())) {
				System.out.println("탈퇴 완료");
				cont.setNext("MallMain");
				return false;
			}else {
				System.out.println("탈퇴 실패");
			}
		}else if(sel == 2){
			cont.setNext("MemberMain");
		}else if (sel == 0) {
			System.out.println("[ 프로그램 종료 ]");
			cont.setNext(null);
		}
		
		return false;
	}

	
}
