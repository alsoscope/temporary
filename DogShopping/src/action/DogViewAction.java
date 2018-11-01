package action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogViewService;
import vo.ActionForward;
import vo.Dog;

public class DogViewAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		DogViewService dogViewService=new DogViewService();//개 상품 정보 상세 보기 비즈니스 로직을 처리하는 서비스 객체를 생성
		int id=Integer.parseInt(request.getParameter("id"));//상세 정보를 출력할 대상 개 상품의 id값을 파라미터로 받는 부분
		Dog dog=dogViewService.getDogView(id);//파라미터 값으로 전송된 id값을 가지고 있는 개의 정보를 Dog클래스 객체 타입으로 반환
		request.setAttribute("dog", dog);//request영역에 dog객체를 속성으로 겅유
		Cookie todayImageCookie=new Cookie("today"+id, dog.getImage());//개 상품 정보의 이미지 이름 문자열을 today 문자열 뒤에 해당 개 상품의 id값을 연결하여
										//("today"+id) 쿠키 이름을 지정한 후 쿠키 객체를 생성하여 저장
		todayImageCookie.setMaxAge(60*60*24);//오늘 본 상품 이미지를 저장한 쿠키 객체가 클라이언트 시스템에 저장돼있을 기간을 24시간으로 설정
		response.addCookie(todayImageCookie);//응답에 쿠키 객체 추가
		ActionForward forward=new ActionForward("dogView.jsp", false);//포워딩 정보를 ActionForward객체로 생성. 포워딩될 URL은 dogView.jsp, 포워딩 방식은 디스패치
		return forward;
	}
}
