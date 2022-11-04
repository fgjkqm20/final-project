<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/market/writeFrm.css">
<link rel="stylesheet" href="/resources/css/dm/dmList.css">
<link rel="stylesheet" href="/resources/css/dm/dmModal.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<div class="page-content">
			<div id="rowSession1">
				<ul class="main-menu">
	                <li class="section"><a href="/writeFrm.do">분양등록</a></li>
	                <li class="section"><a href="/myMarketList.do">분양목록</a></li>
	                <li class="section"><a href="/sendDmFrm.do">분양쪽지함❤</a></li>
	            </ul>	
			</div>
			<div id="rowSession2">
			<h1>분양쪽지함</h1>
			<br>
			<hr>
			<br>
				<div class="dm-list-wrap">
					<li class="list-title">
						<span style="width:10%">카테고리</span>
						<span style="width:50%">제목</span>
						<span style="width:10%">날짜</span>
						<span style="width:10%">이름</span>
						<span style="width:10%">답장</span>
					</li>
					<div class="dm-content-wrap">
					<input type="hidden" id="sessionMemberNo" value="${sessionScope.m.memberNo }">
						<li class="dm-content">
							<span id="category" style="width:10%">분양문의</span>
							<span id="dmContent"  style="width:50%">안녕하세요,분양신청합니다.시고르자브종은무슨종인가요?</span>
							<span id="receiveDate"  style="width:10%">22/11/02</span>
							<span id="senderName"  style="width:10%">안상영</span>
							<span style="width:10%">
								<button>답장</button>
							</span>
						</li>
					</div>
				</div>
			</div>
		</div>
	<jsp:include page="/WEB-INF/views/dm/dmModal.jsp" />
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
<script src="/resources/js/dm/dmList.js"></script>
<script>
</script>
</html>