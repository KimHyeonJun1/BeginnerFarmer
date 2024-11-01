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
<h3 class="my-t">관찰일지 수정</h3>
<form method="post" action="modify" enctype="multipart/form-data" >
	<table class="table tb-row">
	<tr>
		<th>작물</th>
		<td>
          <select class="form-select  " name="plant_id" title="작물이름" >
              <c:forEach items="${plant}" var="p">
				<option  <c:if test="${vo.plant_id eq p.plant_id}">selected</c:if> value="${p.plant_id}"> ${p.plant_name} </option>
              </c:forEach>
             </select>
        </td>
	</tr>
	<tr>
		<th>제목</th>
		<td>
			<input type="text" name="diary_title" value="${diary.diary_title}" title="제목" class="check-empty form-control">
		</td>		
	</tr>
	<tr>
		<th>내용</th>
		<td>
			<textarea id="summernote" name="diary_content" title="내용" class="check-empty form-control">${diary.diary_content}</textarea>
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

	if( isNotEmpty() ) {
		let summernote = new Object();
		summernote.diary_title = $("[name=diary_title]").val();
		summernote.diary_content = $("[name=diary_content]").val();
		summernote.plant_id = $("[name=plant_id]").val();
		summernote.diary_id = ${diary.diary_id} ;

		$.ajax({
			url : "<c:url value='modify'/>",
	       	type : "PUT",
	        contentType : "application/json; charset=utf-8",
	        data: JSON.stringify(summernote),
	        success : function (data){
	        	console.log(data)
	           	if(data){
	               	location.href="<c:url value='info?id=${diary.diary_id}'/>";
	           	}else{
	               	alert("저장 오류 발생");
	           	}
			}
   	    });
	}
})




$("#btn-cancel").on("click",function(){
	location = "info?id=${diary.diary_id}"
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
              } ,
          	    onBlur: function() {
//             	      alert('Editable area loses focus[' +  $(this).val() +'] \n\n'
//             	    		  + $(".note-editable").html() );
           	    },
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
//                     var json = JSON.parse(data);
//                     $(el).summernote('editor.insertImage',json["url"]);
//                         jsonArray.push(json["url"]);
//                         jsonFn(jsonArray);
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