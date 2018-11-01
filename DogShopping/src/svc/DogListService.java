package svc;

//개 상품 목록보기 요청을 처리하는 비즈니스 로직을 구현하는 Service 클래스

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.DogDAO;
import vo.Dog;

public class DogListService {
	public ArrayList<Dog> getDogList(){ 
	DogDAO dogDAO=DogDAO.getInstance();//데이터베이스 작업을 처리할 DogDAO 객체를 얻어오는 부분
	Connection con=getConnection();//데이터베이스 작업에 사용될 Connection 객체 얻어옴
	dogDAO.setConnection(con);//데이터베이스 작업에 사용될 Connection 객체를 DogDAO의 멤버변수로 삽입
	
	//개 상품 목록을 ArrayList 객체 타입으로 반환하는 메소드 호출
	ArrayList<Dog> dogList=dogDAO.selectDogList();
	
	close(con);//사용한 Connection 객체 닫아줌
	return dogList;
	}
}
