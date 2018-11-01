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
	//DogFrontController �������� ������ ��� ��û������ doProcess�޼ҵ� ȣ���ϰ� ��
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1.��û�ľ�
		request.setCharacterEncoding("UTF-8");

		//���۵� ��û�� � ��û������ URL�� �̿��Ͽ� �ľ�
		String RequestURI=request.getRequestURI();
		//��ûURL:http://localhost:8080/boardProject/boardWriteForm.do
		//requestURI:/boardProject/boardWriteForm.do ��ȯ
		
		String contextPath=request.getContextPath();//   /boardProject �ٳ�
		String command=RequestURI.substring(contextPath.length());
		Action action=null; //��û�� �ľǵǸ� �ش� ��û�� ó���ϴ� �� Action Ŭ���� ��ü�� ����� ��û ó���ϴµ�, �� ��û�� �ش��ϴ� ActionŬ���� ��ü���� �������� �̿��� �����ϱ� ���� Action�������̽� Ÿ�� ���� ����.
		ActionForward forward=null;//�� Action Ŭ���� ��ü�� execute �޼ҵ带 ȣ���ϸ� ��û�� �ش��ϴ� ����Ͻ� ������ �����ϰ� �������� URL������ �����̷�Ʈ ��� ������ ��ȯ
		
		//�ľǵ� �� ��û(command)�� ���ؼ� �� ActionŬ���� ��ü�� execute �޼ҵ带 �������� �̿��ؼ� ȣ���Ͽ� ����Ͻ� ������ ó���ϰ� �������� ���õ� ������ ActionForwardŸ������ ��ȯ �޴´�
		//2.�� ��û���� ����Ͻ� ���� ȣ��
		if(command.equals("/dogList.do")){
			action=new DogListAction();
			//������Ʈ��+���+����(?)
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/dogView.do")){
			action=new DogViewAction();
			//������Ʈ��+���+����(?)
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/dogCartAdd.do")){
			action=new DogCartAddAction();
			//������Ʈ��+���+����(?)
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/dogCartList.do")){
			action=new DogCartListAction();
			//������Ʈ��+���+����(?)
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/dogCartSearch.do")){
			action=new DogCartSearchAction();
			//������Ʈ��+���+����(?)
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/dogCartRemove.do")){
			action=new DogCartRemoveAction();
			//������Ʈ��+���+����(?)
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/dogCartQtyUp.do")){
			action=new DogCartQtyUpAction();
			//������Ʈ��+���+����(?)
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/dogCartQtyDown.do")){
			action=new DogCartQtyDownAction();
			//������Ʈ��+���+����(?)
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/dogRegist.do")){
			action=new DogRegistAction();
			//������Ʈ��+���+����(?)
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/dogRegistForm.do")){
			action=new DogRegistFormAction();
			//������Ʈ��+���+����(?)
			try{
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		//Action��ü���� ��ȯ�� forward��ü�� ������ �̿��ؼ� ������ ó���� �ϴ� �κ�
		//3.������
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