/**
 *  공통처리함수
 */

"use strict"

$(function() {
	var today = new Date();
	var range = today.getFullYear()-100 + ":" + today.getFullYear(); //년도선택범위
	
	$.datepicker.setDefaults({
		dateFormat: "yy-mm-dd",
		changeYear: true,
		changeMonth: true,
		showMonthAfterYear: true,
		monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월"
						, "7월", "8월", "9월", "10월", "11월", "12월" ],
		dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
		maxDate: today,  //최대선택가능날짜
		yearRange: range, 
	})
	
	$(".date").attr("readonly", true); //날짜 입력불가(달력선택만가능)
		$(".date").datepicker();
//		$(".ui-datepicker").css("display", "none"); // ui 스타일

	
	

	$(".date").on("change", function(){
		$(this).next(".date-remove").removeClass("d-none")
	})
});
//--------------------------------------------------------------
	
$(document)
.on("click", ".date + .date-remove", function(){
	//폰트이미지가 동적으로 만들어지므로 문서에 이벤트 등록
	$(this).addClass("d-none").prev(".date").val("");
})
//우편번호 주소찾기처리
function findPost( post, address1, address2 ){
	new daum.Postcode({
	    oncomplete: function(data) {
	    	//console.log( data )
	    	post.val( data.zonecode )
	    	var address = data.userSelectedType == "R" ? data.roadAddress : data.jibunAddress
	    	if( data.buildingName != "" ) address += " ("+data.buildingName+")"
	    	address1.val( address )
	    	address2.val("")
	    }
	}).open();		
}
//--------------------------------------------------------------

//필수입력항목 입력여부 확인
function isNotEmpty() {
    var ok = true;
	var summernote = $('#summernote').length == 0 ? false:true
	
    // 텍스트 입력 필드 확인
    $(".check-empty").each(function() {
        if ($(this).val() === "") {
            alert($(this).attr("title") + " 입력하세요!");
            $(this).focus();
            ok = false;
            return ok; // 루프 종료
        }
    });

    // 썸머노트 내용 확인
	if(summernote ){
		var status = $('#summernote').summernote('isEmpty')
		
		if( status ){
			ok = false;
		}else{
			 var p = $('.note-editable p')
			 var content = ''
			 for(var i=0; i<p.length; i++){
				var span = $(p[i]).children()
				for(var j=0; j<span.length; j++){
					content += $(span[j]).html()
				}
			 }
			 content = content.replace(/<br>/g, '').replace(/\s/g, '')
			 ok = content.length==0 ? false : true;
		}
		if( !ok ){
			alert($('#summernote').attr("title") + " 입력하세요!");
	        $('#summernote').summernote('focus'); // 커서를 내용란으로 이동
		}
		
	}

    return ok;
}

//--------------------------------------------------------------

// 요청한 정보를 info에 담아놓기
function addToForm(info) {
    return `<input type="hidden" name="id" value="${info.id}">
	        <input type="hidden" name="pageNo" value="${info.pageNo}">
	        <input type="hidden" name="search" value="${info.search}">
	        <input type="hidden" name="keyword" value="${info.keyword}">
	        <input type="hidden" name="listSize" value="${info.listSize}">`
			;
}


