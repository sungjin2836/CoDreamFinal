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
		<h2>필기 상세</h2>
		<table class="table table-hover">
			<tbody>
				<tr>
					<th>제목</th>
					<td>${mDto.doc_title}</td>
				</tr>
				<tr>
					<th>아이디</th>
					<td>${mDto.author}</td>

				</tr>
				<tr>
					<th>작성일자</th>
					<td><fmt:formatDate value="${mDto.regdate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${mDto.doc_content}</td>
				</tr>
				<tr>
					<th rowspan="${fn:length(list) + 1}">첨부파일</th>
				</tr>
				<c:forEach items="${list}" var="fDto">
					<tr>
					<td><a href="/file/download?filename=${fDto.filename}">${fDto.origname}.${fDto.extension}</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>