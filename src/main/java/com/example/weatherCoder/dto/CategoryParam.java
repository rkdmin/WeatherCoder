package com.example.weatherCoder.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


public class CategoryParam {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request{
        @NotNull(message = "계절 리스트가 없습니다.")
        private List<SeasonInfo> seasonList;
    }
}
