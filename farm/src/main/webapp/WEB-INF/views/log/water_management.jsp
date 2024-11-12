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
	<button class="btn box1 fs-5 gap-3" id="btn-water">물 주기
		<i class="fa-solid fa-oil-can fa-flip-horizontal fs-1" style="color: #1c76ba;"></i>
	</button>
	<div class="box2 fs-5 gap-2">현재토양습도 ${temp.moisture}%
<%-- 	 ${Math.floor((temp.moisture / 1024) * 100)}% --%>
		<i class="fa-solid fa-droplet fs-2 " style="color: #74C0FC;"></i>
	</div>
</div>
<div class="d-flex">
<form method="post" action="water_management">
<select class="mt-4 ms-2 form-select w-px200 gap-4" name="plant_id" id="select-plant" onchange="submit()">
         <option value="${plantid_log}">${vo.plant_name}</option>
         <c:forEach items="${plant}" var="p">
            <c:if test="${p.plant_id != plantid_log}">
                <option value="${p.plant_id}">${p.plant_name}</option>
            </c:if>
        </c:forEach>
      </select>
</form>
</div>

<table class="table tb-list text-center">
<colgroup>
	<col width="200px">
	<col width="">
	<col width="">
</colgroup>
<tr><th>번호</th><th>급수날짜</th><th>토양습도</th></tr>



<c:if test="${empty waterList}">
<tr><td class="text-center" colspan="3">데이터가 없습니다</td></tr>
</c:if>

<c:forEach items="${waterList}" var="vo">
<tr>
	<td>${vo.no}</td>
	<td>${vo.waterdate}</td>
	<td>${vo.soilmoisture} %</td>
<%-- 	${Math.floor((vo.soilmoisture / 1024) * 100)}% --%>
</tr>
</c:forEach>

</table>


<jsp:include page="/WEB-INF/views/include/page.jsp"></jsp:include>

<script>

$("#select-plant").change(function() {
    var selectedPlantId = $(this).val();
    sessionStorage.setItem("plant_id", $(this).val() )

        $.ajax({
            type: "POST",
            url: "/farm/log/saveSelectedPlant",
            data: { plantid_log: selectedPlantId },
            success: function(response) {
                console.log("서버 저장완료");
            },
            error: function() {
                console.error("서버 저장실패");
            }
        });
});


$(document).ready(function() {
    $("#btn-water").on("click", function() {
        $.ajax({
            url: '/farm/relay/waterAndRelay1Update',
            type: 'POST',
            success: function() {
            	alert("물주기 성공");
            	console.log("물주기 기록 및 Relay 상태 업데이트 완료.");
            },
            error: function(xhr, status, error) {
            	alert("물주기 실패");
            	console.error("물주기 및 Relay 상태 업데이트 오류:", error);
            }
        });
    });
});

</script>	
</body>

</html>