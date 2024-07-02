<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<%@include file="./header.jsp" %>

<!-- <div><img alt="광고용 슬라이드" src="/images/banner.PNG" style="margin:auto;"/></div> -->
<div class="container">
	<div>
		<h3>신규 개설 강의</h3>
		<table class="table">
			<c:if test="${ncList.size() eq 0}">
				<tr>
					<td colspan="3">최근 개설된 강의가 없습니다.</td>	
				</tr>
			</c:if>
			<c:forEach var="nc" items="${ncList}">
				<tr>
					<td><a href="/board/classDetail?cl_seq=${nc.cl_seq}">${nc.cl_title}</a></td>
					<td>${nc.teacher}</td>
					<td>${nc.price}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div>
		<h3>개강 임박 강의</h3>
		<table class="table">
			<c:if test="${dcList.size() eq 0}">
				<tr>
					<td colspan="3">개강이 임박한 강의가 없습니다.</td>	
				</tr>
			</c:if>
			<c:forEach var="dc" items="${dcList}">
				<tr>
					<td><a href="/board/classDetail?cl_seq=${dc.cl_seq}">${dc.cl_title}</a></td>
					<td>${dc.teacher}</td>
					<td>${dc.price}</td>
					<td><fmt:formatDate value="${dc.startday}" pattern="yyyy년 MM월 dd일"/> 개강</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
<button onclick="location.href='/file/uploadTest'">파일 테스트</button>
<button onclick="location.href='/class/studentTest?cl_seq=1'">수강신청 테스트</button>
<button onclick="location.href='/class/classMain?cl_seq=1'">강의페이지 테스트</button>
<%@include file="./footer.jsp" %>
</body>
</html>
