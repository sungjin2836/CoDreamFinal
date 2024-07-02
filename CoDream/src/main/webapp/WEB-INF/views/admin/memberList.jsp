<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 조회</title>
</head>
<body>
	<%@include file="../header.jsp"%>
	<div class="container">
	<div style="width:300px;display:inline;float:left;">
		<h3><a href="/admin/memberList">관리자 게시판</a></h3>
		<ul>
			<li><a href="/admin/memberList">회원 관리</a></li>
			<li><a href="/admin/regteacherList">강사 신청 대기</a></li>
		</ul>
	</div>
	
	<div style="width:800px;display:inline;float:right;">
		<h2><a href="/admin/memberList">회원 정보 보기</a></h2>
		<table class="table table-hover">
			<tbody>
				<tr>
					<th>아이디</th>
					<th>이름</th>
					<th>등록일</th>
					<th>탈퇴여부</th>
					<th>권한</th>
					<th>회원탈퇴</th>
				</tr>
				<c:forEach items="${list}" var="uDto">
					<tr>
						<td>${uDto.dto.id}</td>
						<td>${uDto.dto.name}</td>
						<td>${uDto.dto.regdate}</td>
						<td>${uDto.dto.delflag}</td>
						<td>
							<c:set var="flag" value="0"/>
							<c:forEach items="${uDto.authorities}" var="auth">
								<c:if test="${auth eq 'ROLE_ADMIN'}">
									<c:set var= "flag" value="${flag + 10}"/>
								</c:if>
							</c:forEach>
							<c:forEach items="${uDto.authorities}" var="auth">
								<c:if test="${auth eq 'ROLE_TEACHER'}">
									<c:set var= "flag" value="${flag + 1}"/>
								</c:if>
							</c:forEach>
							<select onchange="changeRole('${uDto.dto.id}', this.value)">
								<option value="ROLE_ADMIN" <c:if test="${flag==11}">selected</c:if>>관리자</option>
								<option value="ROLE_TEACHER" <c:if test="${flag==1}">selected</c:if>>강사</option>
								<option value="ROLE_USER" <c:if test="${flag==0}">selected</c:if>>일반회원</option>
							</select>
						</td>
						<td>
							<c:choose>
								<c:when test="${uDto.dto.delflag eq 'N'}">
									<button onclick="deleteUser('${uDto.dto.id}')">탈퇴</button>
								</c:when>
								<c:otherwise>
									<button onclick="reviveUser('${uDto.dto.id}')">복구</button>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="6" style="text-align: center">
					
						<a href="./memberList?page=1"><<</a>
						<a href="./memberList?page=${page.page-page.step}"><</a>
						<c:forEach begin="${page.pagestart}" end="${page.pageend}" step="1" var="var">
							<a href="./memberList?page=${var}" <c:if test="${page.page eq var}">style="font-weight: bold"</c:if>>${var}</a>
						</c:forEach>
						<a href="./memberList?page=${page.page+page.step}">></a>
						<a href="./memberList?page=999">>></a>
					<td>
				</tr>
			</tfoot>
		</table>
		</div>
	</div>
<%@include file="../footer.jsp" %>
</body>
<script type="text/javascript">
	function changeRole(id, role) {
		$.ajax({
			type: "post",
			url: "/admin/changeRole",
			headers: headers,
			data: "id="+id+"&role="+role,
			async: true,
			success: function(msg) {
				console.log(msg);
				if (msg == "true") {
					swal({
						title : "권한 수정 완료!",
				    	closeOnClickOutside : false
					}, function(){
						location.reload();
					});
				} else {
					swal("권한 수정에 오류가 발생했습니다.");
				}
			},
			error: function() {
				alert("잘못된 요청입니다.");
			}
		});
	}

	function deleteUser(id) {
		$.ajax({
			type: "post",
			url: "/admin/deleteUser",
			headers: headers,
			data: "id="+id,
			async: true,
			success: function(msg) {
				console.log(msg);
				if (msg == "true") {
					swal({
						title : "탈퇴 완료!",
				    	closeOnClickOutside : false
					}, function(){
						location.reload();
					});
				} else {
					swal("탈퇴 처리에 오류가 발생했습니다.");
				}
			},
			error: function() {
				alert("잘못된 요청입니다.");
			}
		});
	}
	
	function reviveUser(id) {
		$.ajax({
			type: "post",
			url: "/admin/deleteUser",
			headers: headers,
			data: "id="+id,
			async: true,
			success: function(msg) {
				console.log(msg);
				if (msg == "true") {
					swal({
						title : "복구 완료!",
				    	closeOnClickOutside : false
					}, function(){
						location.reload();
					});
				} else {
					swal("복구 처리에 오류가 발생했습니다.");
				}
			},
			error: function() {
				alert("잘못된 요청입니다.");
			}
		});
	}
</script>
</html>