package action;

//��ٱ��� �׸��� ������ ���ҽ�Ű�� ��û�� ó���ϴ� Action Ŭ����

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartQtyDownService;
import vo.ActionForward;

public class DogCartQtyDownAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String kind=request.getParameter("kind");//������ ���ҽ�ų ����� �Ǵ� ��ٱ��� �׸��� kind���� �Ķ���� ������ �޴� �κ�. ��ٱ��� �׸� �ĺ��ڴ� kind ��
		DogCartQtyDownService dogCartQtyDownService=new DogCartQtyDownService();//��ٱ��� �׸��� ������ ���ҽ�Ű�� ����Ͻ� ������ ó���ϴ� ���� ��ü ����
		dogCartQtyDownService.downCartQty(kind, request);//��ٱ��� �׸��� ������ ���ҽ�Ű�� �޼ҵ� ȣ��
		ActionForward forward=new ActionForward("dogCartList.do", true);
		//��ٱ��� �׸��� ������ ���ҽ�Ű�� ��û�� ó���� �� ������ ������ ActionForward ��ü�� �����ϴ� �κ�. ��ٱ��� �׸��� ���� ���� ó�� �� ��ٱ��� �׸� ��Ϻ��� ��û�� �ٽ� �ϱ� ���ؼ�
		//url�� dogCartList.do�� ����, ������ ����� �����̷�Ʈ�� �ϱ� ���� true ����
		return forward;
	}
}
