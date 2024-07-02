<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("주소입력화면 소스"도 동일하게 적용시켜야 합니다.)
//document.domain = "abc.go.kr";
 
window.onload = function(){
	var url = location.href+"?"+"${_csrf.parameterName}"+"="+"${_csrf.token}"; // 결과값을 리턴받을 URL(팝업창 주소)
	var confmKey = "devU01TX0FVVEgyMDIxMDcxNDE0NDI0MjExMTQxMTE="; // 승인키
	var resultType = "4"; // 검색결과 형태 1 : 도로명, 2 : 도로명+지번+상세보기(관련지번, 관할주민센터), 3 : 도로명+상세보기(상세건물명), 4 : 도로명+지번+상세보기(관련지번, 관할주민센터, 상세건물명)
	var inputYn = document.getElementById("inputYn").value;
	if(inputYn != "Y"){
		document.form.confmKey.value = confmKey;
		document.form.returnUrl.value = url;
		document.form.resultType.value = resultType;
		document.form.action="https://www.juso.go.kr/addrlink/addrLinkUrl.do"; //인터넷망
		document.form.submit();
	}else{
		opener.document.classform.address.value = document.getElementById("roadFullAddr").value;
		window.close();
	}
}
</script>

</head>
<body>
    <form id="form" name="form" method="post">
		<input type="hidden" id="inputYn" value="${inputYn}"/>
		<input type="hidden" id="roadFullAddr" value="${roadFullAddr}"/>
        <input type="hidden" id="confmKey" name="confmKey" value=""/>
        <input type="hidden" id="returnUrl" name="returnUrl" value=""/>
        <input type="hidden" id="resultType" name="resultType" value=""/>
    </form>
</body>
</html>