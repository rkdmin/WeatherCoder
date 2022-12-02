package com.example.weatherCoder.service;

import com.example.weatherCoder.dto.ArticleDto;
import com.example.weatherCoder.entity.Article;
import com.example.weatherCoder.exception.ArticleException;
import com.example.weatherCoder.repository.ArticleRepository;
import com.example.weatherCoder.type.ErrorCode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service// 서비스 선언!(서비스 객체를 스프링부트에 생성)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    // 전체검색
    public List<ArticleDto> showList() {
        List<Article> articleList = articleRepository.findAll();

        return ArticleDto.toDtoList(articleList);
    }

    // 단일검색
    public ArticleDto show(Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if(!optionalArticle.isPresent()){
            throw new ArticleException(ErrorCode.ARTICLE_EMPTY);
        }

        return ArticleDto.toDto(optionalArticle.get());
    }

    // 생성
    public void create(ArticleDto dto) {
        Article article = Article.toEntity(dto);
        if(article.getId() != null){// db가 알아서 id 설정하는데 id값이 있으면 안됨.
            throw new ArticleException(ErrorCode.EXIST_ID);
        }

        articleRepository.save(article);
    }

    // 수정
    public void edit(@RequestBody ArticleDto dto, @PathVariable Long id){
        // 1. 수정용 엔티티 생성
        Article article = Article.toEntity(dto);
        article.setId(id);// 수정할 게시물의 id 설정

        // 2. id로 DB에서 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 응답(대상이 없거나, id가 다른 경우 400)
        if(target == null || id != article.getId() ||
            (article.getTitle() == null && article.getContent() == null)){
            throw new ArticleException(ErrorCode.INVALID_REQUEST);
        }

        // 수정할 데이터에 title이나 content가 비어있을경우 원래 title과 content를 넣어준다.
        article.patch(article, target);
        articleRepository.save(article);
    }

    // 삭제
    public void delete(@PathVariable Long id){
        Optional<Article> optionalArticle = articleRepository.findById(id);

        // 값이 없는 경우
        if(!optionalArticle.isPresent()){
            throw new ArticleException(ErrorCode.INVALID_REQUEST);
        }

        // 3. 잘 된 요청
        articleRepository.delete(optionalArticle.get());
    }

    // transactionTest
    @Transactional// 해당 메소드를 트랜잭션으로 묶는다!(실패되면 롤백)
    public List<Article> createArticles(List<ArticleDto> dtos) {
        // dto 묶음을 entity 묶음으로 변환
        List<Article> articleList = dtos.stream()
                .map(dto -> Article.toEntity(dto))
                .collect(Collectors.toList());

        // entitiy 묶음을 DB로 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

        // 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결제 실패")
        );
        // 결과값 반환
        return articleList;
    }

    /**
     * 제목으로 게시글 검색
     * @param pageable
     * @return
     */
    public Page<ArticleDto> showListByTitle(String title, Pageable pageable) {
        return articleRepository.findByTitleContaining(title, pageable).map(article -> ArticleDto.toDto(article));
    }
}
