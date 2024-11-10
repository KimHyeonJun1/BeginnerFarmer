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

<form method="post" action="device_user_conn">
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
         <option value="${p.mac_address}" data-id="${p.plantid_log }">${p.device_numb}</option>
         </c:forEach>
      </select>
    </div>
    </td>
</tr>
<tr><th>작물</th>
    <td><div class="row">
    <div class="col-auto">
   <div class="col-auto d-flex align-items-center flex-column">
      <select class="form-select w-px250 mt-3"  name="plant_id" id="select-plant">
         <option value="-1">작물선택</option>

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
    <button type="button" class="btn btn-outline-primary px-4" onclick="location='info'">뒤로가기</button>
</div>

</div>



<script>

$("#select-plant").on("change", function(){
	if ($(`#select-plant`).val() == -1   ) return
	if($("#select-mac_address option:selected").data("id") == 0 ){
		$("#btn-save").removeClass("d-none")
		$("#btn-no").addClass("d-none")
		
	}else{
		$("#btn-no").removeClass("d-none")
		$("#btn-save").addClass("d-none")
		
	}
	
})
$("#select-mac_address").on("change", function(){ 
	var plant_id = $("#select-mac_address option:selected").data("id")
	 plant_id = plant_id == "" ? -1 : plant_id
// 	console.log(plant_id)
// 			 console.log( $("#select-plant option:eq(0)").nextAll($("#select-plant option")).html())
			 $(`#select-plant`).empty()
		$.ajax({
			url: "device-plant",
			data: {mac_address:$("#select-mac_address").val(), plant_id:plant_id}
			
		})
			.done(function(response){
				console.log(response)
				var option = `<option value="-1">작물선택</option>`;
				for( var item of response ){
				option += ` <option value="\${item.plant_id}">\${item.plant_name}</option>`
					
				}
				$(`#select-plant`).append( option)
			})
		
	$(`#select-plant option[value=\${plant_id}]`).prop("selected", true)
})

$("#btn-save").on("click", function(){
	if($(`#select-plant`).val()==-1)
		alert("작물을 선택하세요")
		else{
   $("form").submit();
			
		}
})

$("#btn-no").on("click", function(){	
	if(confirm("정말 해제하시겠습니까?"))
$("form").attr("action", "device-disconn_user").submit();
// })


 // 서버에 해제 상태를 알리는 Ajax 요청
    $.ajax({
        type: "POST",
        url: "/farm/log/removeSelectedPlant",
        data: { mac_address: $("#select-mac_address").val() },  // 필요한 데이터 추가
        success: function(response) {
            console.log("서버에서 연결 해제 완료");
        },
        error: function() {
            console.error("서버에서 연결 해제 실패");
        }
    });

    // 폼 제출 (해제 처리로 전송)
//     $("form").attr("action", "device-disconn_user").submit();
});



$("#btn-cancel").on("click", function(){
   location='info';
})

</script>
</body>
</html>