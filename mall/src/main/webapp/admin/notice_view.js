function deleteNotice(midx) {
    if (confirm("정말 삭제하시겠습니까?")) {
        location.href = "./notice_delete.do?midx=" + midx;
    }
}