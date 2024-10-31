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
	display: grid;
	grid-template-columns: 1fr 1fr 3fr;
	grid-template-rows: 200px 200px 500px;
}


</style>
<body>
<h3 class="my-2">온도/습도/조도</h3>
<div class="container">
	<div class="item">A</div>
	<div class="item">B</div>
	<div class="item">C
		<div class="row">
			</div>
		</div>
	<div class="item">D</div>
	<div class="item">E</div>
	<div class="item">F</div>
	<div class="item">G</div>
	
</div>
</body>
</html>