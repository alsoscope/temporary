package org.p.project;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.p.project.member.model.MemberVO;
import org.p.project.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//현재 클래스를 controller bean에 등록시킴
@Controller
public class MemberController {

	private static final Logger logger=LoggerFactory.getLogger(MemberController.class);
	
//MemberService 객체를 스프링에서 생성하여 주입시킴
@Inject
MemberService memberService;

	/*
	//로그인
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(MemberVO vo, String userId, String userPw, HttpSession session, Model model)throws NamingException, IOException {
		MemberVO member=memberService.login(userId, userPw);
		
		if(member==null) {
			model.addAttribute("msg", "로그인 실패");
			return "login";
		}
		model.addAttribute("member", member);
		return "member/member_list";
	}
	*/

	//01 회원목록
	//url pattern mapping
	@RequestMapping("member/list.do")
	public String memberList(Model model) {
		//controller->service->dao 요청
		List<MemberVO> list=memberService.memberList();
		model.addAttribute("list",list);
		return "member/member_list";
	}
	
	//02_01 회원 등록 페이지로 이동
	@RequestMapping("member/write.do")
	public String memberWrite() {
		return "member/member_write";
	}
	
	//02_02 회원 등록 처리 후 -> 회원목록으로 리다이렉트
	//@ModelAttribute에 폼에서 입력한 데이터가 저장된다
	//*폼에서 입력한 데이터를 받아오는 법 3가지
	//public String memberInsert(HttpServlet request){}
	//public String memberInsert(String userId, String userPw, String userName){}
	@RequestMapping("member/insert.do")
	public String memberInsert(@ModelAttribute MemberVO vo) {
		//테이블에 레코드 입력
		memberService.insertMember(vo);
		//* (/)유무의 차이
		// /member/list.do : 루트 디렉토리를 기준
		// member/list.do : 현재 디렉토리를 기준
		// member_list.jsp 로 리다이렉트
		return "redirect:/member/list.do";
	}
	
	//03 회원 상세정보 조회
	@RequestMapping("member/view.do")
	public String memberView(String userId, Model model) {
		//회원 정보를 model에 저장
		model.addAttribute("dto", memberService.viewMember(userId));
		//System.out.println("클릭한 아이디 확인:"+userId);
		logger.info("클릭한 아이디 : "+userId);
		return "member/member_view";
	}
	
	//04 회원 정보 수정 처리
	@RequestMapping("member/update.do")
	public String memberUpdate(@ModelAttribute MemberVO vo, Model model) {
		//비밀번호 체크
		boolean result=memberService.checkPw(vo.getUserId(), vo.getUserPw());
		if(result) {//비밀번호가 일치하면 수정 처리 후, 전체 회원 목록으로 리다이렉트
			memberService.updateMember(vo);
			return "redirect:/member/list.do";			
		}else {//비밀번호가 일치하지 않는다면, div에 불일치 문구 출력, view,jsp로 포워드
			//다시 동일한 화면을 출력하기 위해서 가입일자와 수정일자 그리고 불일치 문구를 model에 저장, 상세화면으로 포워드
			//가입일자, 수정일자 저장
			MemberVO vo2=memberService.viewMember(vo.getUserId());
			vo.setUserRegdate(vo2.getUserRegdate());
			vo.setUserUpdatedate(vo2.getUserUpdatedate());
			model.addAttribute("dto", vo);
			model.addAttribute("message", "비밀번호가 맞지 않습니다");
			return "member/member_view";
		}
	}
	
	//05 회원정보 삭제 처리
	//@RequestMapping:url mapping
	//@RequestParam:get or post 방식으로 전달된 변수값
	@RequestMapping("member/delete.do")
	public String memberDelete(@RequestParam String userId, @RequestParam String userPw, Model model) {
		//비밀번호 체크
		boolean result=memberService.checkPw(userId, userPw);
		if(result) { //비밀번호가 맞다면 삭제 처리 후, 전체 회원 목록으로 리다이렉트
			memberService.deleteMember(userId);
			return "redirect:/member/list.do";
		}else {//비밀번호가 일치하지 않는다면 div에 불일치 문구출력, view.jsp로 포워드
			model.addAttribute("message", "비밀번호가 맞지 않습니다");
			model.addAttribute("dto", memberService.viewMember(userId));
			return "member/member_view";
		}
	}
}//MemberController
