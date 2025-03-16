function writelist(){
	location.href='./write_list.jsp';
}

function writeok(){
	if(frm.subject.value==""){
		alert('제목을 입력해 주세요.');
		frm.subject.focus();
	}
	else if(frm.text.value==""){
		alert('내용을 입력해 주세요.');
		frm.text.focus();
	}
}