package svc;

//전체 회원 목록 보기 요청을 처리하는 Service 클래스

import vo.MemberBean;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;

import java.util.ArrayList;

public class MemberListService {
	public ArrayList<MemberBean> getMemberList(){
		Connection con=getConnection();
		MemberDAO memberDAO=MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		ArrayList<MemberBean> memberList=memberDAO.selectMemberList();//MemberDAO 클래스에 정의되어 있는 selectMemberList 메소드를 호출하여 전체 회원 목록을 반환 받음
		
		close(con);
		return memberList;//전체 회원 목록 정보를 반환
	}
}
