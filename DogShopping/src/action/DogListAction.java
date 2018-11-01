package action;

//�� ��ǰ ��Ϻ��� ��û�� ó���ϴ� Action Ŭ����

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogListService;
import vo.ActionForward;
import vo.Dog;

public class DogListAction implements Action {

@Override
public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
	ArrayList<String> todayImageList=new ArrayList<String>();
	Cookie[] cookieArray=request.getCookies();//Ŭ���̾�Ʈ���� �Ѿ�� Cookie ��ü���� �迭 ���·� ��ȯ. ����Ʈ���� ���� �� ��ǰ�� �ִٸ� �� ��ǰ�� �̹����� ��Ű�� ����Ǿ� ����.
	
	if(cookieArray!=null){//��û�� �Ѿ�� ��Ű ��ü �� ���� �� ��ǰ �̹��� �̸��� �����ϰ� �ִ� ��Ű ��ü�� ã�Ƽ� todayImageList ArrayList ��ü�� ��Ű ��ü�� �� ��, �̹��� �̸��� ��ҷ� �߰�.
		for(int i=0; i<cookieArray.length; i++){
			if(cookieArray[i].getName().startsWith("today")){
				todayImageList.add(cookieArray[i].getValue());
			}
		}
	}//Ư�� ��ǰ�� �ڼ��� ������ �� �� DogViewAction Ŭ�������� ������ �� ��ǰ�� �̹����� today ���ڿ� �ڿ� �ش� ��ǰ�� ���̵� ���� �̸����� ��Ű ��ü�� ����
	
	DogListService dogListService=new DogListService();//�� ��ǰ ��Ϻ��� ��û�� ó���ϴ� ���� ��ü�� ����
	ArrayList<Dog> dogList=dogListService.getDogList();//��ϵ��ִ� �� ��ǰ ������ ArrayList Ÿ������ ����
	request.setAttribute("dogList", dogList);//request ������ �� ��ǰ ��� ������ �Ӽ����� ����
	request.setAttribute("todayImageList", todayImageList);//Request ������ ���� �� �� ��ǰ �̹��� ��� ������ �Ӽ����� ����
	ActionForward forward=new ActionForward("dogList.jsp", false);
	//������ ������ ActionForward ��ü�� ����. �������� �������� dogList.jsp , ����� ����ġ
	
	return forward;
	}//execute
}//DogListAction
