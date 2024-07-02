<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="http://dapi.kakao.com/v2/maps/sdk.js?appkey=87bd052bea45d46f442aeb837542ff12&libraries=services,clusterer"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<body>
<%@include file="../header.jsp"%>
<div class="container">
	<h2><a href="./classList">강의 상세</a></h2>
	<div>
		<h2>${cDto.cl_title}</h2>
		<c:forEach var="hash" items="${cDto.hashList}">
			#${hash["value"]}&nbsp;
		</c:forEach>
	</div>
	
	<div>
	<p>${cDto.teacher}</p>
	
	<span><fmt:formatDate value="${cDto.startday}" pattern="yyyy년 MM월 dd일"/> ~ <fmt:formatDate value="${cDto.endday}" pattern="yyyy년 MM월 dd일"/></span>
	<span>${cDto.price}￦</span>
	<span>최대 수강인원 ${cDto.maxmember}명</span>
	<button class="btn btn-warning" onclick="location.href='/coupon/memlist?cl_seq=${cDto.cl_seq}'">수강신청</button>
	</div>
	
	<h3>강의소개</h3>
	<div>${cDto.cl_content}</div>
	
	<h3>강의시간</h3>
	<table class="table table-bordered timetable">
		<thead>
			<tr>
				<th>요일</th>
				<th>일</th>
				<th>월</th>
				<th>화</th>
				<th>수</th>
				<th>목</th>
				<th>금</th>
				<th>토</th>
			</tr>
		</thead>
		<tbody>
			<tr id="starttime">
					<th>시작</th>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
			</tr>
			<tr id="endtime">
					<th>종료</th>				
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
			</tr>
		</tbody>
	</table>
	
	<div>
		<h3>강의장 위치</h3>
		<p>${cDto.address}</p>
		<div id="map" style="width: 400px; height: 300px;"></div>
	</div>

	<div>
		<h3>수강료 비교</h3>
		<c:choose>
		<c:when test="${not empty pList}">
			<p>유사한 강의의 수강료입니다.</p>
			<div id="chart_div" style="width: 800px; height: 400px;"></div>
		</c:when>
		<c:otherwise>
			<p>같은 해시태그의 강의가 존재하지 않습니다.</p>
			<div id="chart_div" style="display:none;"></div>
		</c:otherwise>
		</c:choose>
	</div>
	
	<button class="btn btn-success" onclick="location.href='./classList'">강의 목록</button>

</div>
<%@include file="../footer.jsp" %>
</body>
<script>
//
window.onload = function() {
	
	// 시간표 작성
	timetable('${cDto.term}');
	
	// 강의장 지도 작성
	openmap('${cDto.cl_title}','${cDto.address}');
	
	// 강의 가격 비교 차트
	googleChart();
}

function timetable(term) {
	var term = JSON.parse(term);
	var starttimes = document.getElementById("starttime").children;
	var endtimes = document.getElementById("endtime").children;

	var days = ["일", "월", "화", "수", "목", "금", "토"];

	for(var i = 0; i < days.length; i ++) {
		var starttime = term[days[i]]['시작시간'];
		var endtime = term[days[i]]['종료시간'];
		starttimes[i+1].textContent = (starttime == '')?'-':starttime;
		endtimes[i+1].textContent = (endtime == '')?'-':endtime;;
	}
}

function openmap(cl_title, address) {
	console.log(cl_title);
	console.log(address);
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = {
		center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		level : 3
		// 지도의 확대 레벨
	};

	// 지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption);

	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();

	// 주소로 좌표를 검색합니다
	geocoder.addressSearch(address,
			function(result, status) {

			// 정상적으로 검색이 완료됐으면 
			if (status === kakao.maps.services.Status.OK) {
	
				var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	
				// 결과값으로 받은 위치를 마커로 표시합니다
				var marker = new kakao.maps.Marker({
					map : map,
					position : coords
				});
	
				// 인포윈도우로 장소에 대한 설명을 표시합니다
				var infowindow = new kakao.maps.InfoWindow(
				{
					content : '<div style="width:150px;text-align:center;padding:6px 0;">'+cl_title+'</div>'
				});
					
				infowindow.open(map, marker);
	
				// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
					map.setCenter(coords);
				}
			});
}

function googleChart() {
	// 구글 chart
	// Load the AJAX API
	// 구글 차트 API 로드 : bar 형태로
	google.charts.load('current', {'packages':['bar']});

	// 차트 그리기 실행
	google.charts.setOnLoadCallback(drawChart);
}

//차트를 그림 : 강의명은 AJAX로 조회 및 현재 강의 이름을 불러와서 사용한다
function drawChart() {
	
	var data = google.visualization.arrayToDataTable([
		['강의명', '수강료'],
		['${cDto.cl_title}', parseInt('${cDto.price}')]
	]);
	
	<c:forEach items="${pList}" var="pDto">
		data.addRow(['${pDto.cl_title}'.substr(0,8)+'...', parseInt('${pDto.price}')]);
	</c:forEach>

	var options = {
		  colors: ['#aaaaf6']
	};

	var chart = new google.charts.Bar(document.getElementById('chart_div'));
	chart.draw(data, google.charts.Bar.convertOptions(options));
}
</script>
</html>