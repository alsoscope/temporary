package svc;

//개 상품 상세 정보보기 요청을 처리하는 비즈니스 로직을 구현하는 Service 클래스

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DogDAO;
import vo.Dog;

public class DogViewService {
	public Dog getDogView(int id){
		DogDAO dogDAO=DogDAO.getInstance();//데이터베이스 작업을 처리할 DogDAO 객체를 얻어오는 부분
		Connection con=getConnection();//데이터베이스 작업에 사용될 Connection 객체 얻어옴
		dogDAO.setConnection(con);//데이터베이스 작업에 사용될 Connection 객체를 DogDAO의 멤버변수로 삽입
		
		int updateCount=dogDAO.updateReadCount(id);//상세 정보를 요청하는 개 상품의 조회수를 증가
		
		if(updateCount>0){//조회수 증가 성공했을 때 트랜잭션 작업 완성
			commit(con);
		}else{
			rollback(con);//조회수 증가 작업 실패했을 때 트랜잭션 작업 취소
		}
		
		Dog dog=dogDAO.selectDog(id);//파라미터로 전송된 id값을 가지고 있는 개 상품 정보 하나를 얻어오는 부분
		close(con);
		return dog;
	}
}
