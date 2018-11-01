package action;

//장바구니 항목 수량 증가 요청을 처리하는 Action 클래스

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartQtyUpService;
import vo.ActionForward;

public class DogCartQtyUpAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String kind=request.getParameter("kind");//수량을 증가시킬 대상이 되는 장바구니 항목의 kind값을 파라미터 값으로 받는 부분. 장바구니 항목의 식별자는 kind값을 사용.
		DogCartQtyUpService dogCartQtyUpService=new DogCartQtyUpService();//장바구니 항목의 수량을 증가시키는 비즈니스 로직을 처리하는 서비스 객체를 생성
		dogCartQtyUpService.upCartQty(kind, request);//장바구니 항목의 수량을 증가시키는 메소드 호출
		ActionForward forward=new ActionForward("dogCartList.do", true);//장바구니 항목의 수량을 증가시키는 요청을 처리한 후 포워딩 정보를 ActionForward 객체로 생성.
		//장바구니 항목의 수량증가 처리 후 장바구니 항목 목록보기 요청을 다시 하기 위해 URL을 dogCartList.do 지정, 포워딩 방식 리다이렉트, true
		return forward;
	}
}
