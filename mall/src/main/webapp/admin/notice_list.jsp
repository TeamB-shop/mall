<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    ArrayList<ArrayList<String>> noticelist = (ArrayList<ArrayList<String>>) request.getAttribute("noticelist");
//     out.print(noticelist);
    %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 리스트 페이지</title>
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
<%@include file="./top.jsp"%>
<main class="maincss">
<section>
    <p>공지사항 관리페이지</p>
    <div class="subpage_view">
    
    <ul>
         <li><input type="checkbox" onclick="chkall(this)"></li>
        <li>NO</li>
        <li>제목</li>
        <li>글쓴이</li>
        <li>날짜</li>
        <li>조회</li>
    </ul>
<%
if (noticelist != null && !noticelist.isEmpty()) {
    int no = noticelist.size();

    // is_pin = 'Y' 먼저 출력
    for (int i = 0; i < noticelist.size(); i++) {
        ArrayList<String> row = noticelist.get(i);
        if ("Y".equals(row.get(1))) {
%>
    <ol>
         <li><input type="checkbox" class="rowchk" value="<%= row.get(0) %>"></li>
        <li><%= no-- %></li>
          <li onclick="location.href='./notice_view.do?midx=<%= row.get(0) %>'" style="cursor: pointer;">[고정] <%= row.get(2) %></li>
        <li><%= row.get(3) %></li>
        <li><%= row.get(4).substring(0,10) %></li>
        <li><%= row.get(5) %></li>
    </ol>
<%
        }
    }

    // is_pin 출력용
    for (int i = 0; i < noticelist.size(); i++) {
        ArrayList<String> row = noticelist.get(i);
        if (!"Y".equals(row.get(1))) {
%>
    <ol>
         <li><input type="checkbox" class="rowchk" value="<%= row.get(0) %>"></li>
        <li><%= no-- %></li>
        <li  onclick="location.href='./notice_view.do?midx=<%= row.get(0) %>'" style="cursor: pointer;"><%= row.get(2) %></li>
        <li><%= row.get(3) %></li>
        <li><%= row.get(4).substring(0,10) %></li>
        <li><%= row.get(5) %></li>
    </ol>
<%
        }
    }
} else {
%>
    <ol class="none_text">
        <li>등록된 공지 내용이 없습니다.</li>
    </ol>
<%
}
%>
    </div>
    <div class="board_btn">
        <button class="border_del" onclick="deletenotice()">공지삭제</button>
        <button class="border_add" onclick="location.href='./notice_write.do'">공지등록</button>
    </div>

    
    <div class="border_page">
        <ul class="pageing">
            <li><img src="./ico/double_left.svg"></li>
            <li><img src="./ico/left.svg"></li>
            <li>1</li>
            <li><img src="./ico/right.svg"></li>
            <li><img src="./ico/double_right.svg"></li>
        </ul>
    </div>
</section>
</main>
<footer class="main_copyright">
    <div>
        Copyright ⓒ 2024 shopbag All rights reserved.
    </div>
</footer>
</body>
<script src="./notice_list.js"></script>
</html>