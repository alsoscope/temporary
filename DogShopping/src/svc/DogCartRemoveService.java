package svc;

//장바구니 항목 삭제 요청을 처리하는 비즈니스 로직을 구현하는 Service 클래스

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import vo.Cart;

public class DogCartRemoveService {
	public void cartRemove (HttpServletRequest request, String[] kindArray){//요청에 대한 세션 객체를 얻어오는 부분
		HttpSession session=request.getSession();//세션 영역에서 장바구니 목록 객체를 얻어오는 부분
		ArrayList<Cart> cartList=(ArrayList<Cart>)session.getAttribute("cartList");
		
		for(int i=0; i<kindArray.length; i++){//클라이언트가 삭제할 대상으로 선택한 항목의 kind값들을 반복해서 처리하는 부분
			for(int j=0; j<cartList.size(); j++){//삭제할 항목의 kind별로 해당 kind값과 동일한 kind값을 가진 장바구니 항목을 찾아서 삭제 처리하는 부분
				if(cartList.get(j).getKind().equals(kindArray[i])){//삭제할 kind값과 장바구니 항목 kind값을 비교
					cartList.remove(cartList.get(j));//삭제할 대상 장바구니 항목을 장바구니 목록에서 제거
				}
			}
		}
	}
}
