package org.p.project.member.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.p.project.member.model.MemberVO;
import org.springframework.stereotype.Repository;

//회원목록 구현
//현재 클래스를 DAO bean으로 등록시킴
@Repository
public class MemberDAOImpl implements MemberDAO {

	//SqlSession 객체를 스프링에서 생성하여 주입시킴
	//의존관계 주입(Dependency Injection, DI)
	//느슨한 결한
	//IoC (Inversion of Control, 제어의 역전)
	@Inject
	SqlSession sqlSession;
	//Inject 어노테이션이 없으면 sqlSession은 null 상태이지만
	//Inject 어노테이션이 있으면 외부에서 객체를 주입시켜주게 된다
	//try-catch문, finally문, 객체를 close 할 필요가 없어졌다

	//회원목록
	@Override
	public List<MemberVO> memberList() {
		return sqlSession.selectList("member2.memberList");
	}

	//회원 등록
	@Override
	public void insertMember(MemberVO vo) {
		sqlSession.insert("member2.insertMember", vo);
	}
	
	//회원정보 상세 조회
	@Override
	public MemberVO viewMember(String userId) {
		return sqlSession.selectOne("member2.viewMember", userId);
	}

	@Override
	public void deleteMember(String userId) {
		sqlSession.delete("member2.deleteMember", userId);
	}

	//회원 정보 수정 처리
	@Override
	public void updateMember(MemberVO vo) {
		sqlSession.update("member2.updateMember", vo);
	}

	//회원정보 수정 및 삭제를 위한 비밀번호 체크
	@Override
	public boolean checkPw(String userId, String userPw) {
		boolean result=false;
		Map<String, String> map=new HashMap<String, String>();
		map.put("userId", userId);
		map.put("userPw", userPw);
		int count=sqlSession.selectOne("member2.checkPw", map);
		if(count==1) result=true;
		return result;
	}

	//로그인
	@Override
	public MemberVO login(String userId, String userPw) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("userId", userId);
		map.put("userPw", userPw);
		return sqlSession.selectOne("member2.memberLogin", map);
	}
}
