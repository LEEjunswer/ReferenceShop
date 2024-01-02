package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dto.Cart;
import dto.Item;

public class CartDAO {
	private CartDAO() {
		cartList = new ArrayList<Cart>();
	}

	private static CartDAO instance = new CartDAO();

	static public CartDAO getInstance() {
		return instance;
	}

	private ArrayList<Cart> cartList;

	
	public void loadData(String data) {
		String[] temp = data.split("\n");
		for(String info : temp) {
			cartList.add(Cart.convertData(info.split("/")));
		}
	}
	public void printAll() {
		for(Cart cart : cartList) {
			System.out.println(cart);
		}
	}
	
	public void addItemToMember(String id, int itemNo,int itemCnt) {
		
		cartList.add(new Cart(id,itemNo,itemCnt));
		
	}
	
	
	public ArrayList<Cart> getCartByMember(String membeId) {
		 ArrayList<Cart> list = new ArrayList<Cart>();
		for(Cart cart : cartList) {

			if(cart.getId().equals(membeId)) {
				list.add(cart);
			}
		
		}
		return list;
	}
	
	public boolean printCartByMember(String memberId ) {
		
		 ItemDAO idao = ItemDAO.getInstance();
		 ArrayList<Cart> list = getCartByMember(memberId);
		 if(list.size()==0) {
			 System.out.println(" 구매 내역이 없습니다 구매해주세요 ");
			 return false;
		 }
		 Set<Cart> itemList = new HashSet<>(list); // 아이템 값 중복 없이 저장 
		 
		 int total = 0;
		 int idx =0;
		 int count =0;
		 for(Cart c : itemList) {
			 int cnt =0;
			 for(Cart cart : list) {
				 if(c.getItemNum() == cart.getItemNum()) {
					 cnt+=cart.getItemCnt();
				 }
			 }
			 
			 Item item = idao.getItemByItemNo(c.getItemNum());
			 int sum = cnt*item.getPrice();
			 total+=sum;
			 count+=cnt;
			 System.out.println("[%3d] %8s(%8d원) \t %3d개 총 %d원 ".formatted
					 (++idx,item.getItemName(),item.getPrice(),cnt,sum));
		 }
		 System.out.println("====================");
		 System.out.println("총 %d 개 ( %d 원)".formatted(count, total));
		 return true;
	}
	

	public int getTotalCntByItem(int itemNo) {
		int cnt =0;
		for(Cart c : cartList) {
			if(c.getItemNum() == itemNo) {
				cnt+= c.getItemCnt();
			}
		}
		return cnt;
	}
	
	public Map<Integer,Integer> getCartListByItem() {
		Map<Integer,Integer> list = new HashMap<>();
		for(Cart c : cartList) {
			int itemNum = c.getItemNum();
			if(list.containsKey(itemNum)) {
				int cnt = list.get(itemNum)+ c.getItemCnt();
				list.put(c.getItemNum(), cnt);
			}else {
				list.put(c.getItemNum(), c.getItemCnt());
			}
		}
		return list;
	}

	public void deleteAllCartByMember(String id) {
		for(int i =0; i < cartList.size();i+=1) {
			if(cartList.get(i).getId().equals(id)) {
				cartList.remove(i);
				i-=1;
			}
		}
		System.out.println("회원 구매 내역 삭제 완료 ");
	}
	public void deleteAllCartByItem(int num) {
		for(int i =0; i < cartList.size();i+=1) {
			if(cartList.get(i).getItemNum() == num) {
				cartList.remove(i);
				i-=1;
			}
		}
		System.out.println("구매내역에서 아이템 삭제 완료");
	}
	
	public String listToFile() {
		StringBuilder buffer = new StringBuilder();
		for(Cart c : cartList) {
			buffer.append(c.saveData());
		}
		return buffer.toString();
	}
	
}
