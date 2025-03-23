<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 내용 확인 페이지</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=10">
    <link rel="stylesheet" type="text/css" href="./css/main.css?v=10">
    <link rel="stylesheet" type="text/css" href="./css/notice.css?v=10">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
</head>
<body>
<%@include file="./top.jsp" %>
<main class="maincss">
<section>
    <p>공지사항 확인 페이지</p>
<div class="write_view">
<ul>
    <li>공지사항 제목 </li>
    <li>
       <%= request.getAttribute("title") %>
    </li>
</ul>
<ul>
    <li>글쓴이</li>
    <li>
     <%= request.getAttribute("writer") %>
    </li>
</ul>
<ul>
 <li>첨부파일</li>
    <li>
        <%
            String mfile = (String) request.getAttribute("mfile");
            if (mfile != null && !mfile.equals("")) {
        %>
            <a href="<%= request.getContextPath() %>/notice/<%= mfile %>" target="_blank"><%= mfile %></a>
        <%
            } else {
        %>
            첨부파일 없음
        <%
            }
        %>
    </li>
</ul>
<ul class="ul_height">
    <li>공지내용</li>
    <li>
        <div class="notice_input3" style="overflow-y: auto;"><%= request.getAttribute("content") %></div>
    </li>
</ul>
</div>
<div class="board_btn">
   <button class="border_del" onclick="location.href='./notice_list.do'">공지목록</button>
   <button type="button" class="border_add">공지수정</button>
    <button class="border_modify" style="margin-left: 8px;" onclick="deleteNotice('<%= request.getAttribute("midx") %>')">공지삭제</button>
</div>
</section>
</main>
<footer class="main_copyright">
    <div>
        Copyright ⓒ 2024 shopbag All rights reserved.
    </div>
</footer>
</body>
<script src="./notice_view.js"></script>
</html>