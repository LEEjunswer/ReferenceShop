package dao;

import java.util.ArrayList;
import java.util.Arrays;

import dto.Board;
import util.Util;

public class BoardDAO {
	int count; 
	int pageSize = 5; 
	int curPageNum = 1; 
	int pageCount; 
	int startRow;
	int endRow;
	
	private BoardDAO() {
		boardList = new ArrayList<>();
	}

	private static BoardDAO instance = new BoardDAO();

	static public BoardDAO getInstance() {
		return instance;
	}

	
	private ArrayList<Board> boardList;

	public int getSize() {
		return boardList.size();
	}

	public void loadData(String data) {
		String[] temp = data.split("\n");
		for(String info : temp) {
			boardList.add(Board.convertData(info.split("/")));
		}
	}
	
	public void updateData() {
		count = boardList.size();
		pageCount = count / pageSize;
		if (count % pageSize > 0) {
			pageCount += 1;
		}
		pageCount = pageCount == 0 ? 1: pageCount;

	}
	
	public void printAll() {
		updateData();
		System.out.printf(" 총 게시글 %d 개 %n", count);
		System.out.printf(" 현재페이지 [%d / %d] %n", curPageNum, pageCount);
		printByPage();
		System.out.println("[1]이전");
		System.out.println("[2]이후");
		System.out.println("[3]게시글보기");
		System.out.println("[0]종료");
		int sel = Util.getValue("메뉴 입력", 0, 3); 
		if (sel == 1) {
			if (curPageNum == 1) {
				System.out.println("이전페이지 존재안함");
				return;
			}
			curPageNum -= 1;
			printAll();
		} else if (sel == 2) {
			if (curPageNum == pageCount) {
				System.out.println("이후페이지 존재안함");
				return;
			}
			curPageNum += 1;
			printAll();
		} else if (sel == 3) {
			
			int num = Util.getValue("게시글 번호 입력", startRow+1, endRow)-1;
			System.out.println();
			Board board = boardList.get(num);
			board.setHits(board.getHits()+1);
			System.out.println(board);
			System.out.println("-----------");
			System.out.println("\t" + board.getContents());
			System.out.println();
			System.out.println();
		} 
	}
	
	public void addBoardByMember(String id) {
		System.out.println(" [ 게시글 추가하기 ] ");
		String title = Util.getValue("게시글 제목");
		String contents = Util.getValue("게시글 내용");
		
		Board board = new Board(title,contents,id,1);
		System.out.println(board);
		boardList.add(board);
	}
	
	public void printByPage() {
		
		startRow = (curPageNum - 1) * pageSize;
		endRow = startRow + pageSize;
		if (endRow > count)
			endRow = count;
		
		for (int i = startRow; i < endRow; i += 1) {
			
			Board b = boardList.get(i);
			System.out.printf("(%3d) [ 제목 : %s \t  작성자 : %s \t 날짜 : %s 조회수 :%d ] %n", i + 1, b.getTitle() , b.getId() ,b.getDate() , b.getHits() );
		}

	}
	
	public ArrayList<Board> getBoardListByMember(String id) {
		ArrayList<Board> list = new ArrayList<Board>();
		for(Board b : boardList) {
			if(b.getId().equals(id)) {
				list.add(b);
			}
		}
		
		return list;
	}
	
	public void printMyBoardList(String id) {
		ArrayList<Board>list = getBoardListByMember(id);
		if(list.size() ==0) {
			System.out.println("게시글이 존재하지 않습니다");
			return;
		}
		System.out.println();
		System.out.println("------ 내 게시글 목록---------");
		for(Board b : list) {
			System.out.println(b);
			System.out.println(b.getContents());
			System.out.println("---------------");
		}
		
		
	}

	public Board deleteABoardByNum(int num) {
		for(Board b : boardList) {
			if(b.getBoradNum() == num) {
				boardList.remove(b);
				return b;
			}
		}
		return null;
	}
	public Board deleteABoardByNum(int num , String id ) {
		ArrayList<Board>list = getBoardListByMember(id);
		for(Board b : list) {
			if(b.getBoradNum() == num) {
				boardList.remove(b);
				return b;
			}
		}
		return null;
	}
	
	public String listToFile() {
		StringBuilder buffer = new StringBuilder();
		for(Board b : boardList) {
			buffer.append(b.saveData());
		}
		return buffer.toString();
	}
	
	

	

}
