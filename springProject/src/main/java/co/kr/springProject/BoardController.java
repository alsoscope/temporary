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

import model.dto.BoardDto;
import model.dto.MemberDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.session.SqlSession;
import java.util.HashMap;
import java.util.List;

@Controller
//@RequestMapping("board")
public class BoardController {

	@Autowired
	private SqlSession sqlSession;
		
	//글쓰기
	@RequestMapping("/WriteForm.do")
	public ModelAndView writeForm(String num, String ref, String re_step, String re_level, String pageNum) {
		if(num==null) { //글쓰기
			num="0"; ref="1"; re_step="0"; re_level="0"; //글번호, 답글그룹, 답글 순서, 깊이
		}//if
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("pageNum",pageNum);//페이지 번호
		map.put("num",num);//글번호
		map.put("ref",ref); //답글 그룹
		map.put("re_step",re_step);//글 순서
		map.put("re_level",re_level); //글 깊이
		return new ModelAndView(".main.board.WriteForm","map",map);
	}//writeForm() -----
		
	//글쓰기Proc
	@RequestMapping(value="/writePro.do",method=RequestMethod.POST)
	public String writeFormPro(BoardDto boardDto,HttpServletRequest request) throws NamingException, IOException{
		int maxNum=0; //최대 글번호
		if(sqlSession.selectOne("board.maxNumber")!=null) { //최대 글번호가
			maxNum=sqlSession.selectOne("board.maxNumber");
		}
		if(maxNum!=0) { //최대 글번호가 0이 아니면
			maxNum=maxNum+1; //최대 글번호 +1로 글작성
		}else { //최대 글번호가 0이면
			maxNum=1; //글번호 1부터 작성
		}
		
		String ip=request.getRemoteAddr(); //ip구하기
		boardDto.setIp(ip);
		if(boardDto.getNum()!=0) { //답변 글이면
			//답글 끼워넣을 위치 확보
			sqlSession.update("board.reStep",boardDto);;
			boardDto.setRe_step(boardDto.getRe_step()+1); //글순서 +1
			boardDto.setRe_level(boardDto.getRe_level()+1); //글깊이 +1
		}else { //원글
			boardDto.setRef(new Integer(maxNum)); //글 그룹번호, 현재 글번호
			boardDto.setRe_step(new Integer(0)); //글순서 0
			boardDto.setRe_level(new Integer(0)); //글 깊이 0
		}//else
		sqlSession.insert("board.insertBoard",boardDto);
		return "redirect:list.do";
	}//writeFormPro() ---
	
	//list
	@RequestMapping("/list.do")
	public String list(Model model, String pageNum) throws NamingException, IOException{
		if(pageNum==null) {
			pageNum="1";
		}
			int pageSize=10; //한 페이지의 글의 개수
			int currentPage=Integer.parseInt(pageNum);
			int startRow=(currentPage-1)*pageSize+1;
			int endRow=currentPage*pageSize;
			int count=0;
			int pageBlock=10;
			
			count=sqlSession.selectOne("board.selectCount"); //글 갯수 출력
			int pageCount=count/pageSize+(count%pageSize==0?0:1);
			
			int number=count-(currentPage-1)*pageSize;
			
			HashMap<String, Integer>map=new HashMap<String, Integer>();
			map.put("start", startRow-1); //시작 위치
			map.put("cnt",pageSize); //글 갯수
			
			List<BoardDto> list=sqlSession.selectList("board.selectAll",map);
			
			model.addAttribute("count", count);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("startRow", startRow);
			model.addAttribute("endRow", endRow);
			model.addAttribute("pageBlock", pageBlock);
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("number", number);
			model.addAttribute("list", list); //리스트에 너ㅎ기
			
			return ".main.board.list";
		}//list() ----
	
	//조회수 증가, 글 내용보기
	@RequestMapping("content.do")
	public ModelAndView content(int num) {
		//조회수 증가
		sqlSession.update("board.readcountBoard", new Integer( num));
		//글내용 보기
		BoardDto boardDto=(BoardDto)sqlSession.selectOne("board.selectOne", num);
		
		return new ModelAndView(".main.board.content","boardDto",boardDto);
	}//getOneBoard() ------
	
	//글수정 폼
	@RequestMapping(value="/edit.do", method=RequestMethod.GET)
	public ModelAndView userEdit(int num) {
		BoardDto boardDto=(BoardDto)sqlSession.selectOne("board.selectOne",num);
		return new ModelAndView(".main.board.edit", "boardDto", boardDto);
	}//userEdit- -----
	
	//글수정 insert
	@RequestMapping(value="/edit.do",method=RequestMethod.POST)
	public String updateBoard(@ModelAttribute("boardDto")BoardDto boardDto) throws NamingException, IOException{
		sqlSession.update("board.updateBoard",boardDto);
		return "redirect:list.do";
	}//updateBoard
	
	//delete 글삭제
	@RequestMapping("delete.do")
	public String deleteBoard(int num) throws NamingException, IOException{
		sqlSession.delete("board.deleteBoard",num);
		return "redirect:list.do";
	}//deleteBoard

}//class
