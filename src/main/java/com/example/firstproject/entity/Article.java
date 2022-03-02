package com.example.firstproject.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity// DB가 해당 객체를 인식 가능
public class Article {

    @GeneratedValue(strategy = GenerationType.IDENTITY)// DB가 id를 자동 생성 어노테이션
    @Id// 기본키(대표값) 지정
    private Long id;

    @Column
    private String title;

    @Column
    private String content;


    // 요청한 값에 입력하지 않은 값이 있으면 원래있던 데이터가 들어감
    public void patch(Article articleEntity, Article target) {

        if(articleEntity.title == null){
            articleEntity.title = target.title;
        }
        if(articleEntity.content == null){
            articleEntity.content = target.content;
        }
    }
}
