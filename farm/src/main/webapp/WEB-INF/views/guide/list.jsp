<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작물가이드</title>
</head>
<body>
<div class="container text-center mt-5">
  <div class="row">
    <c:forEach items="${ list }" var="vo">
      <div class="col-md-4 mb-4">
        <figure class="border border-3 border-success">
          <a class="text-link" href="info?plant_id=${ vo.plant_id }">
            <img src="<c:url value='/img/상추.jpg'/>" alt="상추" width="300" height="200">
            <span class="mt-4 fs-5 d-block">${ vo.plant_name }</span>
          </a>
        </figure>
      </div>
    </c:forEach>
  </div>
</div>
</body>
</html>