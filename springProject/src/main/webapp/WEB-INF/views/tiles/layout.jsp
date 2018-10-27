<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>layout</title>
<style>
font{
		font-family:맑은고딕;
	}
</style>
</head>
<body>
	<table border="1" style="width:100%; min-width:700px; border-collapse:collapse;">
		<tr>
			<td colspan="2" style="border-collapse:collapse;" height="60" bgcolor="CCE1FF">
				<tiles:insertAttribute name="header"/><!-- header.jsp -->
			</td>
		</tr>
		
		<tr>
			<td valign="top" width="70" height="500">
				<br><br>
				<tiles:insertAttribute name="side"/><!-- side.jsp -->
			</td>
			<td valign="top">
				<tiles:insertAttribute name="content"/><!-- 중앙에 표시 -->
			</td>
		</tr>
		
		<tr>
			<td colspan="2" height="50" bgcolor="CCE1FF">
				<tiles:insertAttribute name="footer"/><!-- footer.jsp -->
			</td>
		</tr>
	</table>
</body>
</html>