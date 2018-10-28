package action;

import javax.servlet.http.*;
import vo.ActionForward;

//Action 클래스들의 규격을 정의한 Action 인터페이스

public interface Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
	//모든 Action 클래스들에서 구현해야 할 execute 메소드를 정의한 부분
}
