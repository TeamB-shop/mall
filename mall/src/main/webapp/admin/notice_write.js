function writelist() {
	if (confirm("공지사항 등록을 취소하시겠습니까?")) {
		location.href = "./notice_list.jsp";
	}
}

function writelist() {
	if (confirm("공지사항 등록을 취소하시겠습니까?")) {
		location.href = "./notice_list.jsp";
	}
}

function writeok() {
	const title = frm.title.value.trim();
	const content = frm.content.value.trim();
	const fileInput = frm.mfile;
	const file = fileInput.files.length > 0 ? fileInput.files[0] : null;

	if (title === "") {
		alert("제목을 입력하세요.");
		frm.title.focus();
		return;
	}

	if (content === "") {
		alert("공지내용을 입력하세요.");
		frm.content.focus();
		return;
	}

	if (file && file.size > 1024 * 1024 * 2) {
		alert("첨부파일은 2MB 이하만 업로드 가능합니다.");
		fileInput.value = "";  // 파일 초기화
		return;
	}

	frm.submit();
}