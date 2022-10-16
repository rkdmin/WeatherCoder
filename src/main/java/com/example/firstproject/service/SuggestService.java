package com.example.firstproject.service;

import com.example.firstproject.dto.ClothesDto1;
import com.example.firstproject.dto.ClothesDto2;
import com.example.firstproject.entity.Clothes;
import com.example.firstproject.entity.Member;
import com.example.firstproject.entity.MemberStyle;
import com.example.firstproject.entity.Suggest;
import com.example.firstproject.exception.SuggestException;
import com.example.firstproject.repository.ClothesRepository;
import com.example.firstproject.repository.MemberRepository;
import com.example.firstproject.repository.MemberStyleRepository;
import com.example.firstproject.repository.SuggestRepository;
import com.example.firstproject.type.SuggestType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    private final MemberStyleRepository memberStyleRepository;

    public List<Long> suggest1(List<ClothesDto1> dtoList) {
        // 예외 처리
        if(dtoList == null){
            throw new SuggestException(INVALID_REQUEST);
        }

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
                                .suggestType(SuggestType.SUGGEST1_NON_MEMBER)
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
        if(CollectionUtils.isEmpty(dtoList)){
            throw new SuggestException(INVALID_REQUEST);
        }

        String email = dtoList.get(0).getEmail();// 회원 이메일
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if(!optionalMember.isPresent()){
            throw new SuggestException(MEMBER_EMPTY);
        }

        // 회원-스타일 전부 불러오기
        List<MemberStyle> memberStyleList
                = memberStyleRepository.findAllByMember_Email(email);
        if(memberStyleList == null){
            throw new SuggestException(INVALID_REQUEST);
        }
        Member member = optionalMember.get();

        List<Long> resultList = new ArrayList<>();
        List<Clothes> clothesList = new ArrayList<>();

        // 최저 기온 옷 추천
        // 겉옷이 필수
        int styleIndex = styleRandom(memberStyleList.size());// 랜덤 인덱스로 스타일 선택
        if(dtoList.get(0).isOuter()){
            // 최저 기온 옷 추천
            clothesList.add(clothesRepository.suggest2Outer(
                    dtoList.get(0).getTemp(), member.getGender(),
                    dtoList.get(0).isOuter(), member.getAge(),
                    member.getHeight(), member.getWeight(),
                    memberStyleList.get(styleIndex).getStyle().getStyleName()
            ));
        }else{// 겉옷이 필수가 아님
            clothesList.add(clothesRepository.suggest2(
                    dtoList.get(0).getTemp(), member.getGender(), member.getAge(),
                    member.getHeight(), member.getWeight(),
                    memberStyleList.get(styleIndex).getStyle().getStyleName()
            ));
        }

        // 최고 기온 옷 추천
        styleIndex = styleRandom(memberStyleList.size());// 랜덤 인덱스로 스타일 선택
        clothesList.add(clothesRepository.suggest2(
                dtoList.get(1).getTemp(), member.getGender(), member.getAge(),
                member.getHeight(), member.getWeight(),
                memberStyleList.get(styleIndex).getStyle().getStyleName()
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
                        .suggestType(SuggestType.SUGGEST2)
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