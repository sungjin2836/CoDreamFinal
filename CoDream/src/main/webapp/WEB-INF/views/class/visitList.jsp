<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="../header.jsp"%>
<div class="container">
	<div style="width:300px;display:inline;float:left;">
		<div><h2><a href="/class/classMain?cl_seq=${cDto.cl_seq}">${cDto.cl_title}</a></h2></div>
		<ul>
			<li><a href="/class/docList?cl_seq=${cDto.cl_seq}">강의 자료</a></li>
			<li><a href="/class/memoList?cl_seq=${cDto.cl_seq}">필기 공유 게시판</a></li>
			<li><a href="/class/visit?cl_seq=${cDto.cl_seq}">출결</a></li>
		</ul>
	</div>
	<div style="width:800px;display:inline;float:right;">
		<h2><a href="/class/visit?cl_seq=${cDto.cl_seq}">${cDto.cl_title}의 출결 현황</a></h2>
		<div>
			<table class="table">
				<tr>
					<th>수강 인원</th>
					<th>수업 일수</th>
					<th>평균 이수율</th>
				</tr>
				<tr>
					<td><span class="allDays">-</span>명</td>
					<td><span class="visitDays">-</span>일</td>
					<td><span class="visitRate">-</span>%</td>
				</tr>
			</table>
		</div>
		<div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>이름</th>
						<th>출석 일수</th>
						<th>이수율</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:forEach var="dto" items="${list}">
							<td><a href="/class/visitDetail?cl_seq=${cDto.cl_seq}&student=${dto.student}">${dto.student}</a></td>
							<td>${dto.visit}</td>
							<td></td>
						</c:forEach>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>