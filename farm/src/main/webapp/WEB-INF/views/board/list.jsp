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
			        <a onclick="boardType(${bt.board_type_id}); activeButton(this);"
			           class="btn ${bt.board_type_id == board_type_id ? 'btn-success' : 'btn-outline-success'}">
			            ${bt.board_type_name}
			        </a>
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
		<button type="button" class="btn btn-success" onclick="location='register'">글쓰기</button>
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
<tr><th class="text-center">번호</th><th class="text-center">게시판</th><th>제목</th><th class="text-center">작성자</th>
<th class="text-center">작성일자</th><th class="text-center">조회수</th></tr>

<c:if test="${ empty page.list }">
	<tr><td colspan="6" class="text-center">게시판 글이 없습니다.</td></tr>
</c:if>

<c:forEach items="${ page.list }" var="vo">
<tr>
	<td class="text-center">${ vo.no }</td>
	<td class="text-center">${ vo.type_name }</td>
	<td><a class="text-link" href="javascript:info( ${ vo.board_id } )">${ vo.board_title }</a></td>
	<td class="text-center">${ vo.board_writer }</td>
	<td class="text-center">${ vo.board_writedate }</td>
	<td class="text-center">${ vo.board_readcnt }</td>
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

function info( board_id ){
	// form태그에 search, keyword, listSize
	$("form").append(`<input type="hidden" name="board_id" value="\${board_id}">`)
			 .append(`<input type="hidden" name="board_type_id" value="${board_type_id}">`)
			 .append(`<input type="hidden" name="pageNo" value="${page.pageNo}">`)
			 .attr("action", "info")
			 .submit();
}

$("[name=listSize]").on("change", function(){
	$("form").submit()
})
$("[name=listSize]").val( ${page.listSize} ).prop("selected", true)

// 버튼 활성화 처리 함수
function activeButton(button) {
    // 모든 버튼을 비활성화 (btn-outline-success)로 변경
    $(".boardTypeButtons .btn").removeClass("btn-success").addClass("btn-outline-success");
    // 클릭된 버튼만 활성화 (btn-success)로 변경
    $(button).removeClass("btn-outline-success").addClass("btn-success");
}
</script>

</html>