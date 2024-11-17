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
<div class="container my-5">
    <!-- 상단: 작물 이름, 이미지, 특징 -->
    <div class="row mb-4 ">
        <div class="col-md-4 text-center">
            <h2>${vo.plant_name}</h2>
            <img src="<c:url value='${ vo.image_path }'/>" alt="${vo.plant_name}" width="250" height="200">
        </div>
        <div class="col-md-8 fw-light fs-3 pt-5">
            <p>${vo.feature}</p>
        </div>
    </div>

    <!-- 중간: 성장기간, 사용한 요리 -->
    <div class="row my-5 border border-3 border-success-subtle align-items-center">
    	<div class="col-md-5 text-center">
        	<h4>성장 기간</h4>
        	<p>${vo.plant_groth}</p>
    	</div>

	    <!-- 세로 점선을 위한 중간 div -->
	    <div class="col-md-1 d-flex justify-content-center">
	        <div class="border border-2 border-success-subtle" style="height: 250px; border-style: dashed !important;"></div>
	    </div>

	    <div class="col-md-6 pt-3">
	        <h4>사용한 요리</h4>
	        <ul>
	            <c:forEach items="${vo.food}" var="food">
	                <li>${food}</li>
	            </c:forEach>
	        </ul>
	    </div>
	</div>


    <!-- 하단: 적정 온도와 적정 습도 -->
    <div class="row">
        <div class="col-md-6 text-center">
            <h5>적정 온도</h5>
            <div class="icon">
                <!-- 적정 온도 아이콘 -->
                <i class="fa-solid fa-temperature-half fs-1 mb-2" style="color: #de1717;"></i>
            </div>
            <p>${vo.standard_temp} °C</p>
        </div>
        <div class="col-md-6 text-center">
            <h5>적정 습도</h5>
            <div class="icon">
                <!-- 적정 습도 아이콘 -->
                <i class="fa-solid fa-droplet fs-1 mb-2" style="color: #74C0FC;"></i>
            </div>
            <p>${vo.standard_hum} %</p>
        </div>
    </div>
</div>

<div class="d-flex justify-content-center mb-5">
	<a class="btn btn-success text-white" href="<c:url value='/guide/list'/>">목록으로</a>
</div>
</body>
</html>