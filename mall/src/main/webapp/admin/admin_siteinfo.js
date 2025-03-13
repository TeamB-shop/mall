function save_set(){
	if(frm.mname.value==""){
		alert("홈페이지 이름을 입력하세요.");
	}
	else if(frm.admin_email.value==""){
		alert("관리자 메일 주소를 입력하세요.");
	}
	else if(frm.signup_point.value==""){
		alert("회원가입시 적립금을 입력하세요.");
	}
	else if(frm.signup_lv.value==""){
		alert("회원가입시 권한레벨을 입력하세요.");
	}
	else if(frm.cpname.value==""){
		alert("회사명을 입력하세요.");
	}
	else if(frm.cpno.value==""){
		alert("사업자등록번호를 입력하세요.");
	}
	else if(frm.ceoname.value==""){
		alert("대표자명을 입력하세요.");
	}
	else if(frm.ceotel.value==""){
		alert("대표전화번호를 입력하세요.");
	}
	else if(frm.ecommno.value==""){
		alert("통신판매업 신고번호를 입력하세요.");
	}
	else if(frm.telcommno.value==""){
		alert("부가통신 사업자번호를 입력하세요.");
	}
	else if(frm.bzipcode.value==""){
		alert("사업장 우편번호를 입력하세요.");
	}
	else if(frm.baddr.value==""){
		alert("사업장 주소를 입력하세요.");
	}
	else if(frm.secname.value==""){
		alert("정보관리책임자명을 입력하세요.");
	}
	else if(frm.secmail.value==""){
		alert("정보책임자 E-mail을 입력하세요.");
	}
	else if(frm.banknm.value==""){
		alert("무통장 은행을 입력하세요.");
	}
	else if(frm.bankaccno.value==""){
		alert("은행계좌번호를 입력하세요.");
	}
	else if(frm.min_p_pay.value==""){
		alert("결제 최소 포인트를 입력하세요.");
	}
	else if(frm.min_p_pay.value==""){
		alert("결제 최대 포인트를 입력하세요.");
	}
	else if(frm.shipp_compnm.value==""){
		alert("배송업체명을 입력하세요.");
	}
	else if(frm.shipp_fee.value==""){
		alert("배송가격을 입력하세요.");
	}
	else{
		frm.submit();
		
	}
}