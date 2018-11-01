package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;
import action.Action;
import action.DogCartAddAction;
import action.DogCartListAction;
import action.DogCartQtyDownAction;
import action.DogCartQtyUpAction;
import action.DogCartRemoveAction;
import action.DogCartSearchAction;
import action.DogListAction;
import action.DogRegistAction;
import action.DogRegistFormAction;
import action.DogViewAction;

@WebServlet("*.do")
public class DogFrontController extends HttpServlet{
	//DogFrontController 서블릿으로 들어오는 모든 요청에서는 doProcess메소드 호출하게 됨
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1.요청파악
		request.setCharacterEncoding("UTF-8");

		//전송된 요청이 어떤 요청인지는 URL을 이용하여 파악
		String RequestURI=request.getRequestURI();
		//요청URL:http://localhost:8080/boardProject/boardWriteForm.do
		//requestURI:/boardProject/boardWriteForm.do 반환
		
		String contextPath=request.getContextPath();//   /boardProject 바놘
		String command=RequestURI.substring(contextPath.length());
		Action action=null; //요청이 파악되면 해당 요청을 처리하는 각 Action 클래스 객체를 사용해 요청 처리하는데, 각 요청에 해당하는 Action클래스 객체들을 다형성을 이용해 참조하기 위해 Action인터페이스 타입 변수 정의.
		ActionForward forward=null;//각 Action 클래스 객체의 execute 메소드를 호출하면 요청에 해당하는 비즈니스 로직을 수행하고 포워딩될 URL정보와 리다이렉트 방식 정보를 반환
		
		//파악된 각 요청(command)에 대해서 각 Action클래스 객체의 execute 메소드를 다형성을 이용해서 호출하여 비즈니스 로직을 처리하고 포워딩에 관련된 정보를 ActionForward타입으로 반환 받는다
		//2.각 요청별로 비즈니스 로직 호출
		if(command.equals("/dogList.do")){
			action=new DogListAction();
			//프로젝트명+기능+형태(?)
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/dogView.do")){
			action=new DogViewAction();
			//프로젝트명+기능+형태(?)
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/dogCartAdd.do")){
			action=new DogCartAddAction();
			//프로젝트명+기능+형태(?)
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/dogCartList.do")){
			action=new DogCartListAction();
			//프로젝트명+기능+형태(?)
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/dogCartSearch.do")){
			action=new DogCartSearchAction();
			//프로젝트명+기능+형태(?)
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/dogCartRemove.do")){
			action=new DogCartRemoveAction();
			//프로젝트명+기능+형태(?)
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/dogCartQtyUp.do")){
			action=new DogCartQtyUpAction();
			//프로젝트명+기능+형태(?)
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/dogCartQtyDown.do")){
			action=new DogCartQtyDownAction();
			//프로젝트명+기능+형태(?)
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/dogRegist.do")){
			action=new DogRegistAction();
			//프로젝트명+기능+형태(?)
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/dogRegistForm.do")){
			action=new DogRegistFormAction();
			//프로젝트명+기능+형태(?)
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		//Action객체에서 반환된 forward객체의 정보를 이용해서 포워딩 처리를 하는 부분
		//3.포워딩
		if(forward!=null){
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}//doProcess
}//DogFrontController