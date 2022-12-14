<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>우리 동네 산책 찾기</title>
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <link rel="stylesheet" href="/resources/css/walkmate/walk_mate_content.css">
    <link rel="styleSheet" href="/resources/css/gmarket.css">
</head>
<body>
	    <!-- Wrapper -->
    <div class="wrapper">
        <!--Content Modal-->
        <div id="modal" class="modal-overlay">
            <div class="modal-window">
                <div class="title">
                    <div class="close-area">X</div>
                    <div class="bottom-list-box">
                        <!-- 모달 - 새 글쓰기 타이틀 -->
                        <div id="modal-write-title">
			                	<div class="bottom-list-box">
			                		<div class="box-list-num">
			                			1
			                		</div>
			                        <div class="box-list-tag">
			                            <div class="tag-wrapper">
											태그를 선택하세요
			                            </div>
			                        </div>
			                        <div class="box-list-main">
			                            <div class="box-list-main-title">골든 리트리버 팟 구해요~</div>
			                            <div class="box-list-main-name">
			                                	사과는 맛있어 맛있으면 바나나 바나나는 높아 높으면 민머리
			                            </div>
			                        </div>
			                        <div class="box-list-limit">
			                            <div class="limit-box">1 / 5</div>
			                        </div>
			                        <div class="box-list-stat">
			                            <input type="button" id="stat-box" class="stat-box" onclick="himinho('');" value="사진">
			                        </div>
			                	</div>
                        </div>
                        <!-- End 모달 - 새 글쓰기 타이틀 -->
                        <!--산책갈게 게시물 보기-->
                        <div id="modal-post-title">
                        </div>
                    </div>
                </div>
                <!-- 모달 타이틀 끝-->

				<!-- 글쓰기 컨텐츠 시작 -->
				<div id="modal-write-content">
	                <div class="write-content">
	                
	                	<div class="write-content-input-box titles">
	                		<label for="writeTitle"><span>*</span>제목</label>
	                		<input type="text" name="writeTitle" id="writeTitle"> 
	                	</div>
	                	<div class="write-content-input-box titles">
	                		<label for="writeType"><span>*</span>분류</label>
	                		<input type="text" name="writeType" id="writeType" style="display:none;">
	                		<select id="writeType" name="writeType">
	                			<option value="none" selected disabled>선택해주세요</option>
	                			<option value="산책갈개">산책갈개</option>
	                			<option value="강아지 자랑">강아지 자랑</option>
	                		</select> 
	                	</div>
	                	<div class="write-content-input-box titles">
	                		<label for="writeTitle2">부제목</label>
	                		<input type="text" name="writeTitle2" id="writeTitle2"> 
	                	</div>
	                	<div class="write-content-input-box titles">
	                		<label for="writeTitle2"><span>*</span>모임 최대 인원</label>
	                		<input type="text" name="writeTitle2" id="writeTitle2" placeholder="ex) 5"> 
	                	</div>
	                	<div class="write-content-input-box titles">
	                		<label for="writeTitle2"><span>*</span>모임 장소</label>
	                		<input type="text" name="writeTitle2" id="writeTitle2" placeholder="ex) 5">
	                		<button class="adressBtn" value="주소찾기">주소 찾기</button>
	                	</div>
	                	<div class="write-content-input-box titles">
	                		<label for="writeTitle2"><span>*</span>모임 인원</label>
	                		<input type="text" name="writeTitle2" id="writeTitle2" placeholder="ex) 5"> 
	                	</div>
	                	<div class="write-content-input-box titles">
	                		<label for="writeDate"><span>*</span>모임 요일</label>
	                		<label for="writeDate2" style="display:none;"><span>*</span>모임 시간</label>
	                		<input type="date" name="writeDate" id="writeDate" value="none">
	                		<input type="time" value="xxx" min="yyy" max="zzz" style="display:none;"> 
	                	</div>
	                	<textarea class="input-box-text" placeholder="내용을 입력해주세요."></textarea>
	                	<div id="modal-tag-sub">
	                		
	                	</div>
	                	
	                </div>
				</div>
                 
                <!-- 글쓰기 컨텐츠 끝 -->

                <!--모달 컨텐츠 시작(post)-->
                
                <div class="modal-content-post" id="modal-post-main">
                	<input type="button" id="report-box" class="" onclick="modalReportPost();" value="신고하기">
                    <div class="modal-tag-post">
                        #골든리트리버, #1마리 #산책광
                    </div>
                    <div class="modal-content-img-post">
                        <img src="/resources/img/walkmate/liry1.jpg" alt="">
                    </div>
                    <div class="modal-text-post">
                        <p>안녕하세요. 리트리버맘 입니다. :) <br>
                        	골든 리프리버 파티를 구하고 있어요! 주로 호수공원이나 시냇물공원으로 해서 20:00 ~ 21:00 (2시간) 정도 돌고 있습니다!.<br>
                        	저희 산책파티는 각자 목줄, 배변봉투, 입마개 등 지참해오는 것이니 이점 명시해서 지원해주세요~
                        </p>
                    </div>
                    <div class="modal-btn-post">
                        <button class="btn-modal-content1-post" onclick="walkMyinfo();">신청하기</button>
                        <button class="btn-modal-content2-post" onclick="walkMateLeader();">상세보기</button>
                    </div>
                </div>
                
                <!--모달 컨텐츠 끝(post)-->

                <!--모달 컨텐츠 시작(post-leader)-->
                <div class="modal-content-post-leader" id="modal-post-leader">
                    <div class="modal-leader-title">모임장(犬) 정보</div>
                    <div class="leader-info-tops">
                        <div class="leader-info-top left">
                            <img src="/resources/img/walkmate/liry1.jpg">
                        </div>
                        <div class="leader-info-top right">
                            <div class="leader-info-top-title">릴리</div>
                            <div class="leader-info-top-title sub">minhodaa 님의 반려견</div>
                            <table>
                                <tr>
                                    <td class="pet-title">견종</td>
                                    <td>불독</td>
                                    <td class="pet-title">나이</td>
                                    <td>삼겹살</td>
                                </tr>
                                <tr>
                                    <td class="pet-title">성별</td>
                                    <td>여아</td>
                                    <td class="pet-title">중성화</td>
                                    <td>안했어요</td>
                                </tr>
                                <tr>
                                    <td class="pet-title">몸무게</td>
                                    <td>7Kg</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="leader-info-middles">
                        <div class="leader-info-middle-title">
                           	 모임장의 <strong>산책</strong>기록이에요.
                        </div>
                        <div class="leader-info-middle-content">
                            <div class="content-list one">
                                <img src="/resources/img/walkmate/walk-mate-icon.png" alt="">
                                <div class="content-list-title">산책 횟수</div>
                                <div class="content-list-data">15</div>
                            </div>
                            <div class="content-list two">
                                <img src="/resources/img/walkmate/review-icon.png" alt="">
                                <div class="content-list-title">리뷰 개수</div>
                                <div class="content-list-data">22</div>
                            </div>
                            <div class="content-list three">
                                <div class="review-point">4.8</div>
                                <div class="content-list-title">리뷰 평점</div>
                            </div>
                        </div>
                    </div>
                    <div class="leader-info-bottoms">
                        <div class="leader-info-bottom-title">
                            	우리의 <strong>산책</strong> 등록 정보에요.
                        </div>
                        <div class="bottom-content meeting">
                            <div class="titles">만남 장소</div>
                            <input type="text" id="" name="" placeholder="ex) 서울-용산">
                        </div>
                        <div class="bottom-content range">
                            <div class="titles">1인 반려견 최대 마리수</div>
                            <input type="text" id="" name="" placeholder="ex) 3">
                        </div>
                        <div class="bottom-content time">
                            <div class="titles">모임 시간</div>
                            <div class="content-time start">
                                <div class="starting-title">시작 시간</div>
                                <input type="text" id="" name="" placeholder="ex)00시-00분">
                            </div>
                            <div class="content-time and">
                                ~
                            </div>
                            <div class="content-time end">
                                <div class="starting-title">끝 시간</div>
                                <input type="text" id="" name="" placeholder="ex)00시-00분">
                            </div>
                        </div>
                    </div>
                    <div class="modal-btn-post">
                        <button class="btn-modal-content1-post" onclick="walkMyinfo();">신청하기</button>
                        <button class="btn-modal-content2-post" onclick="closeBtns();">닫기</button>
                    </div>
                </div>
                <!--모달 컨텐츠 끝(post-leader)-->

                <!--모달 컨텐츠 시작(report)-->
                <div class="modal-content-report" id="modal-post-report">
                    <div class="modal-content-report-title">신고하시겠습니까?</div>
                    <div class="modal-content-report-title-sub">신고 사유</div>
                    <textarea name="" id="" placeholder="게시물 신고 사유를 작성해주세요. 허위 신고 시, 작성자에게 불이익이 갈 수 있습니다."></textarea>
                    <div class="modal-btn-post">
                        <button class="btn-modal-content1-post">신청하기</button>
                        <button class="btn-modal-content2-post" onclick="closeBtns();">취소</button>
                    </div>
                </div>
                <!--모달 컨텐츠 끝(post)-->

                <!--모달 컨텐츠 시작(post-myinfo)-->
                <div class="modal-content-post-myinfo" id="modal-post-myinfo">
                    <div class="modal-leader-title">내 정보</div>
                    <div class="leader-info-tops">
                        <div class="leader-info-top left">
                            <img src="/resources/img/walkmate/liry1.jpg">
                        </div>
                        <div class="leader-info-top right">
                            <div class="leader-info-top-title">릴리</div>
                            <div class="leader-info-top-title sub">minhodaa 님의 반려견</div>
                            <table>
                                <tr>
                                    <td class="pet-title">견종</td>
                                    <td>불독</td>
                                    <td class="pet-title">나이</td>
                                    <td>삼겹살</td>
                                </tr>
                                <tr>
                                    <td class="pet-title">성별</td>
                                    <td>여아</td>
                                    <td class="pet-title">중성화</td>
                                    <td>안했어요</td>
                                </tr>
                                <tr>
                                    <td class="pet-title">몸무게</td>
                                    <td>7Kg</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="leader-info-bottom-title">
                       	 해당 <strong>산책</strong> 신청 등록하기.
                    </div>
                    <textarea name="" class="myinfo-post" id="" placeholder="내용을 입력해주세요."></textarea>
	                <div class="modal-btn-post">
	                    <input type="submit" class="btn-modal-content1-post" value="제출하기">
	                    <button class="btn-modal-content2-post" onclick="closeBtns();">취소</button>
	                </div>
                </div>                
                <!--모달 컨텐츠 끝(post-myinfo)-->
            </div>
        </div>
        <!--End Content Modal-->

       	<!-- 헤더  -->
		<jsp:include page="/WEB-INF/views/common/header.jsp" />

        <!-- content -->
        <div class="walk-mate-content">
            <!-- content-top section -->
            <section id="content-top">
                <div class="content-top-button">
                    <div class="content-top-button-left">산책 찾기</div>
                    <div class="content-top-button-right">💖메이트 찾기</div>
                </div>
            </section>
            <!-- End content-top section -->

            <!-- content-midle section -->
            <section id="content-middle">
                <div class="content-middle-title">산책 태그 찾기</div>
                <div class="tag-track">
                    <button type="" class="tags" id="tag1" value="">1:1 산책</button>
                    <button type="" class="tags" id="tag2" value="">N:N 산책</button>
                    <button type="" class="tags" id="tag3" value="">인증된 모임</button>
                    <button type="" class="tags" id="tag4" value="">산책할개 문화</button>
                    <button type="" class="tags" id="tag5" value="">산책할개 문화</button>
                    <button type="" class="tags" id="tag6" value="">산책할개 문화</button>
                    <button type="" class="tags" id="tag7" value="">산책할개 문화</button>
                </div>
            </section>
            <!-- End content-middle section-->

            <!-- content-bottom section -->
            <section id="content-bottom">
                <div class="content-bottom-list">
                    <div class="bottom-list-title">분류 : </div>
                    <select name="">
                        <option value="전체목록">전체목록 보기</option>
                        <option value="진행목록">현재 진행중인 목록</option>
                        <option value="종료목록">현재 종료된 모임 목록</option>
                    </select>
                    <input type="button" onclick="modalWritePostOn();" value="모임 만들기" class="write-modal">
                </div>
                <ul></ul>
                
            </section>
            <!-- End content-bottom section -->
        </div>
        <!-- End content -->
    </div>
    <!-- Start footer -->
        <jsp:include page="/WEB-INF/views/common/footer.jsp" />
        <!-- End footer -->
    
    <script>
        const modal = document.getElementById("modal");
        const modalPostTitle = document.getElementById("modal-post-title");
		const modalLeader = document.getElementById("modal-post-leader");
		const modalMain = document.getElementById("modal-post-main");
		const modalReport = document.getElementById("modal-post-report");
		const modalMyinfo = document.getElementById("modal-post-myinfo");
		const modalWriteTitle = document.getElementById("modal-write-title");
		const modalWriteContent = document.getElementById("modal-write-content");
		 
		/* 모달 window 창 이외 클릭 시 modal 종료,, duration이 안잡혀서 ..0
		$(".modal-overlay").on("click",function(){
			modalOff();
		})
		*/
		
		
		$(document).on("mouseover",".bottom-list-box",function(){
			const idx = $(".bottom-list-box").index(this);
			$(".stat-box").eq(idx-1).css("transition-duration","0.5s").css("background-color","rgb(51, 102, 255)").css("font-size","1.2em").css("border","1px solid white").css("color","white");
			$(".box-list-stat .end").css("background-color","#fff").css("font-size","15px").css("border","2px solid #9e9e9e").css("color","#ccc");
		});
		$(document).on("mouseleave",".bottom-list-box",function(){
			const idx = $(".bottom-list-box").index(this);
			$(".stat-box").eq(idx-1).css("transition-duration","0.5s").css("font-size","15px").css("background-color","white").css("border","2px solid rgb(51, 102, 255)").css("color","rgb(51, 102, 255)");
			$(".box-list-stat .end").css("background-color","#fff").css("font-size","15px").css("border","2px solid #9e9e9e").css("color","#ccc");
		});
		
		
        function modalOn() {
            modal.style.display = "flex"
        }
        function isModalOn() {
            return modal.style.display === "flex"
        }
        function modalOff() {
            modal.style.display = "none";
            modalPostTitle.style.display = "none";
            modalLeader.style.display = "none";
            modalReport.style.display = "none";
            modalMyinfo.style.display = "none";
            modalMain.style.display = "none";
            modalWriteTitle.style.display = "none";
            modalWriteContent.style.display = "none";
        }
        function modalPostOff(){
        	modalPostTitle.style.display = "none";
            modalLeader.style.display = "none";
            modalReport.style.display = "none";
            modalMyinfo.style.display = "none";
            modalMain.style.display = "none";  
            modalWriteTitle.style.display = "none";
            modalWriteContent.style.display = "none";
        }
        function modalWriteOn(){
        	modalWriteTitle.style.display = "block";
        	modalWriteContent.style.display = "block";
        }
        function modalMainOn() {
        	modalPostTitle.style.display = "block";
            modalMain.style.display = "block"
        }
        function modalLeaderOn(){
        	modalPostTitle.style.display = "block";
        	modalLeader.style.display = "block"
        }
        function modalMyinfoOn(){
        	modalPostTitle.style.display = "block";
        	modalMyinfo.style.display = "block"
        }
        function modalReportOn(){
        	modalPostTitle.style.display = "block";
        	modalReport.style.display = "block";
        }
        const closeBtn = modal.querySelector(".close-area")
        closeBtn.addEventListener("click", e => {
            modalOff();
        })
        function closeBtns(){
        	modalOff();
        }
        
		function modalReportPost(){
			modalOn();
			modalPostOff();
			modalReportOn();
		}
        //board 글쓰기
        function modalWritePostOn(){
        	modalOn();
        	modalPostOff();
        	modalWriteOn();
        }
        //board list 메인 게시물 보기 (첫화면)
		function walkMates(){
        	modalOn();
        	modalPostOff();
        	modalMainOn();
        }
        //board list 상세 게시물 보기 (두번째 화면)
        function walkMateLeader(){
        	modalOn();
        	modalPostOff();
        	modalLeaderOn();
        }
      	//board list 상세 신청하기 페이지 (세번째 화면)
      	function walkMyinfo(){
      		modalOn();
        	modalPostOff();
        	modalMyinfoOn();
      	}  
      
        modalOff();
        modalWritePostOn();
        
    </script>
    <script src="/resources/js/walk/walk.js"></script>
</body>
</html>