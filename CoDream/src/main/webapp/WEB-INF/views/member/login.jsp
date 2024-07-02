<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<%@include file="../header.jsp" %>
<div class="container">
<div style="width:500px;margin:auto;">
	<form action="/login" method="post">
		<sec:csrfInput/>
		<div class="input-group">
		<span class="input-group-addon person"><i class="glyphicon glyphicon-user"></i></span>
		<input class="form-control" type="text" name="id" autocomplete="off"><br>
		</div>
		<div class="input-group">
		<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
		<input class="form-control" type="password" name="password" autocomplete="off"><br>
		</div>
		<input type="checkbox" name="remember-me" id="autoLogin">자동 로그인<br>
		<input class="btn btn-block btn-lg" type="submit" value="로그인"><br>
		${requestScope.loginFailMsg}
	</form>
	<div style="text-align: center">
		<a href="${naver_url}"><img style="width:200px" src="/images/naver_login.png"></a><br>
		<a href="${kakao_url}"><img style="width:200px" src="/images/kakao_login.png"></a><br>
		<a href="${google_url}"><img style="width:200px" src="/images/google_login.png"></a>
	</div>
</div>
</div>
<%@include file="../footer.jsp" %>
</body>
<script type="text/javascript">
</script>
</html>