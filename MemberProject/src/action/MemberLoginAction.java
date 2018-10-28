package action;

//로그인 요청을 처리하는 Action 클래스

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberLoginService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberLoginAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();//로그인에 성공한 사용자의 아이디 값을 세션 영역에 속성으로 저장하기 위해 session 객체 생성
		MemberBean member=new MemberBean();//로그인 페이지에서 파라미터로 넘어온 아이디, 비밀번호 값을 저장한 MemberBean 객체 생성
		
		//Member 객체에 클라이언트에서 파라미터 값으로 넘어온 아이디, 비밀번호를 속성 값으로 할당
		member.setMEMBER_ID(request.getParameter("MEMBER_ID"));
		member.setMEMBER_PW(request.getParameter("MEMBER_PW"));
		
		MemberLoginService memberLoginService=new MemberLoginService();//로그인 처리를 위한 비즈니스 로직이 구현되어 있는 MemberLoginService 클래스 객체 생성
		boolean loginResult=memberLoginService.login(member);//로그인 요청을 처리하는 login 메소드 호출
		ActionForward forward=null;//최종적으로 메소드에서 반환해야 되는 ActionForward 객체의 클래스 변수를 정의
		
		if(loginResult){
			forward=new ActionForward();
			session.setAttribute("id", member.getMEMBER_ID());
			forward.setRedirect(true);
			forward.setPath("./memberListAction.do");
		}else{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 실패')");
			out.println("location.href='./memberLogin.do';");
			out.println("</script>");
		}
		return forward;
	}
}
