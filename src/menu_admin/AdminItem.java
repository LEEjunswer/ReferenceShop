package menu_admin;

import java.util.Map;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import util.Util;

public class AdminItem implements MenuCommand {
	private MallController cont;
	@Override
	public void init() {
		System.out.println("============[ 관리자 쇼핑몰관리 ]============");
		
		System.out.println("카테고리 순으로 정렬 카테고리가 같으면 아이템 이름순으로 정렬 ");
		System.out.println("[1] 아이템 추가 \n[2] 아이템 삭제 \n[3] 총 매출 아이템 갯수 출력(판매량 높은순으로)\n[4] 뒤로가기\n[0] 종료");
		cont = MallController.getInstance();
		System.out.println("=====================");
	}

	@Override
	public boolean update() {
		ItemDAO dao = ItemDAO.getInstance();

		int sel = Util.getValue("메뉴 선택", 0, 4);
		if(sel == 1) {
			dao.printByName();
			String itemName = Util.getValue("아이템");
			if(!dao.isValidName(itemName)) {
				System.out.println("이미 있는 카테고리/아이템 이름 입니다");
				return false;
			}
			String category = Util.getValue("카테고리");
			int price = Util.getValue("가격 ", 100, 1000000);
			
			dao.addItem(itemName, category, price);
			
			System.out.println("아이템 추가 완료 ");
			
		}else if(sel == 2){
			dao.printByName();
			System.out.println(" [ 아이템 삭제시 구매 내역이 사라집니다 ]");
			int num = Util.getValue("삭제할 아이템 번호 입력", 1 , dao.getSize());
			if( dao.deleteAItemByNum(num, cont.getLoginId()) == null) {
				System.out.println("아이템 번호가 존재하지 않습니다");
			}else {
				CartDAO.getInstance().deleteAllCartByItem(num);
				System.out.println("아이템 삭제 완료");
			}
		}else if(sel == 3){
			dao.getListBySale();
		}else if(sel == 4){
			cont.setNext("AdminMain");
		}else if (sel == 0) {
			System.out.println("[ 프로그램 종료 ]");
			cont.setNext(null);
		}
		return false;
	}

}
