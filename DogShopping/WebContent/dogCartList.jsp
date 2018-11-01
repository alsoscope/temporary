<%@ page import="com.sun.org.apache.bcel.internal.generic.Select" %>
<%@ page import="vo.Cart" %>
<%@ page import="vo.Dog" %>
<%@ page import="dao.DogDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
#listForm{
	width:640px;
	border:1px red solid;
	margin:auto;
}
h2{
	text-align:center;
}
table{
	width:550px;
	margin:auto;
}
.tr_top{
	background-color:lime;
}
.div_empty{
	text-align:center;
	background-color:
}
.td_command{
	text-align:right;
}
#todayImageList{
	text-align:center;
}
#productImage{
	width:150px;
	height:150px;
	border:none;
}
#cartImage{
	width:70px;
	height:70px;
	border:none;
}
#select{
	text-align:right;
}
#commandList{
	text-align:center;
}
#upImage{
	width:15px;
}
#downImage{
	width:15px;
}
</style>
</head>
<script>
	function checkAll(theForm){
		if(theForm.remove.length==undefined){//장바구니 항목이 하나가 출력되었을 경우 처리되는 부분 theForm은 인자로 전달된 form 객체이며 remove는 
			//<input type="checkbox" id="remove" name="remove" value="${cart.kind }"/> 에서 정의된 각 장바구니 항목을 선택하는 체크박스
			//페이지에 remove 라는 이름의 체크 박스가 하나 출력된 단일 객체로 인식되지만 여러 개가 출력되면 배열 객체로 인식됨. length 속성은 자바 스크립트에서 배열 객체에 지원되는 속성이므로
			//theForm.remove.length==undefined 라는 조건을 만족할 경우는 remove 객체가 배열 객체가 아니라는 의미이므로 remove 체크 박스가 하나 출력되었다는 의미
			theForm.remove.checked=theForm.allCheck.checked;//checked:체크박스가 선택되었으면 true, 아니면 false를 반환하는 속성
		}else{//장바구니 항목을 선택하는 체크 박스가 여러 개 출력되었을 경우를 처리하는 부분. 즉, 장바구니 항목이 여러 개일 경우
			for(var i=0; i<theForm.remove.length; i++){
				theForm.remove[i].checked=theForm.allCheck.checked; 
			}
		}
	} 
	
	function checkQty(kind, qty){//장바구니 항목 수량 감소 요청을 할 때 현재 수량이 1이 아닐 경우만 수량 감소 요청을 하게 처리한 함수
		if(qty!=1){
			location.href="dogCartQtyDown.do?kind="+kind;
		}
	}
	//장바구니 항목 삭제 요청을 할 때 삭제할 항목을 체크 박스를 체크해서 선택한다. 체크 박스를 하나씩 체크할 수도 있지만 전체 체크 박스를 체크하는 체크 박스(상단의 체크 박스)를 선택하면
	//전체 체크 박스가 체크되어야 하고 전체 체크 박스를 체크하는 체크 박스를 선택 해제하면 모든 장바구니 항목의 체크 박스가 선택 해제되어야 한다 - 이 부분을 자바 스크립트로 처리
	//이 함수 영역은 onClick=checkAll(this.form) 부분에서 allCheck 체크박스가 클릭될 때 실행됨 checkAll 함수를 호출하면서 form 객체를 인자값으로 전송한다
</script>
<body>
<c:if test="${startMoney!=null }"><!-- 검색에 사용되는 startMoney 값과 endMoney 값을 속성으로 설정. 검색 작업을 하지 않고 목록보기 페이지가 실행된 경우는 이 값들이 null이기 때문에
										해당 속성들을 사용할 때 NullPointException 발생. 이같이 발생하지 않도록 처리해준 부분 -->
	<c:set var="startMoney" value="${startMoney }"></c:set>
</c:if>

<c:if test="${endMoney!=null }">
	<c:set var="endMoney" value="${endMoney }"></c:set>
</c:if>

