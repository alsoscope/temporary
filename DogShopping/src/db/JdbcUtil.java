package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//�����ͺ��̽� �۾��� �ݺ������� ����ϴ� ����� ����
public class JdbcUtil {
	public static Connection getConnection(){
		Connection con=null;
		try{
			Context initCtx=new InitialContext(); //���� ȯ���� naming Context ȹ���ϱ�
			Context envCtx=(Context)initCtx.lookup("java:comp/env");
			//InitialContext()�� �� ���ø����̼��� ó������ ��ġ�� �� ��� ������ ��Ʈ���� �ڿ��� JNDI namespace��
			//java:comp/env �κп� ���̰� �ȴ�
			//�׷��� �ڿ� ������ Context ct=new InitialContext(); �̷��� �Ѵ�
			
			//Context envCt=(Context)ct.lookup("java:comp/env");
			//JDNI�� ��ϵ� ������ �ڿ� �̸�(��, ���ؽ�Ʈ�� ���� ������� �̸�)
			
			DataSource ds=(DataSource)envCtx.lookup("jdbc/mysql");
			//server.xml �Ǵ� context.xml, web.xml ���� �����س��� �̸� "jdbc/mysql"
			
			//Context ct2=new InitialContext();
			//DataSource ds2=(DataSource)ct.lookup("java:comp/env/jdbc");
			//�� ���� �̷��� �� ���� ����
			
			con=ds.getConnection();//Connection ���
			con.setAutoCommit(false);
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
	
	public static void commit(Connection con){
		try{
			con.commit();
			System.out.println("commit success");
		}catch(Exception e){
			e.printStackTrace();
		}
	}//commit()------
	
	public static void rollback(Connection con){
		try{
			con.rollback();
			System.out.println("rollback success");
		}catch(Exception e){
			e.printStackTrace();
		}
	}//rollback()------
}//JdbcUtil