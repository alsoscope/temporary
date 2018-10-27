package co.kr.springProject;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import model.dto.MemberDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.session.SqlSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("member")
public class MemberController {
	//�������� �տ� @Autowired ���̸� �ڵ����� setter �۾��ȴ�
	
	@Autowired
	private SqlSession sqlSession;
	
	//main.do
	@RequestMapping("/main.do")
	public String mainTest() {
		return "main"; //main.jsp �� ����
	}//mainTest()---
	
	//ȸ������ ��
	@RequestMapping("/insertForm.do")
	public String insertForm() { //GET��� �����ؼ� ��
		return ".main.member.insertForm"; //�� insertForm.jsp
	}//inserForm() ----
	
	//idCheck  id�ߺ�üũ
	@RequestMapping(value="/idCheck.do",method=RequestMethod.POST)
	public String idCheck(String id, Model model) throws NamingException, IOException{
		int check=-1;
		MemberDto memberDto=sqlSession.selectOne("member.selectOne", id);
		
		if(memberDto==null) {
			check=1; //��� ������ id
		}
		model.addAttribute("check",check);
		return "member/idCheck"; //�� ���� idCheck.jsp *****
		//return new ModelAndView("member/idCheck","check",check);
	}//idCheck() ----
	
	//ȸ������
	@RequestMapping(value="/insertPro.do", method=RequestMethod.POST)
	public String memberInsert(@ModelAttribute("MemberDto")MemberDto memberDto) throws NamingException, IOException{
		sqlSession.insert("member.insertMember", memberDto);
		
		//return "redirect:list.do";
		return ".main.member.login"; //���̸� login.jsp
	}//memberInsert
	
	//�α��� ��
	@RequestMapping("/login.do")
	public String userLogin() {
		return ".main.member.login"; //login.jsp ��
	}//userLogin
	
	//�α���
	@RequestMapping(value="/loginPro.do",method=RequestMethod.POST)
	public String memberLogin(String id, String pwd, Model model) throws NamingException, IOException{
		HashMap<String, String>map=new HashMap<String,String>();
		//map�� �ְ� map���� ����
		map.put("id", id);
		map.put("pwd", pwd);
		
		MemberDto memberDto=sqlSession.selectOne("member.selectLogin", map);
		
		if(memberDto==null) { //�α��� ����
			model.addAttribute("msg", "�α��� ����");
			return ".main.member.login"; //login.jsp
			//return new ModelAndView("member/login", "msg", "�α��� ����");
		}
		model.addAttribute("memberDto", memberDto);
		return ".main.member.loginSuccess"; // �� ���� loginSuccess.jsp
		//return new ModelAndView("member/loginSuccess", "memberDto", memberDto);
	}//memberLogin
	
	//�α׾ƿ�
	@RequestMapping("/logOut.do")
	public String userOut() {
		return ".main.member.logOut"; //�� logOut.jsp
	}//userOut() ----
	
	//ȸ���������� ��
	@RequestMapping(value="/editForm.do",method=RequestMethod.POST)
	public String editForm(String id, Model model) throws NamingException, IOException{
		MemberDto memberDto=sqlSession.selectOne("member.selectOne",id);
		model.addAttribute("memberDto",memberDto);
		return ".main.member.editForm";//�丮�� editForm.jsp
		//return new ModelAndView
	}//editForm
	
	//db ȸ����������
	@RequestMapping(value="/editPro.do",method=RequestMethod.POST)
	public String editPro(@ModelAttribute("MemberDto")MemberDto memberDto) throws NamingException, IOException{
		sqlSession.update("member.memberUpdate", memberDto);
		return ".main.member.main"; //login.jsp �� return
	}//editPro
	
	//ȸ��Ż��
	@RequestMapping("memberDelete.do")
	public String userDelete(String id) throws NamingException, IOException{
		sqlSession.delete("member.memberDelete",id);
		return ".main.member.insertForm"; //�� insertForm.jsp
	}//userDelete
	
}//class