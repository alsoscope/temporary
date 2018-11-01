package svc;

//각 클라이언트의 요청을 처리하는 비즈니스 로직이 구현되는 Service 클래스들
//개 상품 등록 요청을 처리하는 비즈니스 로직을 구현하는 Service 클래스

import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.DogDAO;
import vo.Dog;

public class DogRegistService {
	public boolean registDog(Dog dog){
		DogDAO dogDAO=DogDAO.getInstance();//데이터베이스 작업을 처리할 DogDAO 객체를 얻어오는 부분
		Connection con=getConnection();//데이터베이스 작업에 사용될 Connection 객체 얻어옴
		dogDAO.setConnection(con);//데이터베이스 작업에 사용될 Connection 객체를 DogDAO의 멤버변수로 삽입
		boolean isRegistSuccess=false;//등록 작업 성공 여부를 저장할 변수 정의
		int insertCount=dogDAO.insertDog(dog);//데이터베이스에 새로운 개 상품 정보를 추가하는 메소드 호출
		
		if(insertCount>0){//등록 작업이 성공했을때 트랜잭션 작업을 완성
			commit(con);
			isRegistSuccess=true;
		}else{
			rollback(con);//등록 작업 실패했을 때 트랜잭션 작업 취소
		}
		close(con);//사용한 Connection 객체 닫아줌
		return isRegistSuccess;
	}
}
