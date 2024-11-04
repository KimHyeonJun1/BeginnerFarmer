<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%-- <sec:authorize access="isAutheticated()"> --%>
<%-- 	<sec:authentication property="principal.user" var="auth"/> --%>
<%-- </sec:authorize> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3 class="my-5">게시판 목록</h3>


<form action="list" method="post">
<div class="d-flex mb-2 justify-content-between">
	<div class="col d-flex justify-content-between me-2">
<!-- 		<form method="post" action="list" class="d-flex justify-content-between me-2" id="frmType"> -->
			<input type="hidden" name="board_type_id" value="${board_type_id}">
			<div class="boardTypeButtons d-flex gap-3">
			    <c:forEach items="${boardTypes}" var="bt">
			        <a onclick="boardType(${bt.board_type_id})"
			           class="btn btn-success text-white"> <!-- listType 요청에 board_type_id 파라미터를 포함하여 링크 생성 -->
			            ${bt.board_type_name}
			        </a>
<%-- 			        href="<c:url value='/board/list?board_type_id=${bt.board_type_id}' />"  --%>
			    </c:forEach>
			</div>
			
			<div class="col-auto">
				<select name="listSize" class="form-select">
					<c:forEach var="i" begin="1" end="5">
						<option value="${ 10*i }">${ 10*i }개씩</option>
					</c:forEach>
				</select>
			</div>
			
<!-- 		</form> -->
	</div>
	<!-- 로그인되어 있는 경우만 글쓰기 가능 -->
	<sec:authorize access = "isAuthenticated()">
		<button class="btn btn-success" onclick="location='register'">글쓰기</button>
	</sec:authorize>
</div>

<table class="table tb-list">
<colgroup>
	<col width="100px">
	<col width="150px">
	<col width="">
	<col width="100px">
	<col width="150px">
	<col width="100px">
</colgroup>
<tr><th>번호</th><th>게시판</th><th>제목</th><th>작성자</th><th>작성일자</th><th>조회수</th></tr>

<c:if test="${ empty page.list }">
	<tr><td colspan="6" class="text-center">게시판 글이 없습니다.</td></tr>
</c:if>

<!-- <div class="boardList"> -->
<%--     <c:forEach items="${boardType}" var="board"> --%>
<!--         <div class="boardItem"> -->
<%--             <h4>${board.title}</h4> --%>
<%--             <p>${board.content}</p> --%>
<!--             게시글 내용 표시 -->
<!--         </div> -->
<%--     </c:forEach> --%>
<!-- </div> -->

<c:forEach items="${ page.list }" var="vo">
<tr>
	<td>${ vo.no }</td>
	<td>${ vo.type_name }</td>
	<td><a class="text-link" href="info?board_id=${ vo.board_id }&board_type_id=${ board_type_id}">${ vo.board_title }</a></td>
<%-- 	<td><a class="text-link" href="info?board_id=${ vo.board_id }">${ vo.board_title }</a></td> --%>
	<td>${ vo.board_writer }</td>
	<td>${ vo.board_writedate }</td>
	<td>${ vo.board_readcnt }</td>
</tr>
</c:forEach>
</table>


<div class="d-flex mb-2 justify-content-between">
	<div class="col">
<!-- 		<form method="post" action="list" class="d-flex justify-content-between me-2" id="frmSearch"> -->
			<div class="input-group w-px500">
				<select name="search" class="form-select">
					<option value="all">전체</option>
					<option value="title" ${ page.search == "title" ? "selected" : "" }>제목</option>
					<option value="content" <c:if test="${ page.search eq 'content' }">selected</c:if> >내용</option>
					<option value="writer" <c:if test="${ page.search eq 'writer' }">selected</c:if> >작성자</option>
				</select>
				<input type="text" name="keyword" value="${ page.keyword }" class="form-control w-px300">
				<button class="btn btn-success"><i class="fa-solid fa-magnifying-glass"></i></button>
			</div>
<!-- 		</form> -->
	</div>
</div>
</form>
<jsp:include page="/WEB-INF/views/include/page.jsp"/>

</body>

<script>
function boardType(board_type_id){
	$("[name=board_type_id]").val(board_type_id)
	$("form").submit()
}

// $("[name=listSize]").on("change", function(){
// 	$("form").submit()
// })
// $("[name=listSize]").val( ${page.listSize} ).prop("selected", true)

</script>

</html>