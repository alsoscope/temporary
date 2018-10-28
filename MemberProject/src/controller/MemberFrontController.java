package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.MemberDeleteAction;
import action.MemberJoinAction;
import action.MemberListAction;
import action.MemberLoginAction;
import action.MemberViewAction;
import vo.ActionForward;

@WebServlet("*.do")
public class MemberFrontController extends javax.servlet.http.HttpServlet {
	static final long serialVersionUID=1L;
	
	//Ŭ���̾�Ʈ���� �Ѿ���� ��� ��û�� ó���ϴ� ������ ���ǵǴ� �޼ҵ带 doProcess��� �̸����� ����
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
		//��û ���� �Ǵ�
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		
		ActionForward forward=null;
		Action action=null;
		
		//Ŭ���̾�Ʈ���� �Ѿ�� �� ��û�� ó���ϴ� Action Ŭ�������� execute �޼ҵ� ȣ��
		if(command.equals("/memberLogin.do")){
			forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./loginForm.jsp");
		}else if(command.equals("/memberJoin.do")){
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./joinForm.jsp");
		}else if(command.equals("/memberLoginAction.do")){
			action=new MemberLoginAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/memberJoinAction.do")){
			action=new MemberJoinAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/memberListAction.do")){
			action=new MemberListAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/memberViewAction.do")){
			action=new MemberViewAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/memberDeleteAction.do")){
			action=new MemberDeleteAction();
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(forward!=null){//�� �������� ���������ִ� �κ�
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
	//Ŭ���̾�Ʈ���� ��û�� get ������� �Ѿ���� post ������� �Ѿ���� ��û�� ���������� ó���ϴ� doProcess �޼ҵ带 ȣ���ϴ� �κ�
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doProcess(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");//��û�� post������� �Ѿ���� ���� �ѱ� ó�� ���ش�
		doProcess(request, response);
	}
}//MemberFrontController
