<%@page import="java.text.DecimalFormat"%>
<%@page import="model.product_dto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int ctn_per_page = (int)request.getAttribute("ctn_per_page"); //페이지당 출력갯수 
	
	Map<String, Object> result = (Map)request.getAttribute("result");
	int total_ctn = (int)result.get("total_ctn");  //게시물 총갯수 
	ArrayList<model.product_dto> view_all = (ArrayList)result.get("view_all");
	
	//페이지번호 갯수 계산 예시 : 7.0/3.0 = 2.33 => Math.ceil(2.33) = 3 (참고로 정수/정수는 정수이므로 double 형변환이 필요)
	int pageno_ctn = (int)Math.ceil((double)total_ctn/(double)ctn_per_page);  //페이지번호 갯수
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>쇼핑몰 상품관</title>
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
<p>상품관리 페이지</p>
<div class="subpage_view">
    <span>등록된 상품 <%=total_ctn%>건</span>
    <span>
        <form id="frm" method="get" onsubmit="return product_search()">
        <select name="search_key" class="p_select1">
            <option value="product_name">상품명</option>
            <option value="product_code">상품코드</option>
        </select>
        <input type="text" class="p_input1" name="search_word" placeholder="검색어를 입력해 주세요">
        <input type="submit" value="검색" title="상품검색" class="p_submit">
        </form>
    </span>
</div>
<div class="subpage_view2">
    <ul>
        <li><input type="checkbox" id="allck" onclick="check_all(this.checked)"></li>
        <li>코드</li>
        <li>이미지</li>
        <li>상품명</li>
        <li>카테고리 분류</li>
        <li>판매가격</li>
        <li>할인가격</li>
        <li>할인율</li>
        <li>재고현황</li>
        <li>판매유/무</li>
        <li>품절</li>
        <li>관리</li>
    </ul>
<%
	if (result == null){
%>
    <ul>
        <li style="width: 100%;">시스템 오류로 상품관리 목록을 가져오지 못했습니다.</li>
    </ul>
<%
	} else if (view_all.isEmpty()){
%>
    <ul>
        <li style="width: 100%;">등록된 상품이 없습니다.</li>
    </ul>
<%
	} else {
		DecimalFormat df = new DecimalFormat("#,###");
		for(int i=0; i<view_all.size(); i++){
%>
    <ul>
        <li><input type="checkbox" name="ckboxs" value="<%=view_all.get(i).getProduct_code()%>"></li>
        <li><%=view_all.get(i).getProduct_code()%></li>
<%
			if(view_all.get(i).getMain_image_path() != null){
%>
        <li><a href="..<%=view_all.get(i).getMain_image_path()%>" target="_blank">클릭</a></li>
<%
			}else{
%>
		<li>NO IMAGE</a></li>
<%
			}
%>   
        <li><%=view_all.get(i).getProduct_name()%></li>
        <li><%=view_all.get(i).getCategory_name()%></li>
        <li><%=df.format(view_all.get(i).getPrice())%></li>
        <li><%=df.format(view_all.get(i).getDiscount_price())%></li>
        <li><%=view_all.get(i).getDiscount_rate()%></li>
        <li><%=df.format(view_all.get(i).getStock())%></li>
        <li><%=view_all.get(i).getSale_status()%></li>
        <li><%=view_all.get(i).getEarly_soldout()%></li>
        <li>-</li>
    </ul>
<%
		}
	}
%>
</div>
<div class="subpage_view3">
    <ul class="pageing">
        <li><img src="./ico/double_left.svg"></li>
        <li><img src="./ico/left.svg"></li>
<%
	for(int i=1; i<=pageno_ctn; i++){
%>
        <li><a href="./product_list.do?pageno=<%=i%>"><%=i%></a></li>
<%
	}
%>
        <li><img src="./ico/right.svg"></li>
        <li><img src="./ico/double_right.svg"></li>
    </ul>
</div>
<div class="subpage_view4">
    <input type="button" value="선택상품 삭제" title="선택상품 삭제" class="p_button" onclick="check_del()">
    <span style="float: right;">
    <a href="./product_write.do"><input type="button" value="신규상품 등록" title="신규상품 등록" class="p_button p_button_color1"></a>
    <a href="./category_write.do"><input type="button" value="카테고리 등록" title="카테고리 등록" class="p_button p_button_color2"></a>
    </span>
</div>
</section>

<!-- form 전송으로 선택된 값을 삭제 -->
<form id="dform" method="post" action="./product_delete.do">
<input type="hidden" name="ckdel" value="">
</form>

</main>
<footer class="main_copyright">
    <div>
        Copyright ⓒ 2024 shopbag All rights reserved.
    </div>
</footer>
</body>
<script>
function product_search(){
	if(frm.search_word.value == ""){
		alert("검색어를 입력해 주세요.");
		frm.action = "./product_list.do";
		frm.search_word.focus();
		return true;
	}
	else{
		var search_key = frm.search_key.value;
		var search_word = frm.search_word.value
		frm.action = "./product_list.do?search_key="+search_key+"&search_word="+search_word;
		return true;
	}
}

//전체선택 관련 핸들링 함수
function check_all(ck){ 
	var ckboxs = document.getElementsByName("ckboxs");

	for(var i=0; i<ckboxs.length; i++){
		ckboxs[i].checked = ck;
	}
}

//삭제 함수
function check_del(){
	var ar = new Array();  //script 배열
	
	var ckboxs = document.getElementsByName("ckboxs");

	for(var i=0; i<ckboxs.length; i++){
		if(ckboxs[i].checked){
			ar.push(ckboxs[i].value);
		}
	}
	dform.ckdel.value = ar;
	console.log(dform.ckdel.value);
	if(confirm("해당 데이터를 삭제시 복구 되지 않습니다.")){
		dform.submit();
	}
}
</script>
</html>
