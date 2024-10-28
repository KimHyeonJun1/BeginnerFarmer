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

<h3>실시간모니터링</h3>
<!-- 	<div class="d-flex justify-content-center mb-3"> -->
		<h3 class="d-flex justify-content-center mb-3 fs-10 me-6">${vo.plant_name}가 자라고 있어요 !</h3>
<!-- 	</div> -->
	<div class ="box d-flex">
		<div class="cctv justify-content-center ">
<!-- 			카메라부분 -->
		</div>
	</div>
	<div class="box d-flex mt-5 justify-content-center">
		<div class="p-2 fs-5" >키운지 ${vo.register_date} 일 차에요!</div>
	</div>
	
	
	
	
	
</body>
</html>