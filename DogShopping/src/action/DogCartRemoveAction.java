package action;

//��ٱ��� �׸� ���� ��û�� ó���ϴ� Action Ŭ����

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartRemoveService;
import vo.ActionForward;

public class DogCartRemoveAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String[] kindArray=request.getParameterValues("remove");
		//���ÿ� ���� ���� ��ٱ��� �׸��� ������ �� �ֱ� ������ ������ ��ٱ��� �׸��� kind �Ķ���� ���� �迭 ���·� �޴´�.
		//�� ������Ʈ������ ��Ϻ��� ���������� ������ ��ٱ��� �׸��� üũ �ڽ� ���·� üũ�ϰ� ó���ȴ�
		
		DogCartRemoveService dogCartRemoveService=new DogCartRemoveService();
		//��ٱ��� �׸� ���� ����Ͻ� ������ ó���ϴ� ���� ��ü�� �����ϴ� �κ�
		
		dogCartRemoveService.cartRemove(request, kindArray);
		//��ٱ��� �׸� ���� ��û�� ó���ϴ� �޼ҵ� ȣ��. ���� ������ �����Ǿ� �ִ� ��ٱ��� �׸� ������ �����ؾ� �ϱ� ������ ���ǿ� �����ϱ� ���� request��ü�� ���ڷ� ����.
		
		ActionForward forward=new ActionForward("dogCartList.do", true);
		//��ٱ��� �׸� ���� ��û ó���� ������ �� �������� ������ ActionForward ��ü�� �����ϴ� �κ�. ��ٱ��� �׸� ���� ó���� �� �� ��ٱ��� ��Ϻ��� ��û�� �ٽ� �ϱ� ����
		//URL�� dogCartList.do�� ����, ������ ����� �����̷�Ʈ�� ó���ϱ� ���� true ����
		
		return forward;
	}
}
