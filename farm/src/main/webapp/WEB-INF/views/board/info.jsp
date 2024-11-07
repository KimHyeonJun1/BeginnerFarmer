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
<h3 class="my-5">${ vo.type_name } </h3>
<table class="table tb-row">
<colgroup>
	<col width="100px">
	<col width="">
	<col width="100px">
	<col width="200px">
</colgroup>
<tr>
	<th>제목</th><td>${ vo.board_title }</td>
	<th>작성일자</th><td>${ vo.board_writedate }</td>
</tr>
<tr>
	<th>작성자</th><td>${ vo.board_writer }</td>
	<th>조회수</th><td>${ vo.board_readcnt }</td>
</tr>
<tr>
	<th>내용</th><td colspan="3">${ vo.board_content }</td>	
</tr>	
</table>

<div class="btn-toolbar justify-content-center gap-2">
	<button class="btn btn-success" id="btn-list">목록으로</button>
	<sec:authorize access="isAuthenticated()"> <!-- 인증(로그인)된 경우 -->
		<sec:authentication property="principal.user" var="auth"/>
		<!-- 로그인한 사용자가 쓴 글에 대해서만 수정/삭제 가능 -->
		<c:if test="${ auth.userid == vo.board_writer }">
			<button class="btn btn-primary" id="btn-modify">정보수정</button>
			<button class="btn btn-danger" id="btn-delete">정보삭제</button>
		</c:if>	
	</sec:authorize>
</div>

<jsp:include page="comment/register.jsp"/>
</body>

<script>
$("#btn-list").on("click",function(){
	location = "list?board_type_id=${board_type_id}"
})


$("#btn-modify").on("click", function() {
	location = "modify?board_id=${vo.board_id}";
});


$("#btn-delete").on("click", function() {
	if( confirm("정말 삭제하시겠습니까?")) {
		$("<form method='post' action='delete'></form>")
		.appendTo("body")
		.append(`<input type="hidden" name="board_id" value="${vo.board_id}">`)
		.append(`<input type="hidden" name="_method" value="delete">`)
		.submit()
		
	}
})
</script>
</html>