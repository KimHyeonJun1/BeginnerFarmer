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
	<div class="box2 fs-5 gap-2">현재토양습도
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



<%-- <c:if test="${empty waterList}"> --%>
<!-- <tr><td class="text-center" colspan="3">데이터가 없습니다</td></tr> -->
<%-- </c:if> --%>

<%-- <c:forEach items="${waterList}" var="item"> --%>
<!-- <tr> -->
<%-- 	<td>${item.no}</td> --%>
<%-- 	<td>${item.waterdate}</td> --%>
<%-- 	<td>${item.soilmoisture} %</td> --%>
<!-- </tr> -->
<%-- </c:forEach> --%>

<!-- </table> -->

<c:forEach items="${page.list}" var="vo">
<tr>
	<td>${vo.no}</td>
	<td>${vo.waterdate }</td>
	<td>${vo.soilmoisture }</td>

</tr>
</c:forEach>

</table>


<jsp:include page="/WEB-INF/views/include/page.jsp"></jsp:include>

<!-- $(function(){ -->
<%-- 	$("#listSize").val( ${page.listSize} ).prop("selected", true) --%>
	
<!-- 	$("a.page-link").on("click", function(){ -->
<%-- 		location = "<c:url value='list?pageNo='/>"+$(this).data("page")+"&listSize=" --%>
<!-- 				+$("#listSize option:selected").val() -->
<!-- 	}) -->

<!--     // listSize 값 변경 시 실행되는 함수 -->
<!--     $("#listSize").on("change", function() { -->
<!--         if ($("table#weekInfo").length > 0) { -->
<%--         	location = "<c:url value='list?pageNo=1&listSize='/>"+$(this).val() --%>
<!--         } -->
<!--     }); -->
<!-- }) -->
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
</script>	
</body>

</html>