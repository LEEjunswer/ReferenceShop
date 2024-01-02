package dto;

public class Member {
	private static int num = 1000;
	private int memberNum;
	private String id;
	private String pw;
	private String memberName;

	public Member( String id, String pw, String memberName) {
		super();
		this.id = id;
		this.pw = pw;
		this.memberName = memberName;
		this.memberNum = num++;
	}
	
	private Member(String number, String id, String pw, String memberName) {
		super();
		this.memberNum = Integer.parseInt(number);
		this.id = id;
		this.pw = pw;
		this.memberName = memberName;
		++num;
	}


	public int getMemberNum() {
		return memberNum;
	}


	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	@Override
	public String toString() {
		return String.format("[%-6d] [%10s] [%10s] [%10s]", memberNum, id, pw, memberName);
	}
	
	public String saveData() {
		return "%s/%s/%s/%s%n".formatted(memberNum, id, pw, memberName);
	}
	
	public static Member convertData(String[] info) {
		if(info == null || info.length == 0 ) return null;
		
		return new Member(info[0],info[1],info[2],info[3]);
	}
}
