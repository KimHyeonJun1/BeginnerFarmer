<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관찰일지 정보</title>
</head>

<body>
<h3 class="my-t">관찰일지 상세 정보</h3>
<table class="table tb-row">
<colgroup>
	<col width ="50x">
	<col width ="200px">
</colgroup>
	<tr>
		<th>작물</th>
		<td>${diary.plant_name}</td>
	</tr>
    <tr>
        <th>제목</th>
        <td>${diary.diary_title}</td> <!-- DB에서 가져온 제목 -->
    </tr>
    <tr>
        <th>내용</th>
        <td>
            <div id="summernote" class="form-control " >${diary.diary_content}</div> <!-- DB에서 가져온 내용 -->
        </td>
    </tr>
</table>



<div class="btn btn-toolbar justify-content-center gap-2">
	<button class="btn btn-outline-success" id="btn-list">목록으로</button>
	<button class="btn btn-outline-primary px-4" id="btn-modify">수정</button> 
	<button class="btn btn-outline-danger px-4" id="btn-delete">삭제</button>
</div>

<script>

$("#btn-list").on("click",function(){
	location = "list"
})


$("#btn-modify").on("click", function() {
	location = "modify?id=${diary.diary_id}";
});


$("#btn-delete").on("click", function() {
	if( confirm("정말 삭제하시겠습니까?")) {
		$("<form method='post' action='delete'></form>")
		.appendTo("body")
		.append(`<input type="hidden" name="id" value="${diary.diary_id}">`)
		.append(`<input type="hidden" name="_method" value="delete">`)
		.submit()
		
	}
})



</script>

</body>
</html>
