package svc;

//회원 한 명 정보 보기 요청을 처리하는 Service 클래스

import vo.MemberBean;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;

public class MemberViewService {
	public MemberBean getMember(String viewId){
		Connection con=getConnection();
		MemberDAO memberDAO=MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		MemberBean member=memberDAO.selectMember(viewId);
		//MemberDAO 클래스에 정의되어 있는 selectMember 메소드를 호출하여 인자로 지정된 아이디를 가진 회원의 정보를 MemberBean 객체 형태로 반환받는 부분
		
		close(con);
		return member; //회원 정보 하나를 반환하는 부분
	}
}
