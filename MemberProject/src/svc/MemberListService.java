package svc;

//��ü ȸ�� ��� ���� ��û�� ó���ϴ� Service Ŭ����

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
		
		ArrayList<MemberBean> memberList=memberDAO.selectMemberList();//MemberDAO Ŭ������ ���ǵǾ� �ִ� selectMemberList �޼ҵ带 ȣ���Ͽ� ��ü ȸ�� ����� ��ȯ ����
		
		close(con);
		return memberList;//��ü ȸ�� ��� ������ ��ȯ
	}
}
