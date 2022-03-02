/*
    modal event 처리
*/
const commentEditModal = document.querySelector("#comment-edit-modal");

// comment.js의 value들
const editCommentId = document.querySelector("#edit-comment-id");
const editCommentArticleId = document.querySelector("#edit-comment-article-id");
const editCommentNickname = document.querySelector("#edit-comment-nickname");
const editCommentBody = document.querySelector("#edit-comment-body");

function shownModal(event){
    // 트리거 버튼 선택 (보여진 modal의 버튼)
    const triggerBtn = event.relatedTarget;

    // 데이터 가져오기
    const id = triggerBtn.getAttribute("data-bs-id");
    const nickname = triggerBtn.getAttribute("data-bs-nickname");
    const body = triggerBtn.getAttribute("data-bs-body");
    const articleId = triggerBtn.getAttribute("data-bs-article-id");
    console.log(nickname);
    // 데이터 반영
    editCommentId.value = id;
    editCommentArticleId.value = articleId;
    editCommentNickname.value = nickname;
    editCommentBody.value = body;
}

// modal이 열렸을 때 함수 실행
commentEditModal.addEventListener("show.bs.modal", shownModal);


/* 
    수정완료 JSON 데이터 송출
*/
// 수정완료 버튼
const editCommentBtn = document.querySelector("#edit-comment-btn");

function clickEditCommentBtn(){

    // 새 댓글 객체 생성
    const comment = {
        id: editCommentId.value,
        nickname : `${editCommentNickname.value}(수정됨)`,
        body : editCommentBody.value,
        articleId : editCommentArticleId.value
    };

    // 댓글 객체 출력
    console.log(comment);

    // fetch() - REST API 요청을 JS로 보내준다!
    const URL = `/api/articles/${comment.id}/edit-comment`;
    fetch(URL, {
        method : "PATCH",// patch요청
        body : JSON.stringify(comment),
        headers : {
            "Content-Type" : "application/json"
        }
    }).then(response => {
        // http 응답 코드에 따른 메세지 출력
        const msg = (response.ok) ? "댓글이 수정되었습니다." : "댓글 수정 실패.";
        alert(msg);
        // 페이지 새로고침 
        window.location.reload();
    });
  
}

editCommentBtn.addEventListener("click", clickEditCommentBtn);
