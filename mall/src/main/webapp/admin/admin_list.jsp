<%@page import="model.admin_dto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<admin_dto> admins = (ArrayList)request.getAttribute("admins");
%>
<!DOCTYPE html>
<html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 등록 페이지</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="./css/main.css?v=1">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
</head>
<%@include file="./top.jsp" %>
<main class="maincss">
<section>
    <p>신규등록 관리자</p>
    <ol class="new_admin_title2">
        <li>NO</li>
        <li>관리자명</li>
        <li>아이디</li>
        <li>전화번호</li>
        <li>이메일</li>
        <li>담당부서</li>
        <li>담당직책</li>
        <li>가입일자</li>
        <li>승인여부</li>
    </ol>
<%
	if(admins.size() == 1) {  // 최소한개(0번째)는 마스터 정보가 저장되어 있음
%>
    <ol class="new_admin_none">
        <li>신규 등록된 관리자가 없습니다.</li>
    </ol>
<%
	} else {
		for(int i=1; i<admins.size(); i++) {
			String aid = admins.get(i).getAid();
			String tel = "010" + admins.get(i).getAtel1() + admins.get(i).getAtel2();
%>
    <ol class="new_admin_lists2">
        <li><%=i%></li>
        <li><%=admins.get(i).getAname()%></li>
        <li><%=aid%></li>
        <li><%=tel%></li>
        <li><%=admins.get(i).getAemail()%></li>
        <li><%=admins.get(i).getAdepartment()%></li>
        <li><%=admins.get(i).getAposition()%></li>
        <li><%=admins.get(i).getAdate().substring(0,10)%></li>
        <li>
<%
			if(admins.get(i).getApproval().equals("N")) {
%>
				미승인<input type="button" value="승인" class="new_addbtn1" onclick="approval(this, '<%=aid%>')">	
<%
			} else {
%>
 				승인<input type="button" value="미승인" class="new_addbtn2" onclick="approval(this, '<%=aid%>')">
<% 
			}
%>
        </li>
    </ol>
<%			
		}
	}
%>
</section>
<section></section>
<section></section>
</main>
<footer class="main_copyright">
    <div>
        Copyright ⓒ 2024 shopbag All rights reserved.
    </div>
</footer>
</body>
<script>
function approval(ob, aid){
	let statusText = ob.previousSibling;
	if(ob.value == "승인"){
		ob.value = "미승인";
		statusText.textContent = "승인"
		ajaxpost(aid, 'Y');
	}
	else{
		ob.value = "승인";
		statusText.textContent = "미승인";
		ajaxpost(aid, 'N')
	}
}

//Ajax 함수를 이용하여 승인, 미승인 처리
var ok = "";
function ajaxpost(aid, approval){
	function wck(){
		if(window.XMLHttpRequest){ 
			return new XMLHttpRequest(); 
		}
	}
	
	function getdata(){
		console.log(ok.readyState);
		if(ok.readyState==XMLHttpRequest.DONE && ok.status==200){
			if(this.response == "Y"){
				alert("승인 처리되었습니다..");
			}
			else if(this.response == "N"){
				alert("미승인 처리되었습니다.");
			}
			else if(this.response == "NO"){
				alert("오류가 있어 처리되지 않았습니다.");
			}
		}
	}

	ok = wck();
	ok.onreadystatechange = getdata; 
	ok.open("GET","./login_approve.do?aid="+aid+"&approval="+approval,true);
	ok.send(); 
}

</script>
</html>