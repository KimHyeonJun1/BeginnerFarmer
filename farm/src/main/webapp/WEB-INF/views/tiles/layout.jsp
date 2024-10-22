<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.user" var="auth_user"/>
</sec:authorize>


<c:choose>
	<c:when test="${category eq 'login'}"> <c:set var="title" value="- 로그인" /> </c:when>
	<c:when test="${category eq 'find'}"> <c:set var="title" value="- 비밀번호" /> </c:when>
	<c:when test="${category eq 'join'}"> <c:set var="title" value="- 회원가입" /> </c:when>
	<c:when test="${category eq 'ho'}"> <c:set var="title" value="- 홈"/>  </c:when>
	<c:when test="${category eq 'gu'}"> <c:set var="title" value="- 작물가이드"/>  </c:when>
	<c:when test="${category eq 'in'}"> <c:set var="title" value="- 주간농사정보"/>  </c:when>
	<c:when test="${category eq 'vi'}"> <c:set var="title" value="- 농업기술동영상"/>  </c:when>
	<c:when test="${category eq 'bo'}"> <c:set var="title" value="- 게시판"/>  </c:when>
	<c:when test="${category eq 'no'}"> <c:set var="title" value="- 공지사항"/>  </c:when>
	<c:when test="${category eq 'ma'}"> <c:set var="title" value="- 작물관리"/>  </c:when>
	<c:when test="${category eq 'mo'}"> <c:set var="title" value="- 실시간모니터링"/>  </c:when>
	<c:when test="${category eq 'te'}"> <c:set var="title" value="- 온도/습도/조도"/>  </c:when>
	<c:when test="${category eq 'wa'}"> <c:set var="title" value="- 급수관리"/>  </c:when>
	<c:when test="${category eq 'di'}"> <c:set var="title" value="- 관찰일지"/>  </c:when>
</c:choose>


<!DOCTYPE html>
<html>
<!-- <html lang="en"> -->

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <title>초보농부 ${title}</title>
  <meta name="description" content="">
  <meta name="keywords" content="">

  <!-- Favicons -->
  <link href="<c:url value='/img/2222.png'/>" rel="icon">

  <!-- Fonts -->
  <link href="https://fonts.googleapis.com" rel="preconnect">
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;1,300;1,400;1,500;1,600;1,700;1,800&family=Marcellus:wght@400&display=swap" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="<c:url value='/vendor/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">
  <link href="<c:url value='/vendor/bootstrap-icons/bootstrap-icons.css'/>" rel="stylesheet">
  <link href="<c:url value='/vendor/aos/aos.css" rel="stylesheet'/>">
  <link href="<c:url value='/vendor/swiper/swiper-bundle.min.css'/>" rel="stylesheet">
  <link href="<c:url value='/vendor/glightbox/css/glightbox.min.css'/>" rel="stylesheet">
  
