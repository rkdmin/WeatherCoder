package com.example.firstproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class ClothesDto1 {
    private int temp;// 기온
    private boolean isOuter;// 겉옷번호 0이면 x
    private String gender;// male female
}
