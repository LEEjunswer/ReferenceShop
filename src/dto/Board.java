package dto;

import java.time.LocalDate;

public class Board {
	private static int num;
	private int boradNum;
	private String title;
	private String id;
	private String date;
	private String contents;
	private int hits;

	private Board(String number, String title, String contents ,String id,String date, String hits) {
		super();
		boradNum = Integer.parseInt(number);
		this.title = title;
		this.contents = contents;
		this.id = id;
		this.date = date;
		this.hits = Integer.parseInt(hits);
		num++;
		
	}
	
	public Board(String title, String contents ,String id, int hits) {
		super();
		this.title = title;
		this.contents = contents;
		this.id = id;
		this.hits = hits;
		this.date = LocalDate.now().toString();
		this.boradNum=++num;
	
	}

	public String getTitle() {
		return title;
	}

	public int getBoradNum() {
		return boradNum;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public int getHits() {
		return hits;
	}


	public void setHits(int hits) {
		this.hits = hits;
	}

	@Override
	public String toString() {
		return "["+boradNum+"][ 제목 : " + title + "\t "+ " 작성자 : " + id + "\t 날짜 : " + date + "  조회수 : " + hits + "]";
	}
	
	public String saveData() {
		return "%s/%s/%s/%s/%s/%s%n".formatted(boradNum,title,contents,id,date,hits);
	}
	
	public static Board convertData(String[] info) {
		if(info == null || info.length == 0 ) return null;
		
		return new Board(info[0],info[1],info[2],info[3],info[4],info[5]);
	}


}
