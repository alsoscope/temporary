<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list</title>
<style>
	table{
		border:1px solid black;
	}
	font{
		font-family:맑은고딕;
	}
</style>
</head>
<body>
<br><br>
<center><h2>리스트</h2><h3><a href="WriteForm.do">글쓰기</a></h3></center>

<table align=center width=700 bgcolor="white" style="border:1px solid black;">
	<tr height=30>
	<td align=center width=50>번 호</td>
	<td align=center width=150>글제목</td>
	<td align=center width=100>작성자</td>
	<td align=center width=80>조회수</td>
	<td align=center width=50>작성일</td>
	<td align=center width=100>IP</td>
</tr>

	<c:forEach var="map" items="${list }">
		<tr height="40">
			<td align=center>
			<c:out value="${number }"/>
			<c:set var="number" value="${number-1 }"/>
			</td>

		<td>
		<c:if test="${map.re_level>0 }">
			<img src="/springProject/resources/imgs/level.gif" width="${5*map.re_level }" height="16">
			<img src="/springProject/resources/imgs/re.gif">
		</c:if>
		
		<c:if test="${map.re_level==0 }"> <%-- 답글이 아닐 때 if태그 --%>
			<img src="/springProject/resources/imgs/level.gif width="${5*map.re_level}" height="16">
		</c:if>
		
 			<a href="content.do?num=${map.num}&pageNum=${currentPage}">
			${map.subject}
			</a>
			
		<c:if test="${map.readcount>=20}">
			<img src="/springProject/resources/imgs/hot.gif" border=0 height=16"/>
		</c:if>		
		</td>
		
<%-- 		<td>
			<a href="mailto:${map.email}">${map.writer}</a>
		</td> --%>
		
			<td align="center">${map.writer }</td>
<%-- 			<td align="center">
				<a href="content.do?num=${map.num }">${map.subject }</a>
			</td> --%>
		<td>
			${map.readcount }
		</td>
		
		<td>
			${map.regdate}
		</td>
		
		<td>
			${map.ip}
		</td>

		</tr>
	</c:forEach>
</table>

<table width="700" align="center">
	<tr>
	<td>
	<!-- 이번 블럭 페이지 처리 다음 블럭 -->
	<c:if test="${count>0 }">
	<fmt:parseNumber var="result" value="${currentPage/10 }" integerOnly="true"/>
		<c:set var="startPage" value="${result*10+1 }"/>
		<c:set var="endPage" value="${startPage+pageBlock-1 }"/> <%-- el안먹혀서 따로 계산 --%>
		
		<c:if test="${endPage>pageCount }">
			<c:set var="endPage" value="${pageCount }"/>
		</c:if>
	</c:if>
	
	<!-- 이전 블럭 -->
	<c:if test="${startPage>10 }">
		<a href="list.do?pageNum=${startPage-10 }">[이전블럭]</a>
	</c:if>
	
	<!-- 페이지처리 -->
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<a href="list.do?pageNum=${i }">[${i }]</a>
	</c:forEach>
	
	<!-- 다음 블럭 -->
	<c:if test="${endPage<pageCount }">
		<a href="list.do?pageNum=${startPage+10 }">[다음블럭]</a>
	</c:if>

	<!-- 에러방지 -->
	<c:if test="${endPage>pageCount }">
		<c:set var="endPage" value="${pageCount}"/>
	</c:if>
	
	</td>
	</tr>
</table>
</body>
</html>