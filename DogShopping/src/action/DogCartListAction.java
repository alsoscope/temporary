package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartListService;
import vo.ActionForward;
import vo.Cart;

public class DogCartListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		DogCartListService dogCartListService=new DogCartListService();//��ٱ��� ��Ϻ��� ����Ͻ� ������ ó���ϴ� ���� Ŭ���� ��ü ����
		ArrayList<Cart> cartList=dogCartListService.getCartList(request);//��ü ��ٱ��� ��� ArrayList Ÿ���� ��ü�� ��ȯ�ϴ� �޼ҵ� ȣ��
		//�ѱݾ� ���
		int totalMoney=0;//�����ؾ� �ϴ� �ѱݾ��� �����ϴ� ���� ����
		int money=0;//��ٱ��� �׸� �ϳ��� ���� ���� �ݾ��� �����ϴ� ���� ����
		
		for(int i=0; i<cartList.size(); i++){//��ٱ��� �׸� ��Ͽ� �����ϴ� ��ü ��ǰ�� �����ϴ� �� �ʿ��� �ѱݾ��� ����ϴ� �κ�
			money=cartList.get(i).getPrice()*cartList.get(i).getQty();//��ٱ��� �׸� �ϳ����� �ݾ� ���
			totalMoney+=money;//�� ��ٱ��� �׸��� �ݾ��� �ѱݾ׿� ���ϸ鼭 ��ü ��ٱ��� �׸��� ��ǰ�� �����ϱ� ���� �ʿ��� �ѱݾ� ���
		}
		
		request.setAttribute("totalMoney", totalMoney);//�ѱݾ��� request������ �Ӽ����� ����
		request.setAttribute("cartList", cartList);
		ActionForward forward=new ActionForward("dogCartList.jsp", false);
		//�������� �ʿ��� ������ ActionForward ��ü�� ����. �������� URL�� ��ٱ��� ����� ������ִ� dogCartList.jsp�� ����, ������ ����� ����ġ ������� ó���ϱ� ���� false
		
		return forward;
	}
}
