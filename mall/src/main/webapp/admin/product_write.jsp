<%@page import="model.category_dto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//세션 정보 가져오기
/* HttpSession sess = request.getSession();
if (sess == null || sess.getAttribute("aname") == null || ((String) sess.getAttribute("aname")).isEmpty()) { //세션 또는 로그인 사용자 정보가 없으면 
%>
 <script type="text/javascript">
     alert("로그인 후에 이용 가능합니다.");
     window.location.href = "./product_list.do"; 
 </script>
<%
}else{ */
	String msg = (String)request.getAttribute("msg");
	ArrayList<category_dto> category_all = (ArrayList<category_dto>) request.getAttribute("category_all");
	String random_six_digit = (String)request.getAttribute("random_six_digit");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상품등록 페이지</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="./css/main.css">
    <link rel="stylesheet" type="text/css" href="./css/product.css?v=5">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
</head>
<%@include file="./top.jsp" %>
<main class="maincss">
<section>
<p>상품 등록 페이지</p>
<form id="frm" method="post" action="./product_write_ok.do" enctype="multipart/form-data">
<input type="hidden" id="dup_chech" value="">
<div class="product_insert">
    <ul>
        <li>카테고리</li>
        <li>
            <select class="product_input1" name="category_code">
<%
	if(!msg.equals("ok")){
%>
                <option value=""><%=msg%></option>
<%
	}else{
%>
				<option value="">카테고리 선택</option>
<%
		for(int i=0; i<category_all.size(); i++){
%>
				<option value="<%=category_all.get(i).getCategory_code()%>"><%=category_all.get(i).getCategory_name()%></option>
<%
		}
	}
%>
            </select><a href="./category_write.do"><input type="button" value="카테고리 등록" title="카테고리 등록" class="product_btn"></a> <span class="help_text">※ 해당 카테고리가 없을 경우 신규 등록하시길 바랍니다.</span>
        </li>
    </ul>
    <ul>
        <li>상품코드</li>
        <li>
            <input type="text" class="product_input1" name="product_code" value="<%=random_six_digit%>" readonly> 
            <input type="button" value="중복확인" title="중복확인" class="product_btn" onclick="duplicate_chech()"> <span class="help_text">※ 상품코드는 중복되어서는 안됩니다. 중복확인을 해주세요.</span>
        </li>
    </ul>
    <ul>
        <li>상품명</li>
        <li>
            <input type="text" class="product_input2" name="product_name" maxlength="100"> <span class="help_text">※ 상품명은 최대 100자까지만 적용할 수 있습니다.</span>
        </li>
    </ul>
    <ul>
        <li>상품 부가설명</li>
        <li>
            <input type="text" class="product_input4" name="product_description" maxlength="200"> <span class="help_text">※ 상품 부가설명은 최대 200자까지만 적용할 수 있습니다.</span>
        </li>
    </ul>
    <ul>
        <li>판매가격</li>
        <li>
            <input type="text" class="product_input3" name="price" maxlength="7"> <span class="help_text">※ , 없이 숫자만 입력하세요 최대 7자리</span>
        </li>
    </ul>
    <ul>
        <li>할인율</li>
        <li>
            <input type="text" class="product_input3" name="discount_rate" maxlength="2" value="0">% <span class="help_text">※ 숫자만 입력하세요</span>
        </li>
    </ul>
    <ul>
        <li>할인가격</li>
        <li>
            <input type="text" class="product_input3" name="discount_price" maxlength="7" value="0" readonly> <span class="help_text">※ 할인율이 0%일 경우 할인가격은 판매가격과 동일합니다.</span>
        </li>
    </ul>
    <ul>
        <li>상품재고</li>
        <li>
            <input type="text" class="product_input3" name="stock" maxlength="4" value="0">EA <span class="help_text">※ 숫자만 입력하세요. 재고가 0일 경우 soldout이 됩니다.</span>
        </li>
    </ul>
    <ul>
        <li>판매 유/무</li>
        <li>
            <label class="product_label">
            <input type="radio" name="sale_status" value="Y" style="vertical-align:-1px;" checked> 판매시작
            </label>
            <label class="product_label">
            <input type="radio" name="sale_status" value="N" style="vertical-align:-1px;"> 판매종료
            </label> 
        </li>
    </ul>
    <ul>
        <li>조기품절</li>
        <li>
            <label class="product_label">
                <input type="radio" name="early_soldout" value="Y" style="vertical-align:-1px;"> 사용
            </label>
            <label class="product_label">
                <input type="radio" name="early_soldout" value="N" style="vertical-align:-1px;" checked> 미사용
            </label>
        </li>
    </ul>
    <ul style="height: 160px;">
        <li>상품 대표이미지</li>
        <li>
            <ol style="width:100%; height: auto;">
            <li style="width:100%; height:45px;">
            <input type="file" name="pfile_main">
            <span class="help_text">※ 상품 대표이미지 이며, 이미지 용량은 2MB 까지 입니다.</span>
            </li>
            <li style="height:45px;">
            <input type="file" name="pfile_sub1">
            <span class="help_text">※ 추가 이미지 이며, 이미지 용량은 2MB 까지 입니다.</span>
            </li>
            <li style="height:45px;">
            <input type="file" name="pfile_sub2">
            <span class="help_text">※ 추가 이미지 이며, 이미지 용량은 2MB 까지 입니다.</span>
            </li>
            </ol>
        </li>
    </ul>
    <ul style="height: 400px;">
        <li>상품 상세설명</li>
        <li>
            <textarea class="product_text1" name="detail_description"></textarea>
        </li>
    </ul>
