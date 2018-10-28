<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
#joinformArea{
	width:400px;
	margin:auto;
	border:1px solid gray;
}
table{
	width:300px;
	margin:auto;
	text-align:center;
}
</style>
</head>
<body>
<!-- 클라이언트의 요청에 대한 응답 화면을 만들어낼 뷰 페이지
	회원 가입에 필요한 정보를 입력받는 jsp 페이지 -->
<section id="joinformArea">
<form name="joinform" action="memberJoinAction.do" method="post">
<table>
	<tr>
		<td colspan="2">
			<h1>회원 가입 페이지</h1>
		</td>
	</tr>
	<tr>
		<td><label for="MEMBER_ID">아이디 : </label></td>
		<td><input type="text" name="MEMBER_ID" id="MEMBER_ID"/></td>
	</tr>
	<tr>
		<td><label for="MEMBER_PW">비밀번호 : </label></td>
		<td><input type="password" name="MEMBER_PW" id="MEMBER_PW"/></td>
	</tr>
		<tr>
		<td><label for="MEMBER_NAME">이름 : </label></td>
		<td><input type="text" name="MEMBER_NAME" id="MEMBER_NAME" maxlength="2"/></td>
	</tr>
	<tr>
		<td><label for="MEMBER_AGE">나이 : </label></td>
		<td><input type="text" name="MEMBER_AGE" id="MEMBER_AGE" maxlength="2"/></td>
	</tr>
	<tr>
		<td><label for="MEMBER_GENDER">성별 : </label></td>
		<td><input type="radio" name="MEMBER_GENDER" id="MEMBER_GENDER" value="남"/>남자
			<input type="radio" name="MEMBER_GENDER" id="MEMBER_GENDER" value="여" checked="checked"/>여자
		</td>
	</tr>
	<tr>
		<td><label for="MEMBER_EMAIL">이메일 : </label></td>
		<td><input type="text" name="MEMBER_EMAIL" id="MEMBER_EMAIL"/></td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="javascript:joinform.submit()">회원가입</a>&nbsp;&nbsp;
			<a href="javascript:joinform.reset()">다시작성</a>
		</td>
	</tr>
</table>
</form>
</section>
</body>
</html>