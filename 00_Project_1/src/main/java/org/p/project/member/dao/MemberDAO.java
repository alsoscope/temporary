package org.p.project.member.dao;

import java.util.List;

import org.p.project.member.model.MemberVO;

//기본 메소드 세팅
public interface MemberDAO {
	//회원목록
	public List<MemberVO> memberList();
	//회원입력
	public void insertMember(MemberVO vo);
	//회원 정보 상세보기
	public MemberVO viewMember(String userId);
	//회원 삭제
	public void deleteMember(String userId);
	//회원정보 수정
	public void updateMember(MemberVO vo);
	//회원정보 수정, 삭제를 위한 비밀번호 체크
	public boolean checkPw(String userId, String userPw);
	//로그인
	public MemberVO login(String userId, String userPw);
}
