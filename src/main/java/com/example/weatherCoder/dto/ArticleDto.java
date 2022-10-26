package com.example.weatherCoder.dto;

import com.example.weatherCoder.entity.Article;
import lombok.*;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class ArticleDto {
    private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
