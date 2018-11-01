package action;

//�� Ŭ���̾�Ʈ�� ��û�� ó���ϴ� Action Ŭ����
//DogFrontController ���� ���۵� ��û�� �ľ��Ͽ� �� ��û�� ó���ϴ� ActionŬ���� ��ü�� execute�޼ҵ带 ȣ��

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//cos.jar ���̺귯������ �����Ǵ� ���ε� ���� Ŭ����
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.DogRegistService;
import vo.ActionForward;
import vo.Dog;

public class DogRegistAction implements Action{

@Override
public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
	DogRegistService DogRegistService=new DogRegistService();//������ ������ ���ε� �� ���ε�� ������ ������ �����ͺ��̽��� �����ϴ� ����Ͻ� ������ �����Ǵ� service Ŭ���� ��ü ����
	String realFolder="";//���� ���ε�� ���� ���� �������� ���(���� ��θ� ������ ����
	String saveFolder="/images";//������ ���ε�� ������ ���丮 �̸� ����
	String encType="UTF-8";//���ε�Ǵ� ������ ���ڵ� Ÿ�� ����
	int maxSize=5*1024*1024; //�� ���� ���ε� �� �� �ִ� ������ ũ��. ����Ʈ �� ���� 5Mbye
	
	ServletContext context=request.getServletContext();//������ ���ε�� �������� �������� ��� ������ �κ�
	realFolder=context.getRealPath(saveFolder);//�� ���ø����̼� ��Ʈ�� image ���丮�� �������� ��θ� ����
	MultipartRequest multi=new MultipartRequest(request, realFolder, maxSize, "UTF-8", new DefaultFileRenamePolicy());
	//MultipartRequest Ŭ������ �����ڸ� ����ؼ� ��û�� ���۵Ǿ�� ������ ���� �� ���ε�. �����ڸ� ����ؼ� ��ü�� �����ϴ� ���� �ٷ� ���� �� ������ ���ε��.
	//DefaultFileRenamePolicy Ŭ���� ��ü�� Ŭ���̾�Ʈ�� ���ε��ϴ� ���� �̸��� ������ �̸��� ������ �̹� ���� ���� ���ε� ���丮�� ������ �� ���ε�Ǵ� ���� �̸��� �ڵ����� �����Ͽ� ���ε� �ǰ� �ϴ� ��� ����
	String image=multi.getFilesystemName("image");//���� �� ���ε�� ���� �̸� ����
	
	Dog dog=new Dog(0, multi.getParameter("kind"), Integer.parseInt(multi.getParameter("price")), image, multi.getParameter("nation"),
			Integer.parseInt(multi.getParameter("height")), Integer.parseInt(multi.getParameter("weight")), multi.getParameter("content"),0);
	//Ŭ���̾�Ʈ���� ���۵� �Ķ���� �����͵��� ����ؼ� ���� ��ϵ� �� ������ �����ϴ� Dog ��ü ����
	
	boolean isRegistSuccess=DogRegistService.registDog(dog);//�� ��ǰ ��� ����Ͻ� ������ ó���ϴ� registDog �޼ҵ� ȣ��
	ActionForward forward=null;//���������� �޼ҵ忡�� ��ȯ�ؾ� �Ǵ� ActionForward ��ü�� Ŭ���� ������ ����
	
	//�� ��ǰ ����۾��� ���������� ó���Ǿ��� �� �����̷�Ʈ ������� �� ��Ϻ��� ��û�� ��. ���⼭ �����ؼ� ��ȯ�ϴ� forward��ü�� DogFrontController������
	// forward=action.execute(request,response); �κ����� ��ȯ�Ǿ� �ش� ������ ����Ͽ� ������ ó����.
	if(isRegistSuccess){
		forward=new ActionForward("dogList.do", true);
	}else{ //�� ��ǰ ��� �۾��� ���е��� ��� �ڹ� ��ũ��Ʈ�� ����ؼ� ���ڿ��� ���â���� ����ְ� ���� URL, �� ��ǰ ��� �������� �ǵ��ư�.
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("alert('��Ͻ���')");
		out.println("history.back();");
		out.println("</script>");
	}
	return forward;
	
	}//ActionForward
}//DogRegistAction
