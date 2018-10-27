<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:remove var="id" scope="session"/> <!-- 변수제거, 세션 무효화 -->
<%
	//session.invalidate(); //세션 무효화
%>

<%
	response.sendRedirect("main.do"); //http:// 새로 연결된다
%>
</body>
</html>