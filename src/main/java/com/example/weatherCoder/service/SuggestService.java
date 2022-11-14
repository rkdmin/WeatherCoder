package com.example.weatherCoder.service;

import static com.example.weatherCoder.type.ErrorCode.CATEGORY_EMPTY;
import static com.example.weatherCoder.type.ErrorCode.CLOTHES_EMPTY;
import static com.example.weatherCoder.type.ErrorCode.INVALID_REQUEST;
import static com.example.weatherCoder.type.ErrorCode.MEMBER_EMPTY;

import com.example.weatherCoder.dto.ClothesDto1;
import com.example.weatherCoder.dto.ClothesDto2;
import com.example.weatherCoder.entity.Clothes;
import com.example.weatherCoder.entity.Member;
import com.example.weatherCoder.entity.MemberCategory;
import com.example.weatherCoder.entity.MemberStyle;
import com.example.weatherCoder.entity.Suggest;
import com.example.weatherCoder.exception.SuggestException;
import com.example.weatherCoder.repository.ClothesCategoryRepository;
import com.example.weatherCoder.repository.ClothesRepository;
import com.example.weatherCoder.repository.MemberCategoryRepository;
import com.example.weatherCoder.repository.MemberRepository;
import com.example.weatherCoder.repository.MemberStyleRepository;
import com.example.weatherCoder.repository.SuggestRepository;
import com.example.weatherCoder.type.SuggestType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class SuggestService {
    private final ClothesRepository clothesRepository;
    private final SuggestRepository suggestRepository;
    private final MemberRepository memberRepository;
    private final MemberStyleRepository memberStyleRepository;
    private final MemberCategoryRepository memberCategoryRepository;
    private final ClothesCategoryRepository clothesCategoryRepository;
    private ClothesDto2 lowTempDto;
    private ClothesDto2 highTempDto;

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
        if(dtoList.size() == 0){
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
        if(memberStyleList.size() == 0){
            throw new SuggestException(INVALID_REQUEST);
        }
        Member member = optionalMember.get();

        List<Long> resultList = new ArrayList<>();
        List<Clothes> clothesList = new ArrayList<>();

        // 최저 기온 옷 추천
        // 겉옷이 필수
        int styleIndex = getRandomIndex(memberStyleList.size());// 랜덤 인덱스로 스타일 선택
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
        styleIndex = getRandomIndex(memberStyleList.size());// 랜덤 인덱스로 스타일 선택
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

    private int getRandomIndex(int size) {
        return (int)(Math.random() * size);
    }

    public List<Long> suggest3(List<ClothesDto2> dtoList) {
        if(dtoList.size() == 0){
            throw new SuggestException(INVALID_REQUEST);
        }
        lowTempDto = dtoList.get(0);
        highTempDto = dtoList.get(1);

        String email = lowTempDto.getEmail();// 회원 이메일
        // 해당 member의 category 모두 불러오기
        List<MemberCategory> memberCategoryList = memberCategoryRepository.findAllByMember_Email(email);
        if(memberCategoryList.size() == 0){
            throw new SuggestException(CATEGORY_EMPTY, "카테고리를 등록해 주세요");
        }
        Member member = memberCategoryList.get(0).getMember();// 회원 정보

        // 카테고리 아이디로 같은 카테고리를 가진 옷들을 찾음
        Set<Long> clothesIdSet = new HashSet();// 중복방지용 set
        for(MemberCategory memberCategory: memberCategoryList){
            List<Long> clothesList =
            clothesCategoryRepository.getClothesIdByCategoryId(memberCategory.getCategory().getId());
            for(Long clothesId : clothesList){
                clothesIdSet.add(clothesId);
            }
        }
        if(CollectionUtils.isEmpty(clothesIdSet)){
            throw new SuggestException(CATEGORY_EMPTY);
        }

        System.out.println(clothesIdSet);

        List<Long> resultList = new ArrayList<>();
        List<Clothes> clothesListLow = new ArrayList<>();
        List<Clothes> clothesListHigh = new ArrayList<>();

        // 최저 기온 옷 추천
        for(long clothesId: clothesIdSet){
            // 겉옷이 필수
            if(lowTempDto.isOuter()){
                // 최저 기온 옷 추천
                Optional<Clothes> optionalClothes =
                    clothesRepository.suggest3Outer(lowTempDto.getTemp(), member.getGender(), true, clothesId);
                if(!optionalClothes.isEmpty()){
                    clothesListLow.add(optionalClothes.get());
                }
            }else{// 겉옷이 필수가 아님
                Optional<Clothes> optionalClothes =
                    clothesRepository.suggest3(lowTempDto.getTemp(), member.getGender(), clothesId);
                if(!optionalClothes.isEmpty()){
                    clothesListLow.add(optionalClothes.get());
                }
            }

            // 최고 기온 옷 추천
            Optional<Clothes> optionalClothes =
                clothesRepository.suggest3(highTempDto.getTemp(), member.getGender(), clothesId);
            if(!optionalClothes.isEmpty()){
                clothesListHigh.add(optionalClothes.get());
            }
        }

        // 알맞은 옷이 없음
        if(clothesListLow.size() == 0 && clothesListHigh.size() == 0){
            throw new SuggestException(CLOTHES_EMPTY);
        }

        // 뽑아온 최저옷과 최고옷 중 랜덤으로 하나씩 삽입
        List<Clothes> clothesList = new ArrayList<>();
        clothesList.add(clothesListLow.get(getRandomIndex(clothesListLow.size())));
        clothesList.add(clothesListHigh.get(getRandomIndex(clothesListHigh.size())));

        // null이 아닌 경우 suggest테이블에 저장
        for(Clothes clothes: clothesList){
            if(clothes != null){
                resultList.add(clothes.getId());
                suggestRepository.save(Suggest.builder()
                    .member(member)
                    .clothes(clothes)
                    .suggestType(SuggestType.SUGGEST3)
                    .build());
            }else{
                resultList.add(null);
            }
        }

        // 저장 후 리턴
        return resultList;
    }
}