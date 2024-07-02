<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠폰등록</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="../header.jsp" %>
<%
String pg_token = request.getParameter("pg_token");
String str = (String)session.getAttribute("payinfo");
String seq = request.getParameter("seq");
%>

<div class="container">
	<table class="table">
		<tr>
			<th>구매자</th><td>${dto.id}</td>
		</tr>	
		<tr>
			<th>구매상품</th><td id="name"></td>
		</tr>
		<tr>
			<th>구매가격</th><td id="amount"></td>
		</tr>
	</table>
	<h1 id="tid"></h1>
<%-- 	<h1>${cdto.cl_seq}</h1> --%>
	<h1><%=seq %></h1>
	<input type="hidden" id="tid">
</div>
<%@include file="../footer.jsp" %>
</body>
<script type="text/javascript">
	

window.onload = function(){
	$.ajax({
		url:'./kakaoapprove',
		method: 'GET',
		headers: headers,
		data: "pg_token=<%=pg_token%>&seq=<%=seq%>",
		success:function(data){
			console.log("성공");
			console.log(data);
			var kaka = JSON.parse(data);
			console.log(kaka.tid);
			console.log(kaka.item_name);
			console.log(kaka.amount.total);
			$('#name').html(kaka.item_name);
			$('#amount').html(kaka.amount.total);
			$('#tid').html(kaka.tid);
			
			
			
		}, 
		error:function(error){
			console.log(error);
			location.href='/';
		}
	})
}
	


</script>

</html>