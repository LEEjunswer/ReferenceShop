package menu_memeber;

import java.util.ArrayList;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import util.Util;

public class MemberShopping implements MenuCommand {
	private MallController mallCont;

	@Override
	public void init() {
		mallCont = MallController.getInstance();
		System.out.println("======== 쇼핑몰에 오신것을 환영합니다 =========");
	}

	@Override
	public boolean update() {
		ItemDAO idao = ItemDAO.getInstance();
		CartDAO cdao = CartDAO.getInstance();
		int cateSize = idao.getAllCartegoryList();
		if (cateSize == 0) {
			System.out.println("[ no data ]");
			mallCont.setNext("MemberMain");
			return false;
		}

		int sel = Util.getValue("메뉴 입력", 0, cateSize);
		System.out.println("0) 뒤로 가기");
		if (sel == 0) {
			mallCont.setNext("MemberMain");
		} else {
			idao.getAllItemByCate(--sel);

			while (true) {
				String name = Util.getValue("구매 아이템 이름");
				int itemNo = idao.getItemByName(name);
				if (itemNo == -1) {
					System.out.println(" 아이템 이름 오류 다시 입력 해주세요 ");
					continue;
				} else if (itemNo == -2) {
					System.out.println(" [ no item data ] ");
					mallCont.setNext("MemberMain");
					return false;
				}
				int itemCnt = Util.getValue("아이템 구매 수량", 1, 100);
				
				cdao.addItemToMember(mallCont.getLoginId(), itemNo, itemCnt);
				System.out.println("[  %s %d개 구매 완료 ]".formatted(name, itemCnt));
				break;
			}

			mallCont.setNext("MemberCart");

		}
		return false;
	}

}
