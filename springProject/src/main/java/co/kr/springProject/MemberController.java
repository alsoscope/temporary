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
	//변수선언 앞에 @Autowired 붙이면 자동으로 setter 작업된다
	
	@Autowired
	private SqlSession sqlSession;
	
	//main.do
	@RequestMapping("/main.do")
	public String mainTest() {
		return "main"; //main.jsp 뷰 리턴
	}//mainTest()---
	
	//회원가입 폼
	@RequestMapping("/insertForm.do")
	public String insertForm() { //GET방식 생략해서 씀
		return ".main.member.insertForm"; //뷰 insertForm.jsp
	}//inserForm() ----
	
	//idCheck  id중복체크
	@RequestMapping(value="/idCheck.do",method=RequestMethod.POST)
	public String idCheck(String id, Model model) throws NamingException, IOException{
		int check=-1;
		MemberDto memberDto=sqlSession.selectOne("member.selectOne", id);
		
		if(memberDto==null) {
			check=1; //사용 가능한 id
		}
		model.addAttribute("check",check);
		return "member/idCheck"; //뷰 리턴 idCheck.jsp *****
		//return new ModelAndView("member/idCheck","check",check);
	}//idCheck() ----
	
	//회원가입
	@RequestMapping(value="/insertPro.do", method=RequestMethod.POST)
	public String memberInsert(@ModelAttribute("MemberDto")MemberDto memberDto) throws NamingException, IOException{
		sqlSession.insert("member.insertMember", memberDto);
		
		//return "redirect:list.do";
		return ".main.member.login"; //뷰이름 login.jsp
	}//memberInsert
	
	//로그인 폼
	@RequestMapping("/login.do")
	public String userLogin() {
		return ".main.member.login"; //login.jsp 뷰
	}//userLogin
	
	//로그인
	@RequestMapping(value="/loginPro.do",method=RequestMethod.POST)
	public String memberLogin(String id, String pwd, Model model) throws NamingException, IOException{
		HashMap<String, String>map=new HashMap<String,String>();
		//map다 넣고 map으로 전달
		map.put("id", id);
		map.put("pwd", pwd);
		
		MemberDto memberDto=sqlSession.selectOne("member.selectLogin", map);
		
		if(memberDto==null) { //로그인 실패
			model.addAttribute("msg", "로그인 실패");
			return ".main.member.login"; //login.jsp
			//return new ModelAndView("member/login", "msg", "로그인 실패");
		}
		model.addAttribute("memberDto", memberDto);
		return ".main.member.loginSuccess"; // 뷰 리턴 loginSuccess.jsp
		//return new ModelAndView("member/loginSuccess", "memberDto", memberDto);
	}//memberLogin
	
	//로그아웃
	@RequestMapping("/logOut.do")
	public String userOut() {
		return ".main.member.logOut"; //뷰 logOut.jsp
	}//userOut() ----
	
	//회원정보수정 폼
	@RequestMapping(value="/editForm.do",method=RequestMethod.POST)
	public String editForm(String id, Model model) throws NamingException, IOException{
		MemberDto memberDto=sqlSession.selectOne("member.selectOne",id);
		model.addAttribute("memberDto",memberDto);
		return ".main.member.editForm";//뷰리턴 editForm.jsp
		//return new ModelAndView
	}//editForm
	
	//db 회원정보수정
	@RequestMapping(value="/editPro.do",method=RequestMethod.POST)
	public String editPro(@ModelAttribute("MemberDto")MemberDto memberDto) throws NamingException, IOException{
		sqlSession.update("member.memberUpdate", memberDto);
		return ".main.member.main"; //login.jsp 뷰 return
	}//editPro
	
	//회원탈퇴
	@RequestMapping("memberDelete.do")
	public String userDelete(String id) throws NamingException, IOException{
		sqlSession.delete("member.memberDelete",id);
		return ".main.member.insertForm"; //뷰 insertForm.jsp
	}//userDelete
	
}//class