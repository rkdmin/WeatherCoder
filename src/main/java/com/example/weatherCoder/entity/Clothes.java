package com.example.weatherCoder.entity;
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

    private int temp;// 기온

    private boolean isOuter;// 비 여부

    private String gender;// 성별

    private int age;

    private int height;

    private int weight;

    private String styleName;

}