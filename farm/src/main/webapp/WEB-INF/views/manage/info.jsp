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
.btn1 {
   position: absolute;
   top: 20px;
   right: 20px; 
}
.btn2 {
   position: absolute;
   top: 20px;
   right: 110px; 
}
</style>
<body>
<h3 class="my-4">선택화면</h3>
<div class="box">
<div class="d-flex btn-toolbar gap-2 justify-content-center mt-5">
   <button class="btn btn2 btn-outline-success px-4" onclick="fnDelete()">삭제</button>
   <button class="btn btn1 btn-success px-4" id="btn-insert">추가</button>
</div>

<div class="row mb-3 justify-content-center">
   <div class="col-auto d-flex align-items-center flex-column">
      <label class="ml-5 me-2 fs-4 mb-3 ">관리할 작물을 선택하세요</label>
       
      <select class="form-select w-px350 mt-3"  name="plant_id">
         <option value="0">선택</option>
         <c:forEach items="${plant}" var="p">
         <option  <c:if test="${vo.plant_id eq p.plant_id}">selected</c:if> value="${p.plant_id}">${p.plant_name}</option>
         </c:forEach>
      </select>
     
   </div>
</div>
</div>
<script>
$("#btn-insert").on("click", function(){
// 	  $("form").submit();
	location='register';
})

function fnDelete() {
	if( confirm("[${vo.plant}]를 정말 삭제하시겠습니까?") ){
		location = "delete?userid_log=${vo.userid_log}"
	}
}

</script>


</body>
</html>