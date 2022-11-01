package com.example.weatherCoder.dto;

import com.example.weatherCoder.entity.Article;
import com.example.weatherCoder.entity.Comment;
import java.util.ArrayList;
import java.util.List;
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

    public static List<CommentDto> toDtoList(List<Comment> commentList) {
        List<CommentDto> commentDtoList = new ArrayList<>();
        for(Comment comment: commentList){
            commentDtoList.add(toDto(comment));
        }

        return commentDtoList;
    }

    public static CommentDto toDto(Comment comment) {
        return CommentDto.builder()
            .id(comment.getId())
            .articleId(comment.getArticle().getId())
            .nickname(comment.getNickname())
            .body(comment.getBody())
            .build();
    }
}
