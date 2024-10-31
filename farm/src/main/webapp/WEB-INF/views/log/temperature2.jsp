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

.container > div { 
   padding: 10px; 
   background-color: #fff; 
 } 

.container {
  display: grid;
  grid-template-columns: repeat(3, 6fr);
  grid-auto-rows: 200px;
  grid-gap: 20px;
  justfiy-content: center;
  align-items: center;
}

.container1 {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-auto-rows: 90px;
  grid-gap: 10px;
 
}

.container1 > div { 
   background-color: #fff; 
   border: 2px solid black; 
 } 
.container2 {
padding: 20px; 
  display: grid;
  grid-template-columns: repeat(2, 6fr);
  grid-auto-rows: 300px;
  grid-gap: 20px;
}

.container2 > div { 
   padding: 10px; 
   background-color: #fff; 
   border: 2px solid green; 
 } 


</style>
<body>
<h3 class="my-2">온도/습도/조도</h3>
<div class="container">
	<div>사물</div>
			<div>습도</div>
	<div class="container1">
			<div class="col-md">현재온도</div>
			<div class="col-md">적정습도</div>
			<div class="col-md">온습도 낮추기
			<button>ON</button>
			</div>
			
			<div class="col-md">현재습도</div>
			<div class="col-md">적정습도</div>
			<div class="col-md">그림</div>
	</div>
	</div>
	<div class="container2">
			<div>온도</div>
			<div>습도</div>
</div>
	<div class="container2">
			<div>온도</div>
			<div>습도</div>
 </div>
</body>
</html>