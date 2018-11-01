package svc;

//�� Ŭ���̾�Ʈ�� ��û�� ó���ϴ� ����Ͻ� ������ �����Ǵ� Service Ŭ������
//�� ��ǰ ��� ��û�� ó���ϴ� ����Ͻ� ������ �����ϴ� Service Ŭ����

import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.DogDAO;
import vo.Dog;

public class DogRegistService {
	public boolean registDog(Dog dog){
		DogDAO dogDAO=DogDAO.getInstance();//�����ͺ��̽� �۾��� ó���� DogDAO ��ü�� ������ �κ�
		Connection con=getConnection();//�����ͺ��̽� �۾��� ���� Connection ��ü ����
		dogDAO.setConnection(con);//�����ͺ��̽� �۾��� ���� Connection ��ü�� DogDAO�� ��������� ����
		boolean isRegistSuccess=false;//��� �۾� ���� ���θ� ������ ���� ����
		int insertCount=dogDAO.insertDog(dog);//�����ͺ��̽��� ���ο� �� ��ǰ ������ �߰��ϴ� �޼ҵ� ȣ��
		
		if(insertCount>0){//��� �۾��� ���������� Ʈ����� �۾��� �ϼ�
			commit(con);
			isRegistSuccess=true;
		}else{
			rollback(con);//��� �۾� �������� �� Ʈ����� �۾� ���
		}
		close(con);//����� Connection ��ü �ݾ���
		return isRegistSuccess;
	}
}
