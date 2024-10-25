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

<table>
	<colgroup>
		<col width="100px">
		<col width="600px">
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
			<tr><td colspan="6" class="text-center">주간농사정보가 없습니다.</td><tr>
		</c:if>
	</tbody>
	
</table>


</body>
</html>