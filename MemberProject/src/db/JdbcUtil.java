package db;

import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//�����ͺ��̽� �۾��� ���������� ����ϴ� �޼ҵ带 ������ Ŭ����
public class JdbcUtil {
	public static Connection getConnection(){//Connection Pool���� Connection ��ü�� ���ͼ� ��ȯ�ϴ� �޼ҵ� ����
		Connection con=null;
		try{
			Context initCtx=new InitialContext(); //��Ĺ ��ü�� ���ؽ�Ʈ�� ����
			Context envCtx=(Context)initCtx.lookup("java:comp/env");//Resource ���ǿ� ���� ���ؽ�Ʈ�� ������ �κ�
			//lookup �޼ҵ��� ��ȯ Ÿ���� Object�̹Ƿ�, ContextŸ������ �ٿ� ĳ����
			
			DataSource ds=(DataSource)envCtx.lookup("jdbc/mysql");//context.xml�� ������ DataSource ��ü�� ������ �κ�
			//server.xml �Ǵ� context.xml, web.xml ���� �����س��� �̸� "jdbc/mysql"
			
			//Context ct2=new InitialContext();
			//DataSource ds2=(DataSource)ct.lookup("java:comp/env/jdbc");
			//�� ���� �̷��� �� ���� ����
			
			con=ds.getConnection();//Connection Pool���� Connection ��ü ���
			con.setAutoCommit(false);//Connection ��ü�� Ʈ����� ����
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
	
	public static void commit(Connection con){//Ʈ����� �߿� ����� �۾����� �Ϸ��Ű�� ����� �ϴ� �޼ҵ� ����
		try{
			con.commit();
			System.out.println("commit success");
		}catch(Exception e){
			e.printStackTrace();
		}
	}//commit()------
	
	public static void rollback(Connection con){//Ʈ����� �߿� ����� �۾����� ��ҽ�Ű�� ����� �ϴ� �޼ҵ� ����
		try{
			con.rollback();
			System.out.println("rollback success");
		}catch(Exception e){
			e.printStackTrace();
		}
	}//rollback()------
}//JdbcUtil