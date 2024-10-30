<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<li class="dropdown"><a href="/farm/manage/list"><span>나의농장</span><i class="bi bi-chevron-down toggle-dropdown"></i></a>
     <ul>
        <li><a href="<c:url value='/manage/list'/>">작물관리</a></li>
		<li><a href="<c:url value='/log/monitor'/>">실시간모니터링</a></li>
		<li><a href="<c:url value='/log/temperature'/>">온도/습도/조도</a></li>
		<li><a href="<c:url value='/log/water_management'/>">급수관리</a></li>
		<li><a href="<c:url value='/diary/list'/>">관찰일지</a></li>
     </ul>
</li>


<li class="dropdown"><a href="/farm/member/user/myPage"><span>${auth_user.name }</span><i class="bi bi-chevron-down toggle-dropdown"></i></a>
	<ul>
        <li><a href="<c:url value='/member/user/myPage'/>">내 정보</a></li>
		<li><a href="<c:url value='/member/user/changePassword'/>">비밀번호 변경</a></li>
     </ul>

</li>

<li class="nav-item dropdown">
     <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" 
      	data-bs-toggle="dropdown" aria-haspopup="true" 
      	aria-expanded="false">${auth_user.name }</a>
     <div class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
         <a class="dropdown-item" href="<c:url value='/member/user/myPage'/>">내 정보</a>
         <a class="dropdown-item" href="<c:url value='/member/user/changePassword'/>">비밀번호 변경</a>
     </div>
</li>








</body>
</html>