<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3 class="my-4">등록화면</h3>
<div class="box">
<div class="row mb-3 justify-content-center">
   <div class="col-auto d-flex align-items-center flex-column">
      <label class="ml-5 fs-4 ">나의 작물을 선택하세요</label>
      <form method="post" action="list">
      <select class="form-select w-px350 mt-3"  name="plant_id" onchange="submit()">
         <option value="-1">선택</option>
         <c:forEach items="${plant}" var="p">
         <option <c:if test="${plant_id}">selected</c:if> value="${plant_id}">${plant_name}</option>
         </c:forEach>
      </select>
      </form>
   </div>
</div>
<div class="btn-toolbar gap-2 justify-content-center mt-5">
   <button class="btn btn-success px-4" id="btn-save">저장</button>
   <button class="btn btn-outline-success px-4" id="btn-cancel">취소</button>
</div>
</div>
</body>
</html>