<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="../header.jsp"%>
<div class="container">
	<div style="width:300px;display:inline;float:left;">
		<div><h2><a href="/class/classMain?cl_seq=${cDto.cl_seq}">${cDto.cl_title}</a></h2></div>
		<ul>
			<li><a href="/class/docList?cl_seq=${cDto.cl_seq}">강의 자료</a></li>
			<li><a href="/class/memoList?cl_seq=${cDto.cl_seq}">필기 공유 게시판</a></li>
			<li><a href="/class/visit?cl_seq=${cDto.cl_seq}">출결</a></li>
		</ul>
	</div>
	<div style="width:800px;display:inline;float:right;">
		<h2>필기 자료 등록</h2>
		<form action="/class/insertMemo" method="POST" id="uploadForm">
			<sec:csrfInput/>
			<input type="hidden" name="cl_seq" value="${cDto.cl_seq}">
			<input type="hidden" name="file_gid" id="file_gid">
			<table class="table">
				<tr>
					<th>
						작성자
					</th>
					<td>
						${rDto.name}
					</td>
				</tr>
				<tr>
					<th>
						제목
					</th>
					<td>
						<input class="form-control" type="text" name="me_title" required="required"/>
					</td>
				</tr>
				<tr>
					<th>
						내용
					</th>
					<td>
						<textarea class="form-control" rows="10" cols="20" name="me_content" style="resize:vertical;"></textarea>
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
    
    var me_title = form.me_title;
    var me_content = form.me_content;
    
    // 유효성 검사
    if(me_title.value == '') {
    	swal("경고","제목을 입력해 주세요.");
    	me_title.focus();
    	return;
    }
    
    if((me_title.value).length > 20) {
    	swal("경고","제목은 20자 이하여야 합니다.");
    	me_title.focus();
    	return;
    }
    
    if(me_content.value == '') {
    	swal("경고","내용을 입력해 주세요.");
    	me_title.focus();
    	return;
    }
    
    if((me_content.value).length > 256) {
    	swal("경고","내용은 256자 이하여야 합니다.");
    	me_title.focus();
    	return;
    }
    
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
            swal('오류','첨부파일을 확인해 주세요.');
        }
    });
})
</script>
</html>