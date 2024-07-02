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
</head>
<body>
<%@include file="../header.jsp" %>

<div class="container">
<table class="table" id="order">
	<tr>
		<th>구매 상품명</th>
		<td>${cdto.cl_title}</td>
	</tr>
	<tr>
		<th>구매자명</th>
		<td>${dto.id}</td>
	</tr>
	<tr>
		<th>금액</th>
		<td>${cdto.price}</td>
	</tr>
</table>


	<div class="dropdown" style="width: 900px; height: 50px; display: inline-block;">
    <button style="width:700px; text-align:right;" class="btn btn-default dropdown-toggle" type="button" id="coupontagname" data-toggle="dropdown">없음
    <span class="caret"></span></button>
	    <ul style="width:700px; text-align:middle" class="dropdown-menu">
	    <c:forEach var="c" items="${lists}">
	    <li><a onclick="couponresult('${c.discount}','${c.status}','${c.couponname}')">쿠폰명 : ${c.couponname} /
			
			<c:if test="${c.status eq 'P'}">
				할인율 : ${c.discount}% /
			</c:if>
			<c:if test="${c.status eq 'D'}">
				할인금액 : ${c.discount}원 /
			</c:if>
			최대적용금액 : ${c.maxprice}원 /
			쿠폰종류 : ${c.status}
		</a></li>
		</c:forEach>
		<li><a onclick="nocoupon()">없음</a></li>
	    </ul>
 	 </div>
	    <div style="display: inline-block; height: 50px;"><h3 id="couponresult" style="text-align: right;">${cdto.price}</h3></div>
 	 
	<table class="table">
		<tr>
			<th>쿠폰명</th>
			<th>할인</th>
			<th>최대적용금액</th>
			<th>종류</th>
		</tr>
		<c:forEach var="c" items="${lists}">
		<tr>
			<td>${c.couponname}</td>
			
			<c:if test="${c.status eq 'P'}">
				<td>${c.discount}%</td>
			</c:if>
			<c:if test="${c.status eq 'D'}">
				<td>${c.discount}원</td>
			</c:if>
			<td>${c.maxprice}원</td>
			<td>${c.status}</td>
		</tr>
		</c:forEach>
		
	</table>
	
	
	<button onclick="kakao()">kakaopay btn</button>
</div>

<%@include file="../footer.jsp" %>
</body>
<script type="text/javascript">
	var price = document.getElementById("order").rows[2].cells[1].innerHTML;
	var status = document.getElementById("order").rows[2].cells[1].innerHTML;
	console.log(price);
	
	
	
	function couponresult(coupon, status, name){
		console.log(status);
		if(status == 'P'){
			var result = price *(100 - coupon) / 100;
//			alert(result);
			var html = result;
			$('#coupontagname').empty();
			$('#coupontagname').append(name);			
			$('h3').empty();
			/* $('h3').append(price+" -> "+html); */
			$('h3').append(html);
		}else if(status == 'D'){
			var result = price - coupon;
			if(result < 0){
				alert('맞지 않는 쿠폰입니다.');
			}else{
				var html = result;
				$('#coupontagname').empty();
				$('#coupontagname').append(name);
				$('h3').empty();
				/* $('h3').append(price+" -> "+html); */
				$('h3').append(html);
			}
		}
	}
	
	
	function kakao(){
		var result = document.getElementById("couponresult").innerHTML;
		var seq = '${cdto.cl_seq}';
		var title = '${cdto.cl_title}';
		console.log(result);
		console.log(seq);
		console.log(title);
		$.ajax({
			url:'./kakaopay',
			method: 'POST',
			headers: headers,
			data: "result="+result+"&cl_title="+title+"&seq="+seq,
			success:function(data){
				console.log(data);
				var kakao = JSON.parse(data);
				console.log(kakao)
				window.open(kakao.next_redirect_pc_url);
			},
			error:function(error){
				console.log(error);
			}
		})
	}
	
	function nocoupon(){
		$('h3').empty();
		$('h3').append(price);
		
	}
</script>

</html>