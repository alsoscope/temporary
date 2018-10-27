<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WriteForm</title>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	function writeFormCheck(){
		
	if($("#writer").val()==null||$("#writer").val()==""){
		alert("글쓴이 입력해주세요");
		$("#writer").focus();
		return false;
	}//if
	
	if($("#subject").val()==null||$("#subject").val()==""){
		alert("글제목 입력해주세요");
		$("#subject").focus();
		return false;
	}//if
	
	if($("#content").val()==null||$("#content").val()==""){
		alert("글내용 입력해주세요");
		$("#content").focus();
		return false;
	}//if
	return true;
	}//writeFormCheck()
</script>
<style type="text/css">
	table{
		margin:auto;
		line-height:40px;
		width:80%;
		border:1px solid black;
	}
</style>
</head>
<body align=center>
<center><h2>게시판 글쓰기</h2></center>
<%-- <%= request.getContextPath() %><br> --%>
<form method="post" action="writePro.do" onSubmit="return writeFormCheck()">
	<input type=hidden name="num" value="${map.num }"/>
	<input type=hidden name="ref" value="${map.ref }"/>
	<input type=hidden name="re_step" value="${map.re_step }"/>
	<input type=hidden name="re_level" value="${map.re_level }"/>
<table>
<tr>
	<td>글쓴이</td>
	<td><input type="text" id="writer" name="writer" size="10"/></td>
</tr>

<tr>
	<td id=b>제목</td>
	<td>
		<c:if test="${map.num==0 }">
			<input type="text" name="subject" id="subject" size="40">
		</c:if>
		
		<c:if test="${map.num!=0 }">
			<input type="text" name="subject" id="subject" size="40" value="[답변]">
		</c:if>
	</td>
</tr>

<tr>
	<td>이메일</td>
	<td><input type="text" id="email" name="email" size="30"></td>
</tr>

<tr>
	<td>글내용</td>
	<td><textArea name="content" id="content" rows="12" cols="40"></textArea></td>
</tr>

<tr>
	<td>암호</td>
	<td><input type="password" id="pwd" name="pwd" size="10"></td>
</tr>

<tr>
	<td colspan="2" align="center">
	<input type="submit" value="등록">
	<input type="reset" value="리셋">
	</td>
</tr>
</table>
</form>
</body>
</html>