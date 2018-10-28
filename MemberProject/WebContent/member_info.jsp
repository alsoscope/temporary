<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="vo.MemberBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 관리 시스템 관리자 모드(회원 정보 보기)</title>
<style>
#memberInfoArea{
	width:400px;
	margin:auto;
	border:1px solid gray;
}
table{
	width:380px;
	margin:auto;
	text-align:center;
}
</style>
</head>
<body>
<!-- request영역에 member라는 이름으로 공유되어 있는 객체를 얻어와서 각 속성 값들을 EL을 사용해서 출력하는 페이지 -->
<section id="memberInfoArea">
<table>
	<tr>
		<td>아이디 : </td>
		<td>${member.MEMBER_ID }</td>
	</tr>
	<tr>
		<td>비밀번호 : </td>
		<td>${member.MEMBER_PW }</td>
	</tr>
	<tr>
		<td>이름 : </td>
		<td>${member.MEMBER_NAME }</td>
	</tr>
	<tr>
		<td>나이 : </td>
		<td>${member.MEMBER_AGE }</td>
	</tr>
	<tr>
		<td>성별 : </td>
		<td>${member.MEMBER_GENDER }</td>
	</tr>
	<tr>
		<td>이메일 : </td>
		<td>${member.MEMBER_EMAIL }</td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="memberListAction.do">리스트로 돌아가기</a>
		</td>
	</tr>
</table>
</section>
</body>
</html>