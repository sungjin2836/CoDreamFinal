<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신규 강의 개설</title>
<script type="text/javascript" src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>
<%@include file="../header.jsp"%>
<div class="container">
<h2>신규 강의 개설</h2>
<form action="/board/classInput" method="post" name="classform">
	<sec:csrfInput/>
	<div>강의 제목</div>
	<div><input type="text" class="form-control" name="cl_title" placeholder="20글자 이하로 작성해주세요"></div>

	<table>
		<tr>
			<th>수강료</th>
			<td><input type="number" class="form-control" name="price" step="100"></td>
			<td>원</td>
		</tr>
		<tr>
			<th>수강 정원</th>
			<td><input type="number" class="form-control" name="maxmember"></td>
			<td>명</td>
		</tr>
		<tr>
			<th>강의 기간</th>
			<td><input type="date" class="form-control" name="sday"></td>
			<td><input type="date" class="form-control" name="eday"></td>
		</tr>
	</table>
		
	<div>강의 시간표</div>
	<div>선택한 요일의 시간이 반영됩니다.</div>
	<table class="table">
		<tr>
			<th>요일</th>
			<th><input type="checkbox" class="day" value="일"> 일</th>
			<th><input type="checkbox" class="day" value="월"> 월</th>
			<th><input type="checkbox" class="day" value="화"> 화</th>
			<th><input type="checkbox" class="day" value="수"> 수</th>
			<th><input type="checkbox" class="day" value="목"> 목</th>
			<th><input type="checkbox" class="day" value="금"> 금</th>
			<th><input type="checkbox" class="day" value="토"> 토</th>
		</tr>
		<tr>
			<th>시작 시간</th>
			<td><input type="time" class="form-control starttime" readonly="readonly"></td>
			<td><input type="time" class="form-control starttime" readonly="readonly"></td>
			<td><input type="time" class="form-control starttime" readonly="readonly"></td>
			<td><input type="time" class="form-control starttime" readonly="readonly"></td>
			<td><input type="time" class="form-control starttime" readonly="readonly"></td>
			<td><input type="time" class="form-control starttime" readonly="readonly"></td>
			<td><input type="time" class="form-control starttime" readonly="readonly"></td>
		</tr>
		<tr>
			<th>종료 시간</th>
			<td><input type="time" class="form-control endtime" readonly="readonly"></td>
			<td><input type="time" class="form-control endtime" readonly="readonly"></td>
			<td><input type="time" class="form-control endtime" readonly="readonly"></td>
			<td><input type="time" class="form-control endtime" readonly="readonly"></td>
			<td><input type="time" class="form-control endtime" readonly="readonly"></td>
			<td><input type="time" class="form-control endtime" readonly="readonly"></td>
			<td><input type="time" class="form-control endtime" readonly="readonly"></td>
		</tr>
	</table>
	
	<div>강의 설명</div>
	<textarea name="cl_content" class="form-control" style="resize:vertical;"></textarea>
	
	<div>강의장 주소</div>
	<input type="button" class="btn" value="주소 검색" onclick="goPopup()">
	<input type="text" class="form-control" name="address" placeholder="주소 검색을 해주세요" readonly="readonly"> 
	
	<div>연관 태그</div>
	<input type="text" class="form-control" name="hash" placeholder="두 개 이상의 태그는 띄어쓰기로 구분됩니다.">
	
	<input type="button" class="btn btn-primary btn-block btn-lg" value="강의등록" onclick="classInput()">
	<input type="hidden" name="term">
</form>
	<!-- google reCaptcha -->
<!-- 	<div class="g-recaptcha" data-sitekey="6LdWD44bAAAAAHQYVc7qAGmEiVMk1RD4UpNIiR4h"></div> -->
	
</div>
<%@include file="../footer.jsp" %>
</body>
<script type="text/javascript">
window.onload = function() {
	var days = document.getElementsByClassName("day");
	for(var i = 0; i < days.length; i ++) {
		days[i].onclick = function() {
			for(var j = 0; j < days.length; j ++) {
				swapro(j, days[j].checked);
			}
		}
	}
}

function swapro(j, chk) {
	var starttimes = document.getElementsByClassName("starttime");
	var endtimes = document.getElementsByClassName("endtime");
	
	starttimes[j].readOnly = !chk;
	endtimes[j].readOnly = !chk;
}

function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("./jusoPopup","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
} 

