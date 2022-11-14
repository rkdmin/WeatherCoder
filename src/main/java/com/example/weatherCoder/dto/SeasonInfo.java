package com.example.weatherCoder.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SeasonInfo {
    private String seasonName;// 계절 이름
    private List<String> nameList;// 카테고리명 리스트
}
