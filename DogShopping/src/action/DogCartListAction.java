package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartListService;
import vo.ActionForward;
import vo.Cart;

public class DogCartListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		DogCartListService dogCartListService=new DogCartListService();//장바구니 목록보기 비즈니스 로직을 처리하는 서비스 클래스 객체 생성
		ArrayList<Cart> cartList=dogCartListService.getCartList(request);//전체 장바구니 목록 ArrayList 타입의 객체로 반환하는 메소드 호출
		//총금액 계산
		int totalMoney=0;//지불해야 하는 총금액을 저장하는 변수 정의
		int money=0;//장바구니 항목 하나에 대한 지불 금액을 저장하는 변수 정의
		
		for(int i=0; i<cartList.size(); i++){//장바구니 항목 목록에 존재하는 전체 상품을 구매하는 데 필요한 총금액을 계산하는 부분
			money=cartList.get(i).getPrice()*cartList.get(i).getQty();//장바구니 항목 하나당의 금액 계산
			totalMoney+=money;//각 장바구니 항목의 금액을 총금액에 더하면서 전체 장바구니 항목의 상품을 구매하기 위해 필요한 총금액 계산
		}
		
		request.setAttribute("totalMoney", totalMoney);//총금액을 request영역에 속성으로 공유
		request.setAttribute("cartList", cartList);
		ActionForward forward=new ActionForward("dogCartList.jsp", false);
		//포워딩에 필요한 정보를 ActionForward 객체로 생성. 포워딩될 URL로 장바구니 목록을 출력해주는 dogCartList.jsp로 지정, 포워딩 방식은 디스패치 방식으로 처리하기 위해 false
		
		return forward;
	}
}
