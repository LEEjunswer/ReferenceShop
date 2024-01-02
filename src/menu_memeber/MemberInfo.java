package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import dao.MemberDAO;
import util.Util;

public class MemberInfo implements MenuCommand {
	private MallController cont;
	@Override
	public void init() {
		System.out.println("============[ 내정보 ]============");
		System.out.println("[1] 비밀번호변경\n[2] 뒤로가기\n[0] 종료");
		System.out.println("=====================");
		cont = MallController.getInstance();
	}

	@Override
	public boolean update() {

		MemberDAO dao = MemberDAO.getInstance();
		
		// 내정보 출력 
		dao.getAMemberInfo(cont.getLoginId());
		int sel = Util.getValue("메뉴 선택", 0, 2);
		if(sel == 1) {
			
			// 비번변경
			String pw = Util.getValue("패스워드 ");
			String newPw = Util.getValue("신규 패스워드 ");
			if(pw.equals(newPw)) {
				System.out.println("다른 비밀번호를 입력해주세요 ");
				return false;
			}
			if(!dao.changeMyPassWord(cont.getLoginId(), pw, newPw)) {
				System.out.println("비밀번호 변경 실패");
			}else {
				System.out.println("비밀번호 변경환료");
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
