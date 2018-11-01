package action;

//Action 클래스들의 규격을 정의한 Action 인터페이스
//각 요청을 처리하는 Action 클래스들을 다형성을 이용해서 동일한 타입으로 참조하기 위해서 각 Action 클래스들이 구현할 Action 인터페이스를 설계

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.ActionForward;

public interface Action {
	ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
	//각 요청을 처리하눈 Action 클래스들이 공통적으로 구현해야 하는 execute 메소드를 정의한 부분.
	//웹 요청을 처리하고 응답하기 위해서 HttpServletRequest request와 HttpServletResponse response 를 파라미터 변수로 처리
}
