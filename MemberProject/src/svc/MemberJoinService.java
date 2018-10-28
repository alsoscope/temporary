package svc;

//각 클라이언트의 요청을 처리하는 비즈니스 로직이 구현되는 Service 클래스들
//회원등록 요청을 처리하는 Service 클래스

import vo.MemberBean;
import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.MemberDAO;

public class MemberJoinService {
	public boolean joinMember(MemberBean member){
		boolean joinSuccess=false;
		MemberDAO memberDAO=MemberDAO.getInstance();
		Connection con=getConnection();
		memberDAO.setConnection(con);
		int insertCount=memberDAO.insertMember(member);
		
		if(insertCount>0){
			joinSuccess=true;
			commit(con);
		}else{
			rollback(con);
		}
		close(con);
		return joinSuccess;
	}
}
