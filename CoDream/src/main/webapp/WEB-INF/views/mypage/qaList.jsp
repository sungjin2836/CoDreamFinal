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
	<table class="table">
		<tr>
			<th>문의 제목</th>
			<th>작성자</th>
			<th>작성일자</th>
			<th>답변여부</th>
		</tr>
		<c:forEach var="c" items="${lists}">
		<tr>
			<td><a href="./qa/detailQa">${c.qu_title}</a></td>
			<td>${c.author}</td>
			<td>${c.regdate}</td>
			<td>${c.replyed}</td>
		</tr>
		</c:forEach>
	</table>
</div>

</body>
</html>