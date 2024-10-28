<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>


</head>


<body>
<h3 class="my-t">관찰일지 등록</h3>
<form method="post" action="register" enctype="multipart/form-data" >
	<table class="table tb-row">
	<tr>
		<th>제목</th>
		<td>
			<input type="text" name="title" title="제목" class="check-empty form-control">
		</td>		
	</tr>
	
	<tr>
		<th>내용</th>
		<td>
			<textarea id="summernote" name="content" title="내용" class="check-empty form-control"></textarea>
		</td>		
	</tr>
	</table>
</form>
	
		
	 
	 

<div class="btn-toolbar justify-content-center gap-2">
	<button class="btn btn-success px-4" id="btn-save">저장</button>
	<button class="btn btn-outline-success px-4" id="btn-cancel">취소</button>
</div>




<script>

$("#btn-save").on("click", function() {
	if( isNotEmpty() ) 
		$("form").submit()
})

// 저장 버튼 클릭 시 AJAX로 서버에 데이터 전송
// $("#btn-save").on("click", function(event) {
//     event.preventDefault();  // 기본 폼 전송 방지

//     // 데이터 확인용 로그 출력
//     console.log("저장 버튼 클릭!");

//     // 폼 데이터 수집
//     var formData = new FormData($("form")[0]);

//     $.ajax({
//         type: "POST",
//         url: "/farm/diary/register",
//         data: formData,
//         processData: false,
//         contentType: false,
//         success: function(response) {
//             console.log("성공:", response);  // 응답 데이터 출력
//             location.href = "/farm/diary/list";  // 성공 시 목록 페이지로 이동
//         },
//         error: function(xhr, status, error) {
//             console.log("에러 상태:", status);  // 에러 상태 코드
//             console.log("에러 메시지:", error);  // 에러 메시지
//             console.log("응답 본문:", xhr.responseText);  // 에러 내용 출력
//         }
//     });
// });


$("#btn-cancel").on("click",function(){
	location = "list"
})

//summernote 
$('#summernote').summernote({
	  height: 300, // 에디터 높이
	  minHeight: null, // 최소높이
	  maxHeight: null, // 최대높이
	  focus: true, // 에디터 로딩 후 포커스 여부
	  lang: "ko-KR",					// 한글 설정
	  placeholder: '내용을 입력하세요.',	//placeholder 설정
	  
	  toolbar: [
		    // [groupName, [list of button]]
		    ['fontname', ['fontname']],
		    ['fontsize', ['fontsize']],
		    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
		    ['color', ['forecolor','color']],
		    ['table', ['table']],
		    ['para', ['ul', 'ol', 'paragraph']],
		    ['height', ['height']],
		    ['insert',['picture']],
		    ['view', [ 'help']]
		  ],
		fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
		fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
		
		callbacks: {
	          onImageUpload : function(files, editor, welEditable){

	                // 파일 업로드(다중업로드를 위해 반복문 사용)
	                for (var i = files.length - 1; i >= 0; i--) {
	                    uploadSummernoteImageFile(files[i],
	                this);
	                    }
	                }
	            } 
	});
	
$('#summernote').summernote('fontSize', '24');
	






</script>




</body>
</html>