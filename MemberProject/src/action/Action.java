package action;

import javax.servlet.http.*;
import vo.ActionForward;

//Action Ŭ�������� �԰��� ������ Action �������̽�

public interface Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
	//��� Action Ŭ�����鿡�� �����ؾ� �� execute �޼ҵ带 ������ �κ�
}