<section id="listForm">
	<c:if test="${cartList!=null && cartList.size()>0}">
		<h2>장바구니 목록</h2>
		<form method="post">
		<table>
			<tr id="select"><!-- 가격별 검색 부분을 처리 -->
				<td colspan="6">
				<select id="startMoney" name="startMoney">
					<option>=최하=</option>
					<c:choose>
						<c:when test="${startMoney==1000 }">
							<option selected="selected">1000</option>
							<option>2000</option>
							<option>3000</option>
							<option>4000</option>
						</c:when>
						<c:when test="${startMoney==2000}">
							<option>1000</option>
							<option selected="selected">2000</option>
							<option>3000</option>
							<option>4000</option>
						</c:when>
						<c:when test="${startMoney==3000}">
							<option>1000</option>
							<option>2000</option>
							<option selected="selected">3000</option>
							<option>4000</option>
						</c:when>
						<c:when test="${startMoney==4000}">
							<option>1000</option>
							<option>2000</option>
							<option>3000</option>
							<option selected="selected">4000</option>
						</c:when>
						<c:otherwise>
							<option>1000</option>
							<option>2000</option>
							<option>3000</option>
							<option>4000</option>
						</c:otherwise>	
					</c:choose>
				</select>
				
				<select id="endMoney" name="endMoney">
					<option>=최고=</option>
					<c:choose>
						<c:when test="${endMoney==1000 }">
						<option selected="selected">1000</option>
							<option>2000</option>
							<option>3000</option>
							<option>4000</option>
						</c:when>
						<c:when test="${endMoney==2000 }">
							<option>1000</option>
							<option selected="selected">2000</option>
							<option>3000</option>
							<option>4000</option>
						</c:when>
						<c:when test="${endMoney==3000 }">
							<option>1000</option>
							<option>2000</option>
							<option selected="selected">3000</option>
							<option>4000</option>
						</c:when>
						<c:when test="${endMoney==4000 }">
							<option>1000</option>
							<option>2000</option>
							<option>3000</option>
							<option selected="selected">4000</option>
						</c:when>
						<c:otherwise>
							<option>1000</option>
							<option>2000</option>
							<option>3000</option>
							<option>4000</option>
						</c:otherwise>	
					</c:choose>
				</select>
				<input type="submit" value="검색" formaction="dogCartSearch.do"/>
				</td>
			</tr>
			<tr class="tr_top">
				<td><input type="checkbox" id="allCheck" name="allCheck" onclick="checkAll(this.form)"/></td>
				<td>번호</td>
				<td>상품 이미지</td>
				<td>상품명</td>
				<td>가격</td>
				<td>수량</td>
			</tr>
			<c:forEach var="cart" items="${cartList }" varStatus="status">
			<tr>
				<td>
					<input type="checkbox" id="remove" name="remove" value="${cart.kind }"/>
				</td>
				<td>
					${status.index+1 }<!-- 번호 값 계산 --><!-- status.index 속성을 사용하면 forEach문이 실행되는 인덱스 번호 반환. 한 번 실행될 때 0, 두 번째 실행될 때 1이 반환 -->
				</td>
				<td>
					<img src="images/${cart.image }" id="cartImage"/>
				</td>
				<td>
					${cart.kind }
				</td>
				<td>
					<a href="dogCartQtyUp.do?kind=${cart.kind }"><!-- 장바구니 항목의 수량 증가 요청 -->
					<img src="images/up.jpg" id="upImage" border="0"/>
					</a><br>
					${cart.qty }<br>
					<a href="javascript:checkQty('${cart.kind }',${cart.qty })"><!-- 장바구니 항목의 수량 감소 요청 -->
					<img src="images/down.jpg" id="downImage" border="0"/>
					</a>
				</td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="5" style="text-align:center;">
				총 금액:${totalMoney }원
				</td>
			</tr>
			<tr>
				<td colspan="5" style="text-align:center;">
				<input type="submit" value="삭제" formaction="dogCartRemove.do"/>
				</td>
			</tr>
		</table>
		</form>	
	</c:if>
	
	<c:if test="${cartList==null }">
	<section class="div_empty">
		개 정보가 없습니다
	</section>
	</c:if>
	
	<nav id="commandList">
		<a href="dogList.do">쇼핑 계속하기</a>
	</nav>
</section>
</body>
</html>