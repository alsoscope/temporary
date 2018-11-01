package svc;

//새로운 장바구니 항목을 추가하는 요청을 처리하는 비즈니스 로직을 구현하눈 Service 클래스

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.DogDAO;
import vo.Cart;
import vo.Dog;

public class DogCartAddService {
	public Dog getCartDog(int id){ //파라미터 값으로 전송된 id값을 가지고 있는 개 상품 정보를 얻어오는 메소드 정의
		Connection con=getConnection();//데이터베이스 작업에 사용될 Connection 객체 얻어옴
		DogDAO dogDAO=DogDAO.getInstance();//데이터베이스 작업을 처리할 DogDAO 객체를 얻어오는 부분
		dogDAO.setConnection(con);//데이터베이스 작업에 사용될 Connection 객체를 DogDAO의 멤버변수로 삽입

		Dog dog=dogDAO.selectDog(id);
		close(con);
		return dog;
	}
	
	//장바구니 항목을 추가하는 기능이 구현된 메소드 정의
	public void addCart(HttpServletRequest request, Dog cartDog){
		HttpSession session=request.getSession();//요청을 한 클라이언트의 세션 영역 객체를 얻어오는 부분
		ArrayList<Cart> cartList=(ArrayList<Cart>)session.getAttribute("cartList");//현재 세션 영역에 저장되어 있는 장바구니 목록을 얻어오는 부분
		
		if(cartList==null){//요청시 아직 세션영역에 장바구니 목록 객체가 존재하지않으면, 즉, 장바구니 요청을 처음 실행하는 경우에는
			cartList=new ArrayList<Cart>();//장바구니 항목을 요소로 추가할 ArrayList 객체를 생성해서
			session.setAttribute("cartList", cartList);//해당 객체를 세션 영역의 속성으로 공유해주는 부분
		}
		
		//요청에 의해서 추가되는 장바구니 항목이 장바구니 항목 목록에 이미 존재하는 항목인지를 판단하는 변수를 정의.
		//isNewCart 변수의 기본 값을 true로 지정하여 기본적으로 요청해서 지정한 항목이 처음으로 추가되는 장바구니 항목으로 지정되게 함
		boolean isNewCart=true; //지금 장바구니에 담는 항목이 새로 추가되는 항목인지를 저장할 변수
		
		for(int i=0; i<cartList.size(); i++){
			if(cartDog.getKind().equals(cartList.get(i).getKind())){//각 장바구니 항목 데이터의 식별자를 kind 값으로 사용하기 때문에
				//새로 추가하는 상품(cartDog)의 kind값을 가지고 있는 cart객체가 존재하면 새로 추가하려는 상품의 장바구니 항목이 존재한다고 판단.
				
				isNewCart=false;
				cartList.get(i).setQty(cartList.get(i).getQty()+1);//새로 장바구니에 담는 개 상품의 장바구니 항목 개수를 증가
				break;
			}
		}//새로 추가할 장바구니 항목이 기존 장바구니 항목 목록(cartList)에 존재하는지를 판단하여 기존에 존재하는 장바구니 항목이면 isNewCart값을  false로 변경
		//기존 장바구니 항목의 수량을 하나 증가
		
		if(isNewCart){
			Cart cart=new Cart();
			cart.setImage(cartDog.getImage());
			cart.setKind(cartDog.getKind());
			cart.setPrice(cartDog.getPrice());
			cart.setQty(1);
			cartList.add(cart);
		}//장바구니 담기 요청을 한 개 상품의 장바구니 항목으로 존재하지 않으면 장바구니 항목을 저장하는 cartList 객체에 새로운 cart객체를 생성하여 추가하는 부분
	}
}
