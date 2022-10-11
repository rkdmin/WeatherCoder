package com.example.firstproject.entity;

import com.example.firstproject.type.SuggestType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Suggest {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Clothes clothes;

    @Enumerated(EnumType.STRING)
    private SuggestType suggestType;
}
