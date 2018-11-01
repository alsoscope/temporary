<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
#registForm{
	width:500px;
	height:600px;
	border:1px solid red;
	margin:auto;
}
h2{
	text-align:center;
}
table{
	margin:auto;
	width:450px;
}
.td_left{
	width:150px;
	background:orange;
}
.td_right{
	width:300px;
	background:skyblue;
}
#commandCell{
	text-align:center;
}
</style>
</head>
<body>
<section id="registForm">
	<header>
	<h2>개 정보등록</h2>
	</header>												<!-- 등록 요청에 파일 업로드 요청이 존재하므로 enctype="multipart/form-data" 지정-->
	<form action="dogRegist.do" method="post" name="writeForm" enctype="multipart/form-data">
	<table>
		<tr>
			<td colspan="2" align="center">
				<a href="dogList.do">목록보기</a>
			</td>	
		</tr>	
		<tr>
			<td class="td_left">
				<label for="kind">품종 : </label>
			</td>
			<td class="td_right">
				<input type="text" name="kind" id="kind" required="required"/>
			</td>
		</tr>
		<tr>
			<td class="td_left">
				<label for="country">원산지 : </label>
			</td>
			<td class="td_right">
				<input type="text" name="country" id="country"/>
			</td>
		</tr>
		<tr>
			<td class="td_left">
				<label for="price">가격 : </label>
			</td>
			<td class="td_right">
				<input type="text" name="price" id="price"/>
			</td>
		</tr>
		<tr>
			<td class="td_left">
				<label for="height">신장 : </label>
			</td>
			<td class="td_right">
				<input type="text" name="height" id="height"/>
			</td>
		</tr>
		<tr>
			<td class="td_left">
				<label for="weight">체중 : </label>
			</td>
			<td class="td_right">
				<input type="text" name="weight" id="weight"/>
			</td>
		</tr>
		<tr>
			<td class="td_left">
				<label for="content">내용 : </label>
			</td>
			<td class="td_right">
				<textarea name="content" id="content" rows="13" cols="40" wrap="off"></textarea>
			</td>
		</tr>
		<tr>
			<td class="td_left">
				<label for="image">상품 이미지 : </label>
			</td>
			<td class="td_right">
				<input type="file" name="image" id="image"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" id="commandCell">
				<input type="submit" value="개 상품등록"/>
				<input type="reset" value="다시 작성"/>
				<input type="button" value="개 상품목록보기" onClick="window.location.href='dogList.do'"/>
			</td>
		</tr>
	</table>
	</form>
</section>
</body>
</html>