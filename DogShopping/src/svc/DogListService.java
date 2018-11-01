package svc;

//�� ��ǰ ��Ϻ��� ��û�� ó���ϴ� ����Ͻ� ������ �����ϴ� Service Ŭ����

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.DogDAO;
import vo.Dog;

public class DogListService {
	public ArrayList<Dog> getDogList(){ 
	DogDAO dogDAO=DogDAO.getInstance();//�����ͺ��̽� �۾��� ó���� DogDAO ��ü�� ������ �κ�
	Connection con=getConnection();//�����ͺ��̽� �۾��� ���� Connection ��ü ����
	dogDAO.setConnection(con);//�����ͺ��̽� �۾��� ���� Connection ��ü�� DogDAO�� ��������� ����
	
	//�� ��ǰ ����� ArrayList ��ü Ÿ������ ��ȯ�ϴ� �޼ҵ� ȣ��
	ArrayList<Dog> dogList=dogDAO.selectDogList();
	
	close(con);//����� Connection ��ü �ݾ���
	return dogList;
	}
}
