<%@ page import="java.util.HashMap" %>
<%@ page import="vo.Dog" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- JSTL core 파트의 기능 사용위해 커스텀 태그의 접두사를 지정. core파트의 태그 사용은 c접두사로 시작. -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
#listForm{
	width:700px;
	height:500px;
	border:1px solid red;
	margin:auto;
}
h2{
	text-align:center;
}
table{
	margin:auto;
	width:550px;
}
.div_empty{
	background-color:red;
	width:100%;
	height:100%;
	text-align:center;
}
#todayImageList{
	text-align:center;
}
#productImage{
	width:150px;
	height:150px;
	border:none;
}
#todayImage{
	width:100px;
	height:100px;
	border:none;
}
</style>
</head>
<body>
<section id="listForm">
<c:if test="${dogList!=null }"><!-- 조회한 개 상품 목록이 존재할 때 개 상품 정보 출력 -->
<h2>개 상품 목록 <a href="dogRegistForm.do">개 상품 등록</a></h2>
<table>
	<tr>
		<c:forEach var="dog" items="${dogList }" varStatus="status"><!-- 조회한 개 상품 개수만큼 반복하면서 개정보 출력 -->
		<!-- dogList ArrayList 객체에 요소로 추가되어 있는 개 상품 정보를 하나씩 dog변수에 할당하면서 반복문 실행
			dogList객체는 request 영역에 dogList라는 이름으로 공유되어 있는 속성에 접근한 것. status 객체에는 forEach 문의 실행 정보가 저장된다
		 -->
		<td>
			<a href="dogView.do?id=${dog.id }"><!-- 각 개 상품의 이미지를 출력하면서 상품 자세히 보기 요청을 링크함. 상품 자세히 보기 요청에서는 해당 개를 구분할 수 있도록 id값을 파라미터로 전송 -->
			<img src="images/${dog.image }" id="productImage"/>
			</a>
			상품명:${dog.kind }<br>
			가격:${dog.price }<br>
		</td>
		<c:if test="${((status.index+1)mod 4)==0 }"><!-- 개 상품을 출력할 때 한 줄에 4개씩만 출력되도록 처리 -->
	</tr>
	<tr>
		</c:if>
		</c:forEach>
	</tr>
</table>
</c:if>

<c:if test="${dogList==null }"><!-- 조회된 개 상품 정보가 하나도 존재하지 않을 때 출력되는 부분 -->
	<div class="div_empty">
	개 상품이 없습니다. 분양불가
	</div>
</c:if>

<c:if test="${todayImageList!=null }"><!-- 쿠키에 저장되어 있는 오늘 본 개 상품의 이미지를 출력 -->
	<div id="todayImageList">
	<h2>오늘 본 개 상품 목록</h2>
	<table>
		<tr>
			<c:forEach var="todayImage" items="${todayImageList }" varStatus="status">
			<td>
				<img src="imges/${todayImage }" id="todayImage"/>
			</td>
				<c:if test="${((status.index+1)mod 4)==0 }">
			</tr>
			<tr>
		</c:if>
		</c:forEach>
		</tr>
	</table>
	</div>
</c:if>
</section>
</body>
</html>