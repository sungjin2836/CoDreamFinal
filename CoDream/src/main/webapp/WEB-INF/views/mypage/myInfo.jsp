<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보</title>
</head>
<body>
<%@include file="../header.jsp" %>
<div class="container">
	<div style="width:300px;display:inline;float:left;">
		<h3><a href=/mypage/myInfo>마이 페이지</a></h3>
		<ul>
			<li><a href="/mypage/myInfo">나의 회원 정보</a></li>
			<li><a href="/mypage/regteacher">강사 등록 신청/현황</a></li>
			<li><a href="/receipt">강의 구매 내역</a></li>
		</ul>
	</div>
<div style="width:800px;display:inline;float:right;">
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
				<td><c:if test='${dto.adrecieve eq null}'>N</c:if>${dto.adrecieve}</td>
			</tr>
			<tr>
				<td colspan="2"><button class="btn" onclick="location.href='/modifyForm'">수정</button> </td>
			</tr>
		</tbody>
	</table>
</div>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>