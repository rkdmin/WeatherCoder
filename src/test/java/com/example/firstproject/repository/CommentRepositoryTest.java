package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest// JPA와 연동한 테스트!
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")// 전과 달리 이 어노테이션을 써서 주석 굳
    void findByArticleId() {
        /* Case 1: 4번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 4L;
            // 예상
            Article article = new Article(4L, "당신의 인생 영화는 ?", "댓글 ㄱ");
            Comment a = new Comment(1L, article, "Park", "쇼생크탈출");
            Comment b = new Comment(2L, article, "Lee", "시네마천국");
            Comment c = new Comment(3L, article, "Kim", "명량");
            List<Comment> expected = Arrays.asList(a, b, c);
            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력!");
        }
        /* Case 2: 1번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 1L;
            // 예상
            Article article = new Article(1L, "가가가가", "1111");
            List<Comment> expected = Arrays.asList();// 댓글이 없음
            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 검증
            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없음");
        }
        /* Case 3: 9번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 9L;
            // 예상
            Article article = null;
            List<Comment> expected = Arrays.asList();// 9번 게시물이 없음
            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 검증
            assertEquals(expected.toString(), comments.toString(), "9번 게시글이 없음");
        }
        /* Case 4: 999번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 999L;
            // 예상
            Article article = null;
            List<Comment> expected = Arrays.asList();// 999번 게시글이 없음
            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 검증
            assertEquals(expected.toString(), comments.toString(), "999번 게시글이 없음");
        }
        /* Case 5: -1번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = -1L;
            // 예상
            Article article = null;
            List<Comment> expected = Arrays.asList();// 999번 게시글이 없음
            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 검증
            assertEquals(expected.toString(), comments.toString(), "999번 게시글이 없음");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        /* Case 1: "Park"의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            String nickname = "Park";

            // 실제수행
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 예상하기
            /*
               ID  	BODY  	NICKNAME  	ARTICLE_ID
                1	쇼생크탈출	Park	4
                4	비빔밥	Park	5
                5	어쩔티비	Park	6
            */
            Article article4 = new Article(4L, "당신의 인생 영화는 ?", "댓글 ㄱ");
            Article article5 = new Article(5L, "당신의 소울 푸드는 ?", "댓글 ㄱ");
            Article article6 = new Article(6L, "당신의 취미는 ?", "댓글 ㄱ");
            Comment commentA = new Comment(1L,article4,nickname,"쇼생크탈출");
            Comment commentB = new Comment(4L,article5,nickname,"비빔밥");
            Comment commentC = new Comment(5L,article6,nickname,"어쩔티비");
            List<Comment> expected = Arrays.asList(commentA, commentB, commentC);

            // 검증
            assertEquals(expected.toString(), comments.toString(), "Park의 모든 댓글을 출력!");
        }

        /* Case 2: null의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            String nickname = null;

            // 실제수행
            List<Comment> comments = commentRepository.findByNickname(nickname);

            List<Comment> expected = Arrays.asList();// null 값이므로 없음

            // 검증
            assertEquals(expected.toString(), comments.toString(), "null의 모든 댓글을 출력!");
        }

        /* Case 3: ""의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            String nickname = "";

            // 실제수행
            List<Comment> comments = commentRepository.findByNickname(nickname);

            List<Comment> expected = Arrays.asList();// null 값이므로 없음

            // 검증
            assertEquals(expected.toString(), comments.toString(), "\"\"의 모든 댓글을 출력!");
        }

    }
}