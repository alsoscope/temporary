package dao;

import static db.JdbcUtil.*;
import java.sql.*;
import java.util.ArrayList;
import vo.Dog;

public class DogDAO {
	Connection con;
	private static DogDAO boardDAO;
	
	private DogDAO(){//생성자의 접근 제한자를 외부 클래스에서 직접 호출 할 수 없게 private, 외부 클래스에서 DogDAO 객체의 메소드를 사용할 때 마다 객체를 계속 생성하게 하지 않고
		
	}//싱글톤 패턴을 사용해서 처음 생성된 객체를 공유해서 사용.
	
	public void setConnection(Connection con){//데이터베이스 작업을 하기 위해서 필요한 Connection 객체를 멤버 변수 값으로 설정하는 메소드
		this.con=con;
	}
	
	public static DogDAO getInstance(){//외부 클래스에서 DogDAO 클래스에 정의되어 있는 메소드를 사용하기 위해서 객체를 얻어갈 때 첫 번째 요청에서만 객체를 생성해 주고
		if(boardDAO==null){
			boardDAO=new DogDAO();
		}
		
		return boardDAO;//다음에 객체를 사용할 때는 처음 생성된 객체의 레퍼런스 값을 공유하게 해주는 메소드
		//생성자의 접근 제한자를 private으로 지정했으므로 외부 클래스에서 DogDAO 객체의 레퍼런스 값을 얻어갈 때는 반드시 getInstance() 메소드를 호출해서 얻어가야함
	}
	
	public ArrayList<Dog> selectDogList(){//데이터베이스에 저장되어 있는 모든 개 상품 정보를 반환하는 메소드 정의
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
	
	public Dog selectDog(int id){//데이터베이스에 저장되어 있는 개 상품 정보 중 파라미터로 전송된 id값을 가지고 있는 개 상품의 정보를 반환하는 메소드 정의
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
	
	public int updateReadCount(int id){//상세 내용 보기 요청이 된 개 상품 정보의 조회수를 증가시키는 메소드 정의
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
	
	public int insertDog(Dog dog){//새로운 개 상품 정보를 데이터베이스에 추가하는 메소드 정의
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
