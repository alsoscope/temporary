package svc;

//ȸ�� �� �� ���� ���� ��û�� ó���ϴ� Service Ŭ����

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
		//MemberDAO Ŭ������ ���ǵǾ� �ִ� deleteMember �޼ҵ带 ȣ���Ͽ� ���ڷ� ������ ���̵� ������ �ִ� ȸ�� ������ ����
		
		//������ ���ڵ尡 �ϳ� �̻��� ��, �� ȸ�� ���� ���� ��û�� �������� �� Ʈ����� �ϼ�
		if(deleteCount>0){
			commit(con);
			deleteResult=true;
		}else{
			rollback(con);//�������� �� Ʈ����� �۾� ���
		}
		close(con);
		return deleteResult;
	}
}
