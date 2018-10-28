package db;

import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//데이터베이스 작업시 공통적으로 사용하는 메소드를 정의한 클래스
public class JdbcUtil {
	public static Connection getConnection(){//Connection Pool에서 Connection 객체를 얻어와서 반환하는 메소드 정의
		Connection con=null;
		try{
			Context initCtx=new InitialContext(); //톰캣 자체의 컨텍스트를 얻어옴
			Context envCtx=(Context)initCtx.lookup("java:comp/env");//Resource 정의에 관한 컨텍스트를 얻어오는 부분
			//lookup 메소드의 반환 타입이 Object이므로, Context타입으로 다운 캐스팅
			
			DataSource ds=(DataSource)envCtx.lookup("jdbc/mysql");//context.xml에 정의한 DataSource 객체를 얻어오는 부분
			//server.xml 또는 context.xml, web.xml 에서 설정해놓은 이름 "jdbc/mysql"
			
			//Context ct2=new InitialContext();
			//DataSource ds2=(DataSource)ct.lookup("java:comp/env/jdbc");
			//위 줄을 이렇게 쓸 수도 있음
			
			con=ds.getConnection();//Connection Pool에서 Connection 객체 얻기
			con.setAutoCommit(false);//Connection 객체에 트랜잭션 적용
			System.out.println("connect success");
		}catch(Exception e){
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close(Connection con){
		try{
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}//close()------
	
	public static void close(Statement stmt){
		try{
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}//close()------
	
	public static void close(ResultSet rs){
		try{
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}//close()------
	
	public static void commit(Connection con){//트랜잭션 중에 실행된 작업들을 완료시키는 기능을 하는 메소드 정의
		try{
			con.commit();
			System.out.println("commit success");
		}catch(Exception e){
			e.printStackTrace();
		}
	}//commit()------
	
	public static void rollback(Connection con){//트랜잭션 중에 실행된 작업들을 취소시키는 기능을 하는 메소드 정의
		try{
			con.rollback();
			System.out.println("rollback success");
		}catch(Exception e){
			e.printStackTrace();
		}
	}//rollback()------
}//JdbcUtil