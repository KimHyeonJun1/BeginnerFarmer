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
	grid-template-columns: 3fr 3fr 7fr;
/* 	grid-template-columns: 4fr 2fr 7fr; */
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
 
 .result-message {
/*         font-size: 16px; /* 원하는 폰트 크기로 조정 */ */
        color: #333; /* 원하는 글자 색상으로 조정 */
    }
.box4{
	border-radius: 6px;
	display: flex;
	width: 310px;
	height: 100px;	
	border: 2px solid black;
	flex-direction: row; /* 세로 방향 정렬 */
    justify-content: center; /* 수직 중앙 정렬 */
    align-items: center; /* 수평 중앙 정렬 */
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
		
		<div class="item text-center"><h4 class="mb-3">현재 상태</h4>
			<div class="d-flex">
			<div class="box4">
   				 <c:if test="${not empty conditionMessage}">
      				  <h3 class="mt-2 result-message">${conditionMessage}</h3>
   				 </c:if>
			</div>
			</div>
			
		</div>
		<div class="item">
			<div class="d-flex">
				<div class="box1 gap-2">현재온도 ${temp.temperature}°C<i class="fa-solid fa-temperature-half fs-4" style="color:#de1717;"></i></div>
				<div class="box1 gap-2">적정온도 ${vo.standard_temp}°C<i class="fa-solid fa-temperature-half fs-4" style="color:#de1717;"></i></div>
				<button class="btn box1 gap-2">온습도 낮추기  <img src="<c:url value='/img/switch-on.png'/>" alt="on" ></button>
			</div>
			<div class="d-flex">
				<div class="box1 gap-2">현재습도 ${temp.humid}%<i class="fa-solid fa-droplet fs-4" style="color:#74C0FC;"></i> </div>
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
  						<canvas id="myChart_temp" ></canvas>
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

 
//온도 조회
$(document).ready(function(){
	$.ajax({
		url: "temp"
	}).done(function(response){
		console.log(response)
		var info = {};
		info.labels = [], info.data = {}
		info.data.temperature = []
		info.data.humid = []
		info.data.bright = []
		info.data.moisture = []
		
		
		for(var item of response){
			info.labels.push(item.time_log)//['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange', 'Black']
			info.data.temperature.push( item.temperature) // [1, 2, 3, 4, 5, 6, 7]
			info.data.humid.push( item.humid) // [1, 2, 3, 4, 5, 6, 7]
			info.data.bright.push( item.bright) // [1, 2, 3, 4, 5, 6, 7]
			info.data.moisture.push( item.moisture) // [1, 2, 3, 4, 5, 6, 7]
		}
		info.labels.reverse()
		info.data.temperature.reverse()
		info.data.humid.reverse()
		info.data.bright.reverse()
		info.data.moisture.reverse()
		console.log(info)
		lineChart(info); //선차트 그리기
		
	})
})
function lineChart(info){
	new Chart( $("#myChart_temp"), {
		type: 'line',
		data: {
			labels: info.labels,
			datasets: [{
				label: '온도',
				data : info.data.temperature,
				borderColor: '#FF6384',
				BackgroundColor: '#ff0000',
// 				pointBackgroundColor: '#ff0000',
				pointRadius: 5,
				tension: 0.1, //꺾은선 형태
			}]
		},
		options:  setOptions()
	})
	new Chart( $("#myChart_hum"), {
		type: 'line',
		data: {
			labels: info.labels,
			datasets: [{
				label: '대기습도',
				data : info.data.humid,
				borderColor: "#36A2EB",
				backgroundColor: "#9BD0F5",
// 				pointBackgroundColor: "#9BD0F5",
				pointRadius: 5,
				tension: 0.1, //꺾은선 형태
			}]
		},
		options:  setOptions()
	})
	new Chart( $("#myChart_moisture"), {
		type: 'line',
		data: {
			labels: info.labels,
			datasets: [{
				label: '토양습도',
				data : info.data.moisture,
				borderColor: 'rgb(75, 192, 192)',
				backgroundColor: 'rgb(75, 192, 192)',
// 				pointBackgroundColor: 'rgb(75, 192, 192)',
				pointRadius: 5,
				tension: 0.1, //꺾은선 형태
			}]
		},
		options:  setOptions()
	})
	new Chart( $("#myChart_bright"), {
		type: 'line',
		data: {
			labels: info.labels,
			datasets: [{
				label: '조도',
				data : info.data.bright,
				borderColor: "rgb(255, 159, 64)",
				BackgroundColor: "rgb(255, 159, 64)",
// 				pointBackgroundColor: "rgb(255, 159, 64)",
				pointRadius: 5,
				tension: 0.1, //꺾은선 형태
			}]
		},
		options: {
			scales: {
				  y: {
				       beginAtZero: true, 
						  min: Math.min(...info.data.bright)-50,
							 grace:'20%',
							  ticks: {
							   stepSize: 10 // 10 단위로 눈금 설정
								   }
		
								 
							 },
					}
		}
		
	})
	console.log(Math.min(...info.data.bright))
}

  
  //서버에 plant정보 저장하기
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