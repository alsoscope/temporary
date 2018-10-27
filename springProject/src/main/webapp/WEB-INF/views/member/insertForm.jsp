<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>insertForm</title>
<%-- jQuery+Ajax로 id 중복체크 --%>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<%-- open API --%>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
function openDaumPostcode(){
	new daum.Postcode({
		oncomplete:function(data){
			 
			document.getElementById('zipcode').value=data.postcode1+"-"+data.postcode2;
			document.getElementById('addr').value=data.address;
 		}
		
	}).open();
}//openDaumPostcode()
</script>
<script>
	function check(){//데이터 유효성 체크 하기 위한 함수
		if($('#id').val()==''){
			alert("id를 입력 하세요.");
			$('#id').val('').focus(); //지우고(val('')) 포커스 설정
			return false;
		}
	
		if($('#pwd').val()==''){
			alert("암호를 입력하세요.");
			$('#pwd').focus();
			return false;
		}
		
		if($('#pwd2').val()==''){
			alert("암호확인을 입력하세요.");
			$('#pwd2').focus();
			return false;
		}
					//값을 얻어오는 val()
		if($('#pwd').val() != $('#pwd2').val()){
			alert("암호가 일치 하지 않습니다.");
			$('#pwd').focus();
			return false;
		}
		
		if($('#name').val()==''){
			alert("이름을 입력 하세요.");
			$('#name').val.focus();
			return false;
		}
		return true;
	}//check()

	//-----------------------
	//ajax 이용하여 id사용 여부 체크
	//-----------------------
	function confirmIDCheck(){
		if($('#id').val()==''){
			alert("id를 입력하세요");
			$('#id').focus();
		}else{
			//alert($('#id').val());
			//==jQuery.ajax({
			$.ajax({
				type : 'POST',
				url : 'idCheck.do',
				data : "id="+$('#id').val(),
				dataType : 'JSON', //서버에서 넘어오는 자료타입
				success : function(data){ //데이터를 받은 후
					//alert(data.check);
					if(data.check==-1){
						alert("사용중인 id입니다.");
						$('#id').val("").focus();
					}else{
						alert("사용 가능한 id입니다.");
						$('#pwd').val("").focus();
					}//else
				}//success
			});
		}//else
	}//confirmIDCheck
</script>
</head>
<body>
<center><h2>회원가입</h2></center>
<form method="post" name="writeForm" action="insertPro.do" onsubmit="return check()">
<table width="50%" align=center>
	<tr>
		<td>ID</td>
		<td><input type="text" id="id" name="id" size="10">
		<input type="button" id="btn" value="ID중복체크" onClick="confirmIDCheck()">
		</td>
	</tr>

	<tr>
		<td>PWD</td>
		<td><input type="password" id="pwd" name="pwd" size="10"></td>
	</tr>

	<tr>
		<td>PWD2</td>
		<td><input type="password" id="pwd2" name="pwd2" size="10"></td>
	</tr>
	
	<tr>
		<td>이름</td>
		<td><input type="text" id="name" name="name" size="10"></td>
	</tr> 

	<tr>
		<td>주민번호</td>
		<td><input type="text" id="jumin1" name="jumin1" size="6" maxlength="6">-<input type="text" id="jumin2" name="jumin2" size="7" maxlength="7">
		</td>
	</tr>
	
	<tr>
		<td>Email</td>
		<td><input type="text" id="email" name="email" size="20"></td>
	</tr>
	
	<tr>
		<td>우편번호</td>
		<td><input type="text" name="zipcode" id="zipcode" size="7">
		<input type="button" value="우편번호 찾기" onClick="openDaumPostcode()"></td>
	</tr>
	
	<tr>
		<td>주소</td>
		<td><input type="text" name="addr" id="addr" size="50">
		</td>
	</tr>

	<tr>
		<td>직업</td>
		<td><select name="job" id="job">
			<option value="0">선택</option>
			<option value="회사원">회사원</option>
			<option value="전문직">전문직</option>
			<option value="학생">학생</option>
			<option value="공무원">공무원</option>
			<option value="법조인">법조인</option>
			<option value="기타">기타</option>
		</select>
		</td>
	</tr>

	<tr>
		<td>블로그</td>
		<td><input type="text" id="blog" name="blog" size="20" placeholder="http://"></td>
	</tr>

	<tr>
		<td colspan="2" align="center" >
			<input type="submit" value="회원가입">
			<input type="reset" value="다시입력">
			<input type="button" value="가입안함" onClick="location='.main.member.main.do'">
		</td>
	</tr>
</table>
</form>
</body>
</html>