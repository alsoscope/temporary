package svc;

//장바구니 목록보기 요청을 처리하는 비즈니스 로직을 구현하는 Service 클래스

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import vo.Cart;

public class DogCartListService {
	public ArrayList<Cart> getCartList(HttpServletRequest request){
		HttpSession session=request.getSession();//요청한 클라이언트의 세션 영역 객체를 얻어오는 부분
		ArrayList<Cart> cartList=(ArrayList<Cart>)session.getAttribute("cartList");//세션 영역에 공유되어 있는 장바구니 목록 객체를 얻어오는 부분
		return cartList;
	}
}
