<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link rel="stylesheet" href="/resources/css/member/myPage.css">
<link rel="stylesheet" href="/resources/css/member/myPage_myprofile.css">
<script src="https://kit.fontawesome.com/7b7a761eb5.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<div class="page-content">
			<div id="rowSession1">
				<ul class="main-menu">
	                <li class="section"><a href="#">내 정보</a></li>
	                <li class="section"><a href="#">반려견 정보</a></li>
	                <li class="section"><a href="#">산책 메이트 목록</a></li>
	                <li>
	                    <a href="#">신고</a>
	                    <ul class="sub-menu">
	                        <li class="section"><a href="#">- 신고하기</a></li>
	                        <li class="section"><a href="#">- 신고한 기록</a></li>
	                        <li class="section"><a href="#">- 신고 받은 기록</a></li>
	                    </ul>
	                </li>
	                <li>
	                    <a href="#">쪽지</a>
	                    <ul class="sub-menu">
	                        <li class="section"><a href="#">- 받은 쪽지함</a></li>
	                        <li class="section"><a href="#">- 보낸 쪽지함</a></li>
	                        <li class="section"><a href="#">- 쪽지 쓰기</a></li>
	                    </ul>
	                </li>
	            </ul>		
			</div>
			<div id="rowSession2">
				<div class="myprofile">
					<jsp:include page="/WEB-INF/views/member/myPage_myprofile.jsp"/>
			    </div>
			    <div class="dogprofile" style="display: none;">
			        <h1>반려견 정보</h1>
			    </div>
			    <div class="matelist" style="display: none;">
			        <h1>산책 메이트 목록</h1>
			    </div>
			    <div style="display: none;">
			        <h1>신고하기</h1>
			    </div>
			    <div style="display: none;">
			        <h1>신고한 기록</h1>
			    </div>
			    <div style="display: none;">
			        <h1>신고 받은 기록</h1>
			    </div>
			    <div style="display: none;">
			        <h1>받은 쪽지함</h1>
			    </div>
			    <div style="display: none;">
			        <h1>보낸 쪽지함</h1>
			    </div>
			    <div style="display: none;">
			        <h1>쪽지 쓰기</h1>
			    </div>
			</div>
		</div>
		
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script src="/resources/js/member/myPage.js"></script>
	<script src="/resources/js/member/myPage_myprofile.js"></script>
</body>
</html>