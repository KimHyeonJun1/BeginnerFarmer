<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>동영상 목록</title>
    <style>
        /* 부모 컨테이너 스타일 */
        .video-grid {
            display: grid;
            grid-template-columns: repeat(4, 1fr); /* 가로로 4개 */
            gap: 25px; /* 각 동영상 간 간격 */
            max-width: 1200px;
            margin: auto;
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
    		overflow: hidden; /* 필요 시 여백을 자르지 않으려면 삭제 가능 */
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
        }
    </style>
</head>
<body>
	<h3 class="my-5 mb-5" >농업기술동영상</h3>
	
	<c:if test="${videoList.totalCount == 0 }"> 
		<table class="table tb-list">
		    <tr><th></th></tr>
		    <tr><th class="text-center">농업기술동영상</th></tr> 
		</table>
	</c:if>
	
		
	<div class="d-flex video-grid">
		<form method="post" action="list">
				<div class=" mb-2 justify-content-between">
					<div class="col-auto video-top  gap-2"></div>
						<div class="col-auto d-flex gap-2" >
							<select class="form-select w-px300" name="mainCategory" id="mainCategory">
								<option value="">메인카테고리 선택</option>
								<c:forEach items="${mainCategoryList.items.item }" var="vo">
								<option <c:if test="${mainCategory == vo.categoryCode  }">selected</c:if>  value="${vo.categoryCode}">${vo.categoryNm }</option>
								</c:forEach>
							</select>
				
							<c:if test="${ not empty subCategoryList }">
								<select class="form-select w-px300" name="subCategory" id="subCategory">
									<option value="">서브카테고리 선택</option>
									<c:forEach items="${subCategoryList.items.item }" var="vo">
									<option <c:if test="${subCategory == vo.categoryCode  }">selected</c:if>  value="${vo.categoryCode}">${vo.categoryNm }</option>
									</c:forEach>
								</select>
							</c:if>
						</div>
				</div>
		
	            <div class="d-flex input-group mb-3">
	                <input type="text" name="keyword" value="${page.keyword}" class="form-control w-px600" placeholder="검색어를 입력하세요">
	                <button class="btn btn-success" type="submit"><i class="fa-solid fa-magnifying-glass" style="color: #ffffff;"></i></button>
	            </div>
        	
    	</form>
	</div>


    <div class="video-grid mt-5">
	<c:forEach items="${ videoList.items.item }" var="item">
        <!-- 동영상 아이템  -->
        <div class="video-item">
            <div class="video-thumbnail"> <a target="_blank" href="${ item.videoLink }"><img alt="" src="${item.videoImg}"></a>  </div>
            <div class="video-title">${item.videoTitle}</div>
        </div>
    </c:forEach>
    </div>
    
<jsp:include page="/WEB-INF/views/include/subPage.jsp"/>


<script>

$("#mainCategory").on("change",function(){
	$("form").submit()
})

$("#subCategory").on("change",function(){
	$("form").submit()
})

$(function(){
	
//     var listSize = ${page.listSize != null ? page.listSize : 12};
//     var pageNo = ${page.pageNo != null ? page.pageNo : 1};

//     // 페이지 로드 시 select 옵션에서 선택된 값으로 설정
//     $("#listSize").val(listSize).prop("selected", true);

    // 페이지 링크 클릭 시 listSize와 pageNo 값을 반영하여 이동
    $("a.page-link").on("click", function(){
        // 클릭한 페이지 번호 가져오기
        var selectedPageNo = $(this).data("page");
        location.href = "<c:url value='/video/list?pageNo='/>" + selectedPageNo ;
    });

    // listSize 값이 변경될 때 페이지 새로고침
//     $("#listSize").on("change", function() {
//         location.href = "<c:url value='/video/list?pageNo=1&listSize='/>" + $(this).val();
//     });
});
</script>




</body>
</html>
