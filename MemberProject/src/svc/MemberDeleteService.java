package svc;

//회원 한 명 정보 삭제 요청을 처리하는 Service 클래스

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;

public class MemberDeleteService {
	public boolean deleteMember(String deleteId){
		boolean deleteResult=false;
		Connection con=getConnection();
		MemberDAO memberDAO=MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		int deleteCount=memberDAO.deleteMember(deleteId);
		//MemberDAO 클래스에 정의되어 있는 deleteMember 메소드를 호출하여 인자로 지정된 아이디를 가지고 있는 회원 정보를 삭제
		
		//삭제된 레코드가 하나 이상일 때, 즉 회원 정보 삭제 요청이 성공했을 때 트랜잭션 완성
		if(deleteCount>0){
			commit(con);
			deleteResult=true;
		}else{
			rollback(con);//실패했을 때 트랜잭션 작업 취소
		}
		close(con);
		return deleteResult;
	}
}
