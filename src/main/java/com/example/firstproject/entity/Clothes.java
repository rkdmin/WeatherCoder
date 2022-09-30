package com.example.firstproject.entity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Clothes {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column
    private int temp;// 기온

    @Column
    private boolean isOuter;// 비 여부

    @Column
    private String gender;// 성별

}