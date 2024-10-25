<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>


</head>


<body>
<h3 class="my-t">관찰일지 등록</h3>
<form method="post" action="register" enctype="multipart/form-data" >
 <table>
	 <textarea id="summernote" name="content"></textarea>
	 
 </table>
	 
</form>

<div class="btn-toolbar justify-content-center gap-2">
	<button class="btn btn-primary px-4" id="btn-save">저장</button>
	<button class="btn btn-outline-primary px-4" id="btn-cancel">취소</button>
</div>




<script>

$('#summernote').summernote();

$('#summernote').summernote({
	  height: 300, // set editor height
	  minHeight: null, // set minimum height of editor
	  maxHeight: null, // set maximum height of editor
	  focus: true // set focus to editable area after initializing summernote
	});


$("#btn-save").on("click", function() {
	if( isNotEmpty() ) 
		$("form").submit()
})
$("#btn-cancel").on("click",function(){
	location = "list"
})

</script>




</body>
</html>