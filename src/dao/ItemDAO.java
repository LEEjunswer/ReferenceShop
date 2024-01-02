package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import dto.Board;
import dto.Item;

public class ItemDAO {
	private ItemDAO() {
		itemList = new ArrayList<Item>();
	}

	static private ItemDAO instance = new ItemDAO();

	static public ItemDAO getInstance() {
		return instance;
	}
	
	public int getSize() {
		return itemList.size();
	}

	private ArrayList<Item> itemList;

	public void loadData(String data) {
		String[] temp = data.split("\n");
		for(String info : temp) {
			itemList.add(Item.convertData(info.split("/")));
		}
	}
	
	public void printAll() {
		for(Item item : itemList) {
			System.out.println(item);
		}
	}
	
	public void printByName() {
//		ArrayList<Item> list = (ArrayList<Item>)itemList.clone();
//		Collections.sort(list);
//		System.out.println("====== 카테고리별 아이템 목록 ========= ");
//		for(Item i : list) {
//			System.out.println(i);
//		}
		System.out.println("====== 카테고리별 아이템 목록 ========= ");

		itemList.stream()
		.sorted((item1, item2) -> item1.getCategoryName().compareTo(item2.getCategoryName()))
		.forEach(System.out::println);
	}

	public ArrayList<String> getCategoryList() {
//		Set<String> categorySet = new LinkedHashSet<String>();
//		for (int i = 0; i < itemList.size(); i++) {
//			categorySet.add(itemList.get(i).getCategoryName());
//		}
//		Iterator<String> iterator = categorySet.iterator();
//		ArrayList<String> categoryList = new ArrayList<String>();
//		while (iterator.hasNext()) {
//			categoryList.add(iterator.next());
//		}
		
		 ArrayList<String> list = (ArrayList<String>) itemList.stream()
				                  .map(Item::getCategoryName)
				                  .distinct()
				                  .collect(Collectors.toList());
		
		return list;
	}
	
	public int getAllCartegoryList() {
		ArrayList<String> list = getCategoryList();
		
		int idx = 1;
		for(String name : list) {
			System.out.println("[%d] %s".formatted(idx++,name));
		}
		return list.size();
	}

	public void getAllItemByCate(int num) {
		ArrayList<String> list = getCategoryList();
		ArrayList<Item> itemList = getItemListBycate(list.get(num));
		
		System.out.println(" [ %s 의 아이템 목록 ] ".formatted(list.get(num)));
		int idx = 1;
		for(Item item : itemList) {
			System.out.println("[%d] %s %d".formatted(idx++,item.getItemName(), item.getPrice()) );
		}
	}
	

	public ArrayList<Item> getItemListBycate(String category) {
//		ArrayList<Item> list = new ArrayList<Item>();
//		
//		for(Item item : itemList) {
//			if(item.getCategoryName().equals(category)) {
//				categorysItemList.add(item);
//			}
//		}

		ArrayList<Item> list = (ArrayList<Item>)itemList.stream()
							   .filter(item -> item.getCategoryName().equals(category))
							   .collect(Collectors.toList());
							   
		return list;
	}
	
	public int getItemByName(String name) {
		if(itemList.size() == 0 ) return -2;
		for(Item item : itemList) {
			if(item.getItemName().equals(name)) {
				return item.getItemNum();
			}
		}
		return -1;

	}

	public Item getItemByItemNo(int itemNo) {
		for(Item item : itemList) {
			if(item.getItemNum() == itemNo) {
				return item;
			}
		}
		return null;
	}

	public Item deleteAItemByNum(int num, String id) {
		for(Item i : itemList) {
			if(i.getItemNum() == num) {
				itemList.remove(i);
				return i;
			}
		}
		return null;
	}
	

	public void addItem(String itemName, String category,int price) {
		itemList.add(new Item(category,itemName,price));
	}
	
	public void getListBySale() {
		System.out.println(" ========== 판매된 아이템 목록 ==========");
		CartDAO cdao = CartDAO.getInstance();
	    Map<Integer,Integer> map = cdao.getCartListByItem();
	    Map<Item,Integer> itemMap = new HashMap<>();
	    for(Integer num : map.keySet()) {
	    	itemMap.put(getItemByItemNo(num), map.get(num));
	    }

//		for(Item key : itemList.keySet()) {
//			System.out.println( key +" " + itemList.get(key)+"개");
//		}
//	    
//		
	     List<Map.Entry<Item,Integer>> list = new ArrayList<Map.Entry<Item,Integer>>(itemMap.entrySet());

	        Collections.sort(list, new Comparator<>() {
				@Override
				public int compare(Entry<Item, Integer> o1, Entry<Item, Integer> o2) {
					return  o2.getValue() - o1.getValue();
				}
	        });
	        
	        for(Map.Entry<Item, Integer> entry : list){
	            System.out.println(entry.getKey()+" "+entry.getValue()+"개");
	        }
		
		
	}
	
	public boolean isValidName(String item) {
		for(Item i : itemList) {
			if(i.getItemName().equals(item) || i.getCategoryName().equals(item)) {
				return false;
			}
		}
		return true;
	}
	
	public String listToFile() {
		StringBuilder buffer = new StringBuilder();
		for(Item b : itemList) {
			buffer.append(b.saveData());
		}
		return buffer.toString();
	}
	

}
