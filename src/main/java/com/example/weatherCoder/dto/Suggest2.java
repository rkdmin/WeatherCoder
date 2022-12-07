package com.example.weatherCoder.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


public class Suggest2 {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request{
        @NotNull(message = "최저기온이 없습니다.")
        private Integer lowTemp;// 최저기온
        @NotNull(message = "최고기온이 없습니다.")
        private Integer highTemp;// 최고기온

        // weatherDto => clothesDtoList
        public List<ClothesDto2> toClothesDtoList(String username){
            List<ClothesDto2> clothesDto2List = new ArrayList<>();

            // 최저기온 옷 넣기
            ClothesDto2 clothesDto2Low;
            // 23도(반팔입을온도) 보다 최고 기온이 높다면 최저기온에서 겉옷 추가
            if(highTemp >= 23){
                clothesDto2Low = new ClothesDto2(lowTemp, true, username);
            }else{
                clothesDto2Low = new ClothesDto2(lowTemp, false, username);
            }

            // 최고기온 옷 넣기
            ClothesDto2 clothesDto2High = new ClothesDto2(highTemp, false,
                username);

            clothesDto2List.add(clothesDto2Low);
            clothesDto2List.add(clothesDto2High);

            return clothesDto2List;
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
                    result.add("https://res.cloudinary.com/dohqejduh/image/upload/v1666072183/" + item.toString() + ".jpg");
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
