<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>산책갈개</title>
	<!--fonts-->
    <link rel="stylesheet" href="/resources/css/gmarket.css">
    <!--css-->
    <link rel="stylesheet" href="/resources/css/board/writeQna.css">
    <!--jQuery-->
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <!--구글아이콘-->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
</head>
<body>
<!-- 헤더  -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
    <content>
        <div class="writeQna-wrap">
            <div class="writeQna-box">
                <div class="writeQna-header">
                    <h1>문의작성</h1>
                </div>
                <form action="/writeQna.do" method="post" enctype="multipart/form-data" onsubmit ="return checkSelect();">
                    <div class="writeQna-content">
                        <div class="qna-input-box">
                        <label for="qnaCateNo">문의유형</label>
                        <select name="qnaCateNo" id="qnaCateNo" class="write-qna-form">
                            <option value="0" selected="selected">선택해주세요</option>
                            <option value="1">산책메이트 찾기</option>
                            <option value="2">입양</option>
                            <option value="3">회원관련</option>
                            <option value="4">사이트이용</option>
                            <option value="5">기타</option>
                        </select>
                        </div>
                        <div class="qna-input-box">
                            <label for="qnaTitle">제목</label>
                            <input type="text" name="qnaTitle" id="qnaTitle" class="write-qna-form" required>
                        </div>
                        <div class="qna-input-box">
                            <label for="qnaContent">내용</label>
                             <textarea name="qnaContent" id="qnaContent" class="write-qna-form" required></textarea>
                        </div>
                        <div class="qna-input-box" id="file-input-box">
                            <label for="qnaFile">파일첨부</label>
                            <div class="fileZone">
                                <span class="material-symbols-outlined">add</span>
                            </div>
                        </div>
                        <div class="fileMsg">* 첨부파일을 놓아주세요</div>
                        <div class="qna-check-box">
                            <label class="secret-check-label">비밀글 설정</label>
                            <input class="form-check-input" type="checkbox" name="qnaSecret" id="qnaSecret" value="1">
                        </div>
                        <!--display: none-->
                        <input type="file" name="boardFile" multiple style="display:none;">
                        <input type="hidden" name="qnaWriter" id="qnaWriter" class="write-qna-form" value="${sessionScope.m.memberId }">
                    </div><!--writeqna content 끝-->
                    <div class="writeQna-footer">
                        <div class="submit-btn">
                            <button type="button" class="qna-back-btn">취소</button>
                            <button type="submit" class="finish-btn">등록</button>
                        </div>
                    </div>
                </form>
            </div><!--qna box 끝 -->
        </div><!--qna-wrap 끝-->
    </content>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script src="/resources/js/board/writeQna.js"></script>
</body>
</html>