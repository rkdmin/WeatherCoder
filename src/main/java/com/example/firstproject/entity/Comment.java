package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentVO;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne// 일대다 관계 설정
    @JoinColumn(name = "articleId")// Article 내의 article_id란 외부키 설정
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

    // entity -> dto
    public CommentVO toDto() {
        return new CommentVO(id, article.getId(), nickname, body);
    }

    public void patch(CommentVO commentDto) {
        if(commentDto.getNickname() != null){ // 데이터에 닉네임 값이 있으면 원래 데이터를 수정
            this.nickname = commentDto.getNickname();
        }
        if(commentDto.getBody() != null){
            this.body = commentDto.getBody();
        }

    }
}
