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
			        <a href="javascript:void(0);" onclick="boardType(${bt.board_type_id}); activateButton(this);"
			           class="btn btn-outline-success "> <!-- listType 요청에 board_type_id 파라미터를 포함하여 링크 생성 -->
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

// 액티브 처리 미완료
// function activateButton(clickedButton) {
//     // 모든 boardTypeButtons 내의 버튼에서 btn-success를 제거하고 btn-outline-success 추가
//     $('.boardTypeButtons .btn').removeClass('btn-success').addClass('btn-outline-success');
    
//     // 클릭된 버튼에만 btn-success 추가하여 활성화 표시
//     $(clickedButton).removeClass('btn-outline-success').addClass('btn-success');
// }

// function boardType(boardTypeId) {
//     // 선택된 board_type_id를 hidden input에 설정
//     $('input[name="board_type_id"]').val(boardTypeId);
//     console.log("Selected board_type_id:", boardTypeId);
//     $.ajax({
//         url: '/farm/board/list',            // 서버의 엔드포인트
//         type: 'GET',                     // 요청 방식
//         data: { board_type_id: boardTypeId }, // 보낼 데이터
//         success: function(response) {
//             $('#list').html(response);  // 응답 데이터를 목록에 표시
//         },
//         error: function(xhr, status, error) {
//             console.error("Error fetching board list:", error);
//         }
//     });
// }
</script>

</html>