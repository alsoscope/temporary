<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
#loginformArea{
	margin:auto;
	width:400px;
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
<section id="loginformArea">
<form name="loginform" action="./memberLoginAction.do" method="post">
<table>
	<tr>
		<td colspan="2">
			<h1>로그인 페이지</h1>
		</td>
	</tr>
	<tr><td><label for="MEMBER_ID">아이디 : </label></td>
		<td><input type="text" name="MEMBER_ID" id="MEMBER_ID"/></td>
	</tr>
	<tr><td><label for="MEMBER_PW">비밀번호 : </label></td>
		<td><input type="password" name="MEMBER_PW" id="MEMBER_PW"/></td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="javascript:loginform.submit()">로그인</a>&nbsp;&nbsp;
			<a href="memberJoin.do">회원 가입</a>
		</td>
	</tr>
</table>
</form>
</section>
</body>
</html>