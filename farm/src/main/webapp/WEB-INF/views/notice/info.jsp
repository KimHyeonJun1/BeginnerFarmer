<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3 class="my-5">공지글 정보</h3>
<table class="table tb-row">
<colgroup>
	<col width="100px">
	<col width="">
	<col width="100px">
	<col width="200px">
</colgroup>
<tr>
	<th class="text-center">제목</th><td>${ vo.title }</td>
	<th class="text-center">작성일자</th><td>${ vo.writedate }</td>
</tr>
<tr><th class="text-center">작성자</th><td>${ vo.name }</td>
	<th class="text-center">조회수</th><td>${ vo.readcnt }</td>
</tr>
<tr>
	<th class="text-center">내용</th><td colspan="3">${ fn: replace(vo.content, crlf, "<br>") }</td>
</tr>
<tr><th class="text-center">첨부파일</th>
	<td colspan="5">
		<c:if test="${ ! empty vo.filename }">
			<div class="d-flex">
				<!-- 물리적 파일이 존재하면 다운로드 가능 -->
				<c:if test="${ file }">
					<label role="button" class="d-flex col-auto text-link gap-2 file-download">
						<span>${ vo.filename }</span>
						<i class="fs-3 fa-solid fa-circle-down"></i>
					</label>
				</c:if>
				<!-- 물리적 파일이 존재하지 않으면 다운로드 불가 -->
				<c:if test="${ !file }">
					<del class="text-danger">${ vo.filename }</del>
				</c:if>
			</div>
		</c:if>
	</td>
</tr>
</table>
<div class="btn-toolbar justify-content-center gap-2">
	<button class="btn btn-success" id="btn-list">목록으로</button>
	
	<sec:authorize access="isAuthenticated()">	<!-- 인증(로그인)된 경우 -->
		<sec:authentication property="principal.user" var="auth"/> 
		<!-- 로그인한 사용자가 쓴 글에 대해서만 수정/삭제 가능 -->
		<c:if test="${ auth.userid == vo.writer }">
			<button class="btn btn-primary" id="btn-modify">공지수정</button>
			<button class="btn btn-danger" id="btn-delete">공지삭제</button>
		</c:if>
	</sec:authorize>
</div>
<script>
$(".file-download").on("click", function(){
	location = "<c:url value='/notice/download?id=${vo.id}'/>"
})

$("#btn-list, #btn-modify").on("click", function(){
	var id = $(this).attr("id") // btn-list, btn-modify
	id = id.substr( id.indexOf("-")+1 ) // list, modify
	location = id + "?id=${vo.id}&pageNo=${page.pageNo}&search=${page.search}&keyword=${page.keyword}"
})

$("#btn-delete").on("click", function(){
	if( confirm("정말 삭제하시겠습니까?") ){
		$("<form method='post' action='delete'></form>")
		.appendTo("body")
		.append(`<input type="hidden" name="id" value="${vo.id}">`)
		.append(`<input type="hidden" name="_method" value="delete">`)
		.append(`<input type="hidden" name="pageNo" value="${page.pageNo}">`)
		.append(`<input type="hidden" name="search" value="${page.search}">`)
		.append(`<input type="hidden" name="keyword" value="${page.keyword}">`)
		.submit()
	}
})
</script>
</body>
</html>