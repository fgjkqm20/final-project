<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/resources/css/member/mypage/myPage_sendDm.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<h1>보낸 쪽지함</h1>
<div class="dmWrap">
	<table id="sendDmTable" class="dmTable">
		<thead>
			<tr>
				<th>번호</th>
				<th>분류</th>
				<th>내용</th>
				<th>받은 사람</th>
				<th>보낸 날짜</th>
				<th>조회 여부</th>
			</tr>
		</thead>
		<tbody>
			
		</tbody>
	</table>
</div>

<!-- 쪽지 모달 -->
<div id="sendDm-modal" class="modal-wrapper" style="display:none;">
	<div class="modal">
		<div class="modal-header">				
			<button id="closeModalBtn" onclick="closeSendDmModal();">
				<i class="fa-solid fa-xmark"></i>
			</button>
		</div>
		<div class="modal-content">
			쪽지 분류 : <span class="dmCate"></span><br>
			보낸 날짜 : <span class="dmDate"></span><br>
			받은 사람 : <span class="receiver"></span><br>
			<br>
			<div class="dmContentDiv">
				<span class="dmContent"></span>
			</div> 
			<br>
			조회 여부 : <span class="dmReadCheck"></span>
		</div>
	</div>
</div>

<script src="/resources/js/member/mypage/myPage_sendDm.js"></script>