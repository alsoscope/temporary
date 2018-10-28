package action;

//�α��� ��û�� ó���ϴ� Action Ŭ����

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberLoginService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberLoginAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();//�α��ο� ������ ������� ���̵� ���� ���� ������ �Ӽ����� �����ϱ� ���� session ��ü ����
		MemberBean member=new MemberBean();//�α��� ���������� �Ķ���ͷ� �Ѿ�� ���̵�, ��й�ȣ ���� ������ MemberBean ��ü ����
		
		//Member ��ü�� Ŭ���̾�Ʈ���� �Ķ���� ������ �Ѿ�� ���̵�, ��й�ȣ�� �Ӽ� ������ �Ҵ�
		member.setMEMBER_ID(request.getParameter("MEMBER_ID"));
		member.setMEMBER_PW(request.getParameter("MEMBER_PW"));
		
		MemberLoginService memberLoginService=new MemberLoginService();//�α��� ó���� ���� ����Ͻ� ������ �����Ǿ� �ִ� MemberLoginService Ŭ���� ��ü ����
		boolean loginResult=memberLoginService.login(member);//�α��� ��û�� ó���ϴ� login �޼ҵ� ȣ��
		ActionForward forward=null;//���������� �޼ҵ忡�� ��ȯ�ؾ� �Ǵ� ActionForward ��ü�� Ŭ���� ������ ����
		
		if(loginResult){
			forward=new ActionForward();
			session.setAttribute("id", member.getMEMBER_ID());
			forward.setRedirect(true);
			forward.setPath("./memberListAction.do");
		}else{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('�α��� ����')");
			out.println("location.href='./memberLogin.do';");
			out.println("</script>");
		}
		return forward;
	}
}
