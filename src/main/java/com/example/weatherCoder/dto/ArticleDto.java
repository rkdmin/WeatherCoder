package com.example.weatherCoder.dto;

import com.example.weatherCoder.entity.Article;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class ArticleDto {
    private Long id;

    @NotBlank(message = "제목이 없습니다.")
    private String title;

    @NotNull(message = "내용이 없습니다.")
    private String content;

    public static List<ArticleDto> toDtoList(List<Article> articleList) {
        if(articleList.size() == 0) return null;

        List<ArticleDto> articleDtoList = new ArrayList<>();
        for(Article article: articleList){
            articleDtoList.add(toDto(article));
        }

        return articleDtoList;
    }

    public static ArticleDto toDto(Article article) {
        return ArticleDto.builder()
            .id(article.getId())
            .title(article.getTitle())
            .content(article.getContent())
            .build();
    }
}
