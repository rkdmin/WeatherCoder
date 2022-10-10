package com.example.firstproject.service;
import com.example.firstproject.dto.ClothesDto1;
import com.example.firstproject.entity.Clothes;
import com.example.firstproject.entity.Suggest;
import com.example.firstproject.exception.SuggestException;
import com.example.firstproject.repository.ClothesRepository;
import com.example.firstproject.repository.SuggestRepository;
import com.example.firstproject.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class SuggestService {
    private final ClothesRepository clothesRepository;
    private final SuggestRepository suggestRepository;

    public List<Long> suggest1(List<ClothesDto1> dtoList) {
        // 예외 처리
        //

        List<Long> idList = new ArrayList<>();
        List<Clothes> clothesList = new ArrayList<>();

        // 최저 기온 옷 추천
        // 겉옷이 필수
        if(dtoList.get(0).isOuter() == true){
            // 최저 기온 옷 추천
            clothesList.add(clothesRepository.suggest1Outer(
                    dtoList.get(0).getTemp(), dtoList.get(0).isOuter(),
                    dtoList.get(0).getGender()
            ));
        }else{// 겉옷이 필수가 아님

            clothesList.add(clothesRepository.suggest1(dtoList.get(0).getTemp(),
                    dtoList.get(0).getGender()));
        }

        // 최고 기온 옷 추천
        clothesList.add(clothesRepository.suggest1(dtoList.get(1).getTemp(),
                dtoList.get(1).getGender()));


        // 알맞은 옷이 없음
        if(clothesList.get(0) == null && clothesList.get(1) == null){
            throw new SuggestException(ErrorCode.CLOTHES_EMPTY);
        }

        // null이 아닌 경우 suggest테이블에 저장
        for(Clothes clothes: clothesList){
            if(clothes != null){
                idList.add(clothes.getId());
                suggestRepository.save(Suggest.builder()
                                .member(null)
                                .clothes(clothes)
                                .build());
            }else{
                idList.add(null);
            }
        }

        // 저장 후 리턴
        return idList;
    }

    public ClothesDto1 suggest2() {
        return null;
    }

    public ClothesDto1 suggest3() {
        return null;
    }
}