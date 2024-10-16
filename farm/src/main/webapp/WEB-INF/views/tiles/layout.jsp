<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<c:choose>
	<c:when test="${category eq 'ho'}"> <c:set var="title" value="- 홈"/>  </c:when>
	<c:when test="${category eq 'fa'}"> <c:set var="title" value="- 나의농장"/>  </c:when>
	<c:when test="${category eq 'li'}"> <c:set var="title" value="- 모니터링"/>  </c:when>
	<c:when test="${category eq 'ma'}"> <c:set var="title" value="- 온습도 관리"/>  </c:when>
	<c:when test="${category eq 'wa'}"> <c:set var="title" value="- 급수관리"/>  </c:when>
	<c:when test="${category eq 'wt'}"> <c:set var="title" value="- 관찰일지"/>  </c:when>
	<c:when test="${category eq 'login'}"> <c:set var="title" value="- 농사정보"/>  </c:when>
	<c:when test="${category eq 'change'}"> <c:set var="title" value="- 재배 가이드"/>  </c:when>
	<c:when test="${category eq 'my'}"> <c:set var="title" value="- 게시판"/>  </c:when>
	<c:when test="${category eq 'my'}"> <c:set var="title" value="- 공지사항"/>  </c:when>
</c:choose>


<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <title>초보농부</title>
  <meta name="description" content="">
  <meta name="keywords" content="">

  <!-- Favicons -->
  <link href="<c:url value='/img/2.png'/>" rel="icon">
  <link href="<c:url value='/img/apple-touch-icon.png'/>" rel="초보농부">

  <!-- Fonts -->
  <link href="https://fonts.googleapis.com" rel="preconnect">
  <link href="https://fonts.gstatic.com" rel="preconnect" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;1,300;1,400;1,500;1,600;1,700;1,800&family=Marcellus:wght@400&display=swap" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="<c:url value='/vendor/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">
  <link href="<c:url value='/vendor/bootstrap-icons/bootstrap-icons.css'/>" rel="stylesheet">
  <link href="<c:url value='/vendor/aos/aos.css" rel="stylesheet'/>">
  <link href="<c:url value='/vendor/swiper/swiper-bundle.min.css'/>" rel="stylesheet">
  <link href="<c:url value='/vendor/glightbox/css/glightbox.min.css'/>" rel="stylesheet">

  <!-- Main CSS File -->
  <link href="<c:url value='/css/main.css'/>" rel="stylesheet">

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

      <a href="index.html" class="logo d-flex align-items-center">
     
        <!-- Uncomment the line below if you also wish to use an image logo -->
        <img src="<c:url value='/img/logogo1.png'/>" alt="AgriCulture">
        <!-- <h1 class="sitename">AgriCulture</h1>  -->
      </a>

      <nav id="navmenu" class="navmenu">
        <ul>
          <li><a href="index.jsp" class="active">홈</a></li>
          <li class="dropdown"><a href="#"><span>농사정보</span><i class="bi bi-chevron-down toggle-dropdown"></i></a>
            <ul>
              <li><a href="#">작물가이드</a></li>
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
          <li class="dropdown"><a href="#"><span>나의농장</span><i class="bi bi-chevron-down toggle-dropdown"></i></a>
            <ul>
              <li><a href="#">작물관리</a></li>
              <li><a href="#">실시간모니터링</a></li>
              <li><a href="#">온도/습도/조도</a></li>
              <li><a href="#">급수관리</a></li>
              <li><a href="#">관찰일지</a></li>
            </ul>
          </li>
        </ul>
        <i class="mobile-nav-toggle d-xl-none bi bi-list"></i>
        
      </nav>
        <nav class="d-flex navmenu">
       	 <ul>
             <li class="dropdown"><a href="#">로그인</a></li>
             <li class="dropdown"><a href="#">회원가입</a></li>
      	 </ul>
        </nav>
    </div>
  </header>

  <main class="main">
	<tiles:insertAttribute name="container"/>	
  </main>

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

</body>

</html>