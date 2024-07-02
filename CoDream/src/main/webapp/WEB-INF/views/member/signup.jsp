<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>
<%@include file="../header.jsp" %>
<div class="container">
<input type="hidden" id="chkval" value="1">
<form action="/member/register" method="post" onsubmit="return check()">
	<input type="hidden" id="uid" name="uid" value="${uid}">
	<input type="hidden" id="site" name="site" value="${site}">
	<sec:csrfInput/>
	<table>
		<tr>
			<th>
				아이디
			</th>
			<td>
				<input type="text" id="id" name="id" required="required" autocomplete="off">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<p style="height: 20px;" id="result"></p>
			</td>
		</tr>
		<tr>
			<th>
				비밀번호
			</th>
			<td>
				<input type="password" id="pw" name="password" required="required" autocomplete="off">
			</td>
		</tr>
		<tr>
			<th>
				비밀번호 확인
			</th>
			<td>
				<input type="password" id="pwChk" required="required" autocomplete="off">
			</td>
		</tr>
		<tr>
			<th>
				이름
			</th>
			<td>
				<input type="text" id="name" name="name" required="required" autocomplete="off">
			</td>
		</tr>
		<tr>
			<th>
				핸드폰 번호
			</th>
			<td>
				<input type="text" id="phone" name="phone" required="required" autocomplete="off">
			</td>
		</tr>
		<tr>
			<th>
				이메일
			</th>
			<td>
				<input type="text" id="email" name="email" required="required" autocomplete="off">
			</td>
		</tr>
		<tr>
			<th>
				주소
			</th>
			<td>
				<input type="text" id="address" name="address" required="required" autocomplete="off">
			</td>
		</tr>
		<tr>
			<th></th>
			<td><input type="checkbox" id="adrecieve" name="adrecieve"><label for="adrecive">마케팅 수신 동의</label></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="회원 가입"></td>
		</tr>
	</table>
</form>
</div>
<%@include file="../footer.jsp" %>
</body>
<script type="text/javascript">
	function check(){
		var pw = document.getElementById('pw').value;
		var pwChk = document.getElementById('pwChk').value;
		var chkval = document.getElementById('chkval').value;
		
// 		alert(pw + "/" + pwCheck + "/" + chkval);
		
		if (pw != pwChk) {
			swal('회원가입 오류', '비밀번호를 확인해주세요');
// 			alert("비밀번호를 확인해주세요");
			return false;
		} else if(chkval == '0'){
			swal('회원가입 오류', '아이디를 확인해주세요');
// 			alert("아이디를 확인해주세요");
			return false;
		}else{
			return true;
		}
	}
	
	
	$(document).ready(function() {
		if("${site}"== "naver") {
			$('#name').val("${dto.name}");
			$('#phone').val("${dto.phone}");
			$('#email').val("${dto.email}");
		}
		
		if("${site}"== "kakao") {
			$('#email').val("${dto.email}");
		}
		
		if("${site}"== "google") {
			$('#name').val("${dto.name}");
			$('#phone').val("${dto.phone}");
			$('#email').val("${dto.email}");
		}
		
		
		// 아이디 중복체크
		$('#id').keyup(function() {
			var idLength = $(this).val().length;
			var id = '';
			id = $(this).val();
			if(id.indexOf(" ") != -1) {
				$('#result').css("color", "red");
				$('#result').html('공백이 포함된 아이디를 사용하실 수 없습니다.');
				$('#chkval').val('0');
			} else if(idLength <= 5 && idLength >= 15) {
				$('#result').css("color", "red");
				$('#result').html('아이디는 6~15자리만 입력해주세요');
				$('#chkval').val('0');
			} else if(idLength > 5) {
				$.ajax({
					type: "post",
					url: "/member/idChk",
					headers: headers,
					data: "id="+$(this).val(),
					async: true,
					success: function(msg) {
						console.log(msg);
						if (msg == "true") {
							$('#chkval').val('0');
							$('#result').html('사용 불가능한 아이디 입니다.');
							$('#result').css({'color':'red', 'margin-bottom':'15px'});
						} else {
							$('#chkval').val('1');
							$('#result').html('사용 가능한 아이디 입니다.');
							$('#result').css({'color':'green', 'margin-bottom':'15px'});
						}
					},
					error: function() {
						alert("잘못된 요청입니다.");
					}
				});
			} else {
				$('#result').css({'color':'red', 'margin-bottom':'15px'});
				$('#result').html('6자리 이상만 사용 가능합니다.');
				$('#chkval').val('0');
			}
		});
	}); 
</script>
</html>