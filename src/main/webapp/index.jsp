<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>산책갈개</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>    
<link rel="stylesheet" href="/resources/css/main.css">
<link rel="styleSheet" href="/resources/css/gmarket.css">
<link rel="icon" href="/resources/img/favicon.ico" type="image/x-icon" sizes="16x16">
</head>
<body id="body">
	<!-- header -->
	<jsp:include page="/WEB-INF/views/common/mainHeader.jsp" />
	<div class="section" id="section1">
		<div class="mainTitle">
			<a>산책 메이트를 만들고 싶어?</a>
		</div>
	</div>
	<div class="section" id="section2">
		<div class="section2Content">
			<div>
				<h1 class="mbtiTitle" style="font-size:40px; color: #DE4B3F; padding-top:20px;">반려견 메이트 찾기</h1>
				<p class="mbtiTitle">내 반려견은<br>
				멍BTI 16가지 유형 중 어떤 유형일까?</p>
				<p class="mbtiTitle">어떤 유형의 강아지와<br>
				좋은 친구가 될 수 있을까?</p>
				<a href="/mbtiMateMain.do" id="mbtiBtn">테스트 하러가기</a>
			</div>
		</div>
	</div>
	<div class="section" id="section3" style="position:relative;">
		<div class="section3-info-wrap">
		<div class="info-card-wrap1">
			<div>
			산책갈개 플러스친구
			</div>
			<div>
				<p>급한 문의가 있다면 카카오톡 플러스를 이용해보세요!</p>
				
			</div>
			<span>
				<img src="/resources/img/dogfoot.png">
			</span>
			<div>
				<img src="/resources/img/qrcode.png">
			</div>
			<div>
			Contact us 산책갈개
			</div>
		</div>
		<div class="info-card-wrap2">
			<div>
			오시는길
			</div>
			<div>
			서울시 영등포구 선유로 57 이레빌딩 19F / A
			</div>
			<div>
			<div>
			지도
			</div>
			<iframe src="">
			</iframe>
			</div>
			</div>

		<div class="info-card-wrap3">
			<div>조원 소개♥</div>
			<div class="team-info-title">고유빈</div>
			<div class="team-info-box">
				<div class="yubin-wrap team-select">
					<div class="yubin-content">
						<div class="yubin-photo-wrap member-photo-wrap">
							<a href="https://github.com/fgjkqm20">
								<img src="/resources/img/yubin.png">
							</a>
						</div>
						<img id="yubin-job" src="/resources/img/yubin-job.png">
					</div>
					<div class="part-info-wrap">
						<div>- 데이터베이스설계</div>
						<div>- 마이페이지 신고목록</div>
						<div>- 마이페이지 신고하기</div>
						<div>- 산책메이트 과거내역</div>
						<div>- 산책메이트 신청 관리</div>
						<div>- 산책메이트 수락/거절</div>
					</div>
				</div>
				<div class="dahe-wrap team-select">
					<div class="dahe-content">
						<div class="dahe-photo-wrap member-photo-wrap">
							<a href="https://github.com/danashinnn">
								<img src="/resources/img/dahe.jpg">
							</a>
						</div>
						<img id="dahe-job" src="/resources/img/dahe-job.png">
					</div>
					<div class="part-info-wrap">
						<div>- 로그인/회원가입</div>
						<div>- 마이페이지(정보수정)</div>
						<div>- 아이디/비밀번호 찾기</div>
						<div>- 강아지 매칭 시스템</div>
						<div>- 멍BTI 검사</div>
						<div>- 달력 일정관리</div>
					</div>
				</div>				
				<div class="sy-wrap team-select">
					<div class="sy-content">
						<div class="sy-photo-wrap member-photo-wrap">
							<a href="https://blog.naver.com/aidencjswo">
								<img src="/resources/img/sy.png">
							</a>
						</div>
						<img id="sy-job" src="/resources/img/sy-job.png">
					</div>
					<div class="part-info-wrap">
						<div>- 메인페이지</div>
						<div>- 입양페이지</div>
						<div>- 입양등록/수정</div>
						<div>- 입양쪽지함</div>
						<div>- 모임 채팅</div>
						<div>- 팀장</div>
					</div>
				</div>
				<div class="heeok-wrap team-select">
					<div class="heeok-content">
						<div class="heeok-photo-wrap member-photo-wrap">
							<a href="https://github.com/hyogiii">
								<img src="/resources/img/heeok.png">
							</a>
						</div>
						<img id="heeok-job" src="/resources/img/heeok-job.png">
					</div>
					<div class="part-info-wrap">
						<div>- 메인페이지</div>
						<div>- 관리자페이지</div>
						<div>- 공지사항 작성</div>
						<div>- 문의내역</div>
						<div>- 신고회원관리</div>
						<div>- 회원등급관리</div>
					</div>
				</div>
				<div class="minho-wrap team-select">
					<div class="minho-content">
						<div class="minho-photo-wrap member-photo-wrap">
							<a href="https://github.com/loseminho">
								<img src="/resources/img/minho.png">
							</a>
						</div>
						<img id="minho-job" src="/resources/img/minho-job.png">
					</div>
					<div class="part-info-wrap">
						<div>- 산책메이트 찾기</div>
						<div>- 산책메이트 등록</div>
						<div>- 상세페이지</div>
						<div>- 상세페이지 댓글/대댓글</div>
						<div>- 카테고리 분류</div>
						<div>- UI/UX 디자인</div>
					</div>
				</div>
			</div>
			<div class="name-box">
				<div class="yubin-info name-title">
				고유빈
				</div>
				<div class="dahe-info name-title">
				신다혜
				</div>
				<div class="sy-info name-title">
				안상영
				</div>
				<div class="heeok-info name-title">
				유희옥
				</div>
				<div class="minho-info name-title">
				진민호
				</div>
			</div>
		</div>
		</div>
	</div>
	<div class="popup-modal" style="display:none;">
			<div class="popup-content">
				<img src="/resources/img/popup/popup.png">
			</div>
			<div class="popup-content">
				<img src="/resources/img/popup/popup2.png">
			</div>
			<div class="popup-content">
				<img src="/resources/img/popup/popup3.png">
			</div>
			<div class="popup-content">
				<img src="/resources/img/popup/popup4.png">
			</div>
		<button class="popup-close">닫기</button>
		<button class="popup-close-day">오늘하루보지않기</button>
		<button class="popup-back">이전</button>
		<button class="popup-next">다음</button>
	</div>
<script src="/resources/js/main.js"></script>
</body>
</html>