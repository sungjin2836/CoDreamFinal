<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8;");
%>
<!DOCTYPE html>
<html>
<head>
<sec:csrfMetaTags />
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
<link rel="stylesheet" href="/css/header.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<script type="text/javascript">
	var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var headers = {};
	headers[csrfHeader] = csrfToken;
	
	$.ajax({
		type: "post",
		url: "/getName",
		headers: headers,
		async: true,
		success: function(msg) {
			if (msg != null) {
				$('#userName').html(msg+"님");
			} else {
				
			}
		},
		error: function() {
			alert("잘못된 요청입니다.");
		}
	});
</script>
</head>
<header>
<div style="width:1200px;margin:auto;">
	<%-- <c:if test="${requestScope.loginFailMsg ne null }">
		<div style="display: inline; float:right; margin : 8px;">
			<button class="btn btn-info" onclick="location.href='/member/login'">Sign in</button>
			<button class="btn btn-primary" onclick="location.href='/member/agree'">Sign up</button>
		</div>
	</c:if> --%>
	<div style="display: inline; margin : 8px;">
	<a href="/"><img alt="logo" src="/images/codream.jpg" style="width:120px;height:62px;"></a>
		<button class="btn btn-default" onclick="location.href='/board/classList'">강의</button>
<!-- 		<button class="btn btn-default" onclick="location.href='#'">고객센터</button> -->
		<sec:authorize access="hasRole('ROLE_ADMIN')">
		  <button class="btn btn-default" onclick="location.href='/admin/memberList'">관리자 페이지</button>
		  <button class="btn btn-default" onclick="location.href='/admin/regteacherList'">강사등록리스트</button>
		</sec:authorize>
	</div>

	<div style="display: inline; float:right; margin : 8px;">
		<sec:authorize access="isAnonymous()">
				<button class="btn" onclick="location.href='/member/login'">로그인</button>
				<button class="btn" onclick="location.href='/member/agree'">회원가입</button>
		</sec:authorize>
	</div>
	<sec:authorize access="isAuthenticated()">
		<div style="display: inline; float:right; margin : 8px; color : white;">
<!-- 			<div style="display: inline; color:black;" id = "userName"></div> -->
				<div class="btn btn-success" id="userName" onclick="location.href='/mypage/myInfo'"></div>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				  <button class="btn btn-default" onclick="location.href='/admin/memberList'">관리자 페이지</button>
			</sec:authorize>
			<button class="btn btn-default" onclick="location.href='/mypage/regClassList'">내 강의</button>
			<button class="btn btn-default" onclick="location.href='/logout'">로그아웃</button>
		</div>
	</sec:authorize>
	
</div>
</header>
</html>