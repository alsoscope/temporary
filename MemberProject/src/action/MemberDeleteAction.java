package action;

//회원정보 삭제 요청 처리하는 Action 클래스

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberDeleteService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberDeleteAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();	//세션 영역에 공유되어 있는 id 속성 값을 얻어오기 위해 session 객체 얻어옴
		String id=(String)session.getAttribute("id");//현재 세션 영역에 공유되어 있는 id 속성 값을 얻어옴
		ActionForward forward=null;
		
		if(id==null){//현재 로그인한 상태가 아니면 로그인 요청을 다시.
			forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./memberLogin.do");
		}else if(!id.equals("admin")){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('관리자가 아닙니다')");
			out.println("location.href='./memberLogin.do';");
			out.println("</script>");
		}else{
			String deleteId=request.getParameter("id");//사용자가 삭제하기 위해서 선택한 회원의 아이디를 받음
			MemberDeleteService memberDeleteService=new MemberDeleteService();//삭제요청을 처리하는 비즈니스 로직이 구현되어있는 MemberDeleteService 클래스 객체 생성
			boolean deleteResult=memberDeleteService.deleteMember(deleteId);//클라이언트에서 파라미터 값으로 넘어온 아이디를 가지고 있는 회원의 정보를 삭제하는 deleteMember 메소드 호출
			
			if(deleteResult){//회원정보 삭제 작업 성공 시 회원 목록 보기 페이지로 포워딩
				forward=new ActionForward();
				forward.setRedirect(true);
				forward.setPath("./memberListAction.do");
			}else{
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('회원정보 삭제 실패')");
				out.println("location.href='./memberLogin.do';");
				out.println("</script>");
			}
		}
		return forward;
	}
}
