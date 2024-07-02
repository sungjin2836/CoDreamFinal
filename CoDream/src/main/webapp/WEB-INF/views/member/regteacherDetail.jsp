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
	<div style="width:300px;display:inline;float:left;">
		<h3><a href=/mypage/myInfo>마이 페이지</a></h3>
		<ul>
			<li><a href=/mypage/myInfo>나의 회원 정보</a></li>
			<li><a href="/mypage/regteacher">강사 등록 신청/현황</a></li>
			<li><a href="/receipt">강의 구매 내역</a></li>
		</ul>
	</div>

	<div style="width:800px;display:inline;float:right;">
		<h2>신청정보 확인</h2>
		<table class="table table-hover">
			<tbody>
				<tr>
					<th>아이디</th>
					<td>${dto.userid}</td>

				</tr>
				<tr>
					<th>제목</th>
					<td>${dto.te_title}</td>
				</tr>
				<tr>
					<th>상태</th>
					<td>${dto.te_admit}</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${dto.te_content}</td>
				</tr>
				<tr>
					<th rowspan="${fn:length(list) + 1}">첨부파일</th>
				</tr>
				<c:forEach items="${list}" var="fDto">
					<tr>
					<td><a href="/file/download?filename=${fDto.filename}">${fDto.origname}.${fDto.extension}</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<%@include file="../footer.jsp" %>
</body>

<script type="text/javascript">
$('#btnUpload').on('click', function(event) {
    event.preventDefault();
    
    var form = $('#uploadForm')[0]
    var data = new FormData(form);
    console.log($("#files")[0].files);
    for(var i = 0; i< $("#files")[0].files.length ; i++) {
    	console.log($("#files")[0].files[i]);
    	data.append("files",$("#files")[0].files[i]);
    }
    
    
    $('#btnUpload').prop('disabled', true);
	
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/file/upload",
        data: data,
        processData: false,
        contentType: false,
        headers: headers,
        cache: false,
        timeout: 600000,
        success: function (e) {
        	$('#btnUpload').prop('disabled', false);
        	console.log(e);
        	$('#file_gid').val(e);
        	form.submit();
        },
        error: function (e) {
            $('#btnUpload').prop('disabled', false);
            alert('fail');
        }
    });
})
</script>
</html>