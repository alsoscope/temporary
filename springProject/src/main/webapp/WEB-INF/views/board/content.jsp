<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>content</title>
<style type="text/css">
	table{
		margin:auto;
		line-height:40px;
		background-color:#dcdcdc;
		width:80%;
		border:1px solid black;
	}
	font{
		font-family:맑은고딕;
	}
</style>
</head>
<body>
<center><h2>글내용보기</h2></center>
<table align="center">
	<tr>
		<td height="40px" align="center">
			<a href="edit.do?num=${boardDto.num }">글수정</a>&nbsp;
			<a href="delete.do?num=${boardDto.num }">글삭제</a>&nbsp;
			
 			<!-- 로그인 상태인지 체크 -->
<%-- 			<c:if(${empty sessionScope.id }">
				<script>
					alert("로그인 해야 글 삭제 가능");
					//history.back(); //이전으로
					location.href='login.do';
				</script>
			</c:if> --%>
			
<%-- 			<c:if($(sessionScope.id}">
				<input type="button" value="글 삭제" onClick="document.location.href='delete.do?num=${num}'">&nbsp;&nbsp;
			</c:if> --%>
		
			<a href="WriteForm.do">글쓰기</a>&nbsp;
			<a href="list.do">리스트</a>
		</td>
	</tr>
</table>
<br>
<table align="center">
	<tr height="40px">
		<td>글쓴이</td>
		<td>${boardDto.writer }</td>
	</tr>
	
	<tr height="40px">
		<td>글제목</td>
		<td>${boardDto.subject }</td>
	</tr>
	
	<tr>
		<td>이메일</td>
		<td>${boardDto.email }</td>
	</tr>
	
	<tr>
		<td>글내용</td>
		<td><pre>${boardDto.content }</pre></td>
	</tr>
	
	<tr>
		<td>날짜</td>
		<td>
		<%-- <fmt:formatDate value="${boardDto.regdate }" type="date" dateStyle="default"/> --%>
		<fmt:formatDate value="${boardDto.regdate }" pattern="yyyy년 MM월 dd일"/>
		</td>
	</tr>

	<tr>
		<td>조회수</td>
		<td>${boardDto.readcount }</td>
	</tr>

	<tr>
		<td colspan="2" align=center><input type="button" value="답 글" onClick="location='WriteForm.do?num=${boardDto.num }&ref=${boardDto.ref }&re_step=${boardDto.re_step }&re_level=${boardDto.re_level }'"></td>
	</tr>

</table>
</body>
</html>