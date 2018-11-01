package svc;

//��ٱ��� �׸� �˻� ��û�� ó���ϴ� ����Ͻ� ������ �����ϴ� Service Ŭ����

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import vo.Cart;

public class DogCartSearchService {
	public ArrayList<Cart> getCartSearchList(int start_money, int end_money, HttpServletRequest request){
		HttpSession session=request.getSession();
		ArrayList<Cart> oldCartList=(ArrayList<Cart>)session.getAttribute("cartList");//���� ������ ����Ǿ� �ִ� ��ٱ��� ��� ��ü�� ������ �κ�
		ArrayList<Cart> cartList=new ArrayList<Cart>();//�˻��� ��ٱ��� �׸��� ������ ���ο� ArrayList ��ü�� ����
		
		for(int i=0; i<oldCartList.size(); i++){//��ٱ��� ����� �ݺ��ϸ鼭 �˻� ������ �ش��ϴ� ��ٱ��� �׸��� ã�Ƽ� ���� ������ ArrayList ��ü�� �߰�
			if(oldCartList.get(i).getPrice()>=start_money&&oldCartList.get(i).getPrice()<=end_money){//��ٱ��� �׸� �� ������ �˻� ���ݿ� �ش��ϴ����� üũ
				cartList.add(oldCartList.get(i));
			}
		}
		return cartList;
	}
}
