package action;

//�� Ŭ���̾�Ʈ�� ��û�� ó���ϴ� Action Ŭ������
//ȸ������ ��û�� ó���ϴ� Action Ŭ����

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.MemberJoinService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberJoinAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		MemberBean member=new MemberBean();//ȸ������ ���������� ����ڰ� �ۼ��� ������ �Ӽ� ������ ������ MemberBean ��ü�� �����ϴ� �κ�
		boolean joinResult=false;//ȸ�� ���� ��û ó�� ���� ���θ� ������ ����
		
		//ȸ������ ���������� ���۵� ȸ���� ������ member ��ü�� �Ӽ� ������ �Ҵ��ϴ� �κ�
		member.setMEMBER_ID(request.getParameter("MEMBER_ID"));
		member.setMEMBER_PW(request.getParameter("MEMBER_PW"));
		member.setMEMBER_NAME(request.getParameter("MEMBER_NAME"));
		member.setMEMBER_AGE(Integer.parseInt(request.getParameter("MEMBER_AGE")));
		member.setMEMBER_GENDER(request.getParameter("MEMBER_GENDER"));
		member.setMEMBER_EMAIL(request.getParameter("MEMBER_EMAIL"));
		
		MemberJoinService memberJoinService=new MemberJoinService();//ȸ������ ����Ͻ� ������ ó���� MemberJoinService ��ü ����
		joinResult=memberJoinService.joinMember(member);//ȸ������ ��û�� ó���ϴ� joinMember �޼ҵ带 ȣ��
		ActionForward forward=null;
		
		//ȸ������ �������� ��
		if(joinResult==false){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('ȸ����Ͻ���')");
			out.println("history.back()");
			out.println("</script>");
		}else{//ȸ������ ��û �����ϸ� �α��� ��û�� �ϱ� ���ؼ� memberLogin.do URL��û
			forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./memberLogin.do");
		}
		return forward;
	}
}
