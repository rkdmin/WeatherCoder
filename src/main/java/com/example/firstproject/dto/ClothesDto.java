package com.example.firstproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class ClothesDto {
    private Long id;

    private int temp;// 기온

    private boolean rain;// 비 여부

    private int outerwear;// 겉옷번호 0이면 x

    private int top;// 상의번호

    private int bottom;// 하의번호

    private int style;// 스타일번호

    private int height;

    private int weight;

    private int age;

    private String sex;// male female

    // entity -> dto

}
