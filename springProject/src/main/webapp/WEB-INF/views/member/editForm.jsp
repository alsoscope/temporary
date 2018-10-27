<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>editForm</title>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<style>
	body{background-color:#dcdcdc;}
	table{
		align:center;
		margin:auto;	
		line-height:40px;
		padding:3px;
		background-color:cyan;
}
</style>
</head>
<body>
<%-- <!-- 로그인 상태인지 체크 -->
<c:if test="${empty sessionScope.id }">
	<script>
		alert("로그인 해야 글 수정 가능");
		//history.back(); //이전으로
		location.href='/springProject/member/login.do';
	</script>
</c:if>
 --%>
	<h2 align="center">회원정보수정포</h2>
	<form method=post action="editPro.do">
	<table>
		<tr>
			<td>ID</td>
			<td><input type="text" id="id" name="id" value="${memberDto.id }" readonly="readonly"></td>
		</tr>

		<tr>
			<td>PWD</td>
			<td><input type="hidden" id="pwd" name="pwd" value="${memberDto.pwd }"><input type="text" id="npwd" name="npwd"></td>
		</tr>
		
		<tr>
			<td>PWD	확인</td>
			<td><input type="text" id="npwd2" name="npwd2"></td>
		</tr>
		
		<tr>
			<td>이름</td>
			<td><input type="text" id="name" name="name" size="10" value="${memberDto.name }"></td>
		</tr>
		
		<tr>
			<td>주민번호</td>
			<td><input type="text" id="jumin1" name="jumin1" value="${memberDto.jumin1 }">-<input type="text" id="jumin2" name="jumin2" value="${memberDto.jumin2 }"></td>
		</tr>
		
		<tr>
			<td>email</td>
			<td><input type="text" id="email" name="email" size="30" value="${memberDto.email }"></td>
		</tr>
		
		<tr>
			<td>우편번호</td>
			<td><input type="text" name="zipcode" id="zipcode" size="7" value="${memberDto.zipcode }"><input type="button" value="우편번호찾기" onclick="openDaumPostcode()"></td>
		</tr>
		
		<tr>
			<td>주소</td>
			<td><input type="text" name="addr" id="addr" size="50" value="${memberDto.addr }"></td>
		</tr>
		
		<tr>
			<td>Job</td>
			<td><select name="job">
				<option value="${memberDto.job }">${memberDto.job }</option>
				<option value="회사원">회사원</option>
				<option value="전문직">전문직</option>
				<option value="학생">학생</option>
				<option value="공무원">공무원</option>
				<option value="의료인">의료인</option>
				<option value="법조인">법조인</option>
				<option value="기타">기타</option>
			</select></td>
		</tr>
		
 		<tr>
			<td>Blog</td>
			<td><input type="text" name="blog" id="blog" size="10" value="${memberDto.blog }"></td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
			<input type="submit" value="회원정보수정">
			<input type="reset" value="취소"></td>
		</tr>
	</table>
	</form>
</body>
</html>