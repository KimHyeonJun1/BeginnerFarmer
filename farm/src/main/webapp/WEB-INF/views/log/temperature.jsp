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
 [id^="myChart"] {
 	width: 630px;
 	height: 320px;
 }
 
</style>
<body>
<h3 class="my-2 mb-4">온도/습도/조도</h3>
<div class="container">
		<div class="item z-3">
		<form method="post" action="temperature">
		<select class="form-select w-px200 gap-4 mb-2" name="plant_id" id="select-plant" onchange="submit()">
         <option value="${plantid_log}">${vo.plant_name}</option>
         <c:forEach items="${plant}" var="p">
            <c:if test="${p.plant_id != plantid_log}">
                <option value="${p.plant_id}">${p.plant_name}</option>
            </c:if>
        </c:forEach>
      </select>
</form>
		<img src="<c:url value='${vo.image_path}'/>" alt="식물" width="340px" height="240px">
		</div>
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
				
					<div>
  						<canvas id="myChart_temp"></canvas>
					</div>
				
				</div>
				<div class="box3">
				
					<div>
  						<canvas id="myChart_hum"></canvas>
					</div>
				
				</div>
			</div>
			</div>
		<div class="item">
			<div class="d-flex">
				<div class="box3">
				
					<div>
  						<canvas id="myChart_moisture"></canvas>
					</div>
				
				
				
				</div>
				<div class="box3">
				
					<div>
  						<canvas id="myChart_bright"></canvas>
					</div>
				
				
				</div>
			</div>
		</div>
		

			
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-autocolors"></script>
<script src="<c:url value='/js/chart.js'/>"></script>
			
			
<script>
  var ctx = document.getElementById('myChart_temp');
  var labels = getRecentTimeLabels().reverse();
  // Chart.js에 사용될 데이터 예시
  var data = {
      labels: labels.reverse(), // 최신 데이터가 뒤로 가게 하기
      datasets: [{
          label: '온도',
          data: [/* 데이터 배열 */],
          borderColor: '#FF6384',
          backgroundColor: '#FFB1C1',
          borderWidth: 1
      }]
  };
//Chart.js 생성
  var ctx = document.getElementById('myChart_temp');
  var myChart = new Chart(ctx, {
      type: 'line', // 또는 다른 차트 유형
      data: data,
      options: {
          responsive: true,
          scales: {
              y: {
                  beginAtZero: true
              }
          }
      }
  });
  var ctx = document.getElementById('myChart_hum');
  var labels = getRecentTimeLabels().reverse();
  // Chart.js에 사용될 데이터 예시
  var data = {
      labels: labels.reverse(), // 최신 데이터가 뒤로 가게 하기
      datasets: [{
          label: '습도',
          data: [/* 데이터 배열 */],
          borderColor: '#36A2EB',
          backgroundColor: '#9BD0F5',
          borderWidth: 1
      }]
  };
//Chart.js 생성
  var ctx = document.getElementById('myChart_hum');
  var myChart = new Chart(ctx, {
      type: 'line', // 또는 다른 차트 유형
      data: data,
      options: {
          responsive: true,
          scales: {
              y: {
                  beginAtZero: true
              }
          }
      }
  });
  
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