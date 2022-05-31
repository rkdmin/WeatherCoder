package com.example.firstproject.entity;
import com.example.firstproject.dto.MemberDto;
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
    private boolean rain;// 비 여부

    @Column
    private int outerwear;// 상의번호

    @Column
    private int top;// 상의번호

    @Column
    private int bottom;// 하의번호

    @Column
    private int style;// 스타일번호

    @Column
    private int height;

    @Column
    private int weight;

    @Column
    private int age;

    @Column
    private String sex;

    // entity -> dto


}