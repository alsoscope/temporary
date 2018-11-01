package action;

//장바구니 항목 삭제 요청을 처리하는 Action 클래스

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartRemoveService;
import vo.ActionForward;

public class DogCartRemoveAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String[] kindArray=request.getParameterValues("remove");
		//동시에 여러 개의 장바구니 항목을 삭제할 수 있기 때문에 삭제할 장바구니 항목의 kind 파라미터 값을 배열 형태로 받는다.
		//본 프로젝트에서는 목록보기 페이지에서 삭제할 장바구니 항목을 체크 박스 형태로 체크하게 처리된다
		
		DogCartRemoveService dogCartRemoveService=new DogCartRemoveService();
		//장바구니 항목 삭제 비즈니스 로직을 처리하는 서비스 객체를 생성하는 부분
		
		dogCartRemoveService.cartRemove(request, kindArray);
		//장바구니 항목 삭제 요청을 처리하는 메소드 호출. 세션 영역에 공유되어 있는 장바구니 항목 정보를 삭제해야 하기 때문에 세션에 접근하기 위해 request객체를 인자로 던짐.
		
		ActionForward forward=new ActionForward("dogCartList.do", true);
		//장바구니 항목 삭제 요청 처리를 실행한 후 포워딩될 정보를 ActionForward 객체로 생성하는 부분. 장바구니 항목 삭제 처리를 한 후 장바구니 목록보기 요청을 다시 하기 위해
		//URL을 dogCartList.do로 지정, 포워딩 방식은 리다이렉트로 처리하기 위해 true 지정
		
		return forward;
	}
}
