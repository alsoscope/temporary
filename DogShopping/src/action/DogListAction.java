package action;

//개 상품 목록보기 요청을 처리하는 Action 클래스

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
	Cookie[] cookieArray=request.getCookies();//클라이언트에서 넘어온 Cookie 객체들을 배열 형태로 반환. 사이트에서 오늘 본 상품이 있다면 각 상품의 이미지가 쿠키로 저장되어 있음.
	
	if(cookieArray!=null){//요청에 넘어온 쿠키 객체 중 오늘 본 상품 이미지 이름을 저장하고 있는 쿠키 객체를 찾아서 todayImageList ArrayList 객체에 쿠키 객체의 값 즉, 이미지 이름을 요소로 추가.
		for(int i=0; i<cookieArray.length; i++){
			if(cookieArray[i].getName().startsWith("today")){
				todayImageList.add(cookieArray[i].getValue());
			}
		}
	}//특정 상품의 자세한 내용을 볼 때 DogViewAction 클래스에서 내용을 본 상품의 이미지를 today 문자열 뒤에 해당 상품의 아이디를 붙인 이름으로 쿠키 객체에 저장
	
	DogListService dogListService=new DogListService();//개 상품 목록보기 요청을 처리하는 서비스 객체를 생성
	ArrayList<Dog> dogList=dogListService.getDogList();//등록돼있는 개 상품 정보를 ArrayList 타입으로 얻어옴
	request.setAttribute("dogList", dogList);//request 영역에 개 상품 목록 정보를 속성으로 공유
	request.setAttribute("todayImageList", todayImageList);//Request 영역에 오늘 본 개 상품 이미지 목록 정보를 속성으로 공유
	ActionForward forward=new ActionForward("dogList.jsp", false);
	//포워딩 정보를 ActionForward 객체로 생성. 포워딩될 페이지는 dogList.jsp , 방식은 디스패치
	
	return forward;
	}//execute
}//DogListAction