function classInput() {
	
	// 리캡챠 통과 여부 확인
// 	if (grecaptcha.getResponse() == "")
// 	{
// 		swal("경고","먼저 자동봇 여부 확인을 진행해주세요.");
// 		return false;
//     }
	
	
	// 유효값 체크
	
	// 모든 값이 제대로 들어가있는지
	// 강의제목 40자 이내인지
	var cl_title = document.getElementsByName("cl_title")[0];
	if(cl_title.value.length <= 0 || cl_title.value.length > 20) {
		swal("경고","강의 제목은 1자리 이상 20자 이하여야 합니다.");
		cl_title.focus();
		return false;
	}	

	// 수강료에 값이 있는지
	var price = document.getElementsByName("price")[0];
	if(price.value < 0) {
		swal("경고","수강료를 입력해 주세요.");
		price.focus();
		return false;
	}
	
	// 수강정원에 값이 있는지
	var maxmember = document.getElementsByName("maxmember")[0];
	if(maxmember.value < 0) {
		swal("경고","수강정원을 입력해 주세요.");
		maxmember.focus();
		return false;
	}
	
	// 강의 기간 시작일이 강의 기간 종료일보다 크지 않은지
	var sday = document.getElementsByName("sday")[0];
	var eday = document.getElementsByName("eday")[0];
	if(sday.value > eday.value){
		swal("경고","종료일은 시작일보다 전일 수 없습니다.")
		sday.focus();
		return false;
	}
	
	// 강의 기간이 제대로 입력되었는지
	if(sday.value.length == 0) {
		swal("경고","시작일을 입력해 주세요.");
		sday.focus();
		return false;
	}
	
	// 강의 기간이 제대로 입력되었는지
	if(eday.value.length == 0) {
		swal("경고","시작일을 입력해 주세요.");
		eday.focus();
		return false;
	}
	
	
	// 강의 요일로 지정했지만, 시간이 입력되지 않았을 때
	var days = document.getElementsByClassName("day");
	var starttimes = document.getElementsByClassName("starttime");
	var endtimes = document.getElementsByClassName("endtime");
	
	var count = 0;
	for(var i = 0; i < days.length; i ++) {
		if(days[i].checked == true) { // 지정한 요일의
			count ++;
			// 강의시간을 체크
			if(starttimes[i].value == "") {
				swal("경고","시작시간을 지정해주세요.");
				starttimes[i].focus();
				return false;
			}
			if(endtimes[i].value == ""){
				swal("경고","종료시간을 지정해주세요.");
				endtimes[i].focus();
				return false;
			}
		}
	} 
	
	// 강의 요일이 하나도 지정되어있지 않을 경우
	if(count == 0) {
		swal("경고","강의는 일주일에 한 번 이상 진행해야 합니다.")
		return false;
	}

	// 강의 설명이 4천자를 넘어가지 않는지
	var cl_content = document.getElementsByName("cl_content")[0];
	console.log(cl_content);

	if(cl_content.value.length > 4000) {
		swal("경고","강의 설명은 4천자를 넘어갈 수 없습니다.");
		cl_content.focus();
		return false;
	}
	
	// 강의 설명이 비어있지 않은지
	if(cl_content.value.length == 0) {
		swal("경고","강의 설명을 입력해 주세요.");
		cl_content.focus();
		return false;
	}
	
	// 강의장 주소가 미기재되어있을 경우
	var address = document.getElementsByName("address")[0];
	if(address.value == "") {
		swal("경고","강의장 주소를 입력해 주세요.");
		return false;
	}
	
	// 연관 태그가 미기재되어있을 경우
	var hashes = document.getElementsByName("hash")[0];
	if(hashes.value == "") {
		swal("경고", "연관 해시태그를 하나 이상 작성해 주세요.");
		return false;
	}
	
	// 유효값 처리가 완료되었다면
	// 입력된 값들을 넘길 수 있는 상태로 변경
	
	// 입력값 처리 1. 강의 시간표
	// {"월":{"시작시간":"20:00" ~ "종료시간":"22:00"}} 와 같이 저장되어야 함
	
	termtoJson(starttimes, endtimes);
	document.classform.submit();
}

// 시간표를 JSON 문자열로 변경
function termtoJson(starttimes, endtimes) {
	var term = {
		"일":{
			"시작시간":starttimes[0].value,
			"종료시간":endtimes[0].value,
		},
		"월":{
			"시작시간":starttimes[1].value,
			"종료시간":endtimes[1].value,
		},
		"화":{
			"시작시간":starttimes[2].value,
			"종료시간":endtimes[2].value,
		},
		"수":{
			"시작시간":starttimes[3].value,
			"종료시간":endtimes[3].value,
		},
		"목":{
			"시작시간":starttimes[4].value,
			"종료시간":endtimes[4].value,
		},
		"금":{
			"시작시간":starttimes[5].value,
			"종료시간":endtimes[5].value,
		},
		"토":{
			"시작시간":starttimes[6].value,
			"종료시간":endtimes[6].value,
		},
	
	}
	
	document.getElementsByName("term")[0].value = JSON.stringify(term);
	console.log(term);
}

</script>

</html>