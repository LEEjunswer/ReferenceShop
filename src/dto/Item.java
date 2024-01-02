package dto;

public class Item implements Comparable<Item>{
	private static int num;
	private int itemNum;
	private String categoryName;
	private String itemName;
	private int price;

	public Item() {
	}

	private Item(String number, String categoryName, String itemName, String price) {
		this.itemNum = Integer.parseInt(number);
		this.categoryName = categoryName;
		this.itemName = itemName;
		this.price = Integer.parseInt(price);
		num++;
	}

	public Item( String categoryName, String itemName, int price) {
		this.itemNum=++num;
		this.categoryName = categoryName;
		this.itemName = itemName;
		this.price = price;
	}
	
	
	public int getItemNum() {
		return itemNum;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String toString() {
		return String.format("[%-3d] [%5s] [%5s] [%10d원]", itemNum, categoryName, itemName, price);
	}
	
	public String saveData() {
		return "%s/%s/%s/%s%n".formatted(itemNum,categoryName,itemName,price);
	}
	
	public static Item convertData(String[] info) {
		if(info == null || info.length == 0 ) return null;
		
		return new Item(info[0],info[1],info[2],info[3]);
	}

	
	
	
	@Override
	public int compareTo(Item o) {
		if(o.getCategoryName().compareTo(categoryName) == 0) {
			return itemName.compareTo(o.itemName);
		}
		return o.getCategoryName().compareTo(categoryName);
	}

}
