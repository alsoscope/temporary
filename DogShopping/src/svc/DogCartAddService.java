package svc;

//���ο� ��ٱ��� �׸��� �߰��ϴ� ��û�� ó���ϴ� ����Ͻ� ������ �����ϴ� Service Ŭ����

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.DogDAO;
import vo.Cart;
import vo.Dog;

public class DogCartAddService {
	public Dog getCartDog(int id){ //�Ķ���� ������ ���۵� id���� ������ �ִ� �� ��ǰ ������ ������ �޼ҵ� ����
		Connection con=getConnection();//�����ͺ��̽� �۾��� ���� Connection ��ü ����
		DogDAO dogDAO=DogDAO.getInstance();//�����ͺ��̽� �۾��� ó���� DogDAO ��ü�� ������ �κ�
		dogDAO.setConnection(con);//�����ͺ��̽� �۾��� ���� Connection ��ü�� DogDAO�� ��������� ����

		Dog dog=dogDAO.selectDog(id);
		close(con);
		return dog;
	}
	
	//��ٱ��� �׸��� �߰��ϴ� ����� ������ �޼ҵ� ����
	public void addCart(HttpServletRequest request, Dog cartDog){
		HttpSession session=request.getSession();//��û�� �� Ŭ���̾�Ʈ�� ���� ���� ��ü�� ������ �κ�
		ArrayList<Cart> cartList=(ArrayList<Cart>)session.getAttribute("cartList");//���� ���� ������ ����Ǿ� �ִ� ��ٱ��� ����� ������ �κ�
		
		if(cartList==null){//��û�� ���� ���ǿ����� ��ٱ��� ��� ��ü�� ��������������, ��, ��ٱ��� ��û�� ó�� �����ϴ� ��쿡��
			cartList=new ArrayList<Cart>();//��ٱ��� �׸��� ��ҷ� �߰��� ArrayList ��ü�� �����ؼ�
			session.setAttribute("cartList", cartList);//�ش� ��ü�� ���� ������ �Ӽ����� �������ִ� �κ�
		}
		
		//��û�� ���ؼ� �߰��Ǵ� ��ٱ��� �׸��� ��ٱ��� �׸� ��Ͽ� �̹� �����ϴ� �׸������� �Ǵ��ϴ� ������ ����.
		//isNewCart ������ �⺻ ���� true�� �����Ͽ� �⺻������ ��û�ؼ� ������ �׸��� ó������ �߰��Ǵ� ��ٱ��� �׸����� �����ǰ� ��
		boolean isNewCart=true; //���� ��ٱ��Ͽ� ��� �׸��� ���� �߰��Ǵ� �׸������� ������ ����
		
		for(int i=0; i<cartList.size(); i++){
			if(cartDog.getKind().equals(cartList.get(i).getKind())){//�� ��ٱ��� �׸� �������� �ĺ��ڸ� kind ������ ����ϱ� ������
				//���� �߰��ϴ� ��ǰ(cartDog)�� kind���� ������ �ִ� cart��ü�� �����ϸ� ���� �߰��Ϸ��� ��ǰ�� ��ٱ��� �׸��� �����Ѵٰ� �Ǵ�.
				
				isNewCart=false;
				cartList.get(i).setQty(cartList.get(i).getQty()+1);//���� ��ٱ��Ͽ� ��� �� ��ǰ�� ��ٱ��� �׸� ������ ����
				break;
			}
		}//���� �߰��� ��ٱ��� �׸��� ���� ��ٱ��� �׸� ���(cartList)�� �����ϴ����� �Ǵ��Ͽ� ������ �����ϴ� ��ٱ��� �׸��̸� isNewCart����  false�� ����
		//���� ��ٱ��� �׸��� ������ �ϳ� ����
		
		if(isNewCart){
			Cart cart=new Cart();
			cart.setImage(cartDog.getImage());
			cart.setKind(cartDog.getKind());
			cart.setPrice(cartDog.getPrice());
			cart.setQty(1);
			cartList.add(cart);
		}//��ٱ��� ��� ��û�� �� �� ��ǰ�� ��ٱ��� �׸����� �������� ������ ��ٱ��� �׸��� �����ϴ� cartList ��ü�� ���ο� cart��ü�� �����Ͽ� �߰��ϴ� �κ�
	}
}
