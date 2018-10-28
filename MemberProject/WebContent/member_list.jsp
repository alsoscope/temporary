<%@ page import="vo.MemberBean" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- jstl core 파트 라이브러리 접두사 지정 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 관리 시스템 관리자 모드(회원 목록 보기)</title>
<style>
#memberListArea{
	width:400px;
	border:1px solid gray;
	margin:auto;
}
table{
	width:380px;
	margin:auto;
	text-align:center;
}
</style>
</head>
<body>
<section id="memberListArea">
<table>
<tr>
	<td colspan="2"><h1>회원 목록</h1></td>
</tr>

<!-- request영역에 memberList라는 이름으로 공유되어 있는 ArrayList<MemberBean> 타입의 컬렉션 객체에서 MemberBean 객체 요소를 하나씩 member 변수에 할당하면서 반복문이 실행 -->
<c:forEach var="member" items="${memberList }">
<tr>
	<td>
		<a href="memberViewAction.do?id=${member.MEMBER_ID }">${member.MEMBER_ID }</a><!-- EL(Expression Language)을 이용하여 MEMBER_ID 속성 값 출력 -->
	</td>
	<td>
		<a href="memberDeleteAction.do?id=${member.MEMBER_ID }">삭제</a><!-- 삭제 링크를 생성. 사용자가 삭제 요청을 할 때는 EL을 이용하여 삭제하려는 회원의 MEMBER_ID 값을 파라미터로 전송 -->
	</td>
</tr>
</c:forEach>
</table>
</section>
</body>
</html>