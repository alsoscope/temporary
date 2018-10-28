package action;

//회원 한 명의 상세정보 보기를 요청하는 Action 클래스

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberViewService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberViewAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();//현재 admin 사용자가 로그인된 상태인지를 알아보기 위해 session 객체 얻어옴 session객체에 공유되어 있는 id 속성 값이
		//admin이면 현재 관리자로 로그인된 상태. 전체 회원 목록은 관리자만 볼 수 있게 처리하기 위해서 현재 로그인된 사용자가 관리자인지를 체크
		
		String id=(String)session.getAttribute("id");
		
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
		}else{//관리자 로그인 상태이면 아래에서 viewId로 받은 아이디 값을 사용해 MemberViewService 클래스의 getMember 메소드를 호출하여 아이디에 해당하는 사용자 정보를
			//MemberBean 객체 형태로 받은 후 아래 request 영역에 속성으로 공유한 후 member_info.jsp 페이지로 포워딩
			forward=new ActionForward();
			String viewId=request.getParameter("id");
			MemberViewService memberViewService=new MemberViewService();
			MemberBean member=memberViewService.getMember(viewId);
			request.setAttribute("member", member);
			forward.setPath("./member_info.jsp");
		}
		return forward;
	}
}
