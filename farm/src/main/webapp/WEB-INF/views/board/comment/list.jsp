<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.user" var="auth"/>
</sec:authorize>

<!-- 댓글이 없는 경우 -->
<c:if test="${ empty page.list }">
<div class="text-center">
	<div>댓글이 없습니다</div>
	<div class="fs-5">첫번째 댓글을 남겨주세요</div>
</div>
</c:if>
 
<!-- 댓글이 있는 경우 -->
<c:forEach items="${ page.list }" var="vo">
	<div class="comment py-3 border-bottom" data-id="${ vo.id }">
		<div class="d-flex gap-2 align-items-center mb-2 justify-content-between">
			<div>
				<span>${ vo.name } [ ${ vo.writedate }]</span>
			</div>
			<c:if test="${ auth.userid == vo.writer }"> <!-- 로그인된 사용자가 쓴 글만 수정/삭제 가능 -->
			<div>
				<label class="me-3"><span>댓글수정 [ </span><span class="writing">${ fn: length(vo.content) }</span><span> / 200 ]</span></label>
				<a class="btn btn-outline-info btn-sm btn-modify-save">수정</a>
				<a class="btn btn-outline-danger btn-sm btn-delete-cancel">삭제</a>
			</div>
			</c:if>
		</div>
		<div class="content">${fn: replace ( fn:replace(vo.content, lf, "<br>"), crlf, "<br>")} 
		</div>
	</div>
</c:forEach>

<c:if test="${ !empty page.list }">
<jsp:include page="/WEB-INF/views/include/subPage.jsp"/>
</c:if>

<script>
$(".btn-modify-save").on("click", function(){
	var comment = $(this).closest(".comment");  // 해당 댓글요소
	
	if( $(this).text()=="수정"){
		modifyStatus(comment);
	} else { // 저장
		$.ajax({
			type: "put",
			url: "comment/modify",
			data: { id: comment.data("id"), content: comment.find("textarea").val() }
		}).done(function( response ){
			// console.log(response)
			alert( response.message )
			if( response.success ){
				comment.find("span.d-none").text( response.content )
				viewStatus( comment )
			}
		})
	}
})
$(".btn-delete-cancel").on("click", function(){
	var comment = $(this).closest(".comment");  // 해당 댓글요소
	
	if( $(this).text()=="취소"){
		viewStatus(comment);		
	} else { // 삭제
		if( confirm('댓글을 삭제하시겠습니까?') ){
			$.ajax({
				type: "delete",
				url: "comment/delete/" + comment.data("id")
			}).done(function( success ){
				if( success ){
					commentList( ${page.pageNo} );
				} else {
					alert("댓글이 삭제되지 않았습니다")
				}
			})
		}
		
	}
})
// 수정상태
function modifyStatus(comment){
	// 버튼: 저장/취소
	comment.find(".btn-modify-save").text("저장").removeClass("btn-outline-info").addClass("btn-primary");
	comment.find(".btn-delete-cancel").text("취소").removeClass("btn-outline-danger").addClass("btn-secondary");
	
	// 내용: .content 에 있는 텍스트가 textarea에 담겨있게
	// 변경 취소의 경우를 대비해서 원래 댓글 내용을 담아두기
	var content = comment.find(".content");
	var content_text = content.html().replace(/<br>/g, '\n');
	content.html( `<textarea class="form-control h-px110">\${content_text}</textarea>
				   <span class="d-none">\${content_text}</span>` )
	content.find("textarea").focus();
}
// 보기상태
function viewStatus(comment){
	// 버튼: 수정/삭제
	comment.find(".btn-modify-save").text("수정").removeClass("btn-primary").addClass("btn-outline-info");
	comment.find(".btn-delete-cancel").text("삭제").removeClass("btn-secondary").addClass("btn-outline-danger");
	
	// 내용: textarea의 내용을 .content의 텍스트로
	var content = comment.find(".content")
	var text = content.find("span").text();
	comment.find(".writing").text( text.length ); // 글자수 원래대로
	content.html( text.replace(/\n/g, '<br>') ) // 댓글내용 원래대로
}
</script>