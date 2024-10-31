<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
.box1{
	border-radius: 6px;
	display: flex;
	width: 250px;
	height: 100px;
	border: 2px solid black;
	float: left;
	margin: 20px;
	box-sizing: border-box;
	flex-direction: row; /* 세로 방향 정렬 */
    justify-content: center; /* 수직 중앙 정렬 */
    align-items: center; /* 수평 중앙 정렬 */
}
.box2{
	border-radius: 6px;
	display: flex;
	width: 250px;
	height: 100px;
	border: 2px solid black;
	float: left;
	margin: 20px;
	flex-direction: row; /* 세로 방향 정렬 */
    justify-content: center; /* 수직 중앙 정렬 */
    align-items: center; /* 수평 중앙 정렬 */
</style>
<body>
<h3 class="my-2">급수관리</h3>

<div class="mt-4">
	<button class="btn box1 fs-5 gap-3">물 주기
		<i class="fa-solid fa-oil-can fa-flip-horizontal fs-1" style="color: #1c76ba;"></i>
	</button>
	<div class="box2 fs-5 gap-2">현재토양습도 ${vo.moisture}%
		<i class="fa-solid fa-droplet fs-2 " style="color: #74C0FC;"></i>
	</div>
</div>


<table class="table tb-list ">
<colgroup>
	<col width="200px">
	<col width="">
	<col width="">
</colgroup>
<tr><th>번호</th><th>급수날짜</th><th>토양습도</th></tr>



<c:if test="${empty list}">
<tr><td class="text-center" colspan="3">데이터가 없습니다</td></tr>
</c:if>

<c:forEach items="${list}" var="vo">
<tr>
	<td>${vo.water_id}</td>
	<td>${vo.writedate}</td>
	<td>${vo.soilmoisture}</td>
</tr>
</c:forEach>

</table>




</body>

</html>