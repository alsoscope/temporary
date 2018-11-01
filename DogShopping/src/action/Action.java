package action;

//Action Ŭ�������� �԰��� ������ Action �������̽�
//�� ��û�� ó���ϴ� Action Ŭ�������� �������� �̿��ؼ� ������ Ÿ������ �����ϱ� ���ؼ� �� Action Ŭ�������� ������ Action �������̽��� ����

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.ActionForward;

public interface Action {
	ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
	//�� ��û�� ó���ϴ� Action Ŭ�������� ���������� �����ؾ� �ϴ� execute �޼ҵ带 ������ �κ�.
	//�� ��û�� ó���ϰ� �����ϱ� ���ؼ� HttpServletRequest request�� HttpServletResponse response �� �Ķ���� ������ ó��
}
