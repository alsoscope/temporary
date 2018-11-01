package dao;

import static db.JdbcUtil.*;
import java.sql.*;
import java.util.ArrayList;
import vo.Dog;

public class DogDAO {
	Connection con;
	private static DogDAO boardDAO;
	
	private DogDAO(){//�������� ���� �����ڸ� �ܺ� Ŭ�������� ���� ȣ�� �� �� ���� private, �ܺ� Ŭ�������� DogDAO ��ü�� �޼ҵ带 ����� �� ���� ��ü�� ��� �����ϰ� ���� �ʰ�
		
	}//�̱��� ������ ����ؼ� ó�� ������ ��ü�� �����ؼ� ���.
	
	public void setConnection(Connection con){//�����ͺ��̽� �۾��� �ϱ� ���ؼ� �ʿ��� Connection ��ü�� ��� ���� ������ �����ϴ� �޼ҵ�
		this.con=con;
	}
	
	public static DogDAO getInstance(){//�ܺ� Ŭ�������� DogDAO Ŭ������ ���ǵǾ� �ִ� �޼ҵ带 ����ϱ� ���ؼ� ��ü�� �� �� ù ��° ��û������ ��ü�� ������ �ְ�
		if(boardDAO==null){
			boardDAO=new DogDAO();
		}
		
		return boardDAO;//������ ��ü�� ����� ���� ó�� ������ ��ü�� ���۷��� ���� �����ϰ� ���ִ� �޼ҵ�
		//�������� ���� �����ڸ� private���� ���������Ƿ� �ܺ� Ŭ�������� DogDAO ��ü�� ���۷��� ���� �� ���� �ݵ�� getInstance() �޼ҵ带 ȣ���ؼ� ������
	}
	
	public ArrayList<Dog> selectDogList(){//�����ͺ��̽��� ����Ǿ� �ִ� ��� �� ��ǰ ������ ��ȯ�ϴ� �޼ҵ� ����
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Dog> dogList=null;
		
		try{
			pstmt=con.prepareStatement("SELECT * FROM dog");
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dogList=new ArrayList<Dog>();
				
				do{
					dogList.add(new Dog(rs.getInt("id"), rs.getString("kind"), rs.getInt("price"), rs.getString("image"), rs.getString("country")
							,rs.getInt("height"), rs.getInt("weight"), rs.getString("content"), rs.getInt("readcount")));
				}while(rs.next());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}
		return dogList;
	}
	
	public Dog selectDog(int id){//�����ͺ��̽��� ����Ǿ� �ִ� �� ��ǰ ���� �� �Ķ���ͷ� ���۵� id���� ������ �ִ� �� ��ǰ�� ������ ��ȯ�ϴ� �޼ҵ� ����
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Dog dog=null;
		
		
		try{
			pstmt=con.prepareStatement("SELECT * FROM dog WHERE id=?");
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dog=new Dog(rs.getInt("id"), rs.getString("kind"), rs.getInt("price"), rs.getString("image"), rs.getString("country")
						,rs.getInt("height"), rs.getInt("weight"), rs.getString("content"), rs.getInt("readcount"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}
		return dog;
	}
	
	public int updateReadCount(int id){//�� ���� ���� ��û�� �� �� ��ǰ ������ ��ȸ���� ������Ű�� �޼ҵ� ����
		PreparedStatement pstmt=null;
		int updateCount=0;
		String sql="";
		
		try{
			sql="UPDATE dog SET readcount=readcount+1 WHERE id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, id);
			updateCount=pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		return updateCount;
	}
	
	public int insertDog(Dog dog){//���ο� �� ��ǰ ������ �����ͺ��̽��� �߰��ϴ� �޼ҵ� ����
		PreparedStatement pstmt=null;
		int insertCount=0;
		String sql="";
		
		try{
			sql="INSERT INTO dog VALUES(0,?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dog.getKind());
			pstmt.setInt(2, dog.getPrice());
			pstmt.setString(3, dog.getImage());
			pstmt.setString(4, dog.getCountry());
			pstmt.setInt(5, dog.getHeight());
			pstmt.setInt(6, dog.getWeight());
			pstmt.setString(7, dog.getContent());
			pstmt.setInt(8, dog.getReadcount());
			insertCount=pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		return insertCount;
	}
	
}//DogDAO
