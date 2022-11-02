<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="modal-wrap" style="display: none;">
	<div class="modal-content">
		<div class="detail-box-title">
		<!-- 품종데이터 -->
		</div>
		<pre class="sale-info">강아지한줄소개</pre>
		<hr>
		<div class="detail-table-subtitle">품종정보</div>
		<div class="detail-info">
			<table class="detail-info-table">
			<!-- 강아지 정보 입력 테이블칸 -->
			</table>
		</div>
		<div class="market-middle-img">
			<span>입양 시 주의사항! 멍!</span>
			<span>1. 강아지와 함께 할 충분한 시간이 필요해요!</span>
			<span>2. 인내심을 가지고 훈련할 필요가 있어요!</span>
			<span>3. 끝까지 함께 해야 해요!</span>
		</div>
		<div class="detail-table-subtitle">이렇게나 이뻐요!</div>
		<div class="photo-session-wrap">
			<div>
				<img class="detail-image" src="">
			</div>
			<div>
				<img class="detail-image" src="">
			</div>
		</div>
		<div class="require-btn">
			<c:choose>
			<c:when test="${empty sessionScope.m }">
			<button onclick="location.href='/loginFrm.do'">로그인</button>
			</c:when>
			<c:otherwise>
			<button>분양신청하기</button>
			<button onclick="dmModalOn();">쪽지보내기</button>
			</c:otherwise>
			</c:choose>
			<button id="close-modal">닫기</button>
		</div>
	</div>
</div>