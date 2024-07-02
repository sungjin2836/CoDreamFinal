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
			<li><a href='/mypage/teachClassList'>나의 개설 강의</a></li>
		</ul>
	</div>	
	<div style="width:800px;display:inline;float:right;">
	<h2>나의 개설 강의</h2>
		<table class="table">
				<tr>
					<th>강의명</th>
					<th>개강일</th>
					<th>종강일</th>
				</tr>
			<c:if test="${clist.size() eq 0}">
				<tr>
					<td colspan="3">개설한 강의가 없습니다.</td>	
				</tr>
			</c:if>
			<c:forEach var="c" items="${clist}">
				<tr>
					<td><a href="/class/classMain?cl_seq=${c.cl_seq}">${c.cl_title}</a></td>
					<td><fmt:formatDate value="${c.startday}" pattern="yyyy-MM-dd"/></td>
					<td><fmt:formatDate value="${c.endday}" pattern="yyyy-MM-dd"/></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
</body>
</html>