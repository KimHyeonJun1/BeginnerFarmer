<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
                            	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>초보농부 ${title}</title>
<style>
/* 	동영상 */

 /* 부모 컨테이너 스타일 */
        .video-grid {
            display: grid;
            grid-template-columns: repeat(6, 1fr); 
            gap: 25px; /* 각 동영상 간 간격 */
            margin: auto;
            padding: 30px;
        }
     /* 각 동영상 카드 스타일 */
    .video-item {
        background-color: #f9f9f9;
        border-radius: 8px;
        overflow: hidden;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        text-align: center;
    }

    /* 동영상 썸네일 스타일 */
    .video-thumbnail {
        width: 100%;
        height: 150px; /* 썸네일 높이 */
        background-color: #ddd; /* 썸네일 없을 때 기본 배경 */
        display: flex;
        justify-content: center;
        align-items: center;
        overflow: hidden;
    }

    .video-thumbnail img {
        width: 100%;
        height: 100%;
        object-fit: contain; /* 이미지 비율을 유지하며 썸네일 크기에 맞춤 */
    }

    /* 동영상 제목 스타일 */
    .video-title {
        padding: 10px;
        font-size: 1rem;
        color: #333;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    
       .container_2 {
        display: grid;
        grid-template-columns: repeat(4, 1fr); /* 4개의 그리드로 나누기 */
        gap: 30px; /* 그리드 간 간격 */
        border: 3px solid #198754; /* 전체 테두리 실선 */
        border-radius: 20px;
        padding: 30px;
    }
       
/*------------------------------------------------------------------------------- */

/* 작물 난이도 */
  /* 전체 그리드 설정 */
        .container_1 {
            display: grid;
            grid-template-columns: repeat(4, 1fr); /* 4개의 그리드로 나누기 */
            grid-gap: 10px; /* 그리드 간 간격 */
            border: 3px solid #198754; /* 전체 테두리 실선 */
            border-radius: 20px;
        }

        /* 각 그리드 설정 */
        .grid-item {
            margin: 30px;
            height: 200px; /* 높이 설정 */
        }

        /* 첫 번째 그리드 - 작은 그리드 박스 */
        .grid-item-1 {
            display: grid;
            margin: 30px;
            place-items: center; /* 텍스트 중앙 정렬 */
        }
        
        /* 이미지 스타일 */
		.grid-item-1 img {
		    width: 200px; /* 이미지 너비 */
		    height: auto; /* 이미지 비율 유지 */
		    cursor: pointer; /* 마우스 커서가 이미지 위로 오면 손 모양 */
		}


        /* 두 번째, 세 번째, 네 번째 그리드 - 텍스트 및 항목 배치 */
        .grid-item-2, .grid-item-3, .grid-item-4 {
            display: grid;
            margin: 30px;
            grid-template-rows: 1fr 2fr; /* 두 줄로 나누기 */
            height: 70%; /* 그리드 항목의 높이를 100%로 설정 */
        }

        .text-top {
            text-align: center;
            font-size: 18px;
            font-weight: bold;
        }

			.items {
					    display: flex; /* 항목들을 가로로 배치 */
					    flex-wrap: wrap; /* 항목들이 넘칠 경우 줄 바꿈 */
					    gap: 10px; /* 버튼 간 간격 */
					    justify-content: center; /* 수평 중앙 정렬 */
    					align-items: center; /* 수직 중앙 정렬 */
    					margin-top: 10px;
					    height: 50%; /* 부모 그리드 항목에 맞게 크기 설정 */
					}
					
			.crop-btn {
					    flex: 0 0 45%; /* 버튼이 45%의 너비를 가지게 설정, 두 개씩 가로로 배치 */
					    padding: 10px 20px; /* 버튼의 크기 */
					    background-color: #198754; /* 버튼 배경색 */
					    color: white; /* 글자색 */
					    border: none; /* 테두리 제거 */
					    border-radius: 5px; /* 모서리 둥글게 */
					    cursor: pointer; /* 마우스 커서가 버튼 위로 오면 손 모양 */
					    font-size: 16px; /* 글자 크기 */
					    transition: background-color 0.3s ease; /* 마우스 오버 시 배경색 변화 효과 */
						}

			.crop-btn:hover {
			    background-color: #155d41; /* 마우스 오버 시 배경색 변화 */
			}
		
			.rowgroup2 {
		    position: relative;
		    background-color: #f3fbf8;
		    padding-bottom: 80px !important;
		    }
		    
		    .center-content {
		    display: flex;
		    justify-content: center; /* 가로 중앙 정렬 */
		    align-items: center; /* 세로 중앙 정렬 */
		    text-align: center; /* 텍스트 가운데 정렬 */
		    width: 100%; /* 가로로 부모 요소의 전체를 채우기 */
		    height: 100%; /* 부모 요소에 맞게 높이 설정 */
		}

}

</style>
</head>
<body>
        
 <div id="page-content-wrapper">
   <div class="container-fluid">
 
<!-- 메인이미지 Session   -->
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

    <ol class="carousel-indicators"></ol>

  </div>

</section>

<!-- 농업기술 동영상 Section     -->
 <section class="rowgroup2 ">
	<div class="center-content">
        <h1>영상으로 보는 농업기술</h1>
    </div>
	<div class="video-grid mt-5">
		<c:forEach items="${ videoList.items.item }" var="item">
		    
		    <div class="video-item">
	            <div class="video-thumbnail"> <a target="_blank" href="${ item.videoLink }"><img alt="" src="${item.videoImg}"></a>  </div>
	            <div class="video-title">${item.videoTitle}</div>
	        </div>
	        
		</c:forEach>
	</div>
</section>
    
<!-- 난이도별 작물 추천 Section -->
<section> 
	<div class="mt-5">
	    
		<div class="container_1">
	        <!-- 첫 번째 그리드: 작물 추천 -->
	        <div class="grid-item grid-item-1">
		        <img src="<c:url value='/img/bubble.png'/>" alt="작물추천 말풍선">
	        </div>
	        
	        <!-- 두 번째 그리드: 초보 및 작물 목록 -->
	        <div class="grid-item grid-item-2 ">
	            <div class="text-top">초보</div>
	            <div class="items ">
	            	<button class="crop-btn">상추</button>
			        <button class="crop-btn">깻잎</button>
			        <button class="crop-btn">부추</button>
			        <button class="crop-btn">시금치</button>
	                
	            </div>
	        </div>
	
	        <!-- 세 번째 그리드: 초보 및 작물 목록 -->
	        <div class="grid-item grid-item-3">
	            <div class="text-top">중수</div>
	            <div class="items">
	            	<button class="crop-btn">배추</button>
			        <button class="crop-btn">케일</button>
			        <button class="crop-btn">파</button>
			        <button class="crop-btn">방울토마토</button>
	            </div>
	        </div>
	
	        <!-- 네 번째 그리드: 초보 및 작물 목록 -->
	        <div class="grid-item grid-item-4">
	            <div class="text-top">고수</div>
	            <div class="items">
	            	<button class="crop-btn">고추</button>
			        <button class="crop-btn">오이</button>
			        <button class="crop-btn">딸기</button>
			        <button class="crop-btn">가지</button>
	            </div>
	        </div>
	    </div>    
	</div>
 </section>



	</div>
	</div>
	</body>
</html>