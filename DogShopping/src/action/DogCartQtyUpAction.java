package action;

//��ٱ��� �׸� ���� ���� ��û�� ó���ϴ� Action Ŭ����

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartQtyUpService;
import vo.ActionForward;

public class DogCartQtyUpAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String kind=request.getParameter("kind");//������ ������ų ����� �Ǵ� ��ٱ��� �׸��� kind���� �Ķ���� ������ �޴� �κ�. ��ٱ��� �׸��� �ĺ��ڴ� kind���� ���.
		DogCartQtyUpService dogCartQtyUpService=new DogCartQtyUpService();//��ٱ��� �׸��� ������ ������Ű�� ����Ͻ� ������ ó���ϴ� ���� ��ü�� ����
		dogCartQtyUpService.upCartQty(kind, request);//��ٱ��� �׸��� ������ ������Ű�� �޼ҵ� ȣ��
		ActionForward forward=new ActionForward("dogCartList.do", true);//��ٱ��� �׸��� ������ ������Ű�� ��û�� ó���� �� ������ ������ ActionForward ��ü�� ����.
		//��ٱ��� �׸��� �������� ó�� �� ��ٱ��� �׸� ��Ϻ��� ��û�� �ٽ� �ϱ� ���� URL�� dogCartList.do ����, ������ ��� �����̷�Ʈ, true
		return forward;
	}
}
