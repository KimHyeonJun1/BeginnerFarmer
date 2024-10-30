<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>관찰일지 목록</title>
</head>
<body>
    <h3 class="my-4">관찰일지</h3>

    <div class="d-flex mb-2 justify-content-between">
        <form method="post" action="list">
            <div class="input-group">
                <select name="searchType" class="form-select w-px100">
                    <option value="">전체</option>
                    <option value="title" <c:if test="${page.searchType eq 'title'}">selected</c:if>>제목</option>
                    <option value="content" <c:if test="${page.searchType eq 'content'}">selected</c:if>>내용</option>
                </select>
                <input type="text" name="keyword" value="${page.keyword}" class="form-control w-px300" placeholder="검색어를 입력하세요">
                <button class="btn btn-success" type="submit"><i class="fa-solid fa-magnifying-glass" style="color: #ffffff;"></i></button>
            </div>
        </form>
        
        <button class="btn btn-success" onclick="location='register'">글쓰기</button>
        
     </div>

    <table class="table tb-list">
        <colgroup>
            <col width="100px">
            <col width="">
            <col width="160px">
        </colgroup>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성일자</th>
        </tr>

        <c:if test="${empty diaryList}">
            <tr><td colspan="3" class="text-center">관찰일지가 없습니다.</td></tr>
        </c:if>

        <c:forEach items="${diaryList}" var="vo">
            <tr>
                <td>${vo.no}</td>
                <td><a class="text-link" href="info?id=${vo.diary_id}">${vo.diary_title}</a></td>
                <td>${vo.diary_writedate}</td>
            </tr>
        </c:forEach>
    </table>

    <!-- 페이지 네비게이션 (추후 구현 필요) -->
    <%-- <jsp:include page="/WEB-INF/views/include/page.jsp"></jsp:include> --%>

</body>
</html>
