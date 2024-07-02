<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수강중인 강의</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">


</head>
<body>
<%@include file="../header.jsp" %>

<div class="container">
	<table class="table">
		<tr>
			<th>pay_seq</th>
			<th>tid</th>
			<th>paydate</th>
			<th>환불</th>
		</tr>
		<c:forEach var="c" items="${lists}">
		<tr>
			<td>${c.pay_seq}</td>
			<td>${c.tid}</td>
			<td>${c.paydate}</td>
			<td><button onclick="refundPay(${c.tid})">환불</button></td>
		</tr>
		</c:forEach>
		
	</table>
	<h1>test1</h1>
</div>
</body>
<script type="text/javascript">

function refundPay(tid){
	$.ajax({
		type:"get",
		url:"./refundPay",
		headers: headers,
		data : "tid="+tid,
		success: function(msg){
			console.log("성공");
			location.href = "./list";
		}
	})
}


</script>


</html>