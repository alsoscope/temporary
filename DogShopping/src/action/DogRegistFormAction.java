package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class DogRegistFormAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ActionForward forward=new ActionForward("dogRegistForm.jsp", false);
		return forward;
		//DogRegistFormAction 클래스 객체는 특별한 비즈니스 로직을 처리할 필요가 없기 때문에 바로 ActionForward 객체를 생성해서 dogRegistForm.jsp로 포워딩 처리
	}
}
