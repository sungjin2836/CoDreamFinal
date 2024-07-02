<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 강의</title>
</head>
<body>

<%@include file="../header.jsp"%>
<div class="container">
	<h2><a href="./classList">개설 강의</a></h2>
	<c:forEach var="h" begin="0" end="10" step="1" items="${hList}">
	<button class="btn" onclick="location.href='/board/classList?hash='+'${h.value}'">#${h.value}</button>
	</c:forEach>
	<sec:authorize access="hasRole('ROLE_TEACHER')">
	<button class="btn btn-success" onclick="javascript:location.href='./classInput'">강의 개설</button>
	</sec:authorize>
	<c:forEach var="cl" items="${cList}">
		<div class="row">
			<h3><a href="./classDetail?cl_seq=${cl.cl_seq}">${cl.cl_title}</a></h3>
			<p>
				<c:forEach var="hash" items="${cl.hashList}">
					#${hash["value"]}&nbsp;
				</c:forEach>
			</p>
			<p>${fn:substring(cl.cl_content,0,40)}...</p>
			<p>${cl.teacher}</p>
			<p><fmt:formatDate value="${cl.startday}" pattern="yyyy년 MM월 dd일"/> ~ <fmt:formatDate value="${cl.endday}" pattern="yyyy년 MM월 dd일"/></p>
			<p>${cl.price}￦</p>
		</div>
	</c:forEach>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>