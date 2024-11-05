<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3 class="my-5">게시판 등록</h3>

<form method="post" action="register" enctype="multipart/form-data" >
	<table class="table tb-row mt-5">
	<tr>
		<th>게시판</th>
		<td>
          <select class="form-select" name="board_type_id" title="게시판종류" >
              <c:forEach items="${boardTypes}" var="bt">
                  <option value="${bt.board_type_id}" >${bt.board_type_name}</option>
              </c:forEach>
             </select>
        </td>
	</tr>
	<tr>
		<th>제목</th>
		<td>
			<input type="text" name="board_title" title="제목" class="check-empty form-control">
		</td>		
	</tr>
	<tr>
		<th>내용</th>
		<td>
			<textarea id="summernote" name="board_content" title="내용" class="check-empty form-control"></textarea>
		</td>		
	</tr>
	</table>
</form>
	
<div class="btn-toolbar justify-content-center gap-2">
	<button class="btn btn-success px-4" id="btn-save">저장</button>
	<button class="btn btn-outline-danger px-4" id="btn-cancel">취소</button>
</div>

<script>

$("#btn-save").on("click", function() {
	if( isNotEmpty() ) {
		//썸머노트 내용 가져오기
		let summernote = new Object();
		summernote.board_title = $("[name=board_title]").val();
		summernote.board_content = $("[name=board_content]").val();
		summernote.board_type_id = $("[name=board_type_id]").val();
	
		$.ajax({
			url : "<c:url value='register'/>",
	       	type : "POST",
	        contentType : "application/json; charset=utf-8",
	        data: JSON.stringify(summernote),
	        success : function (data){
	           	if(data){
	               	location.href="<c:url value='list'/>";
	           	}else{
	               	alert("저장 오류 발생");
	           	}
			}
   	    });
	}
})



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
	  disableDragAndDrop: true,
	  
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
		fontNames: ['맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체','Arial', 'Arial Black', 'Comic Sans MS', 'Courier New'],
		fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
		
		callbacks: {
	          onImageUpload : function(files, editor, welEditable){

	                // 파일 업로드(다중업로드를 위해 반복문 사용)
	                for (var i = files.length - 1; i >= 0; i--) {
	                    uploadSummernoteImageFile(files[i], this);
	                }
              } 
       } 
});
	
$('#summernote').summernote('fontSize', '24');

function uploadSummernoteImageFile(file, el) {
    var data = new FormData();
    data.append('file', file);
    
    $.ajax({
      url: "<c:url value='summernote_image/upload'/>",
      type: "POST",
      enctype: 'multipart/form-data',
      data: data,
      cache: false,
      contentType : false,
      processData : false,
      success : function( url ) {
      	  console.log( url )
          summerImage.push( url );
          $(el).summernote('editor.insertImage', url);

console.log( summerImage )
        },
        error : function(e) {
            console.log(e);
        }
   });
}
	
var summerImage = [];

</script>
</body>
</html>