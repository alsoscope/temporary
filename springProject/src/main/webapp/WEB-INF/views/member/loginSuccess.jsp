<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>loginSuccess</title>
<script type="text/javascript">
	function updateUser(){
		document.updateForm.action="editForm.do"; //회원수정
		document.updateForm.submit();
	}
	function deleteUser(){
		document.deleteForm.action="memberDelete.do"; //회원탈퇴
		document.deleteForm.submit();
	}
</script>
</head>
<body>
	${memberDto.name }님 환영합니다<br>
<%
	//session.setAttribute("id","park");
%>
<%-- EL, JSTL --%>
<c:set var="id" value="${memberDto.id }" scope="session"/>

<a href="/springProject/board/list.do">게시판 리스트</a>&nbsp;&nbsp;
<a href="javaScript:updateUser()">회원정보수정</a>&nbsp;&nbsp;
<a href="javaScript:deleteUser()">회원탈퇴</a>&nbsp;&nbsp;
<a href="logOut.do">로그아웃</a>

<form method="post" name="updateForm">
	<input type="hidden" id="id" name="id" value="${id }">
</form>

<form method="post" name="deleteForm">
	<input type="hidden" id="id" name="id" value="${id }">
</form>
</body>
</html>