function writelist(){
	if(confirm("공지사항 등록을 취소하시겠습니까?")){
	location.href="./notice_list.jsp";		
	}
}

function writeok(){
	var f = frm.mfile.files[0];
	
	if(frm.title.value==""){
		alert("제목을 입력하세요.");
	}else if(f){
		 if (f.size > 1024*1024*2) {
            alert("첨부파일은 2MB 이하만 업로드 가능합니다.");
            frm.mfile.value = "";  // 선택 초기화
        }
	}
	
	else if(frm.content.value==""){
		alert("공지내용을 입력하세요.");
	}
	
	else{
		frm.submit()
	}
}

