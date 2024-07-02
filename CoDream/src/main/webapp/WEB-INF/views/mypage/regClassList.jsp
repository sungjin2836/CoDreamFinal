<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="../header.jsp" %>
<div class="container">
	<div style="width:300px;display:inline;float:left;">
	<h2>내 강의</h2>
		<ul>
			<li><a href='/mypage/regClassList'>수강 중인 강의</a></li>
			<sec:authorize access="hasRole('ROLE_TEACHER')">
			<li><a href='/mypage/teachClassList'>나의 개설 강의</a></li>
			</sec:authorize>
		</ul>
	</div>
	<div style="width:800px;display:inline;float:right;">
		<h2>수강 중인 강의</h2>
		<c:if test="${clist.size() eq 0}">
			<div>등록한 강의가 없습니다.</div>	
		</c:if>
		<c:forEach var="cl" items="${clist}">
			<div class="row">
				<h3><a href="/class/classMain?cl_seq=${cl.cl_seq}">${cl.cl_title}</a></h3>
				<p>
					<c:forEach var="hash" items="${cl.hashList}">
						#${hash["value"]}&nbsp;
					</c:forEach>
				</p>
				<p>${fn:substring(cl.cl_content,0,40)}...</p>
				<p>${cl.teacher}</p>
				<p><fmt:formatDate value="${cl.startday}" pattern="yyyy년 MM월 dd일"/> ~ <fmt:formatDate value="${cl.endday}" pattern="yyyy년 MM월 dd일"/></p>
			</div>
		</c:forEach>
	</div>
	
</div>
</body>
</html>