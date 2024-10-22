<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<%-- <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> --%>


<%-- <c:choose> --%>
<%--    <c:when test="${category eq 'login'}"> <c:set var="title" value="- 로그인" /> </c:when> --%>
<%--    <c:when test="${category eq 'find'}"> <c:set var="title" value="- 비밀번호" /> </c:when> --%>
<%--    <c:when test="${category eq 'join'}"> <c:set var="title" value="- 회원가입" /> </c:when> --%>
<%-- </c:choose> --%>

<!-- <!DOCTYPE html> -->
<!-- <html> -->
<!-- <head> -->
<!--         <meta charset="utf-8" /> -->
<!--         <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" /> -->
<!--         <meta name="description" content="" /> -->
<!--         <meta name="author" content="" /> -->
<%--         <title>초보농부 ${title}</title> --%>
<!--         Favicon -->
<%--          <link href="<c:url value='/img/2222.png'/>" rel="icon"> --%>
<!--         Core theme CSS (includes Bootstrap) -->
<%--         <link href="<c:url value='/css/main.css' />" rel="stylesheet" /> --%>
<%--         <link href="<c:url value='/css/common.css' />" rel="stylesheet" /> --%>
<!--         <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.3/themes/base/jquery-ui.css"> -->
<!--         <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"> -->
        
<!--         <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script> -->
<!--         <script src="https://code.jquery.com/ui/1.13.3/jquery-ui.js"></script> -->
<!--         <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> -->
<!--        	<script src=" https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/js/all.min.js"></script> -->
<%--         <script src="<c:url value='/js/common.js' />"></script> --%>
        
        
<!--     </head> -->
<!--     <body> -->
<!--         <div class="d-flex index-page" id="wrapper"> -->
           
<!--             Page content wrapper -->
<!--             <div id="page-content-wrapper"> -->
               
<!--                 Page content -->
<!--                 <div class="container-fluid"> -->
<%--                    <tiles:insertAttribute name="container" /> --%>
<!--                 </div> -->
                
<!--             </div> -->
            
<!--         </div> -->
<!--         Bootstrap core JS -->
<!--         <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script> -->
<!--         Core theme JS -->
<%--         <script src="<c:url value='/js/scripts.js' />"></script> --%>
<!--     </body> -->
<!-- </html> -->




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


  <!-- Vendor CSS Files -->
  <link href="<c:url value='/vendor/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">
  <link href="<c:url value='/vendor/bootstrap-icons/bootstrap-icons.css'/>" rel="stylesheet">
  <link href="<c:url value='/vendor/aos/aos.css" rel="stylesheet'/>">
  <link href="<c:url value='/vendor/swiper/swiper-bundle.min.css'/>" rel="stylesheet">
  <link href="<c:url value='/vendor/glightbox/css/glightbox.min.css'/>" rel="stylesheet">

  <!-- Main CSS File -->
  <link href="<c:url value='/css/main.css'/>" rel="stylesheet">


<body class="index-page">

   <main class="main">

     <section class="default_hero ">
      <div class="container">

        <div class="row justify-content-center text-center aos-init aos-animate" data-aos="fade-up" data-aos-delay="100">
          <div class="col-xl-6 col-lg-8">
            <tiles:insertAttribute name="container"/>
          </div>
        </div>
      </div>
    </section>
  </main>


  <!-- Vendor JS Files -->
  <script src="<c:url value='/vendor/bootstrap/js/bootstrap.bundle.min.js'/>"></script>
  <script src="<c:url value='/vendor/php-email-form/validate.js'/>"></script>
  <script src="<c:url value='/vendor/aos/aos.js'/>"></script>
  <script src="<c:url value='/vendor/swiper/swiper-bundle.min.js'/>"></script>
  <script src="<c:url value='/vendor/glightbox/js/glightbox.min.js'/>"></script>


</body>

</html>
