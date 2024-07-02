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
	<!-- 일부 강의자료 표시 -->
	<div>
	<h3><a href="/class/docList?cl_seq=${cDto.cl_seq}">강의자료</a></h3>
	<table class="table">
		<c:if test="${dList.size() eq 0}">
			<tr>
				<td colspan="3">최근 업로드 된 자료가 없습니다.</td>	
			</tr>
		</c:if>
		<c:forEach var="d" items="${dList}">
			<tr>
				<td><a href="/class/docDetail?doc_seq=${d.doc_seq}">${d.doc_title}</a></td>
				<td>${d.author}</td>
				<td><fmt:formatDate value="${d.regdate}" pattern="yyyy-MM-dd hh:mm"/></td>
			</tr>
		</c:forEach>
	</table>
	</div>
	
	<!-- 일부 필기자료 표시 -->
	<div>
	<h3><a href="/class/memoList?cl_seq=${cDto.cl_seq}">필기자료</a></h3>
	<table class="table">
		<c:if test="${mList.size() eq 0}">
			<tr>
				<td colspan="3">최근 업로드 된 필기가 없습니다.</td>	
			</tr>
		</c:if>
		<c:forEach var="m" items="${mList}">
			<tr>
				<td><a href="/class/memoDetail?doc_seq=${m.me_seq}">${m.me_title}</a></td>
				<td>${m.author}</td>
				<td><fmt:formatDate value="${m.regdate}" pattern="yyyy-MM-dd hh:mm"/></td>
			</tr>
		</c:forEach>
	</table>
	</div>
</div>

</div>
<%@include file="../footer.jsp" %>
</body>
</html>