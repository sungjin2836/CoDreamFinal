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
			<li><a href="regteacherForm">강사 등록 신청/현황</a></li>
			<li><a href="/receipt">강의 구매 내역</a></li>
		</ul>
	</div>
<div style="width:800px;display:inline;float:right;">
	<h2>강사 등록 신청</h2>
	<form action="/mypage/regteacher" method="POST" id="uploadForm">
		<sec:csrfInput/>
		<input type="hidden" name="file_gid" id="file_gid">
		<table class="table">
			<tr>
				<th>
					작성자
				</th>
				<td>
					${dto.name}
				</td>
			</tr>
			<tr>
				<th>
					제목
				</th>
				<td>
					<input class="form-control" type="text" name="te_title" required="required"/>
				</td>
			</tr>
			<tr>
				<th>
					내용
				</th>
				<td>
					<textarea class="form-control" rows="10" cols="20" name="te_content" style="resize:vertical;"></textarea>
				</td>
			</tr>
			<tr>
				<th>
					첨부파일
				</th>
				<td>
					<input type="file" multiple="multiple" id="files"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<button class="btn" id='btnUpload'>확인</button>
				</td>
			</tr>
		</table>
	</form>
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