package svc;

//ȸ�� �� �� ���� ���� ��û�� ó���ϴ� Service Ŭ����

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
		//MemberDAO Ŭ������ ���ǵǾ� �ִ� selectMember �޼ҵ带 ȣ���Ͽ� ���ڷ� ������ ���̵� ���� ȸ���� ������ MemberBean ��ü ���·� ��ȯ�޴� �κ�
		
		close(con);
		return member; //ȸ�� ���� �ϳ��� ��ȯ�ϴ� �κ�
	}
}
