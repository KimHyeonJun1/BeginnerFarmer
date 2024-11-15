<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3 class="my-5">주간농사정보</h3>

<div class="d-flex mb-2 justify-content-between">
<!-- <form name="searchApiForm"> -->
<!-- 	<input type="hidden" name="pageNo"> -->
<%-- 	<input type="text" name="searchword" value="<%=request.getParameter("searchword")==null?"":request.getParameter("searchword")%>"> --%>
<!-- 	<input type="button" name="search" value="검색" onclick="return fncSearch();"/> -->
<!-- 	<button class="btn btn-success" type="submit"><i class="fa-solid fa-magnifying-glass" style="color: #ffffff;"></i></button> -->
<!-- </form> -->
<div class="col-auto d-flex gap-2"><span class="fs-5 text-success">※ 제목이나 첨부파일 클릭시 파일이 다운로드됩니다. ※</span></div>
	<div class="col-auto">
		<select id="listSize" class="form-select">
			<c:forEach var="i" begin="1" end="5">
				<option value="${ 10*i }">${ 10*i }개씩</option>
			</c:forEach>
		</select>
	</div>
</div>

<table class="table tb-list text-center" id="weekInfo">
	<colgroup>
		<col width="100px">
		<col width="">
		<col width="200px">
		<col width="150px">
		<col width="150px">
	</colgroup>
	<thead>
		<tr>
			<th>번호</th><th>제목</th><th>등록일</th><th>조회수</th><th>첨부</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${ weekInfo.totalCount==0 }">
			<tr><td colspan="6" class="text-center">주간농사정보가 없습니다.</td></tr>
		</c:if>
		<c:set var="No" value="${ page.endList }"/> 
		<c:forEach items="${ weekInfo.items.item }" var="item">
		
		<tr>
			<td>${ No }</td>
			<td><a href="${item.downUrl}">${ item.subject }</a></td>
<%-- 			<td>${ item.writerNm }</td> --%>
			<td>${ item.regDt }</td>
			<td>${ item.hitCt }</td>
			<td><a href="${item.downUrl}"><i class="fa-regular fa-file"></i></a></td>
		</tr>
		<c:set var="No" value="${ No-1 }"/>
		</c:forEach>
	</tbody>
</table>

<jsp:include page="/WEB-INF/views/include/subPage.jsp"/>

<script>
$(function(){
	$("#listSize").val( ${page.listSize} ).prop("selected", true)
	
	$("a.page-link").on("click", function(){
		location = "<c:url value='list?pageNo='/>"+$(this).data("page")+"&listSize="
				+$("#listSize option:selected").val()
	})

    // listSize 값 변경 시 실행되는 함수
    $("#listSize").on("change", function() {
        if ($("table#weekInfo").length > 0) {
        	location = "<c:url value='list?pageNo=1&listSize='/>"+$(this).val()
        }
    });
})

//검색
// function fncSearch(){
// 	with(document.searchApiForm){
// 		if(searchword.value.replace(/\s/g,"") == ""){
// 	        alert("검색어를 입력해 주세요");
// 	        searchword.focus();
// 	        return false;
// 	    }else{
// 			method="get";
// 			action = "list.jsp";
// 			target = "_self";
// 			submit();
// 		}
// 	}
// }

// 	$.ajax({
// 		url: "weekInfo"
// 	}).done(function(rs){
// 		console.log(rs)
// 	})
</script>

</body>
</html>