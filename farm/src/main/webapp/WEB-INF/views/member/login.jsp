<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#naver { 
	background: url("<c:url value='/img/naver.png'/>") center / contain no-repeat #03c75a
}

#kakao {
	background: url("<c:url value='/img/kakao.png'/>") center/contain no-repeat #FEE500
}

</style>


</head>

<body>
   
   
   <div class="row justify-content-center " >
         <div class="card shadow-lg border-0 rounded-lg mt-5">
            <div class="card-header">
               <h3 class="text-center font-weight-light my-4">
                  <a href="<c:url value='/' />"> <img src="<c:url value='/img/login.png' />" ></a>
               </h3>
            </div>
            <div class="card-body p-5">
               <form method="post" action="farmLogin" autocomplete="off">
                  <div class="form-floating mb-3">
                     <input class="form-control" name="userid" type="text"
                        placeholder="아이디"> <label>아이디</label>
                  </div>
                  <div class="form-floating mb-3">
                     <input class="form-control" name="userpw" type="password"
                        placeholder="비밀번호"> <label>비밀번호</label>
                  </div>
                  <div class="form-check mb-3 d-flex">
					 <label>
					 	<input class="form-check-input " name="remember-me"
							type="checkbox" checked>로그인 상태 유지
					 </label>
				  </div>
                     <button class="btn btn-success form-control py-3">로그인</button>
               </form>
               
               <div class="mt-3 d-flex gap-4">
					<input type="button" class="btn form-control" id="naver"> 
					<input type="button" class="btn form-control" id="kakao">
				</div>
               
            </div>
            <div class="card-footer text-center py-3">
                  <div class="d-flex align-items-center justify-content-between my-2">
                     <a class="small" href="findPassword">비밀번호찾기</a> 
                     <a class="small" href="join">회원가입</a>
               <div class="small">
               </div>
            </div>
         </div>
      </div>
   </div>
</body>

<script>
	$("#naver, #kakao").on("click", function(){
		location = "<c:url value='/oauth2/authorization/' />" + $(this).attr("id")	
	})
</script>



</html>