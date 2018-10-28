package dao;

//����Ŭ �����ͺ��̽��� SQL ������ �����ϴ� Ŭ����

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
	
	//ó�� getInstance �޼ҵ� ��û �ø� MemberDAO ��ü�� ����, �� ��° ��û���ʹ� �̹� ������ MemberDAO ��ü(instance)�� ��ȯ�ϰ� ����. singletone ���� ������ �κ�.
	public static MemberDAO getInstance(){
		if(instance==null){
			instance=new MemberDAO();
		}
		return instance;
	}
	
	public void setConnection(Connection con){//MemberDAO ��ü�� Connection ��ü�� �����ϴ� �޼ҵ�
		this.con=con;
	}
	
	public String selectLoginId(MemberBean member){//�α��ο� ������ ������� ���̵� ��ȯ�ϴ� �޼ҵ� ����
		String loginId=null;
		String sql="SELECT MEMBER_ID FROM MEMBER WHERE MEMBER_ID=? AND MEMBER_PW=?";//����ڰ� �Է��� id,pw�� ���� ȸ���� ���̵� ��ȸ�ϴ� sql���� ����
		
		try{
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member.getMEMBER_ID());
			pstmt.setString(2, member.getMEMBER_PW());
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				loginId=rs.getString("MEMBER_ID");
			}
		}catch(Exception e){
			System.out.println("����:"+e);
		}finally{
			close(rs);
			close(pstmt);
		}
		return loginId;
	}
	
	public int insertMember(MemberBean member){//���ο� ȸ�� ���� �ϳ��� member ���̺� �����ϴ� �޼ҵ� ����
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
			System.out.println("joinMember ����:"+e);
		}finally{
			close(pstmt);
		}
		return insertCount;//member���̺� ���Ե� ���ڵ� ���� ��ȯ
	}
	
	public ArrayList<MemberBean> selectMemberList(){//member ���̺� �����ϴ� ��� ȸ�� ������ ArrayList<MemberBean>Ÿ������ ��ȯ�ϴ� �޼ҵ� ����
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
			System.out.println("getDetailMember ����:"+e);
		}finally{
			close(rs);
			close(pstmt);
		}
		return memberList;
	}
	
	public MemberBean selectMember(String id){//���ڷ� �Ѿ�� id�� ������ �ִ� ȸ���� ������ MemberBean Ÿ������ ��ȯ�ϴ� �޼ҵ�
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
			System.out.println("getDetailMember ����:"+e);
		}finally{
			close(rs);
			close(pstmt);
		}
		return mb;
	}
	
	public int deleteMember(String id){//���ڷ� �Ѿ�� id�� ������ �ִ� ȸ���� ������ member ���̺��� �����ϴ� �޼ҵ� ����
		String sql="DELETE FROM MEMBER WHERE MEMBER_ID=?";
		int deleteCount=0;
		
		try{
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);

			deleteCount=pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println("deleteMember ����:"+e);
		}finally{
			close(pstmt);
		}
		return deleteCount;
	}
}//MemberDAO
