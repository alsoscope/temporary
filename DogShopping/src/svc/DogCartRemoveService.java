package svc;

//��ٱ��� �׸� ���� ��û�� ó���ϴ� ����Ͻ� ������ �����ϴ� Service Ŭ����

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import vo.Cart;

public class DogCartRemoveService {
	public void cartRemove (HttpServletRequest request, String[] kindArray){//��û�� ���� ���� ��ü�� ������ �κ�
		HttpSession session=request.getSession();//���� �������� ��ٱ��� ��� ��ü�� ������ �κ�
		ArrayList<Cart> cartList=(ArrayList<Cart>)session.getAttribute("cartList");
		
		for(int i=0; i<kindArray.length; i++){//Ŭ���̾�Ʈ�� ������ ������� ������ �׸��� kind������ �ݺ��ؼ� ó���ϴ� �κ�
			for(int j=0; j<cartList.size(); j++){//������ �׸��� kind���� �ش� kind���� ������ kind���� ���� ��ٱ��� �׸��� ã�Ƽ� ���� ó���ϴ� �κ�
				if(cartList.get(j).getKind().equals(kindArray[i])){//������ kind���� ��ٱ��� �׸� kind���� ��
					cartList.remove(cartList.get(j));//������ ��� ��ٱ��� �׸��� ��ٱ��� ��Ͽ��� ����
				}
			}
		}
	}
}
