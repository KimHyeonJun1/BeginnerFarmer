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

.container {
/* 	display: grid; */
/* 	gap: 20px; */
/* 	max-width: 1520px; */
/* 	grid-template-columns: 4fr 2fr 7fr; */
/*  	grid-template-rows: 300px; */
display: grid;
	gap: 20px;
	max-width: 1520px;
	grid-template-columns: 4fr 2fr 7fr;
	grid-template-rows: 280px;
}
.box1{
	border-radius: 6px;
	display: flex;
	width: 190px;
	height: 70px;
	border: 2px solid black;
	margin-bottom: 20px;
	margin-right: 20px;
	box-sizing: border-box;
 	flex-direction: row; /* 세로 방향 정렬 */ 
    justify-content: center; /* 수직 중앙 정렬 */
    align-items: center; /* 수평 중앙 정렬 */
    z-index: 10;
}
.box2{
	border-radius: 6px;
	display: flex;
	width: 610px;
	height: 70px;	
	border: 2px solid black;
	flex-direction: column; /* 세로 방향 정렬 */
    justify-content: center; /* 수직 중앙 정렬 */
    align-items: center; /* 수평 중앙 정렬 */
 }
.box3{
	display:flex;
	width: 650px;
	height: 340px;	
	margin: 20px;
	border: 2px solid black;
 	flex-direction: column; /* 세로 방향 정렬 */
    justify-content: center; /* 수직 중앙 정렬 */
    align-items: center; /* 수평 중앙 정렬 */
 }
 
 
</style>
<body>
<h3 class="my-2 mb-4">온도/습도/조도</h3>
<div class="container">
		<div class="item"><img src="<c:url value='${vo.image_path}'/>" alt="식물" width="340px" height="240px"></div>
		<div class="item">작물 정보</div>
		<div class="item">
			<div class="d-flex">
				<div class="box1">현재온도 </div>
				<div class="box1 gap-2">적정온도 ${vo.standard_temp}°C<i class="fa-solid fa-temperature-half fs-4" style="color:#de1717;"></i></div>
				<button class="btn box1 gap-2">온습도 낮추기  <img src="<c:url value='/img/switch-on.png'/>" alt="on" ></button>
			</div>
			<div class="d-flex">
				<div class="box1">현재습도 </div>
				<div class="box1 gap-2">적정습도 ${vo.standard_hum}%<i class="fa-solid fa-droplet fs-4" style="color:#74C0FC;"></i></div>
				<button class="btn box1 gap-2">온습도 낮추기  <img src="<c:url value='/img/switch-off.png'/>" alt="off" ></button>
			</div>		
			<div class="box2">
				<div class="d-flex gap-5">
				<button class="btn">OFF</button>
				
				<button class="btn">
<!-- 				<i class="fa-solid fa-sun fs-2" style="color: #FFD43B;"></i> -->
				<i class="fa-regular fa-lightbulb fs-2" style="color: #FFD43B;"></i>
				</button>
				<button class="btn">
<!-- 				<i class="fa-solid fa-sun fs-2" style="color: #FFD43B;"></i> -->
				<i class="fa-regular fa-lightbulb fs-2" style="color: #FFA500;"></i>
				</button>
				<button class="btn">
<!-- 				<i class="fa-solid fa-sun fs-2" style="color: #FFD43B;"></i> -->
				<i class="fa-regular fa-lightbulb fs-2" style="color: #d94844;"></i>
				</button>
				</div>
			</div>
		</div>
</div>
		<div class="item">
			<div class="d-flex">
				<div class="box3">
				
				dddd
				
				</div>
				<div class="box3">
				
				dddddddddd
				
				</div>
			</div>
			</div>
		<div class="item">
			<div class="d-flex">
				<div class="box3">
				
				
				
				
				
				</div>
				<div class="box3">
				
				
				
				
				</div>
			</div>
				<canvas id="chart" class="m-auto h-100"></canvas>
		</div>

			
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-autocolors"></script>
<script src="<c:url value='/js/chart.js'/>"></script>
			
			
<script>

</script>

</body>
</html>