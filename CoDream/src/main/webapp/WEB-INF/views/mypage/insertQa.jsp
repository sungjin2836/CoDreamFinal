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

<div id="container">
	<form action="/qa/insert" method="post">
	<sec:csrfInput/>
		<table>
			<tr>
				<th>
					문의 제목
				</th>		
				<td>
					<input type="text" id="qatitle" name="qatitle" required="required">
				</td>
			</tr>
			<tr>
				<th>
					작성자 명
				</th>		
				<td>
					<input type="text" id="qaname" name="qaname" value="${dto.id}" readonly="readonly">
				</td>
			</tr>
			<tr>
				<th>
					문의 내용
				</th>		
				<td>
					<textarea id="qatext" name="qatext" rows="10" cols="30" placeholder="문의 내용"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="문의 등록"></td>
			</tr>	
		</table>
	</form>	
</div>
</body>
</html>