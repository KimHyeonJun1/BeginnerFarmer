<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모니터링</title>
</head>
<style>
.cctv {
heigth: 500px;
margin-bottom: 20px;
padding: 100px;
border-width: 5px;
justfiy-content: center;
}
</style>

<body>

<h3 class="my-2">실시간모니터링</h3>
	
<div class="d-flex justify-content-center mb-3">	
<h3 class="mb-4 fs-10 me-5">${vo.plant_name} 자라고 있어요 !</h3>
<form method="post" action="monitor">
<select class=" form-select w-px150 gap-4" name="plant_id" id="select-plant" onchange="submit()">
         <option value="${plantid_log}">${vo.plant_name}</option>
         <c:forEach items="${plant}" var="p">
            <c:if test="${p.plant_id != plantid_log}">
                <option value="${p.plant_id}">${p.plant_name}</option>
            </c:if>
        </c:forEach>
      </select>
</form>
     </div>
<div class ="box d-flex">
		<div class="cctv justify-content-center ">
<!-- 			카메라부분 -->
		</div>
	</div>
	<div class="box d-flex mt-5 justify-content-center">
		<div class="p-2 fs-5" >키운지 ${vo.today} 일 차에요!</div>
	</div>

	

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