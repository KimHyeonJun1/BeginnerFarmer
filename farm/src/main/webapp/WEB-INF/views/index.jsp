<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>초보농부 ${title}</title>
</head>
<body>
        
 <div id="page-content-wrapper">
   <div class="container-fluid">
    
    <section id="hero" class="hero section dark-background1">

      <div id="hero-carousel" class="carousel slide carousel-fade" data-bs-ride="carousel" data-bs-interval="5000">

        <div class="carousel-item active">
          <img src="<c:url value='/img/hero-1.png'/>" alt="초보농부-1">
        </div>

        <div class="carousel-item">
          <img src="<c:url value='/img/hero-2.png'/>" alt="초보농부-2">
        </div>

        <div class="carousel-item">
          <img src="<c:url value='/img/hero-3.png'/>" alt="초보농부-3">
        </div>

        <a class="carousel-control-prev" href="#hero-carousel" role="button" data-bs-slide="prev">
          <span class="carousel-control-prev-icon bi bi-chevron-left" aria-hidden="true"></span>
        </a>

        <a class="carousel-control-next" href="#hero-carousel" role="button" data-bs-slide="next">
          <span class="carousel-control-next-icon bi bi-chevron-right" aria-hidden="true"></span>
        </a>
        </div>
	</section>
	</div>
	</div>
	</body>
</html>