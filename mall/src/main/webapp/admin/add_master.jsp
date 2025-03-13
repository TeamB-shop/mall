<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 등록 페이지</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=1">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
</head>
<body>
    <header class="admin_title_add">
        <p><img src="./img/logo.png" class="logo_sm"> ADMINISTRATOR ADD</p>
    </header>

	<form id="frm" method="post" action="./join_ok.do">
	<!-- 중복체크여부 저장 -->
	<input type="hidden" id="idck" value="">
    <section class="admin_bgcolor_add">
        <div class="admin_login_add">
            <ul>
                <li class="font_color1">아이디 및 패스워드 정보</li>
                <li>
                <input type="text" class="add_input1" name="aid" placeholder="생성할 관리자 아이디를 입력하세요">
                <button type="button"  class="btn_button" onclick="idcheck()">중복체크</button>
                </li>
                <li>
                    <input type="password" class="add_input1" name="apassword" placeholder="접속할 패스워드를 입력하세요">
                    <input type="password" class="add_input1" id="pass2" placeholder="동일한 패스워드를 입력하세요">
                </li>
                <li class="font_color1">관리자 기본정보 입력</li>
                <li>
                    <input type="text" class="add_input1" name="aname" placeholder="담당자 이름을 입력하세요">
                </li>
                <li>
                <input type="text" class="add_input1 emails" name="aemail" placeholder="담당자 이메일을 입력하세요">
                </li>
                <li class="font_color1">
                <input type="text" class="add_input1 hp1" placeholder="HP" value="010" maxlength="3">
                -
                <input type="text" class="add_input1 hp2" name="atel1" placeholder="1234" maxlength="4">
                -
                <input type="text" class="add_input1 hp2" name="atel2" placeholder="5678" maxlength="4">
                </li>
                <li class="font_color1">관리자 담당부서 및 직책</li>
                <li>
                    <select class="add_select1" name="adepartment">
                        <option>담당자 부서를 선택하세요</option>
                        <option>임원</option>
                        <option>전산팀</option>
                        <option>디자인팀</option>
                        <option>CS팀</option>
                        <option>MD팀</option>
                    </select>
                    <select class="add_select1" name="aposiition">
                        <option>담당자 직책을 선택하세요</option>
                        <option>대표</option>
                        <option>부장</option>
                        <option>팀장</option>
                        <option>과장</option>
                        <option>대리</option>
                        <option>사원</option>
                    </select>
                </li>
                <li class="font_color1">※ 가입완료 후 전산 담당자가 확인 후 로그인 할 수 있습니다.</li>
            </ul>
            <span class="admin_addbtn">
                <button type="button" class="btn_button btncolor1" title="관리자 등록" onclick="joinok()">관리자 등록</button>
                <button type="button" class="btn_button btncolor2" title="관리자 취소">등록 취소</button>
            </span>
        </div>
    </section>
    </form>
    <footer class="admin_copy">
        <div>
            Copyright ⓒ 2024 shopbag All rights reserved.
        </div>
    </footer>
</body>
<script>
function joinok(){

	var reg_email = /^[a-z0-9]([a-z0-9-_.])+\@[a-z0-9ㄱ-힇-_]+\.[a-z0-9ㄱ-힇]{2,}/i; //이메일 정규식 
	var reg_tel = /[0-9]{4}/; //휴대폰번호 정규식
	var pass1 = frm.apassword.value;
	var pass2 = document.getElementById("pass2").value
	
	if(frm.aid.value==""){
		alert("아이디를 입력후 중복체크를 하세요.");
		frm.aid.focus();
	}
	else if(document.getElementById("idck").value==""){
		alert("아이디 중복체크를 하세요.");
	}
	else if(frm.apassword.value==""){
		alert("패스워드를 입력하세요.");
		frm.apassword.focus();
	}
	else if(pass1 != pass2){
		alert("동일한 패스워드를 입력하세요.");
	}
	else if(frm.aname.value==""){
		alert("이름을 입력하세요.");
		frm.aname.focus();
	}
	else if(frm.aemail.value==""){
		alert("이메일주소를 입력하세요.");
		frm.aemail.focus();
	}
	else if(reg_email.test(frm.aemail.value)==false){
		alert("이메일 주소를 정확히 입력하세요.");
		frm.aemail.value=="";
		frm.aemail.focus();
	}
	else if(frm.atel1.value==""){
		alert("전화번호를 입력하세요.");
		frm.stel.focus();
	}
	else if(reg_tel.test(frm.atel1.value)==false){
		alert("휴대폰번호를 정확히 입력하세요.");
		frm.stel.value=="";
		frm.stel.focus();
	}
	else if(frm.atel2.value==""){
		alert("전화번호를 입력하세요.");
		frm.stel.focus();
	}
	else if(reg_tel.test(frm.atel2.value)==false){
		alert("휴대폰번호를 정확히 입력하세요.");
		frm.stel.value=="";
		frm.stel.focus();
	}
	else{
		frm.submit();
	}
	
}

//아이디 중복체크 사항
function idcheck(){
	if(frm.aid.value==""){
		alert("아이디를 입력하셔야 합니다.");
	}
	else{
		//Ajax 역할(Back-end 통신)
		ajaxpost(frm.aid.value);
	}
}

//Ajax 함수를 이용하여 중복 체크
var ok = "";
function ajaxpost(data){
	function wck(){
		if(window.XMLHttpRequest){ 
			return new XMLHttpRequest(); 
		}
	}
	
	function getdata(){

		if(ok.readyState==XMLHttpRequest.DONE && ok.status==200){
			if(this.response == "ok"){
				alert("사용가능한 아이디입니다.");
				document.getElementById("idck").value = "ok";
				frm.aid.readOnly = true; 
			}
			else if(this.response == "no"){
				alert("해당 아이디는 이미 사용중 입니다.");
				frm.aid.value = "";
			}
		}
	}

	ok = wck();
	ok.onreadystatechange = getdata; 
	ok.open("GET","./idcheck.do?aid="+data,true);
	ok.send(); 
}
</script>
</html>