<!--   제이쿼리 선언 -->
	<script src="https://code.jquery.com/ui/1.13.3/jquery-ui.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <!-- Main CSS File -->
  <link href="<c:url value='/css/main.css'/>" rel="stylesheet">
  <link href="<c:url value='/css/common.css'/>" rel="stylesheet">

  <!-- =======================================================
  * Template Name: AgriCulture
  * Template URL: https://bootstrapmade.com/agriculture-bootstrap-website-template/
  * Updated: Aug 07 2024 with Bootstrap v5.3.3
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body class="index-page">

  <header id="header" class="header d-flex align-items-center position-relative">
    <div class=" container-fluid container-xl position-relative d-flex align-items-center justify-content-between">

      <a href="/farm" class="logo d-flex align-items-center">
     
        <!-- Uncomment the line below if you also wish to use an image logo -->
        <img src="<c:url value='/img/logogo1.png'/>" alt="AgriCulture">
        <!-- <h1 class="sitename">AgriCulture</h1>  -->
      </a>
      <nav id="navmenu" class="navmenu">
        <ul>
          <li><a href="/farm" class="active">홈</a></li>
          <li class="dropdown"><a href="/farm/guide/list"><span>농사정보</span><i class="bi bi-chevron-down toggle-dropdown"></i></a>
            <ul>
              <li><a href="<c:url value='/guide/list'/>">작물가이드</a></li>
              <li class="dropdown"><a href="#"><span>농사Tip</span> <i class="bi bi-chevron-down toggle-dropdown"></i></a>
                <ul>
                  <li><a href="#">주간농사정보</a></li>
                  <li><a href="#">농업기술동영상</a></li>
                </ul>
              </li>
            </ul>
          </li>
          <li class="dropdown"><a href="#"><span>소통공간</span><i class="bi bi-chevron-down toggle-dropdown"></i></a>
            <ul>
              <li><a href="#">게시판</a></li>
              <li><a href="#">공지사항</a></li>
            </ul>
          </li>
          <li class="dropdown"><a href="/farm/manager/list"><span>나의농장</span><i class="bi bi-chevron-down toggle-dropdown"></i></a>
            <ul>
                <li><a href="<c:url value='/manager/list'/>">작물관리</a></li>
				<li><a href="<c:url value='/monitor/list'/>">실시간모니터링</a></li>
				<li><a href="<c:url value='/environment/temperature'/>">온도/습도/조도</a></li>
				<li><a href="<c:url value='/water-management'/>">급수관리</a></li>
				<li><a href="<c:url value='/observation-diary'/>">관찰일지</a></li>
            </ul>
          </li>
        </ul>
        <i class="mobile-nav-toggle d-xl-none bi bi-list"></i>
      </nav>
      
        <nav class="d-flex navmenu">
       	 <ul> 
       	 	<c:if test="${empty auth_user  }"  >
	             <li class="dropdown"><a href="<c:url value='/member/login' />">로그인</a></li>
	             <li class="dropdown"><a href="#">회원가입</a></li>
       	 	</c:if>
       	 	
       	 	<c:if test="${not empty auth_user }">

<!-- 			<a><i class="fa-regular fa-bell" style="color: #2bb74c;"></i></a> -->

	             <li class="dropdown"><a href="<c:url value='/member/logout' />">로그아웃</a></li>
                      	
                 <li class="nav-item dropdown">
                     <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" 
                      	data-bs-toggle="dropdown" aria-haspopup="true" 
                      	aria-expanded="false">${auth_user.name }</a>
                     <div class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
<%--                      	<c:if test="${empty auth_user.social }"> --%>
<%--                          <a class="dropdown-item" href="#!">아이디: ${auth_user.userid}</a> --%>
                         <a class="dropdown-item" href="<c:url value=''/>">My Page</a>
                         <a class="dropdown-item" href="<c:url value=''/>">비밀번호 변경</a>
<!--                          <div class="dropdown-divider"></div> -->
<%--                          </c:if> --%>
<%--                          <a class="dropdown-item" href="<c:url value='/member/logout'/>">로그아웃</a> --%>
                     </div>
                 </li>

       	 	</c:if>
      	 </ul>
        </nav>
    </div>
  </header>
  
 <!-- Page Title -->
<c:if test="${category != 'home'}">
 	<div class="page-title dark-background" data-aos="fade" style="background-image: url('<c:url value="/img/page-title-bg.jpg"/>');">
  		<div class="container position-relative">
  		</div>
	</div>
</c:if>


