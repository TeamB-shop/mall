//regex부분
function emailvali(input) {
	var emailregex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9가-힣.-]+\.[a-zA-Z가-힣]{2,}$/;
	var value = input.value.trim();


	if (!emailregex.test(value)) {
		alert("유효하지 않은 이메일 형식입니다.");
		input.focus();
		input.value = "";
	}
}
function numvali(input) {
	var numregex = /[^0-9]/g;
	const value = input.value.trim();


	if (!numregex.test(value)) {
		alert("유효하지 않은 숫자 형식입니다.");
		input.focus();
		input.value = "";
	}
}
function phonevali(input) {
	var phoneRegex = /^0\d{1,2}\d{3,4}\d{4}$/;
	var value = input.value.trim();


	if (!phoneRegex.test(value)) {
		alert("유효하지 않은 번호 형식입니다.");
		input.focus();
		input.value = "";
	}
}
function charvali(input) {
	var charRegex = /[a-zA-Zㄱ-힣]/;
	var value = input.value.trim();


	if (!charRegex.test(value)) {
		alert("유효하지 문자 형식입니다.");
		input.focus();
		input.value = "";
	}
}

function change_set() {
	console.log("change!!!");
}

function save_set() {

	var frm = document.getElementById("frm"); // 폼 가져오기

	if (frm.mname.value.trim() == "") {
		alert("홈페이지 이름을 입력하세요.");
		frm.mname.focus();
		return false;
	}
	else if (frm.admin_email.value.trim() == "") {
		alert("관리자 메일 주소를 입력하세요.");
		frm.admin_email.focus();
		return false;
	}
	else if (frm.signup_point.value.trim() == "") {
		alert("회원가입 시 적립금을 입력하세요.");
		frm.signup_point.focus();
		return false;
	}
	else if (frm.signup_lv.value.trim() == "") {
		alert("회원가입 시 권한레벨을 입력하세요.");
		frm.signup_lv.focus();
		return false;
	}
	else if (frm.cpname.value.trim() == "") {
		alert("회사명을 입력하세요.");
		frm.cpname.focus();
		return false;
	}
	else if (frm.cpno.value.trim() == "") {
		alert("사업자등록번호를 입력하세요.");
		frm.cpno.focus();
		return false;
	}
	else if (frm.ceoname.value.trim() == "") {
		alert("대표자명을 입력하세요.");
		frm.ceoname.focus();
		return false;
	}
	else if (frm.ceotel.value.trim() == "") {
		alert("대표 전화번호를 입력하세요.");
		frm.ceotel.focus();
		return false;
	}
	else if (frm.ecommno.value.trim() == "") {
		alert("통신판매업 신고번호를 입력하세요.");
		frm.ecommno.focus();
		return false;
	}
	else if (frm.telcommno.value.trim() == "") {
		alert("부가통신 사업자번호를 입력하세요.");
		frm.telcommno.focus();
		return false;
	}
	else if (frm.bzipcode.value.trim() == "") {
		alert("사업장 우편번호를 입력하세요.");
		frm.bzipcode.focus();
		return false;
	}
	else if (frm.baddr.value.trim() == "") {
		alert("사업장 주소를 입력하세요.");
		frm.baddr.focus();
		return false;
	}
	else if (frm.secname.value.trim() == "") {
		alert("정보관리책임자명을 입력하세요.");
		frm.secname.focus();
		return false;
	}
	else if (frm.secmail.value.trim() == "") {
		alert("정보책임자 E-mail을 입력하세요.");
		frm.secmail.focus();
		return false;
	}
	else if (frm.banknm.value.trim() == "") {
		alert("무통장 은행을 입력하세요.");
		frm.banknm.focus();
		return false;
	}
	else if (frm.bankaccno.value.trim() == "") {
		alert("은행계좌번호를 입력하세요.");
		frm.bankaccno.focus();
		return false;
	}
	else if (frm.min_p_pay.value.trim() == "") {
		alert("결제 최소 포인트를 입력하세요.");
		frm.min_p_pay.focus();
		return false;
	}
	else if (frm.max_p_pay.value.trim() == "") {
		alert("결제 최대 포인트를 입력하세요.");
		frm.max_p_pay.focus();
		return false;
	}
	else if (frm.shipp_compnm.value.trim() == "") {
		alert("배송업체명을 입력하세요.");
		frm.shipp_compnm.focus();
		return false;
	}
	else if (frm.shipp_fee.value.trim() == "") {
		alert("배송 가격을 입력하세요.");
		frm.shipp_fee.focus();
		return false;
	}

	// 모든 검증을 통과하면 폼 제출
	if(confirm("해당 정보를 사이트에 반영하시겠습니까?")){
	frm.submit();
		
	}
}
function save_cancel() {
	if(confirm("해당 정보를 반영하지 않겠습니까?")){
	location.reload;
	}
	
	
}