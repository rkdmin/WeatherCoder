package com.example.weatherCoder.dto;

import com.example.weatherCoder.entity.Article;
import com.example.weatherCoder.entity.Comment;
import javax.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CommentDto {
    private Long id;
    private Long articleId;

    @NotBlank(message = "닉네임이 없습니다.")
    private String nickname;
    @NotBlank(message = "내용이 없습니다.")
    private String body;

    public CommentDto toDto(Comment comment) {
        return CommentDto.builder()
            .id(comment.getId())
            .articleId(comment.getArticle().getId())
            .nickname(comment.getNickname())
            .body(comment.getBody())
            .build();
    }
}
