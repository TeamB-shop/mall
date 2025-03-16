<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	HttpSession se = (HttpSession)request.getSession();
	String aname = (String)se.getAttribute("aname");
%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 등록 페이지</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="./css/main.css">
    <link rel="stylesheet" type="text/css" href="./css/subpage.css?v=5">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
</head>
<header class="headercss">
    <div class="header_div">
        <p><img src="./img/logo.png" class="logo_sm"> ADMINISTRATOR</p>
        <p><%=aname%> 관리자 <a href="#">[개인정보 수정]</a>
        <a href="./logout.do">[로그아웃]</a> <!-- 로그아웃 버튼 기능 추가 -->
        </p>
    </div>
</header>
<nav class="navcss">
    <div class="nav_div">
        <ol>
            <li title="쇼핑몰 상품관리"><a href="./admin_list.do">쇼핑몰 관리자 리스트</a></li>
            <li title="쇼핑몰 상품관리">쇼핑몰 상품관리</li>
            <li title="쇼핑몰 기본설정"><a href="./admin_siteinfo.do">쇼핑몰 기본설정</a></li>
            <li title="쇼핑몰 공지사항">쇼핑몰 공지사항</li>
        </ol>
    </div>
</nav>
</html>