package action;

//전체 회원 목록을 보여주는 요청을 처리하는 Action 클래스

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberListService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberListAction implements Action {
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
		}else{//관리자 로그인 상태이면 ArrayList<MemberBean> 타입으로 모든 사용자의 정보를 얻어와 아래 request 영역에 공유한 후 member_list.jsp 페이지로 포워딩
			forward=new ActionForward();
			MemberListService memberListService=new MemberListService();
			ArrayList<MemberBean> memberList=memberListService.getMemberList();
			request.setAttribute("memberList", memberList);
			forward.setPath("./member_list.jsp");
		}
		return forward;
	}
}
