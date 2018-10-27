<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>updateForm</title>
</head>
<body>
<!-- 로그인 상태인지 체크 -->
<c:if test="${empty sessionScope.id }">
	<script>
		alert("로그인 해야 글 수정 가능");
		//history.back(); //이전으로
		location.href='/springProject/member/login.do';
	</script>
</c:if>
<form method="post" action="edit.do">
		<table border="2" bgcolor="dcdcdc" width="600" align="Center">
		<tr height="40px">
			<td>글쓴이</td>
			<td><input type="hidden" name="num" id="num" value="${boardDto.num }" size="10">
			<input type="text" name="writer" id="writer" value="${boardDto.writer }" size="10"></td>
		</tr>
		
		<tr height="40px">
			<td>글제목</td>
			<td><input type="text" name="subject" id="subject" value="${boardDto.subject }" size="30">
			</td>
		</tr>
		
		<tr height="40px">
			<td>이메일</td>
			<td><input type="text" name="email" id="email" value="${boardDto.email }" size="30">
			</td>
		</tr>
		
		<tr height="40px">
			<td>글내용</td>
			<td><textArea name="content" id="content" rows="13" cols="40">${boardDto.content }</textArea></td>
		</tr>
		
		<tr height="40px">
			<td>암호</td>
			<td><input type="password" name="pwd" id="pwd" size="10"></td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="수정">
				<input type="reset" value="리셋">
			</td>
		</tr>
		
		</table>
	</form>
</body>
</html>