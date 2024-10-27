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

<h3>주간농사정보</h3>

<div class="d-flex mb-2 justify-content-between">
	<div class="col">
		<form method="post" action="list" class="d-flex justify-content-between">
			<div class="input-group w-px500">
				<select name="search" class="form-select w-px100">
					<option value="all">전체</option>
					<option value="title" ${page.search == "title" ? "selected" : "" }>제목</option>
					<option value="content" <c:if test="${page.search eq 'content'}">selected</c:if> >내용</option>
				</select>
				<input type="text" name="keyword" value="${page.keyword }" class="form-control w-px300">
				<button class="btn btn-success" ><i class="fa-solid fa-magnifying-glass" style="color: #ffffff;"></i></button>
			</div>
			
			<div class="col-auto">
				<select name="listSize" class="form-select">
					<c:forEach var="i" begin="1" end="5">
					<option value="${ 10*i }">${ 10*i }개씩</option>
					</c:forEach>
				</select>
			</div>
		</form>
	</div>
</div>

<table class="table tb-list text-center">
	<colgroup>
		<col width="100px">
		<col width="">
		<col width="150px">
		<col width="200px">
		<col width="150px">
		<col width="150px">
	</colgroup>
	<thead>
		<tr>
			<th>번호</th><th>제목</th><th>작성자</th><th>등록일</th><th>조회수</th><th>첨부</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${ weekInfo.totalCount==0 }">
			<tr><td colspan="6" class="text-center">주간농사정보가 없습니다.</td></tr>
		</c:if>
		<tr>
			<td></td>
			<td>${ item.subject }</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</tbody>
	
</table>


</body>
</html>