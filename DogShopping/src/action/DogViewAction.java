package action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogViewService;
import vo.ActionForward;
import vo.Dog;

public class DogViewAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		DogViewService dogViewService=new DogViewService();//�� ��ǰ ���� �� ���� ����Ͻ� ������ ó���ϴ� ���� ��ü�� ����
		int id=Integer.parseInt(request.getParameter("id"));//�� ������ ����� ��� �� ��ǰ�� id���� �Ķ���ͷ� �޴� �κ�
		Dog dog=dogViewService.getDogView(id);//�Ķ���� ������ ���۵� id���� ������ �ִ� ���� ������ DogŬ���� ��ü Ÿ������ ��ȯ
		request.setAttribute("dog", dog);//request������ dog��ü�� �Ӽ����� ����
		Cookie todayImageCookie=new Cookie("today"+id, dog.getImage());//�� ��ǰ ������ �̹��� �̸� ���ڿ��� today ���ڿ� �ڿ� �ش� �� ��ǰ�� id���� �����Ͽ�
										//("today"+id) ��Ű �̸��� ������ �� ��Ű ��ü�� �����Ͽ� ����
		todayImageCookie.setMaxAge(60*60*24);//���� �� ��ǰ �̹����� ������ ��Ű ��ü�� Ŭ���̾�Ʈ �ý��ۿ� ��������� �Ⱓ�� 24�ð����� ����
		response.addCookie(todayImageCookie);//���信 ��Ű ��ü �߰�
		ActionForward forward=new ActionForward("dogView.jsp", false);//������ ������ ActionForward��ü�� ����. �������� URL�� dogView.jsp, ������ ����� ����ġ
		return forward;
	}
}
