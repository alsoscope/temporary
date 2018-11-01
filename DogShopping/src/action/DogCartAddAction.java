package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartAddService;
import vo.ActionForward;
import vo.Dog;

public class DogCartAddAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		DogCartAddService dogCartAddService=new DogCartAddService();//장바구니 항목을 추가하는 비즈니스 로직을 처리하는 구현되어 있는 서비스 객체를 생섯ㅇ
		int id=Integer.parseInt(request.getParameter("id"));//장바구니 항목으로 추가될 개 상품의 아이디를 파라미터 값으로 얻어옴
		Dog cartDog=dogCartAddService.getCartDog(id);//장바구니 항목으로 추가될 개 상품 정보 얻어옴
		dogCartAddService.addCart(request, cartDog);//특정 개 상품을 장바구니 항목으로 추가하는 메소드 호출. 세션 영역 객체에 장바구니 항목을 추가해야 하기 때문에 파라미터 값으로 request 객체 던짐
		ActionForward forward=new ActionForward("dogCartList.do", true);
		//세션 객체를 얻어올 때 request.getSession() 메소드를 호출해서 얻어오게 됨. 포워딩에 사용되는 정보를 ActionForward 객체로 생성.
		//장바구니 항목을 추가한 후 장바구니 목록보기 요청을 다시 하기 위해 url을 dogCartList.do로 지정, 리다이렉트 방식으로 포워딩 처리하기 위해 redirect 속성 값을 true
		
		return forward;
	}
}
