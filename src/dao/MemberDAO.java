package dao;

import java.util.ArrayList;

import dto.Board;
import dto.Item;
import dto.Member;

public class MemberDAO {
	private MemberDAO() {
		memberList = new ArrayList<>();
	}

	private static MemberDAO instance = new MemberDAO();

	static public MemberDAO getInstance() {
		return instance;
	}

	private ArrayList<Member> memberList;

	
	public void loadData(String data) {
		String[] temp = data.split("\n");
		for(String info : temp) {
			memberList.add(Member.convertData(info.split("/")));
		}
	}
	
	public void printAll() {
		for(Member member : memberList) {
			System.out.println(member);
		}
	}
	
	public boolean hasData() {
		if(memberList.size() == 0) {
			System.out.println("회원 데이터가 존재하지 않습니다");
		}
		return memberList.size() >0;
	}
	
	public Member isValidMember(String id, String pw) {
		Member member = getMemberById(id);
		if(getMemberById(id)== null) {
			System.out.println("아이디가 존재하지 않습니다");
			return null;
		}
		if(!isMatchPw(member, pw)) return null;
		return member;
	}
	
	private boolean isMatchPw(Member member , String pw) {
		if(!member.getPw().equals(pw)) {
			System.out.println("비밀번호 불일치 ");
		}
		return member.getPw().equals(pw);
	}
	
	
	public boolean insertMember(String id, String pw, String name) {
		
	
		Member member = new Member(id,pw, name);
		memberList.add(member);
		System.out.println(member +" 추가 완료");
		return true;
	}
	

	public boolean deleteMember(String id, String pw) {
		
		Member member = isValidMember(id, pw);
		if(member == null) return false;
		memberList.remove(member);
		return true;
	}
	

	public Member getMemberById(String id) {
		if(memberList.size() == 0) return null;
		for (Member m : memberList) {
			if (id.equals(m.getId())) {
				return m;
			}
		}
		return null;
	}
	
	
	public boolean updateMember(String id, String pw, String newPw) {

		if(pw.equals(newPw)) {
			
			return false;
		}
		
		Member m = getMemberById(id);
		m.setPw(newPw);
		System.out.println(m);
		return true;
		
	}
	
	public void getAMemberInfo(String id) {
		Member m = getMemberById(id);
		System.out.println(m);
	}

	public boolean changeMyPassWord(String id , String pw , String newPw) {
		Member m = getMemberById(id);
		
		if(!m.getPw().equals(pw)) {
			return false;
		}
		
		m.setPw(newPw);
		return true;
	}
	
	public boolean deleteAMember(String id) {
		if(id.equals("admin")) {
			System.out.println("관리자 회원 삭제 불가능");
			return false;
		}
	
		for(Member m : memberList) {
			if(m.getId().equals(id)) {
				memberList.remove(m);
				return true;
			}
		}
		return false;
	}
	
	public String listToFile() {
		StringBuilder buffer = new StringBuilder();
		for(Member m : memberList) {
			buffer.append(m.saveData());
		}
		return buffer.toString();
	}
	
}
