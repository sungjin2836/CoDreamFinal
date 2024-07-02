<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 약관 동의</title>
</head>
<body>
<%@include file="../header.jsp" %>
<div class="container">
<form action="/member/signup" method="post" onsubmit="return chkbtn()">
	<sec:csrfInput/>
	<h3>회원 약관 동의</h3>
	<div style="width:600px; height: 200px; background-color: skyblue;">
		쏼라쏼라<a href="/">home</a>
	</div>
	<input type="checkbox" id="chk1" name="chk1"><label for="chk1">CoDream 이용 약관에 동의합니다.</label>
	<div style="width:600px; height: 200px; background-color: skyblue;">
		쏼라쏼라<a href="/">home</a>
	</div>
	<input type="checkbox" id="chk2" name="chk2"><label for="chk2">개인정보 수집에 동의합니다.</label>
	<br>
	<input type="submit" value="확인">
</form>
</div>
<%@include file="../footer.jsp" %>
</body>
<script type="text/javascript">
	function chkbtn() {
		var chk1 = document.getElementById("chk1").checked;
		var chk2 = document.getElementById("chk2").checked;
		console.log(chk1);
		console.log(chk2);
		if(chk1 && chk2) {
			return true;
		}
		return false;
		
	}
	
</script>
</html>