package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CommentVO {
    private Long id;
    private Long articleId;
    private String nickname;
    private String body;

    public Comment toEntity(Article articleEntity) {
        return new Comment(id, articleEntity, nickname, body);
    }
}
