package svc;

//�α��� ��û�� ó���ϴ� Service Ŭ����

import vo.MemberBean;
import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.MemberDAO;

public class MemberLoginService {
	public boolean login(MemberBean member){
		Connection con=getConnection();
		MemberDAO memberDAO=MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		boolean loginResult=false;//�α��� ���� ���θ� �����ϴ� ����
		String loginId=memberDAO.selectLoginId(member);//MemberDAO Ŭ������ ���ǵǾ� �ִ� selectLoginId �޼ҵ带 ȣ���Ͽ� �α��ο� ������ id �� ��ȯ����
		//�α��ο� �����ϸ� id���� �α��ο� ������ ������� ���̵� ���� ��ȯ�Ǹ� �α��ο� �����ϸ� null ��ȯ
	
		//�α��ο� �����ϸ� loginResult ���� ���� true �� ����
		if(loginId!=null){
			loginResult=true;
		}
		close(con);
		return loginResult;
	}
}
