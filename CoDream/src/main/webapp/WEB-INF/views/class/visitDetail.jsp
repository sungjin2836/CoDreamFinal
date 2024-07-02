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
		<h2>${sDto.student}님의 출석 현황</h2>
		
		<table class="table">
			<thead>
				<tr>
					<th>수업 일수</th>
					<th>출석 일수</th>
					<th>이수율</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><span id="allDays">-</span>일</td>
					<td><span id="visitDays">-</span>일</td>
					<td><span id="visitRate">-</span>%</td>
				</tr>
			</tbody>
		</table>
		
		<table class="table">
			<thead>
				<tr>
					<th>일차</th>
					<th>날짜</th>
					<th>출결여부</th>
				</tr>
			</thead>				
			<tbody>
				<!-- 일수에 따라 반복하도록 함 -->
				<tr>
					<td></td>
					<!-- 날짜는 아래의 포맷을 따릅니다 -->
<%-- 					<td><fmt:formatDate value="${mDto.regdate}" pattern="yyyy-MM-dd hh:mm:ss"/></td> --%>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>