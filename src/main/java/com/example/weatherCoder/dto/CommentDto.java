package com.example.weatherCoder.dto;

import com.example.weatherCoder.entity.Article;
import com.example.weatherCoder.entity.Comment;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CommentDto {
    private Long id;
    private Long articleId;
    private String nickname;
    private String body;

    public Comment toEntity(Article articleEntity) {
        return new Comment(id, articleEntity, nickname, body);
    }
}
