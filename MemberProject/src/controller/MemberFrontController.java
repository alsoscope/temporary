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
	
	//클라이언트에서 넘어오는 모든 요청을 처리하는 로직이 정의되는 메소드를 doProcess라는 이름으로 정의
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
		//요청 종류 판단
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		
		ActionForward forward=null;
		Action action=null;
		
		//클라이언트에서 넘어온 각 요청을 처리하는 Action 클래스들의 execute 메소드 호출
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
		if(forward!=null){//뷰 페이지로 포워딩해주는 부분
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
	//클라이언트에서 요청이 get 방식으로 넘어오든 post 방식으로 넘어오든 요청을 공통적으로 처리하는 doProcess 메소드를 호출하는 부분
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doProcess(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");//요청이 post방식으로 넘어왔을 때는 한글 처리 해준다
		doProcess(request, response);
	}
}//MemberFrontController