</div>
<div class="subpage_view4" style="text-align:center; margin-bottom: 100px;">
    <a href="./product_list.do"><input type="button" value="상품 리스트" title="상품 리스트" class="p_button p_button_color1" style="margin-right: 5px;"></a>
    <input type="button" value="상품 등록" title="상품 등록" class="p_button p_button_color2" onclick="gopage()">
    </span>
</div>
</form>
</section>
</main>
<footer class="main_copyright">
    <div>
        Copyright ⓒ 2024 shopbag All rights reserved.
    </div>
</footer>
</body>
<script>
function gopage(){
	if(frm.category_code.value == ""){
		alert("카테고리를 선택하세요.");
	}
	else if(document.getElementById("dup_chech").value != "ok"){
		alert("상품코드 중복확인을 하세요.");
	}
	else if(frm.product_name.value == ""){
		alert("상품명을 입력하세요.");
	}
	else if(frm.product_description.value == ""){
		alert("상품 부가설명을 입력하세요.");
	}
	else if(frm.price.value == ""){
		alert("상품가격을 입력하세요.");
	}
	else if(frm.detail_description.value == ""){
		alert("상품상세설명을 입력하세요.");
	}
	else{
		frm.submit();
	}
}

//할인가격 자동계산
document.addEventListener("DOMContentLoaded", function() {
    // 입력 필드 가져오기
    var price_ob = frm.price;
    var discount_rate_ob = frm.discount_rate;
    var discount_price_ob = frm.discount_price;

    // 이벤트 리스너 추가 (가격 또는 할인율이 변경될 때마다 실행)
    function update_discount_price() {
        var price = parseInt(price_ob.value) || 0; // 판매가격
        var discount_rate = parseInt(discount_rate_ob.value) || 0; // 할인율

        // 할인가격
        var discount_price = Math.round(price * (1 - discount_rate / 100));
        
        // 할인가격 필드 업데이트
        discount_price_ob.value = discount_price;
    }

    // 입력값이 변경될 때마다 업데이트 실행
    price_ob.addEventListener("input", update_discount_price);
    discount_rate_ob.addEventListener("input", update_discount_price);
});

//상품코드 중복체크 사항
function duplicate_chech(){
	var product_code = frm.product_code.value;
	var xhr = new XMLHttpRequest(); // XMLHttpRequest 객체 생성
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState==XMLHttpRequest.DONE && xhr.status==200){
			if(xhr.response == "ok"){
				alert("중복되지 않는 상품코드입니다.");
				document.getElementById("dup_chech").value = "ok";
			}
			else if(this.response == "error"){
				alert("중복 체크중 오류가 발생했습니다. 관리자에게 문의 바랍니다.");
			}
			else{
				alert("해당 상품코드는 이미 존재합니다. 다른 6자리 난수로 변경합니다. 중복체크를 한번 더 해주세요.")
				frm.product_code.value = this.response;
			}
		}
	};

	xhr.open("GET","./productcode_check.do?product_code="+product_code,true);
	xhr.send(); 
}

</script>
</html>