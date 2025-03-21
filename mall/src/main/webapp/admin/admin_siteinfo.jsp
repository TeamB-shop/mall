<%@page import="spset.spset_dto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
spset_dto spdata = (spset_dto) request.getAttribute("spdata");
%>

<!DOCTYPE html>
<html lang="ko">
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
<body>
	<%@include file="./top.jsp"%>
	<form id="frm" action="../spset/admin_siteinfo_ok.do" method="post" >
		<main class="maincss">
			<section>
				<p>홈페이지 가입환경 설정</p>

				<div class="subpage_view">
					<ul class="info_form">
						<li>홈페이지 제목</li>
						<li><input type="text"
							value="<%=spdata.getMname() != null ? spdata.getMname() : ""%>"
							class="in_form1" name="mname"></li>
					</ul>
					<ul class="info_form">
						<li>관리자 메일 주소</li>
						<li><input type="text" class="in_form2" name="admin_email" onchange="emailvali(this)"
							value="<%=spdata.getAdmin_email() != null ? spdata.getAdmin_email() : ""%>">
							※ 관리자가 보내고 받는 용도로 사용하는 메일 주소를 입력합니다.(회원가입,인증메일,회원메일발송 등에서 사용)</li>
					</ul>
					<ul class="info_form">
						<li>포인트 사용 유/무</li>
						<li class="checkcss"><em> <label> <input
									type="radio" class="ckclass" value="Y" name="use_point"
									<%=("Y".equals(spdata.getUse_point())) ? "checked" : ""%>>포인트
									사용
							</label>
						</em> <em> <label> <input type="radio" class="ckclass"
									value="N" name="use_point"
									<%=(!"Y".equals(spdata.getUse_point())) ? "checked" : ""%>>포인트
									미사용
							</label>
						</em></li>
					</ul>

					<ul class="info_form2"
						style="border-bottom: 1px solid rgb(81, 61, 61);">
						<li>회원가입시 적립금</li>
						<li><input type="text" class="in_form3" maxlength="5"
							name="signup_point" onchange="numvali(this)"
							value="<%=spdata.getSignup_point() != null ? spdata.getSignup_point() : ""%>">
							점</li>
						<li>회원가입시 권한레벨</li>
						<li><input type="text" class="in_form3" maxlength="1"
							name="signup_lv" onchange="numvali(this)"
							value="<%=spdata.getSignup_lv() != null ? spdata.getSignup_lv() : ""%>">
							레벨</li>
					</ul>
				</div>
				<p>홈페이지 기본환경 설정</p>
				<div class="subpage_view">
					<ul class="info_form2">
						<li>회사명</li>
						<li><input type="text" class="in_form0" name="cpname"
							value="<%=spdata.getCpname() != null ? spdata.getCpname() : ""%>"></li>
						<li>사업자등록번호</li>
						<li><input type="text" class="in_form0" name="cpno" onchange="numvali(this)"
							value="<%=spdata.getCpno() != null ? spdata.getCpno() : ""%>"></li>
					</ul>
					<ul class="info_form2">
						<li>대표자명</li>
						<li><input type="text" class="in_form0" name="ceoname"
							value="<%=spdata.getCeoname() != null ? spdata.getCeoname() : ""%>"></li>
						<li>대표전화번호</li>
						<li><input type="text" class="in_form0" name="ceotel" onchange="numvali(this)"
							value="<%=spdata.getCeotel() != null ? spdata.getCeotel() : ""%>"></li>
					</ul>
					<ul class="info_form2">
						<li>통신판매업 신고번호</li>
						<li><input type="text" class="in_form0" name="ecommno" onchange="numvali(this)"
							value="<%=spdata.getEcommno() != null ? spdata.getEcommno() : ""%>"></li>
						<li>부가통신 사업자번호</li>
						<li><input type="text" class="in_form0" name="telcommno" onchange="numvali(this)"
							value="<%=spdata.getTelcommno() != null ? spdata.getTelcommno() : ""%>"></li>
					</ul>
					<ul class="info_form2">
						<li>사업장 우편번호</li>
						<li><input type="text" class="in_form0" name="bzipcode" onchange="numvali(this)"
							value="<%=spdata.getBzipcode() != null ? spdata.getBzipcode() : ""%>"></li>
						<li>사업장 주소</li>
						<li><input type="text" class="in_form2" name="baddr"
							value="<%=spdata.getBaddr() != null ? spdata.getBaddr() : ""%>"></li>
					</ul>
					<ul class="info_form2"
						style="border-bottom: 1px solid rgb(81, 61, 61);">
						<li>정보관리책임자명</li>
						<li><input type="text" class="in_form0" name="secname" onchange="charvali(this)"
							value="<%=spdata.getSecname() != null ? spdata.getSecname() : ""%>"></li>
						<li>정보책임자 E-mail</li>
						<li><input type="text" class="in_form1" name="secmail" onchange="emailvali(this)"
							value="<%=spdata.getSecmail() != null ? spdata.getSecmail() : ""%>"></li>
					</ul>
				</div>
				<p>결제 기본환경 설정</p>
				<div class="subpage_view">
					<ul class="info_form2">
						<li>무통장 은행</li>
						<li><input type="text" class="in_form0" name="banknm"
							value="<%=spdata.getBanknm() != null ? spdata.getBanknm() : ""%>"></li>
						<li>은행계좌번호</li>
						<li><input type="text" class="in_form1" name="bankaccno" onchange="numvali(this)"
							value="<%=spdata.getBankaccno() != null ? spdata.getBankaccno() : ""%>"></li>
					</ul>
					<ul class="info_form">
						<li>신용카드 결제 사용</li>
						<li class="checkcss"><em><label> <input
									type="radio" class="ckclass" name="use_creditcard" value="Y"
									<%=("Y".equals(spdata.getUse_creditcard())) ? "checked" : ""%>>
									사용
							</label></em> <em><label> <input type="radio" class="ckclass"
									checked name="use_creditcard" value="N"
									<%=(!"Y".equals(spdata.getUse_creditcard())) ? "checked" : ""%>>
									미사용
							</label></em> ※ 해당 PG사가 있을 경우 사용으로 변경합니다.</li>
					</ul>
					<ul class="info_form">
						<li>휴대폰 결제 사용</li>
						<li class="checkcss"><em><label><input
									type="radio" class="ckclass" name="use_mobile" value="Y"
									<%=("Y".equals(spdata.getUse_mobile())) ? "checked" : ""%>>
									사용</label></em> <em><label> <input type="radio" class="ckclass"
									name="use_mobile" value="N"
									<%=(!"Y".equals(spdata.getUse_mobile())) ? "checked" : ""%>>
									미사용
							</label></em> ※ 주문시 휴대폰 결제를 가능하게 할 것인지를 설정합니다.</li>
					</ul>
					<ul class="info_form">
						<li>도서상품권 결제사용</li>
						<li class="checkcss"><em><label> <input
									type="radio" class="ckclass" name="use_gift" value="Y"
									<%=("Y".equals(spdata.getUse_gift())) ? "checked" : ""%>>
									사용
							</label></em> <em><label> <input type="radio" class="ckclass"
									name="use_gift" value="N"
									<%=(!"Y".equals(spdata.getUse_gift())) ? "checked" : ""%>>
									미사용
							</label></em> ※ 도서상품권 결제만 적용이 되며, 그 외에 상품권은 결제 되지 않습니다.</li>
					</ul>
					<ul class="info_form2">
						<li>결제 최소 포인트</li>
						<li><input type="text" class="in_form0" maxlength="5" onchange="numvali(this)"
							name="min_p_pay"
							value="<%=spdata.getMin_p_pay() != null ? spdata.getMin_p_pay() : "1000"%>">
							점</li>
						<li>결제 최대 포인트</li>
						<li><input type="text" class="in_form0" maxlength="5" onchange="numvali(this)"
							name="max_p_pay"
							value="<%=spdata.getMax_p_pay() != null ? spdata.getMax_p_pay() : ""%>">
							점</li>
					</ul>
					<ul class="info_form">
						<li>현금 영수증 발급사용</li>
						<li class="checkcss"><em><label><input
									type="radio" class="ckclass" name="use_cash_rec" value="Y"
									<%=("Y".equals(spdata.getUse_cash_rec())) ? "checked" : ""%>>사용</label></em>
							<em><label> <input type="radio" class="ckclass"
									name="use_cash_rec" value="N"
									<%=(!"Y".equals(spdata.getUse_cash_rec())) ? "checked" : ""%>>
									미사용
							</label></em> ※ 현금영수증 관련 사항은 PG사 가입이 되었을 경우 사용가능 합니다.</li>
					</ul>
					<ul class="info_form2">
						<li>배송업체명</li>
						<li><input type="text" class="in_form0" name="shipp_compnm"
							value="<%=spdata.getShipp_compnm() != null ? spdata.getShipp_compnm() : ""%>"></li>
						<li>배송비 가격</li>
						<li><input type="text" class="in_form0" maxlength="7" onchange="numvali(this)"
							name="shipp_fee"
							value="<%=spdata.getShipp_fee() != null ? spdata.getShipp_fee() : ""%>">
							원</li>
					</ul>
					<ul class="info_form"
						style="border-bottom: 1px solid rgb(81, 61, 61);">
						<li>희망배송일</li>
						<li class="checkcss"><em><label><input
									type="radio" class="ckclass" name="des_deliv_date" value="Y"
									<%=("Y".equals(spdata.getDes_deliv_date())) ? "checked" : ""%>>
									사용</label></em> <em><label> <input type="radio" class="ckclass"
									name="des_deliv_date" value="N"
									<%=(!"Y".equals(spdata.getDes_deliv_date())) ? "checked" : ""%>>
									미사용
							</label></em> ※ 희망배송일 사용시 사용자가 직접 설정 할 수 있습니다.</li>
					</ul>
				</div>
				<div class="btn_div">
		
					<button type="button" class="sub_btn1" title="설정 저장"
						onclick="save_set()">설정 저장</button>
			
						
					<button type="button" class="sub_btn2" title="저장 취소"
						onclick="save_cancel()">저장 취소</button>
				</div>
			</section>
			<section></section>
			<section></section>
		</main>
	</form>
	<footer class="main_copyright">
		<div>Copyright ⓒ 2024 shopbag All rights reserved.</div>
	</footer>
</body>
<script src="./admin_siteinfo.js?v=250321"></script>
</html>