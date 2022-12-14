<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>산책갈개</title>
<!--css-->
<link rel="stylesheet" href="/resources/css/admin/adminPage.css">
<!--fonts-->
<link rel="stylesheet" href="/resources/css/gmarket.css">
<!--jQuery-->
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<body>
	<!-- 헤더  -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<content>
	<div class="adminPage-wrap">
		<div class="adminPage-main">
			<div class="adminPage-main-header">
				<h1>관리자페이지</h1>
				<div class="adminPage-link-box">
					<a href="/writeNoticeFrm.do">공지사항 글쓰기</a>
				</div>
				<div class="admin-main-tab">
					<ul class="admin-tab">
						<a href="#"><li>회원등급</li></a>
						<a href="#" id="adminPageQnaAjax"><li>문의내역</li></a>
						<a href="#" id="reportPageAjax"><li>신고목록</li></a>
					</ul>
				</div>
			</div>
			<!-- adminPage-main-header 끝-->
			<div class="adminPage-content">
				<!-- 상세보기 이동 form -->
				<form action="/qnaView.do" method="post" id="qnaViewFrm">
					<input type="hidden" id="qnaBoardNo" type="text" name=qnaNo
						value="">
				</form>
				<div class="adminQnaAjaxResult">
					<table class="adminPageQna-table">
						<tr class="admin-qna-tr">
							<th>글번호</th>
							<th>문의유형</th>
							<th>제목</th>
							<th>작성자</th>
							<th>문의날짜</th>
							<th>처리상태</th>
							<th></th>
						</tr>
					</table>
				</div>
				<!--ajax 결과 페이지 끝  -->
				<div class="adminQna-add-btn">
					<button id="adminQnaAjax-btn" totalCount="${totalCount }"
						currentCount="0" value="1">더보기</button>
				</div>
			</div>
			<!-- 관리자페이지 메인 문의내역 끝  -->
			<div class="reportPage-content">
				<div class="reportPageAjax-result">
					<table class="admin-report-table">
						<tr class="admin-report-tr">
							<th>신고번호</th>
							<th>신고유형</th>
							<th>신고자</th>
							<th>신고내용</th>
							<th>신고된 사람</th>
							<th>신고된 횟수</th>
							<th>신고날짜</th>
							<th>처리하기</th>
							<th></th>
						</tr>
					</table>
				</div>
				<!-- 신고내역 ajax 끝 -->
				<div class="admin-report-add">
					<button id="adminReportAjax-btn" totalCount="${totalCount }"
						currentCount="0" value="1">더보기</button>
				</div>
			</div>
			<!-- 신고내역 content 끝 -->
			<div class="userLevel-content">
				<div class="member-search-box">
					<form action="#">
						<select name="searchType" id="searchType" class="search-member-form">
							<option value="memberId" selected="selected">아이디</option>
							<option value="memberName">닉네임</option>
						</select> <input type="text" class="search-input" name="keyword"
							id="keyword">
						<button type="button" class="search-btn" id="searchMemberAjax"
						totalCount="${totalCount }"
						currentCount="0" value="1"
						>검색</button>
					</form>
				</div>
				<div class="userLevelAjax-result">
					<table class="adminPage-userLevel-table">
						<tr class="admin-memberList-tr">
							<th>회원번호</th>
							<th>아이디</th>
							<th>닉네임</th>
							<th>가입날짜</th>
							<th>등급</th>
							<th></th>
							<th></th>
						</tr>
					</table>
				</div>
				<!--회원등급 ajax 불러오기 끝-->
				<div class="admin-memberList-add">
					<button id="adminMemberAjax-btn" totalCount="${totalCount }"
						currentCount="0" value="1">더보기</button>
				</div>
			</div>
			<!--회원 등급 불러오기-->
		</div>
	</div>
	</content>
	<!-- footer -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script src="/resources/js/admin/adminPage.js"></script>
</body>
</html>