const createCommentBtn = document.querySelector("#create-comment-btn");

function clickCreateCommentBtn(){

    // 새 댓글 객체 생성
    const comment = {
        nickname : document.querySelector("#new-comment-nickname").value,
        body : document.querySelector("#new-comment-body").value,
        articleId : document.querySelector("#new-comment-article-id").value
    }

    // 댓글 객체 출력
    console.log(comment);

    // fetch() - REST API 요청을 JS로 보내준다!
    const URL = `/api/articles/${comment.articleId}/new-comment`;
    fetch(URL, {
        method : "post",// post요청
        body : JSON.stringify(comment),
        headers : {
            "Content-Type" : "application/json"
        }
    }).then(response => {
        // http 응답 코드에 따른 메세지 출력
        const msg = (response.ok) ? "댓글이 등록되었습니다." : "댓글 등록 실패.";
        alert(msg);
        // 페이지 새로고침 
        window.location.reload();
    });
}

createCommentBtn.addEventListener("click", clickCreateCommentBtn);

