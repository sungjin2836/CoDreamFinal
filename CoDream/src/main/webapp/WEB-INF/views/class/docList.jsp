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
		<h2><a href="/class/docList?cl_seq=${cDto.cl_seq}">강의 자료</a></h2>
		<table class="table table-hover">
			<tbody>
				<tr>
					<th>작성자</th>
					<th>자료 제목</th>
					<th>날짜</th>
				</tr>
				<c:if test="${list.size() eq 0}">
					<tr>
						<td colspan="3">최근 업로드 된 자료가 없습니다.</td>	
					</tr>
				</c:if>
				<c:forEach items="${list}" var="dto">
					<tr>
						<td>${dto.author}</td>
						<td><a href="/class/docDetail?cl_seq=${cDto.cl_seq}&doc_seq=${dto.doc_seq}">${dto.doc_title}</a><c:if test="${dto.file_gid ne 0}">&nbsp;<span class="glyphicon glyphicon-floppy-disk"></span></c:if></td>
						<td><fmt:formatDate value="${dto.regdate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<c:if test="${isTeacher}">
					<tr>
						<td colspan="3"><button class="btn" onclick="location.href='/class/insertDoc?cl_seq=${cDto.cl_seq}'">자료 등록</button></td>
					</tr>
				</c:if>
			</tfoot>
		</table>
	</div>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>