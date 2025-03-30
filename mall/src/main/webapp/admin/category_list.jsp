<%@page import="product.category_dto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int ctn_per_page = (int)request.getAttribute("ctn_per_page"); //페이지당 출력갯수 
	
	Map<String, Object> result = (Map)request.getAttribute("result");
	int total_ctn = (int)result.get("total_ctn");  //게시물 총갯수 
	ArrayList<category_dto> view_all = (ArrayList)result.get("view_all");
	
	//페이지번호 갯수 계산 예시 : 7.0/3.0 = 2.33 => Math.ceil(2.33) = 3 (참고로 정수/정수는 정수이므로 double 형변환이 필요)
	int pageno_ctn = (int)Math.ceil((double)total_ctn/(double)ctn_per_page);  //페이지번호 갯수
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
    <link rel="stylesheet" type="text/css" href="./css/category.css?v=6">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
</head>
<body>
<%@include file="./top.jsp" %>
<main class="maincss">
    <section>    
<p>카테고리관리 페이지</p>
<div class="subpage_view">
    <span>등록된 카테고리 <%=total_ctn%>건</span>
    <span>
        <form id="frm" method="get" action="./category_list.do" onsubmit="return category_search()">
        <select name="search_key" class="p_select1">
            <option value="category_name">카테고리명</option>
            <option value="category_code">카테고리코드</option>
        </select>
        <input type="text" class="p_input1" name="search_word" placeholder="검색어를 입력해 주세요">
        <input type="submit" value="검색" title="카테고리 검색" class="p_submit">
        </form>
    </span>
</div>
<div class="subpage_view2">
    <ul>
        <li><input type="checkbox"></li>
        <li>분류코드(사용안함)</li>
        <li>카테고리 코드</li>
        <li>카테고리명</li>
        <li>소메뉴 코드(사용안함)</li>
        <li>소메뉴명(사용안함)</li>
        <li>사용 유/무(사용안함)</li>
        <li>관리</li>
    </ul>
<%
	if (result == null){
%>
    <ul>
        <li style="width: 100%;">시스템 오류로 카테고리 관리 목록을 가져오지 못했습니다.</li>
    </ul>
<%
	} else if (view_all.isEmpty()){
%>
    <ul>
        <li style="width: 100%;">등록된 카테고리가 없습니다.</li>
    </ul>
<%
	} else {
		for(int i=0; i<view_all.size(); i++){
%>
    <ul>
        <li><input type="checkbox"></li>
        <li style="text-align: left; text-indent: 5px;">-</li>
        <li><%=view_all.get(i).getCategory_code()%></li>
        <li style="text-align: left; text-indent: 5px;"><%=view_all.get(i).getCategory_name()%></li>
        <li>-</li>
        <li style="text-align: left; text-indent: 5px;">-</li>
        <li>-</li>
        <li>[수정]</li>
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
        <li><a href="./category_list.do?pageno=<%=i%>"><%=i%></a></li>
<%
	}
%>
        <li><img src="./ico/right.svg"></li>
        <li><img src="./ico/double_right.svg"></li>
    </ul>
</div>
<div class="subpage_view4">
    <input type="button" value="카테고리 삭제" title="카테고리 삭제" class="p_button">
    <span style="float: right;">
    <a href="./product_list.do"><input type="button" value="상품 리스트" title="상품 리스트" class="p_button p_button_color1"></a>s
    <a href="./category_write.do"><input type="button" value="카테고리 등록" title="카테고리 등록" class="p_button p_button_color2"></a>
    </span>
</div>
</section>
</main>
<footer class="main_copyright">
    <div>
        Copyright ⓒ 2024 shopbag All rights reserved.
    </div>
</footer>
</body>
<script>
function category_search(){
	if(frm.search_word.value == ""){
		alert("검색어를 입력해 주세요.");
		frm.action = "./category_list.do";
		frm.search_word.focus();
		return true;
	}
	else{
		var search_key = frm.search_key.value;
		var search_word = frm.search_word.value
		frm.action = "./category_list.do?search_key="+search_key+"&search_word="+search_word;
		return true;
	}
}
</script>
</html>