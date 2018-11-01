package action;

//각 클라이언트의 요청을 처리하는 Action 클래스
//DogFrontController 에서 전송된 요청을 파악하여 각 요청을 처리하는 Action클래스 객체의 execute메소드를 호출

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//cos.jar 라이브러리에서 제공되는 업로드 관련 클래스
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.DogRegistService;
import vo.ActionForward;
import vo.Dog;

public class DogRegistAction implements Action{

@Override
public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
	DogRegistService DogRegistService=new DogRegistService();//파일을 서버상에 업로드 후 업로드된 파일의 정보를 데이터베이스에 저장하는 비즈니스 로직이 구현되는 service 클래스 객체 생성
	String realFolder="";//파일 업로드될 서버 상의 물리적인 경로(실제 경로를 저장할 변수
	String saveFolder="/images";//파일이 업로드될 논리적인 디렉토리 이름 정의
	String encType="UTF-8";//업로드되는 파일의 인코딩 타입 정의
	int maxSize=5*1024*1024; //한 번에 업로드 할 수 있는 파일의 크기. 바이트 수 정의 5Mbye
	
	ServletContext context=request.getServletContext();//파일이 업로드될 서버상의 물리적인 경로 얻어오는 부분
	realFolder=context.getRealPath(saveFolder);//웹 어플리케이션 루트의 image 디렉토리의 물리적인 경로를 얻어옴
	MultipartRequest multi=new MultipartRequest(request, realFolder, maxSize, "UTF-8", new DefaultFileRenamePolicy());
	//MultipartRequest 클래스의 생성자를 사용해서 요청에 전송되어온 파일을 서버 상에 업로드. 생성자를 사용해서 객체를 생성하는 순간 바로 서버 상에 파일이 업로드됨.
	//DefaultFileRenamePolicy 클래스 객체는 클라이언트가 업로드하는 파일 이름과 동일한 이름의 파일이 이미 서버 상의 업로드 디렉토리에 존재할 때 업로드되는 파일 이름을 자동으로 변경하여 업로드 되게 하는 기능 제공
	String image=multi.getFilesystemName("image");//서버 상에 업로드된 파일 이름 얻어옴
	
	Dog dog=new Dog(0, multi.getParameter("kind"), Integer.parseInt(multi.getParameter("price")), image, multi.getParameter("nation"),
			Integer.parseInt(multi.getParameter("height")), Integer.parseInt(multi.getParameter("weight")), multi.getParameter("content"),0);
	//클라이언트에서 전송된 파라미터 데이터들을 사용해서 새로 등록될 개 정보를 저장하는 Dog 객체 생성
	
	boolean isRegistSuccess=DogRegistService.registDog(dog);//개 상품 등록 비즈니스 로직을 처리하는 registDog 메소드 호출
	ActionForward forward=null;//최종적으로 메소드에서 반환해야 되는 ActionForward 객체의 클래스 변수를 정의
	
	//개 상품 등록작업이 성공적으로 처리되었을 때 리다이렉트 방식으로 개 목록보기 요청을 함. 여기서 생성해서 반환하는 forward객체가 DogFrontController서블릿의
	// forward=action.execute(request,response); 부분으로 반환되어 해당 정보를 사용하여 포워딩 처리됨.
	if(isRegistSuccess){
		forward=new ActionForward("dogList.do", true);
	}else{ //개 상품 등록 작업이 실패됐을 경우 자바 스크립트를 사용해서 문자열을 경고창으로 띄워주고 이전 URL, 개 상품 등록 페이지로 되돌아감.
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("alert('등록실패')");
		out.println("history.back();");
		out.println("</script>");
	}
	return forward;
	
	}//ActionForward
}//DogRegistAction
