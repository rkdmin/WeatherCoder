package com.example.firstproject.service;
import com.example.firstproject.dto.ClothesDto1;
import com.example.firstproject.dto.ClothesDto2;
import com.example.firstproject.entity.*;
import com.example.firstproject.exception.SuggestException;
import com.example.firstproject.repository.ClothesRepository;
import com.example.firstproject.repository.MemberRepository;
import com.example.firstproject.repository.StyleRegistrationRepository;
import com.example.firstproject.repository.SuggestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.example.firstproject.type.ErrorCode.*;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class SuggestService {
    private final ClothesRepository clothesRepository;
    private final SuggestRepository suggestRepository;
    private final MemberRepository memberRepository;
    private final StyleRegistrationRepository styleRegistrationRepository;

    public List<Long> suggest1(List<ClothesDto1> dtoList) {
        // 예외 처리

        List<Long> resultList = new ArrayList<>();
        List<Clothes> clothesList = new ArrayList<>();

        // 최저 기온 옷 추천
        // 겉옷이 필수
        if(dtoList.get(0).isOuter()){
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
            throw new SuggestException(CLOTHES_EMPTY);
        }

        // null이 아닌 경우 suggest테이블에 저장
        for(Clothes clothes: clothesList){
            if(clothes != null){
                resultList.add(clothes.getId());
                suggestRepository.save(Suggest.builder()
                                .member(null)
                                .clothes(clothes)
                                .build());
            }else{
                resultList.add(null);
            }
        }

        // 저장 후 리턴
        return resultList;
    }

    public List<Long> suggest2(List<ClothesDto2> dtoList) {
        // 예외 처리
        if(dtoList == null){
            throw new SuggestException(INVALID_REQUEST);
        }
        String email = dtoList.get(0).getEmail();// 회원 이메일
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if(!optionalMember.isPresent()){
            throw new SuggestException(MEMBER_EMPTY);
        }

        // 회원-스타일 전부 불러오기
        List<StyleRegistration> styleRegistrationList
                = styleRegistrationRepository.findAllByEmail(email);
        if(styleRegistrationList == null){
            throw new SuggestException(INVALID_REQUEST);
        }
        Member member = optionalMember.get();

        List<Long> resultList = new ArrayList<>();
        List<Clothes> clothesList = new ArrayList<>();

        // 최저 기온 옷 추천
        // 겉옷이 필수
        int styleIndex = styleRandom(styleRegistrationList.size());// 랜덤 인덱스로 스타일 선택
        log.info("{} -> " + styleRegistrationList.size());
        log.info("{} -> " + styleIndex);
        if(dtoList.get(0).isOuter()){
            // 최저 기온 옷 추천
            clothesList.add(clothesRepository.suggest2Outer(
                    dtoList.get(0).getTemp(), member.getGender(),
                    dtoList.get(0).isOuter(), member.getAge(),
                    member.getHeight(), member.getWeight(),
                    styleRegistrationList.get(styleIndex).getStyleName()
            ));
        }else{// 겉옷이 필수가 아님
            clothesList.add(clothesRepository.suggest2(
                    dtoList.get(0).getTemp(), member.getGender(), member.getAge(),
                    member.getHeight(), member.getWeight(),
                    styleRegistrationList.get(styleIndex).getStyleName()
            ));
        }

        // 최고 기온 옷 추천
        styleIndex = styleRandom(styleRegistrationList.size());// 랜덤 인덱스로 스타일 선택
        clothesList.add(clothesRepository.suggest2(
                dtoList.get(1).getTemp(), member.getGender(), member.getAge(),
                member.getHeight(), member.getWeight(),
                styleRegistrationList.get(styleIndex).getStyleName()
        ));

        // 알맞은 옷이 없음
        if(clothesList.get(0) == null && clothesList.get(1) == null){
            throw new SuggestException(CLOTHES_EMPTY);
        }

        // null이 아닌 경우 suggest테이블에 저장
        for(Clothes clothes: clothesList){
            if(clothes != null){
                resultList.add(clothes.getId());
                suggestRepository.save(Suggest.builder()
                        .member(member)
                        .clothes(clothes)
                        .build());
            }else{
                resultList.add(null);
            }
        }

        // 저장 후 리턴
        return resultList;
    }

    private int styleRandom(int size) {
        return (int)(Math.random() * size);
    }

    public ClothesDto1 suggest3() {
        return null;
    }
}