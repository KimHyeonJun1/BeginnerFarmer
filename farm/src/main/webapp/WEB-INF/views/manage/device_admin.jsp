<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3 class="my-2">제품등록</h3>

<div class="box">

<form method="post" action="device">
<table class="table tb-row">
<colgroup>
    <col width="200px">
    <col>
</colgroup>
<tr><th>시리얼 넘버</th>
    <td>
    <div class="col-auto">
    <select class="form-select w-px250 mt-3"  name="mac_address" id="select-mac_address">
   
   
    <c:if test="${empty deviceList }">
		<option>연결 가능한 제품이 없습니다.</option>
    </c:if>
    	<option value="">제품선택</option>
         <c:forEach items="${deviceList}" var="p">
         <option value="${p.mac_address}" data-id="${p.userid_log }">${p.device_numb}</option>
         </c:forEach>
      </select>
    </div>
    </td>
</tr>
<tr><th>유저</th>
    <td><div class="row">
    <div class="col-auto">
   <div class="col-auto d-flex align-items-center flex-column">
      <select class="form-select w-px250 mt-3"  name="userid" id="select-userid">
         <option value="-1">사용자선택</option>
         <c:forEach items="${memberList}" var="p">
         <option value="${p.userid}">${p.name}</option>
         </c:forEach>
      </select>
        </div>
   </div>
    </div>
    </td>
</tr>

</table>
</form>
<div class="btn-toolbar justify-content-center gap-2">
    <button type="submit" class="btn btn-primary px-4 d-none" id="btn-save">연결</button>
    <button type="submit" class="btn btn-primary px-4 d-none" id="btn-no">해제</button>
    <button type="button" class="btn btn-outline-primary px-4" onclick="location='list'">취소</button>
</div>

</div>



<script>

$("#select-mac_address").on("change", function(){ 
	var userid = $("#select-mac_address option:selected").data("id")
	 userid = userid == "" ? -1 : userid
	console.log(userid)
	if(userid== -1 ){
		$("#btn-save").removeClass("d-none")
		$("#btn-no").addClass("d-none")
		$(`#select-userid option`).removeClass("d-none")
		
	}else{
		$("#btn-no").removeClass("d-none")
		$("#btn-save").addClass("d-none")
		
		$(`#select-userid option`).addClass("d-none")
		$(`#select-userid option[value=\${userid}]`).removeClass("d-none")
	}
		
		
		
	$(`#select-userid option[value=\${userid}]`).prop("selected", true)
})

$("#btn-save").on("click", function(){
	if($(`#select-userid`).val()==-1)
		alert("사용자를 선택하세요")
		else{
   $("form").submit();
			
		}
})

$("#btn-no").on("click", function(){
	
	
   $("form").attr("action", "device-disconn").submit();
})

$("#btn-cancel").on("click", function(){
   location='info';
})

</script>
</body>
</html>