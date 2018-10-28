package action;

//��ü ȸ�� ����� �����ִ� ��û�� ó���ϴ� Action Ŭ����

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
		HttpSession session=request.getSession();//���� admin ����ڰ� �α��ε� ���������� �˾ƺ��� ���� session ��ü ���� session��ü�� �����Ǿ� �ִ� id �Ӽ� ����
		//admin�̸� ���� �����ڷ� �α��ε� ����. ��ü ȸ�� ����� �����ڸ� �� �� �ְ� ó���ϱ� ���ؼ� ���� �α��ε� ����ڰ� ������������ üũ
		
		String id=(String)session.getAttribute("id");
		ActionForward forward=null;
		
		if(id==null){//���� �α����� ���°� �ƴϸ� �α��� ��û�� �ٽ�.
			forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./memberLogin.do");
		}else if(!id.equals("admin")){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('�����ڰ� �ƴմϴ�')");
			out.println("location.href='./memberLogin.do';");
			out.println("</script>");
		}else{//������ �α��� �����̸� ArrayList<MemberBean> Ÿ������ ��� ������� ������ ���� �Ʒ� request ������ ������ �� member_list.jsp �������� ������
			forward=new ActionForward();
			MemberListService memberListService=new MemberListService();
			ArrayList<MemberBean> memberList=memberListService.getMemberList();
			request.setAttribute("memberList", memberList);
			forward.setPath("./member_list.jsp");
		}
		return forward;
	}
}
