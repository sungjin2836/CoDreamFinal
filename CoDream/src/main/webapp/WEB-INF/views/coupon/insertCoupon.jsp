<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠폰등록</title>
</head>
<body>
<%@include file="../header.jsp" %>

<div id="container">
	<form action="/coupon/input" method="post">
	<sec:csrfInput/>
		<table>
			<tr>
				<th>
					쿠폰명
				</th>		
				<td>
					<input type="text" id="couponname" name="couponname" required="required">
				</td>
			</tr>
			<tr>
				<th>
					할인률
				</th>		
				<td>
					<input type="text" id="discount" name="discount" required="required">
				</td>
			</tr>
			<tr>
				<th>
					최대적용금액
				</th>		
				<td>
					<input type="text" id="maxprice" name="maxprice" required="required">
				</td>
			</tr>
			<tr>
				<th>
					쿠폰종류
				</th>		
				<td>
					<input type="text" id="status" name="status" required="required">
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="쿠폰 등록"></td>
			</tr>	
		</table>
	</form>	
	<input type="button" value="쿠폰리스트" onclick="location.href='/coupon/list'">
</div>
<%@include file="../footer.jsp" %>
</body>
</html>