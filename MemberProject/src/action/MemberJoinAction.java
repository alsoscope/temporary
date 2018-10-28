package action;

//각 클라이언트의 요청을 처리하는 Action 클래스들
//회원가입 요청을 처리하는 Action 클래스

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.MemberJoinService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberJoinAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		MemberBean member=new MemberBean();//회원가입 페이지에서 사용자가 작성한 값들을 속성 값으로 저장한 MemberBean 객체를 생성하는 부분
		boolean joinResult=false;//회원 가입 요청 처리 성공 여부를 저장할 변수
		
		//회원가입 페이지에서 전송된 회원의 정보를 member 객체의 속성 값으로 할당하는 부분
		member.setMEMBER_ID(request.getParameter("MEMBER_ID"));
		member.setMEMBER_PW(request.getParameter("MEMBER_PW"));
		member.setMEMBER_NAME(request.getParameter("MEMBER_NAME"));
		member.setMEMBER_AGE(Integer.parseInt(request.getParameter("MEMBER_AGE")));
		member.setMEMBER_GENDER(request.getParameter("MEMBER_GENDER"));
		member.setMEMBER_EMAIL(request.getParameter("MEMBER_EMAIL"));
		
		MemberJoinService memberJoinService=new MemberJoinService();//회원가입 비즈니스 로직을 처리할 MemberJoinService 객체 생성
		joinResult=memberJoinService.joinMember(member);//회원가입 요청을 처리하는 joinMember 메소드를 호출
		ActionForward forward=null;
		
		//회원가입 실패했을 때
		if(joinResult==false){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('회원등록실패')");
			out.println("history.back()");
			out.println("</script>");
		}else{//회원가입 요청 성공하면 로그인 요청을 하기 위해서 memberLogin.do URL요청
			forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./memberLogin.do");
		}
		return forward;
	}
}
