package action;

//가격으로 장바구니 항목을 검색하는 Action 클래스

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartSearchService;
import vo.ActionForward;
import vo.Cart;

public class DogCartSearchAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		DogCartSearchService dogCartSearchService=new DogCartSearchService();//가격으로 장바구니 항목을 검색하는 비즈니스 로직이 구현되어 있는 서비스 객체 생성
		
		//검색에 사용될 시작 금액과 마지막 금액을 파라미터로 받는 부분
		int startMoney=Integer.parseInt(request.getParameter("startMoney"));
		int endMoney=Integer.parseInt(request.getParameter("endMoney"));
		
		ArrayList<Cart> cartList=dogCartSearchService.getCartSearchList(startMoney, endMoney, request);
		//시작 금액과 마지막 금액 사이에 존재한느 금액을 가지고 있는 상품의 장바구니 항목을 검색하는 메소드 호출
		
		request.setAttribute("cartList", cartList);//검색한 장바구니 항목을 request 영역에 속성으로 공유하는 부분
		request.setAttribute("startMoney", startMoney);//검색에 사용된 시작 금액을 request 영역에 속성으로 공유
		request.setAttribute("endMoney", endMoney);//검색에 사용된 마지막 금액을 request 영역 속성으로 공유
		
		int totalMoney=0;
		int money=0;
		
		for(int i=0; i<cartList.size(); i++){
			money=cartList.get(i).getPrice()*cartList.get(i).getQty();
			totalMoney+=money;
		}
		
		request.setAttribute("totalMoney", totalMoney);
		ActionForward forward=new ActionForward("dogCartList.jsp", false);//검색 요청을 처리 후 포워딩 정보를 ActionForward객체로 생성.
		//포워딩 될 URL로 dogCartList.jsp로 지정하면 포워딩 방식으로 true 지정
		return forward;
	}
}
