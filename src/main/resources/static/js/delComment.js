// 삭제버튼들 가져오기
const commentDeleteBtns = document.querySelectorAll(".comment-delete-btn");

// 클릭하면 댓글 삭제
function clickDeleteComment(event){
    // 어떤 댓글의 삭제버튼을 눌렀는지 알기위해 event.srcElement;를 사용
    const commentDeleteBtn = event.target;
    
    // target 삭제버튼의 데이터 가져오기 
    const commentId = commentDeleteBtn.getAttribute("data-comment-id");

    // fetch() - REST API 요청을 JS로 보내준다!
    const URL = `/api/articles/${commentId}/delete-comment`;
    fetch(URL, {
        method : "DELETE",// patch요청
    }).then(response => {
        // http 응답 코드에 따른 메세지 출력
        const msg = (response.ok) ? "댓글이 삭제되었습니다." : "댓글 삭제 실패.";
        alert(msg);
        // 페이지 새로고침 
        window.location.reload();
    });
}

// 버튼들의 각 버튼들 마다 리스너를 넣어줌
commentDeleteBtns.forEach(btn => {
   btn.addEventListener("click", clickDeleteComment); 
});