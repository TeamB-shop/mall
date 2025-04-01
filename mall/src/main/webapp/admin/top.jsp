<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	HttpSession se = (HttpSession)request.getSession();
	String aname = (String)se.getAttribute("aname");
%>
<header class="headercss">
    <div class="header_div">
        <p><img src="./img/logo.png" class="logo_sm" onclick="location.href='./admin_list.do'"> ADMINISTRATOR</a></p>
        <p><%=aname%> 관리자 <a href="#">[개인정보 수정]</a>
        <a href="./logout.do">[로그아웃]</a> <!-- 로그아웃 버튼 기능 추가 -->
        </p>
    </div>
</header>
<nav class="navcss">
    <div class="nav_div">
        <ol>
            <li title="쇼핑몰 상품관리"><a href="./admin_list.do">쇼핑몰 관리자 리스트</a></li>
            <li title="쇼핑몰 상품관리"><a href="./product_list.do">쇼핑몰 상품관리</a></li>
            <li title="쇼핑몰 기본설정"><a href="./admin_siteinfo.do">쇼핑몰 기본설정</a></li>
            <li title="쇼핑몰 공지사항"><a href="./notice_list.do">쇼핑몰 공지사항</a></li>
        </ol>
    </div>
</nav>
</html>