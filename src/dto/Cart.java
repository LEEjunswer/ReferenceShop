package dto;

import java.util.Objects;

public class Cart {
	private static int num;
	private int cartNum;
	private String id;
	private int itemNum;
	private int itemCnt;
	public Cart() {
	}

	private Cart(String number, String id, String itemNumber, String itemCnt) {
		super();
		this.cartNum =Integer.parseInt(number); 
		this.id = id;
		this.itemNum = Integer.parseInt(itemNumber); 
		this.itemCnt = Integer.parseInt(itemCnt); 
		this.cartNum=++num;
	}
	public Cart(String id, int itemNum, int itemCnt) {
		this.cartNum=++num;
		this.id = id;
		this.itemNum = itemNum;
		this.itemCnt = itemCnt;
	}



	public int getCartNum() {
		return cartNum;
	}

	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
    }


	public int getItemCnt() {
		return itemCnt;
	}

	public void setItemCnt(int itemCnt) {
		this.itemCnt = itemCnt;
	}

	public String toString() {
		String print = String.format("[%-6d] [%10s] [%10s] [%10s] ",cartNum, id , itemNum, itemCnt);
		return print;
	}
	
	public String saveData() {
		return "%s/%s/%s/%s%n".formatted(cartNum,id,itemNum,itemCnt);
	}
	
	public static Cart convertData(String[] info) {
		if(info == null || info.length == 0 ) return null;
		
		return new Cart(info[0],info[1],info[2],info[3]);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, itemNum);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return Objects.equals(id, other.id) && itemNum == other.itemNum;
	}
	


}
