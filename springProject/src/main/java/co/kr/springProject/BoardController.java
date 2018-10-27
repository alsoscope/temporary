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
		
	//�۾���
	@RequestMapping("/WriteForm.do")
	public ModelAndView writeForm(String num, String ref, String re_step, String re_level, String pageNum) {
		if(num==null) { //�۾���
			num="0"; ref="1"; re_step="0"; re_level="0"; //�۹�ȣ, ��۱׷�, ��� ����, ����
		}//if
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("pageNum",pageNum);//������ ��ȣ
		map.put("num",num);//�۹�ȣ
		map.put("ref",ref); //��� �׷�
		map.put("re_step",re_step);//�� ����
		map.put("re_level",re_level); //�� ����
		return new ModelAndView(".main.board.WriteForm","map",map);
	}//writeForm() -----
		
	//�۾���Proc
	@RequestMapping(value="/writePro.do",method=RequestMethod.POST)
	public String writeFormPro(BoardDto boardDto,HttpServletRequest request) throws NamingException, IOException{
		int maxNum=0; //�ִ� �۹�ȣ
		if(sqlSession.selectOne("board.maxNumber")!=null) { //�ִ� �۹�ȣ��
			maxNum=sqlSession.selectOne("board.maxNumber");
		}
		if(maxNum!=0) { //�ִ� �۹�ȣ�� 0�� �ƴϸ�
			maxNum=maxNum+1; //�ִ� �۹�ȣ +1�� ���ۼ�
		}else { //�ִ� �۹�ȣ�� 0�̸�
			maxNum=1; //�۹�ȣ 1���� �ۼ�
		}
		
		String ip=request.getRemoteAddr(); //ip���ϱ�
		boardDto.setIp(ip);
		if(boardDto.getNum()!=0) { //�亯 ���̸�
			//��� �������� ��ġ Ȯ��
			sqlSession.update("board.reStep",boardDto);;
			boardDto.setRe_step(boardDto.getRe_step()+1); //�ۼ��� +1
			boardDto.setRe_level(boardDto.getRe_level()+1); //�۱��� +1
		}else { //����
			boardDto.setRef(new Integer(maxNum)); //�� �׷��ȣ, ���� �۹�ȣ
			boardDto.setRe_step(new Integer(0)); //�ۼ��� 0
			boardDto.setRe_level(new Integer(0)); //�� ���� 0
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
			int pageSize=10; //�� �������� ���� ����
			int currentPage=Integer.parseInt(pageNum);
			int startRow=(currentPage-1)*pageSize+1;
			int endRow=currentPage*pageSize;
			int count=0;
			int pageBlock=10;
			
			count=sqlSession.selectOne("board.selectCount"); //�� ���� ���
			int pageCount=count/pageSize+(count%pageSize==0?0:1);
			
			int number=count-(currentPage-1)*pageSize;
			
			HashMap<String, Integer>map=new HashMap<String, Integer>();
			map.put("start", startRow-1); //���� ��ġ
			map.put("cnt",pageSize); //�� ����
			
			List<BoardDto> list=sqlSession.selectList("board.selectAll",map);
			
			model.addAttribute("count", count);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("startRow", startRow);
			model.addAttribute("endRow", endRow);
			model.addAttribute("pageBlock", pageBlock);
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("number", number);
			model.addAttribute("list", list); //����Ʈ�� �ʤ���
			
			return ".main.board.list";
		}//list() ----
	
	//��ȸ�� ����, �� ���뺸��
	@RequestMapping("content.do")
	public ModelAndView content(int num) {
		//��ȸ�� ����
		sqlSession.update("board.readcountBoard", new Integer( num));
		//�۳��� ����
		BoardDto boardDto=(BoardDto)sqlSession.selectOne("board.selectOne", num);
		
		return new ModelAndView(".main.board.content","boardDto",boardDto);
	}//getOneBoard() ------
	
	//�ۼ��� ��
	@RequestMapping(value="/edit.do", method=RequestMethod.GET)
	public ModelAndView userEdit(int num) {
		BoardDto boardDto=(BoardDto)sqlSession.selectOne("board.selectOne",num);
		return new ModelAndView(".main.board.edit", "boardDto", boardDto);
	}//userEdit- -----
	
	//�ۼ��� insert
	@RequestMapping(value="/edit.do",method=RequestMethod.POST)
	public String updateBoard(@ModelAttribute("boardDto")BoardDto boardDto) throws NamingException, IOException{
		sqlSession.update("board.updateBoard",boardDto);
		return "redirect:list.do";
	}//updateBoard
	
	//delete �ۻ���
	@RequestMapping("delete.do")
	public String deleteBoard(int num) throws NamingException, IOException{
		sqlSession.delete("board.deleteBoard",num);
		return "redirect:list.do";
	}//deleteBoard

}//class
