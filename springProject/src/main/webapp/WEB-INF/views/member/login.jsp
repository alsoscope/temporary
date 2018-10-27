<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<style type="text/css">
	table{
		margin:auto;
		line-height:40px; border:1px solid black; width:80%;
	}
</style>
</head>
<body>
<h2><center>로그인</center></h2>
	${msg }<br>
	
	<form method="post" action="loginPro.do">
	<table>
		<tr>
			<td>ID</td>
			<td><input type="text" id="id" name="id" size="10"></td>
		</tr>
		
		<tr>	
			<td>PWD</td>
			<td><input type="password" id="pwd" name="pwd" size="10"></td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="로그인">
				<input type="reset" value="취소">
			</td>
		</tr>
	
	</table>
	</form>
</body>
</html>