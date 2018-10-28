package action;

//ȸ������ ���� ��û ó���ϴ� Action Ŭ����

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
		HttpSession session=request.getSession();	//���� ������ �����Ǿ� �ִ� id �Ӽ� ���� ������ ���� session ��ü ����
		String id=(String)session.getAttribute("id");//���� ���� ������ �����Ǿ� �ִ� id �Ӽ� ���� ����
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
		}else{
			String deleteId=request.getParameter("id");//����ڰ� �����ϱ� ���ؼ� ������ ȸ���� ���̵� ����
			MemberDeleteService memberDeleteService=new MemberDeleteService();//������û�� ó���ϴ� ����Ͻ� ������ �����Ǿ��ִ� MemberDeleteService Ŭ���� ��ü ����
			boolean deleteResult=memberDeleteService.deleteMember(deleteId);//Ŭ���̾�Ʈ���� �Ķ���� ������ �Ѿ�� ���̵� ������ �ִ� ȸ���� ������ �����ϴ� deleteMember �޼ҵ� ȣ��
			
			if(deleteResult){//ȸ������ ���� �۾� ���� �� ȸ�� ��� ���� �������� ������
				forward=new ActionForward();
				forward.setRedirect(true);
				forward.setPath("./memberListAction.do");
			}else{
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('ȸ������ ���� ����')");
				out.println("location.href='./memberLogin.do';");
				out.println("</script>");
			}
		}
		return forward;
	}
}
