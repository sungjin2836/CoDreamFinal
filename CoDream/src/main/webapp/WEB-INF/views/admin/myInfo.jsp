<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<%@include file="../header.jsp" %>

<!-- 필요없으면 삭제 예정인 파일 -->
<div class="container">
	<h2>나의 정보 보기</h2>
	<table class="table table-hover">
		<tbody>
			<tr>
				<th>아이디</th>
				<td>${dto.id}</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${dto.name}</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>${dto.address}</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>${dto.phone}</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${dto.email}</td>
			</tr>
			<tr>
				<th>자기소개</th>
				<td>${dto.resume}</td>
			</tr>
			<tr>
				<th>등록일</th>
				<td>${dto.regdate}</td>
			</tr>
			<tr>
				<th>마케팅 수신 여부</th>
				<td>${dto.adrecieve}</td>
			</tr>
		</tbody>
	</table>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>