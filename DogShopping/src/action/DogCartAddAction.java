package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartAddService;
import vo.ActionForward;
import vo.Dog;

public class DogCartAddAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		DogCartAddService dogCartAddService=new DogCartAddService();//��ٱ��� �׸��� �߰��ϴ� ����Ͻ� ������ ó���ϴ� �����Ǿ� �ִ� ���� ��ü�� ������
		int id=Integer.parseInt(request.getParameter("id"));//��ٱ��� �׸����� �߰��� �� ��ǰ�� ���̵� �Ķ���� ������ ����
		Dog cartDog=dogCartAddService.getCartDog(id);//��ٱ��� �׸����� �߰��� �� ��ǰ ���� ����
		dogCartAddService.addCart(request, cartDog);//Ư�� �� ��ǰ�� ��ٱ��� �׸����� �߰��ϴ� �޼ҵ� ȣ��. ���� ���� ��ü�� ��ٱ��� �׸��� �߰��ؾ� �ϱ� ������ �Ķ���� ������ request ��ü ����
		ActionForward forward=new ActionForward("dogCartList.do", true);
		//���� ��ü�� ���� �� request.getSession() �޼ҵ带 ȣ���ؼ� ������ ��. �������� ���Ǵ� ������ ActionForward ��ü�� ����.
		//��ٱ��� �׸��� �߰��� �� ��ٱ��� ��Ϻ��� ��û�� �ٽ� �ϱ� ���� url�� dogCartList.do�� ����, �����̷�Ʈ ������� ������ ó���ϱ� ���� redirect �Ӽ� ���� true
		
		return forward;
	}
}
