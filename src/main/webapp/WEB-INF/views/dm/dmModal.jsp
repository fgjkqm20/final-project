<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="modal-wrap" style="display:none;">
	<div class="modal-content">
		<div class="dm-modal-title">
			<span>분양 쪽지함</span>
		</div>
		<div class="dm-modal-content">
			<div class="dm-modal-session1">
				<input id="receiverNo" type="hidden" val="">
				<input id="receiverId" type="hidden" val="">
				<span>보낸사람 : </span><span class="sender-name"> </span><br>
				<span>보낸시간 : </span><span class="dm-date"></span><br>
				<span>내용 : </span>
				<div class="receive-content"></div>
				<div style="margin:10px;margin-left:0;">답장할래!</div>
				<textarea class="redirect-content"></textarea>
			</div>
			<div class="dm-modal-session2">
				
			</div>
		</div>
		<div>
			<div class="input-box-wrap">
				<button class="send-dm-btn">쪽지보내기</button>
				<button class="cancel">취소</button>
			</div>		
		</div>
	</div>
</div>