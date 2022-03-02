package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleVO;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest// 해당 클래스는 스프링부트와 연동되어 테스팅된다
class ArticleServiceTest {

    @Autowired ArticleService articleService;
    @Autowired ArticleRepository articleRepository;


    @Test
    void showList_성공() {
        // 예상
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");
        List<Article> expected =new ArrayList<Article>(Arrays.asList(a,b,c));

        // 실제
        List<Article> articleList = articleService.showList();

        // 비교
        assertEquals(expected.toString(), articleList.toString());
    }

    @Test
    void show_성공____존재하는_id_입력() {

        // 예상
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");
        // 실제
        Article article = articleService.show(id);
        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_실패____존재하지_않는_id_입력() {
        // 예상
        Long id = -1L;
        Article expected = null;// 찾는 id가 없으니 null이 되어야한다.
        // 실제
        Article article = articleService.show(id);
        // 비교
        assertEquals(expected, article);// null은 toString이 안되므로 빼줌
    }

    @Test
    @Transactional
    void create_성공____title과_content만_있는_dto_입력() {
        // 예상
        String title = "테스트";
        String content = "테스트";
        ArticleVO dto = new ArticleVO(null, title, content);
        Article expected = new Article(7L, title, content);
        // 실제
        Article article = articleService.create(dto);
        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void create_실패____id가_포함된_dto가_입력() {
        // 예상
        Long id = 4L;
        String title = "테스트";
        String content = "테스트";
        ArticleVO dto = new ArticleVO(id, title, content);
        Article expected = null;// 아이디가 존재하므로 null이 나와야함
        // 실제
        Article article = articleService.create(dto);
        // 비교
        assertEquals(expected, article);// null은 toString이 안되므로 빼줌
    }

    @Test
    @Transactional
    void update_성공____존재하는_id와_title_content가_있는_dto_입력() {
        // 예상
        Long id = 1L;
        String title = "테스트";
        String content = "테스트";
        ArticleVO dto = new ArticleVO(id, title, content);
        Article expected = new Article(id, title, content);
        // 실제
        Article article = articleService.edit(dto, id);
        // 비교
        assertEquals(expected.toString(), article.toString());// null은 toString이 안되므로 빼줌
    }

    @Test
    @Transactional
    void update_성공____존재하는_id와_title만__있는_dto_입력() {
        // 예상
        Long id = 1L;
        String title = null;
        String content = "테스트";
        ArticleVO dto = new ArticleVO(id, title, content);
        title = articleRepository.findById(id).orElse(null).getTitle();// 원래있던 title
        Article expected = new Article(id, title, content);// 아이디가 존재하므로 null이 나와야함
        // 실제
        Article article = articleService.edit(dto, id);
        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_실패____존재하지_않는_id의_dto_입력() {
        // 예상
        Long id = 4L;
        String title = "테스트";
        String content = "테스트";
        ArticleVO dto = new ArticleVO(id, title, content);
        Article expected = null;// 존재하지 않는 아이디가 존재하므로 null이 나와야함
        // 실제
        Article article = articleService.edit(dto, id);
        // 비교
        assertEquals(expected, article);// null은 toString이 안되므로 빼줌
    }

    @Test
    @Transactional
    void update_실패____id만_있는_dto_입력() {
        // 예상
        Long id = 1L;
        String title = null;
        String content = null;
        ArticleVO dto = new ArticleVO(id, title, content);
        Article expected = null;// 아이디만 존재하므로 null이 나와야함
        // 실제
        Article article = articleService.edit(dto, id);
        // 비교
        assertEquals(expected, article);// null은 toString이 안되므로 빼줌
    }

    @Test
    @Transactional
    void delete_성공____존재하는_id_입력() {
        // 예상
        Long id = 2L;
        String title = articleRepository.findById(id).orElse(null).getTitle();// 원래있던 title
        String content = articleRepository.findById(id).orElse(null).getContent();// 원래있던 content
        Article expected = new Article(id, title, content);// 반환형이 삭제된 Article 데이터임
        System.out.println(expected);
        // 실제
        Article article = articleService.delete(id);
        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void delete_실패____존재하지_않는_id_입력() {
        // 예상
        Long id = 4L;
        Article expected = null;// 없는 아이디가 나오므로 null이 나와야함
        // 실제
        Article article = articleService.delete(id);
        // 비교
        assertEquals(expected, article);// null은 toString이 안되므로 빼줌
    }


}