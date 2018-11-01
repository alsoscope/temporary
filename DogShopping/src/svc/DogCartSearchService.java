package svc;

//장바구니 항목 검색 요청을 처리하는 비즈니스 로직을 구현하는 Service 클래스

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import vo.Cart;

public class DogCartSearchService {
	public ArrayList<Cart> getCartSearchList(int start_money, int end_money, HttpServletRequest request){
		HttpSession session=request.getSession();
		ArrayList<Cart> oldCartList=(ArrayList<Cart>)session.getAttribute("cartList");//세션 영역에 저장되어 있는 장바구니 목록 객체를 얻어오는 부분
		ArrayList<Cart> cartList=new ArrayList<Cart>();//검색된 장바구니 항목을 지정할 새로운 ArrayList 객체를 생성
		
		for(int i=0; i<oldCartList.size(); i++){//장바구니 목록을 반복하면서 검색 범위에 해당하는 장바구니 항목을 찾아서 새로 생성한 ArrayList 객체에 추가
			if(oldCartList.get(i).getPrice()>=start_money&&oldCartList.get(i).getPrice()<=end_money){//장바구니 항목 중 가격이 검색 가격에 해당하는지를 체크
				cartList.add(oldCartList.get(i));
			}
		}
		return cartList;
	}
}
