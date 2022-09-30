package com.example.firstproject.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


public class Suggest1 {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request{
        @NotNull
        private int lowTemp;// 최저기온
        @NotNull
        private int highTemp;// 최고기온
        @NotNull
        private String gender;// male female

        // weatherDto => clothesDtoList
        public List<ClothesDto1> toClothesDtoList(){
            List<ClothesDto1> clothesDto1List = new ArrayList<>();

            // 최저기온 옷 넣기
            ClothesDto1 clothesDto1Low;
            // 23도(반팔입을온도) 보다 최고 기온이 높다면 최저기온에서 겉옷 추가
            if(highTemp >= 23){
                clothesDto1Low = new ClothesDto1(lowTemp, false,
                        gender);
            }else{
                clothesDto1Low = new ClothesDto1(lowTemp, true,
                        gender);
            }

            // 최고기온 옷 넣기
            ClothesDto1 clothesDto1High = new ClothesDto1(highTemp, false,
                    gender);

            clothesDto1List.add(clothesDto1Low);
            clothesDto1List.add(clothesDto1High);

            return clothesDto1List;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response{
        private List<String> clothesList;

        public static Response toResponse(List<Long> list){
            List<String> result = new ArrayList<>();

            for(Long item: list){
                if(item != null){
                    result.add("http://localhost:8080/img/" + item.toString());
                }else{
                    result.add(null);
                }
            }

            return Response.builder()
                    .clothesList(result)
                    .build();
        }
    }
}
