package dao;

//오라클 데이터베이스로 SQL 구문을 전송하는 클래스

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;
import vo.MemberBean;
import static db.JdbcUtil.*;

public class MemberDAO {
	public static MemberDAO instance;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	
	private MemberDAO(){
		
	}
	
	//처음 getInstance 메소드 요청 시만 MemberDAO 객체를 생성, 두 번째 요청부터는 이미 생성된 MemberDAO 객체(instance)를 반환하게 구현. singletone 패턴 구현한 부분.
	public static MemberDAO getInstance(){
		if(instance==null){
			instance=new MemberDAO();
		}
		return instance;
	}
	
	public void setConnection(Connection con){//MemberDAO 객체에 Connection 객체를 주입하는 메소드
		this.con=con;
	}
	
	public String selectLoginId(MemberBean member){//로그인에 성공한 사용자의 아이디를 반환하는 메소드 정의
		String loginId=null;
		String sql="SELECT MEMBER_ID FROM MEMBER WHERE MEMBER_ID=? AND MEMBER_PW=?";//사용자가 입력한 id,pw를 가진 회원의 아이디를 조회하는 sql구문 실행
		
		try{
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member.getMEMBER_ID());
			pstmt.setString(2, member.getMEMBER_PW());
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				loginId=rs.getString("MEMBER_ID");
			}
		}catch(Exception e){
			System.out.println("에러:"+e);
		}finally{
			close(rs);
			close(pstmt);
		}
		return loginId;
	}
	
	public int insertMember(MemberBean member){//새로운 회원 정보 하나를 member 테이블에 삽입하는 메소드 정의
		String sql="INSERT INTO MEMBER VALUES(?,?,?,?,?,?)";
		int insertCount=0;
		
		try{
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member.getMEMBER_ID());
			pstmt.setString(2, member.getMEMBER_PW());
			pstmt.setString(3, member.getMEMBER_NAME());
			pstmt.setInt(4, member.getMEMBER_AGE());
			pstmt.setString(5, member.getMEMBER_GENDER());
			pstmt.setString(6, member.getMEMBER_EMAIL());

			insertCount=pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println("joinMember 에러:"+e);
		}finally{
			close(pstmt);
		}
		return insertCount;//member테이블에 삽입된 레코드 개수 반환
	}
	
	public ArrayList<MemberBean> selectMemberList(){//member 테이블에 존재하는 모든 회원 정보를 ArrayList<MemberBean>타입으로 반환하는 메소드 정의
		String sql="SELECT * FROM MEMBER";
		ArrayList<MemberBean> memberList=new ArrayList<MemberBean>();
		MemberBean mb=null;
		
		try{
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				do{
					mb=new MemberBean();
					mb.setMEMBER_ID(rs.getString("MEMBER_ID"));
					mb.setMEMBER_PW(rs.getString("MEMBER_PW"));
					mb.setMEMBER_NAME(rs.getString("MEMBER_NAME"));
					mb.setMEMBER_AGE(rs.getInt("MEMBER_AGE"));
					mb.setMEMBER_GENDER(rs.getString("MEMBER_GENDER"));
					mb.setMEMBER_EMAIL(rs.getString("MEMBER_EMAIL"));
					memberList.add(mb);
				}while(rs.next());
			}
		}catch(Exception e){
			System.out.println("getDetailMember 에러:"+e);
		}finally{
			close(rs);
			close(pstmt);
		}
		return memberList;
	}
	
	public MemberBean selectMember(String id){//인자로 넘어온 id를 가지고 있는 회원의 정보를 MemberBean 타입으로 반환하는 메소드
		String sql="SELECT * FROM MEMBER WHERE MEMBER_ID=?";
		MemberBean mb=null;
		
		try{
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				mb=new MemberBean();
				mb.setMEMBER_ID(rs.getString("MEMBER_ID"));
				mb.setMEMBER_PW(rs.getString("MEMBER_PW"));
				mb.setMEMBER_NAME(rs.getString("MEMBER_NAME"));
				mb.setMEMBER_AGE(rs.getInt("MEMBER_AGE"));
				mb.setMEMBER_GENDER(rs.getString("MEMBER_GENDER"));
				mb.setMEMBER_EMAIL(rs.getString("MEMBER_EMAIL"));
			}
		}catch(Exception e){
			System.out.println("getDetailMember 에러:"+e);
		}finally{
			close(rs);
			close(pstmt);
		}
		return mb;
	}
	
	public int deleteMember(String id){//인자로 넘어온 id를 가지고 있는 회원의 정보를 member 테이블에서 삭제하는 메소드 정의
		String sql="DELETE FROM MEMBER WHERE MEMBER_ID=?";
		int deleteCount=0;
		
		try{
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);

			deleteCount=pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println("deleteMember 에러:"+e);
		}finally{
			close(pstmt);
		}
		return deleteCount;
	}
}//MemberDAO
