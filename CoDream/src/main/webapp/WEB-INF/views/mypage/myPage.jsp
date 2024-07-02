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
			<th></th>
			<th>강의</th>
			<th>할인</th>
			<th>최대적용금액</th>
		</tr>
		<c:forEach var="c" items="${lists}">
		<tr>
			<td>${c.coupon_seq}</td>
			<td>${c.couponname}</td>
			
			<c:if test="${c.status eq 'P'}">
				<td>${c.discount}%</td>
			</c:if>
			<c:if test="${c.status eq 'D'}">
				<td>${c.discount}원</td>
			</c:if>
			<td>${c.maxprice}원</td>
			<td><button onclick="giveCoupon(${c.coupon_seq})">지급</button></td>
		</tr>
		</c:forEach>
		
	</table>
	<h1>test1</h1>
</div>

</body>
</html>