<div class="d-flex" id="wrapper">
    <!-- Sidebar -->
    <c:if test="${ category != 'home' }">
        <div class="border-end bg-white" id="sidebar-wrapper" style="width: 200px;">
            <div class="sidebar-heading border-bottom bg-light"></div>
            <div class="list-group list-group-flush">
                <!-- 농사정보 -->
                <a class="${ category eq 'gu' ? 'active' : ''} list-group-item list-group-item-action list-group-item-light p-3 ps-4 dropdown-toggle" href="#">농사정보</a>
                <ul class="dropdown-menu" style="display: ${category eq 'gu' ? 'block' : 'none'};">
                    <li><a class="${ category eq 'gu' ? 'active' : ''} list-group-item-action" href="<c:url value='/guide/list'/>">작물가이드</a></li>
                    <li><a href="#">농사Tip</a></li>
                </ul>

                <!-- 소통공간 -->
                <a class="${ category eq 'bo' ? 'active' : ''} list-group-item list-group-item-action list-group-item-light p-3 ps-4 dropdown-toggle" href="#">소통공간</a>
                <ul class="dropdown-menu" style="display: ${category eq 'bo' ? 'block' : 'none'};">
                    <li><a class="${ category eq 'bo' ? 'active' : ''} list-group-item-action" href="/board/list">게시판</a></li>
                    <li><a href="#">공지사항</a></li>
                </ul>

                <!-- 나의농장 -->
                <a class="${ category eq 'ma' ? 'active' : ''} list-group-item list-group-item-action list-group-item-light p-3 ps-4 dropdown-toggle" href="#">나의농장</a>
                <ul class="dropdown-menu" style="display: ${category eq 'ma' ? 'block' : 'none'};">
                    <li><a class="${ category eq 'ma' ? 'active' : ''} list-group-item-action" href="<c:url value='/manager/list'/>">작물관리</a></li>
                    <li><a href="#">실시간모니터링</a></li>
                    <li><a href="#">온도/습도/조도</a></li>
                    <li><a href="#">급수관리</a></li>
                    <li><a href="#">관찰일지</a></li>
                </ul>
            </div>
        </div>
    </c:if>

    <!-- Main Content -->
    <main id="content" class="main ${ category == 'home' ? 'full-width' : ''}" style="flex-grow: 1;">
        <tiles:insertAttribute name="container"/>
    </main>
</div>


	



<!-- 					<div class="container-fluid my-4"> -->
<%--                 	<tiles:insertAttribute name="container" /> --%>
<!--                 </div> -->
  </body>

  <footer id="footer" class="footer dark-background">

   

    <div class="copyright text-center">
      <div class="container d-flex flex-column flex-lg-row justify-content-center justify-content-lg-between align-items-center">

        <div class="d-flex flex-column align-items-center align-items-lg-start">
          <div>
            © Copyright <strong><span>MyWebsite</span></strong>. All Rights Reserved
          </div>
          <div class="credits">
            <!-- All the links in the footer should remain intact. -->
            <!-- You can delete the links only if you purchased the pro version. -->
            <!-- Licensing information: https://bootstrapmade.com/license/ -->
            <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/herobiz-bootstrap-business-template/ -->
            Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
          </div>
        </div>

        <div class="social-links order-first order-lg-last mb-3 mb-lg-0">
          <a href=""><i class="bi bi-twitter-x"></i></a>
          <a href=""><i class="bi bi-facebook"></i></a>
          <a href=""><i class="bi bi-instagram"></i></a>
          <a href=""><i class="bi bi-linkedin"></i></a>
        </div>

      </div>
    </div>
    
				
	
  </footer>

  <!-- Scroll Top -->
  <a href="#" id="scroll-top" class="scroll-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Preloader -->
  <div id="preloader"></div>

  <!-- Vendor JS Files -->
  <script src="<c:url value='/vendor/bootstrap/js/bootstrap.bundle.min.js'/>"></script>
  <script src="<c:url value='/vendor/php-email-form/validate.js'/>"></script>
  <script src="<c:url value='/vendor/aos/aos.js'/>"></script>
  <script src="<c:url value='/vendor/swiper/swiper-bundle.min.js'/>"></script>
  <script src="<c:url value='/vendor/glightbox/js/glightbox.min.js'/>"></script>

  <!-- Main JS File -->
  <script src="<c:url value='/js/main.js'/>"></script>



<script>


</script>

</body>

</html>