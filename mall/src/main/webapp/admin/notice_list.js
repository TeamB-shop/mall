function chkall(chk) {
    const cks = document.querySelectorAll('.rowchk');
    cks.forEach(function(checkbox) {
        checkbox.checked = chk.checked;
    });

}
    function deletenotice() {
        var checkboxes = document.querySelectorAll('.rowchk');
        var selectedIds = [];
        checkboxes.forEach(function(checkbox) {
            if (checkbox.checked) {
                selectedIds.push(checkbox.value);
            }
        });
        if (selectedIds.length === 0) {
            alert("삭제할 공지를 선택해주세요.");
            return;
        }
        if (!confirm("선택한 공지를 삭제하시겠습니까?")) {
            return;
        }
        // GET 방식으로 여러 id 전송: 예) notice_delete.do?ids=1&ids=2&ids=3
        var queryString = selectedIds.map(function(id) {
            return "ids=" + encodeURIComponent(id);
        }).join("&");

        location.href = "./notice_delete.do?" + queryString;
    }