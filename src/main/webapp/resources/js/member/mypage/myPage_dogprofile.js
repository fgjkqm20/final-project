$("#addDog").on("click", function(){
	$("#dog-modal").css("display", "flex");
	$("#dogProfileForm").attr("action", "/insertMyDog.do");
	$("#dogBtn").text("추가하기");
	$("#deleteDogBtn").hide();
	$("#dogNo").val(""); 
    $("#dogNo").attr("name", "");
	$("#dogType1").css("display", "none");
	$("#dogType1").attr("name", "");
	$("#typeSize").css("display", "none");
	$("#typeSize").attr("name", "");
	$("#dogType2").css("display", "block");
	$("#dogType2").attr("name", "dogTypeNo");
	$("#dogType2").empty();
	$("#dogType2").append("<option value='none' selected disabled>선택해주세요</option>")
	$("#dogType2").append("<option value='user'>직접 입력</option>")
	$("#dogPhoto").prop("name", "");
	$("#dogPhoto").val("");
	$("#mbti-box").hide();
	$(".dogprofile #updateMent").hide();
	$(".dogprofile span").text("");
	
	$("#dogName").prop("readonly", false);
	$("#dogName").val("");
	$("#dogType1").val("");
	$("#dogType1").prop("readonly", false);
	$("#dogAge").val("");
	$("#dogWeight").val("");
	$("#dogPreview").attr("src", "/resources/img/default_dog.png");
	$("input:radio[name=dogGender]").prop("checked", false);
	$("input:radio[name=dogNeutral]").prop("checked", false);
	$("input:radio[name=dogVacc]").prop("checked", false);
	
	$.ajax({
        url  : '/selectAllDogType.do',
        type : 'post',
        success : function(data){
        	for(let i=0; i<data.length; i++) {
        		const option = $("<option>");
        		option.val(data[i].typeCode);
        		option.text(data[i].typeName);
        		$("#dogType2").append(option);
        	}
        }
    });
});

$("#dogType2").on("change", function(){
	const result = $("#dogType2").val();
	// console.log(result);
	if(result == "user") {
		$("#dogType1").show();
		$("#dogType1").css("width", "200px");
		$("#dogType1").attr("name", "typeName");
		$("#typeSize").show();
		$("#typeSize").attr("name", "typeSize");
		$("#dogType2").attr("name", "");
		$("#dogType2").hide();
	}
});

$("#typeSize").on("change", function(){
	const size = $(this).val();
	// console.log(size);
});

function dogModal(dogNo) {
	$("#dog-modal").css("display", "flex");
	$("#dogProfileForm").attr("action", "/updateMyDog.do");
	$("#dogType1").css("display", "block");
	$("#typeSize").css("display", "none");
	$("#typeSize").attr("name", "");
	$("#dogType2").css("display", "none");
	$("#dogType2").attr("name", "");
	$("#dogBtn").text("수정하기");
	$("#deleteDogBtn").show();
	$("#deleteDogBtn").attr("onclick", "deleteMyDog("+dogNo+");");
	$("#dogPhoto").prop("name", "");
	$("#dogPhoto").val("");
	$("#dogPreview").attr("src", "");
	$(".dogprofile #updateMent").show();
	$(".dogprofile span").text("*");
	
	$.ajax({
        url  : '/selectMyOneDog.do',
        data : {dogNo : dogNo},
        type : 'post',
        success : function(data){
        	$("#dogName").val(data.dogName);
        	$("#dogName").prop("readonly", true);
        	$("#dogType1").val(data.dogType);
        	$("#dogType1").prop("readonly", true);
        	$("#dogAge").val(data.dogAge);
        	$("#dogWeight").val(data.dogWeight);
        	$("#dogNo").val(data.dogNo); 
        	$("#dogNo").attr("name", "dogNo"); 
        	
        	if(data.dogPhoto != null) {
        		$("#dogPreview").attr("src", "/resources/upload/dog/"+data.dogPhoto);
        	} else{
        		$("#dogPreview").attr("src", "/resources/img/default_dog.png");
        	}
        	
        	if(data.dogGender == "남아") {
        		$("[name=dogGender]").eq(0).prop("checked", true);
        	} else {
        		$("[name=dogGender]").eq(1).prop("checked", true);
        	}
        	
        	if(data.dogNeutral == "O") {
        		$("[name=dogNeutral]").eq(0).prop("checked", true);
        	} else {
        		$("[name=dogNeutral]").eq(1).prop("checked", true);
        	}
        	
        	if(data.dogVacc == "O") {
        		$("[name=dogVacc]").eq(0).prop("checked", true);
        	} else {
        		$("[name=dogVacc]").eq(1).prop("checked", true);
        	}
        	
    		$("#mbti-box").show();
        	if(data.dogMbti != null) {
        		$("#mbtiBtn").hide();
        		$("#dogMbti").show();
        		$("#dogMbti").val(data.dogMbtiName);
        	} else {
        		$("#mbtiBtn").show();
        		$("#dogMbti").hide();
        	}
        }
    });
}

function goToMbti() {
	location.href="/mbtiMateMain.do";
}

function closeDogModal() {
	$("#dog-modal").hide();
}

function addDogFile() {
	$("#dogPhoto").click();
}

// 선택한 사진 미리보기
$("#dogPhoto").on("change", function(event) {
    var file = event.target.files[0];
    var reader = new FileReader(); 
    reader.onload = function(e) {
        $("#dogPreview").attr("src", e.target.result);
    }
    reader.readAsDataURL(file);
    
    const val = $(this).val();
    if(val != null) {
    	$("#dogPhoto").prop("name", "photodog");
    }
    const name = $("#dogPhoto").attr("name");
    
    // console.log(name);
    // console.log(val);
});



