function bdelete(bno) {
    $.ajax({
        url : "/board/boarddelete",
        data : {"bno" : bno},
        success : function(result){
            if (result == 1){
                alert("삭제 완료");
                location.href = "/board/boardlist";
            } else {
                alert("오류 발생");
            }
        }
    });
}