package svc;

//��ٱ��� ��Ϻ��� ��û�� ó���ϴ� ����Ͻ� ������ �����ϴ� Service Ŭ����

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import vo.Cart;

public class DogCartListService {
	public ArrayList<Cart> getCartList(HttpServletRequest request){
		HttpSession session=request.getSession();//��û�� Ŭ���̾�Ʈ�� ���� ���� ��ü�� ������ �κ�
		ArrayList<Cart> cartList=(ArrayList<Cart>)session.getAttribute("cartList");//���� ������ �����Ǿ� �ִ� ��ٱ��� ��� ��ü�� ������ �κ�
		return cartList;
	}
}
