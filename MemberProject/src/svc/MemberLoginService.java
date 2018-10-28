package svc;

//로그인 요청을 처리하는 Service 클래스

import vo.MemberBean;
import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.MemberDAO;

public class MemberLoginService {
	public boolean login(MemberBean member){
		Connection con=getConnection();
		MemberDAO memberDAO=MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		boolean loginResult=false;//로그인 성공 여부를 저장하는 변수
		String loginId=memberDAO.selectLoginId(member);//MemberDAO 클래스에 정의되어 있는 selectLoginId 메소드를 호출하여 로그인에 성공한 id 값 반환받음
		//로그인에 성공하면 id값이 로그인에 성공한 사용자의 아이디 값이 반환되며 로그인에 실패하면 null 반환
	
		//로그인에 성공하면 loginResult 변수 값을 true 로 변경
		if(loginId!=null){
			loginResult=true;
		}
		close(con);
		return loginResult;
	}
}
