package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleVO;
import com.example.firstproject.dto.CommentVO;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class ArticleController {

    // 객체를 Entity를 만들기 위한 Repository 사용
    @Autowired
    private ArticleRepository articleRepository;// 객체를 새로 안만들어도 @Autowired하면 ㄱㅊ
    @Autowired private CommentService commentService;

    // new url 접속
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    // create에 접속하여 데이터를 받음
    @PostMapping("/articles/create")
    public String createArticle(ArticleVO articleVO, RedirectAttributes rttr){

        // 제목이나 내용이 입력되지 않았을 경우
        if(articleVO.getTitle().equals("") || articleVO.getContent().equals("")){
            rttr.addFlashAttribute("msgNULL", "제목 또는 내용을 입력하세요.");
            return "redirect:/articles/new";
        }

        // System.out.println(articleVO.toString());
        log.info(articleVO.toString());

        // 1. Dto를 Entity로 변환
        Article articleEntity = articleVO.toEntity();
        // System.out.println(article.toString()); -> 로깅으로대체
        log.info(articleEntity.toString());

        // 2. Repository에게 Entity를 DB안에 저장하게 함!
        Article saved = articleRepository.save(articleEntity);
        // System.out.println(saved.toString());
        log.info(saved.toString());

        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")// {id}로 통해 url에서 데이터를 가져옴
    public String show(@PathVariable Long id, Model model){// url path로 부터 입력된다는 @PathVariable를 입력
        log.info("id = " + id);

        // 1: id로 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 댓글추가
        List<CommentVO> commentList = commentService.showCommentList(id);// articleId의 모든댓글을 가져옴

        // 2: 가져온 Entity 데이터를 모델에 등록
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentList", commentList);

        // 3: 보여줄 페이지를 설정
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        // 1: 모든 Article을 가져온다.
        List<Article> articleEntityList = articleRepository.findAll();

        // 2: 가저온 Article 묶음을 뷰로 전달!
        model.addAttribute("articleList", articleEntityList);

        // 3: 뷰 페이지를 설정!
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // entity 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 모델 추가
        model.addAttribute("article", articleEntity);

        // 뷰페이지 설정
        return "articles/edit";
    }

    // 수정
    @PostMapping("/articles/update")
    public String update(ArticleVO articleVO){

        log.info(articleVO.toString());// form에 의해 DTO가 초기화 되었나 확인

        // 1. DTO를 Entity로 변경
        Article articleEntity = articleVO.toEntity();
        log.info(articleEntity.toString());// Entity로 잘 변했나 확인

        // 2. 데이터가 비었는지 확인
        Article data = articleRepository.findById(articleEntity.getId()).orElse(null);

        // 3. 데이터가 있으면 기존 값 갱신(수정)
        if(data != null){
            Article saved = articleRepository.save(articleEntity);
            log.info(saved.toString());// DB에 잘 저장됐나 확인
        }
        return "redirect:/articles/" + articleVO.getId();
    }

    // 삭제
    @GetMapping("/articles/{id}/delete")
    public String Delete(@PathVariable Long id, RedirectAttributes rttr){

        // 1. DB속 Entity id로 찾아오기
        Article data = articleRepository.findById(id).orElse(null);

        // 2. 찾아온 Entity가 비어있는지 확인하고, 있다면 삭제하기
        if(data != null){
            articleRepository.delete(data);
            rttr.addFlashAttribute("msg", "삭제완료!");
        }

        // 3. redirect로 데이터 정보를 가진 채 뷰페이지 띄우기
        return "redirect:/articles";
    }

}