// 정규표현식
// 이름 (아무 글자나 1글자 ~ 10글자)
nameReg = /^[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ]{1,10}$/;
// 나이, 몸무게 (숫자로만 1글자 이상)
numReg = /^[0-9]+$/;

$("#dogBtn").on("click", function(){
	// 추가하기일 경우 이름, 품종, 나이, 성별, 몸무게, 중성화, 예방접종 체크
	// 수정하기일 경우 나이, 성별, 몸무게, 중성화, 예방접종만 체크

	const btnText = $("#dogBtn").text();
	
	const nameVal = $("#dogName").val();
	const ageVal = $("#dogAge").val();
	const weightVal = $("#dogWeight").val();
	let selectVal;
	
	// 성별, 중성화, 예방접종 (radio 체크했는지 확인)
	const genderChk = $('input:radio[name=dogGender]').is(':checked');
	const neutralChk = $('input:radio[name=dogNeutral]').is(':checked');
	const vaccChk = $('input:radio[name=dogVacc]').is(':checked');
	
	if(btnText == "추가하기") { // 반려견 추가하는 경우
		// 품종 (select 선택했는지 확인)
		selectVal = $("#dogType2 option:selected").val();
		const typeVal = $("#dogType1").val();
		
		if(nameVal == "") {
			Swal.fire({
				text: '이름을 입력해주세요.',
				confirmButtonColor: '#1abc9c'
			})
		} else if(!nameReg.test(nameVal)) {
			Swal.fire({
				html: '이름은 특수문자를 제외한<br>10글자까지만 입력 가능합니다.',
				confirmButtonColor: '#1abc9c'
			})
		} 
		
		if(ageVal == "") {
			Swal.fire({
				text: '나이를 입력해주세요.',
				confirmButtonColor: '#1abc9c'
			})
		}
		
		if(weightVal == "") {
			Swal.fire({
				text: '몸무게를 입력해주세요.',
				confirmButtonColor: '#1abc9c'
			})
		}
		
		if(!genderChk) {
			Swal.fire({
				text: '성별을 선택해주세요.',
				confirmButtonColor: '#1abc9c'
			})
		}
		
		if(!neutralChk) {
			Swal.fire({
				text: '중성화 여부를 선택해주세요.',
				confirmButtonColor: '#1abc9c'
			})
		}
		
		if(!vaccChk) {
			Swal.fire({
				text: '예방접종 여부를 선택해주세요.',
				confirmButtonColor: '#1abc9c'
			})
		}
		
				
		if(selectVal == "none") {
			Swal.fire({
				text: '품종을 선택하거나 직접 입력해주세요.',
				confirmButtonColor: '#1abc9c'
			})
		} else if(selectVal == "user" && typeVal == "") {
			Swal.fire({
				text: '품종을 직접 입력해주세요.',
				confirmButtonColor: '#1abc9c'
			})
		} else {
			if(nameReg.test(nameVal) && numReg.test(ageVal) && genderChk && numReg.test(weightVal) && neutralChk && vaccChk) {
				$.cookie("tab", 1);
				$("#dogProfileForm").submit();
			}
		}
		
	} else { // 반려견 수정하는 경우
		if(ageVal == "") {
			Swal.fire({
				text: '나이를 입력해주세요.',
				confirmButtonColor: '#1abc9c'
			})
		}
		
		if(weightVal == "") {
			Swal.fire({
				text: '몸무게를 입력해주세요.',
				confirmButtonColor: '#1abc9c'
			})
		}
	
		if(!genderChk) {
			Swal.fire({
				text: '성별을 선택해주세요.',
				confirmButtonColor: '#1abc9c'
			})
		}
		
		if(!neutralChk) {
			Swal.fire({
				text: '중성화 여부를 선택해주세요.',
				confirmButtonColor: '#1abc9c'
			})
		}
		
		if(!vaccChk) {
			Swal.fire({
				text: '예방접종 여부를 선택해주세요.',
				confirmButtonColor: '#1abc9c'
			})
		}
		
		if(numReg.test(ageVal) && genderChk && numReg.test(weightVal) && neutralChk && vaccChk) {
			Swal.fire({
		        title: '반려견 정보 수정',
		        text: "반려견 정보를 수정하시겠습니까?",
		        icon: 'warning',
		        showCancelButton: true,
		        confirmButtonColor: '#1abc9c',
		        cancelButtonColor: '#ccc',
		        confirmButtonText: '수정',
		        cancelButtonText: '취소'
		    }).then((result) => {
		        if (result.isConfirmed) {
		        	$.cookie("tab", 1);
					$("#dogProfileForm").submit();
		        }
		    })
		}
	}
});

function deleteMyDog(dogNo) {
	Swal.fire({
        title: '반려견 정보 삭제',
        text: "반려견 정보를 삭제하시겠습니까?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: 'red',
        cancelButtonColor: '#ccc',
        confirmButtonText: '삭제',
        cancelButtonText: '취소'
    }).then((result) => {
        if (result.isConfirmed) {
        	$.cookie("tab", 1);
			location.href = "/deleteMyDog.do?dogNo="+dogNo;			
        }
    })
}

$(document).keyup(function(e) {
   if ( e.keyCode == 27) {
       closeDogModal();
   };
});