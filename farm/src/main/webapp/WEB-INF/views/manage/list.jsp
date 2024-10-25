<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.user" var="auth_user"/>
</sec:authorize>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작물관리</title>
</head>
<body>
<h3 class="m-2 pb-5">작물관리</h3>
<div class="box">
      
       <a class="link" href='<c:url value="${empty auth_user ? '/member/login' : '/manage/register'}"/>'>
       <div class="plant-mg my-2">
       		 <img src="<c:url value='/img/clover.png'/>" alt="식물">
        <div class="speech-bubble"> 여기를 클릭!</div>
       </div>
       </a>
			<div class="text-box">
			
			  <h3>작물을 등록하세요</h3>
			</div>        	
    </div>
</body>
<!--  <h3 class="m-2 pb-5">작물관리</h3> -->
<!--     <div class="box"> -->
<!--         <sec:authorize access="isAuthenticated()"> -->
<!--             <a class="link" href="/farm/manage/register"> -->
<!--                 <div class="plant-mg my-2"> -->
<%--                     <img src="<c:url value='/img/clover.png'/>" alt="식물"> --%>
<!--                     <div class="speech-bubble"> 여기를 클릭!</div> -->
<!--                 </div> -->
<!--             </a> -->
<!--             <div class="text-box"> -->
<!--                 <h3>작물을 등록하세요</h3> -->
<!--             </div> -->
<!--         </sec:authorize> -->

<!--         <sec:authorize access="!isAuthenticated()"> -->
<!--             <script> -->
<!-- //             $(document).ready(function() { -->
<!-- //                 window.location.href = "/login"; // 로그인 페이지로 리다이렉트 -->
<!-- //             }); -->
<!--             </script> -->
<!--         </sec:authorize> -->
<!--     </div> -->
<!-- </body> -->
</html>