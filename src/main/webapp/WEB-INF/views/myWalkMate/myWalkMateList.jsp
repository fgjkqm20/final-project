<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 산책 메이트</title>
	<link rel="stylesheet" href="/resources/css/myWalkMate/myWalkMateList.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	
	<div class="page-content">
		<div id="rowSession1">
			<ul class="main-menu">
                <li class="section" id="appliedWalkMateList"><a href="#">산책 메이트 신청 내역</a></li>
                <li>
                    <a href="#">산책 메이트 참가 내역</a>
                    <ul class="sub-menu">
                        <li class="section" id="myAttendList"><a href="#">- 내가 만든 모임</a></li>
                        <li class="section" id="otherAttendList"><a href="#">- 그 밖의 모임</a></li>
                    </ul>
                </li>
            </ul>		
		</div>
		
		<div id="rowSession2">
			<!-- 산책 메이트 신청 내역 -->
			<div class="appliedWalkMateList">
				<jsp:include page="/WEB-INF/views/myWalkMate/appliedWalkMateList.jsp"/>
		    </div>

		    <!-- 내가 만든 모임 -->
		    <div class="myAttendList" style="display: none;">
		        <jsp:include page="/WEB-INF/views/myWalkMate/myAttendList.jsp"/>
		    </div>
		    
		    <!-- 그 밖의 모임 -->
		    <div class="otherAttendList" style="display: none;">
		        <jsp:include page="/WEB-INF/views/myWalkMate/otherAttendList.jsp"/>
		    </div>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	
	<script>
		const applyId = "${sessionScope.m.memberId }";
		const wmMakerNo = ${sessionScope.m.memberNo };
	</script>
	<script src="/resources/js/myWalkMate/myWalkMateList.js"></script>
</body>
</html>