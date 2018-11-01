package action;

//�������� ��ٱ��� �׸��� �˻��ϴ� Action Ŭ����

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartSearchService;
import vo.ActionForward;
import vo.Cart;

public class DogCartSearchAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		DogCartSearchService dogCartSearchService=new DogCartSearchService();//�������� ��ٱ��� �׸��� �˻��ϴ� ����Ͻ� ������ �����Ǿ� �ִ� ���� ��ü ����
		
		//�˻��� ���� ���� �ݾװ� ������ �ݾ��� �Ķ���ͷ� �޴� �κ�
		int startMoney=Integer.parseInt(request.getParameter("startMoney"));
		int endMoney=Integer.parseInt(request.getParameter("endMoney"));
		
		ArrayList<Cart> cartList=dogCartSearchService.getCartSearchList(startMoney, endMoney, request);
		//���� �ݾװ� ������ �ݾ� ���̿� �����Ѵ� �ݾ��� ������ �ִ� ��ǰ�� ��ٱ��� �׸��� �˻��ϴ� �޼ҵ� ȣ��
		
		request.setAttribute("cartList", cartList);//�˻��� ��ٱ��� �׸��� request ������ �Ӽ����� �����ϴ� �κ�
		request.setAttribute("startMoney", startMoney);//�˻��� ���� ���� �ݾ��� request ������ �Ӽ����� ����
		request.setAttribute("endMoney", endMoney);//�˻��� ���� ������ �ݾ��� request ���� �Ӽ����� ����
		
		int totalMoney=0;
		int money=0;
		
		for(int i=0; i<cartList.size(); i++){
			money=cartList.get(i).getPrice()*cartList.get(i).getQty();
			totalMoney+=money;
		}
		
		request.setAttribute("totalMoney", totalMoney);
		ActionForward forward=new ActionForward("dogCartList.jsp", false);//�˻� ��û�� ó�� �� ������ ������ ActionForward��ü�� ����.
		//������ �� URL�� dogCartList.jsp�� �����ϸ� ������ ������� true ����
		return forward;
	}
}
