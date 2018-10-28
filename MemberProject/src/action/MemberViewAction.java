package action;

//ȸ�� �� ���� ������ ���⸦ ��û�ϴ� Action Ŭ����

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
		}else{//������ �α��� �����̸� �Ʒ����� viewId�� ���� ���̵� ���� ����� MemberViewService Ŭ������ getMember �޼ҵ带 ȣ���Ͽ� ���̵� �ش��ϴ� ����� ������
			//MemberBean ��ü ���·� ���� �� �Ʒ� request ������ �Ӽ����� ������ �� member_info.jsp �������� ������
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
