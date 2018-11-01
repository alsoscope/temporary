package svc;

//�� ��ǰ �� �������� ��û�� ó���ϴ� ����Ͻ� ������ �����ϴ� Service Ŭ����

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DogDAO;
import vo.Dog;

public class DogViewService {
	public Dog getDogView(int id){
		DogDAO dogDAO=DogDAO.getInstance();//�����ͺ��̽� �۾��� ó���� DogDAO ��ü�� ������ �κ�
		Connection con=getConnection();//�����ͺ��̽� �۾��� ���� Connection ��ü ����
		dogDAO.setConnection(con);//�����ͺ��̽� �۾��� ���� Connection ��ü�� DogDAO�� ��������� ����
		
		int updateCount=dogDAO.updateReadCount(id);//�� ������ ��û�ϴ� �� ��ǰ�� ��ȸ���� ����
		
		if(updateCount>0){//��ȸ�� ���� �������� �� Ʈ����� �۾� �ϼ�
			commit(con);
		}else{
			rollback(con);//��ȸ�� ���� �۾� �������� �� Ʈ����� �۾� ���
		}
		
		Dog dog=dogDAO.selectDog(id);//�Ķ���ͷ� ���۵� id���� ������ �ִ� �� ��ǰ ���� �ϳ��� ������ �κ�
		close(con);
		return dog;
	}
